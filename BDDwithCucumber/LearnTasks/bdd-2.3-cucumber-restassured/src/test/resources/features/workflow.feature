Feature: End-to-End API Workflow
  As an API consumer
  I want to chain multiple API operations together
  So that I can automate real-world workflows

  Scenario: Complete CRUD workflow - Create, Read, Update, Delete
    Given the API base URL is configured
    # Create a user
    When I send a POST request to "/api/users" with body:
      | name | Workflow User  |
      | job  | QA Engineer    |
    Then the response status code should be 201
    And I store the response field "id" as "userId"
    # Update the user
    When I send a PUT request to the stored resource "/api/users/{userId}" with body:
      | name | Updated Workflow User |
      | job  | Senior QA Engineer    |
    Then the response status code should be 200
    And the response should contain field "name" with value "Updated Workflow User"
    # Delete the user
    When I send a DELETE request to the stored resource "/api/users/{userId}"
    Then the response status code should be 204

  Scenario: Authentication and resource access workflow
    Given the API base URL is configured
    # Login to get token
    When I send a POST request to "/api/login" with body:
      | email    | eve.holt@reqres.in |
      | password | cityslicka         |
    Then the response status code should be 200
    And I store the response field "token" as "authToken"
    # Access resource after authentication
    When I send a GET request to "/api/users/1"
    Then the response status code should be 200
    And the response JSON path "data.id" should be 1
    And the response JSON path "data.email" should be "george.bluth@reqres.in"
