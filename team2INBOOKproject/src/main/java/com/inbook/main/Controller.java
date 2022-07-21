package com.inbook.main;

import javax.servlet.http.HttpServletRequest;

public interface Controller {

	// String - JSP의 정보를 담고 있다. 또는 url 이동의 정보를 담고 있다.("redirect:url"의 형식을 사용한다.)
	public String execute(HttpServletRequest request) throws Exception;

}
