# **Hands-On Task: Core RestAssured Features**

This task focuses on leveraging **RestAssured**'s core features for creating modular and reusable request/response specifications, building dynamic API requests, and validating responses effectively using its robust syntax.

---

## **Objective**

By the end of this hands-on task, you should:
1. Understand and configure **RequestSpecification** and **ResponseSpecification** for reusable API automation components.
2. Utilize **SpecBuilder** classes to refine modular configurations for requests and responses.
3. Practice RestAssured’s core syntax (`.given()`, `.when()`, `.then()`) for defining requests and validations.
4. Learn to use methods like `header()`, `body()`, `queryParam()`, and `pathParam()` for dynamic API requests.

---

## **Prerequisites**

1. **RestAssured Project Set Up Using Maven**:
    - Ensure you already have a Maven-based project with RestAssured and TestNG configured.
    - Validate that your dependencies (`RestAssured` and `TestNG`) are properly added to the `pom.xml`.
2. Knowledge of basic API testing concepts (HTTP methods, headers, query parameters, etc.).
3. Access to public APIs for testing purposes:
    - **JSONPlaceholder API**: [https://jsonplaceholder.typicode.com/](https://jsonplaceholder.typicode.com/).

---

## **Hands-On Task: Working with Core RestAssured Features**

Follow the steps below to complete the hands-on task. You will create modular specifications for requests and responses, construct dynamic API requests, and validate responses using RestAssured features.

---

### **Step 1: Configure RequestSpecification**
1. Define a **RequestSpecification** to create reusable configurations for API requests.
2. Include settings such as:
    - `Base URI` (e.g., `https://jsonplaceholder.typicode.com`).
    - Common headers like `Content-Type: application/json`.
    - Query or path parameters (if applicable).
3. Use **RequestSpecBuilder** to build the RequestSpecification dynamically.
4. Apply the RequestSpecification directly to API requests using `.given()`.

---

### **Step 2: Configure ResponseSpecification**
1. Define a **ResponseSpecification** for reusable response validation settings.
2. Include validations such as:
    - Expected **status code** (e.g., `200 OK`).
    - Expected **header values** (e.g., `Content-Type: application/json; charset=utf-8`).
3. Use **ResponseSpecBuilder** to modularize and enhance response specifications.
4. Apply the ResponseSpecification to API responses using `.then()`.

---

### **Step 3: Practice RestAssured Core Syntax**
Use RestAssured’s core methods to define and automate API requests and response handling:

#### **Define a GET Request**
1. Use `.given()` for request creation with RequestSpecification applied.
2. Send the request using `.get()` and define the endpoint (e.g., `/posts/1`).
3. Validate the response using `.then()`, applying ResponseSpecification for:
    - **Status Code**: Verify `200 OK`.
    - **Response Header**: Validate `Content-Type` as `application/json`.

#### **Define a POST Request**
1. Use `.given()` to configure a request with:
    - Headers like `Content-Type: application/json`.
    - Body payload to create a resource (e.g., JSON payload for `title`, `body`, `userId`).
2. Execute the POST request using `.post()`.
3. Validate the response for:
    - **Status Code**: Verify if it is `201 Created`.
    - **Response Fields**: Validate that the created resource is returned in the response body.

---

### **Step 4: Use Key Methods for Dynamic Requests**
Practice using dynamic request-building methods:

#### **Dynamic Path Parameters**
1. Use `.pathParam()` to define dynamic parameters in the endpoint URL (e.g., `/{postId}` in `/posts/{postId}`).
2. Parameterize the value during runtime (e.g., `postId=1`).

#### **Dynamic Query Parameters**
1. Use `.queryParam()` to dynamically define query values (e.g., `?userId=1` for `/posts`).
2. Ensure the query parameter retrieves filtered data.

#### **Add Custom Headers**
1. Use `.header()` to add customizable headers to a request (e.g., `Authorization` or `Content-Type`).

#### **Body Payloads**
1. Use `.body()` to handle JSON payloads for POST or PUT requests.
2. Write reusable payloads in string format, external JSON files, or Java POJOs.

---

### **Step 5: Create Modular Tests with Specifications**
1. Combine RequestSpecification and ResponseSpecification within your test methods.
2. Use these modular configurations to:
    - Automate multiple endpoints (e.g., `/posts`, `/users`, `/comments`).
    - Simplify test logic and improve scalability.

---

### **Step 6: Validate Responses**
1. Extract specific fields from the response body using:
    - **JsonPath**: Query and validate fields dynamically (e.g., validate `title`, `userId`, and `id`).
    - **Matchers**: Use matchers like `equalTo()` or `containsString()` for assertions.
2. Print the response body for debugging or tracking API results.

---

## **Examples of API Scenarios to Automate**
Use the following endpoints from **JSONPlaceholder API**:

1. **GET Request** for a specific resource:
    - Endpoint: `GET https://jsonplaceholder.typicode.com/posts/1`
    - Validate **Status Code**, **Content-Type**, and specific response fields.
2. **POST Request** to create a new resource:
    - Endpoint: `POST https://jsonplaceholder.typicode.com/posts`
    - Body: `{ "title": "foo", "body": "bar", "userId": 1 }`
    - Validate **Status Code**, **Response Body**, and newly created resource ID.
3. **GET Request** with Query Parameters:
    - Endpoint: `GET https://jsonplaceholder.typicode.com/posts?userId=1`
    - Validate the status code and response length.

---

## **Deliverables**
1. Push the completed tests to the provided Git repository:
    - Include modular RequestSpecification and ResponseSpecification configurations.
    - Include dynamic use of `.header()`, `.queryParam()`, `.pathParam()`, and `.body()`.
    - Ensure the repository includes all necessary project files (`pom.xml`, test classes, testng.xml).
2. Provide a **README.md** file summarizing:
    - Key configurations (e.g., specifications).
    - Instructions for running the tests.

---

## **Evaluation Criteria**
Your submission will be evaluated based on:
1. **Specification Setup**:
    - Are the RequestSpecification and ResponseSpecification properly configured and reusable?
2. **Implementation of Core Features**:
    - Do the tests demonstrate the effective use of `.given()`, `.when()`, `.then()`, and dynamic request methods?
3. **Accuracy**:
    - Are response validations correct, including status codes, headers, and body fields?
4. **Project Organization**:
    - Is the project structured cleanly and modularly for extensibility?
5. **Git Practices**:
    - Are commits meaningful, and is the repository well-organized?

---

## **By the End of This Task**
- You will understand how to configure and use RestAssured’s RequestSpecification and ResponseSpecification.
- You will be able to write modular, reusable tests using RestAssured’s syntax and methods.
- You will gain hands-on experience in validating API responses dynamically and comprehensively.

---