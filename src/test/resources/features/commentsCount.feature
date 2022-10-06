Feature: Find vote count for the second store that starts with S
  Scenario: Find comment count for the store
    Given User opens the main page
    When User looks for all stores and obtains sorted list
    And User opens the page for second store that starts with S
    Then User sees correct amount of comment counts for the store
