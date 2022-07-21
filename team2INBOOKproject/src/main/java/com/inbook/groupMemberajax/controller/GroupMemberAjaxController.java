package com.inbook.groupMemberajax.controller;

import javax.servlet.http.HttpServletRequest;

import com.inbook.friend.service.FriendRequestCountService;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;

public class GroupMemberAjaxController implements Controller {

	private FriendRequestCountService friendRequestCountService;

	
	public void setFriendRequestCountService(FriendRequestCountService friendRequestCountService) {
		this.friendRequestCountService = friendRequestCountService;
	}


	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
//		System.out.println("AjaxController.execute()- Ajax 처리하고 있다");
		
		String jsp = null;
		
		String uri = request.getServletPath();
		
		String serviceUri = uri.substring(uri.indexOf("/", 1));
//		System.out.println("AjaxController.execute().serviceUri - "+serviceUri);
		
		switch (serviceUri) {
		
		case "/friendRequestCount.do":
			
//			System.out.println("AjaxController - 친구신청 체크 처리중");
			String id = ((LoginVO)request.getSession().getAttribute("login")).getId();
//			System.out.println("아이디 : "+id);
			int log = 0;
			request.setAttribute("cnt", Execute.service(friendRequestCountService, id, log));
			jsp = "ajax/friendRequestCount";
			break;
			
			

		default:
			break;
		}
		
		return jsp;
	}







}
