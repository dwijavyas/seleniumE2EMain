package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponents.AbstractComponents;

public class DashboardPage extends AbstractComponents {

	WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
				
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> productCards;
	
	@FindBy(css=".ng-animating")
	WebElement loading;
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartIcon;
	
	By productBy = By.cssSelector(".mb-3");
	
	By toastBy = By.cssSelector("#toast-container");
	
	By cartBy = By.cssSelector("button.btn.w-10.rounded");
	
	@FindBy(css="[routerLink*='myorders']")
	WebElement clickOnOrders;
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productBy);
		return productCards;
		
	}
	
	public WebElement getProductName(String productName) {
		
		WebElement product = getProductList().stream().filter(pName -> pName.findElement(By.tagName("b")).getText()
			.contains(productName)).findFirst().orElse(null);
		
		return product;
		
	}
	
	public void addToCart(String productName) throws InterruptedException {
		
		WebElement prod = getProductName(productName);
		prod.findElement(cartBy).click();
		waitForElementToAppear(toastBy);
		waitForElementToDisappear(loading);
	}
	
	public CartPage goToCart() {
		
		cartIcon.click();
		CartPage cp = new CartPage(driver);
		return cp;
		
	}
	
	public OrdersPage goToOrders() {
		
		clickOnOrders.click();
		OrdersPage op = new OrdersPage(driver);
		return op;
	}

	
	}
	
	

