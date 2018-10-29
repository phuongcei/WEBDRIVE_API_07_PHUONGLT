package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class Topic_01_Check_Environment {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Khoi tao trinh duyet
		// driver = new FirefoxDriver();
		
		//Chrome
		
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
		driver = new ChromeDriver();

		// wait cho page duoc load thanh cong
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// maximize browser full screen
//		driver.manage().window().maximize();

		// get URL cua app
		driver.get("http://live.guru99.com");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TC_01_CheckTitle() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals("Home page", homePageTitle);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void TC_02_CheckURL() {
		String homePageURL = driver.getCurrentUrl();
		Assert.assertEquals("http://live.guru99.com/", homePageURL);
	}

	@AfterClass
	public void afterClass() {

		// quit browser
		driver.quit();

	}

}
