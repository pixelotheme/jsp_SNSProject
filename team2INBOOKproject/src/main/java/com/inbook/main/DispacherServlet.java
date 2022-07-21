package com.inbook.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inbook.board.controller.BoardController;
import com.inbook.board.dao.BoardDAO;
import com.inbook.board.service.BoardDeleteService;
import com.inbook.board.service.BoardLikeCancelService;
import com.inbook.board.service.BoardLikeService;
import com.inbook.board.service.BoardListService;
import com.inbook.board.service.BoardUpdateService;
import com.inbook.board.service.BoardViewService;
import com.inbook.board.service.BoardWriteService;
import com.inbook.chat.controller.ChatController;
import com.inbook.chat.dao.ChatDAO;
import com.inbook.chat.service.ChatGetDataService;
import com.inbook.chat.service.ChatMaxNoService;
import com.inbook.chat.service.ChatSendDataService;
import com.inbook.friend.controller.ButtonProcess;
import com.inbook.friend.controller.FriendController;
import com.inbook.friend.controller.FriendServiceImport;
import com.inbook.friend.dao.FriendDAO;
import com.inbook.friend.service.FriendAllUserListService;
import com.inbook.friend.service.FriendDeleteService;
import com.inbook.friend.service.FriendIdListService;
import com.inbook.friend.service.FriendListService;
import com.inbook.friend.service.FriendRequestAcceptService;
import com.inbook.friend.service.FriendRequestCancelService;
import com.inbook.friend.service.FriendRequestCountService;
import com.inbook.friend.service.FriendRequestIdListService;
import com.inbook.friend.service.FriendRequestIdService;
import com.inbook.friend.service.FriendRequestRefuseService;
import com.inbook.friend.service.FriendRequestSendIdService;
import com.inbook.friend.service.FriendRequestSendService;
import com.inbook.friend.service.FriendSearchService;
import com.inbook.friend.service.FriendSuggestionsService;
import com.inbook.friendajax.controller.FriendAjaxController;
import com.inbook.groupList.controller.GroupListController;
import com.inbook.groupList.dao.GroupListDAO;
import com.inbook.groupList.service.GroupListLikeCancelService;
import com.inbook.groupList.service.GroupListDeleteService;
import com.inbook.groupList.service.GroupListLikeService;
import com.inbook.groupList.service.GroupListListService;
import com.inbook.groupList.service.GroupListUpdateService;
import com.inbook.groupList.service.GroupListViewService;
import com.inbook.groupList.service.GroupListWriteService;
import com.inbook.groupMember.controller.GroupMemberButtonProcess;
import com.inbook.groupMember.controller.GroupMemberController;
import com.inbook.groupMember.controller.GroupMemberServiceImport;
import com.inbook.groupMemberajax.controller.GroupMemberAjaxController;
import com.inbook.groupcomment.controller.GroupCommentController;
import com.inbook.groupcomment.dao.GroupCommentDAO;
import com.inbook.groupcomment.service.GroupCommentDeleteService;
import com.inbook.groupcomment.service.GroupCommentListService;
import com.inbook.groupcomment.service.GroupCommentUpdateService;
import com.inbook.groupcomment.service.GroupCommentWriteService;
import com.inbook.main.controller.MainController;
import com.inbook.member.controller.MemberController;
import com.inbook.member.dao.MemberDAO;
import com.inbook.member.service.MemberDeleteService;
import com.inbook.member.service.MemberGradeUpdateService;
import com.inbook.member.service.MemberIdCheckService;
import com.inbook.member.service.MemberListService;
import com.inbook.member.service.MemberLoginService;
import com.inbook.member.service.MemberStatusUpdateService;
import com.inbook.member.service.MemberUpdateService;
import com.inbook.member.service.MemberViewService;
import com.inbook.member.service.MemberWriteService;
import com.inbook.memberajax.controller.MemberAjaxController;
import com.inbook.chatRoom.controller.ChatRoomController;
import com.inbook.chatRoom.dao.ChatRoomDAO;
import com.inbook.chatRoom.service.ChatRoomDeleteService;
import com.inbook.chatRoom.service.ChatRoomListService;
import com.inbook.chatRoom.service.ChatRoomUpdateService;
import com.inbook.chatRoom.service.ChatRoomWriteService;
import com.inbook.chatajax.controller.ChatAjaxController;
import com.inbook.comment.controller.CommentController;
import com.inbook.comment.dao.CommentDAO;
import com.inbook.comment.service.CommentDeleteService;
import com.inbook.comment.service.CommentListService;
import com.inbook.comment.service.CommentUpdateService;
import com.inbook.comment.service.CommentWriteService;


