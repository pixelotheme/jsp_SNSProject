package com.inbook.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inbook.member.vo.LoginVO;


/**
 * Servlet Filter implementation class AuthorityFilter
 */
public class AuthorityFilter extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;
	
	// 권한 맵에 URL = 권한
	// Map<URI, 권한번호> - 권한이 필요 없는 페이지는 저장하지 않는다.
	Map<String, Integer> authorityMap = new HashMap<String, Integer>();

	/**
     * @see HttpFilter#HttpFilter()
     */
    public AuthorityFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		
		String uri = req.getServletPath();
		
		if(uri.indexOf("Ajax") == -1 ) {
			
			System.out.println("AuthorityFilter.doFilter().uri : " + uri);
		}
		Integer pageAuthority = authorityMap.get(uri);
		
		// 로그인이 필요한 경우
		if(pageAuthority != null) {
			HttpSession session = req.getSession();
			LoginVO loginVO = (LoginVO) session.getAttribute("login");
			if(uri.equals("/friendAjax/friendRequestCount.do")) {
				if(loginVO == null || loginVO.equals("")){
					
					((HttpServletResponse)response).getWriter().print("reload");
					return;
				}
			}
			if(loginVO == null) {
				((HttpServletResponse)response).sendRedirect("/member/loginForm.do");
				return;
			}
		
			if(pageAuthority == 9) {
				if(loginVO.getGradeNo() < 9) {
					((HttpServletResponse)response).sendRedirect("/error/authorityError.do");
					return;
				}
			}
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 * 서버가 실행이 될 때 초기화 시키기 위해 딱 한번만 실행되는 메서드
	 * uri의 페이지 권한 정보를 저장해 놓는다.
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("AuthorityFiter.inti()------------------------");
		
		// 페이지와 권한 저장
		
		// 이미지 권한
		authorityMap.put("/image/writeForm.do", 1);
		authorityMap.put("/image/write.do", 1);
		authorityMap.put("/image/updateForm.do", 1);
		authorityMap.put("/image/update.do", 1);
		authorityMap.put("/image/delete.do", 1);
		
		// 공지 사항 권한
		authorityMap.put("/notice/writeForm.do", 9);
		authorityMap.put("/notice/write.do", 9);
		authorityMap.put("/notice/updateForm.do", 9);
		authorityMap.put("/notice/update.do", 9);
		authorityMap.put("/notice/delete.do", 9);
		
		authorityMap.put("/friend/list.do", 1);
		authorityMap.put("/friend/search.do", 1);
		authorityMap.put("/friend/friendRequestList.do", 1);
		authorityMap.put("/friend/friendRequestSend.do", 1);
		authorityMap.put("/friend/friendRequestAccept.do", 1);
		authorityMap.put("/friend/friendRequestCancel.do", 1);
		authorityMap.put("/friend/friendRequestRefuse.do", 1);
		authorityMap.put("/friend/friendDelete.do", 1);
		authorityMap.put("/friend/suggestions.do", 1);
		authorityMap.put("/friendAjax/friendRequestCount.do", 1);
		
		// 회원관리 권한
		authorityMap.put("/member/list.do", 9);
		authorityMap.put("/member/view.do", 1);
	}

}
