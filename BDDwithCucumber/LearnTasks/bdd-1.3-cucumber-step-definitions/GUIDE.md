# Thorough Guide: Cucumber Step Definitions in BDD

## Table of Contents
1. [Overview](#1-overview)
2. [Project Structure](#2-project-structure)
3. [Dependencies & Configuration](#3-dependencies--configuration)
4. [Feature Files (Gherkin)](#4-feature-files-gherkin)
5. [Step Definition Classes](#5-step-definition-classes)
6. [Parameterization Techniques](#6-parameterization-techniques)
7. [Reusable Step Definitions](#7-reusable-step-definitions)
8. [Dependency Injection with PicoContainer](#8-dependency-injection-with-picocontainer)
9. [Hooks (Lifecycle Management)](#9-hooks-lifecycle-management)
10. [Test Runner Configuration](#10-test-runner-configuration)
11. [Debugging Failed Steps](#11-debugging-failed-steps)
12. [Running the Tests](#12-running-the-tests)
13. [Summary of Key Concepts](#13-summary-of-key-concepts)

---

## 1. Overview

**Behavior-Driven Development (BDD)** is a software development methodology where tests are written in a human-readable language (Gherkin) before implementation. **Cucumber** is the tool that bridges the gap between Gherkin specifications and executable Java code through **Step Definitions**.

This project implements all five tasks from the hands-on assignment:

| Task | What Was Done |
|------|---------------|
| Task 1: Create Step Definition File | Created `LoginSteps.java`, `RegistrationSteps.java`, `ShoppingCartSteps.java` in the `steps` package |
| Task 2: Map Gherkin Steps to Java | Every Gherkin step is mapped to a Java method using `@Given`, `@When`, `@Then` |
| Task 3: Parameterize Steps | Used Cucumber Expressions (`{string}`, `{double}`, `{int}`) and `DataTable` for dynamic values |
| Task 4: Reusable Step Definitions | Login steps are shared across Login and Shopping Cart features; error message step is reused across all features |
| Task 5: Debug Gherkin Steps | Every assertion has descriptive error messages; Hooks log scenario lifecycle events |

---

## 2. Project Structure

```
bdd-1.3-cucumber-step-definitions/
├── pom.xml                                          # Maven config with Cucumber + JUnit 5 deps
├── README.md                                        # Task description
├── GUIDE.md                                         # This guide
├── src/
│   ├── main/java/com/epam/campus/bdd/
│   │   ├── AuthenticationService.java               # Simulated auth service
│   │   └── ShoppingCart.java                        # Simulated shopping cart
│   └── test/
│       ├── java/com/epam/campus/bdd/
│       │   ├── CucumberTestRunner.java              # JUnit 5 Suite runner
│       │   └── steps/
│       │       ├── SharedState.java                 # Shared state (DI via PicoContainer)
│       │       ├── LoginSteps.java                  # Login feature step definitions
│       │       ├── RegistrationSteps.java           # Registration feature step definitions
│       │       ├── ShoppingCartSteps.java           # Shopping cart feature step definitions
│       │       └── Hooks.java                       # Before/After scenario hooks
│       └── resources/
│           ├── cucumber.properties                  # Cucumber configuration
│           └── com/epam/campus/bdd/
│               ├── login.feature                    # Login scenarios (4 scenarios)
│               ├── registration.feature             # Registration scenarios (4 scenarios)
│               └── shopping_cart.feature            # Shopping cart scenarios (3 scenarios)
```

---

## 3. Dependencies & Configuration

### Maven Dependencies (`pom.xml`)

| Dependency | Purpose |
|---|---|
| `cucumber-java` 7.23.0 | Core Cucumber library — provides `@Given`, `@When`, `@Then` annotations |
| `cucumber-junit-platform-engine` 7.23.0 | Integrates Cucumber with JUnit 5 Platform |
| `cucumber-picocontainer` 7.23.0 | Lightweight dependency injection for sharing state between step classes |
| `junit-platform-suite` 1.10.3 | Enables `@Suite` annotation for test runner |
| `junit-jupiter` 5.10.3 | JUnit 5 assertions and testing framework |

### Why PicoContainer?
Cucumber creates **separate instances** of each step definition class per scenario. Without DI, step classes cannot share state (e.g., login status, cart contents). PicoContainer auto-injects a shared `SharedState` object into each class's constructor.

### Surefire Configuration
Maven Surefire by default only picks up classes named `*Test`, `Test*`, `*Tests`, or `*TestCase`. Since our runner is named `CucumberTestRunner`, we explicitly include it:

```xml
<plugin>
  <artifactId>maven-surefire-plugin</artifactId>
  <version>3.3.0</version>
  <configuration>
    <includes>
      <include>**/CucumberTestRunner.java</include>
    </includes>
  </configuration>
</plugin>
```

---

## 4. Feature Files (Gherkin)

Feature files define test scenarios in plain English using the **Given-When-Then** structure.

### 4.1 Login Feature (`login.feature`)

```gherkin
@login
Feature: User Login
  As a registered user
  I want to log in to the application
  So that I can access my account

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters username "admin" and password "admin123"
    And the user clicks the login button
    Then the user should be redirected to the dashboard
    And the welcome message should display "Welcome, admin!"
```

**Key points:**
- `@login` is a **tag** — used to selectively run scenarios
- The `As a / I want / So that` block is a **user story** (documentation only, not executed)
- `"admin"` and `"admin123"` are **parameterized values** extracted at runtime

**Scenarios covered:**
1. Successful login with valid credentials
2. Failed login with invalid password
3. Failed login with empty credentials
4. Successful logout after login

### 4.2 Registration Feature (`registration.feature`)

```gherkin
  Scenario: Successful registration with valid details
    Given the user navigates to the registration page
    When the user enters registration details:
      | field     | value              |
      | username  | newuser            |
      | email     | newuser@test.com   |
      | password  | SecurePass123!     |
    And the user submits the registration form
    Then the registration should be successful
```

**Key points:**
- Uses a **DataTable** (pipe-delimited table) to pass structured data
- The table is converted to a `Map<String, String>` in the step definition

**Scenarios covered:**
1. Successful registration
2. Duplicate username rejection
3. Invalid email rejection
4. Weak password rejection

### 4.3 Shopping Cart Feature (`shopping_cart.feature`)

```gherkin
  Scenario: Add multiple items to the cart
    Given the user is on the login page
    When the user enters username "buyer" and password "buyer123"
    And the user clicks the login button
    Then the user should be redirected to the dashboard
    When the user adds item "Laptop" with price 999.99 to the cart
    And the user adds item "Mouse" with price 29.99 to the cart
    Then the cart should contain 2 items
    And the cart total should be 1029.98
```

**Key points:**
- **Reuses login steps** from `LoginSteps.java` — Cucumber auto-discovers all step definitions
- Uses `{double}` and `{int}` Cucumber Expressions for numeric parameters
- Demonstrates cross-feature step reuse

---

## 5. Step Definition Classes

### How Cucumber Maps Steps to Methods

Cucumber uses **annotations** (`@Given`, `@When`, `@Then`) with **Cucumber Expressions** to match Gherkin text to Java methods:

```
Gherkin:   When the user enters username "admin" and password "admin123"
                                         ↓                     ↓
Java:      @When("the user enters username {string} and password {string}")
           public void theUserEntersUsernameAndPassword(String username, String password)
```

The `{string}` placeholder captures any text inside double quotes and passes it as a method argument.

### 5.1 LoginSteps.java

```java
@Given("the user is on the login page")
public void theUserIsOnTheLoginPage() {
    state.setCurrentPage("login");
    assertEquals("login", state.getCurrentPage(),
            "Expected to be on login page but was on: " + state.getCurrentPage());
}

@When("the user enters username {string} and password {string}")
public void theUserEntersUsernameAndPassword(String username, String password) {
    String result = state.getAuthService().login(username, password);
    if ("SUCCESS".equals(result)) {
        state.setCurrentPage("dashboard");
        state.setLastMessage("Welcome, " + username + "!");
    } else {
        state.setLastMessage(result);
    }
}

@Then("an error message should be displayed with text {string}")
public void anErrorMessageShouldBeDisplayedWithText(String expectedError) {
    assertEquals(expectedError, state.getLastMessage(),
            "Error message mismatch. Expected: '" + expectedError
                    + "' but got: '" + state.getLastMessage() + "'");
}
```

**Pattern:**
- `@Given` → **Sets up preconditions** (navigating to a page)
- `@When` → **Performs actions** (entering credentials, clicking buttons)
- `@Then` → **Verifies outcomes** (checking redirects, messages)

### 5.2 RegistrationSteps.java — DataTable Parameterization

```java
@When("the user enters registration details:")
public void theUserEntersRegistrationDetails(DataTable dataTable) {
    Map<String, String> details = dataTable.asMap(String.class, String.class);
    registrationUsername = details.get("username");
    registrationEmail = details.get("email");
    registrationPassword = details.get("password");
}
```

The Gherkin table:
```
| field    | value            |
| username | newuser          |
| email    | newuser@test.com |
```
is automatically converted to `{"username": "newuser", "email": "newuser@test.com", ...}`.

### 5.3 ShoppingCartSteps.java — Multiple Parameter Types

```java
@When("the user adds item {string} with price {double} to the cart")
public void theUserAddsItemWithPriceToTheCart(String itemName, double price) {
    state.getShoppingCart().addItem(itemName, price);
}

@Then("the cart total should be {double}")
public void theCartTotalShouldBe(double expectedTotal) {
    assertEquals(expectedTotal, state.getShoppingCart().getTotal(), 0.01,
            "Cart total mismatch.");
}
```

**Cucumber Expression types used:**
| Expression | Java Type | Example Match |
|---|---|---|
| `{string}` | `String` | `"Laptop"` |
| `{int}` | `int` | `3` |
| `{double}` | `double` | `999.99` |

---

## 6. Parameterization Techniques

### 6.1 Cucumber Expressions (Inline Parameters)

The simplest form — values are embedded directly in the Gherkin step text:

```gherkin
When the user enters username "admin" and password "admin123"
```
```java
@When("the user enters username {string} and password {string}")
public void method(String username, String password) { ... }
```

### 6.2 DataTable (Structured Data)

For passing multiple fields as a structured table:

```gherkin
When the user enters registration details:
  | field    | value         |
  | username | newuser       |
  | email    | new@test.com  |
```
```java
@When("the user enters registration details:")
public void method(DataTable dataTable) {
    Map<String, String> data = dataTable.asMap(String.class, String.class);
}
```

### 6.3 Optional Text with Parentheses

Handle singular/plural in step text:

```java
@Then("the cart should contain {int} item(s)")
public void theCartShouldContainItems(int count) { ... }
```
This matches both `"the cart should contain 1 item"` and `"the cart should contain 3 items"`.

---

## 7. Reusable Step Definitions

### Principle: One Step, Many Scenarios

A single step definition method can serve multiple scenarios across multiple feature files. Cucumber discovers **all** step definitions in the glue package automatically.

### Example: Error Message Step

Defined once in `LoginSteps.java`:
```java
@Then("an error message should be displayed with text {string}")
public void anErrorMessageShouldBeDisplayedWithText(String expectedError) {
    assertEquals(expectedError, state.getLastMessage(), "Error message mismatch.");
}
```

**Used by:**
- `login.feature` → "Invalid username or password", "Username and password are required"
- `registration.feature` → "Username already exists", "Please enter a valid email address", "Password must be at least 8 characters"

### Example: Login Steps Reused by Shopping Cart

The Shopping Cart feature reuses these steps from `LoginSteps.java`:
```gherkin
Given the user is on the login page
When the user enters username "buyer" and password "buyer123"
And the user clicks the login button
Then the user should be redirected to the dashboard
```
No duplicate code is needed in `ShoppingCartSteps.java`.

### Organization Strategy

| Class | Responsibility |
|---|---|
| `LoginSteps` | Authentication actions + common navigation/error verification |
| `RegistrationSteps` | Registration-specific steps with DataTable |
| `ShoppingCartSteps` | Cart management (add, remove, verify totals) |
| `SharedState` | Centralized state shared across all step classes |
| `Hooks` | Lifecycle management (setup/teardown per scenario) |

---

## 8. Dependency Injection with PicoContainer

### The Problem
Cucumber creates a **new instance** of each step definition class for every scenario. If `LoginSteps` and `ShoppingCartSteps` both need to check `isLoggedIn()`, they need to share the same `AuthenticationService` instance.

### The Solution
PicoContainer automatically manages dependency injection:

```java
// SharedState.java — holds all shared objects
public class SharedState {
    private final AuthenticationService authService = new AuthenticationService();
    private final ShoppingCart shoppingCart = new ShoppingCart();
    private String currentPage = "home";
    private String lastMessage = "";
    // getters and setters...
}

// LoginSteps.java — receives SharedState via constructor
public class LoginSteps {
    private final SharedState state;
    public LoginSteps(SharedState state) {
        this.state = state;
    }
}

// ShoppingCartSteps.java — receives the SAME SharedState instance
public class ShoppingCartSteps {
    private final SharedState state;
    public ShoppingCartSteps(SharedState state) {
        this.state = state;
    }
}
```

PicoContainer creates **one `SharedState` per scenario** and injects it into all step classes. After each scenario, everything is garbage-collected (fresh state for the next scenario).

---

## 9. Hooks (Lifecycle Management)

Hooks run automatically before/after each scenario:

```java
public class Hooks {
    private final SharedState state;

    public Hooks(SharedState state) {
        this.state = state;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        System.out.println("Tags: " + scenario.getSourceTagNames());
    }

    @After
    public void afterScenario(Scenario scenario) {
        // Cleanup
        if (state.getAuthService().isLoggedIn()) {
            state.getAuthService().logout();
        }
        state.getShoppingCart().clear();

        System.out.println("Status: " + scenario.getStatus());
        if (scenario.isFailed()) {
            System.err.println("FAILED: " + scenario.getName());
        }
    }
}
```

**Use cases for hooks:**
- Initialize test data before a scenario
- Clean up state after a scenario
- Take screenshots on failure (in UI testing)
- Log scenario execution details for debugging

---

## 10. Test Runner Configuration

```java
@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.epam.campus.bdd")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.epam.campus.bdd.steps")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/com/epam/campus/bdd")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/cucumber-report.html")
public class CucumberTestRunner {
}
```

| Annotation/Parameter | Purpose |
|---|---|
| `@Suite` | Marks this as a JUnit 5 Platform Suite |
| `@IncludeEngines("cucumber")` | Uses Cucumber's test engine |
| `GLUE_PROPERTY_NAME` | Package where step definitions are located |
| `FEATURES_PROPERTY_NAME` | Directory containing `.feature` files |
| `PLUGIN_PROPERTY_NAME` | Output plugins — `pretty` for console, `html` for report file |

---

## 11. Debugging Failed Steps

### Technique 1: Descriptive Assertion Messages

Every assertion includes a custom message explaining what went wrong:

```java
assertEquals(expectedError, state.getLastMessage(),
        "Error message mismatch. Expected: '" + expectedError
                + "' but got: '" + state.getLastMessage() + "'");
```

If this fails, the output will be:
```
Error message mismatch. Expected: 'Invalid password' but got: 'Invalid username or password'
```

### Technique 2: Precondition Validation

Steps validate that prerequisites are met before acting:

```java
@When("the user adds item {string} with price {double} to the cart")
public void theUserAddsItemWithPriceToTheCart(String itemName, double price) {
    assertTrue(state.getAuthService().isLoggedIn(),
            "User must be logged in to add items to cart");
    state.getShoppingCart().addItem(itemName, price);
}
```

### Technique 3: Console Logging via Hooks

The `@Before` and `@After` hooks print scenario names, tags, and status:
```
========================================
Starting scenario: Successful login with valid credentials
Tags: [@login]
========================================
----------------------------------------
Finished scenario: Successful login with valid credentials
Status: PASSED
----------------------------------------
```

### Technique 4: IDE Breakpoints

1. Set a breakpoint inside any step definition method
2. Right-click `CucumberTestRunner` → **Debug As → JUnit Test**
3. The debugger stops at the breakpoint, letting you inspect:
   - Values of `username`, `password` passed from the feature file
   - The internal state of `SharedState`
   - The return value from service methods

### Technique 5: Floating-Point Delta Comparison

For monetary/decimal values, use a delta to avoid precision issues:
```java
assertEquals(expectedTotal, actualTotal, 0.01, "Cart total mismatch.");
```

---

## 12. Running the Tests

### Run All Tests
```bash
mvn clean test
```

### Run by Tag (e.g., only login scenarios)
```bash
mvn test -Dcucumber.filter.tags="@login"
```

### Run a Specific Feature File
```bash
mvn test -Dcucumber.features="src/test/resources/com/epam/campus/bdd/login.feature"
```

### View HTML Report
After running, open: `target/cucumber-reports/cucumber-report.html`

### Expected Output
```
Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

| Feature | Scenarios | Status |
|---|---|---|
| Login | 4 | All PASSED |
| Registration | 4 | All PASSED |
| Shopping Cart | 3 | All PASSED |
| **Total** | **11** | **All PASSED** |

---

## 13. Summary of Key Concepts

| Concept | Implementation | File(s) |
|---|---|---|
| **Step Definition Mapping** | `@Given`, `@When`, `@Then` annotations match Gherkin text to Java methods | `LoginSteps.java`, `RegistrationSteps.java`, `ShoppingCartSteps.java` |
| **Cucumber Expressions** | `{string}`, `{int}`, `{double}` extract dynamic values from steps | All step classes |
| **DataTable Parameterization** | Gherkin tables converted to `Map<String,String>` | `RegistrationSteps.java` |
| **Optional Text** | `item(s)` matches both "item" and "items" | `ShoppingCartSteps.java` |
| **Reusable Steps** | Error/login steps shared across features without duplication | `LoginSteps.java` used by `shopping_cart.feature` |
| **Dependency Injection** | PicoContainer shares `SharedState` across step classes per scenario | `SharedState.java` + all step classes |
| **Hooks** | `@Before`/`@After` for setup, teardown, and logging | `Hooks.java` |
| **Debugging** | Descriptive assertions, precondition checks, console logging | All step classes + `Hooks.java` |
| **Test Runner** | JUnit 5 Suite with Cucumber engine configuration | `CucumberTestRunner.java` |

### Cucumber Step Resolution Flow

```
Feature File (.feature)
    │
    ├── "Given the user is on the login page"
    │       ↓ Cucumber matches annotation text
    │   @Given("the user is on the login page")
    │   LoginSteps.theUserIsOnTheLoginPage()
    │
    ├── "When the user enters username "admin" and password "admin123""
    │       ↓ {string} extracts "admin" and "admin123"
    │   @When("the user enters username {string} and password {string}")
    │   LoginSteps.theUserEntersUsernameAndPassword("admin", "admin123")
    │
    └── "Then an error message should be displayed with text "Invalid...""
            ↓ Same step definition, different feature file
        @Then("an error message should be displayed with text {string}")
        LoginSteps.anErrorMessageShouldBeDisplayedWithText("Invalid...")
```

---

*This guide covers the complete implementation of Cucumber Step Definitions with parameterization, reusability, dependency injection, and debugging — all verified with 11 passing scenarios.*
