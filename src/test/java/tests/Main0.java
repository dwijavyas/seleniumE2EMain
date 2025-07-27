//Hard coded Main code with basic java-selenium framework 
//JS included

package tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main0 {

	public static void main(String[] args) throws InterruptedException {
	
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
		driver.findElement(By.id("userEmail")).sendKeys("ronu@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Hello@123");
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

}
