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

public class ForOrganisations extends wrappers {

	WebDriver driver;

	public ForOrganisations(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[contains(text(),'insurance effortless for you')]")
	WebElement caption;

	@FindBy(xpath = "//*[text()='Guidance']")
	WebElement guidance;

	@FindBy(xpath = "//*[text()='Guidance']/parent::div//a")
	WebElement guidanceLearnMoreLink;

	@FindBy(xpath = "//*[text()='Selection']")
	WebElement selection;

	@FindBy(xpath = "//*[text()='Selection']/parent::div//a")
	WebElement selectionLearnMoreLink;

	@FindBy(xpath = "//*[text()='Service']")
	WebElement service;

	@FindBy(xpath = "//*[text()='Service']/parent::div//a")
	WebElement serviceLearnMoreLink;

//	@FindBy(xpath = "//*[text()=' Industries We Serve']")
//	public WebElement industriesWeServeText;
	
	@FindBy(xpath = "//a[contains(text(),'PRODUCTS')]")
	WebElement all_Product_Details_Button;
	
	@FindBy(xpath = "//*[contains(@class,'section-content')]//h4")
	List<WebElement> all_Product_Headings;
	
	@FindBy(xpath = "//*[@class='modal-header']//strong")
	WebElement assertPolicy;

	@FindBy(xpath = "//*[contains(@class,'content it')]")
	WebElement it;

	@FindBy(xpath = "//*[contains(@class,'content construction')]/parent::a")
	WebElement construction;

	@FindBy(xpath = "//*[contains(@class,'content cement')]")
	WebElement cement;

	@FindBy(xpath = "//*[contains(@class,'content pharma')]")
	WebElement pharma;

	@FindBy(xpath = "//*[contains(@class,'content education')]/parent::a")
	WebElement education;

	@FindBy(xpath = "//*[contains(@class,'content hospitals')]")
	WebElement hospitals;

	@FindBy(xpath = "//*[contains(@class,'content fertilizers')]")
	WebElement fertilizers;

	@FindBy(xpath = "//*[contains(@class,'content shipment')]")
	WebElement shipment;

	@FindBy(xpath = "//*[contains(@class,'content media')]")
	WebElement media;

	@FindBy(xpath = "//*[contains(@class,'content renewable')]/parent::a")
	WebElement renewable;

	@FindBy(xpath = "//*[contains(@class,'content pvtjets')]")
	WebElement pvtJets;

	@FindBy(xpath = "//*[contains(@class,'content manufacturing')]")
	WebElement manufacturing;

	@FindBy(xpath = "//*[contains(@class,'content malls')]")
	WebElement malls;

	@FindBy(xpath = "//*[contains(@class,'content others')]/parent::a")
	WebElement others;

	@FindBy(xpath = "//*[text()='GET A CALLBACK']")
	WebElement getCallBackButton;
	
	@FindBy(xpath ="//*[text()='TALK TO US']")
	WebElement talkToUsButton;

	@FindBy(xpath = "//*[@id='callbackModal']//strong")
	WebElement letUsReachOutText;

	@FindBy(xpath = "//*[@id='name']")
	WebElement inputName;

	@FindBy(xpath = "//*[@id='phone']")
	WebElement inputPhone;

	@FindBy(xpath = "//*[@id='company']")
	WebElement inputCompany;

	@FindBy(xpath = "//*[@id='message']")
	WebElement inputMessage;

	@FindBy(xpath = "//input[@id='date']")
	public WebElement inputDate;

	@FindBy(xpath = "//input[@id='time']")
	public WebElement inputTime;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//div[contains(text(),'Submitted successfully')]")
	WebElement submittedSuccessfully;

	@FindBy(xpath = "//button[@class='btn-close']")
	WebElement closeButton;

	@FindBy(tagName = "h2")
	List<WebElement> h2;
	
	@FindBy(xpath = "//*[@class='text-success']")
	WebElement invalidMessage;
	
	Screenshot scrShot = new Screenshot();
	
	BasePage basePage;

//	String url="file:///C:/Users/Ajay%20Srikanth/Downloads/build-20230718%20(1)/build-20230718/organisations.html";

	public boolean clickForOrganisationsTab() {

		try {

			BasePage basePage = new BasePage(driver);

			basePage.clickForOrganisationsLink();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains(".com"));

			Assert.assertEquals(driver.getCurrentUrl().endsWith(".com"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public void printHeaders() {

		for (WebElement h : h2) {

			System.out.println(h.getText());
		}
	}

	public boolean verifyGuidance() throws InterruptedException {

		try {

			jsMoveToElement(driver, all_Product_Details_Button);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			wait.until(ExpectedConditions.textToBePresentInElement(guidance, "Guidance"));

			boolean status = guidance.getText().equalsIgnoreCase("Guidance");

			return status;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickGuidanceLearnMore() {

		try {

			click(guidanceLearnMoreLink, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("guidance"));

			Assert.assertEquals(driver.getCurrentUrl().endsWith("guidance"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean verifySelection() throws InterruptedException {

		try {

			jsMoveToElement(driver, all_Product_Details_Button);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			wait.until(ExpectedConditions.textToBePresentInElement(selection, "Selection"));

			boolean status = selection.getText().equalsIgnoreCase("Selection");

			return status;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickSelectionLearnMore() {

		try {

			click(selectionLearnMoreLink, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("selection"));

			Assert.assertEquals(driver.getCurrentUrl().endsWith("selection"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public boolean verifyService() throws InterruptedException {

		try {

			jsMoveToElement(driver, all_Product_Details_Button);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			wait.until(ExpectedConditions.textToBePresentInElement(service, "Service"));

			boolean status = service.getText().equalsIgnoreCase("Service");

			return status;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickServiceLearnMore() {

		try {

			click(serviceLearnMoreLink, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("services"));

			Assert.assertEquals(driver.getCurrentUrl().endsWith("services"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickIT() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(it, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("it"));

			driver.getCurrentUrl().endsWith("it");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickConstruction() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(construction, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("construction"));

			driver.getCurrentUrl().endsWith("construction");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickCement() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(cement, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("cement"));

			driver.getCurrentUrl().endsWith("cement");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickPharma() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(pharma, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("pharma"));

			driver.getCurrentUrl().endsWith("pharma");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickEducation() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(education, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("education"));

			driver.getCurrentUrl().endsWith("education");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickHospitals() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(hospitals, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("hospitals"));

			driver.getCurrentUrl().endsWith("hospitals");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickFertilizers() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(fertilizers, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("fertilizers"));

			driver.getCurrentUrl().endsWith("fertilizers");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickShipment() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(shipment, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("shipment"));

			driver.getCurrentUrl().endsWith("shipment");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickMedia() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(media, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("media"));

			driver.getCurrentUrl().endsWith("media");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickRenewable() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(renewable, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("renewable"));

			driver.getCurrentUrl().endsWith("renewable");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickPvtJets() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(pvtJets, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("pvtjets"));

			driver.getCurrentUrl().endsWith("pvtjets");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickManufacturing() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(manufacturing, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("manufacturing"));

			driver.getCurrentUrl().endsWith("manufacturing");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickMalls() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(malls, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("malls"));

			driver.getCurrentUrl().endsWith("malls");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickOthers() {

		try {
			
			jsMoveToElement(driver,all_Product_Details_Button);

			click(others, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("others"));

			driver.getCurrentUrl().endsWith("others");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickAllProductDetailsButton() {
		
		try {
			
			click(all_Product_Details_Button, driver);
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10000));
			
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
			
//			scrollBy(driver);
//			
//			Thread.sleep(2000);
			
			for(WebElement heading:all_Product_Headings) {
				
//				Thread.sleep(1000);			
				
				String header=heading.getText();
				
				System.out.println("Header current : "+header);
				
				if(header.trim().equalsIgnoreCase(policyName.trim())) {
					
					click(heading.findElement(By.xpath("./parent::div//button")), driver);
					
					continue;
				}		
				
			}
			
//			Thread.sleep(2000);
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			
			wait.until(ExpectedConditions.visibilityOf(assertPolicy));
			
			if(assertPolicy.getText().trim().equalsIgnoreCase(assertPolicyName.trim())) {
				
				WebElement talkToUs=assertPolicy.findElement(By.xpath("./ancestor::div[@class='modal-content']//*[contains(text(),'TALK TO US')]"));
			
				click(talkToUs,driver);
				
			}
						
			Thread.sleep(2000);
				
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}

	public boolean clickGetCallBack() throws InterruptedException {

		try {

			Thread.sleep(2000);

			click(getCallBackButton, driver);

			Thread.sleep(2000);

			Assert.assertEquals(letUsReachOutText.getText(), "GET A CALLBACK");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickTalkToUS() throws InterruptedException {

		try {

			Thread.sleep(2000);

			click(talkToUsButton, driver);

			Thread.sleep(2000);

			Assert.assertEquals(letUsReachOutText.getText(), "GET A CALLBACK");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean fillContactUs(String name, String phone, String company, String Message) {

		try {

			inputName.sendKeys(name);

//			Assert.assertEquals(inputName.getAttribute("value"), name);

			inputPhone.sendKeys(phone);

			inputCompany.sendKeys(company);

			inputMessage.sendKeys(Message);

//			Assert.assertEquals(inputPhone.getAttribute("value"), phone);

//			if (!date.isEmpty() || !date.isBlank()) {
//
//				String formattedDate = dateFormatter(date);
//
//				inputDate.sendKeys(formattedDate);
//
////				setAttributeValue(driver, inputDate, "value", formattedDate);
//
////				inputDate.sendKeys(date);
//
//				System.out.println(formattedDate + " : " + inputDate.getAttribute("value"));
//			}

//			if (!time.isEmpty() || !time.isBlank()) {
//
//				String formattedTime = timeFormatter(time);
//
////			System.out.println(date +" : "+formattedTime);
//				inputTime.sendKeys(formattedTime);
//
//				System.out.println(formattedTime + " : " + inputTime.getAttribute("value"));
//
//			}
//			scrollBy(driver);

//			System.out.println(inputDate.getAttribute("value") +" : "+inputTime.getAttribute("value"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean submitContactUs() {

		try {

//			Thread.sleep(5000);

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
