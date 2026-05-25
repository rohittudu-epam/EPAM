Feature: Login Feature

  Scenario: Successful Login
    Given User on the login page
    When User enter valid credentials
    Then User should be redirected to the dashboard

  Scenario: Unsuccessful Login with Invalid Credentials
    Given User on the login page
    When User enter invalid credentials
    And User click on the login button
    Then User should see an error message

  Scenario: Unsuccessful Login with Empty Fields
    Given User on the login page
    When User leave the username and password fields empty
    And User click on the login button
    Then User should see a validation error message