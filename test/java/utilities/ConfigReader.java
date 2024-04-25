package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	Properties prop;
	
	public ConfigReader() {
		File f=new File(System.getProperty("user.dir")+"/src/test/java/configuration.properties");
		
//		System.out.println(f.getAbsolutePath());
		
		try {
			
			FileInputStream fis = new FileInputStream(f);
			
			prop=new Properties();
			
			prop.load(fis);
			

			
		} catch(Exception e) {
			e.printStackTrace();
			}
	}
	
	public String getData(String key) {		
		
		String output=prop.getProperty(key);
			
			return 	output;		
		
	}

}
