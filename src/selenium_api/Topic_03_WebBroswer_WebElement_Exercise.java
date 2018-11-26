package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_WebBroswer_WebElement_Exercise {

	WebDriver driver;

	By EmailByXpathId = By.xpath("//*[@id='mail']");
	By Under18ByXpathId = By.xpath("//*[@id='under_18']");
	By EducationByXpathId = By.xpath("//*[@id='edu']");
	By JobRoleByXpathId = By.xpath("//*[@id='job1']");
	By DevelopmentByXpathId = By.xpath("//*[contains(@id, 'development')]");
	By Slider01ByXpathId = By.xpath("//*[@id='slider-1']");
	By ButtonEnabledByXpathId = By.xpath("//*[@id='button-enabled']");
	
	By PassByXpathId = By.xpath("//*[@id='password']");
	By BiographyByXpathId = By.xpath("//*[@id='bio']");
	By JobRole2ByXpathId = By.xpath("//*[@id='job2']");
	By Slider02ByXpathId = By.xpath("//*[@id='slider-2']");
	By RadioAgeByXpathId = By.xpath("//*[@id='radio-disabled']");
	By CheckboxDisByXpathId = By.xpath("//*[@id='check-disbaled']");
	By ButtonDisByXpathId = By.xpath("//*[@id='button-disabled']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_CheckElementIsDisplayed() {
//			Step 01 - Truy cáº­p vÃ o trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");

//			Step 02 - Kiá»ƒm tra cÃ¡c pháº§n tá»­ sau hiá»ƒn thá»‹ trÃªn trang: Email/ Age (Under 18)/ Education
//			Step 03 - Náº¿u cÃ³ nháº­p giÃ¡ trá»‹: Automation Testing vÃ o 2 field Email/ Education vÃ  chá»�n Age = Under 18
		if (CheckElementDisplayed(EmailByXpathId)) {
			driver.findElement(EmailByXpathId).sendKeys("Automation Testing");
		}

		if (CheckElementDisplayed(Under18ByXpathId)) {
			driver.findElement(Under18ByXpathId).click();
		}

		if (CheckElementDisplayed(EducationByXpathId)) {
			driver.findElement(EducationByXpathId).sendKeys("Automation Testing");
		}

	}

	@Test
	public void TC_02_CheckElementIsEnabledOrDisabled() {
//			Step 01 - Truy cáº­p vÃ o trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");

//			Step 02 - Kiá»ƒm tra cÃ¡c pháº§n tá»­ sau enable trÃªn trang: Email/ Age (Under 18)/ Education/ Job Role 01/ Interests (Development)/ Slider 01/ Button is enabled
		Assert.assertTrue(driver.findElement(EmailByXpathId).isEnabled());
		Assert.assertTrue(driver.findElement(Under18ByXpathId).isEnabled());
		Assert.assertTrue(driver.findElement(EducationByXpathId).isEnabled());
		Assert.assertTrue(driver.findElement(JobRoleByXpathId).isEnabled());
		Assert.assertTrue(driver.findElement(DevelopmentByXpathId).isEnabled());
		Assert.assertTrue(driver.findElement(Slider01ByXpathId).isEnabled());
		Assert.assertTrue(driver.findElement(ButtonEnabledByXpathId).isEnabled());

//			Step 03 - Kiá»ƒm tra cÃ¡c pháº§n tá»­ sau disable trÃªn trang: Password / Age (Radiobutton is disabled)/ Biography/ Job Role 02/ Interests (Checkbox is disabled)/ Slider 02/ Button is disabled
//			
		Assert.assertFalse(driver.findElement(PassByXpathId).isEnabled());
		Assert.assertFalse(driver.findElement(RadioAgeByXpathId).isEnabled());
		Assert.assertFalse(driver.findElement(BiographyByXpathId).isEnabled());
		Assert.assertFalse(driver.findElement(JobRole2ByXpathId).isEnabled());
		Assert.assertFalse(driver.findElement(Slider02ByXpathId).isEnabled());
		Assert.assertFalse(driver.findElement(CheckboxDisByXpathId).isEnabled());
		Assert.assertFalse(driver.findElement(ButtonDisByXpathId).isEnabled());
		
//			Step 04 - Náº¿u cÃ³ in ra Element is enabled/ ngÆ°á»£c láº¡i Element is disabled
		CheckElementEnabled(EmailByXpathId);
		CheckElementEnabled(Under18ByXpathId);
		CheckElementEnabled(EducationByXpathId);
		CheckElementEnabled(JobRoleByXpathId);
		CheckElementEnabled(DevelopmentByXpathId);
		CheckElementEnabled(Slider01ByXpathId);
		CheckElementEnabled(ButtonEnabledByXpathId);
		CheckElementEnabled(PassByXpathId);
		CheckElementEnabled(RadioAgeByXpathId);
		CheckElementEnabled(BiographyByXpathId);
		CheckElementEnabled(JobRole2ByXpathId);
		CheckElementEnabled(Slider02ByXpathId);
		CheckElementEnabled(CheckboxDisByXpathId);
		CheckElementEnabled(ButtonDisByXpathId);
	}

	@Test
	public void TC_03_CheckElementIsSelected() {
//			Step 01 - Truy cáº­p vÃ o trang: https://daominhdam.github.io/basic-form/index.html
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
//			Step 02 - Click chá»�n Age (Under 18)/ Interests (Development)
//		driver.findElement(Under18ByXpathId).click();
		driver.findElement(DevelopmentByXpathId).click();
		
//			Step 03 - Kiá»ƒm tra cÃ¡c pháº§n tá»­ táº¡i Step 02 Ä‘Ã£ Ä‘Æ°á»£c chá»�n
//			Step 04 - Náº¿u chÆ°a Ä‘Æ°á»£c chá»�n thÃ¬ cho phÃ©p chá»�n láº¡i 1 láº§n ná»¯a
		if(!CheckElementSelected(Under18ByXpathId)) {
			driver.findElement(Under18ByXpathId).click();
		} 
		if(!CheckElementSelected(DevelopmentByXpathId)) {
			driver.findElement(DevelopmentByXpathId).click();
		}
	}

	@AfterClass
	public void afterClass() {
//		 driver.quit();

	}

	// Function to check if element 'isDisplayed'
	public boolean CheckElementDisplayed(By xpath) {
		WebElement element = driver.findElement(xpath);

		Boolean tempVar = element.isDisplayed();
		if (tempVar) {
			System.out.println("The element " + xpath + " is displayed!");
			return true;
		} else {
			System.out.println("The element " + xpath + " is NOT displayed!");
			return false;
		}
	}

	// Function to check if element 'isEnabled'
	public boolean CheckElementEnabled(By xpath) {
		WebElement element = driver.findElement(xpath);

		if (element.isEnabled()) {
			System.out.println("The element " + xpath + " is enabled!");
			return true;
		} else {
			System.out.println("The element " + xpath + " is NOT enabled!");
			return false;
		}
	}


	// Function to check if element 'isSelected'
	public boolean CheckElementSelected(By xpath) {
		WebElement element = driver.findElement(xpath);

		if (element.isSelected()) {
			System.out.println("The element " + xpath + " is selected!");
			return true;
		} else {
			System.out.println("The element " + xpath + " is NOT selectet!");
			return false;
		}
	}

}
