package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.ForOrganisations;
import pages.Organisation_Industries;
import utilities.ConfigReader;
import utilities.DP;

public class Industry_Education extends BaseTest {

	ConfigReader config = new ConfigReader();

	ForOrganisations organisations;

	Organisation_Industries industries;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void industry_Education(String policyName, String assertPolicyName, String name, String phone, String company,
			String message, String remarks) throws InterruptedException {

			ExtentTest test = getExtentTest();

			organisations = new ForOrganisations(driver);

			industries = new Organisation_Industries(driver);

			boolean status = organisations.clickEducation();

			if (status == true) {

				test.log(LogStatus.PASS, "Successfully clicked Educational Institutions");

			} else {

				test.log(LogStatus.FAIL, "Failed to click Educational Institutions");
				
				Assert.assertTrue(false);
			}

			test.log(LogStatus.PASS, "Successfully navigated to Educational Institutions");
			
			test.log(LogStatus.INFO, "Input details - Policy Name : "+policyName+", Name : "+name+", Phone : "+phone+", Company Name : "+company+", Message : "+message+", Remarks : "+remarks);

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
