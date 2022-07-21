package com.inbook.chatRoom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.inbook.chatRoom.service.ChatRoomDeleteService;
import com.inbook.chatRoom.service.ChatRoomListService;
import com.inbook.chatRoom.service.ChatRoomUpdateService;
import com.inbook.chatRoom.service.ChatRoomWriteService;
import com.inbook.chatRoom.vo.ChatRoomVO;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;
import com.webjjang.util.PageObject;

public class ChatRoomController implements Controller {

	private ChatRoomListService chatRoomListService;
	private ChatRoomWriteService chatRoomWriteService;
	private ChatRoomUpdateService chatRoomUpdateService;
	private ChatRoomDeleteService chatRoomDeleteService;

	public void setChatRoomListService(ChatRoomListService chatRoomListService) {
		this.chatRoomListService = chatRoomListService;
	}

	public void setChatRoomWriteService(ChatRoomWriteService chatRoomWriteService) {
		this.chatRoomWriteService = chatRoomWriteService;
	}
	


	public void setChatRoomUpdateService(ChatRoomUpdateService chatRoomUpdateService) {
		this.chatRoomUpdateService = chatRoomUpdateService;
	}

	public void setChatRoomDeleteService(ChatRoomDeleteService chatRoomDeleteService) {
		this.chatRoomDeleteService = chatRoomDeleteService;
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {

		System.out.println("ChatRoomController.execute() - 채팅 처리하고 있다.");

		String jsp = null;

		// uri - /board/list.do - 처리 service 결정하는 - /list.do
		String uri = request.getServletPath();
		String serviceUri = uri.substring(uri.indexOf("/", 1));
		System.out.println("ChatRoomController.execute().serviceUri - " + serviceUri);

		switch (serviceUri) {
		case "/list.do":

			// 페이지와 검색 정보 - 메시지 모드느 받은 메시지가 기본으로 선택된다.
//			
//			String noStr = request.getParameter("no");
//			long no = Long.parseLong(noStr);
//			
	      	PageObject pageObject = PageObject.getInstance(request);
//			
	//		chatRoomPageObject chatRoomPageObject = new chatRoomPageObject();
//			String strChatRoomPage = request.getParameter("chatRoomPage");
//			if(strChatRoomPage != null) chatRoomPageObject.setPage(Long.parseLong(strChatRoomPage));
//			
//			chatRoomPageObject.setNo(no);
//			
//			@SuppressWarnings("unchecked")
//			List<ChatRoomVO> list = (List<ChatRoomVO>) Execute.service(chatRoomListService, chatRoomPageObject);
//			
			// 메시지 사용자 데이터를 session에서 가져와서 채워 넣는다.
			// pageObject.setAccepter(((LoginVO)
			// request.getSession().getAttribute("login")).getId());
			 
	     // 	String id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			request.setAttribute("list", Execute.service(chatRoomListService, pageObject));
			request.setAttribute("pageObject", pageObject);

			jsp = "chatRoom/list";
			break;

		case "/view.do":

			break;

		case "/writeForm.do":

			break;

		case "/chatRoomWrite.do":
			String title = request.getParameter("title");
			String id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			String strPerPageNum = request.getParameter("perPageNum");
			
			// 넘겨 받은 데이터를 vo에 생성해서 넣어준다.
			ChatRoomVO vo = new ChatRoomVO();
			
			vo.setTitle(title);
			vo.setId(id);
	
			// DB 등록
			Execute.service(chatRoomWriteService, vo);
			
			// redirect: - url 이동, 없으면 jsp로 이동
			jsp = "redirect:list.do?perPageNum=" + strPerPageNum;
			break;
			
			
		

		case "/updateForm.do":

			break;

		case "/chatRoomUpdate.do":
			
			String strCno = request.getParameter("cno");
			Long cno = Long.parseLong(strCno);
			title = request.getParameter("title");
		
		

			vo = new ChatRoomVO();
			vo.setCno(cno);
			vo.setTitle(title);
			
			
			// DB 등록 
			Execute.service(chatRoomUpdateService, vo);
			
			jsp = "redirect:list.do" ;

			
			
			break;
			

			

		case "/chatRoomDelete.do":
			
			strCno = request.getParameter("cno");
			cno = Long.parseLong(strCno);
			id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			// jsp(Controller) - CommentUpdateService - CommentDAO
			vo = new ChatRoomVO();
			vo.setCno(cno);
			vo.setId(id);
	
			
			// DB 등록 - CommentUpdateService - CommentDAO
			Execute.service(chatRoomDeleteService, vo);
			
			jsp = "redirect:list.do?no =" + request.getParameter("cno")
			+ "&page=" + request.getParameter("page")
			+ "&perPageNum=" + request.getParameter("perPageNum")
			+ "&key=" + request.getParameter("key")
			+ "&word=" + request.getParameter("word")
			+ "&chatRoomPage=1";
		
			
			break;

		default:
			throw new Exception("잘못된 페이지를 요청하셨습니다.");
			
		}

		return jsp;
	}
}
