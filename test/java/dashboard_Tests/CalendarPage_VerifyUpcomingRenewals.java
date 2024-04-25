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

public class CalendarPage_VerifyUpcomingRenewals extends BaseTest {

	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;

	CalendarPage_Dashboard calendarPage;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void cp_VerifyUpcomingRenewals(String userName, String password, String duration, String startDate,
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

		if (duration.equals("Custom Range")) {

			test.log(LogStatus.INFO,
					"Duration : " + duration + " , Start Date : " + startDate + " ,End Date : " + endDate);

		} else {

			test.log(LogStatus.INFO, "Duration : " + duration);

		}

		// To handle first welcome toast
		Thread.sleep(5000);

		boolean status1 = calendarPage.clickDropDownDuration(duration, startDate, endDate);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Drop down value : " + duration);

		} else {

			test.log(LogStatus.FAIL, "Failed to select drop down value : " + duration);

			Assert.assertTrue(false);
		}

//		Thread.sleep(5000);

		int[] policiesArray = calendarPage.verifyNoOfPolicyCount();

		if (policiesArray != null) {

			test.log(LogStatus.PASS, "Successfully verified the Policy count");

			test.log(LogStatus.INFO, "Total Upcoming renewals count : " + policiesArray[0]);

			test.log(LogStatus.INFO, "Cummulative client policy count : " + policiesArray[1]);

			test.log(LogStatus.INFO, "Cummulative category policy count : " + policiesArray[2]);

		} else {

			test.log(LogStatus.FAIL, "Failed to verify the policy count ");

			Assert.assertTrue(false);
		}

		/*
		 * if (!duration.equals("Custom Range")) {
		 * 
		 * String[] indicatorArray =
		 * calendarPage.upcomingRenewalsIndicatorValidation(duration, policiesArray[2]);
		 * 
		 * if (indicatorArray[3].equals("true")) {
		 * 
		 * test.log(LogStatus.PASS,
		 * "Successfully validated the indicator for upcoming Policy renewals: " +
		 * duration);
		 * 
		 * test.log(LogStatus.INFO, "Previous " + duration + " Start Date : " +
		 * indicatorArray[0]);
		 * 
		 * test.log(LogStatus.INFO, "Previous " + duration + " End Date : " +
		 * indicatorArray[1]);
		 * 
		 * test.log(LogStatus.INFO, "Previous " + duration + " Upcoming policy count : "
		 * + indicatorArray[2]);
		 * 
		 * } else if (indicatorArray[3].equals("false")) {
		 * 
		 * test.log(LogStatus.FAIL,
		 * "Failed to validate the indicator for upcoming Policy renewals : " +
		 * duration);
		 * 
		 * Assert.assertTrue(false); } }
		 */
	}

}
