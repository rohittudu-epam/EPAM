# Project Implementation Documentation

## Overview

This document describes the complete implementation of the **BDD Cucumber Test Data Management** project. The project demonstrates managing test data within Gherkin feature files using **Scenario Outlines**, **DataTables**, and **Doc Strings**, along with comprehensive negative scenario and edge case coverage.

---

## Project Structure

```
bdd-1.4-cucumber-test-data/
├── pom.xml
├── README.md
├── .gitignore
├── src/
│   ├── main/java/com/epam/campus/bdd/       # Main source (empty - BDD test project)
│   └── test/
│       ├── java/com/epam/campus/bdd/
│       │   ├── RunCucumberTest.java          # Cucumber test runner
│       │   ├── RegistrationSteps.java        # Step defs for Task 1
│       │   ├── UserProfileSteps.java         # Step defs for Task 2
│       │   ├── ApiPayloadSteps.java          # Step defs for Task 3
│       │   └── LoginNegativeSteps.java       # Step defs for Task 4
│       └── resources/features/
│           ├── registration.feature          # Task 1: Scenario Outline
│           ├── user_profile.feature          # Task 2: DataTables
│           ├── api_payload.feature           # Task 3: Doc Strings
│           └── login_negative.feature        # Task 4: Negative/Edge Cases
```

---

## Dependencies Added to pom.xml

The original `pom.xml` only had `cucumber-java`. The following dependencies were added to enable test execution:

| Dependency                          | Version | Purpose                                      |
|-------------------------------------|---------|----------------------------------------------|
| `cucumber-java`                     | 7.23.0  | Cucumber step definition annotations (existing) |
| `cucumber-junit-platform-engine`    | 7.23.0  | Cucumber engine for JUnit Platform            |
| `junit-platform-suite`              | 1.11.4  | `@Suite` annotation for test runner           |
| `junit-jupiter`                     | 5.11.4  | JUnit 5 test framework                        |

---

## Task 1: Scenario Outline with Examples Table

**Feature File:** `src/test/resources/features/registration.feature`
**Step Definition:** `RegistrationSteps.java`

### What was done:
- Created a **Scenario Outline** for a user registration workflow.
- Used `<username>`, `<email>`, `<password>` placeholders with an `Examples` table containing **8 input combinations**.
- Covers both valid registrations and error cases:
  - 3 valid registration sets
  - 5 invalid combinations (empty username, invalid email format, empty password, short password, missing uppercase)

### Validation logic implemented:
- Username required check
- Email format validation via regex
- Password required, minimum length (8 chars), and uppercase letter checks

---

## Task 2: DataTables for Structured Inputs

**Feature File:** `src/test/resources/features/user_profile.feature`
**Step Definition:** `UserProfileSteps.java`

### What was done:
- Created **3 scenarios** using DataTables:

1. **Fill user profile form** - A 2-column DataTable (Field/Value) with 6 profile fields (First Name, Last Name, Email, Phone, City, Country). Step definition maps each row into a HashMap and validates required fields exist.

2. **Add items to shopping cart** - A 3-column DataTable (Product/Quantity/Price) with 4 items. Validates total product count (4) and total quantity sum (7).

3. **Validate required form fields** - A 3-column DataTable (Field Name/Is Filled/Expected Error) that dynamically checks which fields are unfilled and collects validation errors for those.

---

## Task 3: Doc Strings for Multi-Line Data

**Feature File:** `src/test/resources/features/api_payload.feature`
**Step Definition:** `ApiPayloadSteps.java`

### What was done:
- Created **3 scenarios** using Doc Strings:

1. **JSON API Payload** - Sends a nested JSON body (with address object and roles array) to a simulated `/api/users` endpoint. Validates 201 status code and presence of "id" field in response.

2. **Multi-line blog comment** - Submits a multi-line text comment (8 lines with blank lines). Validates the comment is saved successfully.

3. **XML configuration payload** - Receives an XML configuration with nested elements (database, cache). Parses the XML and validates the database host value is correctly extracted.

---

## Task 4: Negative Scenarios and Edge Cases

**Feature File:** `src/test/resources/features/login_negative.feature`
**Step Definition:** `LoginNegativeSteps.java`

### What was done:
- Created comprehensive negative test coverage using multiple Gherkin constructs:

1. **Invalid email formats** (Scenario Outline with Examples) - 5 test cases: plain text, missing local part, dot after @, missing TLD dot, spaces in email.

2. **Missing/empty fields** (Scenario Outline with Examples) - 3 test cases: empty email, empty password, both empty.

3. **Boundary and special character inputs** (Scenario Outline with Examples) - 4 test cases: malformed email, password too short (2 chars), password too long (33 chars), script tags in password.

4. **Batch validation with DataTable** - 5 invalid login attempts validated in a single scenario with expected errors per row.

5. **SQL Injection & XSS attempts** (Scenario Outline with Examples) - 4 test cases: SQL injection in email/password, XSS script tags, XSS via img tags. Ensures malicious input is rejected.

6. **Account lockout** - Simulates 5 consecutive failed login attempts and verifies the account is locked with appropriate message.

### Security validation in step definitions:
- Regex-based email format validation
- Character blacklist for `<` and `>` in passwords
- Length boundary checks (min 6, max 30)

---

## Test Runner

**File:** `RunCucumberTest.java`

- Uses `@Suite` + `@IncludeEngines("cucumber")` (JUnit Platform approach)
- Configured via `@ConfigurationParameter` annotations:
  - Glue code: `com.epam.campus.bdd`
  - Features: `src/test/resources/features`
  - Plugin: `pretty` (readable console output)

---

## Build and Test Results

**Command:** `mvn clean test`

```
Tests run: 32, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### Test breakdown by feature:
| Feature File            | Scenarios | Test Cases |
|-------------------------|-----------|------------|
| registration.feature    | 1 outline | 8 examples |
| user_profile.feature    | 3         | 3          |
| api_payload.feature     | 3         | 3          |
| login_negative.feature  | 5         | 18         |
| **Total**               | **12**    | **32**     |

---

## Key Design Decisions

1. **Simulated backend logic** - Since this is a BDD/Gherkin-focused task, step definitions contain simulated validation logic rather than actual HTTP/UI interactions.
2. **Regex for email validation** - Used pattern `^[\w.+-]+@[\w-]+\.[\w.]+$` for email format checks.
3. **Security-aware test data** - Task 4 includes SQL injection and XSS payloads to verify input sanitization.
4. **Separated Examples blocks** - In `login_negative.feature`, Examples tables are split by category (invalid emails, missing fields, boundary cases) for readability.
5. **JUnit Platform Engine** - Chose `cucumber-junit-platform-engine` over the older `cucumber-junit` for modern JUnit 5 compatibility.
