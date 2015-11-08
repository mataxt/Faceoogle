package logic;

import java.util.ArrayList;
import java.util.List;

import database.FriendDB;
import database.LogDB;
import database.UserDB;
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

	public static boolean isFriend(String user, String friend) {
		return FriendDB.getFriends(user).contains(friend);
	}
}
