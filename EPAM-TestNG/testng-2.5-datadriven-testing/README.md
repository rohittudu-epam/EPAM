# Hands-On Task: Data-Driven Testing

Welcome to Task 5 of TestNG training! In this task, you will learn how to create reusable test methods using the `@DataProvider` annotation in TestNG. You'll practice implementing data-driven testing to execute a test method with multiple sets of data.

---

## Objectives

By completing this task, you will:

1. Learn the concept of data-driven testing and its importance in automation.
2. Implement `@DataProvider` in TestNG to supply test methods with multiple data sets.
3. Create reusable test methods that validate different scenarios using data providers.
4. Configure and execute the tests efficiently using TestNG XML file (optional).

---

## Task Instructions

### Step 1: Clone the Repository
1. Clone the Git repository provided for this task.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA or Eclipse).
3. Review the provided `Calculator` class, which contains basic arithmetic methods, such as `add`, `subtract`, `multiply`, and `divide`.

---

### Step 2: Implement the Test Class
1. **Create a Test Class:**
   - Name the class `CalculatorDataDrivenTest`.

2. **Test Method:**
   - Write a test method `testAdditionWithData(int a, int b, int expected)` to test different scenarios for the `add` functionality of the `Calculator` class.
      - The test method should:
         - Accept input values `a` and `b`.
         - Compare the actual result of `Calculator.add(a, b)` with the `expected` result using assertions (e.g., `Assert.assertEquals()`).

3. **Create a Data Provider:**
   - Create a data provider method, e.g., `additionDataProvider`, to supply multiple sets of test data to `testAdditionWithData`.
   - Annotate the data provider method with `@DataProvider(name = "additionData")`.
   - The data provider method should return test data as an array of objects (`Object[][]`). Each row represents a test case.
      - Example test data:
        ```java
        {
           {1, 2, 3},    // 1 + 2 = 3
           {-1, -2, -3}, // -1 + -2 = -3
           {0, 0, 0},    // 0 + 0 = 0
        }
        ```

4. **Link the Test Method to the Data Provider:**
   - Use the `dataProvider` attribute in the `@Test` annotation to link the test method to the data provider, e.g.:
     ```java
     @Test(dataProvider = "additionData")
     public void testAdditionWithData(int a, int b, int expected) {
         ...
     }
     ```

---

### Step 3: Design Additional Test Scenarios
1. Once the addition test is implemented with a data provider, design additional test methods (e.g., for `subtract` and `multiply`) using similar structure:
   - Each method should leverage the data provider concept.
   - Create custom data providers for each operation (e.g., `subtractionDataProvider`, `multiplicationDataProvider`).

---

### Step 4: Create a TestNG XML Suite File
1. Create a **TestNG XML file** (`testng.xml`) to define the suite and execute the data-driven tests.
2. Include the `CalculatorDataDrivenTest` class in the XML suite.
3. Run the XML file using your IDE or command-line tools:
   ```bash
   mvn test -DsuiteXmlFile=testng.xml