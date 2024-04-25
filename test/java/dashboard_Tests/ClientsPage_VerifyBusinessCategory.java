package dashboard_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.ClientsPage_Dashboard;
import pages.HomePage_Dashboard;
import pages.LoginPage_Dashboard;
import utilities.DP;

public class ClientsPage_VerifyBusinessCategory extends BaseTest{
	
	LoginPage_Dashboard loginPage;

	HomePage_Dashboard homePage;

	ClientsPage_Dashboard clientPage;
	
	@Test(dataProvider = "TestData", dataProviderClass = DP.class)
	public void client_VerifyBusinessCategory(String userName, String password, String duration, String startDate,
			String endDate) throws InterruptedException {
		
		ExtentTest test = getExtentTest();
		
		loginPage = new LoginPage_Dashboard(driver);

		homePage = new HomePage_Dashboard(driver);
	
		clientPage = new ClientsPage_Dashboard(driver);
		
		boolean status1=loginPage.loginUser(userName, password);

		if (status1 == true) {

			test.log(LogStatus.PASS, "Successfully logged in");

		} else {

			test.log(LogStatus.FAIL, "Failed to perform log in");

			Assert.assertTrue(false);
		}
		
		boolean status2 = homePage.clickClientsLink();
		
		if (status2 == true) {

			test.log(LogStatus.PASS, "Successfully clicked and redirected to Clients page");

		} else {

			test.log(LogStatus.FAIL, "Failed to click and redirect to Clients page");

			Assert.assertTrue(false);

		}
		
		if (duration.equals("Custom Range")) {

			test.log(LogStatus.INFO,
					"Duration : " + duration + " , Start Date : " + startDate + " ,End Date : " + endDate);

		} else {

			test.log(LogStatus.INFO, "Duration : " + duration);

		}
		
		boolean status3 =clientPage.clickDropDownDuration(duration, startDate, endDate);
		
		if (status3 == true) {

			test.log(LogStatus.PASS, "Successfully clicked Drop down value : " + duration);

		} else {

			test.log(LogStatus.FAIL, "Failed to select drop down value : " + duration);

			Assert.assertTrue(false);
		}
		
		int result=clientPage.getNumberOfCorporateClients();
		
		test.log(LogStatus.INFO, "Number of Corporate Clients : " +result);
		
		int result1= clientPage.getNumberOfIndividualClients();
		
		test.log(LogStatus.INFO, "Number of Individual Clients : "+result1);
		
		int result2=clientPage.getNumberOfIndustriesWeServe();
		
		test.log(LogStatus.INFO, "Industries We Serve : "+result2);
				
		int results[]=clientPage.selectCategoryDropDownAndVerify();
		
		if(!(results==null)) {
			
			test.log(LogStatus.PASS, "Policies Booked : "+results[0]);
			
			test.log(LogStatus.PASS, "Brokerage Receivable : "+results[1]);
		}
	}

}
