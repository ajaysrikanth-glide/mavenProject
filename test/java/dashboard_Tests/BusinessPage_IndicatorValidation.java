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

public class BusinessPage_IndicatorValidation extends BaseTest{
	
	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;

	CalendarPage_Dashboard calendarPage;
	
	BusinessPage_Dashboard businessPage;
	
	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void bp_IndicatorValidation(String userName, String password, String businessType, String duration, String startDate,
			String endDate) throws InterruptedException {
		
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
					"Duration : " + duration + " , Start Date : " + startDate + " ,End Date : " + endDate);

		} else {

			test.log(LogStatus.INFO, "Duration : " + duration);

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
		
		String[] result=businessPage.validatePolicyBookedIndicator(duration);
		
		if(!(result==null)) {
			
			for(String res:result) {
				
				test.log(LogStatus.INFO, res);
			}
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to verify Policy Booked Indicator");
			
			Assert.assertTrue(false);
		}

		String[] netPremiumResult=businessPage.validatePolicyNetPremiumIndicator(duration);
		
		if(!(netPremiumResult==null)) {
			
			for(String res:netPremiumResult) {
				
				test.log(LogStatus.INFO, res);
			}
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to verify Net Premium Indicator");
			
			Assert.assertTrue(false);
		}
		
		String [] brokerageReceivableResult=businessPage.validateCommissionReceivableIndicator(duration);
		
		if(!(brokerageReceivableResult==null)) {
			
			for(String res:brokerageReceivableResult) {
				
				test.log(LogStatus.INFO, res);
			}
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to verify Brokerage Receivable Indicator");
			
			Assert.assertTrue(false);
		}
		
	}

}
