package com.inbook.friend.service;

import com.inbook.friend.dao.FriendDAO;
import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Service;

public class FriendRequestRefuseService implements Service{

	private FriendDAO dao;
	public void setDao(FriendDAO dao) {
		this.dao = dao;
	}
	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public Integer service(Object obj) throws Exception {
		
		FriendVO vo = (FriendVO) obj;
		
		return dao.friendRequestRefuse(vo);
	}
	
}
