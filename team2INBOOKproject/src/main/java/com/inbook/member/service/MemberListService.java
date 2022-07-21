package com.inbook.member.service;

import java.util.List;

import com.inbook.main.Service;
import com.inbook.member.dao.MemberDAO;
import com.inbook.member.vo.MemberVO;
import com.webjjang.util.PageObject;

public class MemberListService implements Service {

	private MemberDAO dao;
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<MemberVO> service(Object obj) throws Exception {
		PageObject pageObject = (PageObject) obj;
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		return dao.list(pageObject);
	}
	
}
