package com.inbook.main.controller;

import javax.servlet.http.HttpServletRequest;

import com.inbook.board.service.BoardListService;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.webjjang.util.PageObject;

public class MainController implements Controller {
	
	private BoardListService boardListService;
	
	public void setBoardListService(BoardListService boardListService) {
		this.boardListService = boardListService;
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {

		System.out.println("MainController.execute() - 메인");
		
		String jsp = null;
		
		// uri - /board/list.do - 처리 service 결정하는 - /list.do
		String uri = request.getServletPath();
		String serviceUri = uri.substring(uri.indexOf("/", 1));
		System.out.println("MainController.execute().serviceUri - " + serviceUri);
		
		switch (serviceUri) {

		// 메인
		case "/main.do":
			// Page 정보 받기
			PageObject pageObject = PageObject.getInstance(request);
			pageObject.setPerPageNum(30);
			request.setAttribute("list", Execute.service(boardListService, pageObject));
			request.setAttribute("pageObject", pageObject);
			jsp = "/main/main";
			break;

		default:
			throw new Exception("잘못된 페이지를 요청하셨습니다.");
		}
		
		
		return jsp;
	}

}
