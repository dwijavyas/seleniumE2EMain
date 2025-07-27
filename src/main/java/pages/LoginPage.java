package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents  {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}

	
	@FindBy(id="userEmail")
	WebElement useremail;
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	
	public DashboardPage getLogin(String email, String pswd) {
		
		useremail.sendKeys(email);
		userpassword.sendKeys(pswd);
		login.click();
		DashboardPage dp = new DashboardPage(driver);
		return dp;
		
		
	}
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public String getErrorMsg() {
		
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
