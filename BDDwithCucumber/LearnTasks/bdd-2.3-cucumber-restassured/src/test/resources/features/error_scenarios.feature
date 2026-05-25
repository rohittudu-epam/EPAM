Feature: Error Scenarios
  As an API consumer
  I want to validate that proper error responses are returned
  So that I can handle errors appropriately

  Scenario: Access non-existent user returns 404
    Given the API base URL is configured
    When I send a GET request to "/api/users/999"
    Then the response status code should be 404

  Scenario: Access non-existent resource returns 404
    Given the API base URL is configured
    When I send a GET request to "/api/unknown/23"
    Then the response status code should be 404

  Scenario: Register without password returns 400
    Given the API base URL is configured
    When I send a POST request to "/api/register" with body:
      | email | eve.holt@reqres.in |
    Then the response status code should be 400
    And the response should contain field "error" with value "Missing password"

  Scenario: Delayed response returns data successfully
    Given the API base URL is configured
    When I send a GET request to "/api/users?delay=1"
    Then the response status code should be 200
    And the response JSON path "page" should be 1
