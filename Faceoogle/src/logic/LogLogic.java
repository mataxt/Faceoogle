package logic;

import java.util.ArrayList;
import java.util.List;

import database.LogDB;
import model.Log;
import model.User;
import vm.LogViewModel;

public class LogLogic {
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
	
	public static Integer writeLog(String username, String receiver, String messageLogs) {
		Log log = new Log(username, receiver, messageLogs);
		return LogDB.addLog(log);
	}
}
