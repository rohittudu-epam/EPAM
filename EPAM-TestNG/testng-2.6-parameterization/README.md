# Hands-On Task: TestNG Parameterization

Welcome to Task 6 of TestNG training! In this task, you will learn how to use TestNG XML parameterization to pass inputs directly from a `testng.xml` file into test methods. This practical exercise helps you understand the advantages of externalizing inputs and making your tests more dynamic and reusable.

---

## Objectives

By completing this task, you will:

1. Utilize TestNG XML files to specify test parameters.
2. Use the `@Parameters` annotation to inject and use parameters in test methods.
3. Configure and run tests that rely on externalized input values.

---

## Task Instructions

### Step 1: Clone the Repository
1. Clone the Git repository provided for this task.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA or Eclipse).
3. Review the code structure to familiarize yourself with the folder and file setup.

---

### Step 2: Implement the Test Class
1. Create a test class named `CalculatorParameterizedTest`.
2. Add test methods to validate operations of the `Calculator` class (`add`, `divide`, etc.). Test methods should:
    - Use the `@Parameters` annotation to inject values from the `testng.xml` file.
    - Include assertions to verify results based on the provided input parameters.

---

### Step 3: Configure the TestNG XML File
1. Create a `testng.xml` file.
2. Define parameters in the XML file to pass input values to the test class and methods.
    - Parameters should be defined at the `<suite>` or `<test>` level.
3. Verify that your XML file is properly structured and valid.

---

### Step 4: Execute and Verify
1. Run the test suite using the `testng.xml` file.
    - Observe whether parameters are correctly injected into the test methods.
    - Verify that the test results are as expected.
2. Validate the behavior if parameters are incorrect or missing (optional).

---

### Step 5: Push Your Changes
1. Commit and push your changes to the repository.
2. Ensure your repository includes:
    - Fully implemented Test Class (`CalculatorParameterizedTest`).
    - The configured TestNG XML file (`testng.xml`).
3. Add meaningful commit messages to describe your work.

---

## Deliverables

By the end of this task, you should have:

1. **Test Class (`CalculatorParameterizedTest`)**:
    - Uses the `@Parameters` annotation to utilize parameters passed from the XML file.

2. **TestNG XML File (`testng.xml`)**:
    - Contains properly defined parameters.
    - Configured to pass inputs dynamically to test methods.

3. **Execution Results**:
    - Verified execution of test methods using parameterized inputs.

---

## Additional Information

- Refer to the [TestNG Documentation](https://testng.org/doc/) for `@Parameters` and XML parameterization guidelines.
- Design your test methods to handle edge cases, like invalid parameters or edge conditions.
- Use meaningful parameter values in `testng.xml` to validate both positive and negative scenarios.

---

## Task Submission Checklist

Before marking this task as complete, ensure that:

- [ ] Test methods successfully utilize parameters injected via `@Parameters`.
- [ ] A properly configured `testng.xml` file is included in the repository.
- [ ] Test execution is successful with the provided parameterized inputs.
- [ ] All changes are committed and pushed to the repository.

---

Good luck! If you encounter any issues, feel free to reach out to your trainer for clarification or support.

---