package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class Sample {

    public static void main(String[] args) {
        // Set the path to the chromedriver executable (Update this based on your system)
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    	WebDriverManager.chromedriver().avoidShutdownHook().setup();

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();

        // Navigate to the webpage you want to test (replace 'url' with the actual URL)
        driver.get("http://localhost:3001/Business");

        // Find all anchor (link) elements on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Loop through each link and test it
        try {
        	
        	for (WebElement link : links) {
//                String linkText = link.getText();
                String linkUrl = link.getAttribute("href");

                // Skip empty or JavaScript links
                if (linkUrl == null || linkUrl.isEmpty() || linkUrl.startsWith("javascript:")) {
                    continue;
                }

                // Test the link by clicking on it
                System.out.println(linkUrl);
                
                //Business page
                
//                List<WebElement> policies=driver.findElements(By.xpath("//*[contains(@class,'recharts-dot')]"));
                List<WebElement> policies=driver.findElements(By.xpath("(//*[@class='recharts-layer recharts-bar-rectangles'])[2]/*[@class='recharts-layer recharts-bar-rectangle']"));  
               
                for(WebElement policy:policies) {
                	
                	policy.click();
                	
                	Thread.sleep(2000);
                	
                	System.out.println(driver.findElement(By.xpath("(//*[contains(@class,'recharts-tooltip-wrapper')]//p)[3]")).getText());
                	
//                	System.out.println(driver.findElement(By.xpath("(//div[contains(@class,'tooltip-wrapper-top')]//p)[3]")).getText());
                }
                continue;
//                JavascriptExecutor js=(JavascriptExecutor)driver;
//                js.executeScript("arguments[0].scrollIntoView(true);", link);
//        		
//        		js.executeScript("arguments[0].style.border='2px solid red'", link);
//                link.click();

                // Wait for some time to allow the page to load (adjust the time based on your page load speed)
//                try {
//                    Thread.sleep(2000); // 2 seconds
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                // Check if the current URL after clicking the link is the same as the expected URL
//                if (driver.getCurrentUrl().equals(linkUrl)) {
//                    System.out.println("Link '" + linkText + "' with URL '" + linkUrl + "' is working.");
//                } else {
//                    System.out.println("Link '" + linkText + "' with URL '" + linkUrl + "' is broken.");
//                }
//
//                // Navigate back to the original page to test the next link
//                driver.navigate().back();
            }
        	
//        	driver.findElement(By.linkText("Calendar")).click();
//        	
//        	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//        	
//        	wait.until(ExpectedConditions.urlContains("calender"));
//        	
//        	driver.findElement(By.xpath("//button[contains(@id,'listbox')]")).click();
//        	
//        	Thread.sleep(5000);
//        	
//        	List<WebElement> list=driver.findElements(By.xpath("//button[contains(@id,'listbox')]//following::ul/li"));
//        	
//        	for(WebElement li:list) {
//        		
//        		if(li.getText().contains("Year")) {
//        			
//        			li.click();
//        			
//        			System.out.println("Performed click operation");
//        		}
//        	}
//        	
//        	List<WebElement> headers=driver.findElements(By.xpath("//table[@aria-label='simple table']/tbody/tr/th"));
//        	
//        	for(WebElement head:headers) {
//        		
//        		System.out.println(head.getText());
//        	}
//        	
//        	
//        	String currentDate=driver.findElement(By.xpath("//*[@aria-label='Select month']")).getText();
//        	
//        	System.out.println("Current Month and Date : "+currentDate);
//        	
//        	String year=currentDate.substring(4);
//        	
//        	System.out.println("Year : "+year);
//        	
//        	String month=currentDate.substring(0, 3);
//        	
//        	System.out.println("Month : "+month);
//        	
//        	WebElement nextMonth=driver.findElement(By.xpath("//*[@aria-label='Next month']"));
//        	
//        	WebElement previousMonth=driver.findElement(By.xpath("//*[@aria-label='Previous month']"));
//        	
//        	JavascriptExecutor js=(JavascriptExecutor)driver;
//        	
//        	js.executeScript("arguments[0].scrollIntoView();", nextMonth);
//        	
//        	while(!currentDate.equalsIgnoreCase("Mar 2024")) {
//        		
//        		nextMonth.click();
//        		
//        		Thread.sleep(5000);
//        		
//        		System.out.println(currentDate);
//        		
//        		previousMonth.click();
//        		
//        		Thread.sleep(2000);
//        		
//        		System.out.println(currentDate);
//        	}
        	
        } catch (Exception e) {
        	
        	e.printStackTrace();
        }
        // Close the browser
//        driver.quit();
    }
}

