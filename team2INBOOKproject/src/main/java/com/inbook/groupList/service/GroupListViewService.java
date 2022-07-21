package com.inbook.groupList.service;

import com.inbook.groupList.dao.GroupListDAO;
import com.inbook.groupList.vo.GroupListVO;
import com.inbook.main.Service;

public class GroupListViewService implements Service{
	
	private GroupListDAO dao;
	
	// 밖에서 DAO를 전달해주는것을 저장한다.
	public void setDao(GroupListDAO dao) {
		this.dao = dao;
	}

	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public GroupListVO service(Object obj) throws Exception {
		
		Object[] objs = (Object[]) obj;
		long no = (long) objs[0];
		int inc = (int) objs[1];
		String id = (String) objs[2];
		// 조회수 1증가
		if(inc == 1)
			dao.increase(no);
		return dao.view(no, id);
	}
	
}
