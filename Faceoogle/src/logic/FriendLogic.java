package logic;

import java.util.ArrayList;
import java.util.List;

import database.FriendDB;
import model.User;

public class FriendLogic {
	public static void addFriend(String userName, String friendName) {
		User user = new User(userName);
		User friend = new User(friendName);
		FriendDB.addFriend(user,friend);
	}
	
	public static void removeFriend(String userName, String friendName) {
		User user = new User(userName);
		User friend = new User(friendName);
		FriendDB.removeFriend(user,friend);
	}
	
	public static List<String> getFriends(String userName) {
		User user = new User(userName);
		List<String> friendNames = new ArrayList<String>();
		List<User> friendList = FriendDB.getFriends(user);
		for (User  friend : friendList) {
			friendNames.add(friend.getUsername()) ;
		}
		return friendNames;
	}
	
	public static String isFriend(String user, String friend) {
		if (user.equals(friend)) {
			return "OWN";
		} else if ((FriendDB.getFriends(new User(user)).toString()).contains(friend)) {
			return "KNOWN";
		} else {
			return "UNKNOWN";
		}
	}
}
