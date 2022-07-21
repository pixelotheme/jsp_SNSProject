package com.inbook.util;

import com.webjjang.util.PageObject;

public class ChatRoomPageObject extends PageObject {
	
	private Long no;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "ChatRoomPageObject [" + super.toString() + ",no=" + no + "]";
	}

}
