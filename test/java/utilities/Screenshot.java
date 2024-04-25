package utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public String captureScreenshot(String description, WebDriver driver) {

		try {

			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			String name = String.format("Screenshot_%s_%s.jpg", description, java.time.LocalDateTime.now()).replace(':',
					'_');

			File destination = new File(System.getProperty("user.dir") + "/src/test/java/screenshots/" + name);

//			System.out.println(destination.getAbsolutePath());

			String scrPath=destination.getAbsolutePath();			

			FileUtils.copyFile(source, destination);

			return scrPath;

		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}

}
