@Regression
Feature: User Management End-to-End
  As an administrator, I want to register users and verify their details
  so that I can manage user accounts reliably.

  Scenario: Register a new user and verify stored details
    Given a new user with name "Alice Smith" and email "alice@example.com" and role "admin"
    When the user is registered in the system
    Then the user should exist in the system
    And the user's role should be "admin"
    And the user's email should be "alice@example.com"

  @UI @Regression
  Scenario: Register a user via UI and verify via API
    Given a new user with name "Bob Jones" and email "bob@example.com" and role "editor"
    When the user is registered in the system
    Then the user should exist in the system
    And the user's role should be "editor"
