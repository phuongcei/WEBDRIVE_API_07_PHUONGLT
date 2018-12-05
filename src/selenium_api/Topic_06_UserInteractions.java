package selenium_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_UserInteractions {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", "C:/Phuongcei/workspace/WEBDRIVE_API_07_PHUONGLT/lib/chromedriver.exe");
//		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_01_HoverMouse() throws InterruptedException {
//			Step 01 - Truy cập vào trang: http://www.myntra.com/
		driver.get("http://www.myntra.com/");

//			Step 02 - Hover chuột vào menu Account login
		WebElement AccLogin = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		Actions action = new Actions(driver);

		action.moveToElement(AccLogin).perform();

//			Step 03 - Click login button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='desktop-header-cnt']//a[text()='login']")).click();

//			Step 04 - Verify Login form:
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Login to Myntra')]")).isDisplayed());

	}

	
	public void TC_02_ClickAndHold() throws InterruptedException {
//		Click and hold element - select multiple item
//		Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/selectable/display-grid.html

		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

//		Step 02 - Click and hold tá»« 1-> 4
		
		Actions action = new Actions(driver);

		action.clickAndHold(driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='1']"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='4']"))).perform();
		Thread.sleep(2000);
		action.release().perform();
		Thread.sleep(3000);
		
//		Step 03 - Sau khi chọn kiểm tra 4 phần tử được chọn với xpath
//		//li[@class='ui-state-default ui-selectee ui-selected']
		
		List <WebElement> selectedCells = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		
		Assert.assertEquals(selectedCells.size(), 4);
		
//		Cách khác: kết hợp nhiều actions trong 1 câu lệnh
//		List <WebElement> numberRange = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
//		action.clickAndHold(numberRange.get(0)).moveToElement(numberRange.get(3)).release().perform();
		
	}

	@Test
	public void TC_03_SelectRandomNumber() throws Exception {
		/* Select 6 random number 
		 * Report value
		 * Verify there are 6 selected.
		 */
		
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		List <WebElement> cells = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
			
		Actions action = new Actions(driver);
		
		action.click(cells.get(0)).perform();
		
		action.sendKeys(cells.get(0), Keys.CONTROL).click(cells.get(5)).click(cells.get(9)).perform();
		
		Thread.sleep(2000);
		
		List <WebElement> selectedCells = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		
		Assert.assertEquals(selectedCells.size(), 3);
		
		
	}
	
	
	public void TC_04_DoubleClick() throws InterruptedException {
//		Step 01 - Truy cập vào trang: http://www.seleniumlearn.com/double-click
		driver.get("http://www.seleniumlearn.com/double-click");
		
//		Step 02 - Double click vào element: Double-Click Me!
		WebElement DoubleClick = driver.findElement(By.xpath("//button[contains(text(),'Double-Click Me!')]"));
		
		Actions action = new Actions(driver);
		
		action.doubleClick(DoubleClick).perform();
		
//		Step 03 - Verify text trong alert ‹: 'The Button was double-clicked.'
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		
//		Step 04 - Accept Javascript alert
		alert.accept();
		
		Thread.sleep(2000);
	}

	
	
	@Test
	public void TC_05_RightClick() {
//		Step 01 - Truy cập vào trang: http://swisnl.github.io/jQuery-contextMenu/demo.html
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
//		Step 02 - Right click vào element: right click me
		Actions action = new Actions(driver);
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
//		Step 03 - Hover chuột vào element: Quit
		
		
//		Step 04 - Verify element Quit (visible + hover) với xpath:
//		//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']
		
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
