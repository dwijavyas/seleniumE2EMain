package stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.OrdersPage;
import pages.PlaceOrderPage;
import testComponents.BaseTest;

public class StepDefinition extends BaseTest {
	
	
		public LoginPage lp;
		public DashboardPage dp;
		public CartPage cp;
		public OrdersPage op;
		public PlaceOrderPage pop;
		public OrderConfirmationPage ocp;	
		String actualMsg;
		String actualError;
	
	
	
		@Given("I landed on ecommerce page")
		public void i_landed_on_ecommerce_page() throws IOException {

			lp = loginApplication();
			
		}

		@Given("Logged in with username {string} and password {string}")
		public void logged_in_with_username_and_password(String username, String password) {

			 dp = lp.getLogin(username, password);
			
		}

		@When("I add product {string} to cart")
		public void i_add_product_to_cart(String productName) throws InterruptedException {

			dp.getProductList();
			dp.getProductName(productName);
			dp.addToCart(productName);
			
		}

		@When("Checkout {string} and place order")
		public void checkout_and_place_order(String productName) {

			cp = dp.goToCart();
			cp.getCartItems();
			
			Boolean match = cp.productInCart(productName);
			Assert.assertTrue(match);
			
			pop = cp.clickCheckOut();
			pop.selectCountry("India");
			ocp = pop.clickPlaceOrder();
			
		}

		@Then("verify message {string}")
		public void verify_message(String expectedMsg) {

			actualMsg = ocp.getOrderMsg();
			Assert.assertEquals(actualMsg,expectedMsg);
			
			
		}
		
		@Then("verify the error {string}")
		public void verify_the_error(String expectedError) {

			actualError =lp.getErrorMsg();
			Assert.assertEquals(actualError,expectedError );
			
			
		}

}
