package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_DropdownList {

	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
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

}