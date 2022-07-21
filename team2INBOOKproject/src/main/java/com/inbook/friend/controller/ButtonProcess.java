package com.inbook.friend.controller;

import javax.servlet.http.HttpServletRequest;


import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;
import com.webjjang.util.PageObject;

public class ButtonProcess {

	
	
	FriendServiceImport friendServiceImport;
	
	public void setFriendServiceImport(FriendServiceImport friendServiceImport) {
		this.friendServiceImport = friendServiceImport;
	}

	private String setJsp(HttpServletRequest request,String url, Integer result) throws Exception {
		PageObject pageObject = PageObject.getInstance(request);
		
		request.setAttribute("pageObject", pageObject);
		
		String word = java.net.URLEncoder.encode(pageObject.getWord(), "utf-8");
		
		System.out.println("ButtonProcess.word 값 확인 : "+pageObject.getWord());
		
		String jsp= "redirect:"+url+".do?page="+pageObject.getPage()+"&perPageNum="
				+pageObject.getPerPageNum()+"&key="
				+pageObject.getKey()+"&word="+word+"&result="+result;
		System.out.println("넘어가는 jsp : "+jsp);
		return jsp;
	}
	
	private String setJspOtherPage(String url, Integer result, String id) throws Exception {
		
		//뒤에 커스텀된 파라미터값이 있다면 슬레쉬 2개로 redirect 붙여준다
		String jsp= "redirect:"+url+".do?id="+id+"&result="+result;
		
		return jsp;
	}
	
	
	
	
	private FriendVO setVO(String hostId,String friendId) {
		FriendVO vo = new FriendVO();
	    vo.setHostId(hostId);
	    vo.setFriendId(friendId);
	    return vo;
	}
	
	private String checkJspOtherPage(HttpServletRequest request, String url, int result, String friendId, String hostId) throws Exception {
		//추가 마이페이지일경우 바로 리턴, 마이 페이지는 슬레시 2개로 넘어옴/member/mypage2
				int pos = url.indexOf("/", 1);
				//슬레시 있으면 다른페이지에서 온것
				if(pos >= 0 ) {
					
						if(result != 3) {
							return setJspOtherPage(url, result, friendId);
						}
				}
				//나자신에 대한 신청... 막기
				if(hostId == friendId) {
					result = 3;
					return setJsp(request, url,result);
				}
				//friend 에서 넘어오면 슬레시 없음 ex) search 로 넘어옴
				return setJsp(request, url,result);
				
	}
	
	//친구수락
	public String friendRequestAcceptDo(HttpServletRequest request) throws Exception {
		//url
		String friendRequestAccept = request.getParameter("friendRequestAccept");
		
		String hostId =((LoginVO)request.getSession().getAttribute("login")).getId();
		
		String friendId = request.getParameter("requestAcceptID");
		int result = 0;
		
		//result가 3 이면 db 중복 오류
		result = (Integer)Execute.service(friendServiceImport.getFriendRequestAcceptService(), setVO(hostId, friendId));
		
		
		return checkJspOtherPage(request, friendRequestAccept, result, friendId, hostId);
		
	}
	
	
	public String friendRequestRefuseDo(HttpServletRequest request) throws Exception {
		String friendRequestRefuse = request.getParameter("friendRequestRefuse");
		
		
		String hostId = request.getParameter("requestRefuseID");
		String friendId =((LoginVO)request.getSession().getAttribute("login")).getId();
		
		int result = 0;
		result = (Integer)Execute.service(friendServiceImport.getFriendRequestRefuseService(), setVO(hostId, friendId));
		
		//추가 마이페이지일경우 바로 리턴
		return checkJspOtherPage(request, friendRequestRefuse, result, friendId, hostId);
	}

	
	public String friendRequestDeleteDo(HttpServletRequest request) throws Exception {
		String friendDelete = request.getParameter("friendDelete");
		
		
		String hostId =((LoginVO)request.getSession().getAttribute("login")).getId();
		String friendId = request.getParameter("requestDeleteID");
		
		int result = 0;
		result = (Integer)Execute.service(friendServiceImport.getFriendDeleteService(), setVO(hostId, friendId));
		
		return checkJspOtherPage(request, friendDelete, result, friendId, hostId);
		
	}
	
	
	public String friendRequestCancelDo(HttpServletRequest request) throws Exception {
		String friendRequestCancel = request.getParameter("friendRequestCancel");
		
		String hostId =((LoginVO)request.getSession().getAttribute("login")).getId();
		String friendId = request.getParameter("requestCancelID");
		int result = 0;
		result = (Integer)Execute.service(friendServiceImport.getFriendRequestCancelService(), setVO(hostId, friendId));
		
		return checkJspOtherPage(request, friendRequestCancel, result, friendId, hostId);
	}
	
	
	public String friendRequestSendDo(HttpServletRequest request) throws Exception {
		String friendRequestSend = request.getParameter("friendRequestSend");
		
		String hostId =((LoginVO)request.getSession().getAttribute("login")).getId();
		String friendId = request.getParameter("requestSendID");
		System.out.println("hostID :"+hostId+"friendId"+friendId);
		int result = 0;
		
		result = (int)Execute.service(friendServiceImport.getFriendRequestSendService(), setVO(hostId, friendId));
		return checkJspOtherPage(request, friendRequestSend, result, friendId, hostId);
		
		
	}
}
