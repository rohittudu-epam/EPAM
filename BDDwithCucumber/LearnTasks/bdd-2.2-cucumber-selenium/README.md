# **Cucumber Selenium Integration**

This task focuses on integrating **Selenium WebDriver** with **Cucumber** to automate basic web UI scenarios for the **DemoBlaze web application** ([https://www.demoblaze.com](https://www.demoblaze.com)). You will perform browser actions, write feature files, implement `Step Definitions` to interact with the application's web elements, and handle browser session management using Cucumber Hooks. Additionally, you will implement error handling by capturing screenshots for failed scenarios and embedding them in Cucumber reports for debugging.

---

## **Target Application**

The application to be automated for this task is **DemoBlaze** ([https://www.demoblaze.com](https://www.demoblaze.com)). DemoBlaze is an e-commerce website where you can test the following scenarios:
- Browsing product categories.
- Adding products to the cart and completing purchases.
- Logging in and registering new users.

You will automate scenarios simulating real-world user actions on this web application.

---

## **Learning Objectives**

By completing this task, you will:
1. Understand how to integrate **Selenium WebDriver** with **Cucumber** to automate workflows for DemoBlaze.
2. Write and execute meaningful feature files to describe high-level behavior for user interactions on DemoBlaze.
3. Implement browser setup and teardown logic using `@Before` and `@After` Hooks in Cucumber.
4. Use Selenium WebDriver to perform browser actions such as navigating through categories, adding products to the cart, and validating checkout functionality.
5. Capture and attach screenshots for failed scenarios to Cucumber reports for debugging.

---

## **Tasks**

### **Task 1: Write Feature Files for DemoBlaze Automation**
1. Explore the **DemoBlaze** application ([https://www.demoblaze.com](https://www.demoblaze.com)) to identify automatable workflows.
2. Create feature files to automate the following scenarios:
   - **Scenario 1**: Browse a product category (e.g., "Laptops") and verify the list of products displayed.
   - **Scenario 2**: Add a product to the cart and verify that the product is added successfully.
   - **Scenario 3**: Remove a product from the cart and validate the cart contents.
   - **Scenario 4**: Complete the checkout process after adding products to the cart.
3. Write Gherkin steps (`Given`, `When`, `Then`) in your feature files to define user workflows and expected outcomes.

---

### **Task 2: Implement Step Definitions**
1. Create a package for Step Definitions (e.g., `com.demoblaze.steps`) in `src/test/java`.
2. Write methods in your Step Definition class to map Gherkin steps to Selenium WebDriver actions:
   - Simulate user actions like navigating to a product category, clicking "Add to Cart," verifying product details, and completing a purchase.
   - Use assertions to validate that the expected results match the actual behavior of the web application.

---

### **Task 3: Use Cucumber Hooks for Browser Setup and Teardown**
1. Create a `Hooks` class in your project to implement `@Before` and `@After` Hooks:
   - Use `@Before` to initialize the WebDriver and launch the browser before each scenario.
   - Use `@After` to close the browser session or quit WebDriver after each scenario execution.
2. Ensure that each scenario runs in an isolated browser session without relying on the state of previous tests.

---

### **Task 4: Capture Screenshots for Failed Scenarios**
1. Enhance the `@After` Hook to capture a screenshot if a scenario fails:
   - Use Selenium to take a screenshot of the browser window when a test fails.
   - Save the screenshots locally in a designated folder with unique names.
2. Attach the captured screenshots to the Cucumber report using the `Scenario` object provided by Cucumber.

---

### **Test Cases to Automate**

1. **Product Browsing**:
   - Browse the **Laptops** or **Phones** category and verify that the correct products are displayed.

2. **Add to Cart**:
   - Add a product to the cart and verify its appearance in the cart.

3. **Remove from Cart**:
   - Remove a product from the cart and validate that the cart updates correctly.

4. **Checkout Workflow**:
   - Proceed to checkout from the cart, enter payment details, and validate the success message.

5. **Validation**:
   - Ensure error handling works by attempting invalid workflows (e.g., checkout without products in the cart).

---

## **Submission Guidelines**

1. **Project Structure**:
   - Organize your project into the following structure:
     ```
     src/
         main/
             java/
                 utils/           # Utility classes for WebDriver setup, etc.
         test/
             java/
                 steps/           # Step Definition and Hook classes
             resources/
                 features/        # Gherkin feature files
     ```
2. **Repository Submission**:
   - Push your project to a GitHub repository.
   - Include:
      - Feature files defining DemoBlaze workflows.
      - Step Definition classes mapping Gherkin steps to Selenium actions.
      - Hooks class for managing setup and teardown logic with screenshot support.
      - A `README.md` file with setup and execution instructions.

---

## **Execution Instructions**

1. Open your project in an IDE (e.g., IntelliJ IDEA or Eclipse) and configure the required browser drivers (e.g., ChromeDriver, GeckoDriver).
2. Execute the Cucumber tests using your project's test runner and verify the results.
3. Review the Cucumber-generated HTML reports to ensure all tests pass successfully and failed scenarios include screenshots for debugging.

---

## **Evaluation Criteria**

1. **Feature File Quality**:
   - Are the workflows for DemoBlaze described clearly using proper Gherkin syntax?
   - Are the scenarios meaningful and aligned with the application's functionality?

2. **Selenium Integration**:
   - Are Gherkin steps correctly mapped to Step Definitions using Selenium WebDriver?
   - Do the tests accurately automate browser interactions like navigation, clicking, and validation?

3. **Hook Implementation**:
   - Are `@Before` and `@After` Hooks properly managing browser setup and teardown?
   - Are isolated browser sessions maintained across scenarios?

4. **Screenshot Handling**:
   - Are screenshots captured for failed scenarios?
   - Are they attached properly to the Cucumber reports for debugging?

5. **Project Organization**:
   - Is the project structured cleanly with distinct separation of feature files, Step Definitions, and utility classes?

6. **Completeness**:
   - Are all the assigned workflows for DemoBlaze automated and ready for demonstration?

---

## **By the End of This Task**

- You will have hands-on experience automating real-world UI workflows for the **DemoBlaze** web application using **Selenium WebDriver** and **Cucumber**.
- You will understand how to structure a project for modular and maintainable automation.
- You will implement reliable browser session management using `@Before` and `@After` Hooks.
- You will capture and debug failed scenarios effectively through screenshots attached to Cucumber reports.

---