package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_IFrame_Windows {

	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver ();
		
//		System.setProperty("webdriver.chrome.driver", "C:/Phuongcei/workspace/WEBDRIVE_API_07_PHUONGLT/lib/chromedriver.exe");
//		driver = new ChromeDriver();
		
	}

	@Test
	public void TC_01_CheckXXX() {

	}

	@Test
	public void TC_02_CheckYYY() {
		
	}

	@Test
	public void TC_03_CheckZZZ() {
		
	}

	
	@AfterClass
	public void afterClass() {
		
		
	}

	
	public void switchToWindowByTitle(String expectedTitle) {
		Set <String> allWindowID = driver.getWindowHandles();
		
		for(String runWindows : allWindowID) {
			driver.switchTo().window(runWindows);
			
			
			
		}
	}
}


//a[contains(.,'Account Detail')]  //xem trong cac subtag cua a co cho nao chua text = Accoutn detail khong.