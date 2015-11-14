package Tests.logic;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import database.FriendDB;
import database.LogDB;
import database.UserDB;
import junit.framework.TestCase;
import logic.LogLogic;
import model.Log;
import model.User;
import vm.LogViewModel;

public class LogTests extends TestCase {
	// Add dummies for the purpose of this test
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

	public void testLogMethods() {
		String messageLogs = "Body";

		Random rnd = new Random();

		while (!UserDB.addUser(user) || !UserDB.addUser(friend)) {
			user.setUsername(user.getUsername() + rnd.nextInt());
			friend.setUsername(friend.getUsername() + rnd.nextInt());
		}
		
		//Make users friends for the purpose of this test
		FriendDB.addFriend(user, friend);
		
		Integer id = LogLogic.writeLog(user.getUsername(), friend.getUsername(), messageLogs);

		// Write to log?
		assertFalse("Could not write to log", id.equals(null));

		// Get logs (from profile page)?
		List<LogViewModel> logList = LogLogic.getLogs(friend.getUsername());
		assertFalse("Log not listed", logList.isEmpty());
		assertEquals("Writer not correct", user.getUsername(), logList.get(0).getWriter());
		assertEquals("Receiver not correct", friend.getUsername(), logList.get(0).getReceiver());
		assertEquals("Message (body) not correct", messageLogs, logList.get(0).getBody());

		// Get logs (from profile page)?
		List<LogViewModel> feedList = LogLogic.getFeed(user.getUsername());
		assertFalse("feed not listed", feedList.isEmpty());
		assertEquals("Writer not correct", user.getUsername(), feedList.get(0).getWriter());
		assertEquals("Receiver not correct", friend.getUsername(), feedList.get(0).getReceiver());
		assertEquals("Message (body) not correct", messageLogs, feedList.get(0).getBody());

		// Remove test log
		Log log = new Log(user.getUsername(), friend.getUsername(), messageLogs);
		log.setId(id);
		LogDB.removeLog(log);
		

	}
}
