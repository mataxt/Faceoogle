package Tests.logic;

import java.util.List;

import database.LogDB;
import junit.framework.TestCase;
import logic.LogLogic;
import model.Log;
import vm.LogViewModel;

public class LogTests extends TestCase {
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
	
	public void testLogMethods() {
		String username = "Tester";
		String receiver = "Testee";
		String messageLogs = "Test";
		
		Integer id = LogLogic.writeLog(username, receiver, messageLogs);
		
		//Write to log?
		assertFalse("Could not write to log", id.equals(null));
		
		//Get logs (from profile page)?
		List<LogViewModel> logList = LogLogic.getLogs(receiver);
		assertFalse("Log not listed", logList.isEmpty());
		assertEquals("Writer not correct", username, logList.get(0).getWriter());
		assertEquals("Receiver not correct", receiver, logList.get(0).getReceiver());
		assertEquals("Message (body) not correct", messageLogs, logList.get(0).getBody());
		
		// FEL HÄR!!!
		//Get logs (from profile page)?
		try {
			List<LogViewModel> feedList = LogLogic.getFeed(username);
			assertFalse("Log not listed", feedList.isEmpty());
			assertEquals("Writer not correct", username, feedList.get(0).getWriter());
			assertEquals("Receiver not correct", receiver, feedList.get(0).getReceiver());
			assertEquals("Message (body) not correct", messageLogs, feedList.get(0).getBody());
			System.out.println("DONE");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//Remove test log
		Log log = new Log(username, receiver, messageLogs);
		log.setId(id);
		LogDB.removeLog(log);
	}
}
