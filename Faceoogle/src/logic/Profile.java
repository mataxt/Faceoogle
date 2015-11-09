package logic;

import java.util.ArrayList;
import java.util.List;

import database.FriendDB;
import database.LogDB;
import database.UserDB;
import model.Friend;
import model.Log;
import model.User;
import vm.LogViewModel;
import vm.UserViewModel;

public class Profile {

	public static List<LogViewModel> getLogs(String receiver) {
		List<LogViewModel> lvm = new ArrayList<LogViewModel>();
		List<Log> original = LogDB.listUserLogs(receiver);
		
		for (Log log : original) {
			log.setBody(log.getBody().replaceAll("(.{60})", "$1\n"));
			lvm.add(new LogViewModel(log));
		}
		return lvm;
	}

	public static List<LogViewModel> getFeed(String username) {
		User usr = new User();
		usr.setUsername(username);
		List<LogViewModel> lvm = new ArrayList<LogViewModel>();
		List<Log> original = LogDB.listUserFeed(usr);
		
		for (Log log : original) {
			log.setBody(log.getBody().replaceAll("(.{60})", "$1\n"));
			lvm.add(new LogViewModel(log));
		}
		return lvm;
	}
	
	public static void writeLog(String username, String receiver, String messageLogs) {
		Log log = new Log(username, receiver, messageLogs);
		LogDB.addLog(log);
	}

	public static UserViewModel getUserInfo(String user) {
		List<User> usrInfo = UserDB.findByUserName(user);
		UserViewModel vm = null;
		if (!usrInfo.isEmpty()) {
			vm = new UserViewModel(UserDB.findByUserName(user).get(0));
		}
		return vm;
	}

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
