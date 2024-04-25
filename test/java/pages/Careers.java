package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import seleniumWrappers.wrappers;
import utilities.Screenshot;

public class Careers extends wrappers {

	WebDriver driver;

	public Careers(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='accordionH1']/div/h2/button")
	List<WebElement> openingPositions;

	@FindBy(xpath = "//button[text()='Senior Insurance Expert']")
	WebElement seniorInsuranceExpert;

	@FindBy(xpath = "//button[text()='Corporate Sales Manager']")
	WebElement corporateSalesManager;

	@FindBy(xpath = "//button[text()='Expert Advisor – Retail Insurance (Motor / Health / Travel / Life)']")
	WebElement expertAdvisorRetailInsurance;

	@FindBy(xpath = "//button[text()='Motor Insurance Underwriter']")
	WebElement motorInsuranceUnderwriter;

	@FindBy(xpath = "//*[@id='h1collapseOne']/div/div/div/button")
	WebElement seniorInsuranceExpertApplyButton;

	@FindBy(xpath = "//*[@id='h1collapseTwo']/div/div/div/button")
	WebElement corporateSalesManagerApplyButton;

	@FindBy(xpath = "//*[@id='h1collapseThree']/div/div/div/button")
	WebElement expertAdvisorRetailInsuranceApplyButton;

	@FindBy(xpath = "//*[@id='h1collapseFour']/div/div/div/button")
	WebElement motorInsuranceUnderwriterApplyButton;

	@FindBy(xpath = "//*[@class='modal-title h4']//strong")
	WebElement assertPositionText;

	@FindBy(xpath = "//input[@id='name']")
	WebElement InputName;

	@FindBy(xpath = "//input[@id='phone']")
	WebElement InputPhone;

	@FindBy(xpath = "//input[@id='email']")
	WebElement InputEmail;

	@FindBy(xpath = "//input[@id='attachment']")
	WebElement UploadResume;

	@FindBy(xpath = "//button[text()='Submit']")
	WebElement SubmitButton;

	@FindBy(xpath = "//div[contains(text(),'Form is Submitted')]")
	WebElement submittedSuccessfully;

//	@FindBy(xpath = "/html/body/div[2]/div/div/div[1]/div[2]")
//	WebElement position2submittedSuccessfully;
//	
//	@FindBy(xpath = "(//div[text()='Form is Submitted successfully'])[3]")
//	WebElement position3submittedSuccessfully;
//	
//	@FindBy(xpath = "(//div[text()='Form is Submitted successfully'])[4]")
//	WebElement position4submittedSuccessfully;

	@FindBy(xpath = "//button[@class='btn-close']")
	WebElement closeButton;
	
	@FindBy(xpath = "//*[@class='text-success']")
	WebElement invalidMessage;
	
	Screenshot scrShot = new Screenshot();

	public void clickCareersTab() {

		BasePage basePage = new BasePage(driver);
		
		basePage.clickCareersLink();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.urlContains("careers"));
		
		Assert.assertEquals(driver.getCurrentUrl().endsWith("careers"), true);
	}

	public boolean selectAndFillOpeningPosition(String vaccancy, String name, String phone, String email,
			String fileLocation) throws InterruptedException {

		try {

			if (vaccancy.equalsIgnoreCase("Senior Insurance Expert")) {

				System.out.println("Not clicking :" + seniorInsuranceExpert.getText());

				Thread.sleep(2000);

				click(seniorInsuranceExpertApplyButton, driver);

				Thread.sleep(2000);

				Assert.assertEquals(assertPositionText.getText(), "Application for Senior Insurance Expert");

			} else if (vaccancy.equalsIgnoreCase("Corporate Sales Manager")) {

				click(corporateSalesManager, driver);

				Thread.sleep(2000);

				click(corporateSalesManagerApplyButton, driver);

				Thread.sleep(2000);

				Assert.assertEquals(assertPositionText.getText(), "Application for Corporate Sales Manager");

			} else if (vaccancy.equalsIgnoreCase("Expert Advisor")) {

				click(expertAdvisorRetailInsurance, driver);

				Thread.sleep(2000);

				click(expertAdvisorRetailInsuranceApplyButton, driver);

				Thread.sleep(2000);

				Assert.assertEquals(assertPositionText.getText(), "Application for Expert Advisor – Retail Insurance");

			} else if (vaccancy.equalsIgnoreCase("Motor Insurance Underwriter")) {

				click(motorInsuranceUnderwriter, driver);

				Thread.sleep(2000);

				click(motorInsuranceUnderwriterApplyButton, driver);

				Thread.sleep(2000);

				Assert.assertEquals(assertPositionText.getText(), "Application for Motor Insurance Underwriter");

			} 
			
			Thread.sleep(2000);

			InputName.sendKeys(name);

			InputPhone.sendKeys(phone);

			InputEmail.sendKeys(email);

			if (!fileLocation.isEmpty()) {

				UploadResume.sendKeys(fileLocation);
			}

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public boolean submitPosition() {

		try {

			click(SubmitButton, driver);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public void closeApply() {

		closeButton.click();
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
