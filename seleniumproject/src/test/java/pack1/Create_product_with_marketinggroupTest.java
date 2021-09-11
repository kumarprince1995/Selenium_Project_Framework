package pack1;

import java.io.FileInputStream;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Create_product_with_marketinggroupTest {

	public static void main(String[] args) throws Exception {
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
		WebElement quickcreate = driver.findElement(By.id("qccombo"));
		Select select=new Select(quickcreate);
		select.selectByVisibleText("New Product");
		if(driver.findElement(By.xpath("//b[text()='Create Product']")).isDisplayed()){
			System.out.println("Pass::create product popup page is displayed");
		}
		else
			System.out.println("Fail::create product popup page is not displayed");
		FileInputStream fsixlsx=new FileInputStream(".\\Data\\createorgData.xlsx");
		Workbook wb = WorkbookFactory.create(fsixlsx);
		Sheet sheet = wb.getSheet("productData");
		Row row = sheet.getRow(2);
		String productdata = row.getCell(3).getStringCellValue();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(productdata);
		driver.findElement(By.xpath("//input[@value='T']")).click();
		driver.findElement(By.name("assigned_group_id")).click();
		driver.findElement(By.xpath("//option[text()='Marketing Group']")).click();
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement productverify = driver.findElement(By.xpath("//span[contains(text(),'Product Information')]"));
		System.out.println(productverify.getText());
		if(productverify.getText().contains(productdata)){
			System.out.println("Pass::product hasbeen created sucessfully");
		}else
			System.out.println("Fail::product is not created");
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
