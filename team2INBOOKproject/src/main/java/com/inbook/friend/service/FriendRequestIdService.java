package com.inbook.friend.service;

import java.util.List;

import com.inbook.friend.dao.FriendDAO;
import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Service;
import com.webjjang.util.PageObject;

public class FriendRequestIdService implements Service{

	private FriendDAO dao;
	public void setDao(FriendDAO dao) {
		this.dao = dao;
	}
	@Override
	public List<String> service(Object obj) throws Exception {

		String id = (String) obj;
		return dao.friendRequestId(id);
	}
	
}
