package logic;

import javax.persistence.NoResultException;

import database.UserDB;
import model.User;

public class Login {

	public static boolean login(String username, String password) {
		User usr = new User(username, password);
		try{
			return UserDB.checkUser(usr);
		} catch(NoResultException e){
			return false;
		}
	}

}
