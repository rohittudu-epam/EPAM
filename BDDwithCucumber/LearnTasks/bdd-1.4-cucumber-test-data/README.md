# **Hands-On Task: Test Data Management**

This hands-on task focuses on managing test data directly within **feature files** using Gherkin constructs like **Scenario Outlines**, **DataTables**, and **Doc Strings**. You will parameterize tests for multiple data sets, structure tabular input, handle complex data, and dynamically organize test cases for edge and negative scenarios.

---

## **Learning Objectives**

By the end of this task, you will:
1. Write **Scenario Outlines** using `Examples` tables to perform data-driven testing directly in your feature files.
2. Use **DataTables** to define structured tabular data for multiple steps in a single scenario.
3. Utilize **Doc Strings** to provide multi-line textual input for scenarios requiring more complex data like JSON payloads or lengthy input text.
4. Dynamically organize test data for negative scenarios, edge cases, and large datasets, ensuring completeness within your feature files.

---

## **Tasks**

### **Task 1: Create a Scenario Outline with an Examples Table**
1. Open or create a new feature file in your project under `src/test/resources/features`.
2. Write a **Scenario Outline** to test a workflow with multiple input and expected output combinations:
   - Dynamically pass different values (e.g., username, email, password).
   - Validate the system outcomes (e.g., successful registrations, error messages).
3. Use an `Examples` table to define at least 3-5 sets of input values with expected results.

**Example Acceptance Criteria**:
- Validate a registration form accepting valid inputs.
- Test invalid combinations for error handling (e.g., empty fields or invalid formats).

---

### **Task 2: Use DataTables for Structured Inputs**
1. Add a scenario in a feature file where multiple related inputs are required for a single step.
2. Use a **DataTable** to represent this structured input data within a step:
   - Define inputs spanning multiple columns (e.g., field names and values).
   - Validate that the system processes all provided data correctly (e.g., filling a form or validating a list of items).
3. Map the DataTable in the Step Definition to dynamically process each row of input data.

**Example Use Case**:
- Filling a user profile form where multiple fields (e.g., First Name, Last Name, Email) and their corresponding values need to be tested.

---

### **Task 3: Use Doc Strings for Multi-Line Data**
1. Define a scenario in a feature file that requires complex data or multi-line textual input.
2. Use a **Doc String** to represent the input directly in the Gherkin scenario:
   - Prepare input such as JSON payloads or a long text string.
   - Validate the system’s behavior when processing this data (e.g., sending JSON in an API payload or handling multi-line comments).
3. Ensure the Doc String format is readable and respects Gherkin syntax.

**Example Use Case**:
- Send a JSON payload for an API test where the input has nested fields.

---

### **Task 4: Dynamically Handle Negative Scenarios and Edge Cases**
1. Review the scenarios defined in your feature files to identify possible **edge cases** and **negative scenarios**.
2. Organize test cases with clear documentation of invalid inputs or edge cases directly within your feature files.
   - Use Scenario Outlines or DataTables for these cases where applicable.
3. Ensure that your feature files thoroughly validate system behavior for invalid, missing, or boundary inputs.

**Example Negative Scenarios**:
- Testing login functionality with an invalid email format or missing password.
- Testing API responses for empty payloads or incorrect request formatting.

---

## **Submission Guidelines**
1. Save all updated feature files in the `src/test/resources/features` directory.
2. Ensure that the tasks are implemented as follows:
   - **Task 1**: A feature file with a **Scenario Outline** using `Examples` tables to test multiple input combinations.
   - **Task 2**: A feature file with a **DataTable** testing structured inputs for a single step.
   - **Task 3**: A feature file using **Doc Strings** to handle multi-line or complex input data.
   - **Task 4**: Adequate coverage of **negative scenarios** and **edge cases** organized within feature files.
3. Push the new or updated feature files to the provided Git repository.

---

## **Evaluation Criteria**
Your submission will be evaluated based on:

1. **Scenario Outline Implementation**:
   - Are `Examples` tables correctly used to parameterize scenarios?
   - Are input combinations adequately covered?
2. **Use of DataTables**:
   - Are DataTables used effectively for structured step inputs?
   - Are all rows of data processed correctly in the tests?
3. **Use of Doc Strings**:
   - Are multi-line data or complex inputs represented clearly and accurately using Doc Strings?
   - Is the Step Definition capable of processing the Doc String data?
4. **Negative Scenarios and Edge Cases**:
   - Are edge cases and negative test scenarios dynamically handled and well-organized within feature files?
5. **Completeness**:
   - Have all tasks been implemented, and feature files committed to the repository?

---

## **By the End of This Task**
- You will master using **Scenario Outlines** with `Examples` tables for data-driven testing.
- You will effectively utilize **DataTables** for testing structured tabular data.
- You will gain proficiency in using **Doc Strings** for complex, multi-line inputs.
- You will understand how to dynamically handle edge cases and negative scenarios directly within feature files.

---