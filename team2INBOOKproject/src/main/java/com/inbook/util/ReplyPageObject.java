package com.inbook.util;

import com.webjjang.util.PageObject;

public class ReplyPageObject extends PageObject {
	
	private Long no;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "ReplyPageObject [" + super.toString() + ",no=" + no + "]";
	}

}
