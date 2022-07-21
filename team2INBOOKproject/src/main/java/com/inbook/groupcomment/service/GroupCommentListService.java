package com.inbook.groupcomment.service;

import com.inbook.groupcomment.dao.GroupCommentDAO;
import com.inbook.main.Service;
import com.inbook.util.ReplyPageObject;

public class GroupCommentListService implements Service {
	
	private GroupCommentDAO dao;

	public void setDao(GroupCommentDAO dao) {
		this.dao = dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		ReplyPageObject replyPageObject = (ReplyPageObject) obj;
		replyPageObject.setTotalRow(dao.getTotalRow(replyPageObject));
		return dao.list(replyPageObject);
	}

}
