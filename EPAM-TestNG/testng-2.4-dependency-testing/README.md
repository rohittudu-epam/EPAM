# Hands-On Task: Dependency Testing and Prioritization

Welcome to Task 4 of TestNG training! In this task, you will explore how to implement and control test dependencies (`dependsOnMethods`) and execution order (`priority`) using TestNG. This hands-on activity will help you understand how to efficiently organize dependent tests and manage their execution sequence.

---

## Objectives

By completing this task, you will:

1. Implement test dependencies using `dependsOnMethods` in TestNG.
2. Control the order of test execution using the `priority` attribute.
3. Understand the behavior of dependent tests when one fails.
4. Create and configure a TestNG XML file to execute the test suite.

---

## Task Instructions

### Step 1: Clone the Repository
1. Clone the Git repository provided for this task.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA or Eclipse).
3. Review the provided placeholder code structure where the application under test is represented by the `Calculator` class.

---

### Step 2: Create Test Class
1. Implement a **test class** named `CalculatorDependencyTest` to test the `Calculator` application.
2. Write the following test methods simulating different arithmetic operations of the calculator:
    - `testInitializeCalculator()`: Simulates initializing the calculator (e.g., preparing resources). This method should be executed first.
    - `testAddition()`: Tests the `add` functionality of the `Calculator`. It should depend on `testInitializeCalculator`.
    - `testSubtraction()`: Tests the `subtract` functionality. It should depend on `testAddition`.
    - `testMultiplication()`: Tests the `multiply` functionality. Assign this test a specific priority.
    - `testDivision()`: Tests the `divide` functionality, such as division by zero. Assign this test a priority lower than the multiplication test.

3. Use appropriate TestNG annotations in each method:
    - `@Test(dependsOnMethods = "...")` to define dependencies.
    - `@Test(priority = ...)` to control execution order.

---

### Step 3: Design the Test Execution Flow
1. Ensure the following dependencies and priorities are implemented:
    - `testInitializeCalculator()` must execute first and all other tests must depend on it.
    - `testAddition()` must execute after `testInitializeCalculator()`.
    - `testSubtraction()` must execute after `testAddition()`.
    - `testMultiplication()` must execute independently but with higher priority than `testDivision()`.
    - `testDivision()` should have the lowest priority among the non-dependent tests.

2. Failure Behavior:
    - Observe and document how dependent tests (`dependsOnMethods`) behave if one fails (e.g., skipping dependent tests).

---

### Step 4: Create a TestNG XML Suite File
1. Create a **TestNG XML file** (`testng.xml`) to define the test suite.
2. Configure the suite to include the `CalculatorDependencyTest` class.
3. Ensure all methods are executed within a controlled sequence based on dependencies and priorities.
4. Run the XML file and verify the expected execution order.

---

### Step 5: Execute and Verify
1. Execute the test suite using your IDE or Maven:
    - Run the XML file (e.g., `testng.xml`) from the IDE.
    - Alternatively, use the command line:
      ```bash
      mvn test -DsuiteXmlFile=testng.xml
      ```
2. Verify the test execution results:
    - Confirm that dependent tests execute in the expected order.
    - Ensure tests with `priority` specifically override the order of execution.

---

### Step 6: Push Your Changes
1. Commit and push your changes after verifying the task implementation.
2. Ensure your repository includes:
    - Test class (`CalculatorDependencyTest`).
    - TestNG XML file (`testng.xml`).
3. Write meaningful commit messages, such as:
    - "Implemented test dependencies and priorities using TestNG."
    - "Added a test suite with proper execution flow."

---

## Deliverables

By the end of this task, you should have:

1. **Test Class (`CalculatorDependencyTest`)**:
    - Includes test methods demonstrating dependencies (`dependsOnMethods`) and priorities (`priority`).
    - Handles failure scenarios for dependent tests.
2. **TestNG XML Suite File**:
    - Configured to execute tests in a controlled order using dependencies and priorities.
3. **Execution Results**:
    - Verified test execution order, including dependency flow and priority conflict resolution.

---

## Additional Information

- Refer to the [TestNG Documentation](https://testng.org/doc/) for details on using `dependsOnMethods` and `priority`.
- Modularize your code for better readability and organization.
- Ensure edge cases like dependency failure are tested and documented.

---

## Task Submission Checklist

Before marking this task as complete, ensure that:

- [ ] All test methods are implemented with clear dependencies and priorities.
- [ ] Behavior for skipped dependent tests (on failure) is validated.
- [ ] A TestNG XML file is properly configured and tested.
- [ ] All changes are committed and pushed to the repository.
