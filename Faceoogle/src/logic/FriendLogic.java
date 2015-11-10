package logic;

import database.FriendDB;
import model.Friend;

public class FriendLogic {
	public static void addFriend(String user, String friend) {
		Friend frd = new Friend(user, friend);
		
		FriendDB.addFriend(frd);
	}
	
	public static void removeFriend(String user, String friend) {
		Friend frd = new Friend(user, friend);
		
		FriendDB.removeFriend(frd);
	}
	
	public static String isFriend(String user, String friend) {
		if (user.equals(friend)) {
			return "OWN";
		} else if ((FriendDB.getFriends(user).toString()).contains(friend)) {
			return "KNOWN";
		} else {
			return "UNKNOWN";
		}
	}
}
