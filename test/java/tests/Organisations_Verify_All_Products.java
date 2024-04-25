package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.ForOrganisations;
import utilities.DP;

public class Organisations_Verify_All_Products extends BaseTest{

	ForOrganisations organisations;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void org_Verify_All_Products(String policyName, String assertPolicyName, String name, String phone, String company,
			String message, String remarks) throws InterruptedException {

		organisations = new ForOrganisations(driver);

		ExtentTest test = getExtentTest();
		
		boolean status=organisations.clickAllProductDetailsButton();

		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked All product details button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click All product details button");

			Assert.assertTrue(false);
		}
		
		test.log(LogStatus.INFO, "Input details - Policy Name : "+policyName+", Name : "+name+", Phone : "+phone+", Company Name : "+company+", Message : "+message+", Remarks : "+remarks);
		
		boolean status1=organisations.clickAllProductsPolicy(policyName, assertPolicyName);
		
		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked " + policyName + " policy details and Talk to us");

		} else {

			test.log(LogStatus.FAIL, "Failed to click " + policyName + " policy details and Talk to us");
			
			Assert.assertTrue(false);
		}
		
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
