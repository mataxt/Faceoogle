package Tests.logic;

import java.sql.Date;
import java.util.Random;

import database.UserDB;
import junit.framework.TestCase;
import logic.FriendLogic;
import model.User;

public class FriendTests extends TestCase {
	//Dummy users
	User user = new User("Username", "Password", "Name", Date.valueOf("1990-01-01"), "Man");
	User friend = new User("Friend", "Password", "Name", Date.valueOf("1990-01-01"), "Man");
	
	@Override
	public void setUp() {
		try {
			super.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {
		try {
			//Remove dummies
			UserDB.removeUser(user);
			UserDB.removeUser(friend);
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testFriendMethods() {
		Random rnd = new Random();
		
		// User exists?
		while (!UserDB.addUser(user) || !UserDB.addUser(friend)) {
			user.setUsername(user.getUsername() + rnd.nextInt());
			friend.setUsername(friend.getUsername() + rnd.nextInt());
		}
		
		//can add friend?
		assertTrue("Add friend failed", FriendLogic.addFriend(user.getUsername(), friend.getUsername()));
				
		//can get friends?
		assertEquals("Fetching friends failed", friend.getUsername(),FriendLogic.getFriends(user.getUsername()).get(0));
		
		//can recognise friend?
		assertEquals("Recognistion failed", "KNOWN",FriendLogic.isFriend(user.getUsername(), friend.getUsername()));
		
		//can remove friend?
		assertTrue("Remove friend failed", FriendLogic.removeFriend(user.getUsername(), friend.getUsername()));
	}
}
