package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.DashboardPage;
import testComponents.BaseTest;

public class POM_Main2Test extends BaseTest{

	
	@Test(dataProvider="getData", groups= {"errorHandling"},retryAnalyzer=testComponents.Retry.class)
	public void loginErrorValidation(HashMap<String, String> input) {
		
		lp.getLogin(input.get("email"), input.get("incPswd"));
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMsg());
		
		
	}
	
	
	@Test(dataProvider="getData")
	public void productErrorValidation(HashMap<String, String> input) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		
		DashboardPage dp = lp.getLogin(input.get("email"), input.get("pswd"));
		dp.getProductList();
		dp.getProductName(productName);
		dp.addToCart(productName);
		CartPage cp = dp.goToCart();
		cp.getCartItems();
		
		Boolean match = cp.productInCart("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
	
	
	@DataProvider()
	public Object[][] getData() throws IOException {
		
		
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir", 
				"\\src\\test\\java\\data\\PurchaseOrder.json"));
		
		return new Object[][] {{data.get(2)}, {data.get(3)}};
		
		
	}
	
	
	
}
