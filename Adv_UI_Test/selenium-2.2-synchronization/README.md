# Hands-On Task: Synchronization in Selenium

In this task, you will learn to effectively manage dynamic content and timing issues in Selenium WebDriver. By using implicit waits, explicit waits, and fluent waits, you will reduce flaky test failures caused by delays, race conditions, or slow-loading elements.

---

## Objectives

By completing this hands-on task, you will:

1. Understand and implement various synchronization techniques in Selenium:
    - **Implicit Waits**: Manage delays globally at the WebDriver level.
    - **Explicit Waits**: Use conditions to wait for specific elements or events.
    - **Fluent Waits**: Customize wait time, polling intervals, and exception handling.
2. Handle dynamic web content, such as slowly loading elements or dynamic page updates.
3. Reduce flaky test failures caused by timing-related issues.

---

## Prerequisites

The following are already set up in your provided Maven project:

1. **Selenium WebDriver** dependency is configured in the `pom.xml`.
2. The Maven project structure (`src/test/java`) is ready for adding test classes.
3. Basic TestNG setup is included.

Before starting, ensure you are familiar with TestNG annotations and basic Selenium methods for interacting with elements.

### Practice Websites:
- **Waits**: https://demoqa.com/dynamic-properties
- **Dynamic page updates**: https://demoqa.com/progress-bar

---

## Instructions

### 1. Create a Test Class for Synchronization

- **Location**: Add the test class in the `src/test/java/com.example.synchronization` package.
- **Steps**:
    1. Create a package named `com.example.synchronization` (if it does not already exist).
    2. Inside this package, create a new test class named `SynchronizationTests`.

#### Test Class Details:

1. **Test Class Name**: `SynchronizationTests`

2. **Methods to Implement**:

    - **`testWithImplicitWait()`**
        - **Purpose**: Demonstrate the use of implicit waits to handle timing issues globally in the WebDriver instance.
        - **Description**:
            - Set an implicit wait timeout for the WebDriver instance.
            - Attempt to locate an element that loads after a delay.
            - Verify that the implicit wait correctly handles the delay and avoids a test failure.

    - **`testWithExplicitWait()`**
        - **Purpose**: Implement explicit waits to dynamically wait for specific conditions or elements.
        - **Description**:
            - Use the `WebDriverWait` class to wait for conditions such as:
                - Visibility of an element.
                - Element to be clickable.
                - Presence of an element in the DOM.
            - Verify that the explicit wait resolves timing issues for delayed elements.

    - **`testWithFluentWait()`**
        - **Purpose**: Demonstrate the use of fluent waits for advanced synchronization.
        - **Description**:
            - Create a fluent wait with custom polling intervals and exception handling.
            - Wait for elements that load dynamically or events such as text updates.
            - Verify the behavior with assertions.

    - **`testDynamicPageUpdates()`**
        - **Purpose**: Handle dynamic page content updates (e.g., AJAX elements or animations).
        - **Description**:
            - Wait for specific dynamic changes (e.g., a loading spinner to disappear or content to appear).
            - Use a combination of explicit or fluent waits to cleanly handle these conditions.

---

### 2. Understand and Implement Synchronization Techniques

#### **Implicit Waits**
- **Purpose**: Set a maximum time for which WebDriver will attempt to find all elements globally.
- **Key Method**: `driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(x));`
- **Limitation**: Cannot handle specific conditions—use explicit or fluent waits for complex cases.

#### **Explicit Waits**
- **Purpose**: Dynamically wait for specific conditions for specific elements or events.
- **Key Class**: `WebDriverWait`
- **Key Methods**:
    - `until(ExpectedConditions.visibilityOf(element))`
    - `until(ExpectedConditions.elementToBeClickable(element))`
- **Use Conditions**:
    - Wait for visibility, clickability, or text presence.

#### **Fluent Waits**
- **Purpose**: Customize maximum wait time, polling frequency, and exception handling for more advanced scenarios.
- **Key Class**: `FluentWait`
- **Features**:
    - Specify polling intervals to repeatedly check for conditions.
    - Define exceptions to ignore while waiting.
- **Use Cases**:
    - Handle non-deterministic delays or elements that change state intermittently.

---

### 3. Execute Test Cases

#### Steps to Execute:

1. **Basic Synchronization Test**
    - Run `testWithImplicitWait`.
    - Observe how implicit wait handles slow-loading elements.

2. **Dynamic/Specialized Waits**
    - Run `testWithExplicitWait` and `testWithFluentWait`.
    - Analyze how these techniques handle specific events like:
        - Waiting for elements to appear.
        - Waiting for animations or delays to complete.

3. **Dynamic Updates Test**
    - Run `testDynamicPageUpdates` to handle scenarios involving real-time updates such as AJAX, spinners, or content refresh.

4. **Analyze Results**
    - Confirm that synchronization methods eliminate timing-related errors.

---

## Deliverables

By completing this task, you should have:

1. **SynchronizationTests Class**
    - Include test methods for implicit, explicit, and fluent waits.
    - Additional test cases for handling common dynamic content scenarios (e.g., animations or AJAX updates).

2. **Synchronization Skills**
    - Proficiency in selecting the right synchronization technique based on the test requirements.

3. **Flaky Test Reduction**
    - Verified test execution with reduced failures caused by timing-related issues.

---

## Additional Information

### **Best Practices for Synchronization**
1. Use implicit waits sparingly because they apply globally and may increase execution time unnecessarily.
2. Prefer explicit and fluent waits for scenarios requiring specific conditions.
3. Avoid using `Thread.sleep()` unless absolutely necessary, as it introduces unnecessary delays and lacks dynamism.

### **Key Classes and Methods**
- `WebDriverWait`: A robust utility for handling explicit waits with prebuilt conditions.
- `FluentWait`: Use it for more customized polling and exception handling scenarios.
- `ExpectedConditions`: Provides prebuilt conditions for visibility, presence, clickability, etc.

### **Useful Documentation**
- Selenium Waits: [https://www.selenium.dev/documentation/webdriver/waits](https://www.selenium.dev/documentation/webdriver/waits)

---

## Submission Checklist

Before submission, ensure the following:

- [ ] A new test class named `SynchronizationTests` is created inside the `com.example.synchronization` package.
- [ ] Test methods for implicit waits (`testWithImplicitWait`) are implemented.
- [ ] Test methods for explicit waits (`testWithExplicitWait`) are implemented.
- [ ] Test methods for fluent waits (`testWithFluentWait`) are implemented.
- [ ] All tests execute successfully and handle dynamic behavior seamlessly.

---

## Example Commit Messages

- "Added SynchronizationTests class with implicit wait implementation."
- "Implemented explicit wait functionality for handling delayed elements."
- "Added fluent wait examples to manage advanced synchronization scenarios."
- "Verified test cases for dynamic page updates and flaky test reduction."

---