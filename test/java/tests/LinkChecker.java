package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;

public class LinkChecker {

    public static void main(String[] args) {
        // Setup WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // URL of the web page you want to check
        String url = "http://localhost:3000/contact-us";  // Replace with your web page URL

        try {
            driver.get(url);
            List<WebElement> links = driver.findElements(By.tagName("a"));

            for (WebElement link : links) {
                String linkUrl = link.getAttribute("href");
                int responseCode = getResponseCode(linkUrl);

                if (responseCode == 200) {
                    System.out.println("Link is working: " + linkUrl);
                } else {
                    System.out.println("Broken link: " + linkUrl + " (Response Code: " + responseCode + ")");
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    public static int getResponseCode(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            return connection.getResponseCode();
        } catch (Exception e) {
            return -1; // Return -1 for any exceptions (e.g., unable to connect)
        }
    }
}

