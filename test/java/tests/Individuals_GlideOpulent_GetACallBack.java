package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.ForIndividuals;
import utilities.DP;

public class Individuals_GlideOpulent_GetACallBack extends BaseTest {

	ForIndividuals individuals;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void ind_GlideOpulent_GetACallBack(String name, String phone, String email, String message, String remarks)
			throws InterruptedException {

		individuals = new ForIndividuals(driver);

		ExtentTest test = getExtentTest();
		
		boolean status = individuals.clickForIndividualsTab();

		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked For Individuals tab");

		} else {

			test.log(LogStatus.FAIL, "Failed to click For Individuals tab");
		}

		boolean status1 = individuals.clickGlideOpulentAssuranceLearnMore();

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Learn more under GlideOpulent Assurance");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Learn more under GlideOpulent Assurance");
		}

		boolean status2 = individuals.clickGetCallBack();

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Get A Callback button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Get A Callback button");
		}

		test.log(LogStatus.INFO, "Input details - Name : " + name + ", Phone : " + phone + ", Email : " + email
				+ ", Message : " + message + ", Remarks :" + remarks);

		boolean status3 = individuals.fillGetACallBack(name, phone, email, message);

		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully filled the contact us details");
		} else {

			test.log(LogStatus.FAIL, "Failed to fill the contact us details");
		}

		boolean status4 = individuals.submitGetACallBack();

		if (status4 == true) {

			test.log(LogStatus.PASS, "Successfully clicked the submit button");
		} else {

			test.log(LogStatus.FAIL, "Failed to click the submit button");
		}

		String path = individuals.verifySuccessful();

		if (path.isBlank() || path.isEmpty()) {

			test.log(LogStatus.PASS, "Successfully submitted the details");

		} else {

			test.log(LogStatus.PASS, test.addScreenCapture(path) + "Failed to submit the details");

		}
	}

}
