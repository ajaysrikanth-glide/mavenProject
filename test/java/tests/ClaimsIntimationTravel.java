package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.Claims;
import utilities.DP;

public class ClaimsIntimationTravel extends BaseTest {

	Claims claims;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void claimsIntimationTravel(String selectPolicy, String inputName, String inputPolicyNumber,
			String inputPhoneNumber, String inputEmailId, String inputMailingAddress, String incidentTypeValue,
			String incidentDate, String incidentTime, String inputIncidentLocation, String fileMedicalRecord,
			String filePolicyReport, String fileReceipts, String fileProofs, String inputIncidentDescription,
			String inputPhoneTravel, String inputEmailTravel,String insuranceCompanyName, String remarks) throws InterruptedException {

		ExtentTest test = getExtentTest();

		claims = new Claims(driver);

		boolean status = claims.clickClaimsTab();

		if (status == true) {

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

			test.log(LogStatus.PASS, "Successfully selected " + selectPolicy + " policy");

		} else {

			test.log(LogStatus.FAIL, "Failed to select " + selectPolicy + " policy");
		}
		
		test.log(LogStatus.INFO, "Input Details - Name : " + inputName + ", Policy Number : " + inputPolicyNumber + ", Phone No : " + inputPhoneNumber
				+ ", Email ID :" + inputEmailId + ", Mailing Address :" + inputMailingAddress+", Incident Type : "+incidentTypeValue+", Incident Date : "
				+ incidentDate+", Incident Time : "+incidentTime +", Incident Location : "+inputIncidentLocation+", Medical Record : "+fileMedicalRecord+
				", Policy Report : "+filePolicyReport+", Receipts : "+fileReceipts+", Proofs : "+fileProofs+", Incident Description : "+inputIncidentDescription+
				", Phone Travel : "+inputPhoneTravel+", Email Travel : "+inputEmailTravel+", Insurance Company Name : "+insuranceCompanyName+" , Remarks : "+remarks);
		
		

		boolean status3 = claims.fillTravel(inputName, inputPolicyNumber, inputPhoneNumber, inputEmailId,
				inputMailingAddress, incidentTypeValue, incidentDate, incidentTime, inputIncidentLocation,
				fileMedicalRecord, filePolicyReport, fileReceipts, fileProofs, inputIncidentDescription,
				inputPhoneTravel, inputEmailTravel, insuranceCompanyName);

		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully filled " + selectPolicy + " policy details");

		} else {

			test.log(LogStatus.FAIL, "Failed to fill " + selectPolicy + " policy details");
		}

		boolean status4 = claims.submitClaimForm();

		if (status4 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Submit Button of claim Intimation form");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Submit Button of Motor claim Intimation form");

		}

		String path = claims.verifySuccessfull();

		if (path.isBlank()||path.isEmpty()) {

			test.log(LogStatus.PASS, "Successfully submitted Travel claim details");

		} else {

			 test.log(LogStatus.FAIL, test.addScreenCapture(path) + "Failed to submit Travel claim details");

		}

	}

}
