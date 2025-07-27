//this class cz its eventuallly common to all pages
//this will beparent to pages

package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//toaster - by
	//productcards - by
	public void waitForElementToAppear(By FindBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated((FindBy)));
		
	}
	
	
	  public void waitForWebElementToAppear(WebElement findBy) {
	  
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOf((findBy)));
	  
	  
	  }
	 
	
	//loading - findby
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(3000);
		
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.invisibilityOf((ele)));
		 */
		
	}
	
}
