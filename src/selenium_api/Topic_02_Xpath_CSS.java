package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_CSS {
	WebDriver driver;
	
	
  @BeforeClass
  public void beforeClass() {
	  
	 driver = new FirefoxDriver();
	 	 
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	  
	  
  }

  @Test
  public void TC_01_VerifyUrlAndTitle() {
//		  *	Step 01 - Truy cập vào trang: http://live.guru99.com/
	
	  driver.get("http://live.guru99.com/");
	  
//	  	  * Step 02 - kiểm tra title của page là : "Home page"
	  String titleHomePage = driver.getTitle();
	  Assert.assertEquals(titleHomePage, "Home page");
	 
//		  * Step 03 - Click vào link "My Account" chuyển tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  							
//		  * Step 04 - Click CREATE AN ACCOUNT button để tới trang Đăng ký tài khoản
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  
//		  * Step 05 - Back lại trang Đăng nhập
	  driver.navigate().back();
	  
//		  * Step 06 - Kiểm tra url của page Đăng nhập là: http://live.guru99.com/index.php/customer/account/login/
	  String currentLoginURL = driver.getCurrentUrl();
	  Assert.assertEquals(currentLoginURL, "http://live.guru99.com/index.php/customer/account/login/");
	  
//		  * Step 07 - Forward tới trang Tạo tài khoản
	  driver.navigate().forward();
	  
//		  * Step 08 - kiểm tra url của page Tạo tài khoản là: http://live.guru99.com/index.php/customer/account/create/
	  String currentCreateAccountURL = driver.getCurrentUrl();
	  Assert.assertEquals(currentCreateAccountURL, "http://live.guru99.com/index.php/customer/account/create/");
  }
  
  @Test
  public void TC_02_LoginEmpty() {
//	  	  * Step 01 - Truy cập vào trang: http://live.guru99.com/
	  driver.get("http://live.guru99.com/");
	  
//		  * Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(), 'My Account')]")).click();
	  
//		  * Step 03 - Để trống Username/ Password (using css locator)
	  driver.findElement(By.cssSelector("*[id='email']")).sendKeys("");
	  driver.findElement(By.cssSelector("*[id='pass']")).sendKeys("");
	  	  
//		  * Step 04 - Click Login button
	  driver.findElement(By.xpath("//*[@class='button' and @name='send']")).click();
	  
//		  * Step 05 - Verify error message xuất hiện tại 2 field:  This is a required field.
	  String ErrorMessEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
	  String ErrorMessPass	= driver.findElement(By.id("advice-required-entry-pass")).getText();
	
	  Assert.assertEquals(ErrorMessEmail, "This is a required field.");
	  Assert.assertEquals(ErrorMessPass,  "This is a required field.");
	  
  }
  
  @Test
  public void TC_03_LoginWithEmailInvalid() {
//	  	  * Step 01 - Truy cập vào trang: http://live.guru99.com/
	  driver.get("http://live.guru99.com/");
	  
//		  * Step 02 - Click vào link "My Account" để tới trang đăng nhập - use XPath AXEs
	  driver.findElement(By.xpath("//a[contains(text(),'Orders and Returns')]//parent::li//preceding-sibling::li/a")).click();
	  
//		  * Step 03 - Nhập email invalid: 123434234@12312.123123
	  driver.findElement(By.id("email")).sendKeys("12323@754.345");
	  
//		  * Step 04 - Click Login button
	  driver.findElement(By.id("send2")).click();
	  
//		  * Step 05 - Verify error message xuất hiện:  Please enter a valid email address. For example johndoe@domain.com.
	  String ErrorMessEmailInvalid = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
	  Assert.assertEquals(ErrorMessEmailInvalid, "Please enter a valid email address. For example johndoe@domain.com.");
	 
	  
  }
  
  @Test
  public void TC_04_LoginWithPassLessThanSixChars() {
//	      * Step 01 - Truy cập vào trang: http://live.guru99.com/
	  driver.get("http://live.guru99.com/");
	  
//		  * Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[contains(@class, 'footer')]//a[@title='My Account']")).click();
	  	  
//		  * Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123123
	  driver.findElement(By.cssSelector("*[id='email']")).sendKeys("automation@gmail.com");
	  driver.findElement(By.cssSelector("*[id='pass']")).sendKeys("123");
	  
//		  * Step 04 - Click Login button
	  driver.findElement(By.xpath("//*[@class='button' and @name='send']")).click();
	  
//		  * Step 05 - Verify error message xuất hiện: Please enter 6 or more characters without leading or trailing spaces.
	  String ErrMessValidatePass = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  Assert.assertEquals(ErrMessValidatePass, "Please enter 6 or more characters without leading or trailing spaces.");
  }
  
  @Test
  public void TC_05_LoginWithPassIncorrect() {
//	  	  * Step 01 - Truy cập vào trang: http://live.guru99.com/
	  driver.get("http://live.guru99.com/");
	  
//		  * Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[contains(@class, 'footer')]//a[@title='My Account']")).click();
	  
//		  * Step 03 - Nhập email correct and password incorrect: automation@gmail.com/ 123123123
	  driver.findElement(By.cssSelector("*[id='email']")).sendKeys("automation@gmail.com");
	  driver.findElement(By.cssSelector("*[id='pass']")).sendKeys("123123123");
	  
//		  * Step 04 - Click Login button
	  driver.findElement(By.xpath("//*[@class='button' and @name='send']")).click();
	  
//		  * Step 05 - Verify error message xuất hiện: Invalid login or password.
	  String errMessInvalidPass = driver.findElement(By.xpath("//div[@class='account-login']//ul[contains(@class,'messages')]//span")).getText();
	  Assert.assertEquals(errMessInvalidPass, "Invalid login or password.");
	  
  }
  
  @Test
  public void TC_06_CreateAnAccount() {
//	  	  * Step 01 - Truy cập vào trang: http://live.guru99.com/
	  driver.get("http://live.guru99.com/");
	  
//		  * Step 02 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  
//		  * Step 03 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  
//		  * Step 04 - Nhập thông tin hợp lệ vào tất cả các field: First Name/ Last Name/ Email Address/ Password/ Confirm Password
//		      * (Lưu ý: Tạo random cho dữ liệu tại field Email Address)
	  
	  driver.findElement(By.id("firstname")).sendKeys("Le");
	  driver.findElement(By.id("lastname")).sendKeys("Phuong");
	  driver.findElement(By.id("email_address")).sendKeys("phuongle_" + randomNumberForEmail() + "@gmail.com");
	  driver.findElement(By.id("password")).sendKeys("1234567");
	  driver.findElement(By.id("confirmation")).sendKeys("1234567");
	  
//		  * Step 05 - Click REGISTER button
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  
//		  * Step 06 - Verify message xuất hiện khi đăng kí thành công: Thank you for registering with Main Website Store.
	  String successMess = driver.findElement(By.xpath("//*[contains(@class,'success-msg')]//span")).getText();
	  Assert.assertEquals(successMess, "Thank you for registering with Main Website Store.");
	  
//		  * Step 07 - Logout khỏi hệ thống
	  driver.findElement(By.xpath("//a[contains(@class,'skip-account')]")).click();
	  driver.findElement(By.xpath("//*[@id='header-account']//a[contains(@title,'Log Out')]")).click();
	  	  
//		  * Step 08 - Kiểm tra hệ thống navigate về Home page sau khi logout thành công
	  Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
  }
  
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

  public int randomNumberForEmail() {
	 Random random = new Random();
	 int random_num = random.nextInt(999);
	 System.out.println("Random number is: " + random_num);
	 return random_num;
  }
  
}
