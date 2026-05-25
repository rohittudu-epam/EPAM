Feature: User Registration
  As a new user
  I want to create an account
  So that I can access the application with my own credentials

  Background:
    Given User is on the registration page

  Scenario Outline: User registration with various inputs
    When User registers with username "<username>", email "<email>", and password "<password>"
    Then User should see "<message>"
    And User should receive "<email_status>"

    Examples:
      | username                        | email                  | password              | message                              | email_status         |
      | john_doe                        | john@example.com       | Pass@1234             | Registration successful              | a confirmation email |
      | jane_doe                        | jane@example.com       | Secure#567!           | Registration successful              | a confirmation email |
      |                                 | nouser@example.com     | Pass@1234             | Username is required                 | no email             |
      | bob_doe                         | invalid-email          | Pass@1234             | Please enter a valid email           | no email             |
      | alice                           | alice@example.com      |                       | Password is required                 | no email             |
      | charlie                         | charlie@test.com       | short                 | Password does not meet criteria      | no email             |
      | john_doe                        | john@example.com       | Pass@1234             | Username or email already exists     | no email             |
      | user@name                       | user@test.com          | Pass@1234             | Username contains invalid characters | no email             |
      | toolongusernamethatexceedslimit | max@test.com           | Pass@1234             | Username exceeds maximum length      | no email             |
      | unicode_user                    | uni@example.com        | Pässwörд@1            | Password does not meet criteria      | no email             |
      | edge_user                       | user@@example.com      | Pass@1234             | Please enter a valid email           | no email             |
      | max_pass_user                   | maxpass@example.com    | A@1aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa | Password exceeds maximum length | no email |