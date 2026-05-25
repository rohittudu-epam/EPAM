# API Automation Task - JHipster Sample Application

## Introduction
Welcome to your API automation training task. This project involves automating a series of RESTful API endpoints from the JHipster Sample Application using RestAssured and TestNG. Your task is to write comprehensive automated tests for the provided endpoints, ensuring both positive and negative scenarios are covered.

## Objectives
- Develop automated tests for specified API endpoints.
- Organize tests into appropriate classes and methods.
- Implement both positive and negative test scenarios for each endpoint.
- Use POJO classes for managing request payloads and response validations.
- Follow the project structure and guidelines provided in this document.

## Setup
You have been provided with a Git repository containing a Maven project configured with RestAssured and TestNG. Clone this repository to your local machine to begin your task.

### Repository Structure
- `src/main/java`: Contains the Java source files including POJO classes.
- `src/test/java`: Contains the TestNG test classes.
- `pom.xml`: Maven configuration file with all necessary dependencies.

## Local Application Setup
### Installation / Setup of Application Under Test (JHipster Sample Application)

#### Download the application:
1. Open [JHipster Sample Application GitHub Repository](https://github.com/jhipster/jhipster-sample-app/tree/main).
2. Clone the repository or download the zip file.
3. Unzip the application (if downloaded zip file).

#### Run the application:
1. Open a command prompt in the "jhipster-sample-app" directory.
2. Run the below two commands in sequence:
    - `./mvnw -Pdev clean verify` (build and verify the application)
    - `java -jar target/*.jar` (run the application)
    - `npm start` (start the front-end)
3. Open a browser and navigate to [http://localhost:9000/admin/docs](http://localhost:9000/admin/docs). Log in using "admin" / "admin".
4. View API Documentation for the endpoints.

## Task Details
You will be automating tests for the following API endpoints, grouped by functionality. Each group should be organized into its own test class.

### Operations
- **GET /api/operations**: View Operation
- **POST /api/operations**: Create Operation

### Bank Accounts
- **GET /api/bank-accounts**: View Bank Account
- **POST /api/bank-accounts**: Create Bank Account
- **PUT /api/bank-accounts/{id}**: Update Bank Account
- **DELETE /api/bank-accounts/{id}**: Delete Bank Account
- **PATCH /api/bank-accounts/{id}**: Partial Update Bank Account

### User Management
- **GET /api/admin/users**: View User
- **POST /api/admin/users**: Create User
- **PUT /api/admin/users/{login}**: Update User
- **DELETE /api/admin/users/{login}**: Delete User
- **PUT /api/admin/users**: Update User

### Account Management
- **POST /api/register**: Register User
- **POST /api/account/reset-password/init**: Reset Password

### Authentication
- **POST /api/authenticate**: Authenticate

## Testing Requirements
- Each test class should correspond to one service (e.g., `BankAccountTests`, `UserManagementTests`).
- Each endpoint must have at least two test methods: one for a positive scenario and one for a negative scenario.
- Use POJOs to serialize and deserialize API payloads and responses.
- Ensure your tests validate the response status codes, headers, and the body, where applicable.
- Negative tests should include scenarios such as unauthorized access, providing invalid input, and handling unexpected HTTP methods.

## Tools and Libraries
- **RestAssured**: For making HTTP requests and handling responses.
- **TestNG**: For organizing tests and providing assertions.
- **Jackson**: For JSON serialization and deserialization.

## Submission Guidelines
- Commit and push your changes to the provided Git repository.
- Ensure your code is well-documented and follows Java coding standards.
- Include comments in your tests to explain the purpose and logic of each test case.

## Evaluation Criteria
- Correctness and completeness of the tests.
- Code quality, including readability and use of best practices.
- Proper use of RestAssured and TestNG features.
- Effective use of POJOs for payload and response management.

Good luck with your task! If you have any questions or need further clarification, please feel free to reach out to your trainer.