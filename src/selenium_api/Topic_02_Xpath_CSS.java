package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

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
	 // driver.get("https://youtu.be/wzFjIDZdRDI");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	  
	  
  }

  @Test
  public void TC_01_VerifyUrlAndTitle() {
//		  *	Step 01 - Truy cập vào trang: http://live.guru99.com/
	  driver.get("http://live.guru99.com/");
	  
//	  	  * Step 02 - Kiểm tra title của page là: "Home page"
	  String titleHomePage = driver.getTitle();
	  Assert.assertEquals(titleHomePage, "Home page");
	 
//		  * Step 03 - Click vào link "My Account" để tới trang đăng nhập
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  
//		  * Step 04 - Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  
//		  * Step 05 - Back lại trang đăng nhập
	  driver.navigate().back();
	  
//		  * Step 06 - Kiểm tra url của page đăng nhập là: http://live.guru99.com/index.php/customer/account/login/
	  String currentLoginURL = driver.getCurrentUrl();
	  Assert.assertEquals(currentLoginURL, "http://live.guru99.com/index.php/customer/account/login/");
	  
//		  * Step 07 - Forward tới trang tạo tài khoản
	  driver.navigate().forward();
	  
//		  * Step 08 - Kiểm tra url của page tạo tài khoản là: http://live.guru99.com/index.php/customer/account/create/
	  String currentCreateAccountURL = driver.getCurrentUrl();
	  Assert.assertEquals(currentCreateAccountURL, "http://live.guru99.com/index.php/customer/account/create/");
  }
  
  @Test
  public void TC_02_LoginEmpty() {
	  
  }
  
  @Test
  public void TC_03_LoginWithEmailInvalid() {
	  
  }
  
  @Test
  public void TC_04_LoginWithPassLessThanSixChars() {
	  
  }
  
  @Test
  public void TC_05_LoginWithPassIncorrect() {
	  
  }
  
  @Test
  public void TC_02_CreateAnAccount() {
	  
  }
  
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
