package com.inbook.groupList.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.inbook.groupcomment.service.GroupCommentListService;
import com.inbook.groupcomment.vo.GroupCommentVO;
import com.inbook.groupList.service.GroupListLikeCancelService;
import com.inbook.groupList.service.GroupListDeleteService;
import com.inbook.groupList.service.GroupListLikeService;
import com.inbook.groupList.service.GroupListListService;
import com.inbook.groupList.service.GroupListUpdateService;
import com.inbook.groupList.service.GroupListViewService;
import com.inbook.groupList.service.GroupListWriteService;
import com.inbook.groupList.vo.GroupListVO;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.member.vo.LoginVO;
import com.inbook.util.ReplyPageObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.util.PageObject;

public class GroupListController implements Controller {
	
	@SuppressWarnings("unused")
	private GroupListLikeService groupListLikeService;
	@SuppressWarnings("unused")
	private GroupListLikeCancelService groupListLikeCancelService;
	
	private GroupListListService groupListListService;
	private GroupListViewService groupListViewService ;
	private GroupListWriteService groupListWriteService;
	private GroupListUpdateService groupListUpdateService;
	private GroupListDeleteService groupListDeleteService;
	
	
	
	public void setGroupListListService(GroupListListService groupListListService ) {
		this.groupListListService = groupListListService;
	}
	public void setGroupListViewService(GroupListViewService groupListViewService) {
		this.groupListViewService = groupListViewService;
	}

	public void setGroupListWriteService(GroupListWriteService groupListWriteService) {
		this.groupListWriteService = groupListWriteService;
	}

	public void setGroupListUpdateService(GroupListUpdateService groupListUpdateService) {
		this.groupListUpdateService = groupListUpdateService;
	}

