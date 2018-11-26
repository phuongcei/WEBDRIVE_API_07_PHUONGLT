package selenium_api;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
//					xpathParent - lÃ  xpath cha = xpath cá»§a dropdrown list chá»©a cÃ¡c options 
//					xpathElement - lÃ  xpath Ä‘áº¡i diá»‡n (general xpath) cho cÃ¡c options trong Element - tÆ°Æ¡ng Ä‘á»‘i 
//								khÃ´ng pháº£i xpath tuyá»‡t Ä‘á»‘i Ä‘áº¿n 1 option cá»¥ thá»ƒ nÃ o.
//					ExpectedItem - value cá»§a option mÃ¬nh muá»‘n chá»�n.
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
		
		public static void scrollToSelectItem(String xpathScrollToElement) {
			WebDriver driver = new FirefoxDriver();
			
			JavascriptExecutor javaExecutor;
			javaExecutor = (JavascriptExecutor) driver;
			
			WebElement element = driver.findElement(By.xpath(xpathScrollToElement));
		
			javaExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
			
		}

		public static void clickElementByJS(WebElement element, WebDriver driver) {
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", element);
		}
}
