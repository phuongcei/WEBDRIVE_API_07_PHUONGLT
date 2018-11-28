package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_UserInteractions {

	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver ();
		System.setProperty("webdriver.chrome.driver", "C:/Phuongcei/workspace/WEBDRIVE_API_07_PHUONGLT/lib/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_CheckXXX() throws InterruptedException {
//			Step 01 - Truy cập vào trang: http://www.myntra.com/
		driver.get("http://www.myntra.com/");
		
//			Step 02 - Hover chuột vào Menu để login
		WebElement AccLogin = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		Actions action = new Actions(driver);

		action.moveToElement(AccLogin).perform();
		
//			Step 03 - Chọn Login button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='desktop-header-cnt']//a[text()='login']")).click();
		
//			Step 04 - Verify Login form được hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Login to Myntra')]")).isDisplayed());
		
	}

	@Test
	public void TC_02_CheckYYY() {
		
	}

	@Test
	public void TC_03_CheckZZZ() {
		
	}

	
	
    public void TC_07_Drag_Drop_HTML5_Xpath() throws AWTException, Exception {
		driver.get("https://html5demos.com/drag/");

		String oneXpath = "//a[@id='one']";
		String targetXpath = "//div[@id='bin']";
		
		drag_the_and_drop_html5_by_xpath(oneXpath, targetXpath);
		
		Thread.sleep(2000);
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 0 + xCentreSource;
		sourceLocation.y += 70 + yCentreSource;
		targetLocation.x += 0 + xCentreTarget;
		targetLocation.y += 70 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}

}
