package com.inbook.groupcomment.controller;

import javax.servlet.http.HttpServletRequest;

import com.inbook.groupcomment.service.GroupCommentDeleteService;
import com.inbook.groupcomment.service.GroupCommentUpdateService;
import com.inbook.groupcomment.service.GroupCommentWriteService;
import com.inbook.groupcomment.vo.GroupCommentVO;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;

public class GroupCommentController implements Controller {
	
	private GroupCommentWriteService groupCommentWriteService;
	private GroupCommentUpdateService groupCommentUpdateService;
	private GroupCommentDeleteService groupCommentDeleteService;

	public void setGroupCommentWriteService(GroupCommentWriteService groupCommentWriteService) {
		this.groupCommentWriteService = groupCommentWriteService;
	}

	public void setGroupCommentUpdateService(GroupCommentUpdateService groupCommentUpdateService) {
		this.groupCommentUpdateService = groupCommentUpdateService;
	}

	public void setGroupCommentDeleteService(GroupCommentDeleteService groupCommentDeleteService) {
		this.groupCommentDeleteService = groupCommentDeleteService;
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {

		System.out.println("GroupCommentController.execute() - 댓글 처리중");
		
		String jsp = null;
		
		String uri = request.getServletPath();
		String serviceUri = uri.substring(0);
		System.out.println("GroupCommentController.execute().serviceUri - " + serviceUri);
		
		
		switch (serviceUri) {
		// 댓글 작성
		case "/groupComment/groupCommentWrite.do":
			
			// 넘어오는 데이터 받기
			String strNo = request.getParameter("no");
			Long no = Long.parseLong(strNo);
			String content = request.getParameter("content");
			String id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			// jsp(Controller) - CommentWriteService - CommentDAO
			GroupCommentVO vo = new GroupCommentVO();
			vo.setNo(no);
			vo.setContent(content);
			vo.setId(id);
			
			// DB 등록 - CommentWriteService - CommentDAO
			Execute.service(groupCommentWriteService, vo);
			
			jsp = "redirect:/groupList/view.do?no=" + vo.getNo() 
				+ "&inc=0"
				+ "&page=" + request.getParameter("page")
				+ "&perPageNum=" + request.getParameter("perPageNum")
				+ "&key=" + request.getParameter("key")
				+ "&word=" + request.getParameter("word")
			;

			break;
			
		// 댓글 수정
		case "/groupComment/groupCommentUpdate.do":
			// 넘어오는 데이터 받기
			String strRno = request.getParameter("rno");
			Long rno = Long.parseLong(strRno);
			content = request.getParameter("content");
			
			// jsp(Controller) - CommentUpdateService - CommentDAO
			vo = new GroupCommentVO();
			vo.setRno(rno);
			vo.setContent(content);
			
			// DB 등록 - CommentUpdateService - CommentDAO
			Execute.service(groupCommentUpdateService, vo);
			
			jsp = "redirect:/groupList/view.do?no=" + request.getParameter("no")
				+ "&inc=0"
				+ "&page=" + request.getParameter("page")
				+ "&perPageNum=" + request.getParameter("perPageNum")
				+ "&key=" + request.getParameter("key")
				+ "&word=" + request.getParameter("word")
				+ "&replyPage=" + request.getParameter("replyPage")
			;
			break;
			
		// 댓글 삭제
		case "/groupComment/groupCommentDelete.do":
			// 넘어오는 데이터 받기
			strRno = request.getParameter("rno");
			rno = Long.parseLong(strRno);
			id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			// jsp(Controller) - CommentUpdateService - CommentDAO
			vo = new GroupCommentVO();
			vo.setRno(rno);
			vo.setId(id);
			
			// DB 등록 - CommentUpdateService - CommentDAO
			Execute.service(groupCommentDeleteService, vo);
			
			jsp = "redirect:/groupList/view.do?no=" + request.getParameter("no")
				+ "&inc=0"
				+ "&page=" + request.getParameter("page")
				+ "&perPageNum=" + request.getParameter("perPageNum")
				+ "&key=" + request.getParameter("key")
				+ "&word=" + request.getParameter("word")
				+ "&replyPage=1"
			;
			break;
			
		default:
			break;
		}
		
		return jsp;
	}

}
