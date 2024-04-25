package dashboard_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.LoginPage_Dashboard;
import utilities.ConfigReader;
import utilities.DP;

public class LoginPage_Test extends BaseTest {
	
	ConfigReader config=new ConfigReader();
	
	LoginPage_Dashboard loginPage;
	
	@Test(dataProvider = "TestData",dataProviderClass = DP.class)
	public void loginTest(String userName, String password, String remarks) throws InterruptedException {
		
		 ExtentTest test=getExtentTest();
		 
		 loginPage = new LoginPage_Dashboard(driver);
		 
//		 boolean status=loginPage.redirectLoginPage();
//		 
//		 if(status==true) {
//			 
//			 test.log(LogStatus.PASS, "Successfully redirected to Login Page");
//			 
//		 } else {
//			 
//			 test.log(LogStatus.FAIL, "Failed to redirect to Login Page");
//			 
//		 }
		 
		test.log(LogStatus.INFO, "Input details - UserName : "+userName+" , Password : "+password+" , Remarks : "+remarks);

		boolean status1=loginPage.loginUser(userName, password);
		
		if(status1==true) {
			 
			 test.log(LogStatus.PASS, "Successfully filled Login Page details and clicked Login button");
			 
		 } else {
			 
			 test.log(LogStatus.FAIL, "Failed to fill Login Page details and click Login button");
			
			 Assert.assertTrue(false);
		 }
		 
		String path=loginPage.verifyLogin();
		
		if(path.isEmpty()||path.isBlank()) {
			 
			 test.log(LogStatus.PASS, "Successfully verified login credentials");
			 
		 } else {
			 		 
			 test.log(LogStatus.FAIL, test.addScreenCapture(path) + "Failed to Login - Invalid credentials");
			 
		 }
	}

}
