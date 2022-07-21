package com.inbook.chatRoom.vo;

public class ChatRoomVO {
	
	private Long cno;
	private String id, title;
	public Long getCno() {
		return cno;
	}
	public void setCno(Long cno) {
		this.cno = cno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "ChatRoomVO [cno=" + cno + ", id=" + id + ", title=" + title + "]";
	}

}