//package model;
//
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "friends", catalog = "faceoogle")
//public class Friend {
//	private String userId;
//	private String friendId;
//
//	public Friend() {
//	}
//
//	public Friend(String userId, String friendId) {
//		this.userId = userId;
//		this.friendId = friendId;
//	}
//	
//	@OneToMany(mappedBy="myFriends")
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getFriendId() {
//		return friendId;
//	}
//
//	public void setFriendId(String friendId) {
//		this.friendId = friendId;
//	}
//}
