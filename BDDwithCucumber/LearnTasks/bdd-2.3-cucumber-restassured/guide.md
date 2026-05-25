# Implementation Guide - Cucumber RestAssured Integration

This guide explains all the requirements from the task and how they have been implemented.

---

## Project Overview

This project integrates **RestAssured** with **Cucumber** to automate and validate RESTful API workflows. It uses [reqres.in](https://reqres.in) as the target API, which is a free hosted REST API for testing and prototyping.

---

## Task 1: Set Up RestAssured in a Cucumber Project

### Requirements
- Add dependencies for RestAssured and Cucumber.
- Validate setup by testing a simple GET request.

### Implementation

**pom.xml** has been configured with the following dependencies:

| Dependency | Version | Purpose |
|---|---|---|
| `cucumber-java` | 7.23.0 | Cucumber step definitions |
| `cucumber-junit` | 7.23.0 | JUnit runner for Cucumber |
| `rest-assured` | 5.4.0 | HTTP client for API testing |
| `json-path` | 5.4.0 | JSON response parsing |
| `junit` | 4.13.2 | Test assertions |
| `org.json` | 20231013 | JSON request body construction |

**Configuration**: API base URL is managed via `src/test/resources/config.properties` and loaded by `ConfigManager.java`.

---

## Task 2: Feature Files for RESTful APIs

### Requirements
- Write Gherkin feature files covering Login, CRUD, and Error scenarios.

### Implementation

Four feature files are located in `src/test/resources/features/`:

### 1. `login.feature`
Tests authentication workflows:
- **Successful login** - POST to `/api/login` with valid credentials, validates token is returned.
- **Missing password** - POST without password, validates `400` and error message.
- **Invalid credentials** - Registration with undefined user, validates `400` and error message.

### 2. `crud_operations.feature`
Tests all CRUD operations:
- **CREATE (POST)** - Creates a user at `/api/users`, validates `201`, and checks `id` and `createdAt` fields.
- **READ (GET)** - Retrieves user at `/api/users/2`, validates `200`, checks `Content-Type` header, verifies JSON fields via JsonPath.
- **READ List (GET)** - Retrieves paginated users, validates pagination fields.
- **UPDATE (PUT)** - Updates user at `/api/users/2`, validates `200` and updated fields.
- **DELETE** - Deletes user at `/api/users/2`, validates `204 No Content`.

### 3. `error_scenarios.feature`
Tests error handling:
- **404 Not Found** - Access non-existent user (`/api/users/999`).
- **404 Not Found** - Access non-existent resource (`/api/unknown/23`).
- **400 Bad Request** - Register without password.
- **Delayed response** - Tests API with delay parameter.

### 4. `workflow.feature`
Tests end-to-end chained workflows:
- **Full CRUD workflow** - Creates a user, stores the ID, uses it to update and then delete.
- **Authentication + Resource access** - Logs in, stores token, then accesses a protected resource.

---

## Task 3: Step Definitions

### Requirements
- Map Gherkin steps to RestAssured operations.
- Validate status codes, headers, and JSON body content.
- Make step definitions reusable.

### Implementation

**`ApiStepDefinitions.java`** contains all step definitions:

| Step Pattern | RestAssured Operation |
|---|---|
| `Given the API base URL is configured` | Sets `RestAssured.baseURI` from config |
| `When I send a GET request to {endpoint}` | `request.when().get(endpoint)` |
| `When I send a POST request to {endpoint} with body:` | `request.body(json).when().post(endpoint)` |
| `When I send a PUT request to {endpoint} with body:` | `request.body(json).when().put(endpoint)` |
| `When I send a DELETE request to {endpoint}` | `request.when().delete(endpoint)` |
| `Then the response status code should be {code}` | `assertEquals(code, response.getStatusCode())` |
| `And the response header {name} should contain {value}` | Validates header presence and content |
| `And the response JSON path {path} should be {value}` | Uses JsonPath to extract and compare |
| `And I store the response field {field} as {key}` | Stores values for chaining requests |

### Key Design Decisions:
- **DataTable** is used for request bodies, making steps readable and reusable.
- **Stored values map** enables chaining by saving response fields (IDs, tokens) for use in subsequent requests.
- **Template resolution** (`{userId}`) replaces placeholders in endpoints with stored values.
- **Separate int/string JSON path assertions** handle type-safe comparisons.

---

## Task 4: Automate Real-World API Workflows

### Requirements
- Chain requests using response data.
- Automate authentication and CRUD workflows.

### Implementation

The `workflow.feature` demonstrates:

1. **CRUD Chaining**:
   - POST creates a resource → stores `id`
   - PUT updates using stored `id`
   - DELETE removes using stored `id`

2. **Authentication Flow**:
   - POST to login → stores `token`
   - Subsequent GET uses the authenticated session

The `storedValues` HashMap in step definitions enables this chaining pattern.

---

## Task 5: Validate Error Scenarios

### Requirements
- Test `401 Unauthorized` and `404 Not Found`.
- Validate both error codes and messages.

### Implementation

The `error_scenarios.feature` covers:
- **404 responses** for non-existent resources (users and unknown endpoints).
- **400 responses** for malformed requests (missing required fields).
- Error message validation using `the response should contain field "error" with value "..."`.

Note: reqres.in returns `400` for authentication errors rather than `401`, as it's a mock API. The scenarios are adapted accordingly while testing the same concepts.

---

## Project Structure

```
src/
├── test/
│   ├── java/com/epam/campus/bdd/
│   │   ├── config/
│   │   │   └── ConfigManager.java          # Loads API config from properties
│   │   ├── runner/
│   │   │   └── TestRunner.java             # JUnit Cucumber runner
│   │   └── steps/
│   │       └── ApiStepDefinitions.java     # All step definitions
│   └── resources/
│       ├── config.properties               # API base URL configuration
│       └── features/
│           ├── login.feature               # Authentication scenarios
│           ├── crud_operations.feature     # CRUD operation scenarios
│           ├── error_scenarios.feature     # Error handling scenarios
│           └── workflow.feature            # End-to-end workflow scenarios
```

---

## How to Run

### Prerequisites
- Java 17+
- Maven 3.6+

### Execute Tests
```bash
mvn clean test
```

### Run Specific Feature
```bash
mvn test -Dcucumber.features=src/test/resources/features/login.feature
```

### View Reports
After execution, HTML reports are generated at:
```
target/cucumber-reports/cucumber.html
```

---

## Configuration

The API base URL is configured in `src/test/resources/config.properties`:
```properties
base.url=https://reqres.in
```

To change the target API, update this file. The `ConfigManager` class loads it automatically at runtime.

---

## Key Technologies

| Technology | Role |
|---|---|
| Cucumber 7.23 | BDD framework with Gherkin syntax |
| RestAssured 5.4 | HTTP client for API testing |
| JUnit 4.13 | Test runner and assertions |
| Maven | Build and dependency management |
| reqres.in | Target mock REST API |
