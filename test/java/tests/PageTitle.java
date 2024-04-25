package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import com.relevantcodes.extentreports.LogStatus;

import base.BrowserSetup;
import pages.Claims;
import utilities.ConfigReader;

public class PageTitle extends BrowserSetup{
	
	ConfigReader config=new ConfigReader();
	
//	WebDriver driver;
	
	Claims homePage;
		
	@Test(priority=0)
	public void openTitle() throws IOException, InterruptedException {

		try {
			
		test=report.startTest("Verify title");
		
		homePage=new Claims(driver);
		
//		homePage.navigateUrl(config.getData("url"));
		
		test.log(LogStatus.INFO, driver.getTitle());	
		
		Assert.assertEquals(driver.getTitle(), "Glide Insurance");
		;
		test.log(LogStatus.PASS, "Successfully verified Guidance");


		} catch (Exception e) {
			e.printStackTrace();
		}
			}

}
