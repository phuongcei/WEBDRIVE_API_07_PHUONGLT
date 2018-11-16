package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_XX_NameLesson {

	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver ();
		
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
