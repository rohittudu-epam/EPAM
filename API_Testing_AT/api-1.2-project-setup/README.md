# **RestAssured API Testing Project**

A comprehensive Maven-based API automation testing framework using RestAssured and TestNG for testing the JSONPlaceholder API.

---

## **Project Overview**

This project demonstrates a fully configured RestAssured testing framework with the following features:
- **REST API Testing**: Automated testing of GET, POST, and other HTTP methods
- **TestNG Integration**: Organized test execution and reporting
- **Maven Build Management**: Dependency management and project configuration
- **Modular Architecture**: Reusable test classes organized by functionality
- **Comprehensive Documentation**: Clear setup and execution instructions

---

## **Technology Stack**

| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 11+ | Programming language |
| Maven | 3.6+ | Build tool and dependency management |
| RestAssured | 5.5.0 | REST API testing library |
| TestNG | 7.8.0 | Test framework and execution engine |
| Jackson | 2.20.1 | JSON parsing and serialization |

---

## **Project Structure**

```
restassured-sample/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/epam/campus/restassured/
│   │           └── Main.java
│   └── test/
│       └── java/
│           └── com/epam/campus/restassured/
│               ├── SampleTest.java          # Core GET/POST tests
│               ├── GETtests.java            # Comprehensive GET endpoint tests
│               └── POSTtests.java           # POST and resource creation tests
├── pom.xml                                   # Maven configuration
├── testng.xml                                # TestNG suite configuration
└── README.md                                 # This file
```

---

## **Prerequisites**

Before running the project, ensure you have:

1. **Java Development Kit (JDK) 11 or later**
   ```bash
   java -version
   ```
   
2. **Apache Maven 3.6 or later**
   ```bash
   mvn -version
   ```

3. **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

4. **Internet Connection** (for downloading dependencies and accessing JSONPlaceholder API)

---

## **Setup Instructions**

### **1. Clone or Download the Project**
```bash
cd path/to/your/projects
git clone <repository-url>
cd api-1.2-project-setup
```

### **2. Install Dependencies**
Maven will automatically download all dependencies from the `pom.xml` file. No manual installation is required.

### **3. Verify Project Setup**
```bash
mvn clean validate
```

### **4. Compile the Project**
```bash
mvn clean compile
```

---

## **Running the Tests**

### **Option 1: Run All Tests via Maven**
```bash
mvn clean test
```

### **Option 2: Run Tests via TestNG XML Configuration**
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### **Option 3: Run Tests via IDE**
- **IntelliJ IDEA**: Right-click on `testng.xml` → Run 'testng.xml'
- **Eclipse**: Right-click on `testng.xml` → Run As → TestNG Suite

### **Option 4: Run Specific Test Class**
```bash
mvn test -Dtest=SampleTest
```

### **Option 5: Run Specific Test Method**
```bash
mvn test -Dtest=GETtests#testGetAllPosts
```

---

## **Test Coverage**

### **SampleTest.java**
Core API testing functionality with 3 test methods:
- `testGetPostById()`: Validates GET /posts/1 endpoint
- `testCreatePost()`: Validates POST /posts endpoint for resource creation
- `testGetCommentsByPostId()`: Validates GET /comments?postId=1 endpoint

### **GETtests.java**
Comprehensive GET endpoint testing with 7 test methods:
- `testGetAllPosts()`: Retrieve all posts
- `testGetPostById()`: Retrieve specific post by ID
- `testGetCommentsByPostId()`: Retrieve comments for a specific post
- `testGetAllComments()`: Retrieve all comments
- `testGetPostsByUserId()`: Retrieve posts by user ID
- `testGetCommentById()`: Retrieve specific comment by ID
- `testGetResponseHeaders()`: Validate response headers

### **POSTtests.java**
POST and resource creation testing with 7 test methods:
- `testCreateNewPost()`: Create new post with valid data
- `testCreatePostWithMultipleFields()`: Create post with multiple fields
- `testPostResponseContainsAllFields()`: Verify all required fields in response
- `testCreatePostWithSpecificUser()`: Create post for specific user
- `testPostResponseHeaders()`: Verify POST response headers
- `testCreateMultiplePosts()`: Create multiple posts sequentially
- `testCreatePostWithLongContent()`: Create post with extended content

---

## **Dependencies Overview**

### **RestAssured (5.5.0)**
- Purpose: REST API testing framework
- Usage: Sending HTTP requests and validating responses
- Scope: Test

### **TestNG (7.8.0)**
- Purpose: Test framework for organizing and executing tests
- Usage: Test annotations, assertions, and test organization
- Scope: Test

### **Jackson (2.20.1)**
- Purpose: JSON serialization/deserialization
- Usage: JSON parsing in API responses
- Scope: Compile

---

## **Key Features**

### **1. Fluent API Testing**
```java
Response response = given()
    .baseUri("https://jsonplaceholder.typicode.com")
    .when()
    .get("/posts/1")
    .then()
    .statusCode(200)
    .extract()
    .response();
```

