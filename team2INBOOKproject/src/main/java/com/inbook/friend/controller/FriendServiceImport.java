package com.inbook.friend.controller;

import com.inbook.friend.service.FriendAllUserListService;
import com.inbook.friend.service.FriendDeleteService;
import com.inbook.friend.service.FriendIdListService;
import com.inbook.friend.service.FriendListService;
import com.inbook.friend.service.FriendRequestAcceptService;
import com.inbook.friend.service.FriendRequestCancelService;
import com.inbook.friend.service.FriendRequestIdListService;
import com.inbook.friend.service.FriendRequestIdService;
import com.inbook.friend.service.FriendRequestRefuseService;
import com.inbook.friend.service.FriendRequestSendIdService;
import com.inbook.friend.service.FriendRequestSendService;
import com.inbook.friend.service.FriendSearchService;
import com.inbook.friend.service.FriendSuggestionsService;

public class FriendServiceImport {
	private FriendListService friendListService;
	private FriendDeleteService friendDeleteService;
	private FriendSearchService friendSearchService;
	private FriendIdListService friendIdListService;
	private FriendRequestIdListService friendRequestIdListService;
	private FriendRequestAcceptService friendRequestAcceptService;
	private FriendRequestRefuseService friendRequestRefuseService;
	private FriendRequestSendIdService friendRequestSendIdService;
	private FriendRequestIdService friendRequestIdService;
	private FriendRequestSendService friendRequestSendService;
	private FriendRequestCancelService friendRequestCancelService;
	private FriendSuggestionsService friendSuggestionsService;
	private FriendAllUserListService friendAllUserListService;
	private ButtonProcess buttonProcess;
	//댓글
	
	public void setButtonProcess(ButtonProcess buttonProcess) {
		this.buttonProcess = buttonProcess;
	}
	public void setFriendListService(FriendListService friendListService) {
		this.friendListService = friendListService;
	}


	public void setFriendDeleteService(FriendDeleteService friendDeleteService) {
		this.friendDeleteService = friendDeleteService;
	}

	public void setFriendSearchService(FriendSearchService friendSearchService) {
		this.friendSearchService = friendSearchService;
	}


	public void setFriendIdListService(FriendIdListService friendIdListService) {
		this.friendIdListService = friendIdListService;
	}

	
	public void setFriendRequestSendService(FriendRequestSendService friendRequestSendService) {
		this.friendRequestSendService = friendRequestSendService;
	}

	public void setFriendRequestIdListService(FriendRequestIdListService friendRequestIdListService) {
		this.friendRequestIdListService = friendRequestIdListService;
	}
	
	public void setFriendRequestAcceptService(FriendRequestAcceptService friendRequestAcceptService) {
		this.friendRequestAcceptService = friendRequestAcceptService;
	}

	
	public void setFriendRequestRefuseService(FriendRequestRefuseService friendRequestRefuseService) {
		this.friendRequestRefuseService = friendRequestRefuseService;
	}

	
	public void setFriendRequestSendIdService(FriendRequestSendIdService friendRequestSendIdService) {
		this.friendRequestSendIdService = friendRequestSendIdService;
	}

	public void setFriendRequestIdService(FriendRequestIdService friendRequestIdService) {
		this.friendRequestIdService = friendRequestIdService;
	}

	public void setFriendRequestCancelService(FriendRequestCancelService friendRequestCancelService) {
		this.friendRequestCancelService = friendRequestCancelService;
	}
	

	public void setFriendSuggestionsService(FriendSuggestionsService friendSuggestionsService) {
		this.friendSuggestionsService = friendSuggestionsService;
	}
	
	public void setFriendAllUserListService(FriendAllUserListService friendAllUserListService) {
		this.friendAllUserListService = friendAllUserListService;
	}
	
	
	public FriendAllUserListService getFriendAllUserListService() {
		return friendAllUserListService;
	}
	public FriendListService getFriendListService() {
		return friendListService;
	}
	public FriendDeleteService getFriendDeleteService() {
		return friendDeleteService;
	}
	public FriendSearchService getFriendSearchService() {
		return friendSearchService;
	}
	public  FriendIdListService getFriendIdListService() {
		return friendIdListService;
	}
	public FriendRequestIdListService getFriendRequestIdListService() {
		return friendRequestIdListService;
	}
	public FriendRequestAcceptService getFriendRequestAcceptService() {
		return friendRequestAcceptService;
	}
	public FriendRequestRefuseService getFriendRequestRefuseService() {
		return friendRequestRefuseService;
	}
	public FriendRequestSendIdService getFriendRequestSendIdService() {
		return friendRequestSendIdService;
	}
	public FriendRequestIdService getFriendRequestIdService() {
		return friendRequestIdService;
	}
	public FriendRequestSendService getFriendRequestSendService() {
		return friendRequestSendService;
	}
	public FriendRequestCancelService getFriendRequestCancelService() {
		return friendRequestCancelService;
	}
	public ButtonProcess getButtonProcess() {
		return buttonProcess;
	}
	
	public FriendSuggestionsService getFriendSuggestionsService() {
		return friendSuggestionsService;
	}
}
