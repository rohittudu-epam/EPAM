# BankAccount Test Suite with TestNG

Welcome to the comprehensive BankAccount Test Suite project. Using the TestNG framework, this project aims to validate various functionalities of the `BankAccount` class. This README provides structured tasks ranging from basic test setups to leveraging advanced TestNG features such as data-driven testing, parallel execution, and strategic test ordering. The instructions are designed to familiarize you with extensive aspects of automated software testing.

## Project Setup

This project is set up as a Maven project with dependencies on TestNG specified in the `pom.xml` file. Ensure Maven and Java are installed on your computer to compile and run tests effectively.

## Tasks

### Task 1: Basic Test Case Creation
**Objective**: Validate the correct initialization and basic transaction operations of the `BankAccount` class.

- **Test Class Name**: `BankAccountInitializationTests`
- **Instructions**:
    - Create a test method to verify that the account is correctly initialized with a positive balance. For instance, test with initial balances like 0, 100, and 500 to cover a range of typical scenarios.
    - Include tests to confirm that depositing correct amounts results in the accurate updating of the balance. Check the account balance after a single deposit and multiple deposits, ensuring the sum is reflected correctly.
    - Write a test to confirm proper balance updates after a withdrawal. Test both scenarios where the withdrawal amount is less than the account balance and when it is equal to the current balance, to verify that the new balance is calculated correctly.

### Task 2: Testing Core Functionalities with Setup and Teardown
**Objective**: Ensure that the deposit and withdrawal operations function as expected.

- **Test Class Name**: `BankAccountTransactionTests`
- **Instructions**:
    - Use `@BeforeMethod` to instantiate `BankAccount` before each test.
    - Write tests for deposit and withdrawal operations checking balances.
    - Utilize `@AfterMethod` to nullify the `BankAccount` instance post each test.

### Task 3: Edge Case and Exception Handling
**Objective**: Thoroughly test the system’s response to exceptional and border case scenarios.

- **Test Class Name**: `BankAccountExceptionTests`
- **Instructions**:
    - Prioritize testing common operations before diving into exceptional cases using the `@Test(priority = n)` where n is the order of execution.
    - Check error handling and exceptions for invalid operations such as withdrawing more than the balance.

### Task 4: Data-Driven Testing Using DataProvider
**Objective**: Perform multiple iterations of the deposit method testing under different scenarios.

- **Test Class Name**: `BankAccountDataDrivenTests`
- **Instructions**:
    - Use `@DataProvider` to feed multiple sets of deposit amounts and expect balances to a single `@Test` method.
    - Assert the outputs match the expected outputs supplied by the DataProvider.

### Task 5: Parameterized Tests via XML
**Objective**: Drive tests for withdrawals based on external parameters defined in an XML configuration.

- **Test Class Name**: `BankAccountParameterizedTests`
- **Instructions**:
    - Configure `testng.xml` to send varying withdrawal amounts and expected outcomes.
    - Implement `@Parameters` in test methods to receive these values for more flexible testing.

### Task 6: Implementing Parallel Testing and Test Listeners
**Objective**: Improve execution speed and gain insights through test listeners.

- **Listener Class Name**: `BankAccountTestListener`
- **Instructions**:
    - Mark tests for parallel execution by editing `testng.xml`, ensuring thread safety in test methods.
    - Create and configure a test listener using `ITestListener` interface to observe test events and log relevant information.

### Task 7: Advanced Test Control with `dependsOnMethods` and `enabled`
**Objective**: Fine-tune the execution order and enable/disable specific tests strategically.

- **Test Class Name**: `BankAccountAdvancedControlTests`
- **Instructions**:
    - Utilize `dependsOnMethods` to create dependencies between tests where preceding test outcomes influence others.
    - Experiment with the `enabled` attribute to disable tests that are either flaky or not relevant to current test cycles.

## Deliverables
- Update and push the modified Java classes to your Git repository.
- Submit a detailed report evaluating the execution outcomes of each task and your lessons learned about managing complex test scenarios using TestNG.

## Running Instructions
To execute your tests, run:
```bash
mvn test