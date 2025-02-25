@tag
Feature: To create a purchase order in an e-commerce application
  
Background:
Given i landed on ECommerce page
 

  @tag2
  Scenario Outline: Postive case of purchase order
    Given Logged in with <username> and <password>
    When I add the <productName> in the cart
    And checkout <productName> and select <country> and submit order
    Then "Thankyou for the order."  success message is displayed in the confirmation page

    Examples: 
      | username  			| password | productName  | country |
      |vijay619@gmail.com|Vijay@619.| ADIDAS ORIGINAL| India |
