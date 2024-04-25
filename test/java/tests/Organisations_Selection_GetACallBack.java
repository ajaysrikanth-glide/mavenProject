package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.ForOrganisations;
import utilities.DP;

public class Organisations_Selection_GetACallBack extends BaseTest {

	ForOrganisations organisations;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void org_Selection_GetACallBack(String name, String phone, String company, String message, String remarks)
			throws InterruptedException {

		ExtentTest test = getExtentTest();

		organisations = new ForOrganisations(driver);

		boolean status = organisations.verifySelection();
		
		if (status == true) {

			test.log(LogStatus.PASS, "Selection component verified");
			
		} else {

			test.log(LogStatus.FAIL, "Failed to verify Selection component");
		}

		boolean status1 = organisations.clickSelectionLearnMore();
		
		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Learn more under Selection component");
			
		} else {

			test.log(LogStatus.FAIL, "Failed to click Learn more under Selection component");
		}

		boolean status2 = organisations.clickGetCallBack();
		
		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Get a Call back button");
			
		} else {

			test.log(LogStatus.FAIL, "Failed to click and verify Get Call back button");
		}

		test.log(LogStatus.INFO, "Input details - Name : " + name + ", Phone : " + phone + ", Company Name : " + company
				+ ", Message : " + message + ", Remarks :" + remarks);

		boolean status3 = organisations.fillContactUs(name, phone, company, message);

		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully filled contact us details");

		} else {

			test.log(LogStatus.FAIL, "Failed to fill contact us details");
		}

		boolean status4 = organisations.submitContactUs();
		
		if (status4 == true) {

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
