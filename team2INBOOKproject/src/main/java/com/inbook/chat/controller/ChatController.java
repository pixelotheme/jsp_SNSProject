package com.inbook.chat.controller;

import javax.servlet.http.HttpServletRequest;

import com.inbook.chat.service.ChatMaxNoService;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;
import com.webjjang.util.PageObject;


public class ChatController implements Controller {

	private ChatMaxNoService chatMaxNoService;

	public void setChatMaxNoService(ChatMaxNoService chatMaxNoService) {
		this.chatMaxNoService = chatMaxNoService;
	}



	@Override
	public String execute(HttpServletRequest request) throws Exception {
		
		System.out.println("ChatController.execute() - 채팅 처리하고 있다.");

		String jsp = null;
		
		// uri - /board/list.do - 처리 service 결정하는 - /list.do
		String uri = request.getServletPath();
		String serviceUri = uri.substring(uri. indexOf("/", 1));
		System.out.println("ChatController.execute().serviceUri - " + serviceUri);
	
	
	switch (serviceUri) {
	case "/list.do":
		
		String cnoStr = request.getParameter("cno");
		Long cno = Long.parseLong(cnoStr);
		
		// 페이지와 검색 정보 - 메시지 모드느 받은 메시지가 기본으로 선택된다.
	//	PageObject pageObject = PageObject.getInstance(request);
		
		// 메시지 사용자 데이터를 session에서 가져와서 채워 넣는다.
	//	pageObject.setAccepter(((LoginVO) request.getSession().getAttribute("login")).getId());
		
	//	request.setAttribute("vo", vo);
		request.setAttribute("MaxNo", Execute.service(chatMaxNoService, cno));
		//request.setAttribute("pageObject", pageObject);
		
		jsp = "chat/list";
		break;
		
	case "/view.do":
		
		break;
		
	case "/writeForm.do":
	
	break;
	
	case "/write.do":
	
	break;
	
	case "/updateForm.do":
	
	break;
	
	case "/update.do":
	
	break;
	
	case "/delete.do":
	
	break;

	default:
		break;
	}
	
	return jsp;
	}
}
	
	
