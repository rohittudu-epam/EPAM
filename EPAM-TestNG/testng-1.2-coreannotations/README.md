# Project Name: testng-1.2-coreannotations

## Objectives:
- Understand and use essential TestNG annotations such as @Test, @BeforeSuite, @BeforeClass, @BeforeMethod, and their corresponding 'after' annotations.
- Control test execution order using priority and dependencies (dependsOnMethods).
- Disable specific test methods using enabled=false.

## Project Structure:

```plaintext
testng-1.2-coreannotations
|-- src
|   |-- main
|   |   |-- java
|   |       |-- com
|   |           |-- example
|   |               |-- App.java
|   |-- test
|       |-- java
|           |-- com
|               |-- example
|                   |-- AppTest.java
|-- pom.xml
|-- README.md
```

## Setup Instructions:

1. Clone the forked repository to your local:
```sh
git clone https://autocode.git.epam.com/epm-cmps-in/at/AT_IN_JAVA/testng-1.2-coreannotations.git
```

2. Navigate to the project directory:
```sh
cd testng-1.2-coreannotations
```

3. Add a new TestNG test class called "TestNGCoreAnnotationsTest":

Create a new file `src/test/java/com/example/TestNGCoreAnnotationsTest.java` and add test methods to cover each point from the objectives section

4. Run the test using Maven:
```sh
mvn test
```

5. Verify the test result and submit solution:
Verify test results for correcness, push changes to the remote repository and submit the solution
