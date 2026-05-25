# Hands-On Task: Test Listeners and Custom Reporting

Welcome to Task 7 of TestNG training! In this task, you will learn how to integrate custom TestNG listeners to capture test execution events and generate custom logs. This exercise will help you understand how to monitor and report test executions dynamically.

---

## Objectives

By completing this task, you will:

1. Learn how to use TestNG listeners to capture test execution events.
2. Implement a custom listener to track the status of each test (pass, fail, skip).
3. Generate meaningful logs for better insight into test execution behavior.

---

## Task Instructions

### Step 1: Clone the Repository
1. Clone the Git repository provided for this task.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA or Eclipse).
3. Familiarize yourself with the project structure and existing files.

---

### Step 2: Implement a Custom Listener
1. **Create a Custom Listener Class**:
   - Create a class that implements the `ITestListener` interface from TestNG.
   - Override methods from `ITestListener`, such as:
      - `onTestStart()`: Triggered when a test starts.
      - `onTestSuccess()`: Triggered when a test passes.
      - `onTestFailure()`: Triggered when a test fails.
      - `onTestSkipped()`: Triggered when a test is skipped.

2. **Log Test Execution Details**:
   - In each overridden method, add logic to log details of the test execution (e.g., test name, status).
   - Use `ITestResult` to fetch information about the executed test, such as:
      - Test name: `result.getName()`
      - Exception (if any): `result.getThrowable()`

3. **Sample Scenarios** to Log:
   - A simple message when a test starts (e.g., "Starting Test: testAddition").
   - Pass/Fail status at the end of each test (e.g., "Test Passed: testAddition" or "Test Failed: testDivision").
   - Log the exception details if a test fails, if applicable.

---

### Step 3: Integrate the Listener
1. **Register the Custom Listener in testng.xml**:
   - Add the custom listener class in your `testng.xml` file under the `<listeners>` section:
     ```xml
     <listeners>
         <listener class-name="com.example.listeners.CustomTestListener"/>
     </listeners>
     ```
   - Ensure the XML file references the correct package and class where your listener is implemented.

2. **Alternatively, Use @Listeners Annotation**:
   - Annotate your test class with `@Listeners` to attach the listener:
     ```java
     @Listeners(com.example.listeners.CustomTestListener.class)
     public class MyTestClass { ... }
     ```

---

### Step 4: Validate Listener Behavior
1. Create a test class with multiple test methods (some passing, some failing, and some skipped).
2. Verify that your custom listener:
   - Logs a message at the start of each test.
   - Captures and logs the pass, fail, or skip status correctly at the end of each test.
   - Logs failure details (e.g., exception message) for failed tests.
3. Review the console output to ensure logs are generated as expected.

---

### Step 5: Push Your Changes
1. Commit and push your changes to the repository.
2. Ensure your repository includes:
   - The custom listener class.
   - A test suite showcasing the use of the listener.
   - The updated TestNG configuration (`testng.xml`) with references to the listener.
3. Write meaningful commit messages, such as:
   - "Implemented custom TestNG listener for logging test execution events."
   - "Added test suite to validate custom listener functionality."

---

## Deliverables

By the end of this task, you should have:

1. **Custom Listener Class**:
   - Implements `ITestListener` to capture test execution events.
   - Logs meaningful information (test name, pass/fail status, exception details).
2. **TestNG Configuration File (`testng.xml`)**:
   - Includes the listener under the `<listeners>` section.
3. **Execution Results**:
   - Verified test suite where logs for all test execution events are displayed in the console.

---

## Additional Information

- Refer to the [TestNG Listeners Documentation](https://testng.org/doc/documentation-main.html#testng-listeners) for details on implementing `ITestListener`.
- Add meaningful log messages for better readability and debugging.
- Customize your listener further (optional):
   - Write logs to a file instead of the console, if needed.
   - Extend the listener to include additional details, such as timestamps.

---

## Task Submission Checklist

Before marking this task as complete, ensure that:

- [ ] A custom listener class is implemented with all relevant `ITestListener` methods.
- [ ] Test logs are generated for all test execution events (start, pass, fail, skip).
- [ ] The custom listener is registered in the `testng.xml` file or via annotations.
- [ ] All changes are committed and pushed to the repository.
