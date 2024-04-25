package dashboard_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.HomePage_Dashboard;
import pages.LoginPage_Dashboard;
import pages.PospPage_Dashboard;
import utilities.DP;

public class POSPage_VerifyBusinessCategory extends BaseTest{
	
	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;
	
	PospPage_Dashboard pospPage;
	
	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void posp_VerifyBusinessCategory(String userName, String password, String duration, String startDate,
			String endDate, String business) throws InterruptedException {
		
		ExtentTest test = getExtentTest();

		loginPage = new LoginPage_Dashboard(driver);

		homePage = new HomePage_Dashboard(driver);

		pospPage = new PospPage_Dashboard(driver);

		boolean status1 = loginPage.loginUser(userName, password);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully logged in");

		} else {

			test.log(LogStatus.FAIL, "Failed to perform log in");

			Assert.assertTrue(false);
		}
		
		boolean status = homePage.clickPOSPLink();
		
		if (status == true) {

			test.log(LogStatus.PASS, "Successfully clicked and redirected to POSP page");

		} else {

			test.log(LogStatus.FAIL, "Failed to click and redirect to POSP page");

			Assert.assertTrue(false);

		}
		
		if (duration.equals("Custom Range")) {

			test.log(LogStatus.INFO,
					"Duration : " + duration + " , Start Date : " + startDate + " ,End Date : " + endDate+", Business Dropdown value : "+business);

		} else {

			test.log(LogStatus.INFO, "Duration : " + duration+", Business Dropdown value : "+business);

		}
		
		boolean status2 = pospPage.clickDropDownDuration(duration, startDate, endDate);

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Drop down value : " + duration);

		} else {

			test.log(LogStatus.FAIL, "Failed to select drop down value : " + duration);

			Assert.assertTrue(false);
		}
		
//		Thread.sleep(1500);
				
		int result=pospPage.getBusinessPOSPWise(business);
		
		if(business.equals("Policies Booked")) {
			
			if(result==pospPage.getProspectsConverted(duration)) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by POSP wise");
				
			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by POSP wise");

			}
			
		} else if(business.equals("Premium Booked")){
			
			if(Math.abs(result-pospPage.getPolicyNetPremium(duration))<=20) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by POSP wise");

			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by POSP wise");

			}
			
		} else if(business.equals("Commission Receivable")){
			
			if(Math.abs(result-pospPage.getCommissionReceivable(duration))<=20) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by POSP wise");

			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by POSP wise");

			}
		}
				
		int result2=pospPage.getNoOfPOSP(duration);
		
		System.out.println("Number of POSP for "+duration+" : "+result2);
		
		int result3=pospPage.getProspectsGenerated(duration);
		
		System.out.println("Prospects Generated for "+duration+" : "+result3);
		
		int result4=pospPage.getProspectsConverted(duration);
		
		System.out.println("Prospects Converted for "+duration+" : "+result4);
		
		int result5=pospPage.getPolicyNetPremium(duration);
		
		System.out.println("Net Premium Booked for "+duration+" : "+result5);
		
		int result6=pospPage.getCommissionReceivable(duration);
		
		System.out.println("Commission Receivable for "+duration+" : "+result6+"\n");
		
		pospPage.selectBusinessDropDown(business);
		
		int result1=pospPage.selectBusinessOwners(business);
		
		if(business.equals("Policies Booked")) {
			
			if(result1==pospPage.getProspectsConverted(duration)) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by POSP wise : "+pospPage.getProspectsConverted(duration));
				
			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by POSP wise, Prospected converted : "+pospPage.getProspectsConverted(duration)+", POSP wise : "+result1);

			}
			
		} else if(business.equals("Premium Booked")){
			
			if(Math.abs(result1-pospPage.getPolicyNetPremium(duration))<=20) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by POSP wise : "+pospPage.getPolicyNetPremium(duration));

			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by POSP wise, Net Premium : "+pospPage.getPolicyNetPremium(duration)+", POSP wise : "+result1);

			}
			
		} else if(business.equals("Commission Receivable")){
			
			if(Math.abs(result1-pospPage.getCommissionReceivable(duration))<=20) {
				
				test.log(LogStatus.PASS, "Successfully verified "+business+" by POSP wise : "+pospPage.getCommissionReceivable(duration));

			} else {
				
				test.log(LogStatus.FAIL, "Failed to verify "+business+" by POSP wise, Commission Receivable : "+pospPage.getCommissionReceivable(duration)+", POSP wise : "+result1);

			}
		}
	}
}
