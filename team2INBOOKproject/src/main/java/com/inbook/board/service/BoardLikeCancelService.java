package com.inbook.board.service;

import com.inbook.board.dao.BoardDAO;
import com.inbook.board.vo.BoardVO;
import com.inbook.main.Service;

public class BoardLikeCancelService implements Service{

	private BoardDAO dao;
	
	public void setDao(Object dao) {
		this.dao = (BoardDAO) dao;
	}

	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public Integer service(Object vo) throws Exception {
		return dao.likeCancel((BoardVO) vo);
	}
	
}

