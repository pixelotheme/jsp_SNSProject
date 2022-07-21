package com.inbook.chat.vo;

public class ChatVO {
	
	private Long no;
	private String id, content, writeDate;
	private Long cno;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public Long getCno() {
		return cno;
	}
	public void setCno(Long cno) {
		this.cno = cno;
	}
	@Override
	public String toString() {
		return "ChatVO [no=" + no + ", id=" + id + ", content=" + content + ", writeDate=" + writeDate + ", cno=" + cno
				+ "]";
	}
	
	

	
}