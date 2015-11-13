package Tests.database;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import database.FriendDB;
import database.UserDB;
import junit.framework.TestCase;
import model.User;

public class UserTests extends TestCase {
	EntityManagerFactory emf;
	EntityManager em;

	@Override
	public void setUp() {
		try {
			super.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			emf = Persistence.createEntityManagerFactory("UserPU");
			em = emf.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Exception during JPA EntityManager instanciation.");
		}
	}

	@Override
	public void tearDown() {
		try {
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (em != null) {
			em.close();
		}
		if (emf != null) {
			emf.close();
		}
	}

	public void testUserMethods() {
		try {

			User user = new User("Username", "Password", "Name", Date.valueOf("1990-01-01"), "Man");
			User friend = new User("Friend", "Password", "Name", Date.valueOf("1990-01-01"), "Man");

			Random rnd = new Random();
			
			// User exists?
			while (!UserDB.addUser(user) || !UserDB.addUser(friend)) {
				user.setUsername(user.getUsername() + rnd.nextInt());
				friend.setUsername(friend.getUsername() + rnd.nextInt());
			}
			assertTrue("User did not insert", UserDB.checkUser(user));

			// All values correct?
			User result = em.createQuery("from User where username = ?1", User.class)
					.setParameter(1, user.getUsername()).getSingleResult();
			assertEquals("Wrong username inserted", user.getUsername(), result.getUsername());
			assertEquals("Wrong password inserted", user.getPassword(), result.getPassword());
			assertEquals("Wrong name inserted", user.getName(), result.getName());
			assertEquals("Wrong birthdate inserted", user.getBirthDate(), result.getBirthDate());
			assertEquals("Wrong gender inserted", user.getGender(), result.getGender());

			// Can Search?
			List<User> users = UserDB.searchUserName(user.getUsername());
			assertFalse("Fetching users failed", users.isEmpty());
			assertTrue("Search Failed ", users.contains(user));

			// Can add friends?
			FriendDB.addFriend(user, friend);
			assertTrue("Add friend failed", FriendDB.getFriends(user).contains(friend));
			
			// Can get friends?
			assertFalse("Fetching friendlist failed", FriendDB.getFriends(user).isEmpty());
			assertTrue("Fetching wrong friends", FriendDB.getFriends(user).contains(friend));

			// Can remove friends?
			FriendDB.removeFriend(user, friend);
			assertTrue("Fetching friendlist failed", FriendDB.getFriends(user).isEmpty());
			assertFalse("Remove friend failed", FriendDB.getFriends(user).contains(friend));

			// Can remove?
			UserDB.removeUser(user);
			assertFalse("User did not remove", UserDB.checkUser(user));
			UserDB.removeUser(friend);
			assertFalse("Friend did not remove", UserDB.checkUser(friend));

		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception during user method commit.");
		}
	}
}
