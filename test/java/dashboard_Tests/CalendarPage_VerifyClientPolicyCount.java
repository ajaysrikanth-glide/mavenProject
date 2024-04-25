package dashboard_Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.CalendarPage_Dashboard;
import pages.HomePage_Dashboard;
import pages.LoginPage_Dashboard;
import utilities.DP;

public class CalendarPage_VerifyClientPolicyCount extends BaseTest{

	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;

	CalendarPage_Dashboard calendarPage;

	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void cp_VerifyClientPolicyCount(String userName, String password, String duration, String startDate,
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
		
		//To handle first welcome toast
		Thread.sleep(5000);

		boolean status1 = calendarPage.clickDropDownDuration(duration, startDate, endDate);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Drop down value : " + duration);

		} else {

			test.log(LogStatus.FAIL, "Failed to select drop down value : " + duration);

			Assert.assertTrue(false);
		}
		
		Map<String, Integer> nameCountMap=calendarPage.verifyDuplicateClientName();
		
		for (Map.Entry<String, Integer> entry : nameCountMap.entrySet()) {
			
            if (entry.getValue() > 1) {
            	
                test.log(LogStatus.INFO, "Duplicate Name : "+entry.getKey());
            }
        }
					
		boolean result=calendarPage.verifyClientPolicyCount();
		
		if(result==true) {
			
			test.log(LogStatus.PASS, "successfully verified Individual client policy count category wise");
		
		} else {
			
			test.log(LogStatus.FAIL, "Failed to verify Individual client policy count category wise");
			
			Assert.assertTrue(false);
			
		}
		
		driver.navigate().refresh();
		
		Thread.sleep(2000);
				
		calendarPage.clickDropDownDuration(duration, startDate, endDate);
		
		int[] result2=calendarPage.verifyNoOfPolicyCount();
		
		if(result2[0]==result2[1] && result2[0]==result2[2]) {
			
			test.log(LogStatus.PASS, "Successfully validated Total client wise policy count and Category wise, Total Renewals : "+result2[0]);
		
		} else {
			
			test.log(LogStatus.FAIL, "Failed to validate Upcoming Renewals count : "+result2[0]);
			
			test.log(LogStatus.FAIL, "Failed to validate Total client wise policy count : "+result2[1]+", and Category wise count : "+result2[2]);
		}
		
	}
}
