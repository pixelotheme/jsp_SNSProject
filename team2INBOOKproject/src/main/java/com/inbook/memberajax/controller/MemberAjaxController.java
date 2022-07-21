package com.inbook.memberajax.controller;

import javax.servlet.http.HttpServletRequest;

import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.main.Service;
import com.inbook.member.service.MemberIdCheckService;
import com.inbook.member.vo.LoginVO;

public class MemberAjaxController implements Controller {

	private MemberIdCheckService memberIdCheckService;
	
	
	public void setMemberIdCheckService(MemberIdCheckService memberIdCheckService) {
		this.memberIdCheckService = memberIdCheckService;
	}

	
	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AjaxController.execute() - Ajax 처리하고 있다.");
		
		String jsp = null;
		
		// uri - /board/list.do - 처리 service 결정하는 - /list.do
		String uri = request.getServletPath();
		String serviceUri = uri.substring(uri.indexOf("/", 1));
		System.out.println("AjaxController.execute().serviceUri - " + serviceUri);
		
		switch (serviceUri) {
		case "/idCheck.do":
			System.out.println("AjaxContoller - 아이디 체크 처리 중");
			int log = 0;
			request.setAttribute("id", Execute.service(memberIdCheckService, request.getParameter("id"), log));
			jsp = "ajax/idCheck";
			break;
			
//		case "/memberNewMessageCount.do":
//			System.out.println("AjaxContoller - 새로운 메시지 개수를 가져오는 처리 중");
//			request.setAttribute("cnt", Execute.service((Service) messageNewMessageCountService,
//					((LoginVO) request.getSession().getAttribute("login")).getId()));
//			jsp = "memberajax/NewMessageCount";
//			break;

		default:
			break;
		}
		
		return jsp;
	}

}
