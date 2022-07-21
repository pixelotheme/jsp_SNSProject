package com.inbook.error;



//import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.inbook.main.Controller;
//import com.inbook.main.Execute;
//import com.webjjang.util.PageObject;

public class ErrorController implements Controller {
//
//	// 서버가 시작이 될 때 객체가 초기화 되어야만 한다. - DispacherServlet.init() 처리
//	private ErrorListService errorListService;
//	private Object list;
//	
//	
//	public void setErrorListService(ErrorListService errorListService) {
//		this.errorListService = errorListService;
//	}

	
	@Override
	public String execute(HttpServletRequest request) throws Exception {

		System.out.println("ErrorController.execute() - 게시판 처리하고 있다.");
		
		String jsp = null;
		
		// uri - /error/list.do - 처리 service 결정하는 - /list.do
		String uri = request.getServletPath();
		String serviceUri = uri.substring(uri.indexOf("/", 1));
		System.out.println("ErrorController.execute().serviceUri - " + serviceUri);
		
		switch (serviceUri) {
		// 로그인에러
		case "/loginError.do":
			// Page 정보 받기
//			PageObject pageObject = PageObject.getInstance(request);
//			request.setAttribute("list", Execute.service(errorListService, pageObject));
//			request.setAttribute("pageObject", pageObject);
			
			jsp = "error/loginError";
			break;

		
			
		default:
			throw new Exception("잘못된 페이지를 요청하셨습니다.");
		}
		
		
		return jsp;
	}

}
