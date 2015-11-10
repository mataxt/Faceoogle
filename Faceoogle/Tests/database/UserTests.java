package database;
import java.sql.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
			User usr = new User("Username", "Password", "Name", Date.valueOf("1990-01-01"), "Man");
			Random rnd = new Random();
			//User exists?
			while (!UserDB.addUser(usr)) {
				usr.setUsername(usr.getUsername()+ rnd.nextInt());
			}
			assertTrue("User did not insert",UserDB.checkUser(usr));
			
			//All values correct?
			User result = em.createQuery("from User where username = ?1", User.class).setParameter(1, usr.getUsername()).getSingleResult();
			assertEquals("Wrong username inserted",usr.getUsername(), result.getUsername());
			assertEquals("Wrong password inserted",usr.getPassword(), result.getPassword());
			assertEquals("Wrong name inserted",usr.getName(), result.getName());
			assertEquals("Wrong birthdate inserted",usr.getBirthDate(), result.getBirthDate());
			assertEquals("Wrong gender inserted",usr.getGender(), result.getGender());
			
			//Can Search?
			List<User> users = UserDB.searchUserName(usr.getUsername());
			assertFalse(users.isEmpty());
			assertTrue("Search Failed ",users.contains(usr));
			
			//Can remove?
			UserDB.removeUser(usr);
			assertFalse("User did not remove",UserDB.checkUser(usr));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception during user method commit.");
		}
	}
}
