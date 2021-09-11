package pack1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Search_Product_by_Qty_in_Stocktest {

	public static void main(String[] args) throws Throwable {
	WebDriver driver=null;
		
		FileInputStream fsi=new FileInputStream("./commonData.properties");
		Properties pobj= new Properties();
		pobj.load(fsi);
		String BROWSER=pobj.getProperty("browser");
		String URL=pobj.getProperty("url");
		String USERNAME=pobj.getProperty("username");
		String PASSWORD=pobj.getProperty("password");
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver,10);
		driver.get(URL);
		if(driver.findElement(By.linkText("vtiger")).isDisplayed()){
			System.out.println("Pass::Login page is displayed");
		}else
			System.out.println("Pass::Login page is not displayed");
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		if(driver.findElement(By.linkText("Home")).isDisplayed()){
			System.out.println("Pass::homepage is displayed");
		}
		else
			System.out.println("Fail::homepage is not displayed");
		driver.findElement(By.linkText("Products")).click();
		if(driver.findElement(By.linkText("Products")).isDisplayed()){
			System.out.println("Pass::product page is displayed");
		}
		else
			System.out.println("Fail::product page is not displayed");	
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		if(driver.findElement(By.xpath("//span[text()='Creating New Product']")).isDisplayed()){
			System.out.println("Pass:: create product page is displayed");
		}
		else
			System.out.println("Fail::create product page is not displayed");
		FileInputStream fsixlsx=new FileInputStream(".\\Data\\createorgData.xlsx");
		Workbook wb = WorkbookFactory.create(fsixlsx);
		Sheet sheet = wb.getSheet("productData");
		Row row = sheet.getRow(10);
		String productnamedata = row.getCell(3).getStringCellValue();
		String qtydata = row.getCell(7).getStringCellValue();
		driver.findElement(By.name("productname")).sendKeys(productnamedata);
		driver.findElement(By.id("qtyinstock")).sendKeys(qtydata);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement productverify = driver.findElement(By.xpath("//span[contains(text(),'Product Information')]"));
		System.out.println(productverify.getText());
		if(productverify.getText().contains(productnamedata)){
			System.out.println("Pass::product hasbeen created sucessfully");
		}else
			System.out.println("Fail::product is not created");
		driver.findElement(By.linkText("Products")).click();
		WebElement searchtext = driver.findElement(By.xpath("//input[@name='search_text']"));
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='"+qtydata+"';", searchtext);
		driver.findElement(By.id("bas_searchfield")).click();
		driver.findElement(By.xpath("//select[@id='bas_searchfield']//option[text()='Qty. in Stock']")).click();
		driver.findElement(By.name("submit")).click();
		if(driver.findElement(By.linkText(productnamedata)).isDisplayed()) {
			System.out.println("Pass::product hasbeen found sucessfully after searching");
		}else
			System.out.println("Fail::product is not found");
		Actions actions=new Actions(driver);
		WebElement signoutmenu = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		actions.moveToElement(signoutmenu).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		if(driver.findElement(By.linkText("vtiger")).isDisplayed()){
			System.out.println("Pass::Login page is displayed");
		}else
			System.out.println("Pass::Login page is not displayed");
		
		Cell status = row.createCell(5);
		status.setCellValue("pass");
		FileOutputStream fos=new FileOutputStream(".\\Data\\createorgData.xlsx");
		wb.write(fos);
		wb.close();
		
		driver.quit();

	}

}
