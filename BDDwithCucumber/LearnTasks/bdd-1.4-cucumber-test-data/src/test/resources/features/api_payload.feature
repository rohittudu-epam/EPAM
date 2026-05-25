Feature: API Payload Processing
  As an API consumer
  I want to send complex JSON payloads
  So that the system processes structured data correctly

  Scenario: Send a JSON payload to create a user via API
    Given the API endpoint "/api/users" is available
    When the user sends a POST request with the following JSON body:
      """
      {
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com",
        "address": {
          "street": "123 Main St",
          "city": "Springfield",
          "state": "IL",
          "zipCode": "62704"
        },
        "roles": ["user", "admin"]
      }
      """
    Then the API should respond with status code 201
    And the response should contain "id" field

  Scenario: Send a multi-line comment for a blog post
    Given the user is on the blog post page
    When the user submits a comment with the following text:
      """
      This is a great article about BDD testing!

      I especially liked the section on Cucumber and Gherkin syntax.
      The examples were very clear and easy to follow.

      Looking forward to more content like this.
      Best regards,
      John
      """
    Then the comment should be saved successfully

  Scenario: Send an XML configuration payload
    Given the configuration endpoint is available
    When the system receives the following XML configuration:
      """
      <?xml version="1.0" encoding="UTF-8"?>
      <configuration>
        <database>
          <host>localhost</host>
          <port>5432</port>
          <name>testdb</name>
        </database>
        <cache>
          <enabled>true</enabled>
          <ttl>3600</ttl>
        </cache>
      </configuration>
      """
    Then the configuration should be parsed successfully
    And the database host should be "localhost"
