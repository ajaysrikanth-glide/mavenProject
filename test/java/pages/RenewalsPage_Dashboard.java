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

public class RenewalsPage_Dashboard extends wrappers {

	WebDriver driver;

	public RenewalsPage_Dashboard(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "(//*[contains(@class,'renewals_rectangle')]/div[1])[1]")
	WebElement totalRenewalsValue;
	
	@FindBy(xpath = "(//*[contains(@class,'renewals_rectangle')]/div[1])[2]")
	WebElement lostRenewalsValue;
	
	@FindBy(xpath = "(//*[contains(@id,'headlessui-listbox-button')])[1]")
	WebElement durationDropdownButton;
	
	@FindBy(xpath = "//*[@role='listbox']//li/span")
	List<WebElement> dropDownValues;
	
	@FindBy(xpath = "(//*[name()='select'])[1]")
	WebElement insurerCompanyDropdown;
	
	@FindBy(xpath = "(//*[name()='select'])[2]")
	WebElement categoryWiseDropdownButton;
	
	@FindBy(xpath = "//*[contains(@class,'apexcharts-datalabel-value')]")
	List<WebElement> donutChartValues;
	
	@FindBy(xpath = "//*[contains(@class,'justify-end items')]//p")
	List<WebElement> policiesBookedSubClass;
	
	@FindBy(xpath = "//input[@placeholder='Select start Date']")
	WebElement startDateInput;

	@FindBy(xpath = "//input[@placeholder='Select End Date']")
	WebElement endDateInput;
	
	@FindBy(xpath = "//*[text()='Total Renewals']")
	WebElement totalRenewalsText;
	
	@FindBy(xpath = "//*[@class='companyTitle']")
	WebElement insurerTitle;
	
	//1- policy booked, 2- renewal received
//	@FindBy(xpath = "//*[contains(@data-popper-placement,'start')]//tbody//td[3]/p")
	@FindBy(xpath = "//*[@role='tooltip']//td[3]//p")
	List<WebElement> barChartToolTips;
	
	@FindBy(xpath = "//*[contains(@class,'MuiChartsSurface-root')]//*[local-name()='rect' and contains(@class,'id-0')]")
	List<WebElement> policyBookedBarCharts;
	
	@FindBy(xpath = "//*[contains(@class,'MuiChartsSurface-root')]//*[local-name()='rect' and contains(@class,'id-1')]")
	List<WebElement> renewalsReceivedBarCharts;
	
	public int getTotalRenewals() {
		
		try {
			
			int result=Integer.parseInt(totalRenewalsValue.getText());
			
			System.out.println("Total Renwals : "+result);
			
			return result;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return 0;
		}
		
	}
	
	public int getLostRenewals() {
		
		try {
			
			int result=Integer.parseInt(lostRenewalsValue.getText());
			
			System.out.println("Total Lost renwals : "+result);
			
			
			return result;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return 0;
		}
		
	}
	
