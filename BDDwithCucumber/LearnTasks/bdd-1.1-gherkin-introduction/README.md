# **Hands-On Task: Introduction to Gherkin and Feature Files**

This hands-on task focuses on creating **Gherkin-based feature files** to describe business behavior in a real-life application workflow (e.g., **User Login and Registration**). Trainees will practice writing scenarios using `Given`, `When`, `Then`, and related keywords while learning to structure feature files efficiently using **Backgrounds** and **Scenario Outlines** for data-driven tests.

---

## **Learning Objectives**

By completing this task, you will:
1. Understand the structure of a **Feature File** and how it describes functionality using **Feature**, **Scenario**, and **Steps**.
2. Write concise and meaningful Gherkin scenarios that focus on high-level business behavior for login and registration functionality.
3. Eliminate repetitive steps using **Backgrounds** for shared setup.
4. Create parameterized tests using **Scenario Outlines** with `Examples` tables for testing multiple data inputs (e.g., usernames, passwords, emails).

---

## **Scenario: User Login and Registration**

For this task, you will automate the "User Login and Registration" workflow of an application. Focus on:
1. **Login Workflow**:
   - Validate successful login with valid credentials.
   - Validate error handling for unsuccessful login attempts (e.g., invalid username/password).
2. **Registration Workflow**:
   - Validate successful registration with valid input data.
   - Test various failure scenarios, such as missing fields (username, email, password) or invalid inputs (e.g., non-compliant password formats).

---

## **Tasks**

### **Task 1: Create a Feature File for User Login**
1. Create a feature file named `LoginFeature.feature` in `src/test/resources/features`.
2. Define the **Feature**: "User Login" to describe the application's login functionality.
3. Add at least one **Scenario**:
   - Focus on the basic behavior of logging in with valid credentials.
4. Write Gherkin steps using `Given`, `When`, `Then` to structure the scenario:
   - Ensure clarity and focus on high-level business behavior.
5. Add another **Scenario** for an invalid login attempt:
   - Validate the system's response when invalid credentials are entered.

---

### **Task 2: Write Meaningful Gherkin Scenarios for Registration**
1. Create a new feature file named `RegistrationFeature.feature` in the same directory.
2. Define the **Feature**: "User Registration" for creating new user accounts.
3. Add multiple scenarios to handle:
   - Successful registration with valid inputs.
   - Unsuccessful registration due to missing/invalid fields (e.g., empty username or invalid email).
4. Ensure Gherkin steps avoid implementation details:
   - Focus on `Given`, `When`, `Then` steps that describe actions (e.g., "I fill the registration form") and outcomes (e.g., "I should see a success message").

---

### **Task 3: Use a Background for Shared Setup in LoginFeature**
1. Refactor the login scenarios in your `LoginFeature.feature` file:
   - Identify repetitive setup steps (e.g., "Navigate to login page").
2. Introduce a **Background** to eliminate duplication and share these common setup steps across multiple scenarios:
   - Ensure that all login scenarios leverage this shared Background setup.

---

### **Task 4: Parameterize Registration Tests with Scenario Outlines**
1. Refactor your `RegistrationFeature.feature` file to handle multiple sets of registration inputs.
2. Use a **Scenario Outline** to define:
   - Steps that dynamically accept registration inputs (e.g., username, email, password).
   - Outcomes based on different input combinations (success or error messages).
3. Use an `Examples` table to provide various input values for testing scenarios like:
   - Valid user data.
   - Invalid email formats.
   - Missing or empty fields.

---

## **Submission Guidelines**
1. Save all your feature files under `src/test/resources/features`.
2. Ensure implementation of the following:
   - `LoginFeature.feature`:
      - Includes valid and invalid login scenarios.
      - Uses a Background for shared setup steps.
   - `RegistrationFeature.feature`:
      - Includes scenarios for successful and unsuccessful registrations.
      - Uses a Scenario Outline with an `Examples` table for data-driven testing.
3. Push your feature files to the provided Git repository under version control.

---

## **Evaluation Criteria**
Your submission will be evaluated based on:

1. **Feature File Structure**:
   - Are **Feature**, **Scenario**, and **Steps** correctly organized using Gherkin syntax?
2. **Readability**:
   - Are the Gherkin steps concise, meaningful, and focused on business behavior rather than implementation details?
3. **Background Usage**:
   - Are repetitive steps properly refactored into a Background in the `LoginFeature.feature` file?
4. **Scenario Outlines**:
   - Are parameterized tests implemented correctly using `Examples` tables in the `RegistrationFeature.feature` file?
5. **Completeness**:
   - Have both login and registration workflows been thoroughly covered with positive and negative test cases?

---

## **By the End of This Task**
- You will understand how to write real-world Gherkin scenarios for **User Login and Registration** workflows using `Given`, `When`, `Then`, and related keywords.
- You will learn to eliminate redundancy in feature files using **Backgrounds**.
- You will gain hands-on experience creating **data-driven tests** with **Scenario Outlines** and `Examples`.

---