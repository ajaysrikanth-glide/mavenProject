package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import base.BrowserSetup;
import pages.ContactUs;
import utilities.DP;

public class ContactUs_LetUsReachOut extends BaseTest {

	ContactUs contactUs;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void contactUs_LetUsReachOut(String name, String phone, String email, String message, String remarks)
			throws InterruptedException {

		ExtentTest test = getExtentTest();

		contactUs = new ContactUs(driver);

		boolean status = contactUs.clickContactUsTab();
		
		if (status == true) {

			test.log(LogStatus.PASS, "Successfullly clicked Contact us Tab");
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to click Contact us Tab");

			Assert.assertTrue(false);
		}

		test.log(LogStatus.INFO, "Input details - Name : " + name + ", Phone : " + phone + ", Email : " + email
				+ ", Message : " + message+", Remarks :"+remarks);

		boolean status1 = contactUs.fillContactUs(name, phone, email, message);
		
		if (status1 == true) {

			test.log(LogStatus.PASS, "Filled contact us details");
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to fill contact us details");

			Assert.assertTrue(false);
		}

//			String actualName=contactUs.inputName.getAttribute("value");
//			
//			String actualPhone=contactUs.inputPhone.getAttribute("value");
//			
//			String actualDate=contactUs.inputDate.getAttribute("value");
//			
//			String actualTime=contactUs.inputTime.getAttribute("value");

//			test.log(LogStatus.INFO, "Actual details - Name : "+actualName+", Phone : "+actualPhone+", Date : "+actualDate+", Time : "+actualTime);

		boolean status2 = contactUs.submitContactUs();
		
		if (status2 == true) {

			test.log(LogStatus.PASS, "Clicked Submit button of contact us");
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to click submit button");

			Assert.assertTrue(false);
			
		}

		String path = contactUs.verifySuccessful();
		
		if (path.isBlank() || path.isEmpty()) {

			test.log(LogStatus.PASS, "Successfully submitted the details");

		} else {

			test.log(LogStatus.PASS, test.addScreenCapture(path) + "Failed to submit the details");

		}

	}

}
