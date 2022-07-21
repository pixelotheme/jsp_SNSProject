package com.inbook.groupList.service;

import com.inbook.groupList.dao.GroupListDAO;
import com.inbook.groupList.vo.GroupListVO;
import com.inbook.board.vo.BoardVO;
import com.inbook.main.Service;

public class GroupListWriteService implements Service {
	private GroupListDAO dao;
	
	public void setDao(GroupListDAO dao) {
		this.dao = dao;
	}

	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public Integer service(Object obj) throws Exception {
		System.out.println("BoardWriteService-------------------");
		GroupListVO vo = (GroupListVO) obj;
		// 메서드를 호출한다.
		return dao.write(vo);
	}
	
}

