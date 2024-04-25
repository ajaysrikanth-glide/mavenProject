package pages;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumWrappers.wrappers;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

public class CalendarPage_Dashboard extends wrappers {

	WebDriver driver;

	public CalendarPage_Dashboard(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//*[contains(@id,'headlessui-listbox-button')])[1]")
	WebElement defaultDurationDropDownButton;

	@FindBy(xpath = "//*[@role='listbox']//span")
	List<WebElement> dropDownDuration;

	@FindBy(xpath = "//input[@placeholder='Select start Date']")
	WebElement startDateInput;

	@FindBy(xpath = "//input[@placeholder='Select End Date']")
	WebElement endDateInput;

	@FindBy(xpath = "//*[contains(text(),'Status')]")
	WebElement clientText;

	@FindBy(xpath = "(//table[@aria-label='simple table'])[1]/tbody/tr/td[1]")
//	@FindBy(xpath = "//*[@class='rs-table-body-wheel-area']/div/div/div[1]/div/div")
	List<WebElement> clientNameHeaders;

	@FindBy(xpath = "(//table[@aria-label='simple table'])[1]/tbody/tr/td[2]/h5[1]")
//	@FindBy(xpath = "//*[@class='rs-table-body-wheel-area']/div/div/div[2]/div/div")
	List<WebElement> clientNoOfPolicyHeaders;

	@FindBy(xpath = "//div[contains(text(),'Upcoming Renewals')]//preceding::div[contains(@class,'calender_number')]")
	WebElement upcomingRenewalsCount;

	@FindBy(xpath = "(//div[contains(text(),'Previous Lost Cases')]//preceding::div[contains(@class,'calender_number')])[2]")
	WebElement previousLostCasesCount;

	@FindBy(xpath = "(//div[contains(text(),'RFQ Pending')]//preceding::div[contains(@class,'calender_number')])[3]")
	WebElement rfqPendingCount;

	@FindBy(xpath = "//div[text()='Upcoming Renewals']//parent::div//img")
	WebElement upcomingRenwalsIndicator;
	
	@FindBy(xpath = "//div[text()='Previous Lost Cases']//parent::div//img")
	WebElement previousLostCasesIndicator;

	@FindBy(xpath = "(//table)[2]//tbody//td[1]")
	List<WebElement> categoryWisePolicyCount;
	
	@FindBy(xpath = "(//table)[2]//tbody//th")
	List<WebElement> eachclientPoicyCount;
	
	@FindBy(xpath = "//*[contains(@class,'rowContainer')]/div[1]")
	WebElement clientNameOnGraph;

	public String[] upcomingRenewalsIndicatorValidation(String duration, int upcomingRenewals) {

		LocalDate pastdate = null;

		String[] resultArray = new String[4];

		// Specify the API endpoint URL
		String apiUrl = "http://65.1.177.226:8000/api/v1/get_upcoming_renewals";

		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (duration.equals("This Month")) {

			// Subtract one month to get the previous month's date
			pastdate = currentDate.minusMonths(1);

		} else if (duration.equals("This Year")) {

			// Calculate one year ago
			pastdate = currentDate.minusYears(1);

		} else if (duration.equals("This Week")) {

			// Calculate one week ago
			pastdate = currentDate.minusDays(6);

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
//            int statusCode = response.getStatusLine().getStatusCode();
//            System.out.println("Response Status Code: " + statusCode);

			// Read and print the response body
			String responseBody = EntityUtils.toString(response.getEntity());
//            System.out.println("Response Body:\n" + responseBody);

			// Parse the JSON response using Jackson
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			// Extract the "length" parameter from the JSON
			int lengthValue = jsonNode.get("length").asInt();

			System.out.println("Previous number of policies: " + lengthValue);

			resultArray[0] = startDate;

			resultArray[1] = endDate;

			resultArray[2] = String.valueOf(lengthValue);

			String indicator = upcomingRenwalsIndicator.getAttribute("src");

			System.out.println("Upcoming renewals Indicator src value : "+indicator);
			
			if (upcomingRenewals > lengthValue) {

				if (!indicator.contains("downarrow")) {
					// Your code here if indicator does not contain "downarrow"

					resultArray[3] = "true";

				} else {

					resultArray[3] = "false";
				}

			} else if (upcomingRenewals < lengthValue) {

				if (indicator.contains("downarrow")) {
					// Your code here if indicator does not contain "downarrow"

					resultArray[3] = "true";

				} else {

					resultArray[3] = "false";
				}
				
			} else if(upcomingRenewals==lengthValue) {
				
				if (indicator.contains("hypen")) {
					// Your code here if indicator does not contain "downarrow"

					resultArray[3] = "true";

				} else {

					resultArray[3] = "false";
				}
				
			}

			return resultArray;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
	}

	public boolean clickDropDownDuration(String duration, String startDate, String endDate) {

		try {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			wait.until(ExpectedConditions.elementToBeClickable(defaultDurationDropDownButton));

//			click(defaultYearDropDownButton, driver);
			jsMoveToElement(driver, defaultDurationDropDownButton); 
			
//			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//			
//			wait.until(ExpectedConditions.elementToBeClickable(defaultDurationDropDownButton));
			
//			Thread.sleep(6000);
			
			defaultDurationDropDownButton.click();

			Thread.sleep(1200);

			for (WebElement time : dropDownDuration) {

				if (time.getText().equals(duration)) {
					
					System.out.println();

					System.out.println("Selected Dropdown value : " + time.getText());

					time.click();

					Thread.sleep(2000);

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

			clientText.click();

			return true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}	

	public int[] verifyNoOfPolicyCount() {

		try {

			int[] resultArray = new int[3];
			
			Thread.sleep(2500);

			int Renewals_Count = Integer.parseInt(upcomingRenewalsCount.getText());

			int tempClient = 0, clientPolicyTotal = 0;
			
//			driver.findElement(By.xpath("//*[@class='rs-table-scrollbar-handle']")).click();

			clientText.click();
			
			for (WebElement policy : clientNoOfPolicyHeaders) {
				
				jsMoveToElement(driver, policy);

				tempClient = Integer.parseInt(policy.getText());
				
//				System.out.println("Policy count : "+tempClient);

				clientPolicyTotal = tempClient + clientPolicyTotal;
			}

			int tempCategory = 0, categoryPolicyTotal = 0;

			for (WebElement category : categoryWisePolicyCount) {

				tempCategory = Integer.parseInt(category.getText());

				categoryPolicyTotal = tempCategory + categoryPolicyTotal;

			}

			System.out.println("Total Upcoming renewals count : " + Renewals_Count);

			System.out.println("Cummulative client policy count : " + clientPolicyTotal);

			System.out.println("Cummulative category policy count : " + categoryPolicyTotal);

			resultArray[0] = Renewals_Count;

			resultArray[1] = clientPolicyTotal;

			resultArray[2] = categoryPolicyTotal;

//			if (Renewals_Count == clientPolicyTotal && clientPolicyTotal == categoryPolicyTotal) {
				
				if (clientPolicyTotal == categoryPolicyTotal) {

				return resultArray;

			} else {

				return null;
			}

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
		
	}
	
	public boolean verifyClientPolicyCount() throws InterruptedException {
				
		try {
						
			int tempClient = 0, clientPolicyTotal = 0;
			
			clientText.click();
			
			for (WebElement client : clientNameHeaders) {
				
				client.click();
				
				Thread.sleep(400);
				
				jsMoveToElement(driver, client);
				
				String clientName=client.getText();
				
				System.out.println("Client name : "+clientName);
					
				WebElement clientPolicies = client.findElement(By.xpath("./parent::tr/td[2]/h5[1]"));
				
//				WebElement clientPolicies =client;
				
				tempClient = Integer.parseInt(clientPolicies.getText());
				
				System.out.println("Policy count : "+tempClient);
				
				clientPolicyTotal = tempClient + clientPolicyTotal;
				
//				Thread.sleep(250);
				WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
				
				wait.until(ExpectedConditions.textToBePresentInElement(clientNameOnGraph, clientName.trim()));
				
				int eachClientCount=eachclientPoicyCount.size();
				
				System.out.println("Category wise total count : "+eachClientCount +" for client : "+clientName);
				
				System.out.println("Client name on Category wise graph : "+clientNameOnGraph.getText());
				
				if(eachClientCount==tempClient && clientNameOnGraph.getText().trim().equalsIgnoreCase(clientName.trim())) {
					
					continue;
					
				} else {
										
					System.out.println("Failed to verify Client policy count : "+tempClient+" , Category wise policy count : "+eachClientCount+" , for client : "+clientName);
					
//					uncomment
					return false;
				}
				
			}
			
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public Map<String, Integer> verifyDuplicateClientName() {
		
		// Find all the name elements in the table
		List<WebElement> nameElements=driver.findElements(By.xpath("(//table[@aria-label='simple table'])[1]/tbody/tr/td[1]/h5"));
		
		// Create a map to store the count of occurrences of each name
        Map<String, Integer> nameCountMap = new HashMap<>();

        // Iterate through the name elements and count occurrences
        for (WebElement element : nameElements) {
            String name = element.getText().trim();
            nameCountMap.put(name, nameCountMap.getOrDefault(name, 0) + 1);
        }

        // Print the names that occur more than once
        System.out.println("Duplicate Names:");
        for (Map.Entry<String, Integer> entry : nameCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey());
            }
        }
        
        System.out.println("*****************");
        
        return nameCountMap;
	}
	
	public String[] verifyPreviousLostCasesIndicator(String duration) {
		
		LocalDate pastdate = null;

		String[] resultArray = new String[4];
		
		int previousLostCases=Integer.parseInt(previousLostCasesCount.getText());

		// Specify the API endpoint URL
		String apiUrl = "http://127.0.0.1:8000/api/v1/get_lost_cases";

		// Get the current date
		LocalDate currentDate = LocalDate.now().minusYears(1);

		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (duration.equals("This Month")) {

			// Subtract one month to get the previous month's date
			pastdate = currentDate.minusMonths(1);

		} else if (duration.equals("This Year")) {

			// Calculate one year ago
			pastdate = currentDate.minusYears(1);

		} else if (duration.equals("This Week")) {

			// Calculate one week ago
			pastdate = currentDate.minusDays(6);

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
//            int statusCode = response.getStatusLine().getStatusCode();
//            System.out.println("Response Status Code: " + statusCode);

			// Read and print the response body
			String responseBody = EntityUtils.toString(response.getEntity());
//            System.out.println("Response Body:\n" + responseBody);

			// Parse the JSON response using Jackson
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			// Extract the "length" parameter from the JSON
			int lengthValue = jsonNode.get("length").asInt();

			System.out.println("Previous lost cases: " + lengthValue);

			resultArray[0] = startDate;

			resultArray[1] = endDate;

			resultArray[2] = String.valueOf(lengthValue);

			String indicator = previousLostCasesIndicator.getAttribute("src");

			System.out.println("Previous lost cases Indicator src value : "+indicator);
			
			if (previousLostCases > lengthValue) {

				if (indicator.contains("downarrow")) {
					// Your code here if indicator does not contain "downarrow"

					resultArray[3] = "true";

				} else {

					resultArray[3] = "false";
				}

			} else if (previousLostCases < lengthValue) {

				if (!indicator.contains("downarrow")) {
					// Your code here if indicator does not contain "downarrow"

					resultArray[3] = "true";

				} else {

					resultArray[3] = "false";
				}
				
			} else if(previousLostCases==lengthValue) {
				
				if (indicator.contains("hypen")) {
					// Your code here if indicator does not contain "downarrow"

					resultArray[3] = "true";

				} else {

					resultArray[3] = "false";
				}
				
			}

			return resultArray;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
	}
	
	public String[] verifyRFQPending(String duration) throws InterruptedException {
		
		LocalDate pastdate = null;
		
		String result[]=new String[2];
		
		Thread.sleep(2000);
		
		int rfqPending=Integer.parseInt(rfqPendingCount.getText());

		// Specify the API endpoint URL
		String apiUrl = "http://127.0.0.1:8000/api/v1/get_quotations_not_sent";

		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Define the desired date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (duration.equals("This Month")) {

			// Add one month to get the after month's date
			pastdate = currentDate.plusMonths(1);

		} else if (duration.equals("This Year")) {

			// Calculate one year after
			pastdate = currentDate.plusYears(1);

		} else if (duration.equals("This Week")) {

			// Calculate one week after
			pastdate = currentDate.plusDays(6);

		}

		// Specify start date and end date parameters
		String endDate = pastdate.format(formatter);
		String startDate = currentDate.format(formatter);

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
//            int statusCode = response.getStatusLine().getStatusCode();
//            System.out.println("Response Status Code: " + statusCode);

			// Read and print the response body
			String responseBody = EntityUtils.toString(response.getEntity());
//            System.out.println("Response Body:\n" + responseBody);

			// Parse the JSON response using Jackson
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(responseBody);

			// Extract the "length" parameter from the JSON
			int lengthValue = jsonNode.get("count").asInt();

			System.out.println("Rfq Pending: " + lengthValue);

			if(rfqPending==lengthValue) {
				
				result[0]= "true";
				
			} else {
				
				result[0]= "false";
			}

			return result;
			
		} catch (Exception e) {

			e.printStackTrace();

			return null;
		}
		
	}
}
