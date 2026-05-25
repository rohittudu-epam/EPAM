# **Hands-On Task: Setting Up a Java-Cucumber Project**

This hands-on task focuses on creating and configuring a Maven-based project integrated with **Cucumber** and **Java**. You will set up the environment, configure dependencies, write a basic test case, and use a Cucumber runner to validate your setup.

---

## **Learning Objectives**

By completing this task, you will:
1. Create a Maven-based project integrated with Cucumber and Java.
2. Configure required **Maven dependencies** for running Cucumber test cases.
3. Set up your development environment in **IntelliJ IDEA** or **Eclipse**.
4. Write and run your first Cucumber test using an annotated runner class with `@CucumberOptions`.

---

## **Tasks**

### **Task 1: Create a Maven Project (Note: This structure is already there in the forked repo; You need to do this for your practice only)** 
1. Create a new **Maven project** in your IDE (e.g., IntelliJ IDEA or Eclipse).
2. Provide basic project information during the setup:
   - **Group ID**: Define a unique identifier for your organization or project (e.g., `com.example`).
   - **Artifact ID**: Define the project name (e.g., `cucumber-demo`).
   - **Version**: Use a default project version (e.g., `1.0-SNAPSHOT`).
3. Verify the project structure:
   - `src/main/java`: For main code (optional for this task).
   - `src/test/java`: For Step Definitions and Runner classes.
   - `src/test/resources`: For storing Cucumber feature files.
   

---

### **Task 2: Configure Maven Dependencies**
1. Open the **`pom.xml`** file in your project and add the following dependencies:
   - **Cucumber-Java**: To write and execute Gherkin-based tests.
   - **Cucumber-JUnit**: To run tests with JUnit.
   - Optionally, add **Cucumber-Picocontainer** if dependency injection is required.
2. Save the `pom.xml` file and allow Maven to download and resolve the dependencies.
3. Verify that the dependencies are added successfully under the **External Libraries** section in your IDE.

---

### **Task 3: Write a Feature File**
1. Create a `features` directory inside `src/test/resources`.
2. Write a `.feature` file inside the `features` directory:
   - Define a **Feature** to describe the functionality being tested.
   - Add a **Scenario** to capture one specific piece of behavior.
   - Use `Given`, `When`, and `Then` steps to define the desired workflow.

---

### **Task 4: Implement Step Definitions**
1. Create a package named, for example, `steps` inside `src/test/java`.
2. Write a **Step Definition class** inside this package:
   - Match the Gherkin steps in the feature file to Java methods using annotations like `@Given`, `@When`, and `@Then`.
   - Ensure that the methods perform simple actions such as printing messages to the console.

---

### **Task 5: Create a Cucumber Runner Class**
1. Create a package called `runners` inside `src/test/java`.
2. Write a **Runner class** annotated with `@RunWith(Cucumber.class)` and configure the following in `@CucumberOptions`:
   - **features**: Path to the `features` directory.
   - **glue**: Path to your Step Definition package (e.g., `steps`).
   - **plugin**: Optionally, specify plugins like `pretty` for readable output.
3. Ensure that the Runner class executes all scenarios defined in your feature file(s).

---

### **Task 6: Run Your First Test**
1. Execute the Runner class from your IDE:
   - Right-click the Runner class and select "Run."
   - Alternatively, use the JUnit test runner to execute the class.
2. Verify that tests execute successfully:
   - Ensure that step bindings in the Step Definition class are invoked for each scenario.
   - Observe readable outputs produced by the configured plugins.

---

## **Submission Guidelines**
1. Submit the following components to the Git repository:
   - Maven project files:
     - **pom.xml** with properly configured dependencies.
     - Project structure (`src/test/java` and `src/test/resources/features`).
   - A **feature file** describing one behavior.
   - Its corresponding **Step Definition class** implemented in Java.
   - The **Cucumber Runner class** annotated with `@CucumberOptions`.
2. Ensure the project is functional, and all steps execute successfully by running the Runner class.

---

## **Evaluation Criteria**
Your submission will be evaluated based on:

1. **Correctness**:
   - Does the Maven project compile and run successfully without errors?
   - Are Cucumber and its dependencies configured correctly?
2. **Implementation**:
   - Is the feature file written using valid Gherkin syntax, with clear and meaningful steps?
   - Are Step Definition methods mapped correctly to Gherkin steps?
3. **Project Setup**:
   - Is the project structure organized with proper separation of features, Step Definitions, and Runner classes?
4. **Execution**:
   - Does the Runner class execute the feature file's scenarios successfully and produce readable plugin output?

---

## **By the End of This Task**
- You will have a fully functioning **Cucumber project** integrated with Java and managed using Maven.
- You will understand how to write a feature file, bind it to Java Step Definitions, and execute scenarios using a Runner class.
- You will gain confidence in setting up and validating your first Cucumber test automation project.