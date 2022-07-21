package com.inbook.comment.controller;

import javax.servlet.http.HttpServletRequest;

import com.inbook.comment.service.CommentDeleteService;
import com.inbook.comment.service.CommentUpdateService;
import com.inbook.comment.service.CommentWriteService;
import com.inbook.comment.vo.CommentVO;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;

public class CommentController implements Controller {
	
	private CommentWriteService commentWriteService;
	private CommentUpdateService commentUpdateService;
	private CommentDeleteService commentDeleteService;

	public void setCommentWriteService(CommentWriteService commentWriteService) {
		this.commentWriteService = commentWriteService;
	}

	public void setCommentUpdateService(CommentUpdateService commentUpdateService) {
		this.commentUpdateService = commentUpdateService;
	}

	public void setCommentDeleteService(CommentDeleteService commentDeleteService) {
		this.commentDeleteService = commentDeleteService;
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {

		System.out.println("CommentController.execute() - 댓글 처리중");
		
		String jsp = null;
		
		String uri = request.getServletPath();
		String serviceUri = uri.substring(0);
		System.out.println("CommentController.execute().serviceUri - " + serviceUri);
		
		
		switch (serviceUri) {
		// 댓글 작성
		case "/comment/commentWrite.do":
			
			// 넘어오는 데이터 받기
			String strNo = request.getParameter("no");
			Long no = Long.parseLong(strNo);
			String content = request.getParameter("content");
			String id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			// jsp(Controller) - CommentWriteService - CommentDAO
			CommentVO vo = new CommentVO();
			vo.setNo(no);
			vo.setContent(content);
			vo.setId(id);
			
			// DB 등록 - CommentWriteService - CommentDAO
			Execute.service(commentWriteService, vo);
			
			jsp = "redirect:/board/view.do?no=" + vo.getNo() 
				+ "&inc=0"
				+ "&page=" + request.getParameter("page")
				+ "&perPageNum=" + request.getParameter("perPageNum")
				+ "&key=" + request.getParameter("key")
				+ "&word=" + request.getParameter("word")
			;

			break;
			
		// 댓글 수정
		case "/comment/commentUpdate.do":
			// 넘어오는 데이터 받기
			String strRno = request.getParameter("rno");
			Long rno = Long.parseLong(strRno);
			content = request.getParameter("content");
			
			// jsp(Controller) - CommentUpdateService - CommentDAO
			vo = new CommentVO();
			vo.setRno(rno);
			vo.setContent(content);
			
			// DB 등록 - CommentUpdateService - CommentDAO
			Execute.service(commentUpdateService, vo);
			
			jsp = "redirect:/board/view.do?no=" + request.getParameter("no")
				+ "&inc=0"
				+ "&page=" + request.getParameter("page")
				+ "&perPageNum=" + request.getParameter("perPageNum")
				+ "&key=" + request.getParameter("key")
				+ "&word=" + request.getParameter("word")
				+ "&replyPage=" + request.getParameter("replyPage")
			;
			break;
			
		// 댓글 삭제
		case "/comment/commentDelete.do":
			// 넘어오는 데이터 받기
			strRno = request.getParameter("rno");
			rno = Long.parseLong(strRno);
			id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			// jsp(Controller) - CommentUpdateService - CommentDAO
			vo = new CommentVO();
			vo.setRno(rno);
			vo.setId(id);
			
			// DB 등록 - CommentUpdateService - CommentDAO
			Execute.service(commentDeleteService, vo);
			
			jsp = "redirect:/board/view.do?no=" + request.getParameter("no")
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
