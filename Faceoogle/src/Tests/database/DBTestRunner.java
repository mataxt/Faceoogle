package Tests.database;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class DBTestRunner {
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(UserTests.class,LogTests.class,ChatTests.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println("Database tests success : " + result.wasSuccessful());
	}
}
