package tests;

import java.text.ParseException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.Claims;
import utilities.DP;

public class ClaimsIntimationMotor extends BaseTest {

	Claims claims;

	@Test(dataProvider = "TestData",dataProviderClass = DP.class)
	public void claimsIntimationMotor(String selectPolicy,String inputName, String inputPolicyNumber, String inputPhoneNumber, 
			String inputEmailId, String inputMailingAddress, String inputVehicleName, 
			String inputPolicyHolderName, String inputRegistrationNumber, String inputDriverName, 
			String inputLicenseNumber, String date, String time, String inputAccidentLocation, 
			String inputCauseofAccident, String inputWorkshopDetails, String inputPhoneMotor, 
			String inputEmailMotor, String insuranceCompanyName, String remarks) throws InterruptedException, ParseException {

		ExtentTest test = getExtentTest();

		claims = new Claims(driver);

		boolean status=claims.clickClaimsTab();
		
		if(status==true) {
			
			test.log(LogStatus.PASS, "Successfully Claims Tab clicked");
		} else {
			
		test.log(LogStatus.FAIL, "Failed to click Claims Tab");
			
		}		

		boolean status1 = claims.clickInitiateClaim();

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Intimate Claims button");
			
		} else {

			test.log(LogStatus.FAIL, "Failed to click Intimate Claims button");
		}

		boolean status2 = claims.selectPolicyDropDown(selectPolicy);

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully selected "+selectPolicy+" policy");
			
		} else {

			test.log(LogStatus.FAIL, "Failed to select "+selectPolicy+" policy");
		}
		
		test.log(LogStatus.INFO, "Input Details - Name : " + inputName + ", Policy Number : " + inputPolicyNumber + ", Phone No : " + inputPhoneNumber
				+ ", Email ID :" + inputEmailId + ", Mailing Address :" + inputMailingAddress+", Vehicle Name : "+inputVehicleName+", Policy Holder Name : "
				+ inputPolicyHolderName+", Registration Number : "+inputRegistrationNumber +", Driver Name : "+inputDriverName+", License Number : "+inputLicenseNumber+
				", Accident Date : "+date+", Accident Time : "+time+", Accident Location : "+inputAccidentLocation+", Cause of Accident : "+inputCauseofAccident+
				", Workshop Details : "+inputWorkshopDetails+", Phone Motor : "+inputPhoneMotor+", Email Motor : "+inputEmailMotor+", Insurance Company Name : "+insuranceCompanyName+" , Remarks : "+remarks);
		
		boolean status3=claims.fillMotor(inputName, inputPolicyNumber, inputPhoneNumber, 
			inputEmailId, inputMailingAddress, inputVehicleName, 
			inputPolicyHolderName, inputRegistrationNumber, inputDriverName, 
			inputLicenseNumber, date, time, inputAccidentLocation, 
			inputCauseofAccident, inputWorkshopDetails, inputPhoneMotor, 
			inputEmailMotor,insuranceCompanyName);
		
		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully filled "+selectPolicy+" policy details");
		} else {

			test.log(LogStatus.FAIL, "Failed to fill "+selectPolicy+" policy details");
		}
		
		boolean status4=claims.submitClaimForm();
		
		if(status4==true) {
			
			test.log(LogStatus.PASS, "Successfully clicked Submit Button of claim Intimation form");
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to click Submit Button of Motor claim Intimation form");

		}
		
		String path = claims.verifySuccessfull();

		if (path.isBlank()||path.isEmpty()) {

			test.log(LogStatus.PASS, "Successfully submitted Motor claim details");

		} else {

			 test.log(LogStatus.FAIL, test.addScreenCapture(path) + "Failed to submit Motor claim details");

		}

	}

}
