package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.HomePage;
import utilities.ConfigReader;
import utilities.DP;

public class HomePage_GetACallBack extends BaseTest {

	ConfigReader config = new ConfigReader();

	HomePage homePage;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class, description = "HomePage_GetACallBack")
	public void homePage_GetACallBack(String name, String phone, String email, String message, String remarks)
			throws InterruptedException {

		ExtentTest test = getExtentTest();

		homePage = new HomePage(driver);

		homePage.assertHomePageTitle();

		test.log(LogStatus.PASS, "Successfully asserted Page title");

		homePage.clickGetCallBack();

		test.log(LogStatus.PASS, "Get A CallBack clicked");

		test.log(LogStatus.INFO, "Input Details - Name : " + name + ", Phone : " + phone + ", Email : " + email
				+ ", Message :" + message + ", Remarks :" + remarks);

		boolean status = homePage.fillGetACallBack(name, phone, email, message);

		if (status == false) {

			test.log(LogStatus.FAIL, "Failed to fill Call back details");

			Assert.assertTrue(false);

		} else {

			test.log(LogStatus.PASS, "Successfully filled Call back details");

		}

		boolean status2 = homePage.submitGetACallBack();

		if (status2 == false) {

			test.log(LogStatus.FAIL, "Failed to click Submit button");

			Assert.assertTrue(false);

		} else {

			test.log(LogStatus.PASS, "Successfully clicked Submit button");
		}

		String path = homePage.verifySuccessful();

		if (path.isBlank() || path.isEmpty()) {

			test.log(LogStatus.PASS, "Successfully submitted the details");

		} else {

			test.log(LogStatus.PASS, test.addScreenCapture(path) + "Failed to submit the details");

		}
	}
}
