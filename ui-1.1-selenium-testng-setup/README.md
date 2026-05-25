# Hands-On Task: Setting Up the Environment for Selenium WebDriver with TestNG

In this task, you will set up the required environment for Selenium WebDriver and configure a TestNG-based test automation project using Maven. You will also write a sample test to verify the setup.

---

## Objectives

By completing this hands-on task, you will:

1. Set up a **Maven-based TestNG** project in IntelliJ IDEA or your preferred IDE.
2. Add necessary dependencies for Selenium WebDriver and TestNG.
3. Configure and execute a sample TestNG test to verify the environment setup.

---

## Prerequisites

Ensure the following are already installed and configured:

1. **Java Development Kit (JDK)**:
    - Verify installation by running:
      ```bash
      java -version
      ```

2. **Maven**:
    - Verify installation by running:
      ```bash
      mvn -version
      ```

3. **IntelliJ IDEA (Community Edition)**:
    - Install from the [JetBrains Official Site](https://www.jetbrains.com/idea/download/).

4. **Google Chrome**:
    - Install the latest version of Google Chrome. Selenium will use the installed browser version automatically.

---

## Step-by-Step Instructions

### 1. Create a Maven Project

1. **Create the Project**:
    - Open IntelliJ IDEA and select `File > New Project > Maven`.
    - Provide the details:
        - **Group ID**: `com.example`
        - **Artifact ID**: `SeleniumTestNGProject`
    - Click `Finish`.

2. **Verify Maven Setup**:
    - Ensure a `pom.xml` file is created in the project root.

---

### 2. Add Selenium and TestNG Dependencies

1. **Add Dependencies to `pom.xml`**:
    - Open the `pom.xml` file and add the Selenium and TestNG dependencies:
      ```xml
      <dependencies>
          <!-- Selenium WebDriver -->
          <dependency>
              <groupId>org.seleniumhq.selenium</groupId>
              <artifactId>selenium-java</artifactId>
              <version>4.x.x</version> <!-- Replace with the latest Selenium version -->
          </dependency>
 
          <!-- TestNG -->
          <dependency>
              <groupId>org.testng</groupId>
              <artifactId>testng</artifactId>
              <version>7.x.x</version> <!-- Replace with the latest TestNG version -->
              <scope>test</scope>
          </dependency>
      </dependencies>
      ```

2. **Reload Maven Dependencies**:
    - Right-click on the project in IntelliJ IDEA and select `Maven > Reload Project`. This will download and configure the Selenium and TestNG dependencies.

---

### 3. Write a Sample Test with TestNG

1. **Create the Test Class**:
    - Under `src/test/java`, create a package `com.example.tests`.
    - Inside the package, create a class `SampleTest`.

2. **Write a TestNG Test**:
    - Add the following code to `SampleTest`:
      ```java
      package com.example.tests;
 
      import org.openqa.selenium.WebDriver;
      import org.openqa.selenium.chrome.ChromeDriver;
      import org.testng.Assert;
      import org.testng.annotations.AfterClass;
      import org.testng.annotations.BeforeClass;
      import org.testng.annotations.Test;
 
      public class SampleTest {
          WebDriver driver;
 
          @BeforeClass
          public void setUp() {
              // Initialize WebDriver for Chrome
              driver = new ChromeDriver();
          }
 
          @Test
          public void testGoogleHomePage() {
              // Navigate to Google
              driver.get("https://www.google.com");
 
              // Get and validate the page title
              String actualTitle = driver.getTitle();
              String expectedTitle = "Google";
              Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
          }
 
          @AfterClass
          public void tearDown() {
              // Quit the browser
              if (driver != null) {
                  driver.quit();
              }
          }
      }
      ```
    - This test:
        - Launches the Chrome browser.
        - Navigates to Google's homepage.
        - Validates the page title using a TestNG assertion.
        - Closes the browser after execution.

---

### 4. Add the TestNG XML Configuration File

1. **Create `testng.xml`**:
    - Create a file named `testng.xml` in the project's root directory.
    - Add the following content:
      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <suite name="SampleSuite">
          <test name="SampleTest">
              <classes>
                  <class name="com.example.tests.SampleTest"/>
              </classes>
          </test>
      </suite>
      ```

2. **What This XML Does**:
    - Defines a suite (`SampleSuite`) and a test (`SampleTest`).
    - Includes the `SampleTest` class for execution.

---

### 5. Execute the Test

1. **Run the TestNG Test**:
    - Right-click the `testng.xml` file in IntelliJ IDEA and select `Run 'testng.xml'`.

2. **Verify Output**:
    - Ensure the browser launches and navigates to the Google homepage.
    - Confirm that the assertion (`title == "Google"`) passes.
    - The browser should close automatically after the test completes.

---

## Deliverables

By the end of this task, you should have:

1. **Maven Project**:
    - A fully configured Maven project with Selenium and TestNG.

2. **Sample Test**:
    - A test class that validates the title of Google's homepage.

3. **TestNG XML**:
    - A configuration file to run the test suite.

4. **Test Execution Results**:
    - Verified test execution with correct browser behavior and test results.

---

## Additional Information

1. **Selenium in Version 4+**:
    - Selenium manages browser driver binaries automatically, so you do not need to manually download and configure drivers (e.g., ChromeDriver, GeckoDriver, etc.).


2. **Useful Links**:
    - Selenium WebDriver Documentation: [https://www.selenium.dev/documentation/](https://www.selenium.dev/documentation/)
    - TestNG Documentation: [https://testng.org/doc/](https://testng.org/doc/)

---

## Task Submission Checklist

Before marking this task as complete, ensure that:

- [x] Selenium and TestNG dependencies are added and configured correctly in `pom.xml`.
- [x] Test class (`SampleTest`) is implemented with browser launch and validation.
- [x] TestNG XML (`testng.xml`) is added and configured correctly.
- [x] The test executes successfully and verifies the page title.

---

## Example Commit Messages
- "Created Maven project with Selenium and TestNG dependencies."
- "Added sample test for validating Google's homepage title."
- "Configured TestNG XML for test execution."

---

Good luck! If you encounter any issues or need guidance, feel free to reach out to your trainer.

---