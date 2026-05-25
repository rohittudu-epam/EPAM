# **Hands-On Task: Using Hooks and Shared State in Cucumber**

This hands-on task focuses on configuring **Hooks** in Cucumber to manage setup and teardown logic and sharing state across steps in your scenarios. You will practice implementing `@Before` and `@After` Hooks, use tags to execute specific Hooks, and share data between steps using dependency injection frameworks or context management.

---

## **Learning Objectives**

By the end of this task, you will:
1. Understand the role of **Cucumber Hooks** in managing pre-test and post-test logic.
2. Implement `@Before` and `@After` Hooks for:
   - Initializing browsers or test environments.
   - Cleaning up temporary resources like files or database connections.
3. Configure **tagged Hooks** to execute logic conditionally for specific types of tests (e.g., only for tests tagged `@UI`).
4. Share and manage state between steps:
   - Use dependency injection frameworks like **Picocontainer** or **Spring** to maintain shared state.
   - Implement `ScenarioContext` or `TestContext` for cross-step data sharing.

---

## **Tasks**

### **Task 1: Set Up and Implement Hooks**
1. Create a class (e.g., `Hooks.java`) to define your setup and teardown logic.
2. Use `@Before` Hooks for:
   - Initializing the browser or environment before every scenario.
   - Logging the start of a scenario or setting up prerequisites like loading test data.
3. Use `@After` Hooks for:
   - Cleaning up test execution environments (e.g., closing the browser).
   - Removing temporary test data or rolling back database transactions.
   - Logging the completion or status of scenarios.

---

### **Task 2: Configure Tagged Hooks**
1. Modify or add scenarios in your feature files and tag them with meaningful names (e.g., `@UI`, `@API`, `@Regression`).
2. Update your Hooks class to implement tagged Hooks:
   - Ensure that logic defined in specific Hooks only executes for scenarios tagged with the corresponding annotation.
   - For example, implement setup logic for browser initialization exclusively for scenarios tagged with `@UI`.

#### Steps:
- Define separate Hooks for `@UI` and non-UI tests (e.g., API or database validations).
- Validate that configuration is appropriately triggered based on the tags in your scenarios.

---

### **Task 3: Share State Between Steps**
1. Configure a method to share data between steps in your Step Definitions:
   - Use **Picocontainer** or **Spring Framework** to inject shared state (e.g., for user credentials, session IDs).
2. Alternatively, design a `ScenarioContext` or `TestContext` class to manually store and retrieve shared data.
3. Ensure proper organization of state:
   - Encapsulate reusable pieces of data that multiple steps need (e.g., response objects from API calls or data generated during execution).
   - Use context management to avoid repetitive communication across steps.

#### Example Use Cases:
- Store and retrieve user data entered in one step to validate it in subsequent steps.
- Maintain dynamic data like API tokens or web pages across multiple steps or scenarios.

---

### **Task 4: Validate Hook and State Usage**
1. Add scenarios in your feature file to test the integration of setup/teardown logic and shared state:
   - Confirm that Hooks execute at the right points (before/after scenarios or for specific tags).
   - Test that shared state is passed correctly between steps and scenarios without duplication or errors.
2. Debug unexpected behavior where Hooks or shared state implementations fail:
   - Ensure test data remains reliable and consistent for all steps in a scenario or across multiple scenarios.
   - Validate that tagged Hooks do not affect unrelated tests.

---

## **Submission Guidelines**
1. Submit the following components to the Git repository:
   - A `Hooks` class implementing `@Before`, `@After`, and tagged Hooks.
   - Feature files with scenarios tagged appropriately to test Hooks configuration.
   - Implementation of shared state using dependency injection or context classes to store and retrieve data between steps.
   - A clear project structure organized for modularity and scalability.

---

## **Evaluation Criteria**
Your submission will be evaluated based on:

1. **Hook Implementation**:
   - Are `@Before` and `@After` Hooks correctly implemented for setup and teardown logic?
   - Are tagged Hooks configured and executed properly for relevant scenarios?
2. **Shared State**:
   - Is shared state effectively managed and passed between steps using dependency injection or context classes?
   - Is state management modular and reusable across different scenarios?
3. **Feature File Integration**:
   - Are feature files well-organized, with appropriate tags to trigger specific Hooks?
   - Are scenarios correctly testing the use of Hooks and shared state?
4. **Completeness**:
   - Are all feature files, Step Definitions, and the Hooks class included in the submission?
   - Does the project demonstrate a full setup with proper usage of Hooks and shared state?

---

## **By the End of This Task**
- You will understand the role of **Hooks** in Cucumber and implement `@Before` and `@After` Hooks for setup and teardown logic.
- You will configure **tagged Hooks** to execute logic only for specific test cases.
- You will effectively share state between steps using dependency injection or context classes.
- You will be able to manage test data and environments in a modular and scalable way within your Cucumber project.