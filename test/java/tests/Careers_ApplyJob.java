package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import pages.Careers;
import utilities.ConfigReader;
import utilities.DP;

public class Careers_ApplyJob extends BaseTest{

	ConfigReader config=new ConfigReader();
	
	Careers careers;
	
	@Test(dataProvider = "TestData",dataProviderClass = DP.class)
	public void careers_ApplyJob(String vaccancy, String name, String phone, String email, String location, String remarks) throws InterruptedException {
					
			ExtentTest test=getExtentTest();
			
			careers=new Careers(driver);
			
			careers.clickCareersTab();
			
			test.log(LogStatus.PASS, "clicked Careers Tab");
			
			test.log(LogStatus.INFO, "Input details - Name : "+name+", Phone : "+phone+", Email : "+email+", Location : "+location+", Remarks :"+remarks);
			
			boolean status1=careers.selectAndFillOpeningPosition(vaccancy,name,phone,email,location);
			
			if(status1==false) {
				
	        	test.log(LogStatus.FAIL, "Failed to Select "+vaccancy+" vaccant job positoin");
	        	
	        	Assert.assertTrue(false);
	        }
			
			test.log(LogStatus.PASS, "Selected "+vaccancy+" opening position and filled the details");
			

	        boolean status2=careers.submitPosition();
	        
	        if(status2==false) {
	        	
	        	test.log(LogStatus.FAIL, "Failed to click submit button");
	        	
	        	Assert.assertTrue(false);
	        	
	        } else {
	        	
	        	test.log(LogStatus.PASS, "Submit button of Job opening clicked");
	        }
	               

	        String path=careers.verifySuccessful();
	        
	        if (path.isBlank() || path.isEmpty()) {

				test.log(LogStatus.PASS, "Successfully submitted the details");

			} else {

				test.log(LogStatus.PASS, test.addScreenCapture(path) + "Failed to submit the details");

			}
			
	}
}
