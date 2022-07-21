package com.inbook.chatajax.controller;


import javax.servlet.http.HttpServletRequest;

import com.inbook.chat.service.ChatGetDataService;
import com.inbook.chat.service.ChatSendDataService;
import com.inbook.chat.vo.ChatVO;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;

public class ChatAjaxController implements Controller {

	private ChatSendDataService chatSendDataService;
	private ChatGetDataService chatGetDataService;
	

	public void setChatSendDataService(ChatSendDataService chatSendDataService) {
		this.chatSendDataService = chatSendDataService;
	}

	
	public void setChatGetDataService(ChatGetDataService chatGetDataService) {
		this.chatGetDataService = chatGetDataService;
	}



	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("AjaxController.execute()- Ajax 처리하고 있다");
		
		String jsp = null;
		
		//uri - /board/실행서비스.do  - 처리 service 결정하는 - /list.do
		String uri = request.getServletPath();
		
		//두번째 슬레시부터 글자 찾기 - switch 문으로 해당되는것 실행되게끔 가져오는 처리
		String serviceUri = uri.substring(uri.indexOf("/", 1));
		System.out.println("AjaxController.execute().serviceUri - "+serviceUri);
		
		switch (serviceUri) {
		
		case "/chatSendData.do":
			
			String content = request.getParameter("content");

			String id = ((LoginVO)request.getSession().getAttribute("login")).getId();
			System.out.println("방번호 확인");
			String strCno = request.getParameter("cno");
			System.out.println("방번호 변환"+strCno);
			Long cno = Long.parseLong(strCno);
			
			ChatVO vo = new ChatVO();
			vo.setId(id);
			vo.setContent(content);
			vo.setCno(cno);
			Execute.service(chatSendDataService, vo);
			
	//	request.setAttribute("sendData", Execute.service(chatSendDataService, vo));
				
			jsp = "ajax/chatSendData";
			break;
			
		case "/chatGetData.do":
			
			String MaxNo = request.getParameter("maxNo");
			strCno = request.getParameter("cno");			
			cno = Long.parseLong(strCno);			
			Long maxNo = Long.parseLong(MaxNo);
			System.out.println("cno :" + cno);
			System.out.println("maxno :" + maxNo);
			
			id = ((LoginVO)request.getSession().getAttribute("login")).getId();	
	
			vo = new ChatVO();
			vo.setNo(maxNo);
			vo.setId(id);		
//			vo.setWriteDate(writeDate);
			vo.setCno(cno);
			
			
			System.out.println(" vo: " + vo);
			int log = 0;
			request.setAttribute("dataList", Execute.service(chatGetDataService, vo, log));
			
			jsp = "ajax/chatGetData";
			break;
			
//	case "/chatGetMaxData.do":
//			
//			MaxNo = request.getParameter("maxNo+1");
//			strCno = request.getParameter("cno");			
//			cno = Long.parseLong(strCno);			
//			maxNo = Long.parseLong(MaxNo+1);
//			System.out.println("cno :" + cno);
//			System.out.println("maxno+1 :" + maxNo+1);
//			
//			id = ((LoginVO)request.getSession().getAttribute("login")).getId();	
//	
//			vo = new ChatVO();
//			vo.setNo(maxNo+1);
//			vo.setId(id);		
////			vo.setWriteDate(writeDate);
//			vo.setCno(cno);
//			
//			
//			System.out.println(" vo: " + vo);
//			
//			request.setAttribute("dataList", Execute.service(chatGetDataService, vo));
//		
//			jsp = "ajax/chatGetData";
//			break;
//			

		default:
			break;
		}
		
		return jsp;
	}


}
