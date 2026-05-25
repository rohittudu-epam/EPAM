Feature: DemoBlaze E-Commerce Functionality
  As a user of the DemoBlaze website
  I want to browse products, manage cart, and complete purchases
  So that I can buy electronics online

  Scenario: Browse Laptops category and verify products are displayed
    Given the user is on the DemoBlaze home page
    When the user clicks on the "Laptops" category
    Then the user should see a list of laptop products

  Scenario: Add a product to the cart
    Given the user is on the DemoBlaze home page
    When the user clicks on the "Laptops" category
    And the user clicks on a product "Sony vaio i5"
    And the user clicks the "Add to cart" button
    Then an alert confirms the product was added to the cart
    When the user navigates to the cart
    Then the product "Sony vaio i5" should be visible in the cart

  Scenario: Remove a product from the cart
    Given the user is on the DemoBlaze home page
    When the user clicks on the "Laptops" category
    And the user clicks on a product "Sony vaio i5"
    And the user clicks the "Add to cart" button
    Then an alert confirms the product was added to the cart
    When the user navigates to the cart
    And the user removes the product "Sony vaio i5" from the cart
    Then the product "Sony vaio i5" should not be visible in the cart

  Scenario: Complete the checkout process
    Given the user is on the DemoBlaze home page
    When the user clicks on the "Laptops" category
    And the user clicks on a product "Sony vaio i5"
    And the user clicks the "Add to cart" button
    Then an alert confirms the product was added to the cart
    When the user navigates to the cart
    And the user clicks "Place Order"
    And the user fills in the order form with the following details:
      | Name    | Country | City     | Card         | Month | Year |
      | John    | USA     | New York | 1234567890   | 12    | 2026 |
    And the user clicks "Purchase"
    Then the user should see a purchase confirmation message

  Scenario: Browse Phones category and verify products are displayed
    Given the user is on the DemoBlaze home page
    When the user clicks on the "Phones" category
    Then the user should see a list of phone products
