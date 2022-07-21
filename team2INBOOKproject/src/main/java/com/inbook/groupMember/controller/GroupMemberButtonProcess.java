package com.inbook.groupMember.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;
import com.webjjang.util.PageObject;

public class GroupMemberButtonProcess {

	
	
	GroupMemberServiceImport friendServiceImport;
	
	public void setFriendServiceImport(GroupMemberServiceImport friendServiceImport) {
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
	
	//친구수락
	public String friendRequestAcceptDo(HttpServletRequest request) throws Exception {
		String friendRequestAccept = request.getParameter("friendRequestAccept");
		
		String hostId =((LoginVO)request.getSession().getAttribute("login")).getId();
		
		String friendId = request.getParameter("requestAcceptID");
		int result = 0;
		
		result = (Integer)Execute.service(friendServiceImport.getFriendRequestAcceptService(), setVO(hostId, friendId));
		
		//추가 마이페이지일경우 바로 리턴
		int pos = friendRequestAccept.indexOf("/", 1);
		if(pos >= 0 ) {
				if(result != 3) {
					return setJspOtherPage(friendRequestAccept, result, friendId);
				}
			}
		
		if(hostId == friendId) {
			result = 3;
			return setJsp(request, friendRequestAccept,result);
		}
		
		return setJsp(request, friendRequestAccept,result);
		
		
	}
	
	
	public String friendRequestRefuseDo(HttpServletRequest request) throws Exception {
		String friendRequestRefuse = request.getParameter("friendRequestRefuse");
		
		
		String hostId = request.getParameter("requestRefuseID");
		String friendId =((LoginVO)request.getSession().getAttribute("login")).getId();
		
		int result = 0;
		result = (Integer)Execute.service(friendServiceImport.getFriendRequestRefuseService(), setVO(hostId, friendId));
		
		//추가 마이페이지일경우 바로 리턴
		int pos = friendRequestRefuse.indexOf("/", 1);
		if(pos >= 0 ) {
				if(result != 3) {
					return setJspOtherPage(friendRequestRefuse, result, friendId);
				}
			}
		
		if(hostId == friendId) {
			result = 3;
			return setJsp(request, friendRequestRefuse,result);
		}
		
		return setJsp(request, friendRequestRefuse,result);
	}

	
	public String friendRequestDeleteDo(HttpServletRequest request) throws Exception {
		String friendDelete = request.getParameter("friendDelete");
		
		
		String hostId =((LoginVO)request.getSession().getAttribute("login")).getId();
		String friendId = request.getParameter("requestDeleteID");
		
		int result = 0;
		result = (Integer)Execute.service(friendServiceImport.getFriendDeleteService(), setVO(hostId, friendId));
		
		int pos = friendDelete.indexOf("/", 1);
		if(pos >= 0 ) {
				if(result != 3) {
					return setJspOtherPage(friendDelete, result, friendId);
				}
			}
		
		
		if(hostId == friendId) {
			result = 3;
			return setJsp(request, friendDelete,result);
		}
				
		return setJsp(request, friendDelete,result);
		
	}
	
	
	public String friendRequestCancelDo(HttpServletRequest request) throws Exception {
		String friendRequestCancel = request.getParameter("friendRequestCancel");
		
		String hostId =((LoginVO)request.getSession().getAttribute("login")).getId();
		String friendId = request.getParameter("requestCancelID");
		int result = 0;
		result = (Integer)Execute.service(friendServiceImport.getFriendRequestCancelService(), setVO(hostId, friendId));
		
		
		int pos = friendRequestCancel.indexOf("/", 1);
		if(pos >= 0 ) {
				if(result != 3) {
					return setJspOtherPage(friendRequestCancel, result, friendId);
				}
			}
		
		if(hostId == friendId) {
			result = 3;
			return setJsp(request, friendRequestCancel,result);
		}
		
				
		return setJsp(request, friendRequestCancel,result);
		
	}
	
	
	public String friendRequestSendDo(HttpServletRequest request) throws Exception {
		String friendRequestSend = request.getParameter("friendRequestSend");
		
		String hostId =((LoginVO)request.getSession().getAttribute("login")).getId();
		String friendId = request.getParameter("requestSendID");
		System.out.println("hostID :"+hostId+"friendId"+friendId);
		int result = 0;
		
		result = (int)Execute.service(friendServiceImport.getFriendRequestSendService(), setVO(hostId, friendId));
		
		int pos = friendRequestSend.indexOf("/", 1);
		if(pos >= 0 ) {
				if(result != 3) {
					return setJspOtherPage(friendRequestSend, result, friendId);
				}
			}
		
		if(hostId == friendId) {
			result = 3;
			return setJsp(request, friendRequestSend,result);
		}
		
				
		return setJsp(request, friendRequestSend,result);
		
		
	}
}
