package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_RadioButton_Checkbox_Alert {

	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver ();
		
		System.setProperty("webdriver.chrome.driver", "C:/Phuongcei/workspace/WEBDRIVE_API_07_PHUONGLT/lib/chromedriver.exe");
		driver = new ChromeDriver();
		
	}


	public void TC_01_ClickButton() throws InterruptedException {
//		Step 01 - Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		
//		Step 02 - Click vào link My Account dưới footer (Sử dụng Javascript Executor code)
		
		WebElement myAcc = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		CommonClass.clickElementByJS(myAcc,driver);
		Thread.sleep(1000);
		
//		Step 03 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/login/
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
//		Step 04 - Click vào button CREATE AN ACCOUNT (Sử dụng Javascript Executor code)
		CommonClass.clickElementByJS(driver.findElement(By.xpath("//a[@title='Create an Account']")), driver);
		
//		Step 06 - Kiểm tra url của page sau khi click là: http://live.guru99.com/index.php/customer/account/create/
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		
//		Thread.sleep(3000);
	}

	
	public void TC_02_ClickChecbox() throws InterruptedException {
		
//		Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/checkboxes
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
//		Step 02 - Click vào checkbox: Dual-zone air conditioning (Thẻ input ko được sử dụng thuộc tính id)
//		"//div[@id='example']//label[contains(text(),'Dual-zone air conditioning')]"  -> không thể dùng xpath này để kiểm tra isSelected vì isSelected() chỉ làm việc với thẻ <input>
		
		WebElement slCheckbox = driver.findElement(By.xpath("//div[@id='example']//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"));
		
		
		// Tuy nhiên ở đây vì thẻ input là invisible/inclickable nên không thể dùng click() [mặc dù xpath tìm thấy (nhưng không highlight)] => dùng JS để click
		CommonClass.clickElementByJS(slCheckbox, driver);
				
//		Step 03 - Kiểm tra checkbox đó đã chọn
		Assert.assertTrue(slCheckbox.isSelected());
		
//		Step 04 - Sau khi checkbox đã được chọn - deselect nó và kiểm tra nó chưa được chọn
		CommonClass.clickElementByJS(slCheckbox, driver);
		
		Assert.assertFalse(slCheckbox.isSelected());

	}

	@Test
	public void TC_03_CheckZZZ() {
		
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}

}
