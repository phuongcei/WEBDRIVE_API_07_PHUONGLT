package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_DropdownList {

	WebDriver driver;

//	Drop list Job Role 01
	By xpathDrop1 = By.xpath("//select[@id='job1']");
	
	@BeforeClass
	public void beforeClass() {

//		driver = new FirefoxDriver ();
		
		System.setProperty("webdriver.chrome.driver", "C:/Phuongcei/workspace/WEBDRIVE_API_07_PHUONGLT/lib/chromedriver.exe");
		driver=new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}

	@Test
	public void TC_01_HandleDropDownListHTML() throws InterruptedException {
//	Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
//	Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select
		Select select = new Select(driver.findElement(xpathDrop1));
		Assert.assertFalse(select.isMultiple());
				
//	Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		select.selectByVisibleText("Automation Tester");
		
//	Step 04 - Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		
//	Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue
		select.selectByValue("manual");
		
//	Step 06 - Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		
//	Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex
		select.selectByIndex(3);
		
//	Step 08 - Kiểm tra giá trị đã được chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		
//	Step 09 - Kiểm tra dropdown có đủ 5 giá trị
		System.out.println("Number options: " + select.getOptions().size());
		
	}

	@Test
	public void TC_02_HandleCustomDropList() {
		
	}

	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}

}
