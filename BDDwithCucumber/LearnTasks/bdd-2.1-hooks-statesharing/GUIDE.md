# GUIDE: Cucumber Hooks & Shared State — Implementation Walkthrough

This guide explains every component implemented in this project, how they connect,
and the Cucumber concepts they demonstrate.

---

## Table of Contents

1. [Project Structure](#1-project-structure)
2. [Dependencies (pom.xml)](#2-dependencies-pomxml)
3. [Feature Files & Tags](#3-feature-files--tags)
4. [Hooks — Setup & Teardown](#4-hooks--setup--teardown)
5. [Shared State via Dependency Injection](#5-shared-state-via-dependency-injection)
6. [Step Definitions](#6-step-definitions)
7. [Test Runner Configuration](#7-test-runner-configuration)
8. [How Everything Connects — Execution Flow](#8-how-everything-connects--execution-flow)
9. [Running the Tests](#9-running-the-tests)

---

## 1. Project Structure

```
src/
├── main/java/com/epam/campus/bdd/
│   └── model/
│       └── User.java                  # Simple User POJO (production code)
│
└── test/
    ├── java/com/epam/campus/bdd/
    │   ├── context/
    │   │   ├── ScenarioContext.java    # Low-level key-value shared state store
    │   │   └── TestContext.java        # Typed wrapper around ScenarioContext
    │   ├── hooks/
    │   │   └── Hooks.java             # @Before / @After + tagged hooks
    │   ├── runner/
    │   │   └── TestRunner.java        # JUnit 5 Suite entry point
    │   └── steps/
    │       ├── LoginSteps.java        # Step defs for @UI login scenarios
    │       ├── ApiUserSteps.java      # Step defs for @API user scenarios
    │       └── UserManagementSteps.java # Step defs for @Regression scenarios
    │
    └── resources/
        ├── cucumber.properties         # Cucumber configuration
        └── features/
            ├── login.feature           # Tagged @UI
            ├── api_user.feature        # Tagged @API
            └── user_management.feature # Tagged @Regression (one scenario also has @UI)
```

---

## 2. Dependencies (pom.xml)

| Dependency                          | Purpose                                                |
|-------------------------------------|--------------------------------------------------------|
| `cucumber-java`                     | Core Cucumber step definition annotations (`@Given`, `@When`, `@Then`) |
| `cucumber-picocontainer`            | Lightweight DI — auto-creates & injects shared objects per scenario |
| `cucumber-junit-platform-engine`    | Bridges Cucumber with JUnit 5 Platform so `mvn test` works |
| `junit-platform-suite`              | `@Suite` annotation to configure the Cucumber runner   |
| `junit-jupiter`                     | JUnit 5 assertions (`assertEquals`, `assertTrue`, etc.) |

### Why PicoContainer?

PicoContainer is the simplest DI framework for Cucumber. It requires **zero configuration**:
- If a step definition or hooks class has a constructor parameter, PicoContainer
  automatically creates an instance and injects it.
- A **new instance** is created for **each scenario**, guaranteeing isolation.

---

## 3. Feature Files & Tags

### What are Tags?

Tags (e.g., `@UI`, `@API`, `@Regression`) are labels placed above `Feature:` or
`Scenario:` keywords. They serve two purposes:

1. **Filtering** — Run only specific tests: `mvn test -Dcucumber.filter.tags="@API"`
2. **Conditional Hooks** — Execute setup/teardown logic only for matching scenarios.

### Feature Breakdown

| File                       | Tag(s)               | What It Tests                              |
|----------------------------|----------------------|--------------------------------------------|
| `login.feature`            | `@UI`                | Browser login — success and failure paths  |
| `api_user.feature`         | `@API`               | REST API create & retrieve user operations |
| `user_management.feature`  | `@Regression`, `@UI` | End-to-end user registration & validation  |

**Key point:** The second scenario in `user_management.feature` carries both `@UI` and
`@Regression` tags, so **both** the UI hooks and the Regression hooks fire for it.

---

## 4. Hooks — Setup & Teardown

**File:** `src/test/java/com/epam/campus/bdd/hooks/Hooks.java`

Hooks are methods annotated with `@Before` or `@After` that run automatically around
each scenario. They are **not** called by step definitions — Cucumber invokes them.

### Hook Types Implemented

| Annotation               | Order | Runs For         | Purpose                              |
|--------------------------|-------|------------------|--------------------------------------|
| `@Before`                | 0     | ALL scenarios    | Log scenario start, store scenario name |
| `@Before("@UI")`         | 1     | `@UI` scenarios  | Initialize browser (simulated)       |
| `@Before("@API")`        | 1     | `@API` scenarios | Initialize API client (simulated)    |
| `@Before("@Regression")` | 1     | `@Regression`    | Load test data (simulated)           |
| `@After("@Regression")`  | 1     | `@Regression`    | Clean up test data                   |
| `@After("@API")`         | 1     | `@API` scenarios | Tear down API client                 |
| `@After("@UI")`          | 1     | `@UI` scenarios  | Close browser                        |
| `@After`                 | 0     | ALL scenarios    | Log final status, clear context      |

### Execution Order

- **`@Before`**: Lower `order` value runs **first** (0 before 1).
- **`@After`**: Lower `order` value runs **last** (teardown is reversed — 1 runs before 0).

So for a `@UI` scenario the sequence is:

```
@Before (order=0) → @Before("@UI", order=1) → Steps → @After("@UI", order=1) → @After (order=0)
```

### How Tagged Hooks Work

```java
@Before(value = "@UI", order = 1)
public void beforeUIScenario(Scenario scenario) { ... }
```

This method only executes when the scenario (or its parent feature) has the `@UI` tag.
Scenarios without `@UI` skip this hook entirely. This lets you:
- Initialize a browser **only** for UI tests
- Set up an API client **only** for API tests
- Avoid unnecessary overhead for unrelated tests

---

## 5. Shared State via Dependency Injection

### The Problem

Cucumber creates a **separate instance** of each step definition class per scenario.
If `LoginSteps` stores a `User` in a field, `AnotherSteps` can't see it. You need a
shared object that both classes can access.

### The Solution: PicoContainer + ScenarioContext

#### ScenarioContext (Low-Level Store)

**File:** `src/test/java/com/epam/campus/bdd/context/ScenarioContext.java`

```java
public class ScenarioContext {
    private final Map<String, Object> context = new HashMap<>();

    public void set(String key, Object value) { context.put(key, value); }
    public <T> T get(String key, Class<T> type) { return (T) context.get(key); }
    public boolean containsKey(String key) { return context.containsKey(key); }
    public void clear() { context.clear(); }
}
```

- A `HashMap` that stores arbitrary objects by string key.
- PicoContainer creates **one instance per scenario** and injects the **same instance**
  into every class that requests it.

#### TestContext (Typed Wrapper)

**File:** `src/test/java/com/epam/campus/bdd/context/TestContext.java`

```java
public class TestContext {
    private final ScenarioContext scenarioContext;

    public TestContext(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    public void setCurrentUser(User user) { scenarioContext.set("currentUser", user); }
    public User getCurrentUser() { return scenarioContext.get("currentUser", User.class); }
    // ... typed getters/setters for loginResult, responseStatus, etc.
}
```

- Wraps `ScenarioContext` with **type-safe** convenience methods.
- Step definitions create a `TestContext` locally, passing in the injected `ScenarioContext`.
- Since all classes share the same `ScenarioContext` instance, they share the same data.

### Injection Flow

```
PicoContainer creates ScenarioContext (1 per scenario)
        │
        ├── injects into Hooks(ScenarioContext)
        ├── injects into LoginSteps(ScenarioContext)
        ├── injects into ApiUserSteps(ScenarioContext)
        └── injects into UserManagementSteps(ScenarioContext)
```

Every class receives the **same** `ScenarioContext` object. When `Hooks` writes
`scenarioContext.set("browserInitialized", true)`, `LoginSteps` can read it.

---

## 6. Step Definitions

### LoginSteps.java

Handles the `@UI` login scenarios. Key behaviours:
- **Reads hook state**: Verifies `browserInitialized` was set by the `@UI` hook.
- **Writes shared state**: Stores the `User` and `loginResult` in `TestContext`.
- **Reads shared state**: Later steps retrieve and assert on the stored login result.

### ApiUserSteps.java

Handles the `@API` scenarios. Key behaviours:
- **Reads hook state**: Verifies `apiClientReady` was set by the `@API` hook.
- **Simulates API calls**: Sets response status codes and JSON bodies in `TestContext`.
- **Cross-step sharing**: The `@When` step reads the `User` stored by the `@Given` step.

### UserManagementSteps.java

Handles the `@Regression` scenarios. Key behaviours:
- **Reads hook state**: Verifies `testDataLoaded` was set by the `@Regression` hook.
- **In-memory store**: Uses a `Map<String, User>` to simulate a database.
- **Multi-step verification**: Registration, existence check, and field assertions each
  access the shared `User` object from `TestContext`.

---

## 7. Test Runner Configuration

**File:** `src/test/java/com/epam/campus/bdd/runner/TestRunner.java`

```java
@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.epam.campus.bdd")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.epam.campus.bdd")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html")
public class TestRunner { }
```

| Annotation               | Purpose                                             |
|--------------------------|-----------------------------------------------------|
| `@Suite`                 | Tells JUnit 5 this is a test suite entry point      |
| `@IncludeEngines`        | Use the Cucumber engine (not Jupiter)               |
| `@SelectPackages`        | Where to scan for glue code (steps, hooks)          |
| `FEATURES_PROPERTY_NAME` | Path to `.feature` files                            |
| `GLUE_PROPERTY_NAME`     | Package containing step defs and hooks              |
| `PLUGIN_PROPERTY_NAME`   | Output format — console (`pretty`) + HTML report    |

An equivalent `cucumber.properties` file also exists in `src/test/resources/` as a fallback.

---

## 8. How Everything Connects — Execution Flow

Here's what happens when Cucumber runs the "Successful login" scenario from `login.feature`:

```
1. Cucumber reads login.feature and sees @UI tag on the feature.

2. PicoContainer creates a fresh ScenarioContext instance.

3. HOOKS FIRE (in order):
   ├── @Before(order=0)        → Logs "Starting scenario: Successful login..."
   ├── @Before("@UI", order=1) → Sets browserInitialized=true, browser=Chrome

4. STEPS EXECUTE (sequentially):
   ├── @Given: Creates User(admin, secret123), stores in TestContext
   │          Also asserts browserInitialized==true (set by hook!)
   ├── @When:  Reads User from TestContext, simulates login, stores result
   ├── @Then:  Reads loginResult from TestContext, asserts true
   └── @And:   Reads User from TestContext, builds welcome message, asserts match

5. HOOKS FIRE (reverse order):
   ├── @After("@UI", order=1) → Logs browser closed
   └── @After(order=0)        → Logs scenario status, clears context

6. PicoContainer discards the ScenarioContext. Next scenario gets a fresh one.
```

**State sharing in action:** The `User` object created in `@Given` is available in
`@When`, `@Then`, and `@And` because they all share the same `ScenarioContext`.

---

## 9. Running the Tests

### Run All Tests

```bash
mvn clean test
```

### Run Only UI Tests

```bash
mvn test -Dcucumber.filter.tags="@UI"
```

### Run Only API Tests

```bash
mvn test -Dcucumber.filter.tags="@API"
```

### Run Regression Tests

```bash
mvn test -Dcucumber.filter.tags="@Regression"
```

### Run Tests with Multiple Tags

```bash
mvn test -Dcucumber.filter.tags="@UI and @Regression"
```

### View HTML Report

After running tests, open `target/cucumber-reports.html` in a browser to see the
formatted test report with scenario details and pass/fail status.

---

## Quick Reference: Key Concepts

| Concept            | Where                     | What It Does                                    |
|--------------------|---------------------------|-------------------------------------------------|
| `@Before`          | Hooks.java                | Runs before each scenario                       |
| `@After`           | Hooks.java                | Runs after each scenario                        |
| `@Before("@tag")`  | Hooks.java                | Runs only for scenarios with matching tag        |
| `order` parameter  | Hooks.java                | Controls hook execution sequence                |
| `ScenarioContext`   | context/ScenarioContext.java | Shared HashMap injected by PicoContainer     |
| `TestContext`       | context/TestContext.java  | Typed convenience layer over ScenarioContext     |
| PicoContainer      | pom.xml dependency        | Creates & injects one ScenarioContext per scenario |
| `@Suite` runner    | runner/TestRunner.java    | JUnit 5 entry point that triggers Cucumber      |
