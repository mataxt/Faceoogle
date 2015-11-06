package logic;

import java.sql.Date;

import database.UserDB;
import model.User;

public class Register {

	public static void addUser(String username, String password, String name, Date birthdate, String gender) {
		User usr = new User(username, password, name, birthdate, gender);
		UserDB.addUser(usr);
	}

}
