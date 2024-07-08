
@tag
Feature: Error Validation
  I want to use this template for my feature file



  @ErrorValidation
  Scenario Outline: Title of your scenario outline
  Given I landed on Ecommerce page
    When Logged in with the username <name> and password <password>
    Then "Incorrect email and passowrd." meessage is displayed

   Examples: status  |
       |    name        |password  |
       | ps30@gmail.com |Hello1234|
      