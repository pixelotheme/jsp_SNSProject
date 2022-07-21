package com.inbook.groupcomment.vo;

public class GroupCommentVO {
	
	private long rno, no;
	private String content, photo, id, writeDate;
	
	public long getRno() {
		return rno;
	}
	public void setRno(long rno) {
		this.rno = rno;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	@Override
	public String toString() {
		return "CommentVO [rno=" + rno + ", no=" + no + ", content=" + content + ", photo=" + photo + ", id=" + id
				+ ", writeDate=" + writeDate + "]";
	}

}