	public boolean clickDropDownDuration(String duration, String startDate, String endDate) {

		try {

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

			Thread.sleep(1000);
			

			enterStartDate(startDate, startDateInput);
			
			enterEndDate(driver,endDate, endDateInput);

//			startDateInput.sendKeys(startDate);

//			totalRenewalsText.click();

//			Thread.sleep(1000);
			
//			startDateInput.sendKeys(Keys.RETURN);

//			endDateInput.sendKeys(endDate);
			// Send keys one by one
//	        for (char c : endDate.toCharArray()) {
//	            // Send each character
//	        	endDateInput.sendKeys(String.valueOf(c));
//	        	Thread.sleep(100);
//	        }
			
//			endDateInput.sendKeys(Keys.RETURN);
			
//			totalRenewalsText.click();

//			Thread.sleep(2000);

//			totalRenewalsText.click();
//			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
			
//			wait.until(ExpectedConditions.visibilityOf(totalRenewalsText));

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public int[] selectInsurerDropDownAndVerifyDonutCharts() throws InterruptedException {
		
//		int policyTipValue=0,totalPolicyValue=0,totalRenewalValue=0,renewalTipValue=0;
		
		try {
			
			int result[] = new int[2];
			
			jsMoveToElement(driver, insurerCompanyDropdown);
			
			Select select=new Select(insurerCompanyDropdown);
			
			List<WebElement> options=select.getOptions();
			
			 int tempBooked=0,tempReceived=0,totalBooked=0,totalReceived=0;
			
			for (int j = 0; j < options.size(); j++) {
				
			    select.selectByIndex(j);
			    
			    Thread.sleep(1500);
			    
			    String title= options.get(j).getText().trim();
			    
			    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));		    
			    
			    wait.until(ExpectedConditions.textToBePresentInElement(insurerTitle,title));
			    	    
			    System.out.println("Category selected dropdown value :"+options.get(j).getText());
			    
//			   if(donutChartValues.size()!=0) {
				   				   
				   for(int i=0;i<donutChartValues.size();i++) {
				    	
					   //policy booked value loop
					   if(i==0) {
						   
						   System.out.println("Policy booked value :"+donutChartValues.get(i).getText());
						   
						   if(Integer.parseInt(donutChartValues.get(i).getText())>0) {
							   
							   int temp=0,total=0;
							   
							   for(WebElement subClass:policiesBookedSubClass) {
								   
								  temp= Integer.parseInt(subClass.getText());
								  
								  total=temp+total;
								   
							   }
							   
//							   System.out.println("\t"+"Policy booked sub class total value :"+total);
							   
							   if(Integer.parseInt(donutChartValues.get(i).getText())!=total) {
								   
								   System.out.println("Failed to verify, Policies Booked and sub class total are not equal, Polices booked : "+donutChartValues.get(i).getText()+" , Sub class total value : "+total);
							   }
							   
							   tempBooked=total;
							   
							   totalBooked=tempBooked+totalBooked;
							   
						   } 
						   
					   //renewals received loop
					   } else {
						   
						   System.out.println("Renewals received value :"+donutChartValues.get(i).getText());
						   
						   tempReceived=Integer.parseInt(donutChartValues.get(i).getText());
						   
						   totalReceived=tempReceived+totalReceived;
					   }
					   
					  
					   }
				   
//				   Select categorySelect=new Select(categoryWiseDropdownButton);
//				   
//				   List<WebElement> categoryOptions=categorySelect.getOptions();
//				   
//				   for(int k=0;k<categoryOptions.size();k++) {
//					   
//					   if(k>0) {
//						   
//						   categorySelect.selectByIndex(k);
//						   
//						   Thread.sleep(500);
//						   
//						   System.out.println("Category selected dropdown value :"+categoryOptions.get(k).getText());
//						   						   
//							   for(int l=1;l<=policyBookedBarCharts.size();l++) {
//								   
//								   WebElement poicyElement=driver.findElement(By.xpath("//*[contains(@class,'MuiChartsSurface-root')]//*[local-name()='rect' and contains(@class,'id-0')]["+l+"]"));
//								   
//								   if(poicyElement.getAttribute("style").contains("height: 0px;")) {
//									   
//									   WebElement renewalElement = driver.findElement(By.xpath("//*[contains(@class,'MuiChartsSurface-root')]//*[local-name()='rect' and contains(@class,'id-1')]["+l+"]"));
//									   
//									   if(renewalElement.getAttribute("style").contains("height: 0px;")) {
//										   
//										   System.out.println("Both policies booked and Renewals received are zero : 0");
//										   
//									   } else {
//										   
//										   hover(poicyElement, driver);		
//										   
//										   categoryWiseToolTip();
//										   											   
//									   }
//									   
//								   } else {
//									   
//									   hover(poicyElement, driver);
//									   
//									   categoryWiseToolTip();
//
//								   }
//							   } 
//
//					   }
//				   
//				   else {
//					   
//					   continue;
//				   }
//				    
//				   
//				}
				    	
				    }
				   
//			   }
			   
			
			
			System.out.println("\n"+"Total Policies Booked : "+totalBooked);
			
			result[0]= totalBooked;
			
			System.out.println("Total Renewals Received :"+totalReceived+"\n");
			
			result[1] =totalReceived;
			
			return result;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	public int[] selectInsurerDropDownAndVerifyCategoryWise() {
		
		try {
			
			int result[] = new int[2];
			
			jsMoveToElement(driver, insurerCompanyDropdown);
			
			Select select=new Select(insurerCompanyDropdown);
			
			List<WebElement> options=select.getOptions();
			
//			 int tempBooked=0,tempReceived=0,totalBooked=0,totalReceived=0;
			int policyTipValue=0,totalPolicyValue=0,totalRenewalValue=0,renewalTipValue=0;
			
//			for (int j = 0; j < options.size(); j++) {
				
				for (int j = 0; j < 1; j++) {
				
			    select.selectByIndex(j);
			    
			    Thread.sleep(2500);
			    
			    String title= options.get(j).getText().trim();
			    
			    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));		    
			    
			    wait.until(ExpectedConditions.textToBePresentInElement(insurerTitle,title));
			    	    
			    System.out.println("Insurer selected dropdown value :"+options.get(j).getText());
			    			    
			    Select categorySelect=new Select(categoryWiseDropdownButton);
				   
				   List<WebElement> categoryOptions=categorySelect.getOptions();
				   
				   for(int k=0;k<categoryOptions.size();k++) {
					   
					   int tempBooked=0,tempReceived=0,totalBooked=0,totalReceived=0;
					   
					   if(k>0) {
						   
						   categorySelect.selectByIndex(k);
						   
						   Thread.sleep(2500);
						   
						   System.out.println("Category selected dropdown value :"+categoryOptions.get(k).getText());
						   						   
							   for(int l=1;l<=policyBookedBarCharts.size();l++) {
								   
								   Thread.sleep(300);
								   
								   WebElement poicyElement=driver.findElement(By.xpath("//*[contains(@class,'MuiChartsSurface-root')]//*[local-name()='rect' and contains(@class,'id-0')]["+l+"]"));
								   
								   if(poicyElement.getAttribute("style").contains("height: 0px;")) {
									   
									   WebElement renewalElement = driver.findElement(By.xpath("//*[contains(@class,'MuiChartsSurface-root')]//*[local-name()='rect' and contains(@class,'id-1')]["+l+"]"));
									   
									   if(renewalElement.getAttribute("style").contains("height: 0px;")) {
										   
//										   System.out.println("Both policies booked and Renewals received are zero : 0");
										   continue;
										   
									   } else {
										   
//										   Thread.sleep(300);
										   
										   hover(renewalElement, driver);	
										   
										   wait.until(ExpectedConditions.visibilityOfAllElements(barChartToolTips));										   
//										   wait.until(ExpectedConditions.visibilityOf(barChartToolTips.get(0)));
										   
//										   categoryWiseToolTip();
										   for(int m=0;m<barChartToolTips.size();m++) {
												
												if(m==0) {
													
													policyTipValue=rupeeMatcher(barChartToolTips.get(0));
													
													tempBooked=policyTipValue;
													
												}	else if (m==1) {
													
													renewalTipValue=rupeeMatcher(barChartToolTips.get(1));
													
													tempReceived=renewalTipValue;
												}					
												
											}
											
											totalPolicyValue=policyTipValue+totalPolicyValue;
											
											totalBooked=tempBooked+totalBooked;
											
											totalRenewalValue=renewalTipValue+totalRenewalValue;
											
											totalReceived=tempReceived+totalReceived;
											
//											 System.out.println(totalBooked +"Policy Booked");
//											   
//											 System.out.println(totalReceived +"Renewals Received");
										   											   
									   }
									   
								   } else {
//									   									   
									   hover(poicyElement, driver);
									   
									   wait.until(ExpectedConditions.visibilityOfAllElements(barChartToolTips));
									   
//									   Thread.sleep(150);
									   
//									   WebDriverWait toolTipWait=new WebDriverWait(driver, Duration.ofSeconds(5));
									   
//									   toolTipWait.until(ExpectedConditions.visibilityOf(barChartToolTips.get(1)));
									   
									  // categoryWiseToolTip();
									   for(int m=0;m<barChartToolTips.size();m++) {
											
											if(m==0) {
												
												policyTipValue=rupeeMatcher(barChartToolTips.get(0));
												
												tempBooked=policyTipValue;
												
											}	else if (m==1) {
												
												renewalTipValue=rupeeMatcher(barChartToolTips.get(1));
												
												tempReceived=renewalTipValue;
											}					
											
										}
										
										totalPolicyValue=policyTipValue+totalPolicyValue;
										
										totalBooked=tempBooked+totalBooked;
										
										totalRenewalValue=renewalTipValue+totalRenewalValue;
										
										totalReceived=tempReceived+totalReceived;
										
//										 System.out.println(totalBooked +"Policy Booked");
//										   
//										 System.out.println(totalReceived +"Renewals Received");

								   }
							   } 

					   }
				   
				   else {
					   
					   continue;
				   }
				    
					   System.out.println(totalBooked +" : Policy Booked");
					   
					   System.out.println(totalReceived +" : Renewals Received"+"\n");
				
				   
				}
				   
				   System.out.println(totalPolicyValue +" : Overall Policy Booked");
				   
				   System.out.println(totalRenewalValue +" : Overall Renewals Received"+"\n");
			}
				result[0]=totalPolicyValue;
				
				result[1]=totalRenewalValue;
				
				return result;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
			
		}
		
	}
	
	public void categoryWiseToolTip() {
		
		int policyTipValue=0,totalPolicyValue=0,totalRenewalValue=0,renewalTipValue=0;
		
		for(int m=0;m<barChartToolTips.size();m++) {
			
			if(m==0) {
				
				policyTipValue=rupeeMatcher(barChartToolTips.get(0));
				
			}	else if (m==1) {
				
				renewalTipValue=rupeeMatcher(barChartToolTips.get(1));
			}					
			
		}
		
		totalPolicyValue=policyTipValue+totalPolicyValue;
		
		totalRenewalValue=renewalTipValue+totalRenewalValue;
		
		 System.out.println(totalPolicyValue +"Policy Booked");
		   
		 System.out.println(totalRenewalValue +"Renewals Received");
	}
}
