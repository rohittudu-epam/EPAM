# Hands-On Task: Using Automation Design Patterns - PageObject and PageFactory to Write Maintainable Tests

In this task, you will design a **test automation framework** using the **Page Object Pattern** with **PageFactory**, focusing on implementing automation design patterns to write maintainable and reusable tests. You will also leverage **TestNG** for organizing and executing test cases efficiently, including parameterization and parallel test execution.

---

## Objectives

By completing this task, you will:

1. Implement the **Page Object Pattern** using **PageFactory**:
    - Encapsulate locators for better readability and maintainability.
    - Use methods in page classes to separate interactions from test logic.
2. Organize and execute test cases with **TestNG**:
    - Leverage annotations such as `@Test`, `@BeforeClass`, and `@AfterClass`.
    - Enable parameterized and parallel execution to optimize performance.
3. Write modular and maintainable automation tests:
    - Ensure page classes and test cases are designed for reusability and scalability.

---

## Instructions

### 1. Understand the Role of Page Object Pattern with PageFactory

The **Page Object Pattern** helps you design maintainable tests by encapsulating the locators and interactions for each web page in dedicated page classes.

#### Key Concept: PageFactory in POM
- **PageFactory** simplifies the implementation of Page Object Model:
    - Elements are defined with `@FindBy` annotations for cleaner and more readable locator definitions.
    - PageFactory automatically initializes elements when the page class is instantiated.
- Benefits:
    - Cleaner and modular code structure.
    - Reusable page-specific methods for test interactions.

---

### 2. Implement Page Classes Using PageFactory

#### Steps to Implement Page Classes:

1. **Create a Page Class**:
    - Define a class for each web page, for example:
        - `LoginPage` to encapsulate login functionality.
        - `HomePage` to represent the homepage elements and actions.

2. **Define Locators with `@FindBy`**:
    - Replace `By` definitions with `@FindBy` annotations for readability.
    - Examples:
        - `@FindBy(id = "username") WebElement usernameField;`
        - `@FindBy(xpath = "//button[text()='Login']") WebElement loginButton;`

3. **Add Page-Specific Methods**:
    - Implement interaction methods that perform actions on elements. Examples:
        - `enterUsername(String username)` — enters text into the username field.
        - `clickLoginButton()` — clicks the login button.

4. **Initialize Elements**:
    - Use `PageFactory.initElements(driver, this)` in the constructor to initialize locators.

---

### 3. Create Test Classes Using TestNG

#### Test Class Guidelines:

1. **Test Structure**:
    - For each functionality or feature, create a separate test class.
    - Examples:
        - `LoginTests` for login functionality validation.
        - `HomeTests` for homepage feature validation.

2. **Use TestNG Annotations**:
    - Use `@BeforeClass` for setup methods (e.g., WebDriver initialization and page object creation).
    - Use `@Test` annotation for individual test cases.
    - Use `@AfterClass` for teardown methods (e.g., closing the browser and cleaning up WebDriver).

#### Guidelines for Test Classes:
- Keep test logic separate from page actions.
- Test classes should:
    - Call page methods like `LoginPage.enterUsername()`.
    - Use assertions to validate application behavior.

---

### 4. Configure Test Execution with TestNG

#### Key Features of TestNG to Leverage:

1. **TestNG XML Configuration**:
    - Use `testng.xml` to organize tests into suites.
    - Group related tests (e.g., LoginTests, HomeTests) into appropriate test suites.

2. **Parameterization**:
    - Add dynamic data (e.g., browser types, URLs) in `testng.xml` using `<parameter>` tags.
    - Access these parameters in your test classes using TestNG’s `@Parameters` annotation.

3. **Parallel Execution**:
    - Enable concurrent execution using TestNG’s `<parallel>` attribute to reduce execution time for multiple tests.

4. **Reporting**:
    - Leverage TestNG’s built-in HTML reports to evaluate the success/failure of test execution.

---

### 5. Create Maintainable Page Object Classes and Tests

#### Page Class Example Workflow:
1. **Login Page** (`LoginPage`):
    - Contains locators for username, password, login button, and error messages.
    - Includes methods like `enterUsername(String username)`, `enterPassword(String password)`, and `clickLoginButton()`.

2. **Home Page** (`HomePage`):
    - Encapsulates navigation links, search bar, and visibility checks.
    - Includes methods to interact with homepage features (e.g., navigating links, performing searches).

#### Test Class Example Workflow:
1. **Login Tests** (`LoginTests`):
    - Validate successful and failed login attempts.
    - Check error messages for invalid credentials.
2. **Home Tests** (`HomeTests`):
    - Validate navigation, interactions, and content visibility on the homepage.

---

### 6. Execute and Verify Framework Features

#### Steps to Execute:

1. **Run Individual Test Classes**:
    - Execute `LoginTests` and `HomeTests` to validate individual cases.

2. **Run Tests via TestNG XML**:
    - Execute the grouped test suites by running the `testng.xml` configuration.
    - Ensure proper suite organization, parameterization, and parallel execution.

3. **Analyze Reports**:
    - Use TestNG’s HTML reports to validate test success rates and troubleshoot any failures.

---

## Deliverables

By completing this task, you should have:

1. **Page Object Classes**:
    - Written using **Page Object Model** with **PageFactory**.
    - Modular and reusable methods encapsulated for better test design.

2. **Test Classes**:
    - Organized using **TestNG annotations** for setup, execution, and teardown.
    - Validation logic implemented using page methods and assertions.

3. **TestNG Configuration**:
    - A `testng.xml` file defined for suite execution, parameterization, and parallel testing.

4. **Execution Results**:
    - Successfully executed tests with detailed HTML reports generated by TestNG.

---

## Submission Checklist

- [ ] Page classes (`LoginPage`, `HomePage`) implemented using **PageFactory** with `@FindBy`.
- [ ] Test classes (`LoginTests`, `HomeTests`) developed to validate functionality using page methods.
- [ ] TestNG XML file configured with suites, parameters, and parallel execution.
- [ ] Test execution validated via TestNG reports.

---

## Example Commit Messages

- "Added LoginPage and HomePage classes with PageFactory integration."
- "Implemented LoginTests and HomeTests using Page Object Model and TestNG."
- "Configured testng.xml to support suites, parameters, and parallel execution."
- "Validated test framework for maintainability, modularity, and scalability."

---

## Additional Resources

1. **Selenium PageFactory Documentation**:
    - [https://www.selenium.dev/documentation/webdriver/elements/page_factory/](https://www.selenium.dev/documentation/webdriver/elements/page_factory/)
2. **TestNG Official Documentation**:
    - [https://www.testng.org/doc/](https://www.testng.org/doc/)

---
