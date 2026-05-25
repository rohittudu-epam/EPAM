# Hands-On Task: Assertions in TestNG

Welcome to this hands-on task focusing on assertions in TestNG! Assertions are a fundamental aspect of any test automation framework to validate the correctness of the application under test. This task will help you understand and implement both **hard assertions** (e.g., `Assert.assertEquals`) and **soft assertions** for validating multiple conditions.

---

## Objectives

By completing this task, you will:

1. Learn how to validate the correctness of test results using **hard assertions** (`assertEquals`, `assertTrue`, etc.).
2. Understand the difference between **hard assertions** and **soft assertions**.
3. Implement **soft assertions** to perform multiple validations within a single test method.
4. Practice applying assertions in a test suite.

---

## Task Instructions

### Step 1: Clone the Repository
1. Clone the Git repository provided for this task.
2. Open the project using an IDE of your choice (e.g., IntelliJ IDEA, Eclipse).
3. familiarize yourself with the `Calculator` class provided in the project. It contains basic arithmetic operations.

---

### Step 2: Implement Test Methods with Assertions

1. **Create Test Class**:
    - Create a new test class named `CalculatorAssertionTest`.

2. **Hard Assertions**:
    - Write test methods using **hard assertions**, such as:
        - `Assert.assertEquals(actual, expected)` to verify if the actual output matches the expected result (e.g., validating addition and subtraction results).
        - `Assert.assertTrue(condition)` to verify if a condition holds true (e.g., validating non-zero division results).
        - `Assert.assertFalse(condition)` to verify if a condition holds false (e.g., division by zero validation).

      Hard assertions stop the test's execution immediately when an assertion fails.

3. **Soft Assertions**:
    - Write an additional method to perform **multiple validations** using **soft assertions**:
        - Use `SoftAssert` provided by TestNG.
        - Validate multiple operations (addition, subtraction, multiplication, division) in a single test method.
        - Use `assertAll()` at the end of the test to report all failures.

4. Validate the behavior of both hard and soft assertions:
    - See how hard assertions stop execution after a failure.
    - Observe how soft assertions continue execution despite failures but log all errors at the end.

---

### Step 3: Execute the Test Suite
1. Run the test methods using your IDE or a TestNG XML file.
2. Observe the results in the console or test reports:
    - Confirm that hard assertions stop execution immediately upon encountering a failure.
    - Check the logged failures when using soft assertions.
    - Validate that all scenarios pass when conditions are correct.

---

### Step 4: Push Your Changes
1. Commit and push your changes to the repository.
2. Ensure your repository includes:
    - The test class (`CalculatorAssertionTest`).
    - Test methods implemented with both hard and soft assertions.
3. Use meaningful commit messages, such as:
    - "Implemented hard assertions for Calculator operations."
    - "Added soft assertions for multiple validations in a single method."

---

## Deliverables

By the end of this task, you should have:

1. **Test Class (`CalculatorAssertionTest`)**:
    - Includes test methods using hard assertions for individual validations.
    - Includes test methods using soft assertions to validate multiple conditions in a single method.

2. **Execution Results**:
    - Verified that both hard and soft assertions behave as expected.
    - Logs displaying assertion results, including any failures.

---

## Additional Information

1. **Hard Assertions**:
    - Use `Assert` methods from TestNG for hard assertions.
    - Examples:
      ```java
      Assert.assertEquals(actual, expected, "Addition result mismatch");
      Assert.assertTrue(condition, "Condition failed");
      Assert.assertFalse(condition, "Unexpected condition met");
      ```
    - Stops test execution immediately upon failure.

2. **Soft Assertions**:
    - Use `SoftAssert` for cases requiring multiple validations in a single test.
    - Example:
      ```java
      SoftAssert softAssert = new SoftAssert();
      softAssert.assertEquals(actual1, expected1, "First validation failed");
      softAssert.assertTrue(condition, "Second validation failed");
      softAssert.assertAll(); // Report all assertion failures
      ```
    - Continues execution until `assertAll()` and then reports all failures together.

3. Refer to the [TestNG Documentation](https://testng.org/doc/documentation-main.html#asserts) for detailed guidance on using assertions.

---

## Task Submission Checklist

Before marking this task as complete, ensure that:

- [ ] A test class (`CalculatorAssertionTest`) is implemented and contains both hard and soft assertions.
- [ ] Test methods with hard assertions validate specific operations like addition and subtraction.
- [ ] At least one test method with soft assertions validates multiple operations in a single method.
- [ ] All changes are committed and pushed to the repository.
