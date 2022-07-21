 package com.inbook.board.service;

import java.util.List;

import com.inbook.board.dao.BoardDAO;
import com.inbook.board.vo.BoardVO;
import com.inbook.main.Service;
import com.webjjang.util.PageObject;

public class BoardListService implements Service {
	
	private BoardDAO dao;

	// 밖에서 DAO를 전달해주는것을 저장한다.
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public List<BoardVO> service(Object obj) throws Exception {
		System.out.println("BoardListService-------------------");
		PageObject pageObject = (PageObject) obj;
		// 메서드를 호출한다.
		// 전체 데이터 개수를 구해서 pageObject에 넣어주는 처리가 되어야 한다. 없으면 데이터 안 나옴.
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		return dao.list(pageObject);
	}
	
}
