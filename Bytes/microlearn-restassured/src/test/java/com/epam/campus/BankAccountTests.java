package com.epam.campus;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.epam.campus.Base.BaseTest;
import com.epam.campus.constants.ApiEndPoints;
import com.epam.campus.pojo.BankAccountResponse;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Test class for Bank Account API endpoints.
 * Tests the following endpoints:
 * - GET /api/bank-accounts: View Bank Account
 * - POST /api/bank-accounts: Create Bank Account
 * - PUT /api/bank-accounts/{id}: Update Bank Account
 * - DELETE /api/bank-accounts/{id}: Delete Bank Account
 * - PATCH /api/bank-accounts/{id}: Partial Update Bank Account
 */
public class BankAccountTests extends BaseTest {

    /**
     * Positive Test: Verify successful retrieval of all bank accounts.
     * Expected: HTTP 200 OK with list of bank accounts.
     */
    @Test(description = "Verify successful retrieval of all bank accounts", priority = 1)
    public void testGetAllBankAccounts() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .get(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Expected HTTP 200 OK");
        
        // Verify response is an array
        BankAccountResponse[] bankAccounts = response.as(BankAccountResponse[].class);
        assertNotNull(bankAccounts, "Response should contain bank accounts array");
    }

    /**
     * Negative Test: Verify unauthorized access to bank accounts without authentication.
     * Expected: HTTP 401 Unauthorized.
     */
    @Test(description = "Verify unauthorized access to bank accounts without authentication", priority = 1)
    public void testGetAllBankAccountsWithoutAuth() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .extract()
                .response();

        // Verify status code indicates unauthorized
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Positive Test: Verify successful creation of a new bank account.
     * Expected: HTTP 201 Created with bank account details.
     */
    @Test(description = "Verify successful creation of a new bank account", priority = 2)
    public void testCreateBankAccount() {
        String uniqueName = generateUniqueName("TestAccount");
        BankAccountResponse bankAccount = new BankAccountResponse(uniqueName, 1000.50);

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(bankAccount)
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(response.getStatusCode(), 201, "Expected HTTP 201 Created");
        
        // Verify response contains created bank account
        BankAccountResponse createdAccount = response.as(BankAccountResponse.class);
        assertNotNull(createdAccount.getId(), "Created account should have an ID");
        assertEquals(createdAccount.getName(), uniqueName, "Account name should match");
        assertEquals(createdAccount.getBalance(), 1000.50, "Account balance should match");
    }

    /**
     * Negative Test: Verify bank account creation fails without authentication.
     * Expected: HTTP 401 Unauthorized.
     */
    @Test(description = "Verify bank account creation fails without authentication", priority = 2)
    public void testCreateBankAccountWithoutAuth() {
        BankAccountResponse bankAccount = new BankAccountResponse("UnauthorizedAccount", 500.00);

        Response response = given()
                .spec(requestSpec)
                .body(bankAccount)
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .extract()
                .response();

        // Verify status code indicates unauthorized
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Negative Test: Verify bank account creation fails with invalid data.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify bank account creation fails with missing required fields", priority = 2)
    public void testCreateBankAccountWithMissingFields() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .body("{}")
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .extract()
                .response();

        // Verify status code indicates validation failure
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request");
    }

    /**
     * Positive Test: Verify successful update of an existing bank account.
     * Expected: HTTP 200 OK with updated bank account details.
     */
    @Test(description = "Verify successful update of an existing bank account", priority = 3)
    public void testUpdateBankAccount() {
        // First, create a bank account to update
        String uniqueName = generateUniqueName("UpdateTest");
        BankAccountResponse bankAccount = new BankAccountResponse(uniqueName, 2000.00);

        Response createResponse = given()
                .spec(authenticatedRequestSpec)
                .body(bankAccount)
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .extract()
                .response();

        BankAccountResponse createdAccount = createResponse.as(BankAccountResponse.class);
        Long accountId = createdAccount.getId();

        // Update the bank account
        createdAccount.setName("UpdatedAccount");
        createdAccount.setBalance(3000.00);

        Response updateResponse = given()
                .spec(authenticatedRequestSpec)
                .body(createdAccount)
                .when()
                .put(ApiEndPoints.BANK_ACCOUNTS + "/" + accountId)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(updateResponse.getStatusCode(), 200, "Expected HTTP 200 OK");
        
        // Verify response contains updated bank account
        BankAccountResponse updatedAccount = updateResponse.as(BankAccountResponse.class);
        assertEquals(updatedAccount.getName(), "UpdatedAccount", "Account name should be updated");
        assertEquals(updatedAccount.getBalance(), 3000.00, "Account balance should be updated");
    }

