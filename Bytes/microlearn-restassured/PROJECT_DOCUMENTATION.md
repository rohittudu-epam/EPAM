# REST Assured API Testing Framework - Project Documentation

## Table of Contents
- [Project Overview](#project-overview)
- [Directory Structure](#directory-structure)
- [File Mapping](#file-mapping)
- [Class and Method Documentation](#class-and-method-documentation)
  - [Base Package](#base-package)
  - [Constants Package](#constants-package)
  - [POJO Classes](#pojo-classes)
  - [Test Classes](#test-classes)

---

## Project Overview

This project is a REST Assured-based API testing framework designed to test a banking application's REST APIs. The framework uses TestNG for test execution and follows a modular architecture with POJO classes for request/response handling.

**Technology Stack:**
- **REST Assured**: 5.5.0
- **TestNG**: 7.10.2
- **Jackson Databind**: 2.18.1
- **Java**: Maven-based project

---

## Directory Structure

```
microlearn-restassured/
├── pom.xml
├── README.md
├── testng.xml
├── PROJECT_DOCUMENTATION.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── epam/
    │               └── campus/
    │                   └── App.java
    └── test/
        └── java/
            └── com/
                └── epam/
                    └── campus/
                        ├── AccountsManagementTests.java
                        ├── AuthenticationTests.java
                        ├── BankAccountTests.java
                        ├── OperationsManagementTests.java
                        ├── UserManagementTests.java
                        ├── Base/
                        │   └── BaseTest.java
                        ├── client/
                        │   └── (empty - for future API clients)
                        ├── constants/
                        │   └── ApiEndPoints.java
                        └── pojo/
                            ├── AuthRequest.java
                            ├── AuthResponse.java
                            ├── BankAccountResponse.java
                            ├── OperationResponse.java
                            ├── RegisterRequest.java
                            └── UserResponse.java
```

---

## File Mapping

| File Path | Purpose |
|-----------|---------|
| `src/test/java/com/epam/campus/Base/BaseTest.java` | Base test class with common setup and utility methods |
| `src/test/java/com/epam/campus/constants/ApiEndPoints.java` | API endpoint constants |
| `src/test/java/com/epam/campus/pojo/AuthRequest.java` | Authentication request POJO |
| `src/test/java/com/epam/campus/pojo/AuthResponse.java` | Authentication response POJO |
| `src/test/java/com/epam/campus/pojo/BankAccountResponse.java` | Bank account entity POJO |
| `src/test/java/com/epam/campus/pojo/OperationResponse.java` | Operation entity POJO |
| `src/test/java/com/epam/campus/pojo/RegisterRequest.java` | Registration request POJO |
| `src/test/java/com/epam/campus/pojo/UserResponse.java` | User entity POJO |
| `src/test/java/com/epam/campus/AuthenticationTests.java` | Authentication API tests |
| `src/test/java/com/epam/campus/UserManagementTests.java` | User management API tests |
| `src/test/java/com/epam/campus/BankAccountTests.java` | Bank account API tests |
| `src/test/java/com/epam/campus/OperationsManagementTests.java` | Operations API tests |
| `src/test/java/com/epam/campus/AccountsManagementTests.java` | Account registration & password reset tests |

---

## Class and Method Documentation

---

### Base Package

#### `com.epam.campus.Base.BaseTest`

**Description:** Base test class that provides common setup and utility methods for all API tests. Handles authentication and provides authenticated request specifications.

**Fields:**

| Field | Type | Description |
|-------|------|-------------|
| `BASE_URI` | `String` | Base URI for the API (`http://localhost:8080`) |
| `ADMIN_USERNAME` | `String` | Admin username for authentication |
| `ADMIN_PASSWORD` | `String` | Admin password for authentication |
| `authToken` | `String` | JWT authentication token |
| `requestSpec` | `RequestSpecification` | Base request specification |
| `authenticatedRequestSpec` | `RequestSpecification` | Request specification with auth token |

**Methods:**

| Method Signature | Annotations | Description |
|-----------------|-------------|-------------|
| `public void setup()` | `@BeforeClass` | Sets up base configuration for RestAssured and authenticates with the API. Runs once before all tests in each test class. |
| `protected String authenticate(String username, String password)` | - | Authenticates with the API and returns the JWT token. |
| `protected RequestSpecification getRequestSpecWithToken(String token)` | - | Creates a request specification with a specific bearer token. |
| `protected String generateUniqueName(String prefix)` | - | Generates a unique string for creating unique test data. |

---

### Constants Package

#### `com.epam.campus.constants.ApiEndPoints`

**Description:** Contains static constants for all API endpoint paths.

**Constants:**

| Constant | Value | Description |
|----------|-------|-------------|
| `OPERATIONS` | `/api/operations` | Operations endpoint |
| `BANK_ACCOUNTS` | `/api/bank-accounts` | Bank accounts endpoint |
| `USERS` | `/api/admin/users` | Admin users endpoint |
| `REGISTER` | `/api/register` | User registration endpoint |
| `AUTH` | `/api/authenticate` | Authentication endpoint |
| `RESET_PASSWORD` | `/api/account/reset-password/init` | Password reset endpoint |

---

### POJO Classes

#### `com.epam.campus.pojo.AuthRequest`

**Description:** POJO class representing an Authentication request payload.

**Annotations:** `@JsonIgnoreProperties(ignoreUnknown = true)`

**Fields:**

| Field | Type | JSON Property | Annotation |
|-------|------|---------------|------------|
| `username` | `String` | `username` | `@JsonProperty("username")` |
| `password` | `String` | `password` | `@JsonProperty("password")` |
| `rememberMe` | `Boolean` | `rememberMe` | `@JsonProperty("rememberMe")` |

**Methods:**

| Method Signature | Description |
|-----------------|-------------|
| `public AuthRequest()` | Default constructor |
| `public AuthRequest(String username, String password, Boolean rememberMe)` | Constructor with all fields |
| `public String getUsername()` | Gets the username |
| `public void setUsername(String username)` | Sets the username |
| `public String getPassword()` | Gets the password |
| `public void setPassword(String password)` | Sets the password |
| `public Boolean getRememberMe()` | Gets the rememberMe flag |
| `public void setRememberMe(Boolean rememberMe)` | Sets the rememberMe flag |
| `public String toString()` | Returns string representation |

---

#### `com.epam.campus.pojo.AuthResponse`

**Description:** POJO class representing an Authentication response payload.

**Annotations:** `@JsonIgnoreProperties(ignoreUnknown = true)`

**Fields:**

| Field | Type | JSON Property | Annotation |
|-------|------|---------------|------------|
| `idToken` | `String` | `id_token` | `@JsonProperty("id_token")` |

**Methods:**

| Method Signature | Description |
|-----------------|-------------|
| `public AuthResponse()` | Default constructor |
| `public AuthResponse(String idToken)` | Constructor with token |
| `public String getIdToken()` | Gets the JWT token |
| `public void setIdToken(String idToken)` | Sets the JWT token |
| `public String toString()` | Returns string representation |

---

#### `com.epam.campus.pojo.BankAccountResponse`

**Description:** POJO class representing a Bank Account entity for API request/response handling.

**Annotations:** `@JsonIgnoreProperties(ignoreUnknown = true)`

**Fields:**

| Field | Type | JSON Property | Annotation |
|-------|------|---------------|------------|
| `id` | `Long` | `id` | `@JsonProperty("id")` |
| `name` | `String` | `name` | `@JsonProperty("name")` |
| `balance` | `Double` | `balance` | `@JsonProperty("balance")` |
| `user` | `UserResponse` | `user` | `@JsonProperty("user")` |

**Methods:**

| Method Signature | Description |
|-----------------|-------------|
| `public BankAccountResponse()` | Default constructor |
| `public BankAccountResponse(String name, Double balance)` | Constructor with required fields |
| `public BankAccountResponse(Long id, String name, Double balance, UserResponse user)` | Full constructor |
| `public Long getId()` | Gets the account ID |
| `public void setId(Long id)` | Sets the account ID |
| `public String getName()` | Gets the account name |
| `public void setName(String name)` | Sets the account name |
| `public Double getBalance()` | Gets the account balance |
| `public void setBalance(Double balance)` | Sets the account balance |
| `public UserResponse getUser()` | Gets the associated user |
| `public void setUser(UserResponse user)` | Sets the associated user |
| `public String toString()` | Returns string representation |

---

#### `com.epam.campus.pojo.OperationResponse`

**Description:** POJO class representing an Operation entity for API request/response handling.

**Annotations:** `@JsonIgnoreProperties(ignoreUnknown = true)`

**Fields:**

| Field | Type | JSON Property | Annotation |
|-------|------|---------------|------------|
| `id` | `Long` | `id` | `@JsonProperty("id")` |
| `date` | `String` | `date` | `@JsonProperty("date")` |
| `description` | `String` | `description` | `@JsonProperty("description")` |
| `amount` | `Double` | `amount` | `@JsonProperty("amount")` |
| `bankAccount` | `BankAccountResponse` | `bankAccount` | `@JsonProperty("bankAccount")` |

**Methods:**

| Method Signature | Description |
|-----------------|-------------|
| `public OperationResponse()` | Default constructor |
| `public OperationResponse(String date, String description, Double amount)` | Constructor with required fields |
| `public OperationResponse(Long id, String date, String description, Double amount, BankAccountResponse bankAccount)` | Full constructor |
| `public Long getId()` | Gets the operation ID |
| `public void setId(Long id)` | Sets the operation ID |
| `public String getDate()` | Gets the operation date |
| `public void setDate(String date)` | Sets the operation date |
| `public String getDescription()` | Gets the operation description |
| `public void setDescription(String description)` | Sets the operation description |
| `public Double getAmount()` | Gets the operation amount |
| `public void setAmount(Double amount)` | Sets the operation amount |
| `public BankAccountResponse getBankAccount()` | Gets the associated bank account |
| `public void setBankAccount(BankAccountResponse bankAccount)` | Sets the associated bank account |
| `public String toString()` | Returns string representation |

---

#### `com.epam.campus.pojo.RegisterRequest`

**Description:** POJO class representing a Registration request payload.

**Annotations:** `@JsonIgnoreProperties(ignoreUnknown = true)`

**Fields:**

| Field | Type | JSON Property | Annotation |
|-------|------|---------------|------------|
| `login` | `String` | `login` | `@JsonProperty("login")` |
| `email` | `String` | `email` | `@JsonProperty("email")` |
| `password` | `String` | `password` | `@JsonProperty("password")` |
| `langKey` | `String` | `langKey` | `@JsonProperty("langKey")` |

**Methods:**

| Method Signature | Description |
|-----------------|-------------|
| `public RegisterRequest()` | Default constructor |
| `public RegisterRequest(String login, String email, String password, String langKey)` | Constructor with all fields |
| `public String getLogin()` | Gets the login username |
| `public void setLogin(String login)` | Sets the login username |
| `public String getEmail()` | Gets the email address |
| `public void setEmail(String email)` | Sets the email address |
| `public String getPassword()` | Gets the password |
| `public void setPassword(String password)` | Sets the password |
| `public String getLangKey()` | Gets the language key |
| `public void setLangKey(String langKey)` | Sets the language key |
| `public String toString()` | Returns string representation |

---

#### `com.epam.campus.pojo.UserResponse`

**Description:** POJO class representing a User entity for API request/response handling.

**Annotations:** `@JsonIgnoreProperties(ignoreUnknown = true)`

**Fields:**

| Field | Type | JSON Property | Annotation |
|-------|------|---------------|------------|
| `id` | `Long` | `id` | `@JsonProperty("id")` |
| `login` | `String` | `login` | `@JsonProperty("login")` |
| `firstName` | `String` | `firstName` | `@JsonProperty("firstName")` |
| `lastName` | `String` | `lastName` | `@JsonProperty("lastName")` |
| `email` | `String` | `email` | `@JsonProperty("email")` |
| `imageUrl` | `String` | `imageUrl` | `@JsonProperty("imageUrl")` |
| `activated` | `Boolean` | `activated` | `@JsonProperty("activated")` |
| `langKey` | `String` | `langKey` | `@JsonProperty("langKey")` |
| `authorities` | `List<String>` | `authorities` | `@JsonProperty("authorities")` |

**Methods:**

| Method Signature | Description |
|-----------------|-------------|
| `public UserResponse()` | Default constructor |
| `public UserResponse(String login, String firstName, String lastName, String email, String langKey)` | Constructor with required fields |
| `public UserResponse(Long id, String login, String firstName, String lastName, String email, Boolean activated, String langKey, List<String> authorities)` | Full constructor |
| `public Long getId()` | Gets the user ID |
| `public void setId(Long id)` | Sets the user ID |
| `public String getLogin()` | Gets the login username |
| `public void setLogin(String login)` | Sets the login username |
| `public String getFirstName()` | Gets the first name |
| `public void setFirstName(String firstName)` | Sets the first name |
| `public String getLastName()` | Gets the last name |
| `public void setLastName(String lastName)` | Sets the last name |
| `public String getEmail()` | Gets the email address |
| `public void setEmail(String email)` | Sets the email address |
| `public String getImageUrl()` | Gets the image URL |
| `public void setImageUrl(String imageUrl)` | Sets the image URL |
| `public Boolean getActivated()` | Gets the activated status |
| `public void setActivated(Boolean activated)` | Sets the activated status |
| `public String getLangKey()` | Gets the language key |
| `public void setLangKey(String langKey)` | Sets the language key |
| `public List<String> getAuthorities()` | Gets the user authorities/roles |
| `public void setAuthorities(List<String> authorities)` | Sets the user authorities/roles |
| `public String toString()` | Returns string representation |

---

### Test Classes

#### `com.epam.campus.AuthenticationTests`

**Description:** Test class for Authentication API endpoints. Tests the `POST /api/authenticate` endpoint.

**Extends:** `BaseTest`

**Test Methods:**

| Method Signature | Annotations | Test Type | Description |
|-----------------|-------------|-----------|-------------|
| `public void testAuthenticateWithValidCredentials()` | `@Test(description = "Verify successful authentication with valid credentials")` | Positive | Verifies successful authentication with valid admin credentials. Expected: HTTP 200 OK with valid JWT token. |
| `public void testAuthenticateWithRememberMe()` | `@Test(description = "Verify authentication with rememberMe flag enabled")` | Positive | Verifies authentication with rememberMe flag set to true. Expected: HTTP 200 OK with valid JWT token. |
| `public void testAuthenticateWithInvalidPassword()` | `@Test(description = "Verify authentication fails with invalid password")` | Negative | Verifies authentication fails with invalid password. Expected: HTTP 401 Unauthorized. |
| `public void testAuthenticateWithInvalidUsername()` | `@Test(description = "Verify authentication fails with invalid username")` | Negative | Verifies authentication fails with invalid username. Expected: HTTP 401 Unauthorized. |
| `public void testAuthenticateWithEmptyCredentials()` | `@Test(description = "Verify authentication fails with empty credentials")` | Negative | Verifies authentication fails with empty credentials. Expected: HTTP 400 or 401. |
| `public void testAuthenticateWithMissingFields()` | `@Test(description = "Verify authentication fails with missing required fields")` | Negative | Verifies authentication fails with null/missing values. Expected: HTTP 4xx error. |

---

#### `com.epam.campus.UserManagementTests`

**Description:** Test class for User Management API endpoints. Tests GET, POST, PUT, DELETE on `/api/admin/users`.

**Extends:** `BaseTest`

**Test Methods:**

| Method Signature | Annotations | Test Type | Description |
|-----------------|-------------|-----------|-------------|
| `public void testGetAllUsers()` | `@Test(description = "Verify successful retrieval of all users", priority = 1)` | Positive | Verifies successful retrieval of all users. Expected: HTTP 200 OK with list of users. |
| `public void testGetAllUsersWithoutAuth()` | `@Test(description = "Verify unauthorized access to users without authentication", priority = 1)` | Negative | Verifies unauthorized access without authentication. Expected: HTTP 401 Unauthorized. |
| `public void testCreateUser()` | `@Test(description = "Verify successful creation of a new user", priority = 2)` | Positive | Verifies successful creation of a new user. Expected: HTTP 201 Created with user details. |
| `public void testCreateUserWithDuplicateLogin()` | `@Test(description = "Verify user creation fails with duplicate login", priority = 2)` | Negative | Verifies user creation fails with duplicate login. Expected: HTTP 400 Bad Request. |
| `public void testCreateUserWithoutAuth()` | `@Test(description = "Verify user creation fails without authentication", priority = 2)` | Negative | Verifies user creation fails without authentication. Expected: HTTP 401 Unauthorized. |
| `public void testCreateUserWithInvalidEmail()` | `@Test(description = "Verify user creation fails with invalid email format", priority = 2)` | Negative | Verifies user creation fails with invalid email format. Expected: HTTP 400 Bad Request. |
| `public void testUpdateUserByLogin()` | `@Test(description = "Verify successful update of an existing user", priority = 3)` | Positive | Verifies successful update of an existing user using PUT. Expected: HTTP 200 OK with updated user details. |
| `public void testUpdateNonExistentUser()` | `@Test(description = "Verify update fails for non-existent user", priority = 3)` | Negative | Verifies update fails for non-existent user. Expected: HTTP 400/404/500. |
| `public void testDeleteUser()` | `@Test(description = "Verify successful deletion of a user", priority = 4)` | Positive | Verifies successful deletion of a user. Expected: HTTP 204 No Content. |
| `public void testDeleteNonExistentUser()` | `@Test(description = "Verify deletion of non-existent user", priority = 4)` | Negative | Verifies deletion of non-existent user. Expected: HTTP 404 or 204 (idempotent). |
| `public void testDeleteUserWithoutAuth()` | `@Test(description = "Verify deletion fails without authentication", priority = 4)` | Negative | Verifies deletion fails without authentication. Expected: HTTP 401 Unauthorized. |
| `public void testCannotDeleteAdminUser()` | `@Test(description = "Verify cannot delete the admin user", priority = 5)` | Negative | Verifies admin user cannot be deleted. Expected: HTTP 400 Bad Request. |
| `public void testGetUsersWithPagination()` | `@Test(description = "Verify users retrieval with pagination", priority = 5)` | Positive | Verifies users can be retrieved with pagination parameters. Expected: HTTP 200 OK. |
| `public void testCreateUserWithMissingFields()` | `@Test(description = "Verify user creation fails with missing required fields", priority = 2)` | Negative | Verifies user creation fails with missing required fields. Expected: HTTP 400 Bad Request. |

---

#### `com.epam.campus.BankAccountTests`

**Description:** Test class for Bank Account API endpoints. Tests GET, POST, PUT, PATCH, DELETE on `/api/bank-accounts`.

**Extends:** `BaseTest`

**Test Methods:**

| Method Signature | Annotations | Test Type | Description |
|-----------------|-------------|-----------|-------------|
| `public void testGetAllBankAccounts()` | `@Test(description = "Verify successful retrieval of all bank accounts", priority = 1)` | Positive | Verifies successful retrieval of all bank accounts. Expected: HTTP 200 OK with list of bank accounts. |
| `public void testGetAllBankAccountsWithoutAuth()` | `@Test(description = "Verify unauthorized access to bank accounts without authentication", priority = 1)` | Negative | Verifies unauthorized access without authentication. Expected: HTTP 401 Unauthorized. |
| `public void testCreateBankAccount()` | `@Test(description = "Verify successful creation of a new bank account", priority = 2)` | Positive | Verifies successful creation of a new bank account. Expected: HTTP 201 Created with bank account details. |
| `public void testCreateBankAccountWithoutAuth()` | `@Test(description = "Verify bank account creation fails without authentication", priority = 2)` | Negative | Verifies bank account creation fails without authentication. Expected: HTTP 401 Unauthorized. |
| `public void testCreateBankAccountWithMissingFields()` | `@Test(description = "Verify bank account creation fails with missing required fields", priority = 2)` | Negative | Verifies bank account creation fails with invalid/missing data. Expected: HTTP 400 Bad Request. |
| `public void testUpdateBankAccount()` | `@Test(description = "Verify successful update of an existing bank account", priority = 3)` | Positive | Verifies successful update of an existing bank account. Expected: HTTP 200 OK with updated details. |
| `public void testUpdateNonExistentBankAccount()` | `@Test(description = "Verify update fails for non-existent bank account", priority = 3)` | Negative | Verifies update fails for non-existent bank account. Expected: HTTP 400 or 404. |
| `public void testPartialUpdateBankAccount()` | `@Test(description = "Verify successful partial update of a bank account", priority = 4)` | Positive | Verifies successful partial update using PATCH. Expected: HTTP 200 OK with updated details. |
| `public void testPartialUpdateNonExistentBankAccount()` | `@Test(description = "Verify partial update fails for non-existent bank account", priority = 4)` | Negative | Verifies partial update fails for non-existent bank account. Expected: HTTP 400 or 404. |
| `public void testDeleteBankAccount()` | `@Test(description = "Verify successful deletion of a bank account", priority = 5)` | Positive | Verifies successful deletion of a bank account. Expected: HTTP 204 No Content. |
| `public void testDeleteNonExistentBankAccount()` | `@Test(description = "Verify deletion of non-existent bank account", priority = 5)` | Negative | Verifies deletion of non-existent bank account. Expected: HTTP 404 or 204 (idempotent). |
| `public void testDeleteBankAccountWithoutAuth()` | `@Test(description = "Verify deletion fails without authentication", priority = 5)` | Negative | Verifies deletion fails without authentication. Expected: HTTP 401 Unauthorized. |
| `public void testInvalidHttpMethod()` | `@Test(description = "Verify invalid HTTP method returns appropriate error", priority = 6)` | Negative | Verifies invalid HTTP method returns appropriate error. Expected: HTTP 405 or 400. |

---

#### `com.epam.campus.OperationsManagementTests`

**Description:** Test class for Operations Management API endpoints. Tests GET and POST on `/api/operations`.

**Extends:** `BaseTest`

**Fields:**

| Field | Type | Description |
|-------|------|-------------|
| `testBankAccountId` | `Long` | ID of test bank account created for operations testing |

**Methods:**

| Method Signature | Annotations | Test Type | Description |
|-----------------|-------------|-----------|-------------|
| `public void setup()` | `@Override @BeforeClass` | Setup | Overrides base setup to create a bank account for operations testing. |
| `public void testGetAllOperations()` | `@Test(description = "Verify successful retrieval of all operations", priority = 1)` | Positive | Verifies successful retrieval of all operations. Expected: HTTP 200 OK with list of operations. |
| `public void testGetAllOperationsWithoutAuth()` | `@Test(description = "Verify unauthorized access to operations without authentication", priority = 1)` | Negative | Verifies unauthorized access without authentication. Expected: HTTP 401 Unauthorized. |
| `public void testCreateOperation()` | `@Test(description = "Verify successful creation of a new operation", priority = 2)` | Positive | Verifies successful creation of a new operation. Expected: HTTP 201 Created or 200 OK with operation details. |
| `public void testCreateOperationWithNegativeAmount()` | `@Test(description = "Verify successful creation of operation with negative amount", priority = 2)` | Positive | Verifies successful creation of operation with negative amount (withdrawal). Expected: HTTP 201 or 200. |
| `public void testCreateOperationWithoutAuth()` | `@Test(description = "Verify operation creation fails without authentication", priority = 2)` | Negative | Verifies operation creation fails without authentication. Expected: HTTP 401 Unauthorized. |
| `public void testCreateOperationWithMissingFields()` | `@Test(description = "Verify operation creation fails with missing required fields", priority = 3)` | Negative | Verifies operation creation fails with missing required fields. Expected: HTTP 400 or 500. |
| `public void testCreateOperationWithInvalidBankAccount()` | `@Test(description = "Verify operation creation fails with invalid bank account reference", priority = 3)` | Negative | Verifies operation creation fails with invalid bank account reference. Expected: HTTP 4xx or 5xx. |
| `public void testCreateOperationWithInvalidDateFormat()` | `@Test(description = "Verify operation creation fails with invalid date format", priority = 3)` | Negative | Verifies operation creation fails with invalid date format. Expected: HTTP 400 Bad Request. |
| `public void testInvalidHttpMethodOnOperations()` | `@Test(description = "Verify DELETE operation is not allowed on operations endpoint", priority = 4)` | Negative | Verifies DELETE method is not allowed on operations endpoint. Expected: HTTP 405 or 404. |
| `public void testGetOperationsWithPagination()` | `@Test(description = "Verify operations retrieval with pagination", priority = 4)` | Positive | Verifies operations can be retrieved with pagination parameters. Expected: HTTP 200 OK. |

---

#### `com.epam.campus.AccountsManagementTests`

**Description:** Test class for Account Management API endpoints. Tests user registration and password reset.

**Extends:** `BaseTest`

**Test Methods:**

| Method Signature | Annotations | Test Type | Description |
|-----------------|-------------|-----------|-------------|
| `public void testRegisterUserWithValidData()` | `@Test(description = "Verify successful user registration with valid data")` | Positive | Verifies successful user registration with valid data. Expected: HTTP 201 Created or 200 OK. |
| `public void testRegisterUserWithDuplicateLogin()` | `@Test(description = "Verify registration fails with duplicate login")` | Negative | Verifies registration fails with duplicate login. Expected: HTTP 400 Bad Request. |
| `public void testRegisterUserWithInvalidEmail()` | `@Test(description = "Verify registration fails with invalid email format")` | Negative | Verifies registration fails with invalid email format. Expected: HTTP 400 Bad Request. |
| `public void testRegisterUserWithShortPassword()` | `@Test(description = "Verify registration fails with password that is too short")` | Negative | Verifies registration fails with short password. Expected: HTTP 400 Bad Request. |
| `public void testRegisterUserWithMissingFields()` | `@Test(description = "Verify registration fails with missing required fields")` | Negative | Verifies registration fails with missing required fields. Expected: HTTP 400 Bad Request. |
| `public void testResetPasswordInitWithValidEmail()` | `@Test(description = "Verify password reset initialization with valid email")` | Positive | Verifies password reset initialization with valid email. Expected: HTTP 200 OK or 400. |
| `public void testResetPasswordInitWithNonExistentEmail()` | `@Test(description = "Verify password reset initialization with non-existent email")` | Negative | Verifies password reset with non-existent email (may return 200 for security). Expected: HTTP 200 or 400. |
| `public void testResetPasswordInitWithInvalidEmailFormat()` | `@Test(description = "Verify password reset initialization with invalid email format")` | Negative | Verifies password reset with invalid email format. Expected: HTTP 4xx error. |

---

## Summary Statistics

| Category | Count |
|----------|-------|
| **Total Test Classes** | 5 |
| **Total Test Methods** | 44 |
| **Total POJO Classes** | 6 |
| **Total POJO Methods** | 58 |
| **Base Class Methods** | 4 |
| **Positive Test Cases** | 18 |
| **Negative Test Cases** | 26 |

---

## API Endpoints Tested

| Endpoint | HTTP Methods | Test Class |
|----------|--------------|------------|
| `/api/authenticate` | POST | AuthenticationTests |
| `/api/admin/users` | GET, POST, PUT, DELETE | UserManagementTests |
| `/api/bank-accounts` | GET, POST, PUT, PATCH, DELETE | BankAccountTests |
| `/api/operations` | GET, POST | OperationsManagementTests |
| `/api/register` | POST | AccountsManagementTests |
| `/api/account/reset-password/init` | POST | AccountsManagementTests |

---

*Document generated on: February 20, 2026*
