# Hands-On Task: TestNG Configurations and XML Suite Design (Calculator Application)

This hands-on task focuses on learning how to use TestNG for creating and managing test cases, organizing them into test suites, and executing tests with configurations like parallel execution. This task will use a simple Java application (e.g., `Calculator` class).

---

## Objectives

By the end of this task, you will be able to:

1. Write test classes and test methods to validate a simple Java application (e.g., `Calculator`).
2. Configure a TestNG XML suite file to execute specific test methods and classes.
3. Include or exclude test methods and groups using the XML suite configuration.
4. Set up parallel execution of tests with a specified thread count using the XML suite configuration.
5. Verify that the test execution behavior matches the configurations in the XML suite file.

---

## Task Details

Below are the steps you need to follow to complete this task:

### Step 1: Clone the Repository
1. Clone the Git repository provided for this task.
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA or Eclipse).
3. Explore the repository structure:
   - A placeholder `Calculator` class will be provided with basic arithmetic operations (`add`, `subtract`, `multiply`, `divide`).
   - You will need to write test classes, configure the TestNG suite, and implement functionality based on the instructions below.

---

### Step 2: Implement the TestNG Test Classes
1. **Understand the `Calculator` Application:**
   - A `Calculator` class is provided in the repository, which contains the following methods:
     - `add(int a, int b)`
     - `subtract(int a, int b)`
     - `multiply(int a, int b)`
     - `divide(int a, int b)`
   - The methods perform basic arithmetic operations and return the result.

2. **Create Test Classes:**
   - Implement **two test classes** to test the `Calculator` functionality:
     - `CalculatorAdditionTest`: For testing the `add` and `subtract` methods of the `Calculator`.
     - `CalculatorMultiplicationTest`: For testing the `multiply` and `divide` methods of the `Calculator`.

3. **Write Test Methods:**
   - Write multiple test methods in each class. For example:
     - In `CalculatorAdditionTest`:
       - `testPositiveAddition()`: Tests addition with positive numbers.
       - `testNegativeAddition()`: Tests addition with negative numbers.
       - `testSubtraction()`: Tests the `subtract` method.
     - In `CalculatorMultiplicationTest`:
       - `testPositiveMultiplication()`: Tests multiplication with positive numbers.
       - `testDivisionByZero()`: Tests division by zero (should handle exceptions, if any).

4. Annotate test methods with `@Test` to make them executable by TestNG.

---

### Step 3: Create a TestNG XML Suite File
1. **Add a TestNG XML File:**
   - Create a TestNG XML file (`testng.xml`) at the root of the project.

2. **Define Suite and Test Sections:**
   - Create a `<suite>` tag to define the suite.
   - Add multiple `<test>` sections inside the suite to:
     - Include all test methods from `CalculatorAdditionTest`.
     - Exclude specific methods like `testNegativeAddition` from `CalculatorAdditionTest`.
     - Include only certain methods (e.g., `testDivisionByZero`) from `CalculatorMultiplicationTest`.

3. **Use Groups (Optional):**
   - Categorize your test methods into groups (e.g., "addition", "multiplication", "division").
   - Configure the XML to include or exclude specific groups, if applicable.

---

### Step 4: Configure Parallel Execution
1. **Enable Parallel Execution:**
   - In the `<suite>` tag of the XML file, configure the `parallel` attribute to specify how tests should be executed (e.g., `parallel="methods"` or `parallel="tests"`).
   - Use the `thread-count` attribute to set how many threads will execute tests in parallel.

2. **Verify Behavior:**
   - Ensure the test methods/classes do not interfere with each other during parallel execution.
   - Use log output or assertions to confirm that tests are running as expected in parallel.

---

### Step 5: Execute the TestNG Suite
1. Run the TestNG XML file:
   - Right-click the XML file in your IDE and select "Run."
   - Alternatively, use the command-line or Maven (if Maven is set up):
     ```shell
     mvn test -DsuiteXmlFile=testng.xml
     ```

2. Observe the Results:
   - Verify that all included test methods ran as expected.
   - Confirm that excluded methods or groups were not executed.
   - For parallel execution, check that multiple test methods ran simultaneously (as configured).

---

### Step 6: Push Your Changes
1. Verify all your configurations and test methods before committing your changes.
2. Push the following updates to the repository:
   - Implemented test classes (`CalculatorAdditionTest`, `CalculatorMultiplicationTest`).
   - Configured TestNG XML suite file (`testng.xml`).
3. Add meaningful commit messages, such as:
   - "Implemented Calculator test classes with TestNG."
   - "Configured TestNG XML suite with parallel execution and exclusions."

---

## Deliverables

By the end of this task, you should have:

1. **Test Classes:** At least two classes:
   - `CalculatorAdditionTest` with test methods for addition and subtraction.
   - `CalculatorMultiplicationTest` with test methods for multiplication and division.
2. **TestNG XML Suite File:** A configured XML file (`testng.xml`) that includes test suite definitions, inclusion/exclusion rules, and parallel execution.
3. **Execution Results:** Verified test execution to confirm that XML configurations worked correctly.

---

## Additional Information

- Refer to the [TestNG Documentation](https://testng.org/doc/) to explore advanced configurations for XML suites.
- Modularize your code and test classes for better organization and maintainability.
- Ensure edge cases are tested (e.g., division by zero).

---

## Task Submission Checklist

Use the following checklist before submission:

- [ ] Test classes are implemented with meaningful test methods.
- [ ] A TestNG XML file is configured for inclusions, exclusions, and parallel execution.
- [ ] All tests execute as expected.
- [ ] Changes are committed and pushed to the remote repository.
