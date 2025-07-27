package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class PlaceOrderPage extends AbstractComponents{

	WebDriver driver;
	
	public PlaceOrderPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement typeCountry;
	
	By resultsBy = By.cssSelector(".ta-results");
	
	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
	WebElement selectIndia;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(typeCountry, countryName).build().perform();
		waitForElementToAppear(resultsBy);
		selectIndia.click();
		
		
	}
	
	/*
	 * public void selectCountry(String countryName) {
	 * 
	 * List<WebElement> country = searchCountry(countryName);
	 * country.stream().filter(co ->
	 * co.getText().trim().equals(countryName)).findFirst().ifPresent(WebElement::
	 * click);
	 * 
	 * }
	 */
	
	
	public OrderConfirmationPage clickPlaceOrder() {
		
		placeOrder.click();
		OrderConfirmationPage ocp = new OrderConfirmationPage(driver);
		return ocp;
		
		
	}
	
	
	
		
	
}
