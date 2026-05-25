Feature: User Registration
  As a new user
  I want to register with valid credentials
  So that I can access the system

  Scenario Outline: Register with different input combinations
    Given the user is on the registration page
    When the user enters username "<username>" email "<email>" and password "<password>"
    Then the registration result should be "<result>"

    Examples:
      | username   | email                  | password    | result                          |
      | john_doe   | john@example.com       | Pass@1234   | Registration successful         |
      | jane_smith | jane.smith@company.org | Str0ng!Pass | Registration successful         |
      | alice99    | alice99@domain.net     | Al!ce#2024  | Registration successful         |
      |            | empty@example.com      | Pass@1234   | Username is required            |
      | bob        | invalid-email          | Pass@1234   | Invalid email format            |
      | charlie    | charlie@example.com    |             | Password is required            |
      | dave       | dave@example.com       | short       | Password must be at least 8 characters |
      | eve        | eve@example.com        | nouppercase1! | Password must contain uppercase letter |