/**
 * 
 * Servlet implementation class DisPacherServlet
 */
@WebServlet("/DisPacherServlet")
public class DispacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, Controller> controllerMap = new HashMap<String, Controller>();
    /**
     * Default constructor. 
     */
    public DispacherServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		
		// 일반 게시판 객체
		BoardDAO boardDAO = new BoardDAO();
				
		BoardListService boardListService = new BoardListService();
		boardListService.setDao(boardDAO);
		
		BoardViewService boardViewService = new BoardViewService();
		boardViewService.setDao(boardDAO);
		
		BoardWriteService boardWrtieService = new BoardWriteService();
		boardWrtieService.setDao(boardDAO);
		
		BoardUpdateService boardUpdateService = new BoardUpdateService();
		boardUpdateService.setDao(boardDAO);
		
		BoardDeleteService boardDeleteService = new BoardDeleteService();
		boardDeleteService.setDao(boardDAO);
		
		BoardLikeService boardLikeService = new BoardLikeService();
		boardLikeService.setDao(boardDAO);
		
		BoardLikeCancelService boardLikeCancelService = new BoardLikeCancelService();
		boardLikeCancelService.setDao(boardDAO);
		
		BoardController boardController = new BoardController();
		boardController.setBoardListService(boardListService);
		boardController.setBoardViewService(boardViewService);
		boardController.setBoardWriteService(boardWrtieService);
		boardController.setBoardUpdateService(boardUpdateService);
		boardController.setBoardDeleteService(boardDeleteService);
		boardController.setBoardLikeService(boardLikeService);
		boardController.setBoardLikeCancelService(boardLikeCancelService);
		
		
		// 댓글
		CommentDAO commentDAO = new CommentDAO();
		CommentListService commentListService = new CommentListService();
		commentListService.setDao(commentDAO);
		CommentWriteService commentWriteService = new CommentWriteService();
		commentWriteService.setDao(commentDAO);
		CommentUpdateService commentUpdateService = new CommentUpdateService();
		commentUpdateService.setDao(commentDAO);
		CommentDeleteService commentDeleteService = new CommentDeleteService();
		commentDeleteService.setDao(commentDAO);
		
		boardController.setCommentListService(commentListService);
		
		CommentController commentController = new CommentController();
		commentController.setCommentWriteService(commentWriteService);
		commentController.setCommentUpdateService(commentUpdateService);
		commentController.setCommentDeleteService(commentDeleteService);
		
		
		
	

		
		MemberDAO memberDAO = new MemberDAO();
		MemberLoginService memberLoginService = new MemberLoginService();
		memberLoginService.setDao(memberDAO);
		MemberListService memberListService = new MemberListService();
		memberListService.setDao(memberDAO);
		MemberViewService memberViewService = new MemberViewService();
		memberViewService.setDao(memberDAO);
		MemberWriteService memberWriteService = new MemberWriteService();
		memberWriteService.setDao(memberDAO);
		MemberGradeUpdateService memberGradeUpdateService = new MemberGradeUpdateService();
		memberGradeUpdateService.setDao(memberDAO);
		MemberStatusUpdateService memberStatusUpdateService = new MemberStatusUpdateService();
		memberStatusUpdateService.setDao(memberDAO);
		MemberIdCheckService memberIdCheckService = new MemberIdCheckService();
		memberIdCheckService.setDao(memberDAO);
		MemberUpdateService memberUpdateService = new MemberUpdateService();
		memberUpdateService.setDao(memberDAO);
		MemberDeleteService memberDeleteService = new MemberDeleteService();
		memberDeleteService.setDao(memberDAO);
		
		MemberController memberController = new MemberController();
		
		memberController.setMemberLoginService(memberLoginService);
		memberController.setMemberWriteService(memberWriteService);
		memberController.setMemberListService(memberListService);
		memberController.setMemberViewService(memberViewService);
		memberController.setMemberStatusUpdateService(memberStatusUpdateService);
		memberController.setMemberGradeUpdateService(memberGradeUpdateService);
		memberController.setMemberUpdateService(memberUpdateService);
		memberController.setMemberDeleteService(memberDeleteService);
		
		
		
		MemberAjaxController memberAjaxController = new MemberAjaxController();
		
		memberAjaxController.setMemberIdCheckService(memberIdCheckService);
		

		
		// 메인
		MainController mainController = new MainController();
		mainController.setBoardListService(boardListService);
		
		//친구관리
		FriendDAO friendDAO = new FriendDAO();
		
		FriendListService friendListService = new FriendListService();
		friendListService.setDao(friendDAO);
		
		
		FriendRequestSendService friendRequestSendService = new FriendRequestSendService();
		friendRequestSendService.setDao(friendDAO);
		
		
		FriendDeleteService friendDeleteService = new FriendDeleteService();
		friendDeleteService.setDao(friendDAO);
		
		FriendSearchService friendSearchService = new FriendSearchService();
		friendSearchService.setDao(friendDAO);
		
		FriendRequestCountService friendRequestCountService = new FriendRequestCountService();
		friendRequestCountService.setDao(friendDAO);
		
		FriendIdListService friendIdListService = new FriendIdListService();
		friendIdListService.setDao(friendDAO);
		
		FriendRequestIdListService friendRequestIdListService = new FriendRequestIdListService();
		friendRequestIdListService.setDao(friendDAO);
		
		FriendRequestAcceptService friendRequestAcceptService = new FriendRequestAcceptService();
		friendRequestAcceptService.setDao(friendDAO);
		
		FriendRequestRefuseService friendRequestRefuseService = new FriendRequestRefuseService();
		friendRequestRefuseService.setDao(friendDAO);
		
		FriendRequestIdService friendRequestIdService = new FriendRequestIdService();
		friendRequestIdService.setDao(friendDAO);
		
		FriendRequestSendIdService friendRequestSendIdService = new FriendRequestSendIdService();
		friendRequestSendIdService.setDao(friendDAO);
		
		FriendRequestCancelService friendRequestCancelService = new FriendRequestCancelService();
		friendRequestCancelService.setDao(friendDAO);
		
		FriendSuggestionsService friendSuggestionsService = new FriendSuggestionsService();
		friendSuggestionsService.setDao(friendDAO);
		
		FriendAllUserListService friendAllUserListService = new FriendAllUserListService();
		friendAllUserListService.setDao(friendDAO);
		
		FriendController friendController = new FriendController();

		FriendServiceImport friendServiceImport = new FriendServiceImport();
		System.out.println("임포트 set 실행중");
		friendServiceImport.setFriendRequestSendService(friendRequestSendService);
		friendServiceImport.setFriendRequestAcceptService(friendRequestAcceptService);
		friendServiceImport.setFriendRequestRefuseService(friendRequestRefuseService);
		friendServiceImport.setFriendRequestCancelService(friendRequestCancelService);
		friendServiceImport.setFriendDeleteService(friendDeleteService);
		
		friendServiceImport.setFriendListService(friendListService);
		friendServiceImport.setFriendSearchService(friendSearchService);
		friendServiceImport.setFriendIdListService(friendIdListService);
		
		friendServiceImport.setFriendRequestIdListService(friendRequestIdListService);
		friendServiceImport.setFriendRequestIdService(friendRequestIdService);
		friendServiceImport.setFriendRequestSendIdService(friendRequestSendIdService);
		friendServiceImport.setFriendSuggestionsService(friendSuggestionsService);
		friendServiceImport.setFriendAllUserListService(friendAllUserListService);
		
		ButtonProcess buttonProcess = new ButtonProcess();
		friendController.setFriendServiceImport(friendServiceImport);
		friendController.setButtonProcess(buttonProcess);
		buttonProcess.setFriendServiceImport(friendServiceImport);
		
		//친구 서비스 추가
		memberController.setFriendServiceImport(friendServiceImport);
		
		FriendAjaxController friendAjaxController = new FriendAjaxController();
		
		friendAjaxController.setFriendRequestCountService(friendRequestCountService);
		//친구 관리 끝
		
		
		ChatRoomDAO chatRoomDAO = new ChatRoomDAO();
		
		ChatRoomListService chatRoomListService = new ChatRoomListService();
		chatRoomListService.setDao(chatRoomDAO);
		ChatRoomWriteService chatRoomWriteService = new ChatRoomWriteService();
		chatRoomWriteService.setDao(chatRoomDAO);
		ChatRoomUpdateService chatRoomUpdateService = new ChatRoomUpdateService();
		chatRoomUpdateService.setDao(chatRoomDAO);
		ChatRoomDeleteService chatRoomDeleteService = new ChatRoomDeleteService();
		chatRoomDeleteService.setDao(chatRoomDAO);
		
		ChatRoomController chatRoomController = new ChatRoomController();
		chatRoomController.setChatRoomListService(chatRoomListService);
		chatRoomController.setChatRoomWriteService(chatRoomWriteService);
		chatRoomController.setChatRoomUpdateService(chatRoomUpdateService);
		chatRoomController.setChatRoomDeleteService(chatRoomDeleteService);

		ChatDAO chatDAO = new ChatDAO();
		
		ChatMaxNoService chatMaxNoService = new ChatMaxNoService();
		chatMaxNoService.setDao(chatDAO);
		ChatSendDataService chatSendDataService = new ChatSendDataService();
		chatSendDataService.setDao(chatDAO);
		ChatGetDataService chatGetDataService = new ChatGetDataService();
		chatGetDataService.setDao(chatDAO);
		
		ChatController chatController = new ChatController();
		chatController.setChatMaxNoService(chatMaxNoService);
		
		
		
		ChatAjaxController chatAjaxController = new ChatAjaxController();
		chatAjaxController.setChatSendDataService(chatSendDataService);
		chatAjaxController.setChatGetDataService(chatGetDataService);
		
		// 그룹
				GroupListDAO groupListDAO = new GroupListDAO();
				GroupListListService groupListListService = new GroupListListService();
				groupListListService.setDao(groupListDAO);
				GroupListViewService groupListViewService = new GroupListViewService();
				groupListViewService.setDao(groupListDAO);
				GroupListWriteService groupListWriteService = new GroupListWriteService();
				groupListWriteService.setDao(groupListDAO);
				GroupListUpdateService groupListUpdateService = new GroupListUpdateService();
				groupListUpdateService.setDao(groupListDAO);
				GroupListDeleteService groupListDeleteService = new GroupListDeleteService();
				groupListDeleteService.setDao(groupListDAO);
				GroupListLikeService groupListLikeService = new GroupListLikeService();
				groupListLikeService.setDao(groupListDAO);
				GroupListLikeCancelService groupListLikeCancelService = new GroupListLikeCancelService();
				groupListLikeCancelService.setDao(groupListDAO);
				
				
				
				GroupListController groupListController = new GroupListController();
				groupListController.setGroupListListService(groupListListService);
				groupListController.setGroupListViewService(groupListViewService);
				groupListController.setGroupListWriteService(groupListWriteService);	
				groupListController.setGroupListUpdateService(groupListUpdateService);
				groupListController.setGroupListDeleteService(groupListDeleteService);
				groupListController.setGroupListLikeService(groupListLikeService);
				groupListController.setGroupListLikeCancelService(groupListLikeCancelService);
				
				GroupCommentDAO groupCommentDAO  = new GroupCommentDAO();
				GroupCommentListService groupCommentListService = new GroupCommentListService();
				groupCommentListService.setDao(groupCommentDAO);
				GroupCommentWriteService groupCommentWriteService = new GroupCommentWriteService();
				groupCommentWriteService.setDao(groupCommentDAO);
				GroupCommentUpdateService groupCommentUpdateService = new GroupCommentUpdateService();
				groupCommentUpdateService.setDao(groupCommentDAO);
				GroupCommentDeleteService groupCommentDeleteService = new GroupCommentDeleteService();
				groupCommentDeleteService.setDao(groupCommentDAO);
				
				groupListController.setGroupCommentListService(groupCommentListService);
				
				
				GroupCommentController groupcommentController = new GroupCommentController();
				groupcommentController.setGroupCommentWriteService(groupCommentWriteService);
				groupcommentController.setGroupCommentUpdateService(groupCommentUpdateService);
				groupcommentController.setGroupCommentDeleteService(groupCommentDeleteService);
				
				
				// 그룹 회원 
