package com.inbook.member.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.inbook.board.service.BoardListService;
import com.inbook.board.vo.BoardVO;
import com.inbook.friend.controller.FriendServiceImport;
import com.inbook.friend.service.FriendListService;
import com.inbook.main.Controller;
import com.inbook.main.Execute;
import com.inbook.main.Service;
import com.inbook.member.dao.MemberDAO;
import com.inbook.member.service.MemberDeleteService;
import com.inbook.member.service.MemberGradeUpdateService;
import com.inbook.member.service.MemberListService;
import com.inbook.member.service.MemberLoginService;
import com.inbook.member.service.MemberStatusUpdateService;
import com.inbook.member.service.MemberUpdateService;
import com.inbook.member.service.MemberViewService;
import com.inbook.member.service.MemberWriteService;
import com.inbook.member.vo.LoginVO;
import com.inbook.member.vo.MemberVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.webjjang.util.PageObject;

public class MemberController implements Controller {

	private MemberLoginService memberLoginService;
	private MemberWriteService memberWriteService;
	private MemberListService memberListService;
	private MemberViewService memberViewService;
	private MemberGradeUpdateService memberGradeUpdateService;
	private MemberStatusUpdateService memberStatusUpdateService;
	private MemberUpdateService memberUpdateService;
	private MemberDeleteService memberDeleteService;
	private FriendServiceImport friendServiceImport;

	public void setMemberUpdateService(MemberUpdateService memberUpdateService) {
		this.memberUpdateService = memberUpdateService;
	}

	public void setMemberDeleteService(MemberDeleteService memberDeleteService) {
		this.memberDeleteService = memberDeleteService;
	}

	public void setMemberStatusUpdateService(MemberStatusUpdateService memberStatusUpdateService) {
		this.memberStatusUpdateService = memberStatusUpdateService;
	}

	public void setMemberGradeUpdateService(MemberGradeUpdateService memberGradeUpdateService) {
		this.memberGradeUpdateService = memberGradeUpdateService;
	}

	public void setMemberViewService(MemberViewService memberViewService) {
		this.memberViewService = memberViewService;
	}

	public void setMemberListService(MemberListService memberListService) {
		this.memberListService = memberListService;
	}

	public void setMemberLoginService(MemberLoginService memberLoginService) {
		this.memberLoginService = memberLoginService;
	}

