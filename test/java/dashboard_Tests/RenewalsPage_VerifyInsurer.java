package dashboard_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.HomePage_Dashboard;
import pages.LoginPage_Dashboard;
import pages.RenewalsPage_Dashboard;
import utilities.DP;

public class RenewalsPage_VerifyInsurer extends BaseTest{

	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;

	RenewalsPage_Dashboard renewalsPage;
	
	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void rp_VerifyInsurer(String userName, String password, String duration, String startDate,
			String endDate) throws InterruptedException {
		
		ExtentTest test = getExtentTest();
		
		loginPage = new LoginPage_Dashboard(driver);

		homePage = new HomePage_Dashboard(driver);
		
		renewalsPage = new RenewalsPage_Dashboard(driver);
		
		boolean status1=loginPage.loginUser(userName, password);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully logged in");

		} else {

			test.log(LogStatus.FAIL, "Failed to perform log in");

			Assert.assertTrue(false);
		}
		
		boolean status2 = homePage.clickRenewalsLink();	
		
		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked and redirected to Renewals page");

		} else {

			test.log(LogStatus.FAIL, "Failed to click and redirect to Renewals page");

			Assert.assertTrue(false);

		}
		
		if (duration.equals("Custom Range")) {

			test.log(LogStatus.INFO,
					"Duration : " + duration + " , Start Date : " + startDate + " ,End Date : " + endDate);

		} else {

			test.log(LogStatus.INFO, "Duration : " + duration);

		}
		
		boolean status3 =renewalsPage.clickDropDownDuration(duration, startDate, endDate);
		
		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Drop down value : " + duration);

		} else {

			test.log(LogStatus.FAIL, "Failed to select drop down value : " + duration);

			Assert.assertTrue(false);
		}
		
		int result4=renewalsPage.getTotalRenewals();
		
		int result5=renewalsPage.getLostRenewals();
		
		test.log(LogStatus.INFO, "Lost Renewals : "+result5);
		
		int result6[]=renewalsPage.selectInsurerDropDownAndVerifyDonutCharts();
		
		if(result6[1]==result4) {
			
			test.log(LogStatus.PASS, "Successfully verified Total Renewals");
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to verify Total Renwals : "+result4+", Insurer wise : "+result6[1]);
		}
		
		int result7[]=renewalsPage.selectInsurerDropDownAndVerifyCategoryWise();
		
		if(result7[1]==result4) {
			
			test.log(LogStatus.PASS, "Successfully verified Total Renewals : "+result4);

		} else {
			
			test.log(LogStatus.FAIL, "Failed to verify Total Renwals : "+result4+", Insurer wise : "+result7[1]);
			
		}
		
		if(result6[0]==result7[0]) {
			
			test.log(LogStatus.PASS, "Successfully verified Total Policies Booked : "+result6[0]);
			
		} else {
			
			test.log(LogStatus.FAIL, "Failed to verify Total Policies Booked Insurer wise : "+result6[0]+", Category wise : "+result7[0]);
		}
	}
}