	public void setGroupListDeleteService(GroupListDeleteService groupListDeleteService) {
		this.groupListDeleteService = groupListDeleteService;
	}
	public void setGroupListLikeService(GroupListLikeService groupListLikeService) {
		this.groupListLikeService = groupListLikeService; 
	} 
	public void setGroupListLikeCancelService(GroupListLikeCancelService groupListLikeCancelService) {
		this.groupListLikeCancelService = groupListLikeCancelService;
	}
	private GroupCommentListService groupCommentListService;
	public void setGroupCommentListService(GroupCommentListService groupCommentListService) {
		this.groupCommentListService = groupCommentListService;
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {

		System.out.println("GroupListController.execute() - 메인");
		
		String jsp = null;
		
		// uri - /board/list.do - 처리 service 결정하는 - /list.do
		String uri = request.getServletPath();
		String serviceUri = uri.substring(uri.indexOf("/", 1));
		System.out.println("GroupListController.execute().serviceUri - " + serviceUri);
		
		switch (serviceUri) {

		// 메인
		case "/list.do":
			// Page 정보 받기
			PageObject pageObject = PageObject.getInstance(request);
			request.setAttribute("list", Execute.service(groupListListService, pageObject));
			request.setAttribute("pageObject", pageObject);
			jsp = "groupList/list";
			break;
			// 그룹 등록 폼
			case "/writeForm.do":
				jsp = "groupList/writeForm";
				
				break;
			
			// 그룹 게시글쓰기 처리
			case "/write.do":
				// 넘어오는 데이터 받기
				String savePath = "/upload/grouplist/";
				System.out.println("/gorupList/write.do.savePath - " + savePath);
				// 하드디스크에서의 절대 위치가 필요하다. - File 객체에서 사용하려면 내 컴퓨터에서 처리되는 절대 위치가 필요하다.
				String realSavePath = request.getServletContext().getRealPath(savePath);
				// 폴더를 파일 객체로 만들기
				File folder = new File(realSavePath);
				boolean isExist = folder.exists();
				System.out.println("/groupList/write.do.realSavePath - " + realSavePath + ", 존재 여부 : " + isExist);
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
				GroupListVO vo = new GroupListVO();
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id);
				vo.setHashtag(hashtag);
				vo.setFileName(fileName);
				

				// DB 등록
				Execute.service(groupListWriteService, vo);
				
				// redirect: - url 이동, 없으면 jsp로 이동
				jsp = "redirect:list.do?perPageNum=" + strPerPageNum;
				break;
				
			// 그룹  게시 글보기
			case "/view.do":
				jsp = "groupList/view";
				// 데이터 수집 - 글번호/조회수(넘어오는 데이터), 아이디(세션) -> 3개를 넘겨야 하므로 BoardVO 사용
				String noStr = request.getParameter("no");
				long no = Long.parseLong(noStr);
				
				String strInc = request.getParameter("inc");
				System.out.println("GorupListController.execute().strInc: " + strInc);
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
				vo = (GroupListVO)Execute.service(groupListViewService, new Object[]{no, inc, id});
//				vo = (BoardVO)Execute.service(boardViewService, new Object[]{no});
				
				// 댓글 리스트 가져오기
				@SuppressWarnings("unchecked")
				List<GroupCommentVO> list = (List<GroupCommentVO>) Execute.service(groupCommentListService, replyPageObject);
				
				// vo.content를 그대로 보여 주면 줄바꿈 무시 여러개의 공백문자 무시 -> 처리해 준다.
				vo.setContent(vo.getContent().replace("\n", "<br>").replace(" ", "&nbsp;"));
				
				// 강제 오류 발생
//			 	System.out.println(10/0);
				
				// EL 객체를 이용해서 데이터 표시하고자 한다면 JSP 저장 기본 객체 중 하나에 저장이 되어 있어야만 한다.
				// application - session - request - pageContext 객체
				request.setAttribute("vo", vo); // EL 객체로 꺼내 쓸 수 있다.
				request.setAttribute("pageObject", pageObject); // EL 객체로 꺼내 쓸 수 있다.
				request.setAttribute("list", list); // EL 객체로 꺼내 쓸 수 있다.
				request.setAttribute("replyPageObject", replyPageObject); // EL 객체로 꺼내 쓸 수 있다.

				jsp = "groupList/view";
				break;
			case "/updateForm.do":
				noStr = request.getParameter("no");
				no = Long.parseLong(noStr);
				
				System.out.println("GroupListController.execute().updateForm.do");
				
				// 페이지, 검색 정보 받기
				pageObject = PageObject.getInstance(request);
				
				// EL 객체를 이용해서 데이터 표시하고자 한다면 JSP 저장 기본 객체 중 하나에 저장이 되어 있어야만 한다.
				// application - session - request - pageContext 객체
				request.setAttribute("vo", Execute.service(groupListViewService, new Object[]{no, 0, toString()}));
				request.setAttribute("pageObject", pageObject);

				System.out.println("GroupListController.execute().updateForm.do - OK");
				
				// updateForm.jsp로 이동시키기 위해 정보 저장
				jsp = "groupList/updateForm";
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

				vo = new GroupListVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setHashtag(hashtag);
				vo.setFileName(fileName);
				
				// DB 등록 - GroupListUpdateService - GroupListDAO
				Integer result = (Integer)Execute.service(groupListUpdateService, vo);
				
				// 결과 처리
				if(result == 1) System.out.println("그룹 수정이 되었습니다.");
				else  throw new Exception("그룹 글수정이 되지 않았습니다. 정보를 다시 확인해 주세요.");
				
				// 수정 처리 후 이동할 페이지 정보를 "redirect:"를 붙여서 넣어 준다.
				jsp = "redirect:view.do?no=" + strNo + "&inc=0&page=" + pageObject.getPage()
					+ "&perPageNum=" + pageObject.getPerPageNum() + "&key=" + pageObject.getKey()
					+ "&word=" + pageObject.getWord();

				break;
				
			// 그룹 게시판 좋아요 처리 ----------------------------------------------------------------------------
				// 1. DB에 정보를 넣는다. - board_like
			case "/like.do":

				// 데이터 수집(no, id 필요) 
				noStr = request.getParameter("no");
				no = Long.parseLong(noStr);
				strPerPageNum = request.getParameter("perPageNum");

				id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
				
				vo = new GroupListVO();
				vo.setNo(no);
				vo.setId(id);
				
				
				// 본인의 글만 삭제가 가능하다. 로그인한 아이디가 필요하다.
				Execute.service(groupListLikeService, vo);
				
				
				
				String query = request.getQueryString();
				
				jsp = "redirect:view.do?no=" + no
						+ "&inc=0"
						+ "&page=" + request.getParameter("page")
						+ "&perPageNum=" + request.getParameter("perPageNum")
						+ "&key=" + request.getParameter("key")
						+ "&word=" + URLEncoder.encode(request.getParameter("word"), "utf-8");
				
				// 글쓴 처리 결과를 session의 msg에 담기
//				Session.setAttribute("msg", "성공적으로 좋아요가 처리되었습니다.");
				
				break;
				
			// 그룹 게시글 좋아요 취소 처리 ----------------------------------------------------------------------------
			// 1. DB에 정보를 넣는다. - board_likeCancel
			case "/likeCancel.do":
				
				// 데이터 수집(no, id 필요)
				noStr = request.getParameter("no");
				no = Long.parseLong(noStr);
				strPerPageNum = request.getParameter("perPageNum");

				id = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
				
				vo = new GroupListVO();
				vo.setNo(no);
				vo.setId(id);
				
				Execute.service(groupListLikeService, vo);
				
				// 본인의 글만 삭제가 가능하다. 로그인한 아이디가 필요하다.
//				vo = (BoardVO)Execute.service(boardViewService, new Object[]{no, id});
				
				
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
				
				Execute.service(groupListDeleteService, no);
				
				jsp = "redirect:list.do?perPageNum=" + strPerPageNum;
				
				break;
				
		default:
			throw new Exception("잘못된 페이지를 요청하셨습니다.");
		}
		
		
		return jsp;
	}

}
