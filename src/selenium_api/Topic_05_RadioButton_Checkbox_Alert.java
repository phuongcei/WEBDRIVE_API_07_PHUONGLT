package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		driver = new FirefoxDriver ();
		
//		System.setProperty("webdriver.chrome.driver", "C:/Phuongcei/workspace/WEBDRIVE_API_07_PHUONGLT/lib/chromedriver.exe");
//		driver = new ChromeDriver();
		
	}

	@Test
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

	@Test
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
	public void TC_03_radioButton() throws InterruptedException {
//		Step 01 - Truy cập vào trang: http://demos.telerik.com/kendo-ui/styling/radios
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
//		Step 02 - Click vào radiobutton:  2.0 Petrol, 147kW (Thẻ input ko được sử dụng thuộc tính id)
		WebElement radioButton = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", radioButton);
		
//		Step 03 - Kiểm tra radio button đó đã chọn hay chưa/ nếu chưa chọn lại
		if (!radioButton.isSelected()){
			je.executeScript("arguments[0].click();", radioButton);
		}
		Thread.sleep(4000);
	}

	@Test
	public void TC_04_simpleJsAlert() throws InterruptedException {
		
//		Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
//		Step 02 - Click vào button: Click for JS Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
//		Step 03 - Verify message hiển thị trong alert là: I am a JS Alert
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		
		Assert.assertEquals(textOnAlert, "I am a JS Alert");
		
//		Step 04 - Accept alert và verify message hiển thị tại Result là:  You clicked an alert successfully
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}
	
	@Test
	public void TC_05_confirmAlert() throws InterruptedException {
//		Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
//		Step 02 - Click vào button: Click for JS Confirm
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
//		Step 03 - Verify message hiển thị trong alert là: I am a JS Confirm
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		
		Assert.assertTrue(alertMessage.equals("I am a JS Confirm"));
		
//		Step 04 - Cancel alert và verify message hiển thị tại Result là:  You clicked: Cancel
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
		
	}
	
	@Test
	public void TC_06_alertJsPrompt() {
//		Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
//		Step 02 - Click vào button: Click for JS Prompt
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
//		Step 03 - Verify message hiển thị trong alert là: I am a JS prompt
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		
		Assert.assertEquals(alertMessage, "I am a JS prompt");
		
//		Step 04 - Nhập vào text bất kì (daominhdam) và verify message hiển thị tại Result là:  You entered: daominhdam
		alert.sendKeys("Le Tran Phuong");
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Le Tran Phuong");
		
	}
	
	@Test
	public void TC_07_alertBasicAuthen() {
//		Step 01 - Truy cập vào trang: http://the-internet.herokuapp.com/basic_auth
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
			
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}

}
