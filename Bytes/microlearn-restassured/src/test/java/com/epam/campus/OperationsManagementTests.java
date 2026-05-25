package com.epam.campus;

import java.time.Instant;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.campus.Base.BaseTest;
import com.epam.campus.constants.ApiEndPoints;
import com.epam.campus.pojo.BankAccountResponse;
import com.epam.campus.pojo.OperationResponse;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Test class for Operations Management API endpoints.
 * Tests the following endpoints:
 * - GET /api/operations: View Operation
 * - POST /api/operations: Create Operation
 */
public class OperationsManagementTests extends BaseTest {

    private Long testBankAccountId;

    /**
     * Setup method to create a bank account for operations testing.
     */
    @Override
    @BeforeClass
    public void setup() {
        super.setup();
        
        // Create a bank account to use for operations
        String uniqueName = generateUniqueName("OperationsTestAccount");
        BankAccountResponse bankAccount = new BankAccountResponse(uniqueName, 10000.00);

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(bankAccount)
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .extract()
                .response();

        if (response.getStatusCode() == 201) {
            BankAccountResponse createdAccount = response.as(BankAccountResponse.class);
            testBankAccountId = createdAccount.getId();
        }
    }

    /**
     * Positive Test: Verify successful retrieval of all operations.
     * Expected: HTTP 200 OK with list of operations.
     */
    @Test(description = "Verify successful retrieval of all operations", priority = 1)
    public void testGetAllOperations() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .get(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Expected HTTP 200 OK");
        
        // Verify response is an array
        OperationResponse[] operations = response.as(OperationResponse[].class);
        assertNotNull(operations, "Response should contain operations array");
    }

    /**
     * Negative Test: Verify unauthorized access to operations without authentication.
     * Expected: HTTP 401 Unauthorized.
     */
    @Test(description = "Verify unauthorized access to operations without authentication", priority = 1)
    public void testGetAllOperationsWithoutAuth() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code indicates unauthorized
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Positive Test: Verify successful creation of a new operation.
     * Expected: HTTP 201 Created with operation details.
     */
    @Test(description = "Verify successful creation of a new operation", priority = 2)
    public void testCreateOperation() {
        // Create operation with associated bank account
        String operationBody = String.format(
                "{\"date\":\"%s\",\"description\":\"Test Operation\",\"amount\":100.50,\"bankAccount\":{\"id\":%d}}",
                Instant.now().toString(),
                testBankAccountId
        );

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(operationBody)
                .when()
                .post(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code (201 or 200)
        assertTrue(response.getStatusCode() == 201 || response.getStatusCode() == 200,
                "Expected HTTP 201 Created or 200 OK, got: " + response.getStatusCode());
        
        // If successful, verify response contains operation details
        if (response.getStatusCode() == 201 || response.getStatusCode() == 200) {
            OperationResponse createdOperation = response.as(OperationResponse.class);
            assertNotNull(createdOperation.getId(), "Created operation should have an ID");
            assertEquals(createdOperation.getAmount(), 100.50, "Operation amount should match");
        }
    }

    /**
     * Positive Test: Verify successful creation of operation with negative amount (withdrawal).
     * Expected: HTTP 201 Created with operation details.
     */
    @Test(description = "Verify successful creation of operation with negative amount", priority = 2)
    public void testCreateOperationWithNegativeAmount() {
        String operationBody = String.format(
                "{\"date\":\"%s\",\"description\":\"Withdrawal Operation\",\"amount\":-50.00,\"bankAccount\":{\"id\":%d}}",
                Instant.now().toString(),
                testBankAccountId
        );

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(operationBody)
                .when()
                .post(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code (201 or 200 for successful creation)
        assertTrue(response.getStatusCode() == 201 || response.getStatusCode() == 200,
                "Expected HTTP 201 or 200, got: " + response.getStatusCode());
    }

    /**
     * Negative Test: Verify operation creation fails without authentication.
     * Expected: HTTP 401 Unauthorized.
     */
    @Test(description = "Verify operation creation fails without authentication", priority = 2)
    public void testCreateOperationWithoutAuth() {
        String operationBody = String.format(
                "{\"date\":\"%s\",\"description\":\"Unauthorized Operation\",\"amount\":100.00,\"bankAccount\":{\"id\":1}}",
                Instant.now().toString()
        );

        Response response = given()
                .spec(requestSpec)
                .body(operationBody)
                .when()
                .post(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code indicates unauthorized
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Negative Test: Verify operation creation fails with missing required fields.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify operation creation fails with missing required fields", priority = 3)
    public void testCreateOperationWithMissingFields() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .body("{}")
                .when()
                .post(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code indicates validation failure
        assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 500,
                "Expected HTTP 400 or 500 for missing required fields, got: " + response.getStatusCode());
    }

    /**
     * Negative Test: Verify operation creation fails with invalid bank account reference.
     * Expected: HTTP 400 Bad Request or 500 Internal Server Error.
     */
    @Test(description = "Verify operation creation fails with invalid bank account reference", priority = 3)
    public void testCreateOperationWithInvalidBankAccount() {
        String operationBody = String.format(
                "{\"date\":\"%s\",\"description\":\"Invalid Bank Account\",\"amount\":100.00,\"bankAccount\":{\"id\":99999}}",
                Instant.now().toString()
        );

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(operationBody)
                .when()
                .post(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code indicates failure (may vary by implementation)
        assertTrue(response.getStatusCode() >= 400,
                "Expected HTTP 4xx or 5xx error, got: " + response.getStatusCode());
    }

    /**
     * Negative Test: Verify operation creation fails with invalid date format.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify operation creation fails with invalid date format", priority = 3)
    public void testCreateOperationWithInvalidDateFormat() {
        String operationBody = String.format(
                "{\"date\":\"invalid-date\",\"description\":\"Invalid Date Operation\",\"amount\":100.00,\"bankAccount\":{\"id\":%d}}",
                testBankAccountId
        );

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(operationBody)
                .when()
                .post(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code indicates validation failure
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request for invalid date");
    }

    /**
     * Negative Test: Verify invalid HTTP method returns appropriate error.
     * Expected: HTTP 405 Method Not Allowed.
     */
    @Test(description = "Verify DELETE operation is not allowed on operations endpoint", priority = 4)
    public void testInvalidHttpMethodOnOperations() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .delete(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code indicates method not allowed
        assertTrue(response.getStatusCode() == 405 || response.getStatusCode() == 404,
                "Expected HTTP 405 Method Not Allowed or 404, got: " + response.getStatusCode());
    }

    /**
     * Positive Test: Verify operations can be retrieved with pagination parameters.
     * Expected: HTTP 200 OK.
     */
    @Test(description = "Verify operations retrieval with pagination", priority = 4)
    public void testGetOperationsWithPagination() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get(ApiEndPoints.OPERATIONS)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Expected HTTP 200 OK");
    }
}
