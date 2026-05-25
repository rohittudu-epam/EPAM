# Hands-On Task: Selenium Grid for Distributed Testing

In this task, you will learn to set up and use **Selenium Grid** to execute tests in parallel across multiple environments. You will explore two approaches:
1. Setting up a **local Selenium Grid** on a single machine (both Hub and Node on the same machine).
2. Using a free **BrowserStack** or **LambdaTest** cloud account to execute tests on a cloud-based Grid.

By leveraging distributed execution, you will improve the efficiency of your test automation and gain experience with parallel test execution.

---

## Objectives

By completing this task, you will:

1. Understand the **concept and benefits** of Selenium Grid:
    - Perform distributed test executions across multiple environments.
    - Utilize both local and cloud grids for execution.
2. Set up a **local Selenium Grid**:
    - Configure and start a Selenium Grid Hub and Node on the same machine to run tests.
3. Use **BrowserStack** or **LambdaTest**:
    - Leverage a cloud-based grid for executing tests on multiple browsers and operating systems.
4. Execute tests in parallel across multiple environments to improve test coverage and efficiency.

---

## Instructions

### 1. Introduction to Selenium Grid

#### What is Selenium Grid?

- **Selenium Grid** allows you to:
    - Perform **parallel execution** of automated tests.
    - Run tests across multiple browsers and operating systems.
    - Centralize control of test execution through a **Hub**.

#### Benefits of Selenium Grid:

- **Parallel Execution**: Reduces overall execution time by running multiple tests simultaneously.
- **Cross-Browser Testing**: Ensures compatibility across different browsers and environments.
- **Flexibility and Scalability**: Easily run tests locally or scale up using cloud services like BrowserStack or LambdaTest.

---

### 2. Option 1: Setting Up Local Selenium Grid

#### Local Grid Setup Instructions

