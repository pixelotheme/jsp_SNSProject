package com.inbook.comment.service;

import com.inbook.comment.dao.CommentDAO;
import com.inbook.comment.vo.CommentVO;
import com.inbook.main.Service;

public class CommentWriteService implements Service {
	
	private CommentDAO dao;

	public void setDao(CommentDAO dao) {
		this.dao = dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		CommentVO vo = (CommentVO) obj;
		
		return dao.write(vo);
	}

}
