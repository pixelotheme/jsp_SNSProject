package com.inbook.board.service;

import com.inbook.board.dao.BoardDAO;
import com.inbook.board.vo.BoardVO;
import com.inbook.main.Service;

public class BoardViewService implements Service{
	
	private BoardDAO dao;
	
	// 밖에서 DAO를 전달해주는것을 저장한다.
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public BoardVO service(Object obj) throws Exception {
		
		Object[] objs = (Object[]) obj;
		long no = (long) objs[0];
		int inc = (int) objs[1];
		String id = (String) objs[2];
		// 메서드를 호출한다.
		// 조회수 1증가
		if(inc == 1)
			dao.increase(no);
		// 데이터 가져오는 메소드 호출 가져온 데이터를 리턴한다.
		return dao.view(no, id);
	}
	
}
