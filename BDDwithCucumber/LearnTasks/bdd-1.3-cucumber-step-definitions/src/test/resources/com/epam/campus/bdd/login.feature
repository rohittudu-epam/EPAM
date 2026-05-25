@login
Feature: User Login
  As a registered user
  I want to log in to the application
  So that I can access my account

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters username "admin" and password "admin123"
    And the user clicks the login button
    Then the user should be redirected to the dashboard
    And the welcome message should display "Welcome, admin!"

  Scenario: Failed login with invalid password
    Given the user is on the login page
    When the user enters username "admin" and password "wrongpass"
    And the user clicks the login button
    Then an error message should be displayed with text "Invalid username or password"

  Scenario: Failed login with empty credentials
    Given the user is on the login page
    When the user enters username "" and password ""
    And the user clicks the login button
    Then an error message should be displayed with text "Username and password are required"

  Scenario: Successful logout after login
    Given the user is on the login page
    When the user enters username "admin" and password "admin123"
    And the user clicks the login button
    Then the user should be redirected to the dashboard
    When the user clicks the logout button
    Then the user should be redirected to the login page
