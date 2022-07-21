package com.inbook.friend.service;

import java.util.List;

import com.inbook.friend.dao.FriendDAO;
import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Service;
import com.webjjang.util.PageObject;

public class FriendRequestSendIdService implements Service{

	private FriendDAO dao;
	public void setDao(FriendDAO dao) {
		this.dao = dao;
	}
	@Override
	// 친구신청 받은목록
	public List<String> service(Object obj) throws Exception {

		String id = (String) obj;
		
		return dao.friendRequestSendId(id);
	}
	
}
