# **Hands-On Task: Serialization and Deserialization**

This task focuses on implementing **serialization** and **deserialization** in API testing using RestAssured. You will learn to work with **POJOs (Plain Old Java Objects)**, convert Java objects to JSON/XML (serialization), and map JSON/XML responses back to Java objects (deserialization).

---

## **Objective**

By the end of this task, you will:
1. Understand the concept of **Serialization**: Converting Java objects into JSON/XML for API request payloads.
2. Learn **Deserialization**: Mapping JSON/XML responses into Java objects.
3. Create and use **POJOs** for representing REST API payloads and responses.
4. Handle complex nested API responses and arrays using Java libraries like **Jackson** or **Gson**.

---

## **Prerequisites**

1. A REST API test automation project set up with **RestAssured**, **Maven**, and **TestNG**.
2. Knowledge of Java programming, including working with classes and objects.
3. Familiarity with **JSON**, **XML**, and REST API concepts like request payloads and responses.

---

## **Hands-On Task: Serialization and Deserialization**

Follow these steps to implement serialization and deserialization in your project.

---

### **Step 1: Add Required Dependencies**
1. Open your **`pom.xml`** file in your Maven project.
2. Add the following dependencies:
   - **Jackson Databind** or **Gson** for converting between Java objects and JSON/XML.
   - Ensure your RestAssured and TestNG dependencies are already included.
3. Save the file and allow Maven to download the dependencies.

---

### **Step 2: Create POJOs for API Requests and Responses**
1. Define **Plain Old Java Objects (POJOs)** for the payloads your API sends or receives.
2. Analyze the JSON or XML schema of the API response or request and create a corresponding Java class.
3. Steps to define a POJO:
   - Create a Java class with fields representing JSON keys (e.g., `id`, `title`, `body`, etc.).
   - Use appropriate data types for fields (e.g., integers, strings, lists, etc.).
   - Add getter and setter methods or use libraries like Lombok to generate them automatically.

---

### **Step 3: Serialize Java Objects to JSON or XML**
1. Use your POJO class to construct a Java object representing the API request payload.
2. Serialize the Java object to JSON or XML format using:
   - **Jackson ObjectMapper** for JSON serialization.
   - **RestAssured**’s `.body()` method to pass the serialized object directly.
3. Send the serialized payload in the body of POST/PUT API requests using RestAssured.

---

### **Step 4: Deserialize JSON or XML Responses to Java Objects**
1. Use the POJO class to map API responses into Java objects dynamically.
2. Use Jackson or Gson to perform deserialization of JSON responses.
3. Extract the object’s fields for verification in tests or further processing.
4. Handle nested JSON/XML and arrays by using compound data types like lists or other POJO references.

---

### **Step 5: Handle Complex Responses**
1. Design POJOs to mirror complex API structures:
   - **Nested JSON Objects**: Create POJOs that reference other POJOs for inner objects.
   - **Arrays or Lists**: Use `List<Type>` or arrays for handling API fields with multiple values.
2. Deserialize complex and nested JSON/XML responses into hierarchical Java objects.

---

### **Step 6: Combine Serialization and Deserialization in Tests**
1. Combine both serialization and deserialization in a single test case:
   - Serialize a Java object representing the request payload.
   - Send the payload to the API and capture the response.
   - Deserialize the response into Java objects and validate the fields match expectations.

---

## **Examples of API Scenarios to Automate**
The following endpoints from **JSONPlaceholder API** can be used for testing:

### **Scenario 1: Serialize Java Objects to JSON**
- **API**: `POST /posts`
- **Task**:
   - Create a POJO class for the request payload with fields like `title`, `body`, and `userId`.
   - Serialize the Java object into a JSON payload using Jackson or RestAssured’s `.body()`.
   - Send the serialized payload in a POST request to create a new resource.
   - Validate the status code, headers, and response body.

### **Scenario 2: Deserialize JSON Response to Java Objects**
- **API**: `GET /posts/1`
- **Task**:
   - Define a POJO representing the response object for `/posts/1` (e.g., `id`, `title`, `body`, `userId`).
   - Send a GET request and deserialize the JSON response into a Java object.
   - Validate the content of the returned object fields using assertions.

### **Scenario 3: Handle Nested Responses**
- **API**: `GET /users/1`
- **Task**:
   - Analyze the response, which contains nested JSON fields like `address`, `geo`, etc.
   - Create POJOs for nested objects (e.g., `Address`, `Geo`).
   - Deserialize the full response, including the nested objects, into hierarchical Java objects.
   - Extract and validate values from the hierarchy.

---

## **Deliverables**
1. Push the following to the Git repository:
   - POJO classes created for representing request payloads and response objects.
   - Test cases demonstrating:
      - Serialization of Java objects to JSON/XML for request payloads.
      - Deserialization of JSON/XML responses to Java objects.
      - Handling complex nested responses with arrays and lists.
   - A **README.md** summarizing:
      - Steps to implement serialization and deserialization.
      - Instructions for executing your tests.

---

## **Evaluation Criteria**
Your submission will be evaluated based on:
1. **POJO Implementation**:
   - Are POJO classes correctly designed to reflect JSON/XML structures?
   - Are the data types for fields accurate and appropriate?
2. **Serialization and Deserialization**:
   - Are request payloads successfully serialized and sent using RestAssured?
   - Are responses correctly deserialized into Java objects for validation?
3. **Handling Complexity**:
   - Are nested responses handled effectively using hierarchical POJOs?
   - Are arrays or lists properly parsed and validated?
4. **Modularity**:
   - Are tests written in a modular manner for reuse in larger test suites?
5. **Git Best Practices**:
   - Are commits descriptive, and is the repository well-structured?

---

## **Bonus Task (Optional)**
1. Implement serialization and deserialization for APIs using `application/xml` content type alongside JSON.
2. Explore and implement **Lombok** to simplify POJO creation (remove boilerplate code for getters, setters, and constructors).
3. Write utilities for dynamic serialization/deserialization to reduce redundancy in tests.

---

## **By the End of This Task**
- You will have a strong understanding of serialization and deserialization using RestAssured.
- You will be proficient in creating and using POJO classes for REST API testing.
- You will be able to handle complex JSON/XML structures, making your tests extensible for real-world API scenarios.
- You will learn how to integrate Java libraries like Jackson or Gson into your test automation framework to handle data transformation seamlessly.

---