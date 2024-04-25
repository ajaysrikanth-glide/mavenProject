package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import seleniumWrappers.wrappers;
import utilities.Screenshot;

public class HomePage extends wrappers{
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[text()='GET A CALLBACK']")
	WebElement callBackButton;

	@FindBy(xpath = "//strong[text()='GET A CALLBACK']")
	WebElement getCallBackText;

	@FindBy(xpath = "//input[@id='name']")
	WebElement inputName;

	@FindBy(xpath = "//input[@id='phone']")
	WebElement inputPhone;

	@FindBy(xpath = "//input[@id='email']")
	WebElement inputEmail;

	@FindBy(xpath = "//*[@id='message']")
	WebElement inputMessage;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//div[contains(text(),'Form is Submitted')]")
	WebElement submittedSuccessfully;

	@FindBy(xpath = "//button[@class='btn-close']")
	WebElement closeButton;
	
	@FindBy(xpath = "//*[@class='text-success']")
	WebElement invalidMessage;
	
	Screenshot scrShot = new Screenshot();
	
	public void assertHomePageTitle() {
				
		String title=driver.getTitle();
		
		Assert.assertEquals("Glide Insurance", title, "Home page title not matched :"+title);
	}
	
	public void clickGetCallBack() throws InterruptedException {

		Thread.sleep(2000);

		click(callBackButton, driver);

		Thread.sleep(2000);

		Assert.assertEquals(getCallBackText.getText(), "GET A CALLBACK");

	}
	
	public void closeGetACallBack() {

		closeButton.click();
	}

	public boolean fillGetACallBack(String name, String phone, String email, String message) {

		try {

			inputName.sendKeys(name);

			inputPhone.sendKeys(phone);

			inputEmail.sendKeys(email);

			inputMessage.sendKeys(message);
			
			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public boolean submitGetACallBack() {

		try {

			click(submitButton, driver);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public String verifySuccessful() throws InterruptedException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOf(submittedSuccessfully));

			return "";

		} catch (Exception e) {

			e.printStackTrace();

			if (invalidMessage.isDisplayed()) {
				
				String path = scrShot.captureScreenshot(this.getClass().getSimpleName()+"_Invalid Details", driver);
				
				return path;
			}
		}

		return null;

	}
}
