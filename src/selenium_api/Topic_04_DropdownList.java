package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_DropdownList {

	WebDriver driver;
	WebDriverWait waitExplicit;
	
	 String showSelected1, showSelected2;

	JavascriptExecutor javaExecutor;

//	Drop list Job Role 01
	By xpathDrop1 = By.xpath("//select[@id='job1']");

	@BeforeClass
	public void beforeClass() {

		 driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver",
//				"C:/Phuongcei/workspace/WEBDRIVE_API_07_PHUONGLT/lib/chromedriver.exe");
//		driver = new ChromeDriver();

		// Wait elements are loaded
		waitExplicit = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		javaExecutor = (JavascriptExecutor) driver;

	}

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

	public void TC_02_HandleCustomDropListJQuery() throws InterruptedException {
//	Step 01 - Truy cập vào trang: http://jqueryui.com/resources/demos/selectmenu/default.html
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

//	Step 02 - Chọn item cuối cùng: số 19
		String xpathParent = "//span[@id='number-button']";

		// xpath need to point to parent Dropdown list first
		String xpathElement = "//ul[@id='number-menu']//div[@class='ui-menu-item-wrapper']";
		String ExpectedItem = "19";
		String xpathScrollElement = "//label[contains(text(), 'Select a speed')]";
		
		selectItemInDropdownList(xpathScrollElement, xpathParent, xpathElement, ExpectedItem);

//	Step 03 - Kiểm tra item đã được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath(
				"//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()= '" + ExpectedItem + "']"))
				.isDisplayed());

		Thread.sleep(3000);
	}

	public void TC_03_HandleCustomDropListAngular() throws InterruptedException {
		/*
		 * Go to angular examples page scroll to 'Select with reset option/mat-select'
		 * Click on droplist Scroll to 'Kentucky' and select it Verify selected option
		 */

		System.out.println("Handle custom dropdown list of Angular");
		driver.get("https://material.angular.io/components/select/examples");

		String xpathScrollElement = "//div[contains(text(), 'Select with reset option')]";
		String xpathParent = "//mat-select[@id='mat-select-5']";
		String xpathElement = "//span[@class='mat-option-text']";
		String ExpectedItem = "Kentucky";

		selectItemInDropdownList(xpathScrollElement, xpathParent, xpathElement, ExpectedItem);

		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='mat-option-text' and text()='" + ExpectedItem + "']"))
						.isDisplayed());

		Thread.sleep(3000);

	}

	public void TC_04_HandleCustomDropListTelerik() throws InterruptedException {

		/*
		 * Go to telerik site Select Orange in CAP COLOR dropbox; verify correct
		 * selected Select L - 7 1/8" in CAP SIZE; verify correct selected
		 */

		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");

		String xpathScrollElement ="//div[@id='cap']";
		String xpathParentColor = "//span[@aria-owns='color_listbox']";
		String xpathElementColor = "//ul[@id='color_listbox']//li";
		String ExpectedItemColor = "Orange";
		selectItemInDropdownList(xpathScrollElement, xpathParentColor, xpathElementColor, ExpectedItemColor);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='k-input' and text()='Orange']")).isDisplayed());

		String xpathParentSize = "//span[@aria-owns='size_listbox']";
		String xpathElementSize = "//ul[@id='size_listbox']/li";
		String ExpectedItemSize = "L - 7 1/8\"";
		selectItemInDropdownList(xpathScrollElement, xpathParentSize, xpathElementSize, ExpectedItemSize);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='k-input' and text()='" + ExpectedItemSize + "']"))
				.isDisplayed());

		Thread.sleep(3000);
	}
	
	public void TC_05_HandleCustomDropListVue() throws InterruptedException {
		/* Go to Vue dropdown link
		 * Select Third Option 
		 * Verify selected 
		 *  */
		
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		String xpathScrollElement = "//div[@id='app']/div/li";
		String xpathParent = "//div[@id='app']/div/li";
		String xpathElement = "//ul[@class='dropdown-menu']/li";
		String ExpectedItem = "Third Option";
		
		selectItemInDropdownList(xpathScrollElement, xpathParent, xpathElement, ExpectedItem);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']//li[contains(text(), 'Third Option')]")).isDisplayed());
		
//		Thread.sleep(3000);
	}
	
	public void TC_06_HandleCustomEditableDropList() {
		
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("BMW");
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys(Keys.TAB);
		
		// Here we used .getAttribute to verify if 'BMW' is displayed, instead of .isDisplayed()
		// The reason is that the 'BMW' is in in-visible (show only when clicking on droplist).
		// => cannot use isDisplayed().
		// But we know that if it is selected, that li will have class attribute value = es-visible selected (only es-visible if clicking, instead of sending key: TAB)
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']//li[contains(text(), 'BMW')]")).getAttribute("class"), "es-visible selected");
	
	}
	
	@Test
	public void TC_07_HandleMultipleSelect() throws InterruptedException {
		/*
		 * http://wenzhixin.net.cn/p/multiple-select/docs/
		 * <4: show name selected 
		 * >=4, <12: x of 12 selected
		 * =12 or Select all :  -> show All selected and tick Select all  
		 */
		
		driver.get("http://wenzhixin.net.cn/p/multiple-select/docs/");
		
		// scroll to //h3[@id='the-keep-open']
		scrollToSelectItem("//h3[@id='the-keep-open']");
		
		// Click on parent droplist
		Actions act = new Actions(driver);
		act.click(driver.findElement(By.xpath("//*[@id='e21']/div/button")));
		
//		driver.findElement(By.xpath("//*[@id='e21']/div/button")).click();
		Thread.sleep(2000);
		
		// xpath element in droplist: //p[@id='e21']//label
		List <WebElement> elementList = driver.findElements(By.xpath("//p[@id='e21']//label/span"));
		
		// select 3 - May, August, October
		
		
		for(WebElement optionValue : elementList) {
			System.out.println("Option value: " + optionValue.getText());
				String optionSelectValue = optionValue.getText();
				if(optionSelectValue.equals("May") || optionSelectValue.equals("August") || optionSelectValue.equals("October")) {
					optionValue.click();
				}
				 showSelected1 = driver.findElement(By.xpath("//p[@id='e21']/div/button/span")).getText();
				
				if(optionSelectValue.equals("December")) {
					optionValue.click();
				}
				 showSelected2 = driver.findElement(By.xpath("//p[@id='e21']/div/button/span")).getText();
				
		}
		
		Assert.assertEquals(showSelected1, "May, August, October");
		Assert.assertEquals(showSelected2, "4 of 12 selected");
		
		// Select 5
		
		Thread.sleep(5000);
		
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public void selectItemInDropdownList(String xpathScrollElement, String xpathParent, String xpathElement, String ExpectedItem) {

		// ===============
//				xpathParent - là xpath cha = xpath của dropdrown list chứa các options 
//				xpathElement - là xpath đại diện (general xpath) cho các options trong Element - tương đối 
//							không phải xpath tuyệt đối đến 1 option cụ thể nào.
//				ExpectedItem - value của option mình muốn chọn.
		// ===============

		// Find the scroll element
		WebElement element = driver.findElement(By.xpath(xpathScrollElement));
		
		// Scroll to Scroll element - This is element is used to avoid the case when having a banner cover on
		javaExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		
		// Click on dropdown list
		element.click();

		// Create a list to contain all options in dropdown list
		List<WebElement> optionList = driver.findElements(By.xpath(xpathElement));

		// Wait for all elements in dropdown list are loaded
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(optionList));

		// Go through the list, getText() and compare to expected item
		// optionList is list contains WebElement => Declare an WebElement:
		// optionElement to go through the list
		for (WebElement optionElement : optionList) {
			String textItem = optionElement.getText();
			System.out.println("Option value: " + textItem);

			// Check if option value is as expectation. If yes, select that option.
			if (textItem.equals(ExpectedItem)) {
				javaExecutor.executeScript("arguments[0].scrollIntoView(true);", optionElement);
				optionElement.click();
				break;
			}
		}

	}

	public void scrollToSelectItem(String xpathScrollToElement) {
		
		WebElement element = driver.findElement(By.xpath(xpathScrollToElement));
	
		javaExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
}
