package base;

import java.io.File;
import java.net.MalformedURLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import org.testng.ITestContext;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import singletons.ReportSingleton;
import utilities.ConfigReader;
import utilities.Screenshot;
import utilities.ThreadLocalExtentTest;

public class BrowserSetup {

	ConfigReader config = new ConfigReader();

	Screenshot scrShot = new Screenshot();

	public WebDriver driver;

	public ExtentTest test;

	public ExtentReports report;
	
	
	@BeforeSuite
	public void extentReport() {
		String extentReportTitle=LocalDate.now().toString().replace(":","_");
		report=new ExtentReports(System.getProperty("user.dir")+"/src/test/java/outputResults/ExtentReport_"+extentReportTitle+".html",false);
        report.loadConfig(new File(System.getProperty("user.dir")+"/src/test/java/ExtentReportConfiguration.xml"));
        
		
	}
		
	@BeforeClass(alwaysRun = true)
	public void browserInitialization(ITestResult context) throws MalformedURLException {
		if(config.getData("browser").equals("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options=new ChromeOptions();
			
//			options.addArguments("--headless");
			
			driver=new ChromeDriver(options);
			
		} else if(config.getData("browser").equals("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			
			FirefoxOptions options=new FirefoxOptions();
			
//			options.addArguments("--headless");
			
			driver=new FirefoxDriver(options);
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

//		driver.get(config.getData("url"));	
		
//		ReportSingleton objReport=ReportSingleton.getInstance();
//		
//		report=objReport.getReport();	
//		 String extentReportTitle=LocalDateTime.now().toString().replace(":","_");
//	    	String extentReportTitle=LocalDate.now().toString().replace(":","_");
	    	String extentReportTitle=context.getName();
	    	
	    	System.out.println(extentReportTitle);
	    	
//	        System.out.println(System.getProperty("user.dir")+"/src/test/java/ExtentReportConfiguration.xml");
//	        report=new ExtentReports(System.getProperty("user.dir")+"/src/test/java/outputResults/ExtentReport_"+extentReportTitle+".html",false);
//	        report.loadConfig(new File(System.getProperty("user.dir")+"/src/test/java/ExtentReportConfiguration.xml"));
	        
				
	}
	
	@BeforeMethod
	public void url() {
		driver.get(config.getData("url"));
		
	}

	@AfterClass
	public void tearDown() {

		report.flush();
//		report.endTest(test);

		 driver.close();
	}
	
	@AfterSuite
	public void flush() {
		
//		report.flush();
		
	}

	@AfterMethod
	public void getResult(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {

			String path=scrShot.captureScreenshot(result.getName() + "_Fail", driver);

			 test.log(LogStatus.FAIL,test.addScreenCapture(path)+result.getName()+"failed");

		}

		report.endTest(test);
//		driver.close();
	}

}
