package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Textbox_Textarea {

	WebDriver driver;

//	Input data
	private String CusName, DoB, InAddress, InCity, InState, InPin, InPhone, InEmail, passWord;

//	Edit data		
	private String editAddress, editCity, editState, editPin, editPhone, editEmail;

	String CusId;

//	Xpath Elements
	By xCusName = By.xpath("//input[@name='name']");
	By xDoB = By.xpath("//input[@name='dob']");
	By xAddress = By.xpath("//textarea[@name='addr']");
	By xCity = By.xpath("//input[@name='city']");
	By xState = By.xpath("//input[@name='state']");
	By xPin = By.xpath("//input[@name='pinno']");
	By xPhone = By.xpath("//input[@name='telephoneno']");
	By xEmail = By.xpath("//input[@name='emailid']");
	By xPass = By.xpath("//input[@name='password']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		CusName = "Le Tran Phuong";
		DoB = "1989-05-03";
		InAddress = "59 Nguyen Chanh";
		InCity = "Da Nang";
		InState = "Lien Chieu";
		InPin = "161493";
		InPhone = "0987654321";
		InEmail = "automation_" + CommonClass.randomNumberForEmail() + "@gmail.com";
		passWord = "123456";

//		editAddress, editCity, editState, editPin, editPhone, editEmail, editPass;
		editAddress = "01 Bau Mac";
		editCity = "Ha Noi";
		editState = "Tu Liem";
		editPin = "222222";
		editPhone = "9993338880";
		editEmail = "edit_automation_" + CommonClass.randomNumberForEmail() + "@gmail.com";
		
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
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

//			Verify HomePage được hiển thị thành công
//			Note: Manual test để lấy thông tin User/Pass nếu hết hạn - User chỉ tồn tại trong 20 ngày - http://demo.guru99.com/
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

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
		System.out.println("Email: " + InEmail);
		driver.findElement(xPass).sendKeys(passWord);

		driver.findElement(By.xpath("//input[@name='sub']")).click();

//		Step 05 - Sau khi hệ thống tạo mới Customer thành công > Get ra thông tin của Customer ID
		CusId = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID: " + CusId);

//		Step 06 - Verify tất cả thông tin được tạo mới thành công

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), CusId);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), CusName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), DoB);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), InCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), InAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), InState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), InPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), InPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), InEmail);

//		Step 07 - Chọn menu Edit Customer > Nhập Customer ID > Submit   /14034
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(CusId);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

//		Step 08 - Tại màn hình Edit Customer:
//			Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới New Customer tại Step 04

		String valueCusName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td//input")).getAttribute("value");
		System.out.println("Customer name: " + valueCusName);
		Assert.assertEquals(CusName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td//input")).getAttribute("value"));
		Assert.assertEquals(InAddress, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td/textarea")).getText());

//		Step 09 - Nhập giá trị mới tại tất cả các field (ngoại trừ những field bị disable) > Submit

		driver.findElement(xAddress).clear();
		driver.findElement(xCity).clear();
		driver.findElement(xState).clear();
		driver.findElement(xPin).clear();
		driver.findElement(xPhone).clear();
		driver.findElement(xEmail).clear();

		driver.findElement(xAddress).sendKeys(editAddress);
		driver.findElement(xCity).sendKeys(editCity);
		driver.findElement(xState).sendKeys(editState);
		driver.findElement(xPin).sendKeys(editPin);
		driver.findElement(xPhone).sendKeys(editPhone);
		driver.findElement(xEmail).sendKeys(editEmail);
		System.out.println("Email: " + editEmail);

		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
//		Step 10 - Verify giá trị tất cả các field đúng với dữ liệu sau khi đã Edit thành công

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
