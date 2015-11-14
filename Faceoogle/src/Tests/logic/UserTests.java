package Tests.logic;

import java.sql.Date;

import junit.framework.TestCase;
import logic.UserLogic;

public class UserTests extends TestCase {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void testUserMethods() {
		String username = "Username";
		String password = "123456";
		String name = "Test Test";
		Date birthdate = new Date(2000, 01, 01);
		String gender = "Male";
		
		//Add new user
		assertFalse("Add new user failed", UserLogic.addUser(username, password, name, birthdate, gender));
	}
}