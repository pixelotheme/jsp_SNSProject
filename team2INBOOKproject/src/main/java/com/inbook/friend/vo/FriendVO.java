package com.inbook.friend.vo;

import java.util.Objects;

public class FriendVO {

	private long no;
	private String hostId, friendId, id, name, email, tel, photo;
	private int relationship;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getHostId() {
		return hostId;
	}
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getRelationship() {
		return relationship;
	}
	public void setRelationship(int relationship) {
		this.relationship = relationship;
	}
	@Override
	public String toString() {
		return "FriendVO [no=" + no + ", hostId=" + hostId + ", friendId=" + friendId + ", id=" + id + ", name=" + name
				+ ", email=" + email + ", tel=" + tel + ", photo=" + photo + ", relationship=" + relationship + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, friendId, hostId, id, name, no, photo, relationship, tel);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendVO other = (FriendVO) obj;
		return Objects.equals(email, other.email) && Objects.equals(friendId, other.friendId)
				&& Objects.equals(hostId, other.hostId) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && no == other.no && Objects.equals(photo, other.photo)
				&& relationship == other.relationship && Objects.equals(tel, other.tel);
	}
	
	

	
	
	
	
}
