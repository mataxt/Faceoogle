package logic;

import java.util.ArrayList;
import java.util.List;

import database.FriendDB;
import model.User;

public class FriendLogic {
	public static boolean addFriend(String userName, String friendName) {
		User user = new User(userName);
		User friend = new User(friendName);
		return FriendDB.addFriend(user,friend);
	}
	
	public static boolean removeFriend(String userName, String friendName) {
		User user = new User(userName);
		User friend = new User(friendName);
		return FriendDB.removeFriend(user,friend);
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
	/**
	 * 
	 * @param user
	 * @param friend
	 * @return String "OWN" if the user, "KNOWN" if he is a friend, "UNKNOWN" if he is not a friend.
	 */
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
