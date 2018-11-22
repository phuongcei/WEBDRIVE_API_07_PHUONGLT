package selenium_api;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonClass {
	
	  public static int randomNumberForEmail() {
			 Random random = new Random();
			 int random_num = random.nextInt(999);
			 System.out.println("Random number is: " + random_num);
			 return random_num;
		  }
	  	  
		public static void selectItemInDropdownList(String xpathParent, String xpathElement, String ExpectedItem) {
			//===============
//					xpathParent - là xpath cha = xpath của dropdrown list chứa các options 
//					xpathElement - là xpath đại diện (general xpath) cho các options trong Element - tương đối 
//								không phải xpath tuyệt đối đến 1 option cụ thể nào.
//					ExpectedItem - value của option mình muốn chọn.
			//===============		

					//Declare 
					WebDriver driver = new FirefoxDriver();
					WebDriverWait waitExplicit = new WebDriverWait(driver, 30);
			
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
