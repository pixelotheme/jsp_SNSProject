package com.inbook.member.service;

import com.inbook.main.Service;
import com.inbook.member.dao.MemberDAO;
import com.inbook.member.vo.MemberVO;

public class MemberDeleteService implements Service {

private MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		System.out.println("멤버 삭제 서비스 실행중");
		MemberVO vo =  (MemberVO) obj;
		
		return dao.delete(vo);
	}

}
