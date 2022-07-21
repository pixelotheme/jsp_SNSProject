package com.inbook.board.service;

import com.inbook.board.dao.BoardDAO;
import com.inbook.board.vo.BoardVO;
import com.inbook.main.Service;

public class BoardUpdateService implements Service{
	
	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public Integer service(Object obj) throws Exception {
		System.out.println("BoardUpdateService-------------------");
		BoardVO vo = (BoardVO) obj;
		// 메서드를 호출한다.
		return dao.update(vo);
	}
	
}

