

@tag
Feature: Purchase the order from ecommerce Website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce page
  @tag2
  Scenario Outline:  Positive test of submitting the order
    Given Logged in with the username <name> and password <password>
    When I add the product <productName> to Cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: status  |
       |    name        |password  | productName |
       | ps30@gmail.com |Hello@1234| ZARA COAT 3|
      
