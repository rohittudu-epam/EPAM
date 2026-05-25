# Hands-On Task: Grouping and Parallel Test Execution

Welcome to this hands-on task where you will learn how to group test cases logically using TestNG and configure parallel test execution. This task will guide you in organizing, managing, and optimizing test execution using TestNG's powerful features.

---

## Objectives

By completing this task, you will:

1. Implement **Group Testing** to logically organize test cases into groups using the `groups` attribute.
2. Execute specific test groups selectively using TestNG XML configuration.
3. Configure and execute tests in **parallel** or **multi-threaded mode** to optimize test execution and reduce runtime.

---

## Task Instructions

### Step 1: Clone the Repository
1. Clone the Git repository provided for this task.
2. Open the project using your preferred IDE (e.g., IntelliJ IDEA or Eclipse).
3. Familiarize yourself with the existing folder and file structure.

---

### Step 2: Implement Group Testing
1. **Create a Test Class**:
    - Create a new test class named `CalculatorGroupTest`.

2. **Add Test Methods**:
    - Create test methods that simulate basic calculator operations from the `Calculator` class:
        - Examples:
            - `testAddition()` for addition.
            - `testSubtraction()` for subtraction.
            - `testMultiplication()` for multiplication.
            - `testDivision()` for division.

3. **Assign Groups to Test Methods**:
    - Use the `groups` attribute in the `@Test` annotation to categorize test cases into logical groups, such as:
        - `arithmetic`: For basic operations (e.g., addition, subtraction).
        - `advanced`: For complex operations (e.g., division, multiplication).
        - `edge-cases`: For operations that involve edge conditions (e.g., division by zero).
    - Example:
      ```java
      @Test(groups = {"arithmetic"})
      public void testAddition() { ... }
 
      @Test(groups = {"arithmetic", "edge-cases"})
      public void testDivisionByZero() { ... }
      ```

---

### Step 3: Execute Specific Groups from TestNG XML
1. **Create a TestNG XML File (`testng.xml`)**:
    - Define a `<suite>` and `<test>` configuration.
    - Use the `<groups>` tag to include or exclude specific test groups. Example:
      ```xml
      <test name="ArithmeticTests">
          <groups>
              <run>
                  <include name="arithmetic"/>
              </run>
          </groups>
          <classes>
              <class name="com.example.calculator.CalculatorGroupTest"/>
          </classes>
      </test>
      ```
    - Experiment with excluding a group (e.g., `edge-cases`) from execution.

2. Run the XML configuration to execute only the specified test groups.

---

### Step 4: Configure Parallel Test Execution
1. **Enable Parallel Execution in the TestNG XML File**:
    - Configure the `<suite>` tag with the `parallel` attribute:
        - Options for parallel modes:
            - `parallel="classes"`: Executes test classes in parallel.
            - `parallel="methods"`: Executes test methods in parallel.
            - `parallel="tests"`: Executes `<test>` blocks in parallel.
    - Define the `thread-count` attribute to specify the number of threads for parallel execution.
    - Example:
      ```xml
      <suite name="ParallelExecutionSuite" parallel="methods" thread-count="4">
          <test name="CalculatorTests">
              <classes>
                  <class name="com.example.calculator.CalculatorGroupTest"/>
              </classes>
          </test>
      </suite>
      ```

2. Run your test suite using the XML file and observe the behavior to confirm tests are executed in parallel.

---

### Step 5: Validate and Optimize
1. Verify that:
    - Test methods are executed based on the specified groups.
    - Parallel execution reduces total execution time while maintaining expected results.
2. Experiment by increasing or decreasing the thread count and observe the changes in execution time.
3. Identify any potential conflicts in shared resources if tests cannot run in parallel and apply best practices to avoid them.

---

### Step 6: Push Your Changes
1. Commit and push your changes to your repository.
2. Ensure your repository includes:
    - The grouped test class (`CalculatorGroupTest`).
    - The configured XML file (`testng.xml`) for selective and parallel execution.
3. Write meaningful commit messages, such as:
    - "Implemented group-based test execution in CalculatorGroupTest."
    - "Added parallel configuration to TestNG XML for optimized execution."

---

## Deliverables

By the end of this task, you should have:

1. **Grouped Test Class (`CalculatorGroupTest`)**:
    - Includes methods organized into meaningful groups using the `groups` attribute.

2. **TestNG XML Configuration File**:
    - Configured to execute specific test groups (`<include>` and `<exclude>`).
    - Configured for parallel execution using `parallel` and `thread-count` attributes.

3. **Execution Results**:
    - Verified selective group execution and parallel test execution results.

---

## Additional Information

1. **Grouping in TestNG**:
    - Use the `groups` attribute to manage test cases logically.
    - Include or exclude groups in the `testng.xml` file based on execution needs.

2. **Parallel Tests in TestNG**:
    - Leverage the `parallel` attribute in the TestNG XML file for faster execution.
    - Ensure that tests running in parallel are independent and do not share common resources.

3. **Learn More**:
    - Refer to the [TestNG Documentation](https://testng.org/doc/documentation-main.html#parallel-running) for details on group testing and parallel execution.

---

## Task Submission Checklist

Before marking this task as complete, ensure that:

- [ ] Test methods are logically grouped into meaningful categories (e.g., arithmetic, advanced).
- [ ] The `testng.xml` file is properly configured for selective group execution.
- [ ] The test execution is optimized with parallel configuration (`parallel`, `thread-count`).
- [ ] All changes are committed and pushed to the repository.
