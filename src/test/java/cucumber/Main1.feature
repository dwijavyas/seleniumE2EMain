

Feature: Purchase the product from ecommerce website

	Background:
		Given I landed on ecommerce page
		
  @ValidTest
  Scenario Outline: Positive test of submitting order
    Given Logged in with username "<username>" and password "<password>" 
    When I add product "<productName>" to cart
    And Checkout "<productName>" and place order
    Then verify message "THANKYOU FOR THE ORDER."
    
    Examples: 
      | 	username     |     password  | productName |
      | ronu@gmail.com |     Hello@123 | ZARA COAT 3 |
