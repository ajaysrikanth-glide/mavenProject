package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.Claims;

public class ClaimsPageRedirection extends BaseTest {

	Claims claims;

	@Test()
	public void claimsPageRedirection() {

		ExtentTest test = getExtentTest();

		claims = new Claims(driver);

		boolean status = claims.clickClaimsTab();

		if (status == true) {

			test.log(LogStatus.PASS, "Successfully Claims Tab clicked");
		} else {

			test.log(LogStatus.FAIL, "Failed to click Claims Tab");

		}

		boolean status1 = claims.clickMotor();

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Motor Button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Motor button");
		}

		boolean status2 = claims.clickClaimIntimationAndVerify();

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Claim Intimation Button under Motor section and redirected");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Claim Intimation Button under Motor section and redirected");
		}

		boolean status9 = claims.redirectBackClaimsPage();

		if (status9 == true) {

			test.log(LogStatus.PASS, "Successfully redirected back to claims page");

		} else {

			test.log(LogStatus.FAIL, "Failed to redirect back to Claims page");
		}

		boolean status3 = claims.clickHealth();

		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Health Button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Health button");
		}

		boolean status4 = claims.clickClaimIntimationAndVerify();

		if (status4 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Claim Intimation Button under Health section and redirected");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Claim Intimation Button under Health section and redirected");
		}

		boolean status10 = claims.redirectBackClaimsPage();

		if (status10 == true) {

			test.log(LogStatus.PASS, "Successfully redirected back to claims page");

		} else {

			test.log(LogStatus.FAIL, "Failed to redirect back to Claims page");
		}

		boolean status5 = claims.clickTravel();

		if (status5 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Travel Button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Travel button");
		}

		boolean status6 = claims.clickClaimIntimationAndVerify();

		if (status6 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Claim Intimation Button under Travel section and redirected");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Claim Intimation Button under Travel section and redirected");
		}

		boolean status11 = claims.redirectBackClaimsPage();

		if (status11 == true) {

			test.log(LogStatus.PASS, "Successfully redirected back to claims page");

		} else {

			test.log(LogStatus.FAIL, "Failed to redirect back to Claims page");
		}

		boolean status7 = claims.clickCommercial();

		if (status7 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Commercial Button");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Commercial button");
		}

		boolean status8 = claims.clickClaimIntimationAndVerify();

		if (status8 == true) {

			test.log(LogStatus.PASS,
					"Successfully clicked Claim Intimation Button under Commercial section and redirected");

		} else {

			test.log(LogStatus.FAIL, "Failed to click Claim Intimation Button under Commercial section and redirected");
		}

		boolean status12 = claims.redirectBackClaimsPage();

		if (status12 == true) {

			test.log(LogStatus.PASS, "Successfully redirected back to claims page");

		} else {

			test.log(LogStatus.FAIL, "Failed to redirect back to Claims page");
		}

	}

}
