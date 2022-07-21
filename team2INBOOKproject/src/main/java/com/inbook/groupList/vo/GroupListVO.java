package com.inbook.groupList.vo;

public class GroupListVO {
	
	private long no;
	private String title, content, id, writeDate, hashtag, fileName, photo, myLiked ;
	private long hit, likeCnt;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	public String getMyLiked() {
		return myLiked;
	}
	public void setMyLiked(String myLiked) {
		this.myLiked = myLiked;
	}
	public long getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(long likeCnt) {
		this.likeCnt = likeCnt;
	}
	@Override
	public String toString() {
		return "GroupListVO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", writeDate="
				+ writeDate + ", hashtag=" + hashtag + ", fileName=" + fileName + ", photo=" + photo + ", myLiked="
				+ myLiked + ", hit=" + hit + ", likeCnt=" + likeCnt + "]";
	}
	
	
	
	
	}
	
	

