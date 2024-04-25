package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class LinkTester {

    public static void main(String[] args) {
        // Set the path to the chromedriver executable (Update this based on your system)
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    	WebDriverManager.chromedriver().avoidShutdownHook().setup();

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the webpage you want to test (replace 'url' with the actual URL)
        driver.get("http://localhost:3000/");

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
//                JavascriptExecutor js=(JavascriptExecutor)driver;
//                js.executeScript("arguments[0].scrollIntoView(true);", link);
//        		
//        		js.executeScript("arguments[0].style.border='2px solid red'", link);
//                link.click();

                // Wait for some time to allow the page to load (adjust the time based on your page load speed)
                try {
                    Thread.sleep(2000); // 2 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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

        } catch (Exception e) {
        	e.printStackTrace();
        }
        // Close the browser
        driver.quit();
    }
}

