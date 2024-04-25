package dashboard_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.BusinessPage_Dashboard;
import pages.CalendarPage_Dashboard;
import pages.HomePage_Dashboard;
import pages.LoginPage_Dashboard;
import utilities.DP;

public class BusinessPage_VerifyAllCategory extends BaseTest{

	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;

	CalendarPage_Dashboard calendarPage;
	
	BusinessPage_Dashboard businessPage;
	
	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void bp_VerifyAllCategory(String userName, String password, String businessType,String duration, String startDate,
			String endDate, String business) throws InterruptedException {
		
		ExtentTest test = getExtentTest();

		loginPage = new LoginPage_Dashboard(driver);

		homePage = new HomePage_Dashboard(driver);
		
		businessPage = new BusinessPage_Dashboard(driver);
		
		boolean status1=loginPage.loginUser(userName, password);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully logged in");

		} else {

			test.log(LogStatus.FAIL, "Failed to perform log in");

			Assert.assertTrue(false);
		}
		
		homePage.clickCalendarLink();
		
		boolean status2 = homePage.clickBusinessLink();

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked and redirected to Business page");

		} else {

			test.log(LogStatus.FAIL, "Failed to click and redirect to Business page");

			Assert.assertTrue(false);

		}
		
		if (duration.equals("Custom Range")) {

			test.log(LogStatus.INFO,
					"Duration : " + duration + " , Start Date : " + startDate + " ,End Date : " + endDate+", Business Dropdown value : "+business);

		} else {

			test.log(LogStatus.INFO, "Duration : " + duration+", Business Dropdown value : "+business);

		}
		
		boolean status4=businessPage.click_Overall_Corporate_Retail_Button(businessType);

		if(status4 == true) {

			test.log(LogStatus.PASS, "Successfully clicked "+businessType);
		} else {

			test.log(LogStatus.FAIL, "Failed to click "+businessType);
		}
		
		boolean status3 = businessPage.clickDropDownDuration(duration, startDate, endDate);

		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Drop down value : " + duration);

		} else {

			test.log(LogStatus.FAIL, "Failed to select drop down value : " + duration);

			Assert.assertTrue(false);
		}
		
		Thread.sleep(2000);
		
		System.out.println("Policies Booked : "+businessPage.policiesBooked.getText());
		
		System.out.println("Net Premium : "+businessPage.policyNetPremium.getText());
		
		System.out.println("Brokerage Receivable : "+businessPage.commissionReceivable.getText());
		
		boolean status5=businessPage.selectBusinessDropDown(business);
		
		if(status5==true) {
			
			test.log(LogStatus.PASS, "Successfully selected the Business drop down value : "+business);
		
		} else {
			
			test.log(LogStatus.FAIL, "Failed to select the Business drop down value : "+business);

		}
		
		String[] result1=businessPage.getAllCategoryValues(business);
		
		if(!(result1==null)) {
			
			for(String res:result1) {
				
				test.log(LogStatus.INFO, res);
			}
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to verify "+business);
			
			Assert.assertTrue(false);
		}
		
		
	}
}
