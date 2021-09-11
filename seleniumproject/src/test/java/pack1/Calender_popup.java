package pack1;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Calender_popup {

	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		LocalDateTime ldt = LocalDateTime.now();
		  System.out.println(ldt);
		  int day=ldt.getDayOfMonth();
		 
		FileInputStream fsi=new FileInputStream("./commonData.properties");
		Properties pobj= new Properties();
		pobj.load(fsi);
		String BROWSER=pobj.getProperty("browser");
		String URL2=pobj.getProperty("url2");
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
		driver.get(URL2);
		Actions actions=new Actions(driver);
		actions.click().perform();
		driver.findElement(By.xpath("//label[@for='departure']")).click();
		driver.findElement(By.xpath("//div[text()='August 2021']/../..//p[text()='"+day+"']")).click();

	}

}
