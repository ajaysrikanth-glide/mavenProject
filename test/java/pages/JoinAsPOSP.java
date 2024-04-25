package pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumWrappers.wrappers;

public class JoinAsPOSP extends wrappers {

	WebDriver driver;

	public JoinAsPOSP(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//*[text()='JOIN AS POSP' and @role='button'])[1]")
	WebElement joinAsPOSPButton1;

	@FindBy(xpath = "(//*[text()='JOIN AS POSP' and @role='button'])[2]")
	WebElement joinAsPOSPButton2;

	@FindBy(xpath = "//strong[text()='JOIN AS POSP']")
	WebElement joinAsPOSPText;

	@FindBy(xpath = "//*[@id='name']")
	WebElement inputName;

	@FindBy(xpath = "//*[@id='phone']")
	WebElement inputPhone;

	@FindBy(xpath = "//*[@id='email']")
	WebElement inputEmail;

	@FindBy(xpath = "//*[@id='city']")
	WebElement inputCity;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//div[contains(text(),'Form is Submitted')]")
	WebElement submittedSuccessfully;

	@FindBy(xpath = "//button[@class='btn-close']")
	WebElement closeButton;

	String currentWindow;

	public boolean clickJoinAsPOSPTab() {

		try {

			BasePage basePage = new BasePage(driver);

			basePage.clickJoinAsPOSPLink();

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickJoinAsPOSP() throws InterruptedException {

		try {

			Thread.sleep(2000);
			
			currentWindow= driver.getWindowHandle();

			click(joinAsPOSPButton2, driver);

			Thread.sleep(2000);

//			Thread.sleep(2000);
//
//			Assert.assertEquals(joinAsPOSPText.getText(), "JOIN AS POSP");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public void closeJoinUsToday() {

		closeButton.click();
	}

	public boolean fillJoinUsToday(String name, String phone, String email, String city) {

		try {
			inputName.sendKeys(name);
			inputPhone.sendKeys(phone);
			inputEmail.sendKeys(email);
			inputCity.sendKeys(city);
//			submitButton.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean submitJoinUsToday() {

		try {

			click(submitButton, driver);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean verifySuccessful() throws InterruptedException {

		try {

			Set<String> windows = driver.getWindowHandles();

			for (String window : windows) {

				if (!window.equals(currentWindow)) {

					driver.switchTo().window(window);
				}
			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.or(
			ExpectedConditions.urlContains("https://uat.iceinsurance.in/"),
			ExpectedConditions.urlContains("https://posp.glideinsurance.com/")
			));

			return true;

		} catch (Exception e) {

			e.printStackTrace();
			
			return false;

		}

	}

}