### **2. Assertion and Validation**
- Status code validation
- Header validation
- Response body validation using JsonPath
- Hamcrest matchers for flexible assertions

### **3. Parameterized Testing**
- Query parameters
- Path parameters
- Request body customization

### **4. Modular Organization**
- Separate test classes by functionality
- Clear test naming conventions
- Priority-based test execution

---

## **Test Execution Flow**

1. **Sample Tests** → Core functionality validation
2. **GET Tests** → Comprehensive endpoint testing
3. **POST Tests** → Resource creation and validation

Each test class is independent and can run in any order.

---

## **API Endpoints Tested**

### **GET Endpoints**
- `GET /posts` - Retrieve all posts
- `GET /posts/{id}` - Retrieve specific post
- `GET /comments` - Retrieve all comments
- `GET /comments/{id}` - Retrieve specific comment
- `GET /posts?userId={id}` - Retrieve posts by user
- `GET /comments?postId={id}` - Retrieve comments by post

### **POST Endpoints**
- `POST /posts` - Create new post

### **Base URL**
- `https://jsonplaceholder.typicode.com`

---

## **Troubleshooting**

### **Issue: Maven dependencies not downloading**
**Solution**: 
```bash
mvn clean install -U
```

### **Issue: Tests failing with connection timeout**
**Solution**: Check your internet connection and verify the JSONPlaceholder API is accessible

### **Issue: TestNG plugin not found**
**Solution**: Ensure TestNG is installed in your IDE
- IntelliJ IDEA: Settings → Plugins → Search for TestNG
- Eclipse: Help → Eclipse Marketplace → Search for TestNG

### **Issue: Compilation errors**
**Solution**: 
```bash
mvn clean compile
mvn test
```

---

## **Best Practices Implemented**

✅ **Modular Test Organization**: Separate test classes by functionality
✅ **Clear Naming Conventions**: Descriptive test method and class names
✅ **Reusable Configuration**: Centralized base URI and common setup
✅ **Comprehensive Assertions**: Multiple validation points per test
✅ **Test Priorities**: Execution order defined via @Test priority
✅ **Documentation**: Inline comments and method descriptions
✅ **Maven Configuration**: Proper dependency management and build plugins

---

## **Extending the Project**

### **Add New Test Class**
1. Create a new Java class under `src/test/java/com/epam/campus/restassured/`
2. Add `@Test` annotations to your test methods
3. Update `testng.xml` to include the new test class

### **Add New Dependencies**
1. Edit `pom.xml`
2. Add new `<dependency>` within `<dependencies>` section
3. Run `mvn clean install`

### **Example: Adding Logging**
```bash
# Add log4j to pom.xml
mvn install
```

---

## **Test Results Interpretation**

### **Successful Test Run**
```
[INFO] Tests run: 17, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### **Failed Test**
```
[ERROR] FAILURES: 1
[ERROR] testGetPostById(com.epam.campus.restassured.GETtests)
```

Test reports are generated in `target/surefire-reports/`

---

## **Git Best Practices**

```bash
# Clone the repository
git clone <repository-url>

# Create a feature branch
git checkout -b feature/add-delete-tests

# Make changes and commit
git add .
git commit -m "Add DELETE endpoint tests"

# Push to repository
git push origin feature/add-delete-tests
```

---

## **Continuous Integration (Optional)**

To set up CI/CD pipeline:

1. **GitHub Actions** `.github/workflows/maven.yml`
2. **Jenkins** pipeline configuration
3. **GitLab CI** `.gitlab-ci.yml`

Example GitHub Actions workflow:
```yaml
name: Run Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
      - run: mvn clean test
```

---

## **Performance Optimization**

### **Parallel Test Execution**
Update `testng.xml`:
```xml
<suite name="Suite" parallel="methods" thread-count="4">
```

### **Test Retries**
Add retry logic for flaky tests:
```java
@Test(retryAnalyzer = RetryAnalyzer.class)
```

---

## **Resources and References**

- **RestAssured Documentation**: https://rest-assured.io/
- **TestNG Documentation**: https://testng.org/
- **JSONPlaceholder API**: https://jsonplaceholder.typicode.com/
- **Maven Documentation**: https://maven.apache.org/
- **Hamcrest Matchers**: http://hamcrest.org/

---

## **Support and Contributions**

For issues, questions, or suggestions:
1. Check existing documentation
2. Review test implementations
3. Consult RestAssured and TestNG documentation
4. Submit issues to the repository

---

## **License**

This project is for educational purposes. Refer to your organization's licensing policy.

---

## **Summary**

This RestAssured project provides a solid foundation for API automation testing. It demonstrates:
- Proper Maven project setup
- Comprehensive test implementation
- Best practices in test organization
- Real-world API testing scenarios
- Extensible architecture for future enhancements

By following this project structure, you can scale your API automation testing suite efficiently and maintain high code quality.

---

**Last Updated**: February 2026
**Version**: 1.0-SNAPSHOT