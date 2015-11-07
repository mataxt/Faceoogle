package tests;
import java.sql.Date;
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
	
	
//	public void testUser() {
//		try {
//			em.getTransaction().begin();
//			User usr = new User("Username", "Password", "Name", Date.valueOf("1990-01-01"), "Man");
//			
//			//User exists?
//			em.persist(usr);
//			assertTrue(em.contains(usr));
//		
//			//All values correct?
//			User result = em.createQuery("from User where username = ?1", User.class).setParameter(1, usr.getUsername()).getSingleResult();
//			assertEquals("Username", result.getUsername());
//			assertEquals("Password", result.getPassword());
//			assertEquals("Name", result.getName());
//			assertEquals(Date.valueOf("1990-01-01"), result.getBirthDate());
//			assertEquals("Man", result.getGender());
//			
//			//Can remove?
//			em.remove(usr);
//			assertFalse(em.contains(usr));
//			
//			em.getTransaction().commit();
//			
//		} catch (Exception e) {
//			fail("Exception during commit.");
//			em.getTransaction().rollback();
//		}
//	}
	
	public void testUserMethods() {
		try {
			User usr = new User("Username", "Password", "Name", Date.valueOf("1990-01-01"), "Man");
			Random rnd = new Random();
			//User exists?
			while (!UserDB.addUser(usr)) {
				usr.setUsername(usr.getUsername()+ rnd.nextInt());
			}
			assertTrue(UserDB.checkUser(usr));
			
			//All values correct?
			User result = em.createQuery("from User where username = ?1", User.class).setParameter(1, usr.getUsername()).getSingleResult();
			assertEquals(usr.getUsername(), result.getUsername());
			assertEquals(usr.getPassword(), result.getPassword());
			assertEquals(usr.getName(), result.getName());
			assertEquals(usr.getBirthDate(), result.getBirthDate());
			assertEquals(usr.getGender(), result.getGender());
			
			//Can remove?
			UserDB.removeUser(usr);
			assertFalse(UserDB.checkUser(usr));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception during user method commit.");
		}
	}
}
