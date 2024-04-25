package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import seleniumWrappers.wrappers;
import utilities.Screenshot;

public class ContactUs extends wrappers {
	
	WebDriver driver;
	
	public ContactUs(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[text()='Connect with us today!']")
	WebElement connectWithUsHeading;
	
	@FindBy(xpath = "//*[@id='name']")
	public WebElement inputName;
	
	@FindBy(xpath = "//*[@id='phone']")
	public WebElement inputPhone;
	
	@FindBy(xpath = "//*[@id='email']")
	public WebElement inputEmail;
		
	@FindBy(xpath = "//*[@id='message']")
	public WebElement inputMessage;
	
	@FindBy(xpath = "//*[contains(text(),'Submitted successfully')]")
	WebElement submittedSuccessfully;
	
	@FindBy(xpath = "//*[text()='Phone number should be valid']")
	WebElement invalidPhone;
	
	@FindBy(xpath = "//*[text()='Name cannot start with an underscore']")
	WebElement invalidName;
	
//	//*[@id="4"]/div[1]/div[2]

	@FindBy(xpath = "//button[text()='Submit']")
	WebElement submitButton;
	
	@FindBy(xpath = "//*[@class='text-success']")
	WebElement invalidMessage;
	
	Screenshot scrShot = new Screenshot();
	
	public boolean clickContactUsTab() throws InterruptedException {
		
		try {
			
			BasePage basePage=new BasePage(driver);
			
			basePage.clickContactUsLink();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOf(connectWithUsHeading));
			
			Assert.assertTrue(connectWithUsHeading.getText().trim().equalsIgnoreCase("Connect with us today!"));
			
			return true;
			
		}	catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}	
	}
	
	public boolean fillContactUs(String name, String phone, String email, String message) {
		
		try {
			
				
			inputName.sendKeys(name);
			
			inputPhone.sendKeys(phone);
			
			inputEmail.sendKeys(email);
			
			inputMessage.sendKeys(message);			
					
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	public boolean submitContactUs() {
		
		try {
			
//			click(submitButton, driver);
			
//			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
//			
//			wait.until(ExpectedConditions.elementToBeClickable(submitButton));
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
//			submitButton.click();
			js.executeScript("arguments[0].click();", submitButton);
			
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
