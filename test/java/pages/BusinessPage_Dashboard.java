package pages;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumWrappers.wrappers;

public class BusinessPage_Dashboard extends wrappers {

	WebDriver driver;

	public BusinessPage_Dashboard(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[text()='Policy Booked']//preceding-sibling::div")
	public WebElement policiesBooked;

	@FindBy(xpath = "//*[contains(@class,'business_rectangle')][1]//img")
	WebElement policiesBookedIndicator;

	@FindBy(xpath = "//*[contains(@class,'business_rectangle')][1]//span")
	WebElement policiesBookedIndicatorValue;

	@FindBy(xpath = "//*[text()='Policy Net Premium']//preceding-sibling::div")
	public WebElement policyNetPremium;

	@FindBy(xpath = "//*[text()='Policy Net Premium']")
	WebElement policyNetPremiumText;

	@FindBy(xpath = "//*[contains(@class,'business_rectangle')][2]//img")
	WebElement policyNetPremiumIndicator;

	@FindBy(xpath = "//*[contains(@class,'business_rectangle')][2]//span")
	WebElement policyNetPremiumIndicatorValue;

	@FindBy(xpath = "//*[text()='Commission Receivable']//preceding-sibling::div")
	public WebElement commissionReceivable;

	@FindBy(xpath = "//*[contains(@class,'business_rectangle')][3]//img")
	WebElement commissionReceivableIndicator;

	@FindBy(xpath = "//*[contains(@class,'business_rectangle')][3]//span")
	WebElement commissionReceivableIndicatorValue;
	
	@FindBy(xpath = "//*[contains(@class,'business_option') and text()='Retail']")
	WebElement retailButton;
	
	@FindBy(xpath = "//*[contains(@class,'business_option') and text()='Corporate']")
	WebElement corporateButton;
	
	@FindBy(xpath = "//*[contains(@class,'business_option') and text()='Overall']")
	WebElement overallButton;

	@FindBy(xpath = "(//*[contains(@id,'headlessui-listbox-button')])[1]")
	WebElement durationDropdownButton;

	@FindBy(xpath = "//input[@placeholder='Select start Date']")
	WebElement startDateInput;

	@FindBy(xpath = "//input[@placeholder='Select End Date']")
	WebElement endDateInput;

//	@FindBy(xpath = "//*/span[text()='Policies Booked']")
	@FindBy(xpath = "(//button[contains(@id,'headlessui-listbox-button')])[2]")
	WebElement businessDropDownButton;

	@FindBy(xpath = "//select[contains(@class,'Graphs_dropdown')]")
	WebElement categoryDropdown;

	@FindBy(xpath = "//*[@role='listbox']//li/span")
	List<WebElement> dropDownValues;
	
	@FindBy(xpath = "//*[contains(@class,'MuiBarElement-root')]")
	List<WebElement> barChart;
	
	@FindBy(xpath = "//*[@role='tooltip']//table/tbody//td[3]")
	WebElement toolTipValue;
	
	@FindBy(xpath ="//*[contains(@class,'TabPanel-root')]//div[contains(@class,'Flex-root') and contains(@class,'space-x-2')]/p[1]")
	List<WebElement> subClassValues;
	
	@FindBy(xpath = "//*[text()='No data available']")
	WebElement noDataAvailable;
	
	@FindBy(xpath = "//*[contains(@class,'font-semibold')]")
	WebElement zeroValue;
	
	@FindBy(xpath = "//*[@aria-label='simple table']//tbody//tr//td")
	List<WebElement> companywiseValues;
	
	int[] totalCategoryArray=new int[18];
	
	int i=0;
	
//	String[] result=new String[40];
	
	int[] res=new int[4];
	
	public int getPoliciesBooked() {
		
		int result=rupeeMatcher(policiesBooked);
		
		return result;
	}
	
	public int getPolicyNetPremium() {
		
		int result=rupeeMatcher(policyNetPremium);
		
		return result;
	}
	
	public int getCommissionReceivable() {
		
		int result=rupeeMatcher(commissionReceivable);
		
		return result;
	}
	
	public boolean click_Overall_Corporate_Retail_Button(String businessType) throws InterruptedException {
		
		if(businessType.equals("Corporate")) {
			
			click(corporateButton, driver);
			
			Thread.sleep(2000);
			
			return true;
			
		} else if (businessType.equals("Retail")) {
			
			click(retailButton,driver);
			
			Thread.sleep(2000);
			
			return true;
			
		} else if(businessType.equals("Overall")) {
			
			click(overallButton,driver);
			
			Thread.sleep(2000);
			
			return true;
		}
		
		return false;
	}

	public boolean clickDropDownDuration(String duration, String startDate, String endDate) {

		try {
			
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
			
			enterEndDate(driver,endDate, endDateInput);

			policyNetPremium.click();

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean selectBusinessDropDown(String businessType) {

		try {
			
			jsMoveToElement(driver, businessDropDownButton);

			businessDropDownButton.click();
			
			Thread.sleep(1500);

			for (WebElement type : dropDownValues) {

				if (type.getText().equals(businessType)) {
					
					type.click();
					
					System.out.println("Selected Dropdown value : " + businessType);

					Thread.sleep(1500);
					
					return true;

				}				
				
			}

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	public boolean selectCategory(String category) {

		try {

			Select select = new Select(categoryDropdown);

			select.selectByVisibleText(category);

			Thread.sleep(2000);

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
	
	public int[] selectAllCategoryAndVerify(String business) {
		
		try {
			
			jsMoveToElement(driver, categoryDropdown);
			
			Select select = new Select(categoryDropdown);
			
			List<WebElement> options=select.getOptions();
			
			for (int j = 0; j < options.size(); j++) {
				
			    if(j==0) {
			    	
			    	continue;
			    }
			    
			    select.selectByIndex(j);
			    
			    System.out.println("Category selected dropdown value :"+options.get(j).getText());
			    
			    Thread.sleep(1500);
			    
			    boolean result=verifyBarGraphsAndTotalValue(business);
			    
			    if(result==false) {
			    	
			    	return null;
			    }
			   
			    //iterator for each category value to capture total value in string array
			    i++;
			}
			
			for (int j = 0; j < options.size(); j++) {
				
				if(j==0) {
			    	
			    	continue;
			    }
						    
//				result[j]=options.get(j).getText()+" category Bar chart Total : "+result[j];
			    
//			    result[j+18]=options.get(j).getText()+" category Sub class Total : "+result[j+18];
			    			    			   
			}
			
			int total=0;
			
			for(int temp:totalCategoryArray) {
				
				total=temp+total;
			}			

//			result[36] = "Policies Booked count : "+String.valueOf(rupeeMatcher(policiesBooked));
			res[0] = (rupeeMatcher(policiesBooked));
			
//			result[37] = "Policies Net Premium : "+String.valueOf(rupeeMatcher(policyNetPremium));
			res[1] = (rupeeMatcher(policyNetPremium));
			
//			result[38] = "Policies Net Commission Receivable : "+String.valueOf(rupeeMatcher(commissionReceivable));
			res[2] = (rupeeMatcher(commissionReceivable));
			
//			result[39] = business+" category-wise Value : "+String.valueOf(total);
			res[3] = (total);
					
			System.out.println(business+" category wise Value :"+total);
			
			System.out.println("Total polices booked :"+rupeeMatcher(policiesBooked));
			
			System.out.println("Total premium booked :"+rupeeMatcher(policyNetPremium));
			
			System.out.println("Total Commission collected :"+rupeeMatcher(commissionReceivable)+"\n");
			
//			return result;
			return res;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
	}

	public String[] validatePolicyBookedIndicator(String duration) throws InterruptedException {

		LocalDate pastdate = null;
		
		LocalDate currentDate = null;
		
		String[] resultArray = new String[6];

		Thread.sleep(2000);

		int policyBooked = Integer.parseInt(policiesBooked.getText());

		// Specify the API endpoint URL
		String apiUrl = "http://127.0.0.1:8000/api/v1/get_policies_booked";

		// Get the current date
		LocalDate presentDate = LocalDate.now();

		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (duration.equals("This Month")) {

			// Subtract one month to get the previous month's date
			currentDate=presentDate.minusMonths(1);
			
			pastdate = presentDate.minusMonths(2);

		} else if (duration.equals("This Year")) {

			// Calculate one year ago			
			currentDate=presentDate.minusYears(1);
			
			pastdate = presentDate.minusYears(2);

		}

		// Specify start date and end date parameters
		String startDate = pastdate.format(formatter);
		String endDate = currentDate.format(formatter);

		System.out.println("Start Date : " + startDate);

		System.out.println("End Date : " + endDate);

		// Encode the parameters for inclusion in the URL
		String encodedStartDate = URLEncoder.encode(startDate, StandardCharsets.UTF_8);
		String encodedEndDate = URLEncoder.encode(endDate, StandardCharsets.UTF_8);

		// Construct the URL with query parameters
		String apiUrlWithParameters = apiUrl + "?start_date=" + encodedStartDate + "&end_date=" + encodedEndDate;

		// Create an instance of HttpClient
		HttpClient httpClient = HttpClients.createDefault();

		// Create an HTTP GET request
		HttpGet httpGet = new HttpGet(apiUrlWithParameters);
	
		try {
			// Execute the request and get the response
			HttpResponse response = httpClient.execute(httpGet);

			// Get the response status code
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Status Code: " + statusCode);

			// Read and print the response body
			String responseBody = EntityUtils.toString(response.getEntity());

			// Parse the JSON response using Jackson
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			// Get the last array in the "data" array
            JsonNode lastArray = jsonNode.get("data").get(jsonNode.get("data").size() - 1);
            
            int previousPoliciesBooked=0;
            
            if(lastArray!=null) {
            	
            	// Get the value associated with the "null" key
            	previousPoliciesBooked = lastArray.get(1).asInt();
                
            } 

			resultArray[0] = "Previous start date : "+startDate;

			resultArray[1] = "Previous end date : "+endDate;

			resultArray[2] = "Previous "+duration+" policy booked : "+String.valueOf(previousPoliciesBooked);

			String indicator = policiesBookedIndicator.getAttribute("alt");
			
			System.out.println("Policy booked count : "+policyBooked);
			
			System.out.println("Previoius booked count : "+previousPoliciesBooked);
			
			System.out.println("Policy booked indicator Alt value : "+indicator);
			
			String spanText;
			
			if(!indicator.contains("Zero")) {
			
			 // Get the text content of the span element
				spanText= policiesBookedIndicatorValue.getText();
	        
			} else {
				
				spanText="0";
			}
			
	        // Extract the percentage value (assuming it's always in the format "(-<percentage>%")
	        int percentageValue = Integer.parseInt(spanText.replaceAll("[^0-9-]", ""));
			
	        System.out.println("Policies comparision value : "+percentageValue);
	        
			if(duration.equals("This Month")) {
				
				System.out.println("Expected Policies comparision value :"+Math.abs(previousPoliciesBooked-policyBooked));
				
				resultArray[4] = "Actual Indicator Value : "+String.valueOf(percentageValue);
				
				resultArray[5] = "Expected Indicator Value : "+String.valueOf(Math.abs(previousPoliciesBooked-policyBooked));
				
				if (policyBooked > previousPoliciesBooked) {

					if (!indicator.contains("Down") && percentageValue==Math.abs(previousPoliciesBooked-policyBooked)) {
						// Your code here if indicator does not contain "downarrow"

						resultArray[3] = "Policy Booked Indicator is : "+"true";

					} else {

						resultArray[3] = "Policy Booked Indicator is : "+"false";
					}

				} else if (policyBooked < previousPoliciesBooked) {

					if (indicator.contains("Down")) {
						// Your code here if indicator does not contain "downarrow"

						resultArray[3] = "Policy Booked Indicator is : "+"true";

					} else {

						resultArray[3] = "Policy Booked Indicator is : "+"false";
					}
					
				} else if(policyBooked==previousPoliciesBooked) {
					
					if (indicator.contains("Zero")) {
						// Your code here if indicator does not contain "downarrow"

						resultArray[3] = "Policy Booked Indicator is : "+"true";

					} else {

						resultArray[3] = "Policy Booked Indicator is : "+"false";
					}
				}
				
			} else if (duration.equals("This Year")) {
				
				resultArray[3] = "Policy Booked Indicator is : "+"flat";
				
			} else if(duration.equals("Custom Range")) {
				
				resultArray[3] = "Policy Booked Indicator is : "+"No indicator";
			}

			return resultArray;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
	}
	
	public String[] validatePolicyNetPremiumIndicator(String duration) throws InterruptedException {
		
		
		LocalDate pastdate = null;
		
		LocalDate currentDate = null;
		
		String[] resultArray = new String[6];
		
		double PolicyNetPremium=0;

		Thread.sleep(1500);
		
		PolicyNetPremium=rupeeMatcher(policyNetPremium);
		
		// Specify the API endpoint URL
		String apiUrl = "http://127.0.0.1:8000/api/v1/get_net_premium";

		// Get the current date
		LocalDate presentDate = LocalDate.now();

		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (duration.equals("This Month")) {

			// Subtract one month to get the previous month's date
			currentDate=presentDate.minusMonths(1);
			
			pastdate = presentDate.minusMonths(2);

		} else if (duration.equals("This Year")) {

			// Calculate one year ago			
			currentDate=presentDate.minusYears(1);
			
			pastdate = presentDate.minusYears(2);

		}

		// Specify start date and end date parameters
		String startDate = pastdate.format(formatter);
		String endDate = currentDate.format(formatter);

		System.out.println("Start Date : " + startDate);

		System.out.println("End Date : " + endDate);

		// Encode the parameters for inclusion in the URL
		String encodedStartDate = URLEncoder.encode(startDate, StandardCharsets.UTF_8);
		String encodedEndDate = URLEncoder.encode(endDate, StandardCharsets.UTF_8);

		// Construct the URL with query parameters
		String apiUrlWithParameters = apiUrl + "?start_date=" + encodedStartDate + "&end_date=" + encodedEndDate;

		// Create an instance of HttpClient
		HttpClient httpClient = HttpClients.createDefault();

		// Create an HTTP GET request
		HttpGet httpGet = new HttpGet(apiUrlWithParameters);
	
		try {
			// Execute the request and get the response
			HttpResponse response = httpClient.execute(httpGet);

			//	 Get the response status code
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Status Code: " + statusCode);

			// Read and print the response body
			String responseBody = EntityUtils.toString(response.getEntity());

			// Parse the JSON response using Jackson
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			// Get the last array in the "data" array
            JsonNode lastArray = jsonNode.get("data").get(jsonNode.get("data").size() - 1);
            
            double previousNetPremium=0;
            
            if(lastArray!=null) {
            	
            	// Get the value associated with the "null" key
            	previousNetPremium = lastArray.get(1).asInt();
                
            } 

			resultArray[0] = "Previous start date : "+startDate;

			resultArray[1] = "Previous end date : "+endDate;

			resultArray[2] = "Previous "+duration+" Net Premium : "+String.valueOf(previousNetPremium);

			String indicator = policyNetPremiumIndicator.getAttribute("alt");
			
			System.out.println("Policy Net Premium : "+(long)PolicyNetPremium);
			
			System.out.println("Previoius Policy Net Premium : "+(long)previousNetPremium);
			
			System.out.println("Policy Net Premium indicator Alt value : "+indicator);
			
			String spanText;
			
			if(!indicator.contains("Zero")) {
			
			 // Get the text content of the span element
				spanText= policyNetPremiumIndicatorValue.getText();
	        
			} else {
				
				spanText="0";
			}
			
	        // Extract the percentage value (assuming it's always in the format "(-<percentage>%")
	        int percentageValue = Integer.parseInt(spanText.replaceAll("[^0-9-]", ""));
			
	        System.out.println("Policy Net Premium Percentage value : "+percentageValue);
	       	        
			if(duration.equals("This Month")) {
				
				 System.out.println("Expected Policy Net Premium Percentage : "+(int)Math.round((((PolicyNetPremium-previousNetPremium)/previousNetPremium)*100)));
				
				 resultArray[4] = "Actual Indicator Value : "+String.valueOf(percentageValue);
				 
				 resultArray[5] = "Expected Indicator Value : "+String.valueOf((int)Math.round(((PolicyNetPremium-previousNetPremium)/previousNetPremium)*100));
				 
				if (PolicyNetPremium > previousNetPremium) {

					if (!indicator.contains("Down") && percentageValue==(int)Math.round(((PolicyNetPremium-previousNetPremium)/previousNetPremium)*100)) {

						resultArray[3] = "Policy Net Premium Indicator is : "+"true";

					} else {

						resultArray[3] = "Policy Net Premium Indicator is : "+"false";
					}

				} else if (PolicyNetPremium < previousNetPremium) {

					if (indicator.contains("Down") && percentageValue==(int)Math.round(((PolicyNetPremium-previousNetPremium)/previousNetPremium)*100)) {
						
						resultArray[3] = "Policy Net Premium Indicator is : "+"true";

					} else {

						resultArray[3] = "Policy Net Premium Indicator is : "+"false";
					}
					
				} else if(PolicyNetPremium==previousNetPremium) {
					
					if (indicator.contains("Zero")) {

						resultArray[3] = "Policy Net Premium Indicator is : "+"true";

					} else {

						resultArray[3] = "Policy Net Premium Indicator is : "+"false";
					}
				}
				
			} else if (duration.equals("This Year")) {
				
				resultArray[3] = "Policy Net Premium Indicator is : "+"flat";
				
			} else if(duration.equals("Custom Range")) {
				
				resultArray[3] = "Policy Net Premium Indicator is : "+"No indicator";
			}

			return resultArray;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
	}
	
	public String[] validateCommissionReceivableIndicator(String duration) throws InterruptedException {

		LocalDate pastdate = null;
		
		LocalDate currentDate = null;
		
		String[] resultArray = new String[6];
		
		double CommissionReceivable=0;

		Thread.sleep(1500);
		
		CommissionReceivable=rupeeMatcher(commissionReceivable);

		// Specify the API endpoint URL
		String apiUrl = "http://127.0.0.1:8000/api/v1/get_brokerage_receivable_information";

		// Get the current date
		LocalDate presentDate = LocalDate.now();

		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (duration.equals("This Month")) {

			// Subtract one month to get the previous month's date
			currentDate=presentDate.minusMonths(1);
			
			pastdate = presentDate.minusMonths(2);

		} else if (duration.equals("This Year")) {

			// Calculate one year ago			
			currentDate=presentDate.minusYears(1);
			
			pastdate = presentDate.minusYears(2);

		}

		// Specify start date and end date parameters
		String startDate = pastdate.format(formatter);
		String endDate = currentDate.format(formatter);

		System.out.println("Start Date : " + startDate);

		System.out.println("End Date : " + endDate);

		// Encode the parameters for inclusion in the URL
		String encodedStartDate = URLEncoder.encode(startDate, StandardCharsets.UTF_8);
		String encodedEndDate = URLEncoder.encode(endDate, StandardCharsets.UTF_8);

		// Construct the URL with query parameters
		String apiUrlWithParameters = apiUrl + "?start_date=" + encodedStartDate + "&end_date=" + encodedEndDate;

		// Create an instance of HttpClient
		HttpClient httpClient = HttpClients.createDefault();

		// Create an HTTP GET request
		HttpGet httpGet = new HttpGet(apiUrlWithParameters);
	
		try {
			// Execute the request and get the response
			HttpResponse response = httpClient.execute(httpGet);

			// Get the response status code
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Status Code: " + statusCode);

			// Read and print the response body
			String responseBody = EntityUtils.toString(response.getEntity());

			// Parse the JSON response using Jackson
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			// Get the last array in the "data" array
            JsonNode lastArray = jsonNode.get("data").get(jsonNode.get("data").size() - 1);
            
            double previousCommissionReceivable=0;
            
            if(lastArray!=null) {
            	
            	// Get the value associated with the "null" key
            	previousCommissionReceivable = lastArray.get(1).asLong();
                
            } 

			resultArray[0] = "Previous start date : "+startDate;

			resultArray[1] = "Previous end date : "+endDate;

			resultArray[2] = "Previous "+duration+" Commission Receivable : "+String.valueOf(previousCommissionReceivable);

			String indicator = commissionReceivableIndicator.getAttribute("alt");
			
			System.out.println("Commission Receivable count : "+(long)CommissionReceivable);
			
			System.out.println("Previoius Commission Receivable count : "+(long)previousCommissionReceivable);
			
			System.out.println("Commission Receivable indicator Alt value : "+indicator);
			
			String spanText;
			
			if(!indicator.contains("Zero")) {
			
			 // Get the text content of the span element
				spanText= commissionReceivableIndicatorValue.getText();
	        
			} else {
				
				spanText="0";
			}
			
	        // Extract the percentage value (assuming it's always in the format "(-<percentage>%")
	        int percentageValue = Integer.parseInt(spanText.replaceAll("[^0-9-]", ""));
			
	        System.out.println("Commission Receivable comparision percentage value : "+percentageValue);
	        
			if(duration.equals("This Month")) {
				
				System.out.println("Expected Commission Receivable comparision percentage value :"+(int)Math.round((((CommissionReceivable-previousCommissionReceivable)/previousCommissionReceivable)*100)));
				
				System.out.println("Actual value : "+percentageValue);
				
				resultArray[4] = "Actual Indicator Value : "+String.valueOf(percentageValue);
				
				resultArray[5] = "Expected Indicator Value : "+String.valueOf((int)Math.round((((CommissionReceivable-previousCommissionReceivable)/previousCommissionReceivable)*100)));
				
				if (CommissionReceivable > previousCommissionReceivable) {

					if (!indicator.contains("Down") && percentageValue==(int)Math.round((((CommissionReceivable-previousCommissionReceivable)/previousCommissionReceivable)*100))) {
						// Your code here if indicator does not contain "downarrow"

						resultArray[3] = "Commission Receivable Indicator is : "+"true";

					} else {

						resultArray[3] = "Commission Receivable Indicator is : "+"false";
					}

				} else if (CommissionReceivable < previousCommissionReceivable) {

					if (indicator.contains("Down") && percentageValue==(int)Math.round((((CommissionReceivable-previousCommissionReceivable)/previousCommissionReceivable)*100))) {
						// Your code here if indicator does not contain "downarrow"

						resultArray[3] = "Commission Receivable Indicator is : "+"true";

					} else {

						resultArray[3] = "Commission Receivable Indicator is : "+"false";
					}
					
				} else if(CommissionReceivable==previousCommissionReceivable) {
					
					if (indicator.contains("Zero")) {
						// Your code here if indicator does not contain "downarrow"

						resultArray[3] = "Commission Receivable Indicator is : "+"true";

					} else {

						resultArray[3] = "Commission Receivable Indicator is : "+"false";
					}
				}
				
			} else if (duration.equals("This Year")) {
				
				resultArray[3] = "Commission Receivable Indicator is : "+"flat";
				
			} else if(duration.equals("Custom Range")) {
				
				resultArray[3] = "Commission Receivable Indicator is : "+"No indicator";
			}

			return resultArray;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
	}
	
	public boolean verifyBarGraphsAndTotalValue(String business) {
		
		try {
			
			long ToolTipValue=0, totalValue=0;
			
			int SubClassValue=0,totalSubClass=0;
 			
			if(!zeroValue.getText().equals("0")) {
//			if(!barChart.isEmpty()||!noDataAvailable.isEnabled()) {
				
				for(WebElement bar:barChart) {
					
					if(bar.getAttribute("style").contains("height: 0px;")) {
						
						continue;
					}
									
					hover(bar, driver);
					
					Thread.sleep(500);
					
					WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
					
					wait.until(ExpectedConditions.visibilityOf(toolTipValue));
					
					ToolTipValue=rupeeMatcher(toolTipValue);
					
//					 System.out.println("Tool tip value: " + ToolTipValue);
					
			        totalValue=ToolTipValue+totalValue;
				}
				
//				result[i]=String.valueOf(totalValue);
				
				System.out.println("Total bar chart values :"+totalValue);
				
				for(WebElement subClass:subClassValues) {
					
			        SubClassValue=rupeeMatcher(subClass);
					
//		            System.out.println("Sub class value: " + SubClassValue);
		            
			        totalSubClass=SubClassValue+totalSubClass;
			        
			        totalCategoryArray[i]=totalSubClass;
				}
				
//				result[i+18]=String.valueOf(totalSubClass);
				
				System.out.println("Total sub class value :"+totalSubClass+"\n");
				
								
			} else {
				
				System.out.println("No data available");
				
//				result[i]="No data available";
				
				System.out.println("No data available");
				
//				result[i+18]="No data available";
				
			}
						
			if(business.equals("Policies Booked")) {
				
				if(totalValue==totalSubClass) {
					
					return true;
					
				} else {
										
					System.out.println("Policies Booked value not matched, Bar chart Total :"+totalValue+" , Sub class total"+totalSubClass);
					
					return false;
				}
				
			} else {
				
				if(Math.abs(totalValue-totalSubClass)>30) {
					
					System.out.println("Failed to validate "+business+" Bar chart value : "+totalValue+", Sub class value"+totalSubClass);
					
					return false;
				}
			}
			
			return true;
						
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
		
	}
	
	public int getInsuranceCompaniesBusinessValue(String business) {
		
		try {
			
			int total=0,temp=0;
			
			for(WebElement value:companywiseValues) {
				
				temp=rupeeMatcher(value);
				
				total=temp+total;				
				
			}
			
			System.out.println("Total Insurance companies "+business+" : "+total);
			
			return total;
			
		} catch (Exception e) {
			
			e.printStackTrace();		
			
			return 0;
		}
	}
	
	public String[] getAllCategoryValues(String business) {
		
		String[] result=new String[2];
		
		try {
			
			jsMoveToElement(driver, categoryDropdown);
			
			Select select = new Select(categoryDropdown);
			
//			List<WebElement> options=select.getOptions();
			select.selectByIndex(0);
			
			long ToolTipValue=0, totalValue=0;
			
			int SubClassValue=0,totalSubClass=0;
 			
			if(!zeroValue.getText().equals("0")) {
				
				for(WebElement bar:barChart) {
					
					if(bar.getAttribute("style").contains("height: 0px;")) {
						
						continue;
					}
									
					hover(bar, driver);
					
					Thread.sleep(500);
					
					WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
					
					wait.until(ExpectedConditions.visibilityOf(toolTipValue));
					
					ToolTipValue=rupeeMatcher(toolTipValue);
					
//					 System.out.println("Tool tip value: " + ToolTipValue);
					
			        totalValue=ToolTipValue+totalValue;
				}
				
				result[0]="Total bar chart values : "+String.valueOf(totalValue);
						
				System.out.println("Total bar chart values :"+totalValue);
				
				for(WebElement subClass:subClassValues) {
					
			        SubClassValue=rupeeMatcher(subClass);
					
//		            System.out.println("Sub class value: " + SubClassValue);
		            
			        totalSubClass=SubClassValue+totalSubClass;
			        
			      }
								
				result[1]="Total sub class value :"+String.valueOf(totalSubClass);
				
				System.out.println("Total sub class value :"+totalSubClass+"\n");
				
			} else {
				
				System.out.println("No data available");
						
				result[0]="Total bar chart values : No data available";
						
				System.out.println("No data available");
							
				result[1]="Total sub class value : No data available";
			}
			
			return result;			
			
						
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
		
	}

}
