Feature: Login Negative Scenarios and Edge Cases
  As a QA engineer
  I want to test login with invalid inputs
  So that the system handles errors gracefully

  # Edge cases and negative scenarios using Scenario Outline
  Scenario Outline: Login with invalid credentials
    Given the user is on the login page
    When the user attempts to login with email "<email>" and password "<password>"
    Then the login should fail with message "<error_message>"

    Examples: Invalid email formats
      | email              | password   | error_message              |
      | plainaddress       | Pass@1234  | Invalid email format       |
      | @missinglocal.com  | Pass@1234  | Invalid email format       |
      | user@.com          | Pass@1234  | Invalid email format       |
      | user@com           | Pass@1234  | Invalid email format       |
      | user name@mail.com | Pass@1234  | Invalid email format       |

    Examples: Missing or empty fields
      | email              | password   | error_message              |
      |                    | Pass@1234  | Email is required          |
      | user@example.com   |            | Password is required       |
      |                    |            | Email is required          |

    Examples: Boundary and special character inputs
      | email                                                              | password                          | error_message                     |
      | a@b                                                                | Pass@1234                         | Invalid email format              |
      | user@example.com                                                   | ab                                | Password too short                |
      | user@example.com                                                   | aB1!aB1!aB1!aB1!aB1!aB1!aB1!aB1! | Password too long                 |
      | user@example.com                                                   | <script>alert(1)</script>         | Invalid characters in password    |

  # Negative scenario with DataTable for batch validation
  Scenario: Validate multiple invalid login attempts
    Given the user is on the login page
    When the following login attempts are made:
      | email              | password    | expected_error                  |
      | not-an-email       | Pass@1234   | Invalid email format            |
      | user@example.com   | wrong       | Password too short              |
      |                    | Pass@1234   | Email is required               |
      | user@example.com   |             | Password is required            |
      | test@test.com      | correctPass | Invalid credentials             |
    Then all login attempts should display appropriate error messages

  # Edge case: SQL injection and XSS attempts
  Scenario Outline: Reject malicious input in login fields
    Given the user is on the login page
    When the user attempts to login with email "<email>" and password "<password>"
    Then the login should fail with message "<error_message>"

    Examples:
      | email                          | password               | error_message                  |
      | ' OR '1'='1                    | Pass@1234              | Invalid email format           |
      | user@example.com               | ' OR '1'='1           | Invalid credentials            |
      | <script>alert('xss')</script>  | Pass@1234              | Invalid email format           |
      | user@example.com               | <img src=x onerror=alert(1)> | Invalid characters in password |

  # Edge case: Account lockout after multiple failed attempts
  Scenario: Account lockout after consecutive failed login attempts
    Given the user is on the login page
    And the account "locked@example.com" exists
    When the user fails to login 5 times consecutively with email "locked@example.com"
    Then the account should be temporarily locked
    And the error message should be "Account locked. Try again after 30 minutes."
