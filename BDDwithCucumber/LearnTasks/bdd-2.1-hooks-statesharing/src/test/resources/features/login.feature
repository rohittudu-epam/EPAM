@UI
Feature: User Login via UI
  As a user, I want to log in through the browser
  so that I can access the application dashboard.

  Scenario: Successful login with valid credentials
    Given a user with username "admin" and password "secret123"
    When the user attempts to login
    Then the login should be successful
    And the user should see the welcome message "Welcome, admin!"

  Scenario: Failed login with invalid credentials
    Given a user with username "admin" and password "wrongpass"
    When the user attempts to login
    Then the login should fail
    And an error message "Invalid credentials" should be displayed
