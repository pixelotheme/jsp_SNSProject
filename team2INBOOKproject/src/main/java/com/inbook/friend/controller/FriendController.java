package com.inbook.friend.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.main.Service;
import com.inbook.member.vo.LoginVO;
import com.webjjang.util.PageObject;

public class FriendController implements Controller {

	FriendServiceImport friendServiceImport;
	ButtonProcess buttonProcess;
	
	public void setFriendServiceImport(FriendServiceImport friendServiceImport) {
		this.friendServiceImport = friendServiceImport;
	}

	public void setButtonProcess(ButtonProcess buttonProcess) {
		this.buttonProcess = buttonProcess;
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception{
		
		System.out.println("friendController.execute()- 게싶판 처리하고 있다");
		
		String jsp = null;
		
		String uri = request.getServletPath();
		
		String serviceUri = uri.substring(uri.indexOf("/", 1));
		System.out.println("friendController.execute().serviceUri - "+serviceUri);
		
		switch (serviceUri) {
		case "/list.do":
			PageObject pageObject = PageObject.getInstance(request);
			
			String id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			request.setAttribute("list", Execute.service(friendServiceImport.getFriendListService(), new Object[] {pageObject,id}));
			request.setAttribute("pageObject", pageObject);

			jsp = "friend/list";
			break;
			//일반게시판 글쓰기 폼
		case "/search.do":
			pageObject = PageObject.getInstance(request);
			System.out.println(" word값 찍기 : "+pageObject.getWord());
			id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			request.setAttribute("searchList", Execute.service(friendServiceImport.getFriendSearchService(), pageObject));
			request.setAttribute("friendIdList", Execute.service(friendServiceImport.getFriendIdListService(), id));
			request.setAttribute("friendRequestSendId", Execute.service(friendServiceImport.getFriendRequestSendIdService(), id));
			request.setAttribute("friendRequestId", Execute.service(friendServiceImport.getFriendRequestIdService(), id));
			
			request.setAttribute("pageObject", pageObject);
			
			jsp = "friend/search";
			break;
		
		case "/friendRequestList.do":
				//친구요청 받은 페이지로 가는 코드
			pageObject = PageObject.getInstance(request);
			
			id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			request.setAttribute("friendRequestList", Execute.service(friendServiceImport.getFriendRequestIdListService(), new Object[] {pageObject,id}));
			request.setAttribute("pageObject", pageObject);

			jsp = "friend/friendRequestList";
			
			break;

			
		case "/friendRequestSend.do":
			//친구요청
			jsp = buttonProcess.friendRequestSendDo(request);
			
			break;
			
			case "/friendRequestAccept.do":
				//친구 수락
				jsp = buttonProcess.friendRequestAcceptDo(request);
				
				break;
			case "/friendRequestCancel.do":
				//친구신청 취소
				
				jsp = buttonProcess.friendRequestCancelDo(request);
				
				break;
			case "/friendRequestRefuse.do":
				//친구거절버튼눌렀을때
				
				jsp = buttonProcess.friendRequestRefuseDo(request);
			    
				break;
				
				
			case "/friendDelete.do":
				//친구삭제
			    jsp = buttonProcess.friendRequestDeleteDo(request);
				
				break;
			case "/suggestions.do":
				pageObject = PageObject.getInstance(request);
				
				id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
				//1. 친구가져오기
				List<FriendVO> friendIdList = (List<FriendVO>) Execute.service(friendServiceImport.getFriendIdListService(), id);
				request.setAttribute("friendIdList", friendIdList);
				String[] friendIdArray = null;
				
				if(friendIdList ==null || friendIdList.equals("")) {
					List<FriendVO> SuggestionsId = (List<FriendVO>)Execute.service(friendServiceImport.getFriendAllUserListService(), new Object[] {pageObject,id});
					request.setAttribute("SuggestionsId", SuggestionsId);
					
				}
				else {
					friendIdArray = friendIdList.toArray(new String[friendIdList.size()]);
					List<FriendVO> SuggestionsId = (List<FriendVO>) Execute.service(friendServiceImport.getFriendSuggestionsService(), new Object[] {pageObject,id,friendIdArray});
					
					if(SuggestionsId ==null || SuggestionsId.equals("")) {
						SuggestionsId = (List<FriendVO>)Execute.service(friendServiceImport.getFriendAllUserListService(), new Object[] {pageObject,id});
						request.setAttribute("SuggestionsId", SuggestionsId);
					}
					else {
						request.setAttribute("SuggestionsId", SuggestionsId);
						pageObject.setTotalRow(SuggestionsId.size());
						
					}
				}
				//친구신청보낸정보, 친구신청 받은정보 가져오기
				request.setAttribute("friendRequestSendId", Execute.service(friendServiceImport.getFriendRequestSendIdService(), id));
				request.setAttribute("friendRequestId", Execute.service(friendServiceImport.getFriendRequestIdService(), id));
				request.setAttribute("pageObject", pageObject);
				//
				
				jsp = "friend/suggestions";
				
				break;
			
		default:
			throw new Exception("잘못된 페이지를 요청하셨습니다");
		}
		


		
		return jsp;
	}






}
