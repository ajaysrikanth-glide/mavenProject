package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import utilities.Screenshot;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
	private static ExtentReports extent;
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
	public WebDriver driver;
	ConfigReader config = new ConfigReader();
	Screenshot scrShot = new Screenshot();

	@BeforeSuite
	public void beforeSuite() {
//        extent = new ExtentReports("extent-report.html", true);
//        extent.loadConfig(new File(System.getProperty("user.dir")+"/src/test/java/ExtentReportConfiguration.xml"));
		String extentReportTitle = LocalDateTime.now().toString().replace(":", "_");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/test/java/outputResults/ExtentReport_"
				+ extentReportTitle + ".html");
		extent.loadConfig(new File(System.getProperty("user.dir") + "/src/test/java/ExtentReportConfiguration.xml"));

	}

	@BeforeClass
	public void beforeClass() {
		if (config.getData("browser").equals("chrome")) {

//			WebDriverManager.chromedriver().setup();
			WebDriverManager.chromedriver().clearDriverCache().setup();
			WebDriverManager.chromedriver().clearResolutionCache().setup();
			
			 // Create ChromeOptions instance
	        ChromeOptions options = new ChromeOptions();

	     // Set Chrome options
	        options.addArguments("--remote-allow-origins=*", "--incognito");
	        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			driver = new ChromeDriver(options);

		} else if (config.getData("browser").equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			
			 // Create FirefoxOptions instance
	        FirefoxOptions options = new FirefoxOptions();

	        // Add the incognito option
	        options.addArguments("-private-window");

			driver = new FirefoxDriver(options);
		}

		 // Set the window size to 1280x720 pixels
//        Dimension dimension = new Dimension(1280, 595);
//        driver.manage().window().setSize(new Dimension(1920, 945));
        
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get(config.getData("url"));
	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driverThread.set(driver);
		driver.get(config.getData("url"));
		
		Thread.sleep(3000);
//        ExtentTest extentTest = extent.startTest(this.getClass().getSimpleName() + " - " + Thread.currentThread().getName());
		ExtentTest extentTest = extent.startTest(this.getClass().getSimpleName());

		test.set(extentTest);
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            test.get().log(LogStatus.FAIL, "Test Failed: " + result.getName());
//            test.get().log(LogStatus.FAIL, "Error Details: " + result.getThrowable());
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//            test.get().log(LogStatus.PASS, "Test Passed: " + result.getName());
//        } else if (result.getStatus() == ITestResult.SKIP) {
//            test.get().log(LogStatus.SKIP, "Test Skipped: " + result.getName());
//        }
		if (ITestResult.FAILURE == result.getStatus()) {

			String path = scrShot.captureScreenshot(result.getName() + "_Fail", driver);

			test.get().log(LogStatus.FAIL, test.get().addScreenCapture(path) + result.getName() + "failed");

		}

		extent.endTest(test.get());

		// Quit the WebDriver instance after each test
//        driverThread.get().quit();
	}

	@AfterClass
	public void afterClass() {
    	driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}

	// Method to get the ExtentTest instance
	public ExtentTest getExtentTest() {
		return test.get();
	}

	// Method to get the WebDriver instance
//    public WebDriver getDriver() {
//        return driverThread.get();
//    }
}
