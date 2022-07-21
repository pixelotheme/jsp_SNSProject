package com.inbook.member.service;

import com.inbook.main.Service;
import com.inbook.member.dao.MemberDAO;
import com.inbook.member.vo.MemberVO;

public class MemberStatusUpdateService implements Service {

	private MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		
		MemberVO vo = (MemberVO) obj;
		return dao.statusUpdate(vo);
	}

}
