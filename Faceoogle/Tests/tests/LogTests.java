package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import database.LogDB;
import junit.framework.TestCase;
import model.Log;


public class LogTests extends TestCase{

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
	
	
	public void testLog() {
		try {
			Log log = new Log("Writer","Receiver","Body");
			
			//Log created?
			log.setId(LogDB.addLog(log));
			
			//All values correct?
			Log result = em.createQuery("from Log where id = ?1", Log.class).setParameter(1, log.getId()).getSingleResult();
			assertEquals(log.getId(), result.getId());
			assertEquals(log.getWriter(), result.getWriter());
			assertEquals(log.getReceiver(), result.getReceiver());
			assertEquals(log.getBody(), result.getBody());
			assertEquals(log.getTimesamp(), result.getTimesamp());
			
			//Can remove?
			LogDB.removeLog(log);

			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception during commit.");
		}
	}
}
