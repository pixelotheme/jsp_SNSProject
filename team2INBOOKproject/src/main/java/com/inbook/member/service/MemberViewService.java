package com.inbook.member.service;

import com.inbook.main.Service;
import com.inbook.member.dao.MemberDAO;
import com.inbook.member.vo.MemberVO;

public class MemberViewService implements Service{

	private MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	@Override
	
	public MemberVO service(Object obj) throws Exception {
		String id = (String) obj;
		
		return dao.view(id);
	}
	
}
