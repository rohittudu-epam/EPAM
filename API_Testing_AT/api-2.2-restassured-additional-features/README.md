# **Hands-On Task: Additional RestAssured Features**

This hands-on task covers advanced features in RestAssured that are critical for modern API testing. You will learn different authentication mechanisms, manage headers/cookies, and implement data-driven API testing using external data sources.

---

## **Objective**

By the end of this task, you will:
1. Understand and implement various authentication mechanisms in API requests using RestAssured.
2. Learn how to handle and validate **headers** and **cookies** in API requests/responses.
3. Perform **data-driven testing** by parameterizing tests using data from external files (JSON, Excel, or CSV).

---

## **Prerequisites**
1. A REST API automation project set up using **RestAssured** with **Maven** and **TestNG**.
2. Basic understanding of REST APIs and HTTP concepts (authentication, headers, cookies).
3. Tools/software installed:
   - **Java Development Kit (JDK)** (Java 11 or later recommended).
   - IDE like **IntelliJ IDEA** or **Eclipse**.
   - For data-driven tests:
      - External JSON/Excel/CSV file prepared with input data.
      - Apache POI library (for Excel) added to your Maven project, if testing with Excel files.

---

## **Hands-On Task: Working with Additional RestAssured Features**

Follow these steps to complete the task.

---

### **Step 1: Implement Authentication Mechanisms**
1. **Basic Authentication**:
   - Set up **Basic Authentication** for endpoints requiring username and password.
   - Example: Use APIs requiring a basic encoded authorization header (e.g., `Authorization: Basic Base64(username:password)`).
   - Write tests to validate endpoints access with correct and incorrect authentication credentials.

2. **OAuth 1.0/2.0**:
   - Configure OAuth authentication (e.g., via `access_token`) to access secured APIs.
   - Use endpoints requiring an OAuth 2.0 Bearer token.
   - Write tests to validate:
      - Valid tokens grant access to APIs.
      - Expired or invalid tokens result in proper error responses (e.g., `401 Unauthorized`).

3. **Bearer Tokens**:
   - Generate and use **Bearer Tokens** for secured endpoints.
   - Add the Bearer token dynamically as a header in your API requests (`Authorization: Bearer <token>`).
   - Validate token validity or expirations using assertions in response handling.

---

### **Step 2: Add and Validate Headers**
1. Add **custom headers** while sending API requests:
   - Example Headers:
      - Authentication tokens (`Authorization`).
      - Content-Type (`application/json` or `application/xml`).
      - Unique identifiers for tracking requests (`Correlation-Id`).
2. Validate headers received in the API response:
   - Ensure specific headers exist (e.g., `Content-Type`, `Cache-Control`, etc.).
   - Validate the header values match expected outputs.
   - Extract specific header values for further processing or assertions.

---

### **Step 3: Add and Validate Cookies**
1. Send cookies with your API requests (if required by the API):
   - Example Cookies:
      - Session cookies (`JSESSIONID`, `PHPSESSID`).
      - Custom tracking cookies.
   - Use RestAssured’s `.cookie()` method to include cookies in your API requests.
2. Validate cookies received in the API responses:
   - Ensure specific cookies exist.
   - Extract cookie values for further validation or data persistence.

---

### **Step 4: Perform Data-Driven API Testing**
1. Use external data sources to parameterize your API tests:
   - **JSON**: Prepare a JSON file with multiple sets of test data.
   - **Excel**: Prepare an Excel sheet with test data values (requires Apache POI or similar library).
   - **CSV**: Prepare a CSV file containing input parameters for tests.
2. Implement tests to:
   - Read test data dynamically from external files.
   - Iterate through data sets to execute tests for multiple test cases, such as endpoints or parameter values.
   - Validate responses for each set of input data:
      - Successful response for valid data.
      - Proper error handling for invalid data.

3. Key Libraries to Use:
   - **Jackson/Gson** for reading JSON files.
   - **Apache POI** to read Excel files.
   - Java’s built-in libraries to read CSV data.

---

### **Step 5: Combine Features in Modular Tests**
1. Write end-to-end tests combining authentication, headers, cookies, and data-driven techniques:
   - Simulate real-world scenarios such as:
      - Login using Basic Authentication, extracting cookies, and passing them to subsequent requests.
      - Validating secured endpoints with Bearer Tokens while passing custom headers.
      - Running test cases for multiple user scenarios using parameterized data from JSON/CSV/Excel.

---

## **Examples of API Scenarios to Automate**
Use any combination of public APIs for the following authentication and data-driven scenarios:

### **Authentication Example**:
**API**: GitHub REST API (Personal Access Token for Authentication)
- Endpoint: `GET https://api.github.com/user/repos`
- Header:
   - `Authorization: token <your_personal_access_token>`
- Objective:
   - Validate access with proper tokens.
   - Verify error handling for expired or invalid tokens.

### **Headers Example**:
**API**: JSONPlaceholder API
- Endpoint: `GET https://jsonplaceholder.typicode.com/posts/1`
- Headers:
   - `Content-Type: application/json`
- Objective:
   - Add custom headers and validate response headers.

### **Data-Driven Example**:
**API**: JSONPlaceholder API
- Endpoint: `GET https://jsonplaceholder.typicode.com/posts/{id}`
- Parameterize multiple `id` values using a JSON/CSV file (e.g., `1`, `2`, `3`).

---

## **Deliverables**
1. Push the completed tests to the provided Git repository:
   - Include authentication tests for **Basic Authentication**, **OAuth 2.0**, or **Bearer Tokens**.
   - Include tests validating **headers** and **cookies**.
   - Include **data-driven tests** leveraging inputs from external files (JSON, Excel, or CSV).
   - Ensure modular tests that combine features are implemented.
2. Provide a **README.md** file summarizing:
   - Steps to run authentication, headers/cookies validation, and data-driven tests.
   - Instructions for adding external data sources for testing.

---

## **Evaluation Criteria**
Your submission will be evaluated based on:
1. **Authentication Implementation**:
   - Are authentication mechanisms correctly implemented (Basic, OAuth, Bearer Tokens)?
2. **Headers and Cookies**:
   - Are headers and cookies correctly managed and validated with assertions?
3. **Data-Driven Testing**:
   - Does the application handle external data sources and execute tests dynamically?
4. **Modularity**:
   - Are tests modular, reusable, and well-organized?
5. **Git Best Practices**:
   - Are commits meaningful, and is the repository well-structured?

---

## **Bonus Task (Optional)**
- Implement token handling where:
   - The token is fetched dynamically from a login API and automatically applied to subsequent requests.
- Write tests to validate API rate-limiting responses (e.g., `429 Too Many Requests`).
- Explore advanced cookie operations, such as extracting session cookies and reusing them across requests.

---

## **By the End of This Task**
- You will be proficient in implementing various authentication mechanisms in API tests.
- You will know how to handle headers and cookies effectively in API requests/responses.
- You will learn to implement data-driven tests using external files, making your tests scalable for multiple scenarios.
- You will be able to create modular, reusable RestAssured tests for real-world applications.

---