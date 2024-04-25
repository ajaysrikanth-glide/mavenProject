package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.ForOrganisations;
import pages.Organisation_Industries;
import utilities.ConfigReader;

public class Industry_Film extends BaseTest {

	ConfigReader config = new ConfigReader();

	ForOrganisations organisations;

	Organisation_Industries industries;

	@Test()
	public void industry_Film(String policyName, String assertPolicyName, String name, String phone, String company,
			String message) throws InterruptedException {

			ExtentTest test = getExtentTest();

			organisations = new ForOrganisations(driver);

			industries = new Organisation_Industries(driver);

			boolean status = organisations.clickMedia();

			if (status == true) {

				test.log(LogStatus.PASS, "Successfully clicked Film, Media & OTT Industry");

			} else {

				test.log(LogStatus.FAIL, "Failed to click Film, Media & OTT Industry");
				
				Assert.assertTrue(false);
			}

			test.log(LogStatus.PASS, "Successfully navigated to Film, Media & OTT Industry");

			boolean status1 = industries.clickPolicy(policyName);

			if (status1 == true) {

				test.log(LogStatus.PASS, "Successfully clicked " + policyName + " policy details");

			} else {

				test.log(LogStatus.FAIL, "Failed to click " + policyName + " policy details");
			}

			boolean status2 = industries.verifyAndClickCallBack(assertPolicyName);

			if (status2 == true) {

				test.log(LogStatus.PASS,
						"Successfully verified and clicked " + assertPolicyName + " policy : GET A CALLBACK");

			} else {

				test.log(LogStatus.FAIL, "Failed to verify and click " + assertPolicyName + " policy : GET A CALLBACK");

				Assert.assertTrue(false);
			}

			boolean status3 = industries.fillContactUs(name, phone, company, message);

			if (status3 == true) {

				test.log(LogStatus.PASS, "Successfully filled contact us details");

			} else {

				test.log(LogStatus.FAIL, "Failed to fill contact us details");
			}
			
			boolean status4 = industries.submitGetACallBack();

			if (status4 == true) {

				test.log(LogStatus.PASS, "Successfully clicked the submit button");

			} else {

				test.log(LogStatus.FAIL, "Failed to click the submit buttton");
			}
			
			String path = industries.verifySuccessful();

			if (path.isBlank() || path.isEmpty()) {

				test.log(LogStatus.PASS, "Successfully submitted the details");

			} else {

				test.log(LogStatus.PASS, test.addScreenCapture(path) + "Failed to submit the details");

			}
		
	}

}
