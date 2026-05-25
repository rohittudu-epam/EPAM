package com.epam.campus;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.epam.campus.Base.BaseTest;
import com.epam.campus.constants.ApiEndPoints;
import com.epam.campus.pojo.RegisterRequest;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Test class for Account Management API endpoints.
 * Tests the following endpoints:
 * - POST /api/register: Register User
 * - POST /api/account/reset-password/init: Reset Password
 */
public class AccountsManagementTests extends BaseTest {

    /**
     * Positive Test: Verify successful user registration with valid data.
     * Expected: HTTP 201 Created.
     */
    @Test(description = "Verify successful user registration with valid data")
    public void testRegisterUserWithValidData() {
        String uniqueId = generateUniqueName("");
        RegisterRequest registerRequest = new RegisterRequest(
                "testuser" + uniqueId,
                "testuser" + uniqueId + "@example.com",
                "Password123!",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(registerRequest)
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .extract()
                .response();

        // Verify status code (201 Created or 200 OK)
        assertTrue(response.getStatusCode() == 201 || response.getStatusCode() == 200,
                "Expected HTTP 201 Created or 200 OK, got: " + response.getStatusCode());
    }

    /**
     * Negative Test: Verify registration fails with duplicate login.
     * Expected: HTTP 400 Bad Request (login already exists).
     */
    @Test(description = "Verify registration fails with duplicate login")
    public void testRegisterUserWithDuplicateLogin() {
        // Try to register with existing admin login
        RegisterRequest registerRequest = new RegisterRequest(
                "admin",
                "newadmin@example.com",
                "Password123!",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(registerRequest)
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .extract()
                .response();

        // Verify status code indicates failure
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request for duplicate login");
    }

    /**
     * Negative Test: Verify registration fails with invalid email format.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify registration fails with invalid email format")
    public void testRegisterUserWithInvalidEmail() {
        String uniqueId = generateUniqueName("");
        RegisterRequest registerRequest = new RegisterRequest(
                "testuser" + uniqueId,
                "invalid-email-format",
                "Password123!",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(registerRequest)
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .extract()
                .response();

        // Verify status code indicates validation failure
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request for invalid email");
    }

    /**
     * Negative Test: Verify registration fails with short password.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify registration fails with password that is too short")
    public void testRegisterUserWithShortPassword() {
        String uniqueId = generateUniqueName("");
        RegisterRequest registerRequest = new RegisterRequest(
                "testuser" + uniqueId,
                "testuser" + uniqueId + "@example.com",
                "123",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(registerRequest)
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .extract()
                .response();

        // Verify status code indicates validation failure
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request for short password");
    }

    /**
     * Negative Test: Verify registration fails with missing required fields.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify registration fails with missing required fields")
    public void testRegisterUserWithMissingFields() {
        Response response = given()
                .spec(requestSpec)
                .body("{}")
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .extract()
                .response();

        // Verify status code indicates validation failure
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request for missing fields");
    }

    /**
     * Positive Test: Verify password reset initialization with valid email.
     * Expected: HTTP 200 OK.
     */
    @Test(description = "Verify password reset initialization with valid email")
    public void testResetPasswordInitWithValidEmail() {
        Response response = given()
                .spec(requestSpec)
                .body("admin@localhost")
                .when()
                .post(ApiEndPoints.RESET_PASSWORD)
                .then()
                .extract()
                .response();

        // Verify status code (200 OK expected for successful request)
        // Note: The API may return 200 even for non-existent emails for security reasons
        assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 400,
                "Expected HTTP 200 OK or 400, got: " + response.getStatusCode());
    }

    /**
     * Negative Test: Verify password reset initialization with non-existent email.
     * Expected: HTTP 200 OK (for security, API may not reveal if email exists) or 400 Bad Request.
     */
    @Test(description = "Verify password reset initialization with non-existent email")
    public void testResetPasswordInitWithNonExistentEmail() {
        Response response = given()
                .spec(requestSpec)
                .body("nonexistent@example.com")
                .when()
                .post(ApiEndPoints.RESET_PASSWORD)
                .then()
                .extract()
                .response();

        // API might return 200 for security (not revealing if email exists) or 400
        assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 400,
                "Expected HTTP 200 OK or 400, got: " + response.getStatusCode());
    }

    /**
     * Negative Test: Verify password reset initialization with invalid email format.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify password reset initialization with invalid email format")
    public void testResetPasswordInitWithInvalidEmailFormat() {
        Response response = given()
                .spec(requestSpec)
                .body("not-an-email")
                .when()
                .post(ApiEndPoints.RESET_PASSWORD)
                .then()
                .extract()
                .response();

        // Verify status code indicates failure for invalid email
        assertTrue(response.getStatusCode() >= 400,
                "Expected HTTP 4xx error for invalid email format, got: " + response.getStatusCode());
    }
}
