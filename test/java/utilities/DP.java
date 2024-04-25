package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DP {

    @DataProvider(name = "TestData")
    public String[][] TestDataProvider(Method m) throws IOException {
    String[][] data=null;
    File file=new File("./src/test/resources/Datasets.xlsx");
    FileInputStream fis;
    try {
    fis = new FileInputStream(file);
    XSSFWorkbook workbook=new XSSFWorkbook(fis);
    XSSFSheet sheet=workbook.getSheet(m.getName());
    XSSFRow row=sheet.getRow(0);
    int lastRowNumber=sheet.getPhysicalNumberOfRows();
    int lastCellNumber=row.getLastCellNum();
    Cell cell;
    data=new String[lastRowNumber-1][lastCellNumber-1];
    DataFormatter formatter=new DataFormatter();
    for(int i=1;i<lastRowNumber;i++) {
        for(int j=1;j<lastCellNumber;j++) {
            cell=sheet.getRow(i).getCell(j);
//        	cell.getStringCellValue();
                    
            data[i-1][j-1]=formatter.formatCellValue(cell);
        }
    }
    workbook.close();
    } catch (Exception e) {
        
        e.printStackTrace();
    }
    
    return data;

    }
    private static boolean isDateFormat(String value) {
        // Add your custom logic here to determine if the value represents a date
        // For example, you can check if the value matches a specific date format
        // For simplicity, we assume that all values representing date are in the format "yyyy-MM-dd"
        return value.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    private static Date parseDate(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isTimeFormat(String value) {
        // Add your custom logic here to determine if the value represents a time
        // For example, you can check if the value matches a specific time format
        // For simplicity, we assume that all values representing time are in the format "HH:mm:ss"
        return value.matches("\\d{2}:\\d{2}");
    }

    private static Date parseTime(String value) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            return timeFormat.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
