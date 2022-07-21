package com.inbook.chatRoom.service;

import com.inbook.chatRoom.dao.ChatRoomDAO;
import com.inbook.chatRoom.vo.ChatRoomVO;
import com.inbook.main.Service;

public class ChatRoomWriteService implements Service {

	private ChatRoomDAO dao;
	
	public void setDao(ChatRoomDAO dao) {
		this.dao = dao;
	}

	@Override
	// Controller - [Service] - DAO(Data Access Object)
	public Integer service(Object obj) throws Exception {
		ChatRoomVO vo = (ChatRoomVO) obj;
		// 메서드를 호출한다.
		return dao.write(vo);
	}
	
}
