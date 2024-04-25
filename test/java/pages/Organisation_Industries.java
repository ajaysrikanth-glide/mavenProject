package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import seleniumWrappers.wrappers;
import utilities.Screenshot;

public class Organisation_Industries extends wrappers {

	WebDriver driver;

	public Organisation_Industries(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[contains(@class,'aos-init')]//h4")
	List<WebElement> policies;

	@FindBy(xpath = "//*[@class='modal-header']//strong")
	WebElement assertPolicy;

	@FindBy(xpath = "//*[@id='name']")
	WebElement inputName;

	@FindBy(xpath = "//*[@id='phone']")
	WebElement inputPhone;

	@FindBy(xpath = "//*[@id='company']")
	WebElement inputCompany;

	@FindBy(xpath = "//*[@id='message']")
	WebElement inputMessage;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//div[contains(text(),'Submitted successfully')]")
	WebElement submittedSuccessfully;

	@FindBy(xpath = "//button[@class='btn-close']")
	WebElement closeButton;

	@FindBy(xpath = "//*[@class='text-success']")
	WebElement invalidMessage;

	Screenshot scrShot = new Screenshot();

	BasePage basePage;

	public boolean clickPolicy(String policyName) throws InterruptedException {

		try {

			basePage = new BasePage(driver);

			jsMoveToElement(driver, basePage.contactPhoneLink);

			Thread.sleep(2000);

			for (WebElement policy : policies) {

				String header = policy.getText();

				System.out.println("Header current : " + header);

				if (policy.getText().contains(policyName)) {

					click(policy.findElement(By.xpath("./parent::div//button")), driver);

					continue;
				}
			}

			Thread.sleep(2000);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean verifyAndClickCallBack(String assertPolicyName) {

		try {

			System.out.println(assertPolicy.getText().trim().toLowerCase());

			System.out.println(assertPolicyName.trim().toLowerCase());

			assertPolicy.getText().trim().toLowerCase().contains(assertPolicyName.trim().toLowerCase());

			WebElement button = assertPolicy
					.findElement(By.xpath("./ancestor::div[@class='modal-content']//*[contains(text(),'TALK TO US')]"));

			click(button, driver);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public boolean fillContactUs(String name, String phone, String company, String Message) {

		try {

			inputName.sendKeys(name);

			Assert.assertEquals(inputName.getAttribute("value"), name);

			inputPhone.sendKeys(phone);

			inputCompany.sendKeys(company);

			inputMessage.sendKeys(Message);

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

				String path = scrShot.captureScreenshot(this.getClass().getSimpleName() + "_Invalid Details", driver);

				return path;
			}
		}

		return null;

	}

}
