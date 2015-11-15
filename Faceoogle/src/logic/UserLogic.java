package logic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import database.UserDB;
import model.User;
import vm.UserViewModel;

public class UserLogic {
	public static boolean login(String username, String password) {
		User usr = new User(username, password);
		return UserDB.checkUser(usr);
	}

	public static boolean addUser(String username, String password, String name, Date birthdate, String gender) {
		User usr = new User(username, password, name, birthdate, gender);
		return UserDB.addUser(usr);
	}

	public static ArrayList<String> getUserNames(String name) {
		ArrayList<String> names = new ArrayList<String>();
		List<User> users = UserDB.searchUserName(name);
		for (User user : users) {
			names.add(user.getUsername());
		}
		return names;
	}

	public static UserViewModel getUserInfo(String user) {
		List<User> usrInfo = UserDB.searchUserName(user);
		UserViewModel vm = null;
		if (!usrInfo.isEmpty()) {
			vm = new UserViewModel(UserDB.searchUserName(user).get(0));
		}
		return vm;
	}
}
