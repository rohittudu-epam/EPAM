# **Sauce Demo Automation Assignment**

This assignment focuses on automating test cases for the **Sauce Demo web application** ([https://www.saucedemo.com](https://www.saucedemo.com)) using **Selenium WebDriver** in Java. Additionally, you'll manage the testing flow using **TestNG** for structured execution and comprehensive reporting.

---

## **Automation Scope**

### **Login Functionality Tests**
- **Test Case 1**: Validate successful login with correct credentials.
- **Test Case 2**: Validate unsuccessful login with invalid credentials and ensure that correct error messages are displayed.

### **Product Interaction Tests**
- **Test Case 3**: Validate that product inventory is accessible post-login.
- **Test Case 4**: Validate the ability to add a product to the cart.
- **Test Case 5**: Validate the ability to remove a product from the cart.
- **Test Case 6**: Validate the checkout process by proceeding through the cart to the "Finish" page.

### **Sort Functionality Test**
- **Test Case 7**: Validate sorting functionality on the product page:
   - Check different sorting criteria:
      - Name (A to Z).
      - Name (Z to A).
      - Price (Low to High).
      - Price (High to Low).

### **Logout Functionality Test**
- **Test Case 8**: Validate that the user can successfully log out.

---

## **Acceptance Criteria**

### **Page Object Model (POM)**
- Implement Page Object Model (POM) with separate classes for web pages (e.g., LoginPage, ProductPage, CartPage, CheckoutPage).
- Encapsulate web elements and interactions within these classes for maintainability and reusability.

### **TestNG Configuration**
- Use **TestNG annotations** to set up and manage tests:
   - `@BeforeSuite`: Initialize test configuration and WebDriver.
   - `@BeforeMethod`: Perform test-specific setup steps.
   - `@Test`: Write individual test cases.
   - `@AfterMethod`: Execute teardown logic specific to each test.
   - `@AfterSuite`: Close WebDriver and clean up the environment.

### **Test Isolation**
- Ensure that each test case runs independently and does not rely on the execution or state of other tests.
- Validate that state (e.g., product selections, cart data) is cleared or reset between tests.

### **Clean Code Principles**
- Apply clean coding principles like:
   - **SOLID**: Ensure classes and methods adhere to single purpose and responsibility.
   - **DRY (Don’t Repeat Yourself)**: Refactor common logic into reusable methods or helper classes.
   - **YAGNI (You Aren’t Gonna Need It)**: Avoid writing unnecessary code or overengineering.

### **TestNG Report**
- Generate a TestNG report summarizing execution results for all test cases, including:
   - Status: Pass/Fail/Skipped.
   - Execution times and any logged messages.
   - Overview of individual test results across functionality groups (Login, Product Interaction, Logout, etc.).

---

## **Project Structure Guidelines**

Organize your project directory as follows:
- **Page Classes**:
   - Create individual classes for each web page in the Sauce Demo application.
   - Define web elements and write methods to interact with those elements (e.g., login, add product to cart, sort products).
- **Test Classes**:
   - Write test classes for different functionality groups (e.g., LoginTests, ProductInteractionTests, LogoutTests).
   - Use TestNG annotations to manage setup, execution, verification, and teardown for each test.
- **Utility Classes**:
   - Create utility classes for reusable logic such as WebDriver setup, reading configuration files, or handling common interactions.

---

## **Setup Instructions**

### **Environment Setup**
1. Install the required tools, including JDK, Maven, and an IDE (e.g., IntelliJ IDEA or Eclipse).
2. Configure the necessary dependencies in your Maven project for Selenium WebDriver and TestNG.

### **Configuration Files**
1. Create a configuration file (e.g., `config.properties`) to manage environment variables such as:
   - Base URL of Sauce Demo.
   - Valid and invalid login credentials for testing.
2. Load configuration variables dynamically in your tests.

### **Execution Setup**
1. Prepare a TestNG XML file to manage test suites and execution order.
2. Ensure tests can be executed either via the TestNG XML file or directly from the IDE.

---

## **Execution Instructions**

### **Testing Requirements**
1. Login Tests:
   - Validate correct and incorrect login behaviors, ensuring error messages are displayed correctly for invalid inputs.
2. Product Interaction Tests:
   - Access the product inventory post-login and interact with products by adding/removing them from the cart.
   - Complete the checkout process, navigating to the "Finish" page successfully.
3. Sorting Tests:
   - Test sorting functionality using the predefined sorting criteria (Name and Price).
4. Logout:
   - Validate that the user can successfully log out of the application.

---

## **Submission Guidelines**

1. Host your complete implementation on a GitHub repository and submit the repository link.
2. Ensure your GitHub repository includes:
   - Properly structured code following the **Acceptance Criteria** and **Project Guidelines**.
   - A clear `README.md` file with instructions on setting up and running the project.
   - Feature functionalities implemented as described in the **Automation Scope**.

---

## **Evaluation Criteria**

1. **Functionality Coverage**:
   - Are all test cases automated and validating correct behaviors as described in the **Automation Scope**?

2. **Code Quality**:
   - Is your code modular and reusable (following POM and clean coding principles like SOLID and DRY)?

3. **TestNG Usage**:
   - Are TestNG annotations properly implemented for test management?
   - Do tests execute independently with accurate setup and teardown logic?

4. **Reporting**:
   - Is the TestNG report comprehensive, showing all test case results (pass/fail/skipped)?

5. **Repository Organization**:
   - Is your project folder structure logical and maintainable?
   - Is the `README.md` file clear and easy to follow?

---

## **By the End of This Assignment**

- You will have practical experience in automating UI functionality for a real-world application using **Selenium WebDriver** in Java.
- You will learn how to structure a test automation framework using **Page Object Model (POM)** and manage execution flow using **TestNG**.
- You will understand the importance of clean coding principles in automation scripts for scalability and long-term maintainability.
- You will deliver a professional-grade test automation project hosted on GitHub with clear instructions and reliable reporting.

---