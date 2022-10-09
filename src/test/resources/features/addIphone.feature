Feature: Add iphone by searching the wrong keyword
  @addIphone
  Scenario: Add two iphones to cart by searching ayfon and try to buy
    Given User opens the n11 main page
    When User searches for the ayfon user sees the iphones
    And User adds the first and last iphone to cart from the first page
    And User adds one more of the cheapest iphone
    And User tries to buy them with wrong credentials
    Then User sees the credentials are invalid