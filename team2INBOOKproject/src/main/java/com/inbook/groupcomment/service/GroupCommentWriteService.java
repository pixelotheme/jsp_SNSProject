package com.inbook.groupcomment.service;

import com.inbook.groupcomment.dao.GroupCommentDAO;
import com.inbook.groupcomment.vo.GroupCommentVO;
import com.inbook.main.Service;

public class GroupCommentWriteService implements Service {
	
	private GroupCommentDAO dao;

	public void setDao(GroupCommentDAO dao) {
		this.dao = dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		GroupCommentVO vo = (GroupCommentVO) obj;
		
		return dao.write(vo);
	}

}
