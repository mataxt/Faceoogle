package logic;

import database.UserDB;
import model.User;

public class Login {

	public static boolean login(String username, String password) {
		User usr = new User(username, password);
		return UserDB.checkUser(usr);
	}

}
