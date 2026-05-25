# **Hands-On Task: Basics of API Functional Testing**

This hands-on exercise will give you practical experience in performing functional API testing manually using **Postman**. You’ll learn how to define test inputs, validate responses, and design various test cases for APIs while querying public APIs.

---

## **Objective**

The goal of this task is to:
1. Understand the importance of API testing within the software testing lifecycle.
2. Use **Postman** to manually test APIs by sending requests and validating responses.
3. Write positive, negative, boundary, and edge case test scenarios for API functionality.
4. Practice querying **public APIs** to enhance your hands-on API testing skills.

---

## **Prerequisites**

1. Install **Postman** on your local machine (Available at: [Postman Download](https://www.postman.com/downloads/)).
2. Familiarity with basic HTTP concepts (e.g., Methods, Status Codes, Headers).
3. Access to the following public APIs for testing purposes:
   - JSONPlaceholder: [https://jsonplaceholder.typicode.com/](https://jsonplaceholder.typicode.com/).
   - ReqRes: [https://reqres.in/](https://reqres.in/).

---

## **Hands-On Task: Manual API Testing Using Postman**

### **Sub-Task 1: Explore Postman and Send a Basic API Request**
1. Open Postman and create a new request.
2. Configure the request as follows:
   - Method: `GET`
   - URL: `https://jsonplaceholder.typicode.com/posts/1`
3. **Send the request** and analyze the response:
   - Validate the status code (should be `200 OK`).
   - Check the `Content-Type` header (should be `application/json; charset=utf-8`).
   - Inspect the response body and verify it contains details of the post with `id=1`.

---

### **Sub-Task 2: Add Headers and Query Parameters**
1. Create a new request for the following endpoint:  
   `https://jsonplaceholder.typicode.com/comments`
2. Add the following **query parameter**:
   - `postId=1` (to fetch comments for the specific post).
3. Send the request:
   - Validate that the status code is `200 OK`.
   - Verify the response contains a list of comments for `postId=1`.
   - Check the length of the JSON array (there should be 5 comments).

---

### **Sub-Task 3: Create Post Requests with a Request Body**
1. Use the **POST** method to send a request to create a new resource:
   - URL: `https://jsonplaceholder.typicode.com/posts`
   - Headers:
     - `Content-Type: application/json`
   - Body (raw JSON):
     ```json
     {
       "title": "Test Post",
       "body": "This is a test post created via Postman",
       "userId": 123
     }
     ```
2. **Send the request** and validate the response:
   - Status code should be `201 Created`.
   - Response body should echo back the created post, including a new `id` for the resource.

---

### **Sub-Task 4: Validate Error Scenarios (Negative Tests)**
1. Send a **GET request** to the following invalid URL:
   - URL: `https://jsonplaceholder.typicode.com/posts/99999`
   - Validate the response:
     - Status code should be `404 Not Found`.
     - The response body should be empty or contain an error message.
2. Send a **POST request** to create a resource without a body or headers:
   - URL: `https://jsonplaceholder.typicode.com/posts`
   - Validate the response:
     - Status code should be `400 Bad Request`.
     - The error message (if available) should explain the issue with the request.

---

### **Sub-Task 5: Write Positive, Boundary, and Edge Case Tests**
1. Define **Positive Test Cases**:
   - Ensure valid inputs return expected responses.
   - Example: For the endpoint `GET https://jsonplaceholder.typicode.com/posts/{id}`, the `id` values of `1`, `50`, or any valid number should return corresponding posts.
2. Define **Boundary Test Cases**:
   - Test the limits of valid input values.
   - Example 1: For `id` in `GET https://jsonplaceholder.typicode.com/posts/{id}`, test `id=1` (lower boundary) and `id=100` (upper boundary).
   - Example 2: For `POST https://jsonplaceholder.typicode.com/posts`, test a minimum length for the title/body fields (1 character) and a maximum length (e.g., 500 characters).
3. Define **Edge Case Tests**:
   - Test extreme or invalid inputs that fall outside normal ranges.
   - Example 1: Use a non-integer `id` in the URL, e.g., `GET https://jsonplaceholder.typicode.com/posts/abc`.
   - Example 2: For `POST https://jsonplaceholder.typicode.com/posts`, submit very large payloads or empty objects.

---

### **Sub-Task 6: Practice with Another Public API**
1. Choose a second public API (e.g., **ReqRes API**: [https://reqres.in/](https://reqres.in/)).
2. Perform the following:
   - Send a **GET request** to fetch a user by id (`https://reqres.in/api/users/2`).
   - Send a **POST request** to create a user (`https://reqres.in/api/users`) with a JSON payload like:
     ```json
     {
       "name": "test user",
       "job": "developer"
     }
     ```
   - Validate the status code, headers, and the echoed response payload.
3. Explore the API documentation for more functionalities and test additional endpoints using variations of inputs.

---

## **Deliverables**
1. Create a **Postman collection** where each request is:
   - Properly named to reflect its purpose (e.g., `GET Post with ID 1`, `POST Create New Post`).
   - Organized into folders for different scenarios, such as `Positive Tests`, `Negative Tests`, and `Boundary/Edge Cases`.
   - Include request details like headers, query parameters, body, and description.
2. Save and export the Postman collection with all the test requests.
3. Push the exported **Postman collection** file (`.json`) to the Git repository provided to you. Ensure the repository structure is clean, and instructions for importing the collection are included in a `README.md` file within the repo.

---

## **How to Import the Postman Collection**

1. **Download Postman** (if not already installed) from [https://www.postman.com/downloads/](https://www.postman.com/downloads/).

2. **Import the Collection**:
   - Open Postman
   - Click on **"File"** → **"Import"** (or use **Ctrl+O**)
   - Select the file **`api-1.1-functional-testing.postman_collection.json`** from this repository
   - Click **"Import"** to add the collection to your workspace

3. **View the Collection**:
   - The collection will appear in the left sidebar under **Collections**
   - Expand the collection to view the organized folders:
     - **Positive Tests - JSONPlaceholder**: Basic GET and POST requests
     - **Negative Tests - Error Scenarios**: Error handling tests
     - **Boundary & Edge Cases Tests**: Boundary and edge case validations
     - **ReqRes API Tests**: Additional API testing scenarios

4. **Run the Requests**:
   - Click on any request to open it
   - Review the request details (method, URL, headers, body, tests)
   - Click **"Send"** to execute the request
   - Review the response and test results in the tabs below

5. **Execute the Full Collection** (Optional):
   - Select the collection name and click the **"Run"** button
   - This will execute all requests sequentially with their configured test scripts

---

## **Repository Structure**

```
api-1.1-functional-testing/
├── api-1.1-functional-testing.postman_collection.json  (Exported Postman collection)
├── README.md                                             (This file with import instructions)
└── .git/                                                 (Git repository metadata)
```

---

---

## **Evaluation Criteria**
1. **Collection Accuracy**:
   - Are all the required requests present, and are their details correct (method, headers, body, etc.)?
2. **Request Naming and Organization**:
   - Are requests and folders properly named and grouped logically?
3. **Effective Test Scenarios**:
   - Are the positive, negative, boundary, and edge case tests complete and well-designed?
4. **Git Usage**:
   - Was the collection correctly pushed to the provided Git repository following best practices for commit messages and organization?

---

## **By the End of This Task**
- You will gain proficiency in using Postman to manually test APIs and organize requests in a proper collection.
- Learn how to validate various aspects of API responses, including headers, status codes, and response body.
- Be able to create a reusable Postman collection that can be shared with others (or integrated into automated workflows).

---