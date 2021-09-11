package pack1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Vtiger1 {

	public static void main(String[] args) throws Exception {
		WebDriver driver=null;
		
		FileInputStream fsi=new FileInputStream("./commonData.properties");
		Properties pobj= new Properties();
		pobj.load(fsi);
		String BROWSER=pobj.getProperty("browser");
		String URL=pobj.getProperty("url");
		String USERNAME=pobj.getProperty("username");
		String PASSWORD=pobj.getProperty("password");
		String OrgName=pobj.getProperty("organization");
		String WEBSITE=pobj.getProperty("website");
		String TICKER=pobj.getProperty("tickersymbol");
		String EMPLOYEES=pobj.getProperty("Employees");
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
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.cssSelector("img[title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(OrgName);
		driver.findElement(By.name("website")).sendKeys(WEBSITE);
		driver.findElement(By.id("tickersymbol")).sendKeys(TICKER);
		driver.findElement(By.id("employees")).sendKeys(EMPLOYEES);
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
		
		WebElement orgverify = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
		System.out.println(orgverify.getText());
		if(orgverify.getText().contains(OrgName)){
			System.out.println("Pass::organization hasbeen created sucessfully");
		}else
			System.out.println("Fail::organization is not created");
		Actions actions=new Actions(driver);
		WebElement signoutmenu = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		actions.moveToElement(signoutmenu).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
	}
}
