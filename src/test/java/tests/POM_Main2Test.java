package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import pages.DashboardPage;
import pages.OrderConfirmationPage;
import pages.CartPage;
import pages.PlaceOrderPage;
import testComponents.BaseTest;

public class POM_Main2Test extends BaseTest{

	
	@Test(groups= {"errorHandling"},retryAnalyzer=testComponents.Retry.class)
	public void loginErrorValidation() {
		
		lp.getLogin("anshika@gmail.com", "Iamking@999");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMsg());
		
		
	}
	
	
	@Test
	public void productErrorValidation() throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		
		DashboardPage dp = lp.getLogin("tonu@gmail.com", "Iamking@000");
		dp.getProductList();
		dp.getProductName(productName);
		dp.addToCart(productName);
		CartPage cp = dp.goToCart();
		cp.getCartItems();
		
		Boolean match = cp.productInCart("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
	
	
	
}
