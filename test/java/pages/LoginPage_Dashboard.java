package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Screenshot;

public class LoginPage_Dashboard {

	WebDriver driver;

	public LoginPage_Dashboard(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@type='username']")
	WebElement userNameInput;

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordInput;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	public WebElement loginButton;
	
	@FindBy(xpath = "//a[contains(text(),'Forgot password')]")
	WebElement forgotPassword;

	@FindBy(xpath = "//a[contains(@href,'signup')]")
	WebElement signUpLink;
	
	@FindBy(xpath="//*[contains(@class,'LoginPage_errorMsg')]")
	WebElement invalidMessage;
	
	Screenshot scrShot = new Screenshot();
	

	public boolean redirectLoginPage() {

		try {

			driver.get("http://localhost:3000");

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			wait.until(ExpectedConditions.elementToBeClickable(loginButton));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickSignUpLink() {
		
		try {

			signUpLink.click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			wait.until(ExpectedConditions.urlContains("signup"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public boolean loginUser(String userName, String password) throws InterruptedException {

		try {
			
			Thread.sleep(2000);
			
			if(!userName.isBlank()||!userName.isEmpty()) {
				
				userNameInput.sendKeys(userName);
			}

			if(!password.isBlank()||!password.isEmpty()) {
				
				passwordInput.sendKeys(password);
			}
			
			loginButton.click();
			
			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public String verifyLogin() {

		try {			
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(
					ExpectedConditions.urlContains("calendar")
//					,ExpectedConditions.visibilityOfElementLocated(By.xpath("//p"))
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

//		return false;

	}

}
