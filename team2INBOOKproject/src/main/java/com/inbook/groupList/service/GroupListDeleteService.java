package com.inbook.groupList.service;

import com.inbook.groupList.dao.GroupListDAO;
import com.inbook.main.Service;

public class GroupListDeleteService implements Service{

	private GroupListDAO dao;
	
	public void setDao(GroupListDAO dao) {
		this.dao = dao;
	}

	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public Integer service(Object obj) throws Exception {
		System.out.println("GroupListDeleteService-------------------");
		long no = (long) obj;
		// 메서드를 호출한다.
		return dao.delete(no);
	}
	
}

