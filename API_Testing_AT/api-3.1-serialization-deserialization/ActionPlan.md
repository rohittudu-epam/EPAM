# 🚀 ACTION PLAN — Serialization & Deserialization Project

---

# 1️⃣ High-Level Architecture

We will build the project using a clean layered structure:

```
serialization-deserialization-framework/
│
├── pom.xml
├── README.md
│
└── src
    └── test
        ├── java
        │   ├── base/
        │   │   └── BaseTest.java
        │   │
        │   ├── config/
        │   │   └── TestConfig.java
        │   │
        │   ├── constants/
        │   │   └── ApiEndpoints.java
        │   │
        │   ├── pojo/
        │   │   ├── request/
        │   │   │   └── PostRequest.java
        │   │   │
        │   │   ├── response/
        │   │   │   ├── PostResponse.java
        │   │   │   ├── UserResponse.java
        │   │   │   ├── Address.java
        │   │   │   ├── Geo.java
        │   │   │   └── Company.java
        │   │
        │   ├── client/
        │   │   └── PostApiClient.java
        │   │
        │   ├── utils/
        │   │   ├── ObjectMapperUtil.java
        │   │   └── ResponseValidator.java
        │   │
        │   └── tests/
        │       ├── SerializationTest.java
        │       ├── DeserializationTest.java
        │       └── NestedResponseTest.java
        │
        └── resources/
            └── testng.xml
```

---

# 2️⃣ pom.xml Dependencies

### Required Dependencies

```xml
<dependencies>

    <!-- RestAssured -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.4.0</version>
    </dependency>

    <!-- Jackson -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.17.0</version>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.9.0</version>
    </dependency>

    <!-- Lombok (Optional Bonus) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.32</version>
        <scope>provided</scope>
    </dependency>

</dependencies>
```

---

# 3️⃣ Base Configuration Layer

---

## 📁 config/TestConfig.java

```java
public class TestConfig {

    public static final String BASE_URI = "https://jsonplaceholder.typicode.com";

}
```

---

## 📁 constants/ApiEndpoints.java

```java
public class ApiEndpoints {

    public static final String POSTS = "/posts";
    public static final String USERS = "/users";

}
```

---

## 📁 base/BaseTest.java

```java
public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = TestConfig.BASE_URI;
    }

}
```

---

# 4️⃣ POJO Design

---

# ✅ Scenario 1 — POST /posts (Serialization)

### JSON Structure

```json
{
  "title": "Test Title",
  "body": "Test Body",
  "userId": 1
}
```

---

## 📁 pojo/request/PostRequest.java

```java
public class PostRequest {

    private String title;
    private String body;
    private int userId;

    // Constructors
    public PostRequest() {}

    public PostRequest(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    // Getters & Setters
}
```

---

## 📁 pojo/response/PostResponse.java

```java
public class PostResponse {

    private int id;
    private String title;
    private String body;
    private int userId;

    // Getters & Setters
}
```

---

# ✅ Scenario 3 — Nested GET /users/1

Endpoint:
`GET https://jsonplaceholder.typicode.com/users/1`

---

## 📁 pojo/response/UserResponse.java

```java
public class UserResponse {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private Company company;

    // Getters & Setters
}
```

---

## 📁 pojo/response/Address.java

```java
public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

}
```

---

## 📁 pojo/response/Geo.java

```java
public class Geo {

    private String lat;
    private String lng;

}
```

---

## 📁 pojo/response/Company.java

```java
public class Company {

    private String name;
    private String catchPhrase;
    private String bs;

}
```

---

# 5️⃣ API Client Layer (Reusable)

---

## 📁 client/PostApiClient.java

```java
public class PostApiClient {

    public Response createPost(PostRequest request) {
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(ApiEndpoints.POSTS);
    }

    public Response getPostById(int id) {
        return given()
                .when()
                .get(ApiEndpoints.POSTS + "/" + id);
    }

    public Response getUserById(int id) {
        return given()
                .when()
                .get(ApiEndpoints.USERS + "/" + id);
    }
}
```

---

# 6️⃣ Utility Layer

---

## 📁 utils/ObjectMapperUtil.java

```java
public class ObjectMapperUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T deserialize(Response response, Class<T> clazz) {
        try {
            return mapper.readValue(response.asString(), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Deserialization Failed", e);
        }
    }

}
```

---

# 7️⃣ Test Layer

---

# 🔹 SerializationTest.java

```java
public class SerializationTest extends BaseTest {

    @Test
    public void createPost_SerializationTest() {

        PostRequest request = new PostRequest(
                "Automation Title",
                "Automation Body",
                1
        );

        PostApiClient client = new PostApiClient();
        Response response = client.createPost(request);

        PostResponse postResponse =
                response.as(PostResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(postResponse.getTitle(), request.getTitle());
    }
}
```

---

# 🔹 DeserializationTest.java

```java
public class DeserializationTest extends BaseTest {

    @Test
    public void getPost_DeserializationTest() {

        PostApiClient client = new PostApiClient();
        Response response = client.getPostById(1);

        PostResponse post =
                response.as(PostResponse.class);

        Assert.assertEquals(post.getId(), 1);
        Assert.assertNotNull(post.getTitle());
    }
}
```

---

# 🔹 NestedResponseTest.java

```java
public class NestedResponseTest extends BaseTest {

    @Test
    public void getUser_NestedDeserializationTest() {

        PostApiClient client = new PostApiClient();
        Response response = client.getUserById(1);

        UserResponse user =
                response.as(UserResponse.class);

        Assert.assertEquals(user.getId(), 1);
        Assert.assertNotNull(user.getAddress().getCity());
        Assert.assertNotNull(user.getAddress().getGeo().getLat());
        Assert.assertNotNull(user.getCompany().getName());
    }
}
```

---

# 8️⃣ Program Flow

---

## 🔄 FLOW — POST Serialization Test

```
Test Method
    ↓
Create PostRequest object
    ↓
RestAssured .body(request)
    ↓
Jackson auto-serializes object → JSON
    ↓
POST API call
    ↓
Response JSON received
    ↓
response.as(PostResponse.class)
    ↓
Jackson auto-deserializes JSON → Java Object
    ↓
Assertions
```

---

## 🔄 FLOW — Nested GET Test

```
GET /users/1
    ↓
JSON Response (nested)
    ↓
response.as(UserResponse.class)
    ↓
Jackson maps:
    address → Address object
    geo → Geo object
    company → Company object
    ↓
Hierarchical object tree created
    ↓
Assertions
```

---

# 9️⃣ Execution Instructions

Run:

```
mvn clean test
```

Or via TestNG XML.

---

# 🔟 README Content Outline

Your README.md should include:

* What is Serialization?
* What is Deserialization?
* Project Structure
* How to Run Tests
* Sample Output
* Nested Handling Explanation
* Bonus XML Handling (if implemented)

---

# 💎 BONUS (Advanced Level)

If you want senior-level improvement:

* Create a GenericApiClient
* Use Builder Pattern for POJOs
* Add RequestSpecBuilder
* Add Logging Filters
* Implement Response Wrapper
* Add Schema Validation

---

# 🏁 Final Result

By completing this structure:

✔ Clean layered framework
✔ Reusable API client
✔ Proper POJO modeling
✔ Nested object handling
✔ Production-ready modular design

