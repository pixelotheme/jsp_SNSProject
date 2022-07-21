package com.inbook.member.service;

import com.inbook.main.Service;
import com.inbook.member.dao.MemberDAO;
import com.inbook.member.vo.LoginVO;

public class MemberLoginService implements Service{

	private MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public LoginVO service(Object obj) throws Exception {
		LoginVO vo = (LoginVO) obj;
		
		return dao.login(vo);
	}
	
}
