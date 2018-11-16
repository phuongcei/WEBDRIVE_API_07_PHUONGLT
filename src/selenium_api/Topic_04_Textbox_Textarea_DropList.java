package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Textbox_Textarea_DropList {

	WebDriver driver;
	
//	Input data
	private String CusName, DoB, InAddress, InCity, InState, InPin, InPhone, InEmail, passWord;
	
//	Edit data		
	private String editAddress, editCity, editState, editPin, editPhone, editEmail, editPass;
	
	String CusId;

//	Xpath Elements
	By xCusName = By.xpath("//input[@name='name']");
	By xDoB		= By.xpath("//input[@name='dob']");
	By xAddress = By.xpath("//textarea[@name='addr']");
	By xCity	= By.xpath("//input[@name='city']");
	By xState	= By.xpath("//input[@name='state']");
	By xPin		= By.xpath("//input[@name='pinno']");
	By xPhone	= By.xpath("//input[@name='telephoneno']");
	By xEmail	= By.xpath("//input[@name='emailid']");
	By xPass	= By.xpath("//input[@name='password']");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver ();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		CusName 	= "Le Tran Phuong";
		DoB			= "03/05/1989";
		InAddress 	= "59 Nguyen Chanh";
		InCity		= "Da Nang";
		InState		= "Lien Chieu";
		InPin		= "161493";
		InPhone		= "0987654321";
		InEmail		= "automation_" + RandomNumber.randomNumberForEmail() + "@gmail.com";
				System.out.println("Email: " + InEmail);
		passWord	= "123456";
		
	}

	@Test
	public void TC_01_Handle_TextBox_TextArea() {
//		Step 01 - Access vào trang: http://demo.guru99.com/v4
		driver.get("http://demo.guru99.com/v4");
		
//		Step 02 - Đăng nhập với thông tin: User = mngr161493 | Pass = harErAh
		driver.findElement(By.xpath("//input[@name='uid']")).clear();
		driver.findElement(By.name("password")).clear();
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr161493");
		driver.findElement(By.name("password")).sendKeys("harErAh");
		
//			Verify HomePage được hiển thị thành công
//			Note: Manual test để lấy thông tin User/Pass nếu hết hạn - User chỉ tồn tại trong 20 ngày - http://demo.guru99.com/
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Guru99 Bank Home Page" );
		
//		Step 03 - Chọn menu New Customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
//		Step 04 - Nhập toàn bộ dữ liệu đúng > Click Submit
		driver.findElement(xCusName).clear();
		driver.findElement(xDoB).clear();
		driver.findElement(xAddress).clear();
		driver.findElement(xCity).clear();
		driver.findElement(xState).clear();
		driver.findElement(xPin).clear();
		driver.findElement(xPhone).clear();
		driver.findElement(xEmail).clear();
		driver.findElement(xPass).clear();
		
		driver.findElement(xCusName).sendKeys(CusName);
		driver.findElement(xDoB).sendKeys(DoB);
		driver.findElement(xAddress).sendKeys(InAddress);
		driver.findElement(xCity).sendKeys(InCity);
		driver.findElement(xState).sendKeys(InState);
		driver.findElement(xPin).sendKeys(InPin);
		driver.findElement(xPhone).sendKeys(InPhone);
		driver.findElement(xEmail).sendKeys(InEmail);
		driver.findElement(xPass).sendKeys(passWord);
		
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
//		Step 05 - Sau khi hệ thống tạo mới Customer thành công > Get ra thông tin của Customer ID
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Customer Registered successfully!!!']")).isDisplayed());
		
		CusId = driver.findElement(By.xpath("//td[text()='Customer Id']/following-sibling::td")).getText();
		System.out.println("Customer ID: " + CusId);
		
//		Step 06 - Verify tất cả thông tin được tạo mới thành công
		
		"//"
		
		
//		Step 07 - Chọn menu Edit Customer > Nhập Customer ID > Submit
		
//		Step 08 - Tại màn hình Edit Customer:
//			Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới New Customer tại Step 04
		
//		Step 09 - Nhập giá trị mới tại tất cả các field (ngoại trừ những field bị disable) > Submit
		
//		Step 10 - Verify giá trị tất cả các field đúng với dữ liệu sau khi đã Edit thành công
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
