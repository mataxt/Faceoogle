package Tests.logic;

import java.sql.Date;

import database.UserDB;
import junit.framework.TestCase;
import logic.UserLogic;
import model.User;
import vm.UserViewModel;

public class UserTests extends TestCase {
	String username = "Username";
	String password = "123456";
	String name = "Test Test";
	Date birthdate = Date.valueOf("1990-10-10");
	String gender = "Male";

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
			super.tearDown();
			//remove dummy user
			UserDB.removeUser(new User(username));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testUserMethods() {

		// Add new user
		assertTrue("Add new user failed", UserLogic.addUser(username, password, name, birthdate, gender));

		// Login
		assertTrue("Login failed", UserLogic.login(username, password));

		// Can search?
		assertEquals("Search failed", username, UserLogic.getUserNames(username).get(0));

		// Can get info?
		UserViewModel uvm = UserLogic.getUserInfo(username);
		assertEquals(username, uvm.getUsername());
		assertEquals(name, uvm.getName());
		assertEquals(birthdate, uvm.getBirthDate());
		assertEquals(gender, uvm.getGender());
		
		
	}
}