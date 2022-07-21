package com.inbook.groupList.service;

import com.inbook.groupList.dao.GroupListDAO;
import com.inbook.groupList.vo.GroupListVO;
import com.inbook.main.Service;

public class GroupListUpdateService implements Service{
	
	private GroupListDAO dao;
	
	public void setDao(GroupListDAO dao) {
		this.dao = dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		System.out.println("GroupListUpdateService-------------------");
		GroupListVO vo = (GroupListVO) obj;
		return dao.update(vo);
	}
	
}

