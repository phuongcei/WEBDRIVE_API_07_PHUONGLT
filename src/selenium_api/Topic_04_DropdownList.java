package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_DropdownList {

	WebDriver driver;
	WebDriverWait waitExplicit;

//	Drop list Job Role 01
	By xpathDrop1 = By.xpath("//select[@id='job1']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);

//		System.setProperty("webdriver.chrome.driver", "C:/Phuongcei/workspace/WEBDRIVE_API_07_PHUONGLT/lib/chromedriver.exe");
//		driver=new ChromeDriver();

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
	public void TC_02_HandleCustomDropListJQuery() throws InterruptedException {
//	Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/selectmenu/default.html
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

//	Step 02 - Chọn item cuối cùng: số 19
		String xpathParent = "//span[@id='number-button']";

		// xpath need to point to parent Dropdown list first
		String xpathElement = "//ul[@id='number-menu']//div[@class='ui-menu-item-wrapper']";
		String ExpectedItem = "19";

		selectItemInDropdownList(xpathParent, xpathElement, ExpectedItem);

//	Step 03 - Kiểm tra item đã được chọn thành công
		driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()= '" + ExpectedItem + "']")).isDisplayed();
		

	}

	@Test
	public void TC_03_HandleCustomDropListAngular() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public void selectItemInDropdownList(String xpathParent, String xpathElement, String ExpectedItem) {
		//===============
//				xpathParent - là xpath cha = xpath của dropdrown list chứa các options 
//				xpathElement - là xpath đại diện (general xpath) cho các options trong Element - tương đối 
//							không phải xpath tuyệt đối đến 1 option cụ thể nào.
//				ExpectedItem - value của option mình muốn chọn.
		//===============		
		
				// Click on dropdown list
				WebElement element = driver.findElement(By.xpath(xpathParent));
				element.click();

				// Create a list to contain all options in dropdown list
				List<WebElement> optionList = driver.findElements(By.xpath(xpathElement));

				// Wait for all elements in dropdown list are loaded
				waitExplicit.until(ExpectedConditions.visibilityOfAllElements(optionList));

				// Go through the list, getText() and compare to expected item
				// optionList is list contains WebElement => Declare an WebElement: optionElement to go through the list
				for (WebElement optionElement : optionList) {
					String textItem = optionElement.getText();
					System.out.println("Option value: " + textItem);

					// Check if option value is as expectation. If yes, select that option.
					if (textItem.equals(ExpectedItem)) {
						optionElement.click();
						break;
					}
				}

			}


}
