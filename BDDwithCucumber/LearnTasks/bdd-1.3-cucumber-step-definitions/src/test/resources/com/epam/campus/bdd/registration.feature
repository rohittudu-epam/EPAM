@registration
Feature: User Registration
  As a new user
  I want to register for an account
  So that I can use the application

  Scenario: Successful registration with valid details
    Given the user navigates to the registration page
    When the user enters registration details:
      | field     | value              |
      | username  | newuser            |
      | email     | newuser@test.com   |
      | password  | SecurePass123!     |
    And the user submits the registration form
    Then the registration should be successful
    And a confirmation message should display "Registration successful! Please log in."

  Scenario: Registration fails with existing username
    Given the user navigates to the registration page
    When the user enters registration details:
      | field     | value              |
      | username  | admin              |
      | email     | admin2@test.com    |
      | password  | AdminPass123!      |
    And the user submits the registration form
    Then an error message should be displayed with text "Username already exists"

  Scenario: Registration fails with invalid email
    Given the user navigates to the registration page
    When the user enters registration details:
      | field     | value              |
      | username  | testuser           |
      | email     | invalid-email      |
      | password  | TestPass123!       |
    And the user submits the registration form
    Then an error message should be displayed with text "Please enter a valid email address"

  Scenario: Registration fails with weak password
    Given the user navigates to the registration page
    When the user enters registration details:
      | field     | value              |
      | username  | testuser2          |
      | email     | test2@test.com     |
      | password  | 123                |
    And the user submits the registration form
    Then an error message should be displayed with text "Password must be at least 8 characters"