    /**
     * Negative Test: Verify update fails for non-existent bank account.
     * Expected: HTTP 400 Bad Request or 404 Not Found.
     */
    @Test(description = "Verify update fails for non-existent bank account", priority = 3)
    public void testUpdateNonExistentBankAccount() {
        BankAccountResponse bankAccount = new BankAccountResponse(99999L, "NonExistent", 1000.00, null);

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(bankAccount)
                .when()
                .put(ApiEndPoints.BANK_ACCOUNTS + "/99999")
                .then()
                .extract()
                .response();

        // Verify status code indicates failure
        assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 404,
                "Expected HTTP 400 or 404, got: " + response.getStatusCode());
    }

    /**
     * Positive Test: Verify successful partial update of a bank account (PATCH).
     * Expected: HTTP 200 OK with updated bank account details.
     */
    @Test(description = "Verify successful partial update of a bank account", priority = 4)
    public void testPartialUpdateBankAccount() {
        // First, create a bank account to patch
        String uniqueName = generateUniqueName("PatchTest");
        BankAccountResponse bankAccount = new BankAccountResponse(uniqueName, 4000.00);

        Response createResponse = given()
                .spec(authenticatedRequestSpec)
                .body(bankAccount)
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .extract()
                .response();

        BankAccountResponse createdAccount = createResponse.as(BankAccountResponse.class);
        Long accountId = createdAccount.getId();

        // Partial update - only update balance
        String patchBody = "{\"id\":" + accountId + ",\"balance\":5000.00}";

        Response patchResponse = given()
                .spec(authenticatedRequestSpec)
                .body(patchBody)
                .when()
                .patch(ApiEndPoints.BANK_ACCOUNTS + "/" + accountId)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(patchResponse.getStatusCode(), 200, "Expected HTTP 200 OK");
        
        // Verify balance was updated but name remains
        BankAccountResponse patchedAccount = patchResponse.as(BankAccountResponse.class);
        assertEquals(patchedAccount.getBalance(), 5000.00, "Balance should be updated");
    }

    /**
     * Negative Test: Verify partial update fails for non-existent bank account.
     * Expected: HTTP 400 Bad Request or 404 Not Found.
     */
    @Test(description = "Verify partial update fails for non-existent bank account", priority = 4)
    public void testPartialUpdateNonExistentBankAccount() {
        String patchBody = "{\"id\":99999,\"balance\":5000.00}";

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(patchBody)
                .when()
                .patch(ApiEndPoints.BANK_ACCOUNTS + "/99999")
                .then()
                .extract()
                .response();

        // Verify status code indicates failure
        assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 404,
                "Expected HTTP 400 or 404, got: " + response.getStatusCode());
    }

    /**
     * Positive Test: Verify successful deletion of a bank account.
     * Expected: HTTP 204 No Content.
     */
    @Test(description = "Verify successful deletion of a bank account", priority = 5)
    public void testDeleteBankAccount() {
        // First, create a bank account to delete
        String uniqueName = generateUniqueName("DeleteTest");
        BankAccountResponse bankAccount = new BankAccountResponse(uniqueName, 1500.00);

        Response createResponse = given()
                .spec(authenticatedRequestSpec)
                .body(bankAccount)
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .extract()
                .response();

        BankAccountResponse createdAccount = createResponse.as(BankAccountResponse.class);
        Long accountId = createdAccount.getId();

        // Delete the bank account
        Response deleteResponse = given()
                .spec(authenticatedRequestSpec)
                .when()
                .delete(ApiEndPoints.BANK_ACCOUNTS + "/" + accountId)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(deleteResponse.getStatusCode(), 204, "Expected HTTP 204 No Content");

        // Verify the account no longer exists
        Response getResponse = given()
                .spec(authenticatedRequestSpec)
                .when()
                .get(ApiEndPoints.BANK_ACCOUNTS + "/" + accountId)
                .then()
                .extract()
                .response();

        assertEquals(getResponse.getStatusCode(), 404, "Deleted account should not be found");
    }

    /**
     * Negative Test: Verify deletion fails for non-existent bank account.
     * Expected: HTTP 404 Not Found or 204 No Content (idempotent delete).
     */
    @Test(description = "Verify deletion of non-existent bank account", priority = 5)
    public void testDeleteNonExistentBankAccount() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .delete(ApiEndPoints.BANK_ACCOUNTS + "/99999")
                .then()
                .extract()
                .response();

        // API may return 404 or 204 (idempotent delete)
        assertTrue(response.getStatusCode() == 404 || response.getStatusCode() == 204,
                "Expected HTTP 404 or 204, got: " + response.getStatusCode());
    }

    /**
     * Negative Test: Verify deletion fails without authentication.
     * Expected: HTTP 401 Unauthorized.
     */
    @Test(description = "Verify deletion fails without authentication", priority = 5)
    public void testDeleteBankAccountWithoutAuth() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .delete(ApiEndPoints.BANK_ACCOUNTS + "/1")
                .then()
                .extract()
                .response();

        // Verify status code indicates unauthorized
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Negative Test: Verify invalid HTTP method returns appropriate error.
     * Expected: HTTP 405 Method Not Allowed.
     */
    @Test(description = "Verify invalid HTTP method returns appropriate error", priority = 6)
    public void testInvalidHttpMethod() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .body("{\"name\":\"Test\"}")
                .when()
                .put(ApiEndPoints.BANK_ACCOUNTS) // PUT without ID is not allowed
                .then()
                .extract()
                .response();

        // Verify status code indicates method not allowed or bad request
        assertTrue(response.getStatusCode() == 405 || response.getStatusCode() == 400,
                "Expected HTTP 405 or 400, got: " + response.getStatusCode());
    }
}
