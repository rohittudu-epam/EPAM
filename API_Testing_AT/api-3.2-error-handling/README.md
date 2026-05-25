# **Hands-On Task: Error Handling and Debugging**

This hands-on task focuses on handling and debugging API errors effectively. Using **WireMock**, we have preconfigured mappings to simulate various error scenarios, especially **400-series status codes**. Trainees will work on validating these error responses and debugging their tests using RestAssured’s logging capabilities.

---

## **Objective**

By the end of this task, you will:
1. Handle common **400-series errors** in API testing, such as **401 Unauthorized**, **404 Not Found**, **400 Bad Request**, etc.
2. Validate error responses using assertions for status codes, headers, and body content.
3. Debug API tests effectively using RestAssured’s logging features (`log().all()`) and troubleshoot failed tests.
4. Implement best practices for error handling in API testing.

---

## **Provided Setup**

1. **WireMock**:
    - A WireMock server is preconfigured to simulate API responses based on provided mappings.
    - The WireMock mappings file defines all error scenarios related to **400-series status codes**.

2. **BaseTest Class**:
    - The `BaseTest` class initializes and starts the WireMock server.
    - WireMock mappings are automatically loaded during test execution from the `mappings` directory.
    - The **`baseURI` is already set to `http://host:port`**, where `host` and `port` correspond to the WireMock server's configuration.

---

## **Task Scope**

Your task is to **write test cases** to validate the following API error responses (as defined in the WireMock mappings). Perform debugging using RestAssured’s logging features and apply assertions to verify the correctness of these errors.

---

## **API Documentation for 400-Series Status Codes**

### **GET /api/students**

1. **Scenario: Unauthorized Access**
    - **Request**:
        - `GET /api/students`
        - Header: `Authorization: Bearer my-incorrect-bearer-token007`
    - **Response**:
        - **Status Code**: 401 Unauthorized
        - **Headers**:
            - `Content-Type: application/json`
        - **Body**:
          ```json
          {
            "error": "Unauthorized",
            "message": "You must be logged in to access this resource."
          }
          ```

2. **Scenario: Missing Authorization Header**
    - **Request**:
        - `GET /api/students`
        - No `Authorization` header provided.
    - **Response**:
        - **Status Code**: 403 Forbidden
        - **Headers**:
            - `Content-Type: application/json`
        - **Body**:
          ```json
          {
            "error": "Forbidden",
            "message": "Authorization header is missing. Please provide valid credentials."
          }
          ```

---

### **POST /api/students**

1. **Scenario: Bad Request (Missing Required Fields)**
    - **Request**:
        - `POST /api/students`
        - Body:
          ```json
          {
            "firstName": "",
            "lastName": "",
            "email": ""
          }
          ```
        - Missing required fields (`firstName`, `lastName`, `email`).
    - **Response**:
        - **Status Code**: 400 Bad Request
        - **Headers**:
            - `Content-Type: application/json`
        - **Body**:
          ```json
          {
            "error": "Bad Request",
            "message": "Missing required fields: firstName, lastName, or email."
          }
          ```

---

### **GET /api/students/non-exist-id**

1. **Scenario: Resource Not Found**
    - **Request**:
        - `GET /api/students/non-exist-id`
        - Header: `Authorization: Bearer my-unique-bearer-token007`
    - **Response**:
        - **Status Code**: 404 Not Found
        - **Headers**:
            - `Content-Type: application/json`
        - **Body**:
          ```json
          {
            "error": "Not Found",
            "message": "Student with the requested ID does not exist."
          }
          ```

---

### **GET /api/students**

1. **Scenario: Invalid Query Parameter**
    - **Request**:
        - `GET /api/students?sortField=non-existent-field`
    - **Response**:
        - **Status Code**: 422 Unprocessable Entity
        - **Headers**:
            - `Content-Type: application/json`
        - **Body**:
          ```json
          {
            "error": "Unprocessable Entity",
            "message": "The requested field for sorting does not exist."
          }
          ```

---

## **Steps to Complete the Task**

### **Step 1: Write Test Cases**

1. **Send Requests**:
    - Use RestAssured to send API requests to the provided endpoints.
    - **You do not need to configure `baseURI` manually**; it is already set to `http://host:port` in the `BaseTest` class.
    - Simply use relative URLs for the requests (e.g., `/api/students`, `/api/students/non-exist-id`).

2. **Validate Responses**:
    - Assert status codes (e.g., `401`, `403`, `400`, `404`, `422`).
    - Validate response headers (e.g., `Content-Type`).
    - Extract and assert error messages from the JSON response body.

---

### **Step 2: Use RestAssured Logging for Debugging**

1. Enable logging for detailed debugging:
    - Use `.log().all()` to capture the complete lifecycle of the request and response.
    - Log specific parts, such as headers or body, for focused debugging.

2. Debug failed tests:
    - Reproduce test failures and analyze logged requests/responses to pinpoint issues.
    - Identify missing headers, incorrect payloads, or endpoint mismatches.

---

### **Step 3: Follow Best Practices for Error Handling**

1. **Modular Test Design**:
    - Write separate test methods for each error scenario (e.g., `testUnauthorizedAccess`, `testResourceNotFound`).

2. **Clear Assertions**:
    - Include meaningful assertion messages for better readability and traceability during failures.

3. **Document Results**:
    - Record observations and findings during debugging (e.g., response-specific issues or unexpected errors).

---

## **Deliverables**

1. Push the following to the Git repository:
    - Test cases validating all **400-series errors** based on the API documentation provided.
    - Use logging features to capture detailed request/response details for debugging.
    - Modular test methods and meaningful assertions for each error scenario.

2. Include a **README.md** in the repository summarizing:
    - Steps to run the tests.
    - Observations during execution (e.g., debugging findings or unexpected issues).

---

## **Evaluation Criteria**

1. **Test Implementation**:
    - Are the tests validating error scenarios comprehensively (status codes, headers, body)?

2. **Debugging**:
    - Are RestAssured’s logging features utilized effectively for debugging?

3. **Modularity**:
    - Are test cases cleanly separated for each scenario?

4. **Git Practices**:
    - Is the repository clean, and are commits meaningful?

---

## **By the End of This Task**

- You will learn how to validate API error responses (400-series).
- You will develop debugging skills using detailed logs captured by RestAssured.
- You will understand and apply best practices for error handling in automated API testing.

---