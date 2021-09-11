package pack1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Calenderpopup {

	public static void main(String[] args) throws Throwable {
		//Declaring the webdriver
		WebDriver driver=null;
		//Create a java representation of excel sheet
		FileInputStream fsixlsx=new FileInputStream(".\\Data\\createorgData.xlsx");
		//create workbook
		Workbook wb = WorkbookFactory.create(fsixlsx);
		//get the sheet
		Sheet sheet = wb.getSheet("Calender");
		//go to the row
		Row row = sheet.getRow(1);
		//get the data from excel and store it
		String daydata = row.getCell(0).getStringCellValue();
		String monthyeardata = row.getCell(1).getStringCellValue();
		System.out.println(daydata);
		System.out.println(monthyeardata);
		//Create a java representation of properties file
		FileInputStream fsi=new FileInputStream("./commonData.properties");
		//creating the object of properties
		Properties pobj= new Properties();
		//using inpur variable,load the properties file
		pobj.load(fsi);
		//get the data from properties file and store it
		String BROWSER=pobj.getProperty("browser");
		String URL2=pobj.getProperty("url2");
		//opeining the browser in runtime according to the browser in properties file
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("ie")) {
			driver=new InternetExplorerDriver();
		}
		else if(BROWSER.equals("opera")) {
			driver=new OperaDriver();
		}
		//applying preconditions like maximize,implicity wait and explicit wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver,10);
		//navigating to the main url
		driver.get(URL2);
		//creating the option of Action class
		Actions actions=new Actions(driver);
		//click on the webpage to avoid yhe login popup
		actions.click().perform();
		Thread.sleep(3000);
		//click on the departure date
		driver.findElement(By.xpath("//label[@for='departure']")).click();
		//creating infinite loop for getting the data while iterting
		for(;;) {
			//using try and catch block to fetch the data while iterting then breaking from the loop
			try {
				driver.findElement(By.xpath("//div[text()='"+monthyeardata+"']/ancestor::div[@class='DayPicker-Month']//p[text()='"+daydata+"']")).click();
				break;
				//catching the exception type object thrown by the try block
			} catch (Exception e) {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			}
			
		}
		//passing the pass comment in the excel sheet if the exection happens properly
		Cell status = row.createCell(2);
		status.setCellValue("pass");
		FileOutputStream fos=new FileOutputStream(".\\Data\\createorgData.xlsx");
		wb.write(fos);
		wb.close();
		
		driver.quit();
	}


	}