1. **Install Requirements**
    - Ensure **Java Development Kit (JDK)** is installed and `java` is available in your system's PATH.
    - Download the latest **Selenium Server JAR** from the official Selenium website:  
      [https://www.selenium.dev/downloads](https://www.selenium.dev/downloads).

2. **Start the Selenium Grid Hub and Node on the Same Machine**
    - Open a terminal or command prompt.
    - Run the required command to start the Hub (this also runs as a Node in modern Selenium versions).  
      Navigate to `http://localhost:4444` in your browser to confirm setup.

3. **Configure Your Tests for Local Selenium Grid Hub**  
   Update your test scripts to point to the Selenium Grid Hub at `http://localhost:4444/wd/hub`.

---

### 3. Option 2: Using BrowserStack or LambdaTest (Cloud Grid)

#### Steps to Use Cloud Grids:

1. **Create an Account**  
   Sign up for a free account on one of the platforms:
    - [BrowserStack](https://www.browserstack.com/automate)
    - [LambdaTest](https://www.lambdatest.com/selenium-automation-testing)

2. **Obtain Access Credentials**  
   Retrieve your **username** and **access key** from your account dashboard.

3. **Run Tests on the Cloud Grid**  
   Update your test configuration to interact with BrowserStack or LambdaTest using their respective cloud-based grid URLs. Follow the documentation on each platform for integration details.

4. Execute your test scripts locally, while leveraging the cloud infrastructure for execution on multiple browsers and platforms.

---

### 4. Configure Parallel Execution With TestNG

#### Steps to Enable Parallel Execution:

1. Define your test classes and execution order.
2. Enable parallel execution using the TestNG XML configuration file:
    - Add `<parallel>` and `thread-count>` attributes to specify how tests should run in parallel.
3. Parameterize your tests if needed to run them dynamically with different configurations (e.g., browser, platform).

⚠️ **Note**: Trainees are expected to create and configure the `testng.xml` file on their own.

---

### 5. Verify Distributed Test Execution

#### For Local Grid Execution:
- Ensure tests are executed on the locally set up Selenium Grid.
- Verify Node activity by navigating to `http://localhost:4444` in your browser.

#### For Cloud Grid Execution:
- Monitor test activity via the BrowserStack or LambdaTest dashboard.
- View recorded sessions and test results directly on the cloud platform.

---

## Benefits of Using Selenium Grid for Testing

### 1. Improved Test Efficiency

- Execute multiple tests in parallel to save time.

### 2. Cross-Browser and Cross-Platform Testing

- Validate compatibility across various browsers and operating systems.

### 3. Scalability

- Automate distributed testing locally or scale up effortlessly using cloud services.

### 4. Centralized Control

- Manage and monitor distributed test execution using a grid.

---

## Deliverables

By completing this task, you should have:

1. **Local Selenium Grid Setup**:
    - A working Selenium Grid Hub and Node running on the same machine.

2. **Cloud Grid Execution**:
    - Successfully executed tests on BrowserStack or LambdaTest.

3. **Parallel Execution**:
    - Configured a TestNG XML file to execute tests across different browsers in parallel.

4. **Execution Results**:
    - Verified test execution and results via dashboards and TestNG reports.

---

## Submission Checklist

- [ ] Local Selenium Grid is set up.
- [ ] Tests are executed successfully on BrowserStack or LambdaTest.
- [ ] Test scripts are configured for distributed execution using grid infrastructure.
- [ ] TestNG XML file is updated for parallel execution.
- [ ] Execution results validated through reports and dashboards.

---

## Implementation In This Repository

### 1) Execution Modes (local, grid, browserstack)

Configuration file: `src/test/resources/config.properties`

- `execution.mode=local` uses local browser drivers (`ChromeDriver`, `FirefoxDriver`, `EdgeDriver`)
- `execution.mode=grid` uses `RemoteWebDriver` with `grid.hub.url`
- `execution.mode=browserstack` uses `RemoteWebDriver` with BrowserStack hub and `bstack:options`

Override settings with JVM properties:

```bash
mvn test -Dexecution.mode=grid -Dgrid.hub.url=http://localhost:4444/wd/hub
```

PowerShell-safe alternatives:

```powershell
mvn test "-Dexecution.mode=grid" "-Dgrid.hub.url=http://localhost:4444/wd/hub"
# or use camelCase property that avoids dot parsing issues
mvn test -DexecutionMode=grid -Dgrid.hub.url=http://localhost:4444/wd/hub
```

Environment variables are also supported (for example `BROWSERSTACK_USERNAME`, `BROWSERSTACK_ACCESSKEY`).

### 2) Local Selenium Grid Startup Scripts

- `start-grid-hub.ps1` starts Selenium Grid in standalone mode
- `start-grid-node.ps1` starts a dedicated node and attaches to an existing hub

Examples:

```powershell
./start-grid-hub.ps1 -SeleniumServerJar "selenium-server-4.29.0.jar"
./start-grid-node.ps1 -SeleniumServerJar "selenium-server-4.29.0.jar" -HubUrl "http://localhost:4444"
```

### 3) Parallel Execution

Parallel browser execution is configured in `testng.xml` with:

- `parallel="tests"`
- `thread-count="3"`
- Browser-parameterized test blocks for Chrome, Firefox, and Edge

Maven Surefire is configured to run `testng.xml` directly.

### 3.1) Where To Find Submission Evidence

- Parallel suite file: `testng.xml`
- Surefire reports: `target/surefire-reports`
- Failure screenshots (auto-captured): `target/screenshots`
- Grid status endpoint: `http://localhost:4444/status`
- Grid UI: `http://localhost:4444/ui`

### 4) BrowserStack Reporting

When running in BrowserStack mode, each test method:

- sets BrowserStack session name in `@BeforeMethod`
- sets session status (`passed`/`failed`) in `@AfterMethod`

Note on BrowserStack Local:

- `browserstack.local=false` by default
- set `browserstack.local=true` only when BrowserStack Local is running

### 5) Suggested Evidence For Submission

- Grid UI screenshot: `http://localhost:4444/ui`
- BrowserStack dashboard screenshot of sessions
- TestNG report from `target/surefire-reports`

---

## Example Commit Messages

- "Set up local Selenium Grid with Hub and Node on the same machine."
- "Configured tests for BrowserStack execution."
- "Updated TestNG configuration for parallel test execution."
- "Verified successful distributed test execution and reporting."

---

## Additional Resources

### Selenium Grid Documentation
[https://www.selenium.dev/documentation/grid/getting_started](https://www.selenium.dev/documentation/grid/getting_started)

### BrowserStack Documentation
[https://www.browserstack.com/docs/selenium](https://www.browserstack.com/docs/selenium)

### LambdaTest Documentation
[https://www.lambdatest.com/support/docs/selenium-testing-overview](https://www.lambdatest.com/support/docs/selenium-testing-overview)

### TestNG Parallel Execution
[https://testng.org/doc/documentation-main.html#parallel-running](https://testng.org/doc/documentation-main.html#parallel-running)

---
