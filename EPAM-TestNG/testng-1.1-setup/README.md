# Project Name: testng-1.1-setup

## Objectives:
- To set up a working environment for TestNG using Maven.
- To create a basic TestNG project structure.
- To verify the TestNG installation by running a simple test case.

## Project Structure:

```plaintext
testng-1.1-setup
|-- src
|   |-- main
|   |   |-- java
|   |       |-- App.java
|   |-- test
|       |-- java
|           |-- AppTest.java
|-- pom.xml
|-- README.md
```

## Setup Instructions:

1. Clone the forked repository to your local:

2. Navigate to the project directory:
```sh
cd testng-1.1-setup
```
3. Add a new TestNG test class called "MyFirstTestNGTest":

Create a new file src/test/java/MyFirstTestNGTest.java with a test method called firstTestMethod which will print "This is my first TestNG test method!".

4. Run the test using Maven:
```sh
mvn test
```

5. Verify the test result:
You should see the test result in the console output, indicating that the TestNG setup is working correctly.

## Files to be Implemented by Trainees:
- Add more test cases in `MyFirstTestNGTest.java` to further explore TestNG features.

## Additional Notes:
- Ensure that you have Maven and JDK installed on your machine before running the tests.
- Refer to the official TestNG documentation for more details on writing and running tests.