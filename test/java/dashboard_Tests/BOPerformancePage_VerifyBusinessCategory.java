package dashboard_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.BOPerformancePage_Dashboard;
import pages.HomePage_Dashboard;
import pages.LoginPage_Dashboard;
import utilities.DP;

public class BOPerformancePage_VerifyBusinessCategory extends BaseTest{

	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;
	
	BOPerformancePage_Dashboard boPerformancePage;
	
	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void bo_VerifyBusinessCategory(String userName, String password, String duration, String startDate,
			String endDate, String business) throws InterruptedException {
		
		ExtentTest test = getExtentTest();

		loginPage = new LoginPage_Dashboard(driver);

		homePage = new HomePage_Dashboard(driver);

		boPerformancePage = new BOPerformancePage_Dashboard(driver);
		
		boolean status1 = loginPage.loginUser(userName, password);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully logged in");

		} else {

			test.log(LogStatus.FAIL, "Failed to perform log in");

			Assert.assertTrue(false);
		}
		
		boolean status = homePage.clickBusinessOwnerPerformanceLink();
		
		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked and redirected to Business Owner Performance page");

		} else {

			test.log(LogStatus.FAIL, "Failed to click and redirect to Business Owner Performance page");

			Assert.assertTrue(false);

		}
		
		if (duration.equals("Custom Range")) {

			test.log(LogStatus.INFO,
					"Duration : " + duration + " , Start Date : " + startDate + " ,End Date : " + endDate+", Business Dropdown value : "+business);

		} else {

			test.log(LogStatus.INFO, "Duration : " + duration+", Business Dropdown value : "+business);

		}
	
		boolean status2 = boPerformancePage.clickDropDownDuration(duration, startDate, endDate);

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Drop down value : " + duration);

		} else {

			test.log(LogStatus.FAIL, "Failed to select drop down value : " + duration);

			Assert.assertTrue(false);
		}
		
		int result=boPerformancePage.getBusinessBookedBOWise(business);
		
		if(business.equals("Policies Booked")) {
			
			if(result==boPerformancePage.getProspectsConvertedValue(duration)) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by Business owner wise");
				
			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by Business owner wise");

			}
			
		} else if(business.equals("Premium Booked")){
			
			if(Math.abs(result-boPerformancePage.getNetPremiumValue(duration))<=30) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by Business owner wise");

			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by Business owner wise");

			}
			
		} else if(business.equals("Commission Receivable")){
			
			if(Math.abs(result-boPerformancePage.getCommissionReceivableValue(duration))<=30) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by Business owner wise");

			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by Business owner wise");

			}
		}
				
		int result2=boPerformancePage.getBusinessOwnerValue(duration);
		
		System.out.println("Business Owner for "+duration+" : "+result2);
		
		int result3=boPerformancePage.getProspectsGeneratedValue(duration);
		
		System.out.println("Prospects Generated for "+duration+" : "+result3);
		
		int result4=boPerformancePage.getProspectsConvertedValue(duration);
		
		System.out.println("Prospects Converted for "+duration+" : "+result4);
		
		int result5=boPerformancePage.getNetPremiumValue(duration);
		
		System.out.println("Net Premium Booked for "+duration+" : "+result5);
		
		int result6=boPerformancePage.getCommissionReceivableValue(duration);
		
		System.out.println("Commission Receivable for "+duration+" : "+result6+"\n");
		
		boPerformancePage.selectBusinessDropDown(business);
		
		int result1=boPerformancePage.selectBusinessOwners(business);
		
		if(business.equals("Policies Booked")) {
			
			if(result1==boPerformancePage.getProspectsConvertedValue(duration)) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by Business owner wise : "+boPerformancePage.getProspectsConvertedValue(duration));
				
			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by Business owner wise, Prospected converted : "+boPerformancePage.getProspectsConvertedValue(duration)+", Business owner wise : "+result1);

			}
			
		} else if(business.equals("Premium Booked")){
			
			if(Math.abs(result1-boPerformancePage.getNetPremiumValue(duration))<=25) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by Business owner wise : "+boPerformancePage.getNetPremiumValue(duration));

			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by Business owner wise, Net Premium : "+boPerformancePage.getNetPremiumValue(duration)+", Business owner wise : "+result1);

			}
			
		} else if(business.equals("Commission Receivable")){
			
			if(Math.abs(result1-boPerformancePage.getCommissionReceivableValue(duration))<=25) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by Business owner wise : "+boPerformancePage.getCommissionReceivableValue(duration));

			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by Business owner wise, Commission Receivable : "+boPerformancePage.getCommissionReceivableValue(duration)+", Business owner wise : "+result1);

			}
		}
		
	}
}
