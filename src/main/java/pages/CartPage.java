package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="div[class='cartSection'] h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	
	public List<WebElement> getCartItems() {
		
		return cartProducts;
		
	}
	
	public Boolean productInCart(String productName) {
		
		Boolean match = getCartItems().stream().anyMatch(cp -> cp.getText().equalsIgnoreCase(productName));
		return match;
	}

	public PlaceOrderPage clickCheckOut() {
		
		checkOut.click();
		PlaceOrderPage pop = new PlaceOrderPage(driver);
		return pop;
		
	}
	
	
}
