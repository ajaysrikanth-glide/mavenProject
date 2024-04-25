package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.Claims;
import utilities.DP;

public class ClaimsIntimationCommercial extends BaseTest{
	
	Claims claims;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void claimsIntimationCommercial(String selectPolicy,String inputName, String inputPolicyNumber, String inputPhoneNumber, 
			String inputEmailId,String inputMailingAddress, String dateLoss, String timeLoss, 
			String inputLocationOfLoss,String inputPhoneCommercial, String inputEmailCommercial,String insuranceCompanyName, String inputIncidentDetails,
			String inputDamagedItems, String inputProbableCause, String inputThirdPartyDamage, String inputOtherLoss,
			String inputEstimateLoss, String remarks) throws InterruptedException {

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
		
		boolean status3=claims.fillCommercial(inputName, inputPolicyNumber, inputPhoneNumber, inputEmailId, 
				inputMailingAddress, dateLoss, timeLoss, inputLocationOfLoss, inputPhoneCommercial, 
				inputEmailCommercial,insuranceCompanyName, inputIncidentDetails, inputDamagedItems, inputProbableCause, 
				inputThirdPartyDamage, inputOtherLoss, inputEstimateLoss);
		
		test.log(LogStatus.INFO, "Input Details - Name : " + inputName + ", Policy Number : " + inputPolicyNumber + ", Phone No : " + inputPhoneNumber
				+ ", Email ID :" + inputEmailId + ", Mailing Address :" + inputMailingAddress+", Date of Loss : "+dateLoss+", Time of Loss : "
				+ timeLoss+", Location of Loss : "+inputLocationOfLoss +", Phone Number of Person on site : "+inputPhoneCommercial+", "
				+ "Email of Person on site : "+inputEmailCommercial+", Insurance Company Name : "+insuranceCompanyName+", Incident details : "+inputIncidentDetails+", Details of Damaged Items : "
				+ ""+inputDamagedItems+", Probable cause : "+inputProbableCause+", Damage to Third party details : "+inputThirdPartyDamage+
				", Details of loss to any other Authorities: "+inputOtherLoss+", Estimated Loss amount : "+inputEstimateLoss+" , Remarks : "+remarks);
		
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

			test.log(LogStatus.PASS, "Successfully submitted Commercial claim details");

		} else {

			 test.log(LogStatus.FAIL, test.addScreenCapture(path) + "Failed to submit Commercial claim details");

		}

	}
}
