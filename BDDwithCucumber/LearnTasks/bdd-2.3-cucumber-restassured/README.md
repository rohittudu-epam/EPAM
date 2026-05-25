# **Cucumber RestAssured Integration**

This task focuses on integrating **RestAssured** with **Cucumber** to automate and validate RESTful API workflows. You will write feature files to define API behavior, implement API operations using RestAssured in Step Definitions, and validate outcomes such as status codes, headers, and JSON content.

---

## **Learning Objectives**

By completing this task, you will:
1. Integrate **RestAssured** with **Cucumber** for RESTful API automation.
2. Write **feature files** for testing common API operations such as GET, POST, PUT, and DELETE.
3. Implement **Step Definitions** to perform API operations and validate responses.
4. Automate real-world API workflows, including:
   - Authentication and login.
   - CRUD operations (Create, Retrieve, Update, Delete).
   - Error handling for scenarios such as unauthorized requests (`401`) and resource not found (`404`).

---

## **Tasks**

### **Task 1: Set Up RestAssured in a Cucumber Project**
1. Configure **RestAssured** in your Cucumber project:
   - Add dependencies for RestAssured and Cucumber in your project setup.
   - Validate the setup by testing a simple GET request against a public API.

---

### **Task 2: Write Feature Files for RESTful APIs**
1. Write feature files to describe RESTful API workflows using **Gherkin syntax**.
2. Define scenarios for:
   - **Login API**:
      - Validate successful login with correct credentials.
      - Test login failure with incorrect credentials and validate the error message.
   - **CRUD Operations**:
      - Test creating a resource (POST).
      - Test retrieving a resource (GET).
      - Test updating a resource (PUT).
      - Test deleting a resource (DELETE).
   - **Error Scenarios**:
      - Access non-existent resources and validate `404 Not Found`.
      - Validate unauthorized access with `401 Unauthorized`.

---

### **Task 3: Implement Step Definitions for RESTful Operations**
1. Implement Step Definitions to map Gherkin steps to **RestAssured** operations:
   - Use RestAssured syntax (`given()`, `when()`, `then()`) to send requests and validate responses.
   - Perform assertions for:
      - **Status Codes**: Ensure the correct HTTP status codes are returned (e.g., 200 OK, 404 Not Found).
      - **Headers**: Validate response headers like `Content-Type`.
      - **JSON Body Content**: Use JsonPath to verify fields and values.
2. Ensure Step Definitions are reusable and can handle dynamic data where required.

---

### **Task 4: Automate Real-World API Workflows**
1. Automate workflows such as:
   - **Authentication**:
      - Perform login using POST.
      - Validate authentication success with expected tokens or status codes.
   - **CRUD Operations**:
      - Create a resource using POST.
      - Retrieve the resource and validate its fields using GET.
      - Update the resource with new data using PUT.
      - Delete the resource and confirm deletion with GET or DELETE.
2. Chain requests by using data from one API response (e.g., resource IDs) in subsequent requests.

---

### **Task 5: Validate Error Scenarios**
1. Automate negative test cases:
   - Validate `401 Unauthorized` responses by sending requests without authorization or tokens.
   - Simulate resource retrieval or update for non-existing resources and validate a `404 Not Found` response.
2. Include appropriate assertions to validate both error codes and error messages.

---

## **Submission Guidelines**

1. Prepare and submit your project via your GitHub repository link.
2. Ensure that your repository includes:
   - Clear, well-written **feature files** that describe the API workflows.
   - Step Definition classes that implement and validate API operations using RestAssured.
   - A `README.md` file with instructions on setting up and running the project.

---

## **Execution Instructions**

1. Configure API base URLs and authentication credentials dynamically using either property files or a configuration manager.
2. Run the Cucumber test cases using your preferred runner (e.g., JUnit or TestNG).
3. After execution, review the Cucumber-generated HTML reports for validation of test results.

---

## **Evaluation Criteria**

Your submission will be evaluated based on:

1. **Feature File Quality**:
   - Are the Gherkin scenarios clear, concise, and aligned with RESTful API workflows?
   - Do the scenarios adequately cover all defined workflows, including error handling?

2. **Step Definition Implementation**:
   - Are the Gherkin steps appropriately mapped to RestAssured methods?
   - Do Step Definitions validate responses effectively (status codes, headers, JSON content)?

3. **Error Handling**:
   - Are scenarios for common errors like `404` and `401` implemented effectively with proper assertions and validations?

4. **Workflow Automation**:
   - Do the automated tests accurately simulate real-world scenarios such as authentication and CRUD workflows?
   - Are they logically organized and reusable?

5. **Project Structure and Clarity**:
   - Is the repository structured clearly with organized feature files and Step Definitions?
   - Does the `README.md` provide complete, easy-to-follow instructions on setting up and running the project?

---

## **By the End of This Task**

- You will understand how to integrate **RestAssured** with **Cucumber** to automate and validate RESTful API workflows.
- You will gain experience in writing feature files and mapping them to Step Definitions for actions like GET, POST, PUT, and DELETE.
- You will learn to validate API responses, including status codes, headers, and JSON content.
- You will develop skills to automate real-world workflows, including API authentication and error case testing.

---