package logic;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<String> getFriends(String user) {
		Friend frd = new Friend();
		frd.setuser(user);
		List<String> friendNames = new ArrayList<String>();
		List<Friend> friendList = FriendDB.getFriends(user);
		for (Friend  friend : friendList) {
			friendNames.add(friend.getfriend()) ;
		}
		return friendNames;
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
