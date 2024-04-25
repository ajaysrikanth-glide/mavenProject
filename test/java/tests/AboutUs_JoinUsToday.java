package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.AboutUs;
import utilities.ConfigReader;
import utilities.DP;

public class AboutUs_JoinUsToday extends BaseTest {

	ConfigReader config = new ConfigReader();

	AboutUs aboutUs;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class, description = "AboutUs_JoinUsToday")
	public void aboutUs_JoinUsToday(String name, String phone, String email, String message, String remarks)
			throws InterruptedException {

		ExtentTest test = getExtentTest();

		aboutUs = new AboutUs(driver);

		aboutUs.clickAboutUsTab();
		
		test.log(LogStatus.PASS, "About us tab clicked");

		aboutUs.clickGetCallBack();
		
		test.log(LogStatus.PASS, "Get A CallBack clicked");

		test.log(LogStatus.INFO, "Input Details - Name : " + name + ", Phone : " + phone + ", Email : " + email
				+ ", Message :" + message + ", Remarks :" + remarks);

		boolean status2 = aboutUs.fillJoinUsToday(name, phone, email, message);
		
		if (status2 == false) {

			test.log(LogStatus.FAIL, "Failed to fill Call back details");

			Assert.assertTrue(false);
			
		} else {
			
			test.log(LogStatus.PASS, "Filled Call back details");
			
		}

		boolean status3 = aboutUs.submitJoinUsToday();
		
		if (status3 == false) {

			test.log(LogStatus.FAIL, "Failed to click Submit button");

			Assert.assertTrue(false);

		} else {

			test.log(LogStatus.PASS, "Successfully clicked Submit button");
		}

		String path = aboutUs.verifySuccessful();

		if (path.isBlank() || path.isEmpty()) {

			test.log(LogStatus.PASS, "Successfully submitted the details");

		} else {

			test.log(LogStatus.PASS, test.addScreenCapture(path) + "Failed to submit the details");

		}

	}

}
