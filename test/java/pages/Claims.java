package pages;

import java.text.ParseException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import seleniumWrappers.wrappers;
import utilities.Screenshot;

public class Claims extends wrappers {

	WebDriver driver;

	public Claims(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[text()='INITIATE CLAIM']")
	List<WebElement> initiateClaimsButton;

	@FindBy(xpath = "//*[text()='Motor']")
	WebElement motor;

	@FindBy(xpath = "//*[text()='Health']")
	WebElement health;

	@FindBy(xpath = "//*[text()='Travel']")
	WebElement travel;

	@FindBy(xpath = "//*[text()='Commercial']")
	WebElement commercial;

	@FindBy(xpath = "//*[text()='Claim Intimation']")
	WebElement claim_Intimation_Button;

	@FindBy(xpath = "//*[@name='name']")
	WebElement name;

	@FindBy(xpath = "//*[@name='policyNumber']")
	WebElement policyNumber;

	@FindBy(xpath = "//*[@name='phone']")
	WebElement phone;

	@FindBy(xpath = "//*[@name='email']")
	WebElement email;

	@FindBy(xpath = "//*[@name='messageMail']")
	WebElement mailingAddress;

	@FindBy(xpath = "//*[text()='Select A Policy']")
	WebElement policyDropDown;

	// Motor form fields
	@FindBy(xpath = "//*[@id='vehicle']")
	WebElement vehicleNameMotor;

	@FindBy(xpath = "//*[@id='policyName']")
	WebElement policyHolderNameMotor;

	@FindBy(xpath = "//*[@id='registrationNumber']")
	WebElement registrationNumberMotor;

	@FindBy(xpath = "//*[@id='driverName']")
	WebElement driverNameMotor;

	@FindBy(xpath = "//*[@id='licenseNumber']")
	WebElement licenseNumberMotor;

	@FindBy(xpath = "//*[@id='dateMotor']")
	WebElement accidentDateMotor;

	@FindBy(xpath = "//*[@id='timeMotor']")
	WebElement accidentTimeMotor;

	@FindBy(xpath = "//*[@id='accidentLocation']")
	WebElement accidentLocationMotor;

	@FindBy(xpath = "//*[@id='causeOfAccident']")
	WebElement causeOfAccidentMotor;

	@FindBy(xpath = "//*[@id='workShopDetails']")
	WebElement workshopDetailsMotor;

	@FindBy(xpath = "//*[@name='phoneForm']")
	WebElement phoneNumberMotor;

	@FindBy(xpath = "//*[@id='emailForm']")
	WebElement emailIdMotor;
	
	@FindBy(xpath = "//*[@id='insuranceCompanyNameMotor']")
	WebElement insuranceCompanyMotor;
	
	// Health form fields
	
	@FindBy(xpath = "//*[@name='companyName']")
	WebElement companyName;
	
	@FindBy(xpath = "//*[@name='employeeName']")
	WebElement employeeName;
	
	@FindBy(xpath = "//*[@name='patientName']")
	WebElement patientName;
	
	@FindBy(xpath = "//*[@name='relationship']")
	WebElement relationship;
	
	@FindBy(xpath = "//*[@name='employeeID']")
	WebElement employeeID;
	
	@FindBy(xpath = "//*[@name='uhid']")
	WebElement uhidCard;
	
	@FindBy(xpath = "//*[@name='dateOfAdmission']")
	WebElement dateOfAdmission;
	
	@FindBy(xpath = "//*[@name='reasonForDiagnosis']")
	WebElement reasonForDiagnosis;
	
	@FindBy(xpath = "//*[@name='nameOfHosiptal']")
	WebElement nameOfHospital;
	
	@FindBy(xpath = "//*[@name='detailsAddressOfHospital']")
	WebElement hospitalAddress;
	

	// Travel form fields

	@FindBy(xpath = "//*[@id='incidentType']")
	WebElement incidentTypeDropDownTravel;

	@FindBy(xpath = "//*[@id='dateIncident']")
	WebElement incidentDateTravel;

	@FindBy(xpath = "//*[@id='timeIncident']")
	WebElement incidentTimeTravel;

	@FindBy(xpath = "//*[@id='incidentLocation']")
	WebElement incidentLocationTravel;

	@FindBy(xpath = "//*[@id='attachmentMed']")
	WebElement attachMedicalRecordTravel;

	@FindBy(xpath = "//*[@id='attachmentPolicy']")
	WebElement attachPolicyReportTravel;

	@FindBy(xpath = "//*[@id='attachmentReceipts']")
	WebElement attachReceiptsTravel;

	@FindBy(xpath = "//*[@id='attachmentOther']")
	WebElement attachProofsTravel;

	@FindBy(xpath = "//*[@id='IncedentDescription']")
	WebElement incidentDescriptionTravel;

	@FindBy(xpath = "//*[@name='phoneTravel']")
	WebElement phoneNumberTravel;

	@FindBy(xpath = "//*[@id='emailTravel']")
	WebElement emailIdTravel;
	
	@FindBy(xpath = "//*[@id='insuranceCompanyNameTravel']")
	WebElement insuranceCompanyTravel;

	// Commercial form fields

	@FindBy(xpath = "//*[@id='dateLoss']")
	WebElement dateOfLossCommercial;

	@FindBy(xpath = "//*[@id='timeLoss']")
	WebElement timeOfLossCommercial;

	@FindBy(xpath = "//*[@id='accidentLocationLoss']")
	WebElement lossLocationCommercial;

	@FindBy(xpath = "//*[@name='phoneFormCommercial']")
	WebElement phoneNumberCommercial;

	@FindBy(xpath = "//*[@id='emailFormCommercial']")
	WebElement emailIdCommercial;
	
	@FindBy(xpath = "//*[@id='insuranceCompanyName']")
	WebElement insuranceCompanyNameCommercial;

	@FindBy(xpath = "//*[@id='occurrenceCommercial']")
	WebElement incidentDetailsCommercial;

	@FindBy(xpath = "//*[@id='detailsDamaged']")
	WebElement damagedItemsCommercial;

	@FindBy(xpath = "//*[@id='problemCause']")
	WebElement probableCauseCommercial;

	@FindBy(xpath = "//*[@id='damageThirdParty']")
	WebElement damageThirdPartyCommercial;

	@FindBy(xpath = "//*[@id='reportAuth']")
	WebElement otherLossDetailsCommercial;

	@FindBy(xpath = "//*[@id='estimatedLoss']")
	WebElement estimateLossAmountCommercial;

	@FindBy(xpath = "//button[text()='Submit']")
	WebElement submitButton;

	@FindBy(xpath = "//*[contains(text(),'Submitted successfully')]")
	WebElement submittedSuccessfully;

	@FindBy(xpath = "//*[@class='text-success']")
	WebElement invalidMessage;

	Screenshot scrShot = new Screenshot();

	public boolean clickClaimsTab() {

		try {

			BasePage basePage = new BasePage(driver);

			basePage.clickClaims();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("claims"));

			Assert.assertEquals(driver.getCurrentUrl().endsWith("claims"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickInitiateClaim() throws InterruptedException {

		try {

			click(initiateClaimsButton.get(0), driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("Intimation"));

			Assert.assertEquals(driver.getCurrentUrl().contains("Intimation"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public boolean clickMotor() {

		try {

			click(motor, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOf(claim_Intimation_Button));

//			Assert.assertEquals(driver.getCurrentUrl().endsWith("Intimation"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickHealth() {

		try {

			click(health, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOf(claim_Intimation_Button));

//			Assert.assertEquals(driver.getCurrentUrl().endsWith("Intimation"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickTravel() {

		try {

			click(travel, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOf(claim_Intimation_Button));

//			Assert.assertEquals(driver.getCurrentUrl().endsWith("Intimation"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickCommercial() {

		try {

			click(commercial, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.visibilityOf(claim_Intimation_Button));

//			Assert.assertEquals(driver.getCurrentUrl().endsWith("Intimation"), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean clickClaimIntimationAndVerify() {

		try {

			click(claim_Intimation_Button, driver);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.urlContains("Intimation"));

			Assert.assertEquals(driver.getCurrentUrl().endsWith("Intimation"), true);

			Thread.sleep(2000);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean redirectBackClaimsPage() {

		try {

			redirectBack(driver, "claims");

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean selectPolicyDropDown(String value) {

		try {

//			Select s = new Select(policyDropDown);

//			s.selectByValue(value);

			click(policyDropDown, driver);
//			policyDropDown.click();

			Actions keyDown = new Actions(driver);

			if (value.equals("Motor")) {

				keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.ENTER)).perform();

			} else if (value.equals("Health")) {

				keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();

			} else if (value.equals("Travel")) {

				keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();

			} else if (value.equals("Commercial")) {

				keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
			}

			Thread.sleep(2000);

			WebElement assertText = driver.findElement(By.xpath("//*[contains(@class,'singleValue')]"));

			System.out.println("assert drop down value: " + assertText.getText());

			Assert.assertEquals(assertText.getText().trim().equalsIgnoreCase(value), true);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean fillMotor(String inputName, String inputPolicyNumber, String inputPhoneNumber, String inputEmailId,
			String inputMailingAddress, String inputVehicleName, String inputPolicyHolderName,
			String inputRegistrationNumber, String inputDriverName, String inputLicenseNumber, String accidentDate,
			String accidentTime, String inputAccidentLocation, String inputCauseofAccident, String inputWorkshopDetails,
			String inputPhoneMotor, String inputEmailMotor, String insuranceCompanyName) throws ParseException {

		try {

			name.sendKeys(inputName);

			policyNumber.sendKeys(inputPolicyNumber);

			phone.sendKeys(inputPhoneNumber);

			email.sendKeys(inputEmailId);

			mailingAddress.sendKeys(inputMailingAddress);

			vehicleNameMotor.sendKeys(inputVehicleName);

			policyHolderNameMotor.sendKeys(inputPolicyHolderName);

			registrationNumberMotor.sendKeys(inputRegistrationNumber);

			driverNameMotor.sendKeys(inputDriverName);

			licenseNumberMotor.sendKeys(inputLicenseNumber);

			if (!accidentDate.isEmpty() || !accidentDate.isBlank()) {

				String formattedDate = dateFormatter(accidentDate);

				accidentDateMotor.sendKeys(formattedDate);
			}

			if (!accidentTime.isEmpty() || !accidentTime.isBlank()) {

				String formattedTime = timeFormatter(accidentTime);

				accidentTimeMotor.sendKeys(formattedTime);

			}

			accidentLocationMotor.sendKeys(inputAccidentLocation);

			causeOfAccidentMotor.sendKeys(inputCauseofAccident);

			workshopDetailsMotor.sendKeys(inputWorkshopDetails);

			phoneNumberMotor.sendKeys(inputPhoneMotor);

			emailIdMotor.sendKeys(inputEmailMotor);
			
			insuranceCompanyMotor.sendKeys(insuranceCompanyName);

			return true;

		}

		catch (Exception e) {

			e.printStackTrace();

			return false;
		}

	}

	public boolean fillHealth(String inputName, String inputPolicyNumber, String inputPhoneNumber, String inputEmailId,
			String inputMailingAddress, String inputCompanyName, String inputEmployeeName, String inputPatientName, 
			String inputRelationship, String inputEmployeeID, String inputUHID, String inputAdmissionDate, String inputDiagnosisReason,
			String inputHospitalName, String inputHospitalAddress) {

		try {

			name.sendKeys(inputName);

			policyNumber.sendKeys(inputPolicyNumber);

			phone.sendKeys(inputPhoneNumber);

			email.sendKeys(inputEmailId);

			mailingAddress.sendKeys(inputMailingAddress);
			
			companyName.sendKeys(inputCompanyName);
			
			employeeName.sendKeys(inputEmployeeName);
			
			patientName.sendKeys(inputPatientName);
			
			relationship.sendKeys(inputRelationship);
			
			employeeID.sendKeys(inputEmployeeID);
			
			uhidCard.sendKeys(inputUHID);
			
			if(!inputAdmissionDate.isEmpty() || !inputAdmissionDate.isBlank()) {
				
//				SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/mm/yyyy");
//				Date dateInput = inputDateFormat.parse(inputAdmissionDate);
//				
//				
//				SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-mm-dd");
//				String outputDate = outputDateFormat.format(dateInput);	
//				
//				for (char c : outputDate.toCharArray()) {
//		            // Send each character
//					dateOfAdmission.sendKeys(String.valueOf(c));
//					
//		        	Thread.sleep(100);
//		        }
//				dateOfAdmission.sendKeys(outputDate);
				
				String formattedDate = dateFormatter(inputAdmissionDate);

				dateOfAdmission.sendKeys(formattedDate);
			}

			reasonForDiagnosis.sendKeys(inputDiagnosisReason);
			
			nameOfHospital.sendKeys(inputHospitalName);
			
			hospitalAddress.sendKeys(inputHospitalAddress);
			
			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;

		}
	}

	public boolean fillTravel(String inputName, String inputPolicyNumber, String inputPhoneNumber, String inputEmailId,
			String inputMailingAddress, String incidentTypeValue, String incidentDate, String incidentTime,
			String inputIncidentLocation, String fileMedicalRecord, String filePolicyReport, String fileReceipts,
			String fileProofs, String inputIncidentDescription, String inputPhoneTravel, String inputEmailTravel, String insuranceCompanyName) {

		try {

			name.sendKeys(inputName);

			policyNumber.sendKeys(inputPolicyNumber);

			phone.sendKeys(inputPhoneNumber);

			email.sendKeys(inputEmailId);

			mailingAddress.sendKeys(inputMailingAddress);

			Select incidentType = new Select(incidentTypeDropDownTravel);

//			List<WebElement> elementCount=incidentType.getOptions();
//			
////			incidentType.sele
//			
//			for(WebElement element:elementCount) {
//				
////				System.out.println("Dropdown value : "+element.getText());
//				
//				if(element.getText().toLowerCase().equals("Medical Emergencies")) {
//					
//					incidentTypeDropDownTravel.sendKeys(Keys.chord(Keys.ARROW_DOWN,Keys.ENTER));
//				}
//			}

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			wait.until(ExpectedConditions.elementToBeClickable(incidentTypeDropDownTravel));

//			WebDriverWait(driver,Duration.ofSeconds(5));

			incidentType.selectByVisibleText(incidentTypeValue);

			if (!incidentDate.isEmpty() || !incidentDate.isBlank()) {

				String formattedDate = dateFormatter(incidentDate);

				incidentDateTravel.sendKeys(formattedDate);
			}

			if (!incidentTime.isEmpty() || !incidentTime.isBlank()) {

				String formattedTime = timeFormatter(incidentTime);

				incidentTimeTravel.sendKeys(formattedTime);

			}

			incidentLocationTravel.sendKeys(inputIncidentLocation);

			if (!fileMedicalRecord.isEmpty()) {

				attachMedicalRecordTravel.sendKeys(fileMedicalRecord);
			}

			if (!filePolicyReport.isEmpty()) {

				attachPolicyReportTravel.sendKeys(filePolicyReport);
			}

			if (!fileReceipts.isEmpty()) {

				attachReceiptsTravel.sendKeys(fileReceipts);
			}

			if (!fileProofs.isEmpty()) {

				attachProofsTravel.sendKeys(fileProofs);
			}

			incidentDescriptionTravel.sendKeys(inputIncidentDescription);

			phoneNumberTravel.sendKeys(inputPhoneTravel);

			emailIdTravel.sendKeys(inputEmailTravel);
			
			insuranceCompanyTravel.sendKeys(insuranceCompanyName);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;

		}
	}

	public boolean fillCommercial(String inputName, String inputPolicyNumber, String inputPhoneNumber,
			String inputEmailId, String inputMailingAddress, String dateLoss, String timeLoss,
			String inputLocationOfLoss, String inputPhoneCommercial, String inputEmailCommercial,
			String insuranceCompanyName, String inputIncidentDetails, String inputDamagedItems, 
			String inputProbableCause, String inputThirdPartyDamage, String inputOtherLoss, 
			String inputEstimateLoss) {

		try {

			name.sendKeys(inputName);

			policyNumber.sendKeys(inputPolicyNumber);

			phone.sendKeys(inputPhoneNumber);

			email.sendKeys(inputEmailId);

			mailingAddress.sendKeys(inputMailingAddress);

			if (!dateLoss.isEmpty() || !dateLoss.isBlank()) {

				String formattedDate = dateFormatter(dateLoss);

				dateOfLossCommercial.sendKeys(formattedDate);
			}

			if (!timeLoss.isEmpty() || !timeLoss.isBlank()) {

				String formattedTime = timeFormatter(timeLoss);

				timeOfLossCommercial.sendKeys(formattedTime);

			}

			lossLocationCommercial.sendKeys(inputLocationOfLoss);

			phoneNumberCommercial.sendKeys(inputPhoneCommercial);

			emailIdCommercial.sendKeys(inputEmailCommercial);
			
			insuranceCompanyNameCommercial.sendKeys(insuranceCompanyName);

			incidentDetailsCommercial.sendKeys(inputIncidentDetails);

			damagedItemsCommercial.sendKeys(inputDamagedItems);

			probableCauseCommercial.sendKeys(inputProbableCause);

			damageThirdPartyCommercial.sendKeys(inputThirdPartyDamage);

			otherLossDetailsCommercial.sendKeys(inputOtherLoss);

			estimateLossAmountCommercial.sendKeys(inputEstimateLoss);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean submitClaimForm() {

		try {

			click(submitButton, driver);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public String verifySuccessfull() {

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
