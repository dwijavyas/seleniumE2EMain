package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.DashboardPage;
import pages.OrderConfirmationPage;
import pages.OrdersPage;
import pages.PlaceOrderPage;
import testComponents.BaseTest;

public class POM_Main1Test extends BaseTest {

		
		@Test(dataProvider="getData", groups= {"purchaseOrder"})
		public void mainTest(HashMap<String, String> input) throws InterruptedException, IOException {
			

			DashboardPage dp = lp.getLogin(input.get("useremail"), input.get("userpswd"));
			dp.getProductList();
			dp.getProductName(input.get("userproductName"));
			dp.addToCart(input.get("userproductName"));
			CartPage cp = dp.goToCart();
			cp.getCartItems();
			Boolean match = cp.productInCart(input.get("userproductName"));
			Assert.assertTrue(match);
			PlaceOrderPage pop = cp.clickCheckOut();
			pop.selectCountry(input.get("usercountryName"));
			OrderConfirmationPage ocp = pop.clickPlaceOrder();
			ocp.getOrderMsg();
			Assert.assertEquals("THANKYOU FOR THE ORDER.",ocp.getOrderMsg());
			
			
		}
		
		//verify product in order hisotry 
		@Test(dependsOnMethods= {"mainTest"}, dataProvider="getData")
		public void orderHistoryTest(HashMap<String, String> input) {
			
			
			DashboardPage dp = lp.getLogin(input.get("useremail"),input.get("userpswd"));
			OrdersPage op = dp.goToOrders();
			Assert.assertTrue(op.verifyOrderDisplay(input.get("userproductName")));			
		}

		
		@DataProvider()
		public Object[][] getData() throws IOException {

			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+
					"\\src\\test\\java\\data\\PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)} };
			
		}
		
		
		
		
		
	}

	
	
	

