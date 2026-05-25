@shopping
Feature: Shopping Cart
  As a logged-in user
  I want to manage items in my shopping cart
  So that I can purchase products

  Scenario: Add a single item to the cart
    Given the user is on the login page
    When the user enters username "buyer" and password "buyer123"
    And the user clicks the login button
    Then the user should be redirected to the dashboard
    When the user adds item "Laptop" with price 999.99 to the cart
    Then the cart should contain 1 item
    And the cart total should be 999.99

  Scenario: Add multiple items to the cart
    Given the user is on the login page
    When the user enters username "buyer" and password "buyer123"
    And the user clicks the login button
    Then the user should be redirected to the dashboard
    When the user adds item "Laptop" with price 999.99 to the cart
    And the user adds item "Mouse" with price 29.99 to the cart
    And the user adds item "Keyboard" with price 79.99 to the cart
    Then the cart should contain 3 items
    And the cart total should be 1109.97

  Scenario: Remove an item from the cart
    Given the user is on the login page
    When the user enters username "buyer" and password "buyer123"
    And the user clicks the login button
    Then the user should be redirected to the dashboard
    When the user adds item "Laptop" with price 999.99 to the cart
    And the user adds item "Mouse" with price 29.99 to the cart
    Then the cart should contain 2 items
    When the user removes item "Mouse" from the cart
    Then the cart should contain 1 item
    And the cart total should be 999.99
