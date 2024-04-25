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

public class ClientsPage_Dashboard extends wrappers {

	WebDriver driver;

	public ClientsPage_Dashboard(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[text()='Number Of Corporate Clients']//preceding-sibling::div")
	WebElement noOfCorporateClients;

	@FindBy(xpath = "//*[text()='Number Of Individual Clients']//preceding-sibling::div")
	WebElement noOfIndividualClients;

	@FindBy(xpath = "//*[text()='Industries We Serve']//preceding-sibling::div")
	WebElement industriesWeServe;

	@FindBy(xpath = "(//*[contains(@id,'headlessui-listbox-button')])[1]")
	WebElement durationDropdownButton;
	
	@FindBy(xpath = "//*[name()='select']")
	WebElement categoryDropdown;
	
	@FindBy(xpath = "//*[local-name()='g' and contains(@seriesName,'PoliciesxBooked')]//*[local-name()='path']")
	List<WebElement> barCharts;
	
	@FindBy(xpath = "//*[text()='No Data']")
	WebElement noData;
	
	//1=Policy Booked
	@FindBy(xpath = "//*[@id='apexchartscombined-chart']//*[@class='apexcharts-tooltip-text-y-value']")
	List<WebElement> tooltips;
	
	@FindBy(xpath = "//*[contains(@class,'apexcharts-line-series')]//*[@text-anchor='middle']")
	List<WebElement> brokerageReceivable;

	@FindBy(xpath = "//input[@placeholder='Select start Date']")
	WebElement startDateInput;

	@FindBy(xpath = "//input[@placeholder='Select End Date']")
	WebElement endDateInput;

	@FindBy(xpath = "//*[@role='listbox']//li/span")
	List<WebElement> dropDownValues;
	
	public boolean clickDropDownDuration(String duration, String startDate, String endDate) {

		try {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			wait.until(ExpectedConditions.elementToBeClickable(durationDropdownButton));

			jsMoveToElement(driver, durationDropdownButton);

			durationDropdownButton.click();

			Thread.sleep(1500);

			for (WebElement time : dropDownValues) {

				if (time.getText().equals(duration)) {

					System.out.println("Selected Dropdown value : " + time.getText());

					time.click();

					Thread.sleep(1500);

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
			
			System.out.println("Start Date : "+startDate);
			
			System.out.println("End Date : "+endDate);

			startDate = dashboardDateFormatter(startDate);

			endDate = dashboardDateFormatter(endDate);

			enterStartDate(startDate, startDateInput);
			
			Thread.sleep(2000);
			
			enterEndDate(driver,endDate, endDateInput);

//			endDateInput.sendKeys(endDate);
			// Send keys one by one
//	        for (char c : endDate.toCharArray()) {
//	            // Send each character
//	        	endDateInput.sendKeys(String.valueOf(c));
//	        	Thread.sleep(100);
//	        }
			
//			endDateInput.sendKeys(Keys.RETURN);
			
			noOfCorporateClients.click();

			Thread.sleep(2000);

//			totalRenewalsText.click();
//			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
			
//			wait.until(ExpectedConditions.visibilityOf(totalRenewalsText));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public int getNumberOfCorporateClients() {

		try {

			return rupeeMatcher(noOfCorporateClients);

		} catch (Exception e) {

			e.printStackTrace();

			return 0;
		}
	}

	public int getNumberOfIndividualClients() {

		try {

			return rupeeMatcher(noOfIndividualClients);

		} catch (Exception e) {

			e.printStackTrace();

			return 0;
		}
	}
	
	public int getNumberOfIndustriesWeServe() {

		try {

			return rupeeMatcher(industriesWeServe);

		} catch (Exception e) {

			e.printStackTrace();

			return 0;
		}
	}
	
	public int[] selectCategoryDropDownAndVerify() {
		
	   try {
		   
		   int[] res= new int[2];
		   
		   Select categorySelect=new Select(categoryDropdown);
		   
		   List<WebElement> categoryOptions=categorySelect.getOptions();

		   int overallPolicies=0, overallBrokerage=0;
		   
		   for(int i=0;i<categoryOptions.size();i++) {
			   
			   int tempBooked=0,tempBrokerage=0,totalBooked=0,totalBrokerage=0;

			   if(i>0) {
				   
				   categorySelect.selectByIndex(i);
				   
				   Thread.sleep(1500);
				   
				   System.out.println("Category selected dropdown value :"+categoryOptions.get(i).getText());
				   
				   try {
					   
					   if(barCharts.get(0).isDisplayed()) {
						   
						   for(int j=1;j<=barCharts.size();j++) {
							   
							   WebElement bar=driver.findElement(By.xpath("//*[local-name()='g' and contains(@seriesName,'PoliciesxBooked')]//*[local-name()='path']["+j+"]"));

//							   barHeight
							   System.out.println("bar height value : "+(bar.getAttribute("barHeight")));
							   
							  if(Double.parseDouble(bar.getAttribute("barHeight"))>0) {
								  
								  hover(bar, driver);	
								   
								   for(int m=0;m<tooltips.size();m++) {
										
										if(m==0) {
											
											tempBooked=rupeeMatcher(tooltips.get(0));
											
											WebElement monthLabel=driver.findElement(By.xpath("//*[@id='apexchartscombined-chart']//*[@class='apexcharts-tooltip-title']"));
											
											System.out.println(monthLabel.getText()+" : "+tempBooked);
											
//											tempBooked=policyTipValue;
											
										}	
										
//										else if (m==1) {
											
//											tempBrokerage=rupeeMatcher(tooltips.get(1));
											
//											tempReceived=renewalTipValue;
//										}					
										
									}
								   
								   totalBooked=tempBooked+totalBooked;
							  }
							   
							  
							   
//							   totalBrokerage=tempBrokerage+totalBrokerage;
						   }
						   
						   for(WebElement brokerage:brokerageReceivable) {
							   
							   if(!brokerage.getText().isEmpty() || !brokerage.getText().isBlank()) {
								  
								   tempBrokerage=Integer.valueOf(brokerage.getText());
								   
								   totalBrokerage=tempBrokerage+totalBrokerage;
							   }
						   }
						   
						   
						   
						   System.out.println(totalBooked +" : Policies Booked");
					
						   System.out.println(totalBrokerage +" : Brokerage Receivable");
						   
					   }
				   }

				   catch (Exception e) {
					   
					   e.printStackTrace();
					   
					  System.out.println("No Data available"+"\n");
					  
					   
				   }

			   }
			   
			   overallPolicies=totalBooked+overallPolicies;
			   
			   overallBrokerage=totalBrokerage+overallBrokerage;
			   
		   }
		   
		   System.out.println("Total Poicies Booked : "+overallPolicies);
		   
		   System.out.println("Total Brokerage Receivable : "+overallBrokerage);
		   
		   res[0]=overallPolicies;
		   
		   res[1]=overallBrokerage;
		   
		   return res;
		   
	   } catch (Exception e) {
		   
		   System.out.println(e.getMessage());
		   
		   return null;
		   
	   }
	

	}

}
