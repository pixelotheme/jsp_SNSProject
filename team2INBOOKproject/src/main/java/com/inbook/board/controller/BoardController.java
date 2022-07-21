package com.inbook.board.controller;


 
import java.io.File;
import java.net.URLEncoder;
import java.util.List;

//import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.inbook.board.service.BoardDeleteService;
import com.inbook.board.service.BoardLikeCancelService;
import com.inbook.board.service.BoardLikeService;
import com.inbook.board.service.BoardListService;
import com.inbook.board.service.BoardUpdateService;
import com.inbook.board.service.BoardViewService;
import com.inbook.board.service.BoardWriteService;
import com.inbook.board.vo.BoardVO;
import com.inbook.comment.service.CommentListService;
import com.inbook.comment.vo.CommentVO;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;
//import com.inbook.member.vo.LoginVO;
import com.webjjang.util.PageObject;
import com.inbook.util.ReplyPageObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardController implements Controller {

	
	// 서버가 시작이 될 때 객체가 초기화 되어야만 한다. - DispacherServlet.init() 처리
	private BoardListService boardListService;
	private BoardViewService boardViewService;
	private BoardWriteService boardWriteService;
	private BoardUpdateService boardUpdateService;
	private BoardDeleteService boardDeleteService;
	@SuppressWarnings("unused")
	private BoardLikeService boardLikeService;
	@SuppressWarnings("unused")
	private BoardLikeCancelService boardLikeCancelService;
	
	// 댓글
	private CommentListService commentListService;
	public void setCommentListService(CommentListService commentListService) {
		this.commentListService = commentListService;
	}

	public void setBoardListService(BoardListService boardListService) {
		this.boardListService = boardListService;
	}

	public void setBoardViewService(BoardViewService boardViewService) {
		this.boardViewService = boardViewService;
	}

	public void setBoardWriteService(BoardWriteService boardWriteService) {
		this.boardWriteService = boardWriteService;
	}

	public void setBoardUpdateService(BoardUpdateService boardUpdateService) {
		this.boardUpdateService = boardUpdateService;
	}

	public void setBoardDeleteService(BoardDeleteService boardDeleteService) {
		this.boardDeleteService = boardDeleteService;
	}
	public void setBoardLikeService(BoardLikeService boardLikeService) {
		this.boardLikeService = boardLikeService; 
	} 
	public void setBoardLikeCancelService(BoardLikeCancelService boardLikeCancelService) {
		this.boardLikeCancelService = boardLikeCancelService;
	}

	
	@SuppressWarnings("unused")
	@Override
	public String execute(HttpServletRequest request) throws Exception {

		System.out.println("BoardController.execute() - 게시판 처리하고 있다.");
		
		String jsp = null;
		
		// uri - /board/list.do - 처리 service 결정하는 - /list.do
		String uri = request.getServletPath();
		String serviceUri = uri.substring(uri.indexOf("/", 1));
		System.out.println("BoardController.execute().serviceUri - " + serviceUri);
		
		switch (serviceUri) {
		// 일반 게시판 리스트
		case "/list.do":
			// Page 정보 받기
			PageObject pageObject = PageObject.getInstance(request);
			request.setAttribute("list", Execute.service(boardListService, pageObject));
			request.setAttribute("pageObject", pageObject);
			
			jsp = "board/list";
			break;

		// 일반 게시판 글쓰기 폼
		case "/writeForm.do":
			jsp = "board/writeForm";
			break;
		
		// 일반 게시판글쓰기 처리
		case "/write.do":
			
			// -------------------------- realPath를 해줘야 파일업로드가 됨 ------------------------------
			// 서버에서의 저장 위치
			String savePath = "/upload/board/";
			System.out.println("/board/write.do.savePath - " + savePath);
			// 하드디스크에서의 절대 위치가 필요하다. - File 객체에서 사용하려면 내 컴퓨터에서 처리되는 절대 위치가 필요하다.
			String realSavePath = request.getServletContext().getRealPath(savePath);
			// 폴더를 파일 객체로 만들기
			File folder = new File(realSavePath);
			boolean isExist = folder.exists();
			System.out.println("/board/write.do.realSavePath - " + realSavePath + ", 존재 여부 : " + isExist);
			// 존재하지 않으면 만든다.(savePath에 폴더 주소 바꾸고 사용하기, 없는 폴더를 생성)
			if(!isExist) folder.mkdirs();
			
			// 용량 제한 - 최대 20MB
			int maxSize = 1024 * 1024 * 20;
			
			// 데이터 받기 - 파일도 받아야 한다. - 처리 방법이 틀리다. - cos 라이브러리 사용
			// 파일 업로드가 된다. multi에 전달된 정보와 파일 정보가 저장된다. - request의 내용은 지워진다.
			// new MultipartRequest(request, 저장절대위치, 업로드최대용량제한, 엔코딩방식, 중복파일처리객체)
			MultipartRequest multi = new MultipartRequest(request, realSavePath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			// 넘어오는 데이터 받기
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String hashtag = multi.getParameter("hashtag");
			String fileName = savePath + multi.getFilesystemName("fileName");
			String strPerPageNum = multi.getParameter("perPageNum");
			// -------------------------- realPath를 해줘야 파일업로드가 됨 ------------------------------
			
			String id = ((LoginVO)request.getSession().getAttribute("login")).getId();
			
			// 넘겨 받은 데이터를 vo를 생성해서 넣어준다.
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setId(id);
			vo.setHashtag(hashtag);
			vo.setFileName(fileName);
			

			// DB 등록
			Execute.service(boardWriteService, vo);
			
			// redirect: - url 이동, 없으면 jsp로 이동
			jsp = "redirect:list.do?perPageNum=" + strPerPageNum;
			break;
			
		// 게시판 글보기
		case "/view.do":
			
			// 데이터 수집 - 글번호/조회수(넘어오는 데이터), 아이디(세션) -> 3개를 넘겨야 하므로 BoardVO 사용
			String noStr = request.getParameter("no");
			long no = Long.parseLong(noStr);
			
			String strInc = request.getParameter("inc");
			System.out.println("BoardController.execute().strInc: " + strInc);
			int inc = Integer.parseInt(strInc);
			
			id = ((LoginVO)request.getSession().getAttribute("login")).getId();
			
			// 게시판에 대한 페이지, 검색 정보
			pageObject = PageObject.getInstance(request);
			
			// 댓글 페이지 정보
			ReplyPageObject replyPageObject = new ReplyPageObject();
			String strReplyPage = request.getParameter("replyPage");
			if(strReplyPage != null) replyPageObject.setPage(Long.parseLong(strReplyPage));
			replyPageObject.setNo(no);
			
			// 게시판 글보기 데이터 가져오기
			vo = (BoardVO)Execute.service(boardViewService, new Object[]{no, inc, id});
//			vo = (BoardVO)Execute.service(boardViewService, new Object[]{no});
			
			// 댓글 리스트 가져오기
			@SuppressWarnings("unchecked")
			List<CommentVO> list = (List<CommentVO>) Execute.service(commentListService, replyPageObject);
			
			// vo.content를 그대로 보여 주면 줄바꿈 무시 여러개의 공백문자 무시 -> 처리해 준다.
			vo.setContent(vo.getContent().replace("\n", "<br>").replace(" ", "&nbsp;"));
			
			// 강제 오류 발생
//		 	System.out.println(10/0);
			
			// EL 객체를 이용해서 데이터 표시하고자 한다면 JSP 저장 기본 객체 중 하나에 저장이 되어 있어야만 한다.
			// application - session - request - pageContext 객체
			request.setAttribute("vo", vo); // EL 객체로 꺼내 쓸 수 있다.
			request.setAttribute("pageObject", pageObject); // EL 객체로 꺼내 쓸 수 있다.
			request.setAttribute("list", list); // EL 객체로 꺼내 쓸 수 있다.
			request.setAttribute("replyPageObject", replyPageObject); // EL 객체로 꺼내 쓸 수 있다.

			jsp = "board/view";
			break;
			
		case "/updateForm.do":
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			
			// 페이지, 검색 정보 받기
			pageObject = PageObject.getInstance(request);
			
			// EL 객체를 이용해서 데이터 표시하고자 한다면 JSP 저장 기본 객체 중 하나에 저장이 되어 있어야만 한다.
			// application - session - request - pageContext 객체
			request.setAttribute("vo", Execute.service(boardViewService, new Object[]{no, 0, toString()}));
			request.setAttribute("pageObject", pageObject);

			// updateForm.jsp로 이동시키기 위해 정보 저장
			jsp = "board/updateForm";
			break;
			
		case "/update.do":
			// 넘어 오는 데이터 받기
			String strNo = request.getParameter("no");
			no = Long.parseLong(strNo);
			title = request.getParameter("title");
			content = request.getParameter("content");
			hashtag = request.getParameter("hashtag");
			fileName = request.getParameter("fileName");

			// 페이지, 검색 정보 받기
			pageObject = PageObject.getInstance(request);

			vo = new BoardVO();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setHashtag(hashtag);
			vo.setFileName(fileName);
			
			// DB 등록 - BoardUpdateService - BoardDAO
			Integer result = (Integer)Execute.service(boardUpdateService, vo);
			
			// 결과 처리
			if(result == 1) System.out.println("게시판 글수정이 되었습니다.");
			else  throw new Exception("게시판 글수정이 되지 않았습니다. 정보를 다시 확인해 주세요.");
			
			// 수정 처리 후 이동할 페이지 정보를 "redirect:"를 붙여서 넣어 준다.
			jsp = "redirect:view.do?no=" + strNo + "&inc=0&page=" + pageObject.getPage()
				+ "&perPageNum=" + pageObject.getPerPageNum() + "&key=" + pageObject.getKey()
				+ "&word=" + pageObject.getWord();

			break;
			
		// 게시판 리스트 좋아요2 처리 ----------------------------------------------------------------------------
			// 1. DB에 정보를 넣는다. - board_like
		case "/like2.do":

			// 데이터 수집(no, id 필요) 
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			strPerPageNum = request.getParameter("perPageNum");

			id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			vo = new BoardVO();
			vo.setNo(no);
			vo.setId(id);
			
			
			// 본인의 글만 삭제가 가능하다. 로그인한 아이디가 필요하다.
			Execute.service(boardLikeService, vo);
			
			
			System.out.println("BoardController.like().id - 서비스 실행 확인" );
			
			String query = request.getQueryString();
			System.out.println("query실행" + query);
			jsp = "redirect:list.do?no=" + no
					// + "&inc=0" 좋아요를 눌러서 다시 view.do로 돌아와도 조회수가 올라가지 않게 함.
					+ "&inc=0"
					+ "&page=" + request.getParameter("page")
					+ "&perPageNum=" + request.getParameter("perPageNum")
					+ "&key=" + request.getParameter("key")
					+ "&word=" + URLEncoder.encode(request.getParameter("word"), "utf-8");
			
			// 글쓴 처리 결과를 session의 msg에 담기
//			Session.setAttribute("msg", "성공적으로 좋아요가 처리되었습니다.");
			
			break;
			
		// 게시판 리스트 좋아요 취소2 처리 ----------------------------------------------------------------------------
		// 1. DB에 정보를 넣는다. - board_likeCancel
		case "/likeCancel2.do":
			
			// 데이터 수집(no, id 필요)
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			strPerPageNum = request.getParameter("perPageNum");

			id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			vo = new BoardVO();
			vo.setNo(no);
			vo.setId(id);
			
			
			Execute.service(boardLikeCancelService, vo);
			
			// 본인의 글만 삭제가 가능하다. 로그인한 아이디가 필요하다.
//			vo = (BoardVO)Execute.service(boardViewService, new Object[]{no, id});
			
			
			query = request.getQueryString();
			
			jsp = "redirect:list.do?no=" + no
					+ "&inc=0"
					+ "&page=" + request.getParameter("page")
					+ "&perPageNum=" + request.getParameter("perPageNum")
					+ "&key=" + request.getParameter("key")
					+ "&word=" + URLEncoder.encode(request.getParameter("word"), "utf-8");
			
			break;
			
			// 게시판 글보기 좋아요 처리 ----------------------------------------------------------------------------
			// 1. DB에 정보를 넣는다. - board_like
		case "/like.do":
			
			// 데이터 수집(no, id 필요) 
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			strPerPageNum = request.getParameter("perPageNum");
			
			id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			vo = new BoardVO();
			vo.setNo(no);
			vo.setId(id);
			
			
			// 본인의 글만 삭제가 가능하다. 로그인한 아이디가 필요하다.
			Execute.service(boardLikeService, vo);
			
			
			System.out.println("BoardController.like().id - 서비스 실행 확인" );
			
			query = request.getQueryString();
			
			jsp = "redirect:view.do?no=" + no
					// + "&inc=0" 좋아요를 눌러서 다시 view.do로 돌아와도 조회수가 올라가지 않게 함.
					+ "&inc=0"
					+ "&page=" + request.getParameter("page")
					+ "&perPageNum=" + request.getParameter("perPageNum")
					+ "&key=" + request.getParameter("key")
					+ "&word=" + URLEncoder.encode(request.getParameter("word"), "utf-8");
			
			// 글쓴 처리 결과를 session의 msg에 담기
//			Session.setAttribute("msg", "성공적으로 좋아요가 처리되었습니다.");
			
			break;
			
			// 게시판 좋아요 취소 처리 ----------------------------------------------------------------------------
			// 1. DB에 정보를 넣는다. - board_likeCancel
		case "/likeCancel.do":
			
			// 데이터 수집(no, id 필요)
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			strPerPageNum = request.getParameter("perPageNum");
			
			id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			vo = new BoardVO();
			vo.setNo(no);
			vo.setId(id);
			
			
			Execute.service(boardLikeCancelService, vo);
			
			// 본인의 글만 삭제가 가능하다. 로그인한 아이디가 필요하다.
//			vo = (BoardVO)Execute.service(boardViewService, new Object[]{no, id});
			
			
			query = request.getQueryString();
			
			jsp = "redirect:view.do?no=" + no
					+ "&inc=0"
					+ "&page=" + request.getParameter("page")
					+ "&perPageNum=" + request.getParameter("perPageNum")
					+ "&key=" + request.getParameter("key")
					+ "&word=" + URLEncoder.encode(request.getParameter("word"), "utf-8");
			
			break;
			
		case "/delete.do":
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			strPerPageNum = request.getParameter("perPageNum");
			
			// DB 삭제 - BoardDeleteService - BoardDAO
			Execute.service(boardDeleteService, no);
			
			jsp = "redirect:list.do?perPageNum=" + strPerPageNum;
			
			break;
			
		default:
			throw new Exception("잘못된 페이지를 요청하셨습니다.");
		}
		
		
		return jsp;
	}

}
