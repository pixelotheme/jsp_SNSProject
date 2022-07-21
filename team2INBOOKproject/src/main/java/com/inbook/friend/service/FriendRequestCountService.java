package com.inbook.friend.service;

import com.inbook.friend.dao.FriendDAO;
import com.inbook.friend.vo.FriendVO;
import com.inbook.main.Service;

public class FriendRequestCountService implements Service{

	private FriendDAO dao;
	public void setDao(FriendDAO dao) {
		this.dao = dao;
	}
	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public Long service(Object obj) throws Exception {
//		System.out.println("BoardWriteService-------------------");
		
		String id = (String) obj;
		return dao.requestCount(id);
	}
	
}