//				GroupMemberController groupMemberController = new GroupMemberController();
//
//				GroupMemberServiceImport groupMemberServiceImport = new GroupMemberServiceImport();
//				System.out.println("임포트 set 실행중");
//				groupMemberServiceImport.setFriendRequestSendService(friendRequestSendService);
//				groupMemberServiceImport.setFriendRequestAcceptService(friendRequestAcceptService);
//				groupMemberServiceImport.setFriendRequestRefuseService(friendRequestRefuseService);
//				groupMemberServiceImport.setFriendRequestCancelService(friendRequestCancelService);
//				groupMemberServiceImport.setFriendDeleteService(friendDeleteService);
//				
//				groupMemberServiceImport.setFriendListService(friendListService);
//				groupMemberServiceImport.setFriendSearchService(friendSearchService);
//				groupMemberServiceImport.setFriendIdListService(friendIdListService);
//				
//				groupMemberServiceImport.setFriendRequestIdListService(friendRequestIdListService);
//				groupMemberServiceImport.setFriendRequestIdService(friendRequestIdService);
//				groupMemberServiceImport.setFriendRequestSendIdService(friendRequestSendIdService);
//				
//				GroupMemberButtonProcess groupMemberButtonProcess = new GroupMemberButtonProcess();
//				friendController.setFriendServiceImport(friendServiceImport);
//				friendController.setButtonProcess(buttonProcess);
//				buttonProcess.setFriendServiceImport(friendServiceImport);
		
		
		//친구 서비스 추가
		memberController.setFriendServiceImport(friendServiceImport);
		
		GroupMemberAjaxController groupMemberAjaxController = new GroupMemberAjaxController();
		
		groupMemberAjaxController.setFriendRequestCountService(friendRequestCountService);
		//친구 관리 
		
		
		
		controllerMap.put("/member", memberController);
		controllerMap.put("/mypage", memberController);
		controllerMap.put("/friend", friendController);
		controllerMap.put("/board", boardController);
		controllerMap.put("/comment", commentController);
		controllerMap.put("/groupComment", groupcommentController);
		controllerMap.put("/main", mainController);
		controllerMap.put("/chatRoom", chatRoomController);
		controllerMap.put("/chat", chatController);
		controllerMap.put("/groupList", groupListController);
		controllerMap.put("/groupMember", groupListController);
	
		//ajax 컨트롤러
		controllerMap.put("/memberajax", memberAjaxController);
		controllerMap.put("/friendAjax", friendAjaxController);
		controllerMap.put("/chatAjax", chatAjaxController);
		
		 
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uri = request.getServletPath();
		//Ajax uri 를 가진 페이지는 로그를 안내보낸다
		if(uri.indexOf("Ajax") != -1 ) {
			
			
//			uri = request.getServletPath();
			
			if(uri.equals("/index.do")) {
				
				response.sendRedirect("/main/main.do");
				return;
			}
			
			int pos = uri.indexOf("/", 1);
			if(pos < 0 ) throw new ServletException("잘못된 페이지 요청");
			
			String module = uri.substring(0, pos);
			
			try {
				
				Controller controller = controllerMap.get(module);
				if(controller == null) System.out.println("DispacherServlet.service() - 요청하신 모듈의 페이지가 존재하지 않는다");
				
				else {
					String jsp = controller.execute(request);
					if(jsp != null) {
						if(jsp.indexOf("redirect:") != 0) {
							request.getRequestDispatcher("/WEB-INF/views/"+jsp+".jsp").forward(request, response);
						}else {
							response.sendRedirect(jsp.substring("redirect:".length()));
						}
					}
				}
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}

		}
		else {
			
			
			System.out.println("DispacherServlet.service() - *.do URL 요청하고 있다");
			
			
//			uri = request.getServletPath();
			System.out.println("DispacherServlet.service() - "+ uri);
			
			if(uri.equals("/index.do")) {
				
				response.sendRedirect("/main/main.do");
				return;
			}
			
			int pos = uri.indexOf("/", 1);
			if(pos < 0 ) throw new ServletException("잘못된 페이지 요청");
			
			String module = uri.substring(0, pos);
			System.out.println("DispacherServlet.service() - "+ module);
			
			try {
				
				Controller controller = controllerMap.get(module);
				if(controller == null) System.out.println("DispacherServlet.service() - 요청하신 모듈의 페이지가 존재하지 않는다");
				
				else {
					String jsp = controller.execute(request);
					if(jsp != null) {
						if(jsp.indexOf("redirect:") != 0) {
							request.getRequestDispatcher("/WEB-INF/views/"+jsp+".jsp").forward(request, response);
						}else {
							response.sendRedirect(jsp.substring("redirect:".length()));
							System.out.println("보내지는 url : "+jsp.substring("redirect:".length()));
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			
		}
		
	}
	

}
