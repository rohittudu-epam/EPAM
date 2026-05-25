# Implementation Guide — Cucumber Selenium Integration for DemoBlaze

## Overview

This project automates UI testing for the **DemoBlaze** e-commerce web application using **Cucumber BDD** with **Selenium WebDriver**. It demonstrates behavior-driven development by writing human-readable feature files that map to automated browser interactions.

---

## What Is Implemented

### 1. Feature File (`src/test/resources/features/demoblaze.feature`)

Five automated scenarios covering core DemoBlaze functionality:

| Scenario | Description |
|----------|-------------|
| Browse Laptops category | Navigates to "Laptops" and verifies product listings are displayed |
| Add product to cart | Adds "Sony vaio i5" to the cart and verifies it appears there |
| Remove product from cart | Adds a product, then removes it, and verifies the cart is empty |
| Complete checkout | Full purchase flow — add to cart, fill order form, confirm purchase |
| Browse Phones category | Navigates to "Phones" and verifies phone product listings |

### 2. Step Definitions (`src/test/java/com/epam/campus/bdd/steps/DemoBlazeSteps.java`)

Maps every Gherkin step to Selenium WebDriver actions:
- Navigating to the DemoBlaze home page
- Clicking product categories
- Selecting specific products
- Adding/removing products from the cart
- Filling checkout forms using DataTable
- Asserting expected outcomes (product visibility, alert messages, confirmation dialogs)

### 3. Hooks (`src/test/java/com/epam/campus/bdd/steps/Hooks.java`)

Implements Cucumber lifecycle hooks:
- **`@Before`** — Initializes a fresh ChromeDriver instance before each scenario
- **`@After`** — Captures a screenshot on failure (attaches to report + saves to disk), then quits the browser

### 4. WebDriver Factory (`src/main/java/com/epam/campus/bdd/utils/WebDriverFactory.java`)

A thread-safe utility that:
- Uses `ThreadLocal<WebDriver>` for thread safety
- Manages ChromeDriver setup via WebDriverManager (auto-downloads the correct driver binary)
- Provides `getDriver()` and `quitDriver()` methods for clean session management

### 5. Test Runner (`src/test/java/com/epam/campus/bdd/runner/TestRunner.java`)

JUnit-based Cucumber runner configured with:
- Feature file path
- Glue code package
- Report plugins (HTML + JSON + console pretty-print)

---

## How It Is Implemented

### Project Structure

```
src/
├── main/java/com/epam/campus/bdd/
│   └── utils/
│       └── WebDriverFactory.java       # WebDriver lifecycle management
└── test/
    ├── java/com/epam/campus/bdd/
    │   ├── runner/
    │   │   └── TestRunner.java         # Cucumber-JUnit test runner
    │   └── steps/
    │       ├── DemoBlazeSteps.java     # Step definitions (Gherkin → Selenium)
    │       └── Hooks.java             # @Before/@After hooks with screenshot support
    └── resources/
        └── features/
            └── demoblaze.feature       # Gherkin scenarios
```

### Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Language |
| Cucumber 7.23.0 | BDD framework — feature files and step definitions |
| Selenium 4.27.0 | Browser automation |
| WebDriverManager 5.9.2 | Automatic browser driver binary management |
| JUnit 4.13.2 | Test execution framework |
| Maven | Build and dependency management |

### Key Design Decisions

1. **ThreadLocal WebDriver** — Ensures each scenario gets an isolated browser session; safe for parallel execution in the future.

2. **WebDriverManager** — Eliminates manual ChromeDriver binary management. It auto-detects the installed Chrome version and downloads the matching driver.

3. **Explicit Waits** — Uses `WebDriverWait` with `ExpectedConditions` instead of `Thread.sleep` where possible. Short sleeps are used only where DemoBlaze's AJAX rendering requires them (category loading, cart updates).

4. **Screenshot on Failure** — The `@After` hook checks `scenario.isFailed()`, captures a PNG screenshot, and:
   - Attaches it to the Cucumber report (visible in HTML report)
   - Saves it to `target/screenshots/` for local review

5. **DataTable for Checkout** — The checkout scenario uses a Cucumber DataTable to pass structured form data, making the feature file readable and the step definition reusable.

---

## Why It Was Required

### BDD Purpose
Behavior-Driven Development bridges the gap between business requirements and automated tests. Feature files serve as living documentation that non-technical stakeholders can read and validate.

### Selenium Integration
Real browser automation verifies that the UI works as expected from the end-user's perspective — not just backend logic, but actual rendered elements, navigation, and JavaScript interactions.

### Hooks for Session Management
Without proper `@Before`/`@After` hooks:
- Tests would share browser state, causing flaky interdependent tests
- Browser processes would leak and consume resources
- Failed test debugging would be significantly harder without screenshots

### Screenshot Capture
When a UI test fails, the error message alone ("element not found") is rarely sufficient. A screenshot provides immediate visual context of what the page looked like at failure time — essential for debugging dynamic web applications.

---

## How to Run

### Prerequisites
- Java 17+
- Maven 3.6+
- Google Chrome installed

### Execute Tests

```bash
mvn clean test
```

### View Reports

After execution, open:
```
target/cucumber-reports/cucumber.html
```

Failed scenario screenshots are saved to:
```
target/screenshots/
```

---

## Notes

- This file (`guide.md`) is excluded from version control via `.gitignore`.
- The DemoBlaze website is a public demo app; tests depend on its availability.
- If Chrome version changes, WebDriverManager handles it automatically — no manual driver updates needed.
