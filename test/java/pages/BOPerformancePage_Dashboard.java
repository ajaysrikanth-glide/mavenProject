package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumWrappers.wrappers;

public class BOPerformancePage_Dashboard extends wrappers {

	WebDriver driver;

	public BOPerformancePage_Dashboard(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[text()='Business Owner']//preceding-sibling::div")
	WebElement businessOwner;

	@FindBy(xpath = "//*[text()='Prospects Generated']//preceding-sibling::div")
	WebElement prospectsGenerated;

	@FindBy(xpath = "//*[text()='Prospects Converted']//preceding-sibling::div")
	WebElement prospectsConverted;

	@FindBy(xpath = "//*[text()='Policy Net Premium']//preceding-sibling::div")
	WebElement policyNetPremium;

	@FindBy(xpath = "//*[text()='Commission Receivable']//preceding-sibling::div")
	WebElement commissionReceivable;

	// 0-BO name, 1- business type, 2- business value
	@FindBy(xpath = "//div[contains(@class,'tooltip-wrapper-top')]//p")
	List<WebElement> toolTipValues;

//	@FindBy(xpath = "//*[contains(@class,'recharts-bar-rectangle')]//*[local-name()='rect']")
//	List<WebElement> businessBarCharts;

	@FindBy(xpath = "(//*[contains(@id,'headlessui-listbox-button')])[1]")
	WebElement durationDropdownButton;

	@FindBy(xpath = "//input[@placeholder='Select start Date']")
	WebElement startDateInput;

	@FindBy(xpath = "//input[@placeholder='Select End Date']")
	WebElement endDateInput;

	@FindBy(xpath = "//*[text()='Business Owner']")
	WebElement businessOwnerText;

	@FindBy(xpath = "(//*[contains(@id,'headlessui-listbox-button')])[2]")
	WebElement businessDropDownButton;

	@FindBy(xpath = "//*[@role='listbox']//li/span")
	List<WebElement> dropDownValues;

	@FindBy(xpath = "//select[contains(@class,'business_owner_apexDropDown')]")
	WebElement businessOwnerDropdown;

	@FindBy(xpath = "//table[@aria-label='simple table']/tbody/tr/th")
	List<WebElement> categoryList;

	@FindBy(xpath = "//table[@aria-label='simple table']/tbody/tr[1]/td")
	List<WebElement> categoryWiseTotal;

	public boolean clickDropDownDuration(String duration, String startDate, String endDate) {

		try {

			jsMoveToElement(driver, durationDropdownButton);

			durationDropdownButton.click();

			Thread.sleep(1500);

			for (WebElement time : dropDownValues) {

				if (time.getText().equals(duration)) {

					System.out.println("Selected Dropdown value : " + duration);

					time.click();

					Thread.sleep(1000);

					if (duration.equals("Custom Range")) {

						enterCustomDateRange(startDate, endDate);

						Thread.sleep(2000);
					}

					return true;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}

		return false;

	}

	public boolean enterCustomDateRange(String startDate, String endDate) {

		try {

			System.out.println("Start Date : " + startDate);

			System.out.println("End Date : " + endDate);

			startDate = dashboardDateFormatter(startDate);

			endDate = dashboardDateFormatter(endDate);

			Thread.sleep(1000);

			enterStartDate(startDate, startDateInput);

			enterEndDate(driver, endDate, endDateInput);
//			startDateInput.sendKeys(startDate);

//			businessOwnerText.click();

//			Thread.sleep(1000);
//			startDateInput.sendKeys(Keys.RETURN);

//			endDateInput.sendKeys(endDate);

//			Thread.sleep(1000);

			// Send keys one by one
//	        for (char c : endDate.toCharArray()) {
//	            // Send each character
//	        	endDateInput.sendKeys(String.valueOf(c));
//	        	Thread.sleep(100);
//	        }

//			endDateInput.sendKeys(Keys.RETURN);

			businessOwnerText.click();

			Thread.sleep(2000);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public int getPoliciesBookedBOWise() {

		try {

			jsMoveToElement(driver, businessDropDownButton);

			businessDropDownButton.click();

			dropDownValues.get(0).click();

			int toolTipValue = 0, totalValue = 0;
			
			List<WebElement> businessBarCharts = driver.findElements(By.xpath("//*[contains(@class,'recharts-bar-rectangle')]//*[local-name()='rect']"));

			for (WebElement bar : businessBarCharts) {

				if (Double.parseDouble(bar.getAttribute("height")) > 0) {

					hover(bar, driver);

					for (int i = 0; i < toolTipValues.size(); i++) {

						if (i == 2) {

							toolTipValue = rupeeMatcher(toolTipValues.get(i));

//							System.out.println(" Policies Booked by "+toolTipValues.get(0).getText()+" : "+toolTipValue);
						}

					}

					totalValue = toolTipValue + totalValue;
				}
			}

			System.out.println("Total Policies Booked Business owner wise : " + totalValue + "\n");

			return totalValue;

		} catch (Exception e) {

			e.printStackTrace();

			return 0;
		}
	}

	public int getBusinessBookedBOWise(String business) {
		
		try {

			int toolTipValue = 0, totalValue = 0;			
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			if (business.equals("Premium Booked")) {

				jsMoveToElement(driver, businessDropDownButton);

				businessDropDownButton.click();

				dropDownValues.get(1).click();
				
				Thread.sleep(2000);
				
				WebElement refresh=driver.findElement(By.xpath("//p[contains(text(),'Premium Booked')]"));
								
				hover(refresh, driver);
				
				Thread.sleep(500);
//				selectAllBusinessOwner();
				
//				System.out.println("Size : " + businessBarCharts.size());
				List<WebElement> businessBarCharts = driver.findElements(By.xpath("//*[contains(@class,'recharts-bar-rectangle')]//*[local-name()='rect']"));

				for (WebElement bar : businessBarCharts) {

					// Wait for the element to be clickable
					wait.until(ExpectedConditions.visibilityOf(bar));					

					if (Double.parseDouble(bar.getAttribute("height")) > 0) {

						hover(bar, driver);
						
						Thread.sleep(500);

						for (int i = 0; i < toolTipValues.size(); i++) {

							if (i == 2) {

								toolTipValue = rupeeMatcher(toolTipValues.get(i));

//								System.out.println(" Premium Booked by " + toolTipValues.get(0).getText() + " : " + toolTipValue);
							}

						}

						totalValue = toolTipValue + totalValue;
					}
				}

				System.out.println("Total Premium Booked Business owner wise : " + totalValue + "\n");

			} else if (business.equals("Policies Booked")) {

				jsMoveToElement(driver, businessDropDownButton);

				businessDropDownButton.click();

				dropDownValues.get(0).click();

				Thread.sleep(2000);
				
				WebElement refresh = driver.findElement(By.xpath("//p[contains(text(),'Policies Booked')]"));
				
				hover(refresh, driver);
				
				Thread.sleep(500);
				
				List<WebElement> businessBarCharts = driver.findElements(By.xpath("//*[contains(@class,'recharts-bar-rectangle')]//*[local-name()='rect']"));

//				selectAllBusinessOwner();
				
//				System.out.println("Size : " + businessBarCharts.size());

				for (WebElement bar : businessBarCharts) {
					
					//wait for element to be clickable
					wait.until(ExpectedConditions.visibilityOf(bar));					

					if (Double.parseDouble(bar.getAttribute("height")) > 0) {

						hover(bar, driver);
						
						Thread.sleep(500);

						for (int i = 0; i < toolTipValues.size(); i++) {

							if (i == 2) {

								toolTipValue = rupeeMatcher(toolTipValues.get(i));

//								System.out.println(" Policies Booked by " + toolTipValues.get(0).getText() + " : " + toolTipValue);
							}

						}

						totalValue = toolTipValue + totalValue;
					}
				}

				System.out.println("Total Policies Booked Business owner wise : " + totalValue + "\n");

			} else if (business.equals("Commission Receivable")) {

				jsMoveToElement(driver, businessDropDownButton);

				businessDropDownButton.click();

				dropDownValues.get(2).click();

				Thread.sleep(2000);
				
//				System.out.println("Size : " + businessBarCharts.size());
				
				WebElement refresh=driver.findElement(By.xpath("//p[contains(text(),'Commission Receivable')]"));
				
				hover(refresh, driver);
				
				Thread.sleep(500);
				
				List<WebElement> businessBarCharts = driver.findElements(By.xpath("//*[contains(@class,'recharts-bar-rectangle')]//*[local-name()='rect']"));
								
//				System.out.println("Size : " + businessBarCharts.size());

				for (WebElement bar : businessBarCharts) {
					
					wait.until(ExpectedConditions.visibilityOf(bar));					

//					System.out.println("Size : " + businessBarCharts.size());

					if (Double.parseDouble(bar.getAttribute("height")) > 0) {

						hover(bar, driver);
						
						Thread.sleep(500);

						for (int i = 0; i < toolTipValues.size(); i++) {

							if (i == 2) {

								toolTipValue = rupeeMatcher(toolTipValues.get(i));

//								System.out.println(" Brokerage Receivable by " + toolTipValues.get(0).getText() + " : "+ toolTipValue);
							}

						}

						totalValue = toolTipValue + totalValue;
					}
				}

				System.out.println("Total Commission Receivable Business owner wise : " + totalValue + "\n");

			}

			return totalValue;

		} catch (Exception e) {

			e.printStackTrace();

			return 0;
		}
	}

	public int getCommissionReceivableBOWise() {

		try {

			int toolTipValue = 0, totalValue = 0;

			jsMoveToElement(driver, businessDropDownButton);

			businessDropDownButton.click();

			dropDownValues.get(2).click();
			
			List<WebElement> businessBarCharts = driver.findElements(By.xpath("//*[contains(@class,'recharts-bar-rectangle')]//*[local-name()='rect']"));

			for (WebElement bar : businessBarCharts) {

				if (Double.parseDouble(bar.getAttribute("height")) != 0) {

					hover(bar, driver);

					for (int i = 0; i < toolTipValues.size(); i++) {

						if (i == 2) {

							toolTipValue = rupeeMatcher(toolTipValues.get(i));

//							System.out.println(" Brokerage Receivable by "+toolTipValues.get(0).getText()+" : "+toolTipValue);
						}

					}

					totalValue = toolTipValue + totalValue;
				}
			}

			System.out.println("Total Commission Receivable Business owner wise : " + totalValue + "\n");

			return totalValue;

		} catch (Exception e) {

			e.printStackTrace();

			return 0;
		}
	}

	public void selectBusinessDropDown(String business) {

		try {

			jsMoveToElement(driver, businessDropDownButton);

			businessDropDownButton.click();

			Thread.sleep(500);

			for (WebElement option : dropDownValues) {

				if (option.getText().equals(business)) {

					option.click();

					System.out.println("Selected Business drop down value : " + business);
				}
			}

			Thread.sleep(1000);

		} catch (InterruptedException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		}

	}

	public void selectAllBusinessOwner() {

		Select select = new Select(businessOwnerDropdown);

		select.selectByValue("All");

		System.out.println("Selected Business owner All category");

	}

	public int selectBusinessOwners(String business) {

		try {

			int temp = 0, total = 0;

//			jsMoveToElement(driver, businessDropDownButton);

			Select select = new Select(businessOwnerDropdown);

			for (int i = 0; i < select.getOptions().size(); i++) {

				if (i != 0) {

					String businessOwner = select.getOptions().get(i).getText();

					select.selectByIndex(i);

					System.out.println("Selected Business owner : " + businessOwner);

					Thread.sleep(500);

					temp = categoryWiseTotal(businessOwner, business);

				}

				total = temp + total;
			}

			System.out.println("Total " + business + " : " + total + "\n");

			return total;

		} catch (Exception e) {

			e.printStackTrace();

			return 0;
		}

	}

	public int categoryWiseTotal(String businessOwner, String business) throws InterruptedException {

		int eachTemp = 0, eachTotal = 0;

		if (categoryList.isEmpty()) {

			System.out.println("No table values present" + "\n");
			// Handle the situation accordingly, e.g., throw an exception or exit the
			// program
		} else {

			for (int i = 1; i < categoryList.size() + 1; i++) {

				int temp = 0, total = 0;

				for (int j = 1; j < categoryWiseTotal.size(); j++) {

					jsMoveToElement(driver, driver.findElement(
							By.xpath("//table[@aria-label='simple table']/tbody/tr[" + i + "]/td[" + j + "]")));

					WebElement cell = driver.findElement(
							By.xpath("//table[@aria-label='simple table']/tbody/tr[" + i + "]/td[" + j + "]"));

//	    				temp=Integer.valueOf(driver.findElement(By.xpath("//table[@aria-label='simple table']/tbody/tr["+i+"]/td["+j+"]")).getText());

					temp = rupeeMatcher(cell);

//	    				System.out.println("temp value :"+temp);

					total = temp + total;
				}

				WebElement totalCell = driver.findElement(By.xpath("//table[@aria-label='simple table']/tbody/tr[" + i
						+ "]/td[" + (categoryWiseTotal.size()) + "]"));

//	    			int categoryTotal=Integer.valueOf(driver.findElement(By.xpath("//table[@aria-label='simple table']/tbody/tr["+i+"]/td["+(categoryWiseTotal.size())+"]")).getText());
				int categoryTotal = rupeeMatcher(totalCell);

				if (business.equals("Policies Booked")) {

					if (total == categoryTotal) {

//		    				System.out.println(categoryList.get(i-1).getText()+" category Total :"+categoryTotal);

					} else {

						System.out.println("Failed to validate, " + categoryList.get(i - 1).getText()
								+ " total value : " + total + " , Total value : " + categoryTotal);
					}

				} else {

					if (Math.abs(total - categoryTotal) <= 20) {

//		    				System.out.println(categoryList.get(i-1).getText()+" category Total :"+categoryTotal);

					} else {

						System.out.println("Failed to validate, " + categoryList.get(i - 1).getText()
								+ " total value : " + total + " , Total value : " + categoryTotal);
					}

				}

				eachTotal = categoryTotal + eachTotal;
			}

			System.out.println(businessOwner + " total " + business + " : " + eachTotal + "\n");
		}

		return eachTotal;

	}

	public int getBusinessOwnerValue(String duration) {

//		System.out.println("Business Owner for "+duration+" : "+(rupeeMatcher(businessOwner)));

		return rupeeMatcher(businessOwner);
	}

	public int getProspectsGeneratedValue(String duration) {

//		System.out.println("Prospects Generated for "+duration+" : "+(rupeeMatcher(prospectsGenerated)));

		return rupeeMatcher(prospectsGenerated);
	}

	public int getProspectsConvertedValue(String duration) {

//		System.out.println("Prospects Converted for "+duration+" : "+(rupeeMatcher(prospectsConverted)));

		return rupeeMatcher(prospectsConverted);
	}

	public int getNetPremiumValue(String duration) {

//		System.out.println("Net Premium Booked for "+duration+" : "+(rupeeMatcher(policyNetPremium)));

		return rupeeMatcher(policyNetPremium);
	}

	public int getCommissionReceivableValue(String duration) {

//		System.out.println("Commission Receivable for "+duration+" : "+(rupeeMatcher(commissionReceivable))+"\n");

		return rupeeMatcher(commissionReceivable);
	}
}
