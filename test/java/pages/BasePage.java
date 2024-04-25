package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumWrappers.wrappers;
import utilities.ConfigReader;

public class BasePage extends wrappers {

	String url = "";
	HttpURLConnection huc = null;
	int respCode = 200;

	WebDriver driver;

	public BasePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	ConfigReader config = new ConfigReader();

	@FindBy(tagName = "a")
	List<WebElement> links;
	
	@FindBy(xpath = "//a[text()='Home']")
	WebElement homePageLink;

	@FindBy(xpath = "//a[text()='FOR ORGANISATIONS']")
	WebElement forOrganisationsLink;

	@FindBy(xpath = "//a[text()='FOR INDIVIDUALS']")
	WebElement forIndividualsLink;

	@FindBy(xpath = "//a//*[text()='About Us']")
	WebElement aboutUsLink;

	@FindBy(xpath = "//a//*[text()='CONTACT US']")
	WebElement contactUsLink;

	@FindBy(xpath = "//a[text()='CAREERS']")
	WebElement careersLink;

	@FindBy(xpath = "//a//*[text()='JOIN AS POSP']")
	WebElement joinAsPOSPLink;

	@FindBy(xpath = "//a[text()='CLAIMS']")
	WebElement claims;

	@FindBy(xpath = "//button[text()='More']")
	WebElement moreButton;

	@FindBy(xpath = "//*[text()='+91 9502963366']")
	WebElement contactPhoneLink;

	@FindBy(xpath = "//a[text()='info@glideinsurance.com']")
	WebElement contactEmailLink;

	@FindBy(xpath = "//a[text()='+91 8884499506']")
	WebElement poPhoneLink;

	@FindBy(xpath = "//a[text()='principalofficer@glideinsurance.com']")
	WebElement poEmailLink;

	@FindBy(xpath = "//a[@aria-label='Instagram']")
	WebElement instagramLink;

	@FindBy(xpath = "//a[@aria-label='Facebook']")
	WebElement facebookLink;

	@FindBy(xpath = "//a[@aria-label='LinkedIn']")
	WebElement linkedinLink;

	@FindBy(xpath = "//a[text()='Twitter']")
	WebElement twitterLink;

	@FindBy(xpath = "//a[text()='Glossary']")
	WebElement glossaryLink;

	@FindBy(xpath = "//a[text()='Brochure']")
	WebElement downloadBroucherLink;

	@FindBy(xpath = "//a[text()='Grievance Redressal']")
	WebElement grievanceLink;

	@FindBy(xpath = "//a[text()='Privacy Policy']")
	WebElement privacyPolicyLink;

	@FindBy(xpath = "//a[text()='Terms of Use']")
	WebElement termsOfUseLink;
	
	public void clickHomePageLink() {
		
		click(homePageLink, driver);
	}

	public void clickForOrganisationsLink() {

		click(forOrganisationsLink, driver);
	}

	public void clickForIndividualsLink() {

		click(forIndividualsLink, driver);

	}

	public void clickAboutUsLink() {

		click(moreButton, driver);

		waitElement(driver, aboutUsLink);

		click(aboutUsLink, driver);
	}

	public void clickContactUsLink() {

		click(moreButton, driver);

		waitElement(driver, contactUsLink);

		click(contactUsLink, driver);
	}

	public void clickCareersLink() {

		click(careersLink, driver);
	}

	public void clickJoinAsPOSPLink() throws InterruptedException {
		
		click(moreButton, driver);

		waitElement(driver, joinAsPOSPLink);

		Thread.sleep(2000);

		click(joinAsPOSPLink, driver);

		Thread.sleep(2000);

	}

	public void clickClaims() {

		click(claims, driver);
	}

	public void clickContactPhone() {

		click(contactPhoneLink, driver);
	}

	public void clickContactEmail() {

		click(contactEmailLink, driver);
	}

	public void clickPoPhone() {

		click(poPhoneLink, driver);
	}

	public void clickPoEmail() {

		click(poEmailLink, driver);
	}

	public void clickInstagram() {

		click(instagramLink, driver);
	}

	public void clickLinkedin() {

		click(linkedinLink, driver);
	}

	public void clickFacebook() {

		click(facebookLink, driver);
	}

	public void clickTwitter() {

		click(twitterLink, driver);
	}

	public void clickGlossary() {

		click(glossaryLink, driver);
	}

	public void downloadBroucher() {

		click(downloadBroucherLink, driver);
	}

	public void downloadAndSwitchGrievanceRedressal() {

		String currentWindow = driver.getWindowHandle();
		click(grievanceLink, driver);
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!driver.getWindowHandle().equals(currentWindow)) {
				driver.switchTo().window(window);
			}
		}
		// driver.switchTo().window((driver.getWindowHandle()));
	}

	public void navigateHomePage() {
		if (!driver.getCurrentUrl().equals(config.getData("url")))
			driver.get(config.getData("url"));
	}

	public void verifyLInks() {

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			System.out.println(url);

			if (url == null || url.isEmpty()) {

				System.out.println("URL is not configured for anchor tag or it is empty");

				continue;
			}

			try {

				huc = (HttpURLConnection) (new URI(url).toURL().openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {

					System.out.println(url + " is a broken link");

				} else {
					System.out.println(url + " is a valid link");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}

	public List<List<String>> verifyLinks() throws Exception {

		List<String> emptyUrls = new ArrayList<>();
		List<String> errorUrls = new ArrayList<>();
		List<String> successUrls = new ArrayList<>();

		System.out.println("Total url size is :" + links.size());
		for (WebElement link : links) {

			url = link.getAttribute("href");

			System.out.println(url + " link text :" + link.getText());

			if (url == null || url.isEmpty()) {

				emptyUrls.add(link.toString() + " link text :" + link.getText());
			}

			if (url.startsWith("tel") || url.startsWith("mailto")) {

				continue;
			}

			try {

				huc = (HttpURLConnection) (new URI(url).toURL().openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				int statusCode = huc.getResponseCode();

				if (statusCode >= 400) {
					errorUrls.add(url + " link text :" + link.getText());
				} else {
					successUrls.add(url + " link text :" + link.getText());
				}

				huc.disconnect();
			} catch (IOException e) {
				// Handle exception if unable to connect or get status code
				e.printStackTrace();
			}
		}

		// Print and return the lists
		System.out.println("URLs with status code >= 400:");
		System.out.println("Error url size :" + errorUrls.size());
		for (String url : errorUrls) {

			System.out.println(url);
		}

		System.out.println("\nURLs with status code < 400:");
		System.out.println("Success url size :" + successUrls.size());
		for (String url : successUrls) {

			System.out.println(url);
		}
		System.out.println("\nURLs are empty fields");
		System.out.println("Empty url size :" + emptyUrls.size());
		for (String url : emptyUrls) {

			System.out.println(url);
		}

		// Return the lists
		List<List<String>> result = new ArrayList<>();
		result.add(errorUrls);
		result.add(successUrls);
		result.add(emptyUrls);
		return result;
	}

}
