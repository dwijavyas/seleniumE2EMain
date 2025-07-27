//hard coded main test with java streams and 
//then converted into pom/testng-listeners, retry/cucumber/extent&cucumber reports
//json data reader, global prop, jenkins, mvn 

package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Main1 {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		String name = "ZARA COAT 3";
		String country = "India";
		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
		driver.findElement(By.id("userEmail")).sendKeys("ronu@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Hello@123");
		driver.findElement(By.id("login")).click();
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//find and select product
		List<WebElement> productCards = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement product = productCards.stream().filter(productName -> productName.findElement(By.tagName("b")).getText()
							.contains(name)).findFirst().orElse(null);
		
		//add to cart
		product.findElement(By.cssSelector("button.btn.w-10.rounded")).click();
	
		//wait for toaster
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//go to cart icon
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		//check item is in the cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(name));
		Assert.assertTrue(match);
		
		//click checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
	
		//select country - india
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), country).build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-results button"));
		
		countryList.stream()
	    .filter(co -> co.getText().trim().equals(country))
	    .findFirst()
	    .ifPresent(WebElement::click);
	
		
		//click on place order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//get order successfully placed msg
		String msg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals("THANKYOU FOR THE ORDER.",msg);
		
		driver.quit();
	
	
	
	
	
	}
	

}
