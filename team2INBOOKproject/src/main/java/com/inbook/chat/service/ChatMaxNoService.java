package com.inbook.chat.service;

import java.util.List;

import com.inbook.chat.dao.ChatDAO;
import com.inbook.chat.vo.ChatVO;
import com.inbook.main.Service;
import com.webjjang.util.PageObject;

public class ChatMaxNoService implements Service {
	
	private ChatDAO dao;
	
	public void setDao(ChatDAO dao) {
		this.dao = dao;
	}

	@Override 
	public Long service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Long cno = (Long) obj;
			
		
		return dao.getMaxNo(cno);
		
	
	}

}
