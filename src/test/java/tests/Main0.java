//Hard coded Main code with basic java-selenium framework 
//JS included

package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testComponents.BaseTest;

public class Main0 extends BaseTest{

	@Test(dataProvider="getdata")
	public void main0(HashMap<String, String> input) throws InterruptedException {
	
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
		driver.findElement(By.id("userEmail")).sendKeys(input.get("email"));
		driver.findElement(By.id("userPassword")).sendKeys(input.get("pswd"));
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".card")));
		
		//expected product list
		List<String> expectedList = new ArrayList<String>();
		expectedList.add("ZARA COAT 3");
		expectedList.add("ADIDAS ORIGINAL");
		expectedList.add("IPHONE 13 PRO");
		
		List<WebElement> productCards = driver.findElements(By.cssSelector(".card"));
		
		//enhanced for loop
		for(WebElement productNames:productCards) {
			
			String name = productNames.findElement(By.tagName("b")).getText();
			
			if(expectedList.contains(name)) {
				
				System.out.println("Adding Product:"+name);
				
				WebElement addToCart = productNames.findElement(By.cssSelector("button.btn.w-10.rounded"));
				
				try {
				
					addToCart.click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
								
				
				}
				catch(org.openqa.selenium.ElementClickInterceptedException e){
				
					System.out.println("Standard click failed for "+name+"trying click via JS");
					((JavascriptExecutor) driver).executeScript("arguments[0].click", addToCart);
				
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
												
				}			
				catch(Exception e){
				
					System.out.println("Error clicking on "+name+ ":"+e.getMessage());
				
				}
				
			}
				
			
			
		}
		
		
	}

	
	@DataProvider()
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}};
		
	}


}
