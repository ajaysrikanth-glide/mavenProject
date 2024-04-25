package singletons;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.relevantcodes.extentreports.ExtentReports;

public class ReportSingleton {

    private static ReportSingleton instance=null;
    
    private ExtentReports report;

    private ReportSingleton() {
        String extentReportTitle=LocalDateTime.now().toString().replace(":","_");
//    	String extentReportTitle=LocalDate.now().toString().replace(":","_");
//        System.out.println(System.getProperty("user.dir")+"/src/test/java/ExtentReportConfiguration.xml");
        report=new ExtentReports(System.getProperty("user.dir")+"/src/test/java/outputResults/ExtentReport_"+extentReportTitle+".html");
        report.loadConfig(new File(System.getProperty("user.dir")+"/src/test/java/ExtentReportConfiguration.xml"));
        
    }

    public static ReportSingleton getInstance() {
        if(instance==null) {
            instance=new ReportSingleton();
        }
        return instance;
    }

    public ExtentReports getReport() {        
        return report;
    }
}