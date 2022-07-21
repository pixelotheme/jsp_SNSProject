package com.inbook.chatRoom.service;

import com.inbook.chatRoom.dao.ChatRoomDAO;
import com.inbook.chatRoom.vo.ChatRoomVO;
import com.inbook.main.Service;

public class ChatRoomDeleteService implements Service {
	
	private ChatRoomDAO dao;
	
	public void setDao(ChatRoomDAO dao) {
		this.dao = dao;
		
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		ChatRoomVO vo = (ChatRoomVO) obj;
		return dao.delete(vo);
	}

}
