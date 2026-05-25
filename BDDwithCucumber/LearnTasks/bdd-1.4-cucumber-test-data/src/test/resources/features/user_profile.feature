Feature: User Profile Form
  As a registered user
  I want to fill in my profile details
  So that my account information is complete

  Scenario: Fill user profile form with structured data
    Given the user is on the profile page
    When the user fills in the following profile details:
      | Field       | Value              |
      | First Name  | John               |
      | Last Name   | Doe                |
      | Email       | john.doe@email.com |
      | Phone       | +1-555-0123        |
      | City        | New York           |
      | Country     | United States      |
    Then the profile should be updated successfully

  Scenario: Add multiple items to a shopping cart
    Given the user has an empty shopping cart
    When the user adds the following items:
      | Product       | Quantity | Price  |
      | Laptop        | 1        | 999.99 |
      | Mouse         | 2        | 29.99  |
      | Keyboard      | 1        | 79.99  |
      | USB Cable     | 3        | 9.99   |
    Then the cart should contain 4 different products
    And the total quantity should be 7

  Scenario: Validate a list of required form fields
    Given the user is on the registration form
    When the system checks the following required fields:
      | Field Name | Is Filled | Expected Error              |
      | Username   | false     | Username is required        |
      | Email      | false     | Email is required           |
      | Password   | false     | Password is required        |
      | First Name | true      |                             |
    Then validation errors should be displayed for unfilled fields
