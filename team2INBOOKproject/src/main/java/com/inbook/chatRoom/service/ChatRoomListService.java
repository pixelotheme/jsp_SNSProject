package com.inbook.chatRoom.service;

import java.util.List;

import com.inbook.chatRoom.dao.ChatRoomDAO;
import com.inbook.chatRoom.vo.ChatRoomVO;
import com.inbook.main.Service;
import com.webjjang.util.PageObject;


public class ChatRoomListService implements Service {
	
	private ChatRoomDAO dao;
	
	public void setDao(ChatRoomDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<ChatRoomVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		PageObject pageObject = (PageObject) obj;
		
		// pageObject.setTotalRow() 호출을 하지 않으면 데이터를 못 가져온다. startRow, endRow = 0을 세팅되기 때문에
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		return dao.list(pageObject);
		
	
	}

}
