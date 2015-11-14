package Tests.database;

import java.util.List;

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
			Log log = new Log("Writer", "Receiver", "Body");
			
			//Log created?
			log.setId(LogDB.addLog(log));
			assertFalse("Log could not be created", log.getId().equals(null));

			//All values correct?
			Log result = em.createQuery("from Log where id = ?1", Log.class).setParameter(1, log.getId()).getSingleResult();
			assertEquals("Database and Entity Id mismatch",log.getId(), result.getId());
			assertEquals("Wrong writer inserted",log.getWriter(), result.getWriter());
			assertEquals("Wrong receiver inserted ",log.getReceiver(), result.getReceiver());
			assertEquals("Wrong body inserted",log.getBody(), result.getBody());

			//Get the logs
			List<Log> logs = LogDB.listUserLogs(log.getReceiver());
			assertFalse("Fetching logs failed",logs.isEmpty());
			assertEquals("Fetching writer failed",log.getWriter(), logs.get(0).getWriter());
			assertEquals("Fetching receiver failed",log.getReceiver(), logs.get(0).getReceiver());
			assertEquals("Fetching body failed",log.getBody(), logs.get(0).getBody());
			
			//Can remove?
			LogDB.removeLog(log);
			assertTrue("Remove failed",LogDB.listUserLogs(log.getReceiver()).isEmpty());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception during log commit.");
		}
	}
}
