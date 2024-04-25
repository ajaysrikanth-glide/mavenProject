package dashboard_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.HomePage_Dashboard;
import pages.LeadsPage_Dashboard;
import pages.LoginPage_Dashboard;
import utilities.DP;

public class LeadsPage_LeadsGeneratedValidation extends BaseTest{

	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;
	
	LeadsPage_Dashboard leadsPage;
	
	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void lp_LeadsGeneratedValidation(String userName, String password, String duration, String startDate,
			String endDate) throws InterruptedException {
		
		ExtentTest test = getExtentTest();

		loginPage = new LoginPage_Dashboard(driver);

		homePage = new HomePage_Dashboard(driver);
		
		leadsPage = new LeadsPage_Dashboard(driver);
		
		boolean status1=loginPage.loginUser(userName, password);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully logged in");

		} else {

			test.log(LogStatus.FAIL, "Failed to perform log in");

			Assert.assertTrue(false);
		}
				
		boolean status2 = homePage.clickLeadsLink();

		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked and redirected to Leads page");

		} else {

			test.log(LogStatus.FAIL, "Failed to click and redirect to Leads page");

			Assert.assertTrue(false);

		}
		
		if (duration.equals("Custom Range")) {

			test.log(LogStatus.INFO,
					"Duration : " + duration + " , Start Date : " + startDate + " ,End Date : " + endDate);

		} else {

			test.log(LogStatus.INFO, "Duration : " + duration);

		}
		
		boolean status3 = leadsPage.clickDropDownDuration(duration, startDate, endDate);

		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Drop down value : " + duration);

		} else {

			test.log(LogStatus.FAIL, "Failed to select drop down value : " + duration);

			Assert.assertTrue(false);
		}
		
		Thread.sleep(2000);
		
		int leadsGenerated=leadsPage.getLeadsGenetated();
				
		int result=leadsPage.getOverallLeadsGeneratedByCategory();	
		
		if(result==leadsGenerated) {
			
			test.log(LogStatus.PASS, "Successfully Validated leads generated value by category : "+result);
		
		} else {
			
			test.log(LogStatus.FAIL, "Failed to Validate leads generated value by category : Leads generated - "+leadsGenerated+" , Category wise leads - "+result);

		}
		
		int result1=leadsPage.getLeadsGeneratedByBO();
		
		if(result1==leadsGenerated) {
			
			test.log(LogStatus.PASS, "Successfully Validated Total leads generated value by Business owners : "+result1);
		
		} else {
			
			test.log(LogStatus.FAIL, "Failed to Validate Total leads generated value by Business owners : Leads generated - "+leadsGenerated+" , Business owner wise Total Leads - "+result1);

		}
		
		int leadsConverted=leadsPage.getLeadsConverted();
		
		int result2=leadsPage.getLeadsConvertedByBO();
		
		if(result2==leadsConverted) {
			
			test.log(LogStatus.PASS, "Successfully Validated Total leads converted value by Business owners : "+result2);
		
		} else {
			
			test.log(LogStatus.FAIL, "Failed to Validate Total leads generated value by Business owners : Leads generated - "+leadsConverted+" , Business owner wise Total Leads - "+result2);

		}
	}
		
}
