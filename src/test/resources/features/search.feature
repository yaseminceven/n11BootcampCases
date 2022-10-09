Feature: Search for phone
  @searchPhone
  Scenario: Search for phone, sort and list free shipments
    Given User opens the n11 main page
    When User search for telefon
    And User sorts the results according to comment count and selects free shipping option
    Then User sees the relevant results