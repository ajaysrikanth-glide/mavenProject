package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumWrappers.wrappers;

public class HomePage_Dashboard extends wrappers {

	WebDriver driver;

	public HomePage_Dashboard(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[text()='Leads']")
	WebElement leadsLink;

	@FindBy(xpath = "//*[text()='Renewals']")
	WebElement renewalsLink;

	@FindBy(xpath = "//*[text()='Claims']")
	WebElement claimsLink;

	@FindBy(xpath = "//*[text()='Calendar']")
	WebElement calendarLink;

	@FindBy(xpath = "//*[text()='Business Owner Performance']")
	WebElement businessOwnerPerformanceLink;

	@FindBy(xpath = "//*[text()='Clients']")
	WebElement clientsLink;

	@FindBy(xpath = "//*[text()='Business']")
	WebElement businessLink;
	
	@FindBy(xpath = "//*[text()='POSP']")
	WebElement pospLink;
	

	public boolean clickLeadsLink() {

		try {

			click(leadsLink, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("leads"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickRenewalsLink() {

		try {

			click(renewalsLink, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("Renewals"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickClaimsLink() {

		try {

			click(claimsLink, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("claims"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickCalendarLink() {

		try {
			
			Thread.sleep(2500);	
			
			waitElement(driver, calendarLink);

//			click(calendarLink, driver);
			calendarLink.click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("calendar"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickBusinessOwnerPerformanceLink() {

		try {
			
			Thread.sleep(2500);	
			
			waitElement(driver, businessOwnerPerformanceLink);

			businessOwnerPerformanceLink.click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("business-owner-perfomance"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickClientsLink() {

		try {
			
			Thread.sleep(2500);	
			
			waitElement(driver, clientsLink);

			clientsLink.click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("clients"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickPOSPLink() {

		try {

			Thread.sleep(2500);	
			
			waitElement(driver, pospLink);

			pospLink.click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("posp"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public boolean clickBusinessLink() {

		try {
			
			Thread.sleep(2500);

			click(businessLink, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("Business"));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
}
