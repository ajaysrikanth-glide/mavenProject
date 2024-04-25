package dashboard_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.CalendarPage_Dashboard;
import pages.HomePage_Dashboard;
import pages.LoginPage_Dashboard;
import utilities.DP;

public class CalendarPage_VerifyLostCasesAndRFQ extends BaseTest{

	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;

	CalendarPage_Dashboard calendarPage;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void cp_VerifyLostCasesAndRFQ(String userName, String password, String duration, String startDate,
			String endDate) throws InterruptedException {
		
		ExtentTest test = getExtentTest();

		loginPage = new LoginPage_Dashboard(driver);

		homePage = new HomePage_Dashboard(driver);

		calendarPage = new CalendarPage_Dashboard(driver);

		boolean status3 = loginPage.loginUser(userName, password);

		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully logged in");

		} else {

			test.log(LogStatus.FAIL, "Failed to perform log in");

			Assert.assertTrue(false);
		}

		boolean status = homePage.clickCalendarLink();

		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked and redirected to Calendar page");

		} else {

			test.log(LogStatus.FAIL, "Failed to click and redirect to Calendar page");

			Assert.assertTrue(false);

		}
		
		test.log(LogStatus.INFO, "Duration : " + duration);

		boolean status1 = calendarPage.clickDropDownDuration(duration, startDate, endDate);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Drop down value : " + duration);

		} else {

			test.log(LogStatus.FAIL, "Failed to select drop down value : " + duration);

			Assert.assertTrue(false);
		}
		
		if(!duration.equals("Custom Range")) {
			
			String[] indicatorArray = calendarPage.verifyPreviousLostCasesIndicator(duration);

			if (indicatorArray[3].equals("true")) {

				test.log(LogStatus.PASS, "Successfully validated the indicator for Previous lost cases: "+duration);

				test.log(LogStatus.INFO, "Previous "+duration+" Start Date : " + indicatorArray[0]);

				test.log(LogStatus.INFO, "Previous "+duration+" End Date : " + indicatorArray[1]);

				test.log(LogStatus.INFO, "Previous "+duration+" lost cases : " + indicatorArray[2]);
				

			} else if(indicatorArray[3].equals("false")){

				test.log(LogStatus.FAIL, "Failed to validate the indicator for Previous lost cases : "+duration);

				Assert.assertTrue(false);
			}
		}
		
		String result[]=calendarPage.verifyRFQPending(duration);
		
		if (result[1] == "true") {

			test.log(LogStatus.INFO, "RFQ Pending count : "+result[0]);
			
			test.log(LogStatus.PASS, "Successfully Verified RFQ Pending count");

		} else if (result[1] == "false"){

			test.log(LogStatus.FAIL, "Failed to Verify RFQ Pending count");

			Assert.assertTrue(false);
		}
	}
	
}
