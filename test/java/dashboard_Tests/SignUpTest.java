package dashboard_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.LoginPage_Dashboard;
import pages.SignUpPage;
import utilities.ConfigReader;
import utilities.DP;

public class SignUpTest extends BaseTest {

	ConfigReader config = new ConfigReader();

	LoginPage_Dashboard loginPage;

	SignUpPage signUpPage;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void signUpTest(String userName, String email, String password, String remarks) {

		ExtentTest test = getExtentTest();

		loginPage = new LoginPage_Dashboard(driver);

		signUpPage = new SignUpPage(driver);
		
//		loginPage.redirectLoginPage();

		boolean status = loginPage.clickSignUpLink();

		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked Sign up link and redirected to Sign Up Page");

		} else {

			test.log(LogStatus.FAIL, "Failed to click and redirect to Sign Up Page");

		}
		
		test.log(LogStatus.INFO, "Input details - UserName : "+userName+" , Email : "+email+" , Password : "+password+" , Remarks : "+remarks);

		boolean status1 = signUpPage.SignUpUser(userName, email, password);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully filled Sign up Page details and clicked Sign Up button");

		} else {

			test.log(LogStatus.FAIL, "Failed to fill Sign up Page details and click Sign Up button");

			Assert.assertTrue(false);
		}

		String path = signUpPage.verifySignUp();

		if (path.isBlank()||path.isEmpty()) {

			test.log(LogStatus.PASS, "Successfully verified Sign up credentials");

		} else {

			 test.log(LogStatus.FAIL, test.addScreenCapture(path) + "Failed to Sign Up - Invalid credentials");

		}
	}

}
