Feature: Purchase the order from ecommerce

Background: User landend to login page
    Given User landing to logged ecommerce
    When User input email "simanjuntakalbert57@gmail.com" and password "XBf@rWNvByn!#K8"
    Then User redirect to homepage

Scenario: Buyer Create New order
    When Buyer add product to Cart ZARA COAT 3
    And Buyer redirect to cart page 
    Then Verify Product successfully added  on cart page ZARA COAT 3
    When Buyer redirect to checkout page
    And Buyer select country Indonesia
    And Buyer click button submit
    Then Buyer will see message is displayed on confirmation page THANKYOU FOR THE ORDER.