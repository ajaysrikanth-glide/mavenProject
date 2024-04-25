package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.ForIndividuals;
import utilities.DP;

public class Individuals_Verify_All_Products extends BaseTest{

	ForIndividuals individuals;
	
	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void ind_Verify_All_Products(String policyName, String assertPolicyName, String name, String phone, String email,
			String message, String remarks) throws InterruptedException {

		individuals = new ForIndividuals(driver);

		ExtentTest test = getExtentTest();
		
		boolean status5=individuals.clickForIndividualsTab();
		
		if(status5==true) {
			
			test.log(LogStatus.PASS, "Successfully clicked For Individuals Tab");
		
		} else {
			
			test.log(LogStatus.FAIL, "Failed to click For Individuals Tab");

		}
		
		boolean status=individuals.clickAllProductDetailsButton();

		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked All product details button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click All product details button");

			Assert.assertTrue(false);
		}
		
		test.log(LogStatus.INFO, "Input details - Policy Name : "+policyName+", Name : "+name+", Phone : "+phone+", Email : "+email+", Message : "+message+", Remarks : "+remarks);
		
		boolean status1=individuals.clickAllProductsPolicy(policyName, assertPolicyName);
		
		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked " + policyName + " policy details and Talk to us");

		} else {

			test.log(LogStatus.FAIL, "Failed to click " + policyName + " policy details and Talk to us");
			
			Assert.assertTrue(false);
		}
		
		boolean status3 = individuals.fillGetACallBack(name, phone, email, message);

		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully filled contact us details");

		} else {

			test.log(LogStatus.FAIL, "Failed to fill contact us details");
		}

		boolean status4 = individuals.submitGetACallBack();

		if (status4 == true) {

			test.log(LogStatus.PASS, "Successfully clicked the submit button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click the submit buttton");
		}

		String path = individuals.verifySuccessful();

		if (path.isBlank() || path.isEmpty()) {

			test.log(LogStatus.PASS, "Successfully submitted the details");

		} else {

			test.log(LogStatus.PASS, test.addScreenCapture(path) + "Failed to submit the details");

		}

	}
}
