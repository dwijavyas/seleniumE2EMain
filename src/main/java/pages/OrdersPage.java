package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {

	
WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
				
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean verifyOrderDisplay(String productName) {

		Boolean match = productNames.stream().anyMatch(ohp -> ohp.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	
	
}
