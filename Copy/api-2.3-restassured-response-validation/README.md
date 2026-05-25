# RestAssured API Response Validation - Complete Guide

This project demonstrates comprehensive API response validation using **RestAssured**, **Maven**, and **TestNG**. It covers status codes, headers, body content, JsonPath extraction, schema validation, and performance benchmarking.

---

## Project Overview

This is a fully implemented REST API testing project that validates various aspects of API responses using the JSONPlaceholder API (`https://jsonplaceholder.typicode.com`) as a test target.

### Tools & Technologies
- **RestAssured 5.5.5** - REST API testing library
- **TestNG 7.11.0** - Testing framework
- **Maven 3.x** - Build tool
- **Java 17** - Programming language
- **JSON Schema Validator** - Schema validation support

---

## Project Structure

```
api-validation-project/
│
├── pom.xml                           # Maven configuration with dependencies
├── README.md                         # This file
├── ActionPlan.md                     # Project planning document
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/epam/campus/restassured/
│   │
│   └── test/
│       ├── java/
│       │   └── com/epam/campus/restassured/
│       │       ├── base/
│       │       │   ├── BaseTest.java                    # Base test class
│       │       │   ├── RequestSpecBuilderUtil.java      # Request specification builder
│       │       │   ├── ResponseSpecBuilderUtil.java     # Response specification builder
│       │       │   └── ResponseUtils.java               # Utility methods
│       │       │
│       │       ├── listeners/
│       │       │   └── TestListener.java                # Test execution listener
│       │       │
│       │       └── tests/
│       │           ├── status/
│       │           │   └── StatusCodeTests.java
│       │           ├── headers/
│       │           │   └── HeaderValidationTests.java
│       │           ├── body/
│       │           │   └── BodyValidationTests.java
│       │           ├── jsonpath/
│       │           │   └── JsonPathValidationTests.java
│       │           ├── schema/
│       │           │   └── SchemaValidationTests.java
│       │           ├── performance/
│       │           │   └── PerformanceTests.java
│       │           └── integration/
│       │               └── CombinedValidationTests.java
│       │
│       └── resources/
│           ├── testng.xml                              # TestNG configuration
│           ├── schemas/
│           │   ├── post-schema.json
│           │   ├── posts-array-schema.json
│           │   └── user-schema.json
│           └── testdata/
│               └── test-config.properties
```

---

## Installation & Setup

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Internet connection

### Installation Steps

```bash
cd api-validation-project
mvn clean install
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

---

## Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn test -Dtest=StatusCodeTests
mvn test -Dtest=HeaderValidationTests
mvn test -Dtest=BodyValidationTests
mvn test -Dtest=JsonPathValidationTests
mvn test -Dtest=SchemaValidationTests
mvn test -Dtest=PerformanceTests
mvn test -Dtest=CombinedValidationTests
```

### Run Specific Test Method
```bash
mvn test -Dtest=StatusCodeTests#validateGetPostStatusCode
```

---

## Test Coverage

### 1. Status Code Validation (StatusCodeTests.java)
Validates HTTP status codes for various scenarios:
- `validateGetPostStatusCode()` - Verify GET returns 200 OK
- `validateCreatePostStatusCode()` - Verify POST returns 201 Created
- `validateInvalidEndpointReturns404()` - Verify invalid endpoints return 404
- `validateBadRequestReturns400()` - Verify bad requests return 400

### 2. Header Validation (HeaderValidationTests.java)
Validates response headers:
- `validateContentTypeHeader()` - Verify Content-Type is application/json
- `validateCacheControlHeader()` - Verify Cache-Control header
- `validateContentLengthHeaderExists()` - Verify Content-Length header
- `validateAllHeadersPresent()` - Verify all expected headers
- `validateSpecificHeaderValue()` - Verify specific header values

### 3. Body Validation (BodyValidationTests.java)
Validates response body content:
- `validateSinglePostFields()` - Verify all required fields exist
- `validatePostIdEqualsOne()` - Verify specific field values
- `validatePostTitleNotNull()` - Verify fields are not null
- `validateResponseContainsExpectedKeys()` - Verify expected keys
- `validateBodyContent()` - Validate content using matchers

### 4. JsonPath Validation (JsonPathValidationTests.java)
Dynamically extract and validate JSON data:
- `extractTitleUsingJsonPath()` - Extract single values
- `validatePostsCountForUser()` - Count array elements
- `validateAllPostsBelongToUser()` - Validate array content
- `validateNestedJsonFields()` - Extract nested fields
- `iterateAndValidateArrayElements()` - Iterate and validate arrays
- `extractAndValidateMultipleValues()` - Extract multiple values

