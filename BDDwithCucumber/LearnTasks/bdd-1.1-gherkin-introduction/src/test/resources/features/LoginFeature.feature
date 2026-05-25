Feature: User Login
  As a registered user
  I want to log into the system
  So that I can access my personalized dashboard

  Background:
    Given User is on the login page

  Scenario: User logs in with valid credentials
    When User enters valid username and password
    Then User should be redirected to the dashboard

  Scenario: User attempts to log in with empty credentials
    When User submits the login form without entering username and password
    Then User should see a validation error message

  Scenario: Account is locked after multiple failed login attempts
    When User enters incorrect password 5 times consecutively
    Then User account should be locked
    And User should see an account lockout message

  Scenario: User uses Remember Me functionality
    When User enters valid username and password
    And User selects the Remember Me option
    Then User should be redirected to the dashboard
    And User session should persist after browser restart

  Scenario Outline: User attempts to log in with invalid credentials
    When User enters "<username>" and "<password>"
    Then User should see "<message>"

    Examples:
      | username        | password        | message                                  |
      | wrong_user      | Pass@1234       | Invalid username or password             |
      | john_doe        | wrongpassword   | Invalid username or password             |
      |                 | Pass@1234       | Username is required                     |
      | john_doe        |                 | Password is required                     |
      | ' OR '1'='1    | ' OR '1'='1    | Invalid username or password             |
      | john_doe        | Pass@12345678901234567890123456789012345 | Invalid username or password             |