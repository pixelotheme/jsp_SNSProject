package com.inbook.member.service;

import com.inbook.main.Service;
import com.inbook.member.dao.MemberDAO;

public class MemberIdCheckService implements Service{

	private MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@Override

	public String service(Object obj) throws Exception {
		String id = (String) obj;
	
		return dao.idCheck(id);
	}
	
}
