 package utilities;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ThreadLocalExtentTest {

	private static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	 
	public static ExtentTest getTest() {
	return extentTest.get();
	}
	 
	public static void setTest(ExtentTest test) {
	extentTest.set(test);
	}
	 
	public static void log(LogStatus status, String message) {
	extentTest.get().log(status, message);
	}
}
