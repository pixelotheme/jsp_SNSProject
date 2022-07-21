package com.inbook.chat.service;

import com.inbook.chat.dao.ChatDAO;
import com.inbook.chat.vo.ChatVO;

import com.inbook.main.Service;

public class ChatSendDataService implements Service {
	private ChatDAO dao;
	
	public void setDao(ChatDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		
		ChatVO vo = (ChatVO) obj;
		
		return dao.chatSendData(vo);
	}

}
