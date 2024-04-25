package seleniumWrappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

public class wrappers {
	
	ConfigReader config = new ConfigReader();

	public void jsMoveToElement(WebDriver driver, WebElement element) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;

//		int scrollY = element.getLocation().getY()+15;

//		js.executeScript("window.scrollTo(0, arguments[0]);", scrollY);

		js.executeScript("arguments[0].scrollIntoView(true)", element);

//		js.executeScript("arguments[0].style.border='2px solid red'", element);

//		Thread.sleep(5000);

	}

	public void scrollBy(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0, 550);");
	}
	
	public void waitElement(WebDriver driver, WebElement element) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void setAttributeValue(WebDriver driver, WebElement element, String attribute, String value) {
		// Cast WebDriver to JavascriptExecutor
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		// Execute JavaScript to set the attribute value
		String script = "arguments[0].setAttribute(arguments[1], arguments[2]);";
		jsExecutor.executeScript(script, element, attribute, value);
	}

	public boolean click(WebElement elementToClick, WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOf(elementToClick));

		if (elementToClick.isDisplayed() && elementToClick.isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, 5);", elementToClick);
			js.executeScript("arguments[0].scrollIntoView(true); ", elementToClick);

			js.executeScript("arguments[0].style.border='2px solid red'", elementToClick);
			js.executeScript("arguments[0].click();", elementToClick);

			return true;
		}

		return false;
	}
	
	public boolean hover(WebElement elementToHover, WebDriver driver) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.elementToBeClickable(elementToHover));
		
//		System.out.println("Boolean : "+elementToHover.isDisplayed());
		//Creating object of an Actions class
		Actions action = new Actions(driver);
		
		if(elementToHover.isDisplayed()) {			

			//Performing the mouse hover action on the target element.
			action.moveToElement(elementToHover).build().perform();
			
			return true;
		}
		
		return false;
		
	}
	
	public int rupeeMatcher(WebElement value) {
		
		try {
			
			// Define the regular expression pattern to match the whole number
//	        Pattern pattern = Pattern.compile("\\d[\\d,]*");
			
			Pattern pattern = Pattern.compile("(-?\\d[\\d,]*)");			

	        // Create a matcher object
	        Matcher matcher = pattern.matcher(value.getText());

	        // Find the match
	        if (matcher.find()) {
	            // Get the matched whole number without the rupee symbol
	            String matchedNumber = matcher.group();

	            // Remove commas if present and convert to a numeric value
	            int result = Integer.parseInt(matchedNumber.replaceAll(",", ""));
		
	            return result;
	        } 
		}
	        catch(Exception e) {
	        	
	        	System.out.println("Rupee matcher not found");
	        	
	        	e.printStackTrace();
	        	
	        }
		
		return 0;
	}

	public boolean sendKeys(WebElement inputBox, String keysToSend) {
		inputBox.clear();
		inputBox.sendKeys(keysToSend);
		return true;
	}

	public boolean navigate(WebDriver driver, String url) {
		String currentUrl = driver.getCurrentUrl();
		if (!currentUrl.equals(url)) {
			driver.get(url);
			currentUrl = url;
			return true;
		} else {
			currentUrl = url;
			return true;
		}

	}

	public String dateFormatter(String date) throws ParseException {

		SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date dateInput = inputDateFormat.parse(date);
		
		if(config.getData("browser").equals("chrome")) {
			SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-mm-yyyy");
			return outputDateFormat.format(dateInput);
			
		} else if(config.getData("browser").equals("firefox")) {
			SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-mm-dd");
			return outputDateFormat.format(dateInput);			
		}

		return null;

	}
	
	public String dashboardDateFormatter(String date) throws ParseException {

		SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateInput = inputDateFormat.parse(date);
		
		
			SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return outputDateFormat.format(dateInput);			
		

	}
	
	public void enterStartDate(String startDate, WebElement startDateInput) throws InterruptedException {
		
		startDateInput.click();
		
		Thread.sleep(500);
		
		// Splitting the date string
        String[] parts = startDate.split("-");
        
        // Storing values separately
        String year = parts[0];
        String month = parts[1];
        String day = parts[2];
        
//        System.out.println(startDate);
        
//        System.out.println(year+" - "+month+" - "+day);

        startDateInput.sendKeys(Keys.ARROW_RIGHT);
        
        startDateInput.sendKeys(Keys.ARROW_RIGHT);
        
        startDateInput.sendKeys(year);
             
        // Press the left arrow key
        startDateInput.sendKeys(Keys.ARROW_LEFT);
        
        startDateInput.sendKeys(month);
                
        // Press the left arrow key twice
        startDateInput.sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT);
                
        Thread.sleep(1000);
     // Send keys one by one
//        for (char c : day.toCharArray()) {
//            // Send each character
//        	startDateInput.sendKeys(String.valueOf(c));
//        	Thread.sleep(100);
//        }
        startDateInput.sendKeys(day);
        
        Thread.sleep(1000);
	}
	
	public void enterEndDate(WebDriver driver,String endDate, WebElement endDateInput) throws InterruptedException {
		
	endDateInput.click();
		
		Thread.sleep(500);
		
		// Splitting the date string
        String[] parts = endDate.split("-");
        
        // Storing values separately
        String year = parts[0];
        String month = parts[1];
        String day = parts[2];
        
//        System.out.println(endDate);
        
//        System.out.println(year+" - "+month+" - "+day);
        
        endDateInput.sendKeys(Keys.ARROW_RIGHT);
        
        endDateInput.sendKeys(Keys.ARROW_RIGHT);
        
        endDateInput.sendKeys(year);
              
        // Press the left arrow key
        endDateInput.sendKeys(Keys.ARROW_LEFT);
        
        endDateInput.sendKeys(month);
                
        // Press the left arrow key twice
        endDateInput.sendKeys(Keys.ARROW_LEFT, Keys.ARROW_LEFT);
                  
        Thread.sleep(1000);
        
//        Actions act=new Actions(driver);
     // Send keys one by one
//        for (char c : day.toCharArray()) {
//            // Send each character
////        	endDateInput.sendKeys(String.valueOf(c));
//        	act.sendKeys(Integer.valueOf(String.valueOf(c)));
//        	Thread.sleep(1500);
//        }
//        for(int i=1;i<=Integer.valueOf(day);i++) {
//        	
//        	endDateInput.sendKeys(Keys.ARROW_UP);
//        }
        endDateInput.sendKeys(day);
        endDateInput.sendKeys(Keys.ARROW_LEFT);
        endDateInput.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
//        endDateInput.sendKeys(Keys.ARROW_LEFT);
        endDateInput.sendKeys(Keys.ARROW_UP);
        
        Thread.sleep(10000);
	}

	public String timeFormatter(String time) throws ParseException {

		SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");
		Date timeInput = inputFormat.parse(time);

		SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
		return outputFormat.format(timeInput);

	}
	
	public boolean redirectBack(WebDriver driver, String value) {
		
		try {
			
			driver.navigate().back();
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			
			wait.until(ExpectedConditions.urlContains(value));
		
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
		
	}

}
