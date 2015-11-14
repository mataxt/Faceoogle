package Tests.logic;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class LogicTestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(ChatTests.class,LogTests.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		System.out.println("Logic tests success : " + result.wasSuccessful());
	}
}