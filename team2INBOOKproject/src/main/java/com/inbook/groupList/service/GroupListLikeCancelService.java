package com.inbook.groupList.service;

import com.inbook.groupList.dao.GroupListDAO;
import com.inbook.groupList.vo.GroupListVO;
import com.inbook.main.Service;

public class GroupListLikeCancelService implements Service{

	private GroupListDAO dao;
	
	public void setDao(Object dao) {
		this.dao = (GroupListDAO) dao;
	}

	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public Integer service(Object vo) throws Exception {
		return dao.likeCancel((GroupListVO) vo);
	}
	
}

