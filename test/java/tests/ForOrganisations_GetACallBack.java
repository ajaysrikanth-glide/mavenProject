package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.ForOrganisations;
import utilities.ConfigReader;
import utilities.DP;

public class ForOrganisations_GetACallBack extends BaseTest {

	ConfigReader config = new ConfigReader();

	ForOrganisations organisations;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void forOrganisations_GetACallBack(String name, String phone, String company, String message, String remarks)
			throws InterruptedException {

		ExtentTest test = getExtentTest();

		organisations = new ForOrganisations(driver);

		boolean status = organisations.clickGetCallBack();

		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked Get a Call back button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click and verify Get Call back button");
		}

		test.log(LogStatus.INFO, "Input details - Name : " + name + ", Phone : " + phone + ", Company Name : " + company
				+ ", Message : " + message + ", Remarks :" + remarks);

		boolean status1 = organisations.fillContactUs(name, phone, company, message);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully filled contact us details");

		} else {

			test.log(LogStatus.FAIL, "Failed to fill contact us details");
		}

		boolean status2 = organisations.submitContactUs();

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked the submit button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click the submit buttton");
		}

		String path = organisations.verifySuccessful();

		if (path.isBlank() || path.isEmpty()) {

			test.log(LogStatus.PASS, "Successfully submitted the details");

		} else {

			test.log(LogStatus.PASS, test.addScreenCapture(path) + "Failed to submit the details");

		}

	}

}
