Feature: CRUD Operations on Users API
  As an API consumer
  I want to perform CRUD operations on user resources
  So that I can manage user data

  Scenario: Create a new user using POST
    Given the API base URL is configured
    When I send a POST request to "/api/users" with body:
      | name | John Doe        |
      | job  | Software Engineer |
    Then the response status code should be 201
    And the response should contain field "name" with value "John Doe"
    And the response should contain field "job" with value "Software Engineer"
    And the response should contain a "id" field
    And the response should contain a "createdAt" field

  Scenario: Retrieve a user using GET
    Given the API base URL is configured
    When I send a GET request to "/api/users/2"
    Then the response status code should be 200
    And the response header "Content-Type" should contain "application/json"
    And the response JSON path "data.id" should be 2
    And the response JSON path "data.email" should be "janet.weaver@reqres.in"
    And the response JSON path "data.first_name" should be "Janet"

  Scenario: Retrieve list of users using GET
    Given the API base URL is configured
    When I send a GET request to "/api/users?page=2"
    Then the response status code should be 200
    And the response JSON path "page" should be 2
    And the response JSON path "per_page" should be 6

  Scenario: Update a user using PUT
    Given the API base URL is configured
    When I send a PUT request to "/api/users/2" with body:
      | name | Jane Updated    |
      | job  | Lead Engineer   |
    Then the response status code should be 200
    And the response should contain field "name" with value "Jane Updated"
    And the response should contain field "job" with value "Lead Engineer"
    And the response should contain a "updatedAt" field

  Scenario: Delete a user using DELETE
    Given the API base URL is configured
    When I send a DELETE request to "/api/users/2"
    Then the response status code should be 204