### 5. Schema Validation (SchemaValidationTests.java)
Validates responses against JSON schemas:
- `validateSinglePostSchema()` - Validate single post structure
- `validatePostsArraySchema()` - Validate array structure
- `validateUserSchema()` - Validate user object structure
- `validateSchemaWithAssertions()` - Combined schema & field validation

### 6. Performance Testing (PerformanceTests.java)
Measures and validates response times:
- `validateResponseTimeUnder200ms()` - Single endpoint performance
- `validateResponseTimeUsingAssertions()` - Using built-in matchers
- `validateMultipleEndpointsPerformance()` - Multiple endpoints
- `logResponseTime()` - Log response times
- `validateAverageResponseTime()` - Calculate average time
- `validatePerformanceWithLargePayload()` - Large payload performance

### 7. Integration Tests (CombinedValidationTests.java)
Complete workflow tests combining all validations:
- `validatePostEndpointEndToEnd()` - Complete validation of single endpoint
- `validateUserWorkflowScenario()` - User retrieval workflow
- `validatePostsWorkflowScenario()` - Posts retrieval workflow
- `validateErrorScenarios()` - Error handling validation

---

## Base Classes & Utilities

### BaseTest.java
Base class for all test classes:
```java
protected RequestSpecification requestSpec;
protected ResponseSpecification responseSpec;

@BeforeClass
public void setUp(){
    requestSpec = RequestSpecBuilderUtil.getRequestSpec();
}
```

### RequestSpecBuilderUtil.java
Builds reusable request specifications with base URI and headers.

### ResponseSpecBuilderUtil.java
Builds reusable response specifications with expected status and content type.

### ResponseUtils.java
Utility methods for response handling:
- `getJsonValue()` - Extract value using JsonPath
- `extractList()` - Extract list from response
- `getStringValue()` - Get string value
- `getIntValue()` - Get integer value
- `getBooleanValue()` - Get boolean value

### TestListener.java
Listener for test execution events with logging and statistics.

---

## JSON Schema Files

### post-schema.json
Defines schema for a single post object with userId, id, title, and body fields.

### posts-array-schema.json
Defines schema for array of posts.

### user-schema.json
Defines schema for user object with nested address and company fields.

---

## Configuration Files

### testng.xml
TestNG configuration defining test suite structure, test groupings, and listener.

### test-config.properties
Configuration properties for base URI, performance thresholds, and test data.

---

## Key Features

✅ Comprehensive Validation - Status codes, headers, body content, schema, performance
✅ JsonPath Extraction - Dynamically extract and validate nested JSON data
✅ Schema Validation - Validate responses against JSON schemas
✅ Performance Monitoring - Track response times and benchmarks
✅ Reusable Components - Base classes, utilities, and builders
✅ Modular Structure - Tests organized by validation type
✅ Test Listener - Automatic logging and reporting
✅ Best Practices - Follows Maven and TestNG conventions

---

## Troubleshooting

### Issue: "jsonschema file not found"
- Ensure schema files are in `src/test/resources/schemas/`
- Verify file names match exactly in code

### Issue: "Connection refused"
- Verify JSONPlaceholder API is accessible
- Check internet connectivity

### Issue: "Test timeout"
- Increase timeout in pom.xml if API is slow

---

## Maven Commands

```bash
mvn clean install          # Clean and install
mvn clean test             # Run all tests
mvn test -X                # Run with verbose output
mvn clean install -DskipTests  # Skip tests during build
mvn test -Dtest=*Validation*   # Run tests with pattern
mvn surefire-report:report # Generate test report
mvn clean package          # Package the project
```

---

## Best Practices Implemented

✅ DRY Principle - Reusable specifications and utilities
✅ Single Responsibility - Each test class has specific focus
✅ Clear Naming - Descriptive test method names
✅ Comprehensive Assertions - Multiple validation points per test
✅ Logging - Detailed output for debugging
✅ Maintainability - Organized structure
✅ Version Control - Proper project structure

---

## Next Steps

Potential improvements:
1. Add database validation
2. Implement API mocking
3. Add performance profiling
4. Create CI/CD pipeline integration
5. Add API security testing
6. Implement API load testing
7. Add API contract testing

---

## Contributing

To add new tests:
1. Create a new test class extending `BaseTest`
2. Use `@Test` annotation with descriptive names
3. Follow the existing pattern for assertions
4. Add appropriate schema files if needed

---

## License

This project is provided for educational purposes.

---

## Support

For issues or questions:
1. Check the Troubleshooting section
2. Review test output logs
3. Verify JSON schemas match API responses
4. Ensure JSONPlaceholder API is accessible

---

**Happy Testing! 🚀**
