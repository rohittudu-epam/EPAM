# Hands-On Task: Browser Automation Basics

In this task, you will extend an existing Maven project to automate basic browser operations using Selenium WebDriver. You will also set up cross-browser testing capabilities to validate tests across multiple browsers: **Chrome**, **Firefox**, and **Edge**.

---

## Objectives

By completing this hands-on task, you will:

1. Write tests to automate basic browser workflows:
   - Launching a browser.
   - Navigating to a specific webpage.
   - Refreshing the webpage.
   - Validating certain browser behavior using **assertions**.
   - Closing the browser after performing the operations.
2. Implement cross-browser test execution for Chrome, Firefox, and Edge using Selenium WebDriver and TestNG.
3. Configure TestNG XML to support dynamic parameter-driven execution for browser selection.

---

## Prerequisites

The following have already been set up for you:

1. **Maven Project**:
   - A pre-configured Maven project is provided with the necessary dependencies.

2. **Selenium and TestNG Setup**:
   - Selenium WebDriver and TestNG dependencies are already included in the `pom.xml`.

Before starting this task, ensure that you are familiar with TestNG annotations and Selenium WebDriver basics.

---

## Instructions

### 1. Implement the Test Class

- **Location**: Navigate to `src/test/java` in the project structure.
- **Steps**:
   1. Create a package named `com.example.browsers` (if it does not already exist).
   2. Create a new Java class in the package named `BrowserTests`.

#### Test Class Specifications:

1. **Test Class Name**: `BrowserTests`

2. **Methods to Implement**:

   - **`setUp(String browser)`**:
      - Purpose: Initialize the WebDriver instance dynamically based on the browser parameter (e.g., Chrome, Firefox, or Edge).
      - Annotation: Use `@BeforeMethod` to indicate this setup method runs before each test.
      - Parameter:
         - `browser`: A string parameter passed from the TestNG XML file to determine which browser to launch.

   - **`testBasicBrowserActions()`**:
      - Purpose: Implement the core test workflow using Selenium WebDriver.
         - Launch the specified browser.
         - Navigate to "https://www.google.com".
         - Validate:
            - The page title matches the expected value using assertions.
         - Refresh the page and confirm functionality using logs or basic validation.
      - Annotation: Use `@Test` to signify this is the test case.

   - **`tearDown()`**:
      - Purpose: Safely close and quit the WebDriver instance to avoid leaving browser instances open after test execution.
      - Annotation: Use `@AfterMethod` for cleanup after each test.

---

### 2. Configure Cross-Browser Testing in `testng.xml`

- **Location**: The `testng.xml` file is located at the root of the project.

- **Steps**:
   1. Add a parameter named `browser` to the `testng.xml` file.
   2. Configure the parameter to pass the browser selection (`chrome`, `firefox`, `edge`) to the `setUp` method in the `BrowserTests` class.
   3. Ensure the `testng.xml` file includes the following:
      - A suite definition.
      - A test that references the `BrowserTests` class.
      - The ability to dynamically select a browser at runtime.

#### Example Configuration:
- The `browser` parameter is defined in the `testng.xml` file and dynamically passed to the test method during execution.

---

### 3. Execute the Tests

Once the test class and configuration are implemented:

1. **Run Default Test**:
   - Run the `testng.xml` configuration.
   - Verify the test executes successfully on the default browser (`chrome`).

2. **Test with Other Browsers**:
   - Modify the `browser` parameter in the `testng.xml` file to execute tests on:
      - Firefox (`firefox`)
      - Edge (`edge`)
   - Run the test again and confirm it works on all specified browsers.

3. **Validate Behavior**:
   - Confirm that the following expected behaviors occur:
      1. The browser launches successfully.
      2. The browser navigates to "https://www.google.com".
      3. The **page title** matches the expected value ("Google").
      4. The test logs indicate the page was refreshed.
      5. The browser instance is closed after execution.

---

## Deliverables

By completing this task, you should have:

1. **BrowserTests Test Class**:
   - Implements the following:
      - `setUp(String browser)`
      - `testBasicBrowserActions()`
      - `tearDown()`

2. **Dynamic Cross-Browser Support**:
   - Configured to execute tests on Chrome, Firefox, and Edge.

3. **TestNG XML Configuration**:
   - Supports dynamic parameterization for browser selection.

4. **Verified Execution**:
   - Tests pass successfully on all three browsers.

---

## Additional Information

1. **Dynamic Driver Management**:
   - Selenium 4+ automatically manages browser drivers (e.g., ChromeDriver, GeckoDriver) if WebDriverManager is being used.

2. **Useful Documentation**:
   - Selenium WebDriver: [Selenium Documentation](https://www.selenium.dev/documentation/webdriver/)
   - TestNG: [TestNG Documentation](https://testng.org/doc/)

---

## Submission Checklist

Before marking this task as complete, ensure the following:

- [ ] A new test class named `BrowserTests` has been added to the package `com.example.browsers`.
- [ ] All required methods (`setUp`, `testBasicBrowserActions`, `tearDown`) are implemented.
- [ ] The `testng.xml` file is correctly configured for dynamic cross-browser testing using a `browser` parameter.
- [ ] Tests are verified on Chrome, Firefox, and Edge with no errors.
- [ ] Test outputs confirm:
   - Browser launches and navigates correctly.
   - Page title is validated successfully.
   - Browser refreshes without issues.
   - Browser closes after execution.

---

## Example Commit Messages

- "Added BrowserTests class with setup, test, and teardown methods for cross-browser testing."
- "Configured TestNG XML to support browser parameterization."
- "Validated test execution on Chrome, Firefox, and Edge."

---

Good luck! Explore and troubleshoot as needed to improve your skills. If you have questions, don't hesitate to reach out to your trainer.

---