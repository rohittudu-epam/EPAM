Feature: Login API
  As a user of the application
  I want to authenticate via the Login API
  So that I can access protected resources

  Scenario: Successful login with valid credentials
    Given the API base URL is configured
    When I send a POST request to "/api/login" with body:
      | email    | eve.holt@reqres.in |
      | password | cityslicka         |
    Then the response status code should be 200
    And the response should contain a "token" field

  Scenario: Login failure with missing password
    Given the API base URL is configured
    When I send a POST request to "/api/login" with body:
      | email | eve.holt@reqres.in |
    Then the response status code should be 400
    And the response should contain field "error" with value "Missing password"

  Scenario: Login failure with invalid credentials
    Given the API base URL is configured
    When I send a POST request to "/api/register" with body:
      | email    | sydney@fife |
      | password | pistol      |
    Then the response status code should be 400
    And the response should contain field "error" with value "Note: Only defined users succeed registration"
