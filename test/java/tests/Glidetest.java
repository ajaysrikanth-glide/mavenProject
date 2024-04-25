package tests;

import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.By;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseTest;
import base.BrowserSetup;
import pages.Claims;
import utilities.ConfigReader;
import utilities.DP;

public class Glidetest extends BaseTest{
	
	ConfigReader config=new ConfigReader();
	
	Claims hm;
	
//	WebDriver driver;
		
	@Test(dataProvider = "TestData",dataProviderClass = DP.class)
	public void openUrl(String verify) throws IOException, InterruptedException {

		try {
			
//		test=report.startTest("Opern Url and assert Guidance");
//		
//		hm=new Claims(driver);
//		
//		hm.navigateUrl(config.getData("url"));
//		
//		Thread.sleep(2000);
//		
//		driver.findElement(By.xpath("//a[text()=' For Organizations']")).click();
//		
//		Thread.sleep(5000);
//		
//		hm.verifyGuidance(verify);	
//		
//		test.log(LogStatus.PASS, "Successfully verified Guidance");


		} catch (Exception e) {
			e.printStackTrace();
		}
			}

}
