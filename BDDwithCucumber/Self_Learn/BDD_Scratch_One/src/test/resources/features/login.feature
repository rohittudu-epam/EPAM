Feature: Login Functionality

    Background:
        Given user is on login page

    Scenario: Successful login with valid credentials
        When user logs in with username "standard_user" and password "secret_sauce"
        And clicks on login button
        Then user should be navigated to inventory page

    Scenario: Login fails with invalid password
        When user logs in with username "standard_user" and password "wrong_password"
        And clicks on login button
        Then error message should be displayed "Epic sadface: Username and password do not match any user in this service"

    Scenario: Login fails with locked out user
        When user logs in with username "locked_out_user" and password "secret_sauce"
        And clicks on login button
        Then error message should be displayed "Epic sadface: Sorry, this user has been locked out."

    Scenario: Login fails with empty credentials
        When user logs in with username "" and password ""
        And clicks on login button
        Then error message should be displayed "Epic sadface: Username is required"