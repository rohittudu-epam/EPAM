# **Hands-On Task: Validating API Responses**

This hands-on task focuses on effectively verifying API responses using **RestAssured**. You will validate status codes, inspect response content, query JSON responses, perform schema validation, and measure API performance.

---

## **Objective**

By the end of this task, you will:
1. Validate HTTP status codes to ensure correct API responses.
2. Verify response body content through field and value validations.
3. Extract and query values from JSON responses using **JsonPath**.
4. Perform **schema validation** to ensure responses conform to predefined JSON schemas.
5. Measure **API performance** by validating response times against benchmarks.

---

## **Prerequisites**

1. A working REST API project set up with **RestAssured**, **Maven**, and **TestNG**.
2. Basic knowledge of API responses, HTTP status codes, and JSON payloads.
3. Access to public APIs for testing purposes:
    - **JSONPlaceholder API**: [https://jsonplaceholder.typicode.com/](https://jsonplaceholder.typicode.com/).

---

## **Hands-On Task: Validating API Responses**

Follow the steps below to validate various aspects of API responses.

---

### **Step 1: Validate HTTP Status Codes**
1. Send API requests to endpoints using RestAssured (e.g., `GET /posts/1` from JSONPlaceholder API).
2. Validate the **status code** returned by the server:
    - Ensure correct codes for successful requests (e.g., `200 OK` for GET, `201 Created` for POST).
    - Check for error codes during invalid requests (e.g., `404 Not Found`, `400 Bad Request`).
3. Add assertions to verify that the status matches the expected value.

---

### **Step 2: Validate Response Headers**
1. Inspect and validate headers in the API response:
    - Common headers to verify include `Content-Type`, `Cache-Control`, and `Content-Length`.
2. Ensure headers match expected values (e.g., `Content-Type: application/json; charset=utf-8` for JSON responses).
3. Use assertions to confirm the presence and correctness of header values.

---

### **Step 3: Validate Response Body Content**
1. Verify specific fields and values in the response body:
    - Extract and validate details like `id`, `title`, and `body` in JSON responses.
    - Ensure correct field values for desired endpoints (e.g., `/posts/1` should return a post object with `id=1`).
2. Add assertions to match fields and values with the expected output (e.g., matching strings, numbers, or arrays).

---

### **Step 4: Extract Values from JSON Responses Using JsonPath**
1. Use **JsonPath** to query and extract specific values dynamically from JSON responses.
    - Example: Extract the `title` field from a post object.
2. Validate nested fields, arrays, and collections using JsonPath expressions.
3. Use JsonPath to iterate through arrays and validate data points (e.g., validating all users returned in a JSON array).

---

### **Step 5: Perform Schema Validation**
1. Define a JSON schema file that specifies the expected structure of the API response.
2. Validate API responses against the predefined schema:
    - Confirm field presence, data types, and value constraints.
    - Identify responses deviating from expected schema definitions.
3. Use RestAssured’s schema validation feature to automate checks for schema compliance.

---

### **Step 6: Measure API Performance**
1. Validate response times against performance benchmarks:
    - Measure how quickly the server responds to API requests.
    - Assert that response times remain within acceptable thresholds (e.g., under 200ms for non-complex APIs).
2. Log response times for monitoring and troubleshooting slow endpoints.

---

### **Step 7: Combine Validations**
1. Write tests combining multiple validations:
    - Validate status codes, headers, body content, JSON paths, schema compliance, and response performance in a single test.
2. Automate responses for different endpoints (e.g., `/posts`, `/users`) to simulate realistic workflows.

---

## **Examples of API Scenarios to Automate**
The following endpoints from **JSONPlaceholder API** can be used for testing:

### **Example 1: Status Code and Body Validation**
- **API**: `GET /posts/1`
- **Validations**:
    - Status Code: Assert the response is `200 OK`.
    - Body Fields: Extract `id`, `title`, `body`, and validate values.

### **Example 2: JsonPath Validation for an Array**
- **API**: `GET /posts?userId=1`
- **Validations**:
    - Use JsonPath to extract all posts for `userId=1`.
    - Ensure the length of the response array matches the expected count.

### **Example 3: Schema Validation**
- **API**: `GET /posts/1`
- **Validations**:
    - Validate the response schema includes fields like `id`, `title`, `body`, `userId`.
    - Confirm the types match expectations (e.g., `id` is an integer, `title` is a string).

---

## **Deliverables**
1. Push the completed tests to the provided Git repository:
    - Include tests validating **status codes**, **response body content**, and **headers**.
    - Include JsonPath-based tests that dynamically extract and validate response values.
    - Include schema validation tests with schema files stored in an organized folder (e.g., `resources/schema`).
    - Include performance benchmarks for response time validation.
2. Provide a **README.md** file summarizing:
    - Key validations implemented.
    - Steps for running schema validation and JsonPath-based tests.
    - Instructions for running performance validation tests.

---

## **Evaluation Criteria**
Your submission will be evaluated based on:
1. **Correctness**:
    - Are HTTP status codes, headers, body content, and response times validated effectively?
2. **Use of JsonPath**:
    - Are JsonPath queries correctly implemented for dynamic field extraction and validation?
3. **Schema Validation**:
    - Is schema validation implemented, and does it conform to expected response structures?
4. **Performance Validation**:
    - Are response time benchmarks correctly measured and asserted?
5. **Modularity**:
    - Are test cases structured cleanly for extensibility and maintainability?
6. **Git Best Practices**:
    - Are commits meaningful, and is the repository well-organized?

---

## **Bonus Task (Optional)**
- Automate tests for different content types, including XML responses.
- Implement tests that dynamically fetch schema files from external sources (e.g., schema registry or URLs).
- Create performance benchmarks for APIs under load testing scenarios using response time validation.

---

## **By the End of This Task**
- You will be proficient in validating API responses comprehensively using **RestAssured**.
- You will learn how to query JSON response fields dynamically and validate complex data structures.
- You will be able to ensure compliance with predefined schemas and measure API performance effectively.
- You will gain hands-on practice writing modular tests combining multiple validations.

---