package com.inbook.friend.service;

import java.util.List;

import com.inbook.friend.dao.FriendDAO;
import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Service;
import com.webjjang.util.PageObject;

public class FriendSearchService implements Service {

	private FriendDAO dao;
	public void setDao(FriendDAO dao) {
		this.dao = dao;
	}
	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public List<FriendVO> service(Object obj) throws Exception {
		
		PageObject pageObject = (PageObject) obj;
		pageObject.setTotalRow(dao.getSearchTotalRow(pageObject));
		return dao.search(pageObject);
	}
	
}
