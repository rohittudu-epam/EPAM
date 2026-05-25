@API
Feature: User API Operations
  As a developer, I want to manage users via API
  so that I can create and retrieve user data programmatically.

  Scenario: Create a new user via API
    Given an API request payload with name "John Doe" and email "john@example.com"
    When the create user API is called
    Then the API response status code should be 201
    And the response body should contain the user name "John Doe"

  Scenario: Retrieve user details via API
    Given an existing user with id 42
    When the get user API is called
    Then the API response status code should be 200
    And the response body should contain the user id 42
