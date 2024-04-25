package singletons;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;

public class DriverSingleton {
    
    ConfigReader config = new ConfigReader();

    private static DriverSingleton instance=null;

    private WebDriver driver;
    
    
    private DriverSingleton() throws MalformedURLException {
       
        if (config.getData("browser").equals("chrome")) {
			
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+config.getData("chromePath"));
//
			
        	
        	WebDriverManager.chromedriver().avoidShutdownHook().create();
        	
        	 ChromeOptions options = new ChromeOptions();
        	 
        	 driver=new ChromeDriver(options);
        	 
        }
        else if (config.getData("browser").equals("edge")) {
			
//			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+config.getData("edgePath"));

        	WebDriverManager.edgedriver().setup();
        	
        	EdgeOptions options= new EdgeOptions();
        	
			driver=new EdgeDriver(options);

        }
    }

    public static DriverSingleton getInstance() throws MalformedURLException {
        if(instance==null) {
            instance=new DriverSingleton();
        } 
        return instance;
    }
    
    public WebDriver getDriver() {
        driver.manage().window().maximize();
        return driver;
    }
}