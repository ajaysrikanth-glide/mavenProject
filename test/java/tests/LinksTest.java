package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BrowserSetup;
import pages.BasePage;

public class LinksTest extends BrowserSetup{

    BasePage basePage;
    
    @BeforeClass
    public void before() {
    	
////    	String extentReportTitle="verifyLinks"+LocalDateTime.now().toString().replace(":","_");
//		String extentReportTitle="s";
//        report=new ExtentReports(System.getProperty("user.dir")+"/src/test/java/outputResults/ExtentReport_"+extentReportTitle+".html",true);
//		report.loadConfig(new File(System.getProperty("user.dir")+"/src/test/java/ExtentReportConfiguration.xml"));
        
    }

    @Test
    public void verifyLinks() {
    	
    	try { 
    	
    	test=report.startTest("verify links");
    		
        basePage=new BasePage(driver);
        
        basePage.navigateHomePage();

        List<List<String>> urls=basePage.verifyLinks();
        
        List<String> emptyUrls=urls.get(2);
        if(!(emptyUrls==null)) {
        	test.log(LogStatus.FAIL, "empty url links are present");
        	Assert.assertEquals(emptyUrls.size(),0);
        	System.out.println("empty url size is :"+emptyUrls.size());
        	for(String url:emptyUrls) {
        		test.log(LogStatus.INFO, url);
        	}
        }
        
        List<String> errorUrls=urls.get(0);
        
        if(!errorUrls.isEmpty()) {
        	test.log(LogStatus.FAIL, "error url links are present");
        	Assert.assertNull(errorUrls);
        	System.out.println("error url size is :"+errorUrls.size());
        	for(String url:errorUrls) {
        		test.log(LogStatus.INFO, url);
        	}
        }
        
        
        List<String> successUrls=urls.get(1);
        System.out.println("success url size is :"+successUrls.size());
        for(String url:successUrls) {
        	test.log(LogStatus.INFO, url);
        }
             
        test.log(LogStatus.PASS, "verify links are valid");

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
