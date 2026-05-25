package com.epam.campus;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.campus.Base.BaseTest;
import com.epam.campus.constants.ApiEndPoints;
import com.epam.campus.pojo.Request.RequestBankAccount;
import com.epam.campus.pojo.Response.BankAccountResponse;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class BankAccountTests extends BaseTest {

    private int id;

    @Test(description = "Verify successful retrieval of all bank accounts", priority = 1)
    public void testGetAllBankAccounts() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .get(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .statusCode(200)
                .extract().response();

        BankAccountResponse[] bankAccounts = response.as(BankAccountResponse[].class);
        Assert.assertNotNull(bankAccounts, "Bank accounts should not be null");
    }

    @Test(description = "Verify unauthorized access denied for bank accounts", priority = 1)
    public void testGetAllBankAccountsWithoutAuth() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(ApiEndPoints.BANK_ACCOUNTS)
                .then()
                .statusCode(401)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    @Test(description = "Verify successful bank account creation", priority = 2)
    public void testCreateBankAccount() {
        RequestBankAccount requestBankAccount = new RequestBankAccount(
                "Jackson",
                1350,
                null
        );

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(requestBankAccount)
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS);

        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 201,
                "Expected HTTP 200 or 201");
        id = response.jsonPath().get("id");
        Assert.assertTrue(id > 0, "Created account should have a valid ID");
    }

    @Test(description = "Verify bank account creation fails without auth", priority = 2)
    public void testCreateBankAccountWithoutAuth() {
        RequestBankAccount requestBankAccount = new RequestBankAccount(
                "Jackson",
                1350,
                null
        );

        Response response = given()
                .spec(requestSpec)
                .body(requestBankAccount)
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS);

        Assert.assertEquals(response.statusCode(), 401, "Expected HTTP 401 Unauthorized");
    }


    @Test(description = "Verify bank account creation fails with missing fields", priority = 3)
    public void testCreateBankAccountWithMissingFields() {
        RequestBankAccount requestBankAccount = new RequestBankAccount();

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(requestBankAccount)
                .when()
                .post(ApiEndPoints.BANK_ACCOUNTS);

        Assert.assertTrue(response.statusCode() >= 400,
                "Expected HTTP 4xx or 5xx error for missing fields");
    }

    @Test(description = "Verify successful bank account update", priority = 4)
    public void testUpdateBankAccounts() {
        String updateBankDetail = String.format("""
                {
                    "id": "%d",
                    "name": "Wolf",
                    "balance": 9999
                }
                """, id);
        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(updateBankDetail)
                .when()
                .put(ApiEndPoints.BANK_ACCOUNTS + String.format("/%d", id));

        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 204,
                "Expected HTTP 200 or 204 for successful update");
    }

    @Test(description = "Verify successful bank account deletion", priority = 5)
    public void testDeleteBankAccount() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .delete(ApiEndPoints.BANK_ACCOUNTS + String.format("/%d", id));

        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 204,
                "Expected HTTP 200 or 204 for successful deletion");
    }
}
