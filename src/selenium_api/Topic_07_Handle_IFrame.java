package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Handle_IFrame {

	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver ();
		
//		System.setProperty("webdriver.chrome.driver", "C:/Phuongcei/workspace/WEBDRIVE_API_07_PHUONGLT/lib/chromedriver.exe");
//		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_iFrame() {
//		Step 01 - Truy cập vào trang: http://www.hdfcbank.com/
		driver.get("http://www.hdfcbank.com/");
		
//		Step 02 - Close popup nếu có hiển thị (switch qua iframe nếu có)  - F5 (refresh page) nhiều lần thì sẽ xuất hiện popup
//			Xpath iframe: //iframe[@id='vizury-notification-template']
		
//		Vì selenium chỉ thao tác với các element hiển thị (visible)
//		Nên trong trường hợp không có pop-up, iframe bên dưới sẽ có trong DOM, nhưng không visible.
//		Dùng findElement sẽ throw exception: no element 
//		Solution: dùng findElements, nếu k có pop-up => size = 0;
		
		List <WebElement> iframePopups = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		int sizeOfListiFramePopups = iframePopups.size();
		if (sizeOfListiFramePopups > 0  ) {
			driver.switchTo().frame(iframePopups.get(0));
			driver.findElement(By.xpath("//div[@id='div-close']")).click();
			System.out.println("Close pop-up successfully!");
			
//		Vì text cần verify ở bước 3 nằm trong 1 frame khác.
//		Muốn vào được frame đó, phải thoát ra frame hiện tại, trở về top windows.
			driver.switchTo().defaultContent();
			
		} else {
			System.out.println("There is no pop-up!!");
		}
		
//		Step 03 - Verify đoạn text được hiển thị:  What are you looking for? (switch qua iframe nếu có)
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class=\"flipBannerWrap\"]//iframe[starts-with(@id,'viz_iframe')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id=\"messageText\"]")).getText(), "What are you looking for?");
		driver.switchTo().defaultContent();
		
//		Step 04 - Verify banner có đúng 6 images (switch qua iframe nếu có)
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class=\"slidingbanners\"]//iframe")));
		List <WebElement> listBanner = driver.findElements(By.xpath("//div[contains(@id,'prd-item-list')]"));
		Assert.assertEquals(listBanner.size(), 6);
		
		
//		Check one of images is displayed
		for(WebElement image: listBanner) {
//			System.out.println(image.getAttribute("style"));
			if(image.getAttribute("style").equals("z-index: 10;")) {
				assertTrue(image.isDisplayed());
				System.out.println("There is one displayed image at one time!");
			} else {
				System.out.println("This image is not displayed");
			}
		}
		driver.switchTo().defaultContent();
		
//		Step 05 - Verify flipper banner được hiển thị và có 8 items
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());
		
		List <WebElement> listImage = driver.findElements(By.xpath("//div[contains(@class,'product')]//img[@class='front icon']"));
		Assert.assertEquals(listImage.size(), 8);
		
		for(WebElement eachImage: listImage) {
			Assert.assertTrue(eachImage.isDisplayed());
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	public void switchToWindowByTitle(String expectedTitle) {
		Set <String> allWindowID = driver.getWindowHandles();
		
		for(String runWindows : allWindowID) {
			driver.switchTo().window(runWindows);
			
		}
	}
	
}


//a[contains(.,'Account Detail')]  //xem trong cac subtag cua a co cho nao chua text = Accoutn detail khong.