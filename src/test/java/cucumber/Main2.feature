

Feature: Validating errors

	@InvalidTest
  Scenario Outline: Negative test for login
    Given I landed on ecommerce page
    When Logged in with username "<username>" and password "<password>"
    Then verify the error "Incorrect email or password."

    Examples: 
      | username  		 | password |
      | tonu@gmail.com |  Iamking |
