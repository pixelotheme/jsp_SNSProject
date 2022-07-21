package com.inbook.friend.service;

import java.util.List;

import com.inbook.friend.dao.FriendDAO;
import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Service;
import com.webjjang.util.PageObject;

public class FriendListService implements Service {

	private FriendDAO dao;
	public void setDao(FriendDAO dao) {
		this.dao = dao;
	}
	@Override
	public List<FriendVO> service(Object obj) throws Exception {
		
		Object[] objs = (Object[]) obj;
		
		PageObject pageObject = (PageObject) objs[0];
		String id = (String) objs[1];
		
		pageObject.setTotalRow(dao.getTotalRow(pageObject,id));
		return dao.list(pageObject, id);
	}

	
}
