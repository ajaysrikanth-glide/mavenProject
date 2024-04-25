package tests;

import java.text.ParseException;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.Claims;
import utilities.DP;

public class ClaimsIntimationHealth extends BaseTest{

	Claims claims;

	@Test(dataProvider = "TestData",dataProviderClass = DP.class)
	public void claimsIntimationHealth(String selectPolicy,String inputName, String inputPolicyNumber, String inputPhoneNumber, 
			String inputEmailId, String inputMailingAddress, String inputCompanyName, String inputEmployeeName, String inputPatientName, 
			String inputRelationship, String inputEmployeeID, String inputUHID, String inputAdmissionDate, String inputDiagnosisReason,
			String inputHospitalName, String inputHospitalAddress, String remarks) throws InterruptedException, ParseException {

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
				+ ", Email ID :" + inputEmailId + ", Mailing Address :" + inputMailingAddress+", Company Name : "+inputCompanyName+", Employee Name : "
				+ inputEmployeeName+", Patient Name : "+inputPatientName +", Relationship : "+inputRelationship+", Employee ID : "+inputEmployeeID+
				", UHID Number : "+inputUHID+", Admission Time : "+inputAdmissionDate+", Reason for Diagnosis : "+inputDiagnosisReason+", Hospital Name : "+inputHospitalName+
				", Hospital Address : "+inputHospitalAddress+" , Remarks : "+remarks);
		
		boolean status3=claims.fillHealth(inputName, inputPolicyNumber, inputPhoneNumber, inputEmailId, inputMailingAddress, inputCompanyName, inputEmployeeName,
				inputPatientName, inputRelationship, inputEmployeeID, inputUHID, inputAdmissionDate, inputDiagnosisReason, inputHospitalName, inputHospitalAddress);
		
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

			test.log(LogStatus.PASS, "Successfully submitted Health claim details");

		} else {

			 test.log(LogStatus.FAIL, test.addScreenCapture(path) + "Failed to submit Health claim details");

		}

	}
}