	public void setMemberWriteService(MemberWriteService memberWriteService) {
		this.memberWriteService = memberWriteService;
	}

	
	public void setFriendServiceImport(FriendServiceImport friendServiceImport) {
		this.friendServiceImport = friendServiceImport;
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		System.out.println("MemberController.execute()");

		String jsp = null;

		String uri = request.getServletPath();
		String serviceUri = uri.substring(uri.indexOf("/", 1));
		System.out.println("MemberController.execute().serviceUri - " + serviceUri);

		switch (serviceUri) {
		case "/writeForm.do":
			jsp = "member/writeForm";
			break;

		case "/view.do":

			String viewid = request.getParameter("id");

			boolean isMyPage = false;
			if (viewid == null) {
				isMyPage = true;
				viewid = ((LoginVO) request.getSession().getAttribute("login")).getId();
			}

			request.setAttribute("vo", Execute.service(memberViewService, viewid));
			request.setAttribute("isMyPage", isMyPage);

			jsp = "member/view";
			break;
			
		case "/mypage.do" :
			
			String vid = request.getParameter("id");
			vid = ((LoginVO) request.getSession().getAttribute("login")).getId();
			request.setAttribute("vo", Execute.service(memberViewService, vid));
			
			jsp = "member/mypage";
			break;
			
		case "/mypage2.do" :
			
			String uid = request.getParameter("id");
			System.out.println("uid : "+ uid);
			request.setAttribute("vo", Execute.service(memberViewService, uid));
			
			String loginId = (String)((LoginVO)request.getSession().getAttribute("login")).getId();
			
			//dispacher 에 추가(친구 맺기관련 버튼 메서드 호출)
			request.setAttribute("friendIdList", Execute.service(friendServiceImport.getFriendIdListService(), loginId));
			request.setAttribute("friendRequestSendId", Execute.service(friendServiceImport.getFriendRequestSendIdService(), loginId));
			request.setAttribute("friendRequestId", Execute.service(friendServiceImport.getFriendRequestIdService(), loginId));
			
			jsp = "member/mypage2";
			break;

		case "/statusUpdate.do":

			String stvo = request.getParameter("id");
			String status = request.getParameter("status");

			MemberVO vo3 = new MemberVO();
			vo3.setId(stvo);
			vo3.setStatus(status);

			Execute.service(memberStatusUpdateService, vo3);

			jsp = "redirect:/member/list.do";
			break;

		case "/write.do":

			String savePath = "/upload/member/";
			System.out.println("MemberController.savePath - " + savePath);

			String realSavePath = request.getServletContext().getRealPath(savePath);

			File folder = new File(realSavePath);
			boolean isExist = folder.exists();
			System.out.println("MemberController.realSavePath - " + realSavePath + ", 존재 여부 : " + isExist);

			if (!isExist)
				folder.mkdirs();
			System.out.println("MemberController - 저장 폴더가 없다면 만들어 진다.");

			int maxSize = 1024 * 1024 * 20;

			MultipartRequest multi = new MultipartRequest(request, realSavePath, maxSize, "utf-8",
					new DefaultFileRenamePolicy());

			String id = multi.getParameter("id");
			String pw = multi.getParameter("pw");
			String name = multi.getParameter("name");
			String gender = multi.getParameter("gender");
			String birth = String.join("-", multi.getParameterValues("births"));
			String tel = multi.getParameter("tel");
			String email = multi.getParameter("email");
			String fileName = multi.getFilesystemName("photoFile");
			String photo = null;
			if (fileName != null && !fileName.equals(""))
					photo = savePath + fileName;
			else photo = "/upload/member/noImage.jpg";

			MemberVO memberVo = new MemberVO();
			memberVo.setId(id);
			memberVo.setPw(pw);
			memberVo.setName(name);
			memberVo.setGender(gender);
			memberVo.setBirth(birth);
			memberVo.setTel(tel);
			memberVo.setEmail(email);
			memberVo.setPhoto(photo);

			System.out.println("MemberController-write.memberVo - " + memberVo);

			Execute.service(memberWriteService, memberVo);

			jsp = "redirect:/main/main.do";
			break;

		case "/gradeUpdate.do":

			// id 정보를 id1으로 저장
			String guid = request.getParameter("id");
			// 등급을 저장
			String strGradeNo = request.getParameter("gradeNo");
			// 변환
			int gradeNo = Integer.parseInt(strGradeNo);

			MemberVO vo1 = new MemberVO();
			// 에 해당 정보담기
			vo1.setId(guid);
			vo1.setGradeNo(gradeNo);

			Execute.service(memberGradeUpdateService, vo1);

			jsp = "redirect:/member/list.do";
			break;

		case "/list.do":

			PageObject pageObject1 = PageObject.getInstance(request);

			request.setAttribute("list", Execute.service(memberListService, pageObject1));
			request.setAttribute("pageObject", pageObject1);

			jsp = "member/list";
			
			break;
			
			
			case "/updateForm.do":
				
				request.setCharacterEncoding("utf-8");
				
				id = ((LoginVO) request.getSession().getAttribute("login")).getId();
				request.setAttribute("vo", Execute.service(memberViewService, id));
				
			jsp = "member/updateForm";
			break;
			
			
			case "/update.do":
				
				// 사진 저장장소 설정
				String savePath2 = "/upload/member/";
				System.out.println("MemberController.savePath - " + savePath2);
				
				String realSavePath2 = request.getServletContext().getRealPath(savePath2);

				File folder2 = new File(realSavePath2);
				boolean isExist2 = folder2.exists();
				System.out.println("MemberController.realSavePath - " + realSavePath2 + ", 존재 여부 : " + isExist2);

				if (!isExist2)
					folder2.mkdirs();
				System.out.println("MemberController - 저장 폴더가 없다면 만들어 진다.");

				int maxSize2 = 1024 * 1024 * 20;
		
				MultipartRequest multi2 = new MultipartRequest(request, realSavePath2, maxSize2, "utf-8",
						new DefaultFileRenamePolicy());
				
				//새로 입력받기
				String npw = multi2.getParameter("pw");
				
				String nname = multi2.getParameter("name");
				String ngender = multi2.getParameter("gender");
				String nbirth = String.join("-", multi2.getParameterValues("births"));
				String ntel= multi2.getParameter("tel");
				String nemail = multi2.getParameter("email");
				String uvid = multi2.getParameter("id");
				String fileName2 = multi2.getFilesystemName("photoFile");
				
				String photo2 = null;
				if (fileName2 != null && !fileName2.equals(""))
					photo2 = savePath2 + fileName2;
				else
					photo2 = "/upload/member/noImage.jpg";
				
				// vo 새로 세팅
				MemberVO NewVo = new MemberVO();
				
				//입력받은것 vo에 적용	
				NewVo.setPw(npw);
				NewVo.setName(nname);
				NewVo.setGender(ngender);
				NewVo.setBirth(nbirth);
				NewVo.setTel(ntel);
				NewVo.setEmail(nemail);
				NewVo.setPhoto(photo2);
				NewVo.setId(uvid);
				
				// 적용한것 다시 넣기
				request.setAttribute("vo", Execute.service(memberUpdateService, NewVo));
				
				jsp = "redirect:/member/mypage.do";
				break;
			
		case "/loginForm.do":
			
			jsp = "member/loginForm";
			break;
			
		case "/delete.do":
				
			String did = ((LoginVO)(request.getSession().getAttribute("login"))).getId();
		
			MemberVO vo = new MemberVO();
			vo.setId(did);
			
			Execute.service(memberDeleteService, vo);
			
			jsp = "redirect:/member/logout.do";
			break;

		case "/login.do":

			id = request.getParameter("id");
			pw = request.getParameter("pw");

			LoginVO vo11 = new LoginVO();
			vo11.setId(id);
			vo11.setPw(pw);

			LoginVO loginVO = (LoginVO) Execute.service(memberLoginService, vo11);

			if (loginVO != null) {
				request.getSession().setAttribute("login", loginVO);
				jsp = "redirect:/main/main.do";
			}

			else {
				jsp = "error/loginError";
			}

			break;

		case "/logout.do":

			request.getSession().removeAttribute("login");

			jsp = "redirect:/main/main.do";
			break;
			
			
		default:
			break;
		}

		return jsp;
	}

}
