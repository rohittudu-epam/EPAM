# **Hands-On Task: Step Definitions**

This hands-on task focuses on creating reusable **Step Definitions** for Cucumber scenarios. You will learn how to map Gherkin steps to Java methods, parameterize steps for dynamic values, reuse Step Definitions across scenarios, and debug failed steps effectively.

---

## **Learning Objectives**

By the end of this task, you will:
1. Understand how **Cucumber maps Gherkin steps** (`Given`, `When`, `Then`) to Java methods using decorators like `@Given`, `@When`, and `@Then`.
2. Learn to use **parameterization** to handle dynamic values in Gherkin steps using regex patterns.
3. Write reusable **Step Definitions** to avoid duplication and promote modularity.
4. Debug failed steps using meaningful error messages and built-in IDE debugging tools.

---

## **Tasks**

### **Task 1: Create a Step Definition File**
1. Open your existing Cucumber project or create a new one.
2. Inside `src/test/java`, create a package named `steps` (e.g., `com.epam.campus.bdd.steps`).
3. Add a Step Definition class to the package (e.g., `LoginSteps.java` or `RegistrationSteps.java`).
4. Write methods in the class to map feature file steps using decorators like `@Given`, `@When`, and `@Then`.

---

### **Task 2: Map Gherkin Steps to Java Methods**
1. Open your feature file containing one or more scenarios.
2. Implement corresponding Step Definitions in your class:
   - Annotate methods with `@Given`, `@When`, and `@Then` to match the Gherkin steps.
   - Write simple Java methods that execute the actions described in the Gherkin steps.
3. Ensure each Gherkin step is correctly mapped to one unique Java method.
4. Verify that the feature file and Step Definitions are properly linked and functional.

---

### **Task 3: Parameterize Steps for Dynamic Values**
1. Modify Step Definitions to handle dynamic inputs using **regular expressions**.
2. Update Gherkin steps to include placeholders for values (e.g., username, password, email).
3. Extract these dynamic values from the steps and pass them as arguments to the corresponding Java methods.
4. Ensure parameterized steps handle different inputs for scenarios, enabling flexible testing.

---

### **Task 4: Write Reusable Step Definitions**
1. Identify common steps shared across multiple scenarios (e.g., navigation steps, login/logout actions).
2. Implement these steps in generalized methods within your Step Definition class to avoid duplication.
3. Link multiple scenarios in your feature files to these reusable Step Definitions.
4. Organize the Step Definition class to promote modularity and scalability.

---

### **Task 5: Debug Gherkin Steps**
1. Handle errors or failures in Step Definitions effectively by adding meaningful assertions and error messages.
2. Use assertions to validate the expected outcomes for each Gherkin step.
3. Debug failed steps using IDE tools:
   - Set breakpoints in Step Definition methods to inspect runtime data.
   - Check the values passed from the feature file and verify if the actions performed meet expectations.
4. Ensure failed steps provide helpful outputs for identifying the root cause of the failure.

---

## **Submission Guidelines**
1. Submit the following components to the Git repository:
   - **Step Definition class(es)** implemented using `@Given`, `@When`, and `@Then`.
   - Feature files with scenarios mapped to the Step Definitions.
   - Successfully executed tests demonstrating parameterized and reusable actions.
2. Ensure your Step Definitions:
   - Match Gherkin steps correctly.
   - Handle dynamic values using regex patterns.
   - Are modular and reusable across multiple scenarios.

---

## **Evaluation Criteria**
Your task will be evaluated based on:

1. **Step Definition Mapping**:
   - Are the Gherkin steps correctly mapped to Java methods using decorators?
2. **Parameterization**:
   - Are dynamic inputs handled correctly using regex patterns?
3. **Reusability**:
   - Are Step Definitions modular and reused across scenarios rather than duplicated?
4. **Debugging**:
   - Are meaningful error messages and debugging techniques applied effectively for failed steps?
5. **Completeness**:
   - Have all feature files and Step Definitions been implemented and executed successfully?

---

## **By the End of This Task**
- You will understand how Cucumber maps Gherkin steps to Java methods using `@Given`, `@When`, and `@Then`.
- You will implement reusable and parameterized Step Definitions for dynamic testing.
- You will gain confidence in debugging failed steps using meaningful error messages and IDE tools.
- You will write Step Definitions that are modular, extensible, and work across multiple scenarios.

---