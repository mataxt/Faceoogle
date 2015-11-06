package logic;
import java.util.ArrayList;
import java.util.List;

import database.LogDB;
import model.Log;
import vm.LogViewModel;
public class Profile {

	public static List<LogViewModel> getLogs(String receiver){
		List<LogViewModel> lvm = new ArrayList<LogViewModel>();
		List<Log> original = LogDB.listUserLogs(receiver);
		for (Log log : original) {
			lvm.add(new LogViewModel(log));
		}
		return lvm;
		
	}
}
