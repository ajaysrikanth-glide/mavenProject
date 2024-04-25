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

public class ForIndividuals extends wrappers {

	WebDriver driver;

	public ForIndividuals(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[text()='Advisory Services']/parent::div//a")
	WebElement advisoryServicesLearnMore;

	@FindBy(xpath = "//*[text()='GlideOpulent Assurance']/parent::div//a")
	WebElement glideOpulentAssuranceLearnMore;

	@FindBy(xpath = "//*[text()='Glide Experience Hub']/parent::div//a")
	WebElement glideExperienceHub;

	@FindBy(xpath = "//*[text()='NON-LIFE']")
	WebElement non_Life_Button;

	@FindBy(xpath = "//*[text()='Motor']")
	WebElement motor;

	@FindBy(xpath = "//*[text()='Health']")
	WebElement health;

	@FindBy(xpath = "//*[text()='Overseas Travel']")
	WebElement overseasTravel;

	@FindBy(xpath = "//*[text()='Pet']")
	WebElement pet;

	@FindBy(xpath = "//*[text()='Home']")
	WebElement home;

	@FindBy(xpath = "//*[text()='LIFE']")
	WebElement life_Button;

	@FindBy(xpath = "//*[text()='Term Plans']")
	WebElement termPlans;

	@FindBy(xpath = "//*[text()='Wealth Plans ']")
	WebElement wealthPlans;

	@FindBy(xpath = "//*[text()='Investment Plans']")
	WebElement investmentPlans;

	@FindBy(xpath = "//*[text()='Children Plans']")
	WebElement childrenPlans;

	@FindBy(xpath = "//*[text()='Pension Plans']")
	WebElement pensionPlans;

	@FindBy(xpath = "//a[contains(text(),'PRODUCTS')]")
	WebElement all_Product_Details_Button;
	
	@FindBy(xpath = "//*[contains(@class,'section-content')]//h4")
	List<WebElement> all_Product_Headings;

	@FindBy(xpath = "//*[contains(@class,'subtab-content')]//ul//button")
	List<WebElement> slickDots;

	@FindBy(xpath = "//*[@aria-hidden='false']//h4")
	WebElement slickPolicyHeading;

	@FindBy(xpath = "//*[@class='modal-header']//strong")
	WebElement assertPolicy;

	@FindBy(xpath = "//*[text()='GET A CALLBACK']")
	WebElement getCallBackButton;

	@FindBy(xpath = "//strong[text()='GET A CALLBACK']")
	WebElement getACallBackText;

	@FindBy(xpath = "//input[@id='name']")
	WebElement inputName;

	@FindBy(xpath = "//input[@id='phone']")
	WebElement inputPhone;

	@FindBy(xpath = "//*[@id='email']")
	public WebElement inputEmail;

	@FindBy(xpath = "//*[@id='message']")
	public WebElement inputMessage;

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

	public boolean clickForIndividualsTab() {

		try {

			BasePage basePage = new BasePage(driver);

			basePage.clickForIndividualsLink();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("individuals"));

			Assert.assertEquals(driver.getCurrentUrl().endsWith("individuals"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickAdvisoryServicesLearnMore() {

		try {

			jsMoveToElement(driver, non_Life_Button);

			click(advisoryServicesLearnMore, driver);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickGlideOpulentAssuranceLearnMore() {

		try {

			jsMoveToElement(driver, non_Life_Button);

			click(glideOpulentAssuranceLearnMore, driver);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickGlideExperienceHubLearnMore() {

		try {
			click(glideExperienceHub, driver);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickCategory(String category) {

		try {

			if (category.equals("Motor")) {

				click(motor, driver);

			} else if (category.equals("Health")) {

				click(health, driver);

			} else if (category.equals("Overseas Travel")) {

				click(overseasTravel, driver);

			} else if (category.equals("Pet")) {

				click(pet, driver);

			} else if (category.equals("Home")) {

				click(home, driver);

			} else if (category.equals("Term Plans")) {

				click(termPlans, driver);

			} else if (category.equals("Wealth Plans")) {

				click(wealthPlans, driver);

			} else if (category.equals("Investment Plans")) {

				click(investmentPlans, driver);

			} else if (category.equals("Children Plans")) {

				click(childrenPlans, driver);

			} else if (category.equals("Pension Plans")) {

				click(pensionPlans, driver);

			}

			Thread.sleep(2000);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean selectAndClickPolicyReadMore(String policyName) {

//		//*[@aria-hidden='false']//h4/parent::div//button
//		//*[@aria-hidden='false']//h4/parent::div//a
		try {

			if(slickDots.size()!=0) {
				
				for (WebElement slick : slickDots) {

					click(slick, driver);

					Thread.sleep(3000);

					if (slickPolicyHeading.getText().trim().toLowerCase().contains(policyName.toLowerCase().trim())) {

						WebElement readMore = slickPolicyHeading.findElement(By.xpath("./parent::div//button"));

						click(readMore, driver);

					}

				}
			} else if(slickPolicyHeading.getText().trim().toLowerCase().contains(policyName.toLowerCase().trim())) {
				
				WebElement readMore = slickPolicyHeading.findElement(By.xpath("./parent::div//button"));

				click(readMore, driver);
			}

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;

		}
	}

	public boolean verifyAndClickCallBack(String assertPolicyName) {

//		//*[@class='modal-header']//strong/ancestor::div[@class='modal-content']//*[contains(text(),'TALK TO US')]
		try {

			Thread.sleep(2000);

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

	public boolean clickGetCallBack() throws InterruptedException {

		try {

//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//			wait.until(ExpectedConditions.elementToBeClickable(getCallBackButton));

			Thread.sleep(2000);

			click(getCallBackButton, driver);

//			wait.until(ExpectedConditions.visibilityOf(getACallBackText));

			Thread.sleep(2000);

			Assert.assertEquals(getACallBackText.getText(), "GET A CALLBACK");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean fillGetACallBack(String name, String phone, String email, String message) {

		try {

			inputName.sendKeys(name);

//			Assert.assertEquals(inputName.getAttribute("value"), name);

			inputPhone.sendKeys(phone);

//			Assert.assertEquals(inputPhone.getAttribute("value"), phone);

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

//			Thread.sleep(2000);

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

	public boolean clickAllProductDetailsButton() {

		try {

			click(all_Product_Details_Button, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));

			wait.until(ExpectedConditions.urlContains("products"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public boolean clickAllProductsPolicy(String policyName, String assertPolicyName) {

		try {

			basePage = new BasePage(driver);

			Thread.sleep(2000);

			jsMoveToElement(driver, basePage.contactPhoneLink);

			Thread.sleep(5000);

//		scrollBy(driver);
//		
//		Thread.sleep(2000);

			for (WebElement heading : all_Product_Headings) {

//			Thread.sleep(1000);			

				String header = heading.getText();

				System.out.println("Header current : " + header);

				if (header.trim().equalsIgnoreCase(policyName.trim())) {

					click(heading.findElement(By.xpath("./parent::div//button")), driver);

					continue;
				}

			}

//		Thread.sleep(2000);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOf(assertPolicy));

			if (assertPolicy.getText().trim().equalsIgnoreCase(assertPolicyName.trim())) {

				WebElement talkToUs = assertPolicy.findElement(
						By.xpath("./ancestor::div[@class='modal-content']//*[contains(text(),'TALK TO US')]"));

				click(talkToUs, driver);

			}

			Thread.sleep(2000);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

}
