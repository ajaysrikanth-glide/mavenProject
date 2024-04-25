package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.ForIndividuals;
import utilities.DP;

public class Individuals_Policy_TalkToUs extends BaseTest{

	ForIndividuals individuals;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void individuals_Policy_TalkToUs(String category, String policyName, String assertPolicyName,String name, String phone, String email, String message, String remarks)
			throws InterruptedException {

		individuals = new ForIndividuals(driver);

		ExtentTest test = getExtentTest();
		
		boolean status = individuals.clickForIndividualsTab();

		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked For Individuals tab");

		} else {

			test.log(LogStatus.FAIL, "Failed to click For Individuals tab");
		}

		boolean status1 = individuals.clickCategory(category);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked : "+category);

		} else {

			test.log(LogStatus.FAIL, "Failed to click : "+category);
		}
		
		boolean status2 = individuals.selectAndClickPolicyReadMore(policyName);

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Read More of : "+policyName);

		} else {

			test.log(LogStatus.FAIL, "Failed to click Read More of : "+policyName);
		}
		
		boolean status3 = individuals.verifyAndClickCallBack(assertPolicyName);

		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully verified and clicked Get A Callback button : "+policyName);

		} else {

			test.log(LogStatus.FAIL, "Failed to verify and click Get A Callback button : "+policyName);
		}

		test.log(LogStatus.INFO, "Input details - Category : " + category + ", Policy Name : " + policyName +", Name : " + name + ", Phone : " + phone + ", Email : " + email
				+ ", Message : " + message + ", Remarks :" + remarks);

		boolean status4 = individuals.fillGetACallBack(name, phone, email, message);

		if (status4 == true) {

			test.log(LogStatus.PASS, "Successfully filled the contact us details");
		} else {

			test.log(LogStatus.FAIL, "Failed to fill the contact us details");
		}

		boolean status5 = individuals.submitGetACallBack();

		if (status5 == true) {

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
