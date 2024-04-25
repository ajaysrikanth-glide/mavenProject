package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Screenshot;

public class SignUpPage {

	WebDriver driver;

	public SignUpPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='username']")
	WebElement userNameInput;

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordInput;

	@FindBy(xpath = "//input[@type='email']")
	WebElement emailInput;

	@FindBy(xpath = "//button[text()='Sign Up']")
	WebElement signUpButton;
	
	@FindBy(xpath="//*[contains(@class,'SignupPage_errorMsg')]")
	WebElement invalidMessage;
	
	Screenshot scrShot = new Screenshot();

	public boolean SignUpUser(String userName, String email, String password) {

		try {
			
			Thread.sleep(2000);
			
			if(!userName.isBlank()||!userName.isEmpty()) {
				
				userNameInput.clear();
				
				userNameInput.sendKeys(userName);
			}
			
			if(!email.isBlank()||!email.isEmpty()) {
				
				emailInput.clear();
				
				emailInput.sendKeys(email);
			}
			
			if(!password.isBlank()||!password.isEmpty()) {
				
				passwordInput.clear();
				
				passwordInput.sendKeys(password);
			}
			

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public String verifySignUp() {

		try {
			
			signUpButton.click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(
//					ExpectedConditions.urlToBe("http://localhost:3000/")
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Login')]"))
					);

			return "";

		} catch (Exception e) {

			e.printStackTrace();

			if (invalidMessage.isDisplayed()) {
				
				String path = scrShot.captureScreenshot("Invalid Credentials", driver);

				return path;
			}

		}

		return null;

	}

}
