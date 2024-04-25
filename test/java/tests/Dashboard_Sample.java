package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class Dashboard_Sample {

    public static void main(String[] args) throws InterruptedException {
        // Set the path to the chromedriver executable (Update this based on your system)
//        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
    	WebDriverManager.chromedriver().avoidShutdownHook().setup();

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();

        // Navigate to the webpage you want to test (replace 'url' with the actual URL)
        driver.get("http://localhost:3001/Renewals");
        
        Thread.sleep(2000);

     // Find the Canvas element
        WebElement canvas = driver.findElement(By.xpath("//*[name()='canvas']"));


        // Use Actions class to perform mouse hover actions
        Actions actions = new Actions(driver);

        // Assuming you have a method to get all data points
        // You may need to modify this based on your application
        List<WebElement> dataPoints = getAllDataPoints(canvas);

        // Hover over each data point
        for (WebElement dataPoint : dataPoints) {
            actions.moveToElement(dataPoint).perform();
            // Additional actions or validations after hovering
            // You can extract information or take screenshots, etc.
            // You might need to wait for changes to take effect
            Thread.sleep(1000);
        }

        // Close the browser
        driver.quit();
    }

    // Example method to retrieve all data points from the canvas
    private static List<WebElement> getAllDataPoints(WebElement canvas) {
        // Implement the logic to get all data points from the canvas
        // This could involve executing JavaScript to interact with the canvas
        // and return a list of WebElement representing data points.
        // Modify this method based on your application.
        // For simplicity, let's assume the canvas contains child elements representing data points.
        return canvas.findElements(By.cssSelector(".data-point"));
    
    }
}

