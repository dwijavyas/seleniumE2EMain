package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {

	
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	
	}
	
	
	@FindBy(css=".hero-primary")
	WebElement orderMsg;
	
	public String getOrderMsg() {
		
		String msg = orderMsg.getText();
		return msg;
	}
	
	
}
