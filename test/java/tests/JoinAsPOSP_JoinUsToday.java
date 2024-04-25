package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import base.BrowserSetup;
import pages.JoinAsPOSP;
import utilities.ConfigReader;
import utilities.DP;
import utilities.ThreadLocalExtentTest;

public class JoinAsPOSP_JoinUsToday extends BaseTest {

	ConfigReader config = new ConfigReader();

	JoinAsPOSP joinPOSP;

	@Test()
	public void joinAsPOSP_JoinUsToday()
			throws InterruptedException {
		
		joinPOSP = new JoinAsPOSP(driver);

		ExtentTest test = getExtentTest();		

		boolean status = joinPOSP.clickJoinAsPOSPTab();

		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked Join As POSP tab");			

		} else {

			test.log(LogStatus.FAIL, "Failed to click Join As POSP tab");
			
			Assert.assertTrue(false);

		}

		boolean status1 = joinPOSP.clickJoinAsPOSP();

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Join As POSP and redirect to Join As POSP link");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Join As POSP button and redirect to Join As POSP link");
			
			Assert.assertTrue(false);
		}

		boolean status2 = joinPOSP.verifySuccessful();

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully redirected to Join As POSP link");			

		} else {

			test.log(LogStatus.FAIL, "Failed to redirect to Join As POSP link");

			Assert.assertTrue(false);
		}

		


	}
}
