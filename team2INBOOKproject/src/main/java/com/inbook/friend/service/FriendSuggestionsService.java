package com.inbook.friend.service;

import java.util.List;

import com.inbook.friend.dao.FriendDAO;
import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Service;
import com.webjjang.util.PageObject;

public class FriendSuggestionsService implements Service {

	private FriendDAO dao;
	public void setDao(FriendDAO dao) {
		this.dao = dao;
	}
	@Override
	public List<FriendVO> service(Object obj) throws Exception {
		
		Object[] objs = (Object[]) obj;
		
		PageObject pageObject = (PageObject) objs[0];
		
		String id = (String) objs[1];
		String[] friendIdList = (String[]) objs[2];
		
//		pageObject.setTotalRow(dao.getTotalRow(pageObject,id)); 친구추천용으로 
		
		return dao.suggestions(pageObject, id,friendIdList);
	}

	
}
