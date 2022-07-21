package com.inbook.comment.service;

import com.inbook.comment.dao.CommentDAO;
import com.inbook.main.Service;
import com.inbook.util.ReplyPageObject;

public class CommentListService implements Service {
	
	private CommentDAO dao;

	public void setDao(CommentDAO dao) {
		this.dao = dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		ReplyPageObject replyPageObject = (ReplyPageObject) obj;
		replyPageObject.setTotalRow(dao.getTotalRow(replyPageObject));
		return dao.list(replyPageObject);
	}

}
