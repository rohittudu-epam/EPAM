package com.epam.campus;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.epam.campus.Base.BaseTest;
import com.epam.campus.constants.ApiEndPoints;
import com.epam.campus.pojo.AuthRequest;
import com.epam.campus.pojo.AuthResponse;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Test class for Authentication API endpoints.
 * Tests the POST /api/authenticate endpoint for user authentication.
 */
public class AuthenticationTests extends BaseTest {

    /**
     * Positive Test: Verify successful authentication with valid admin credentials.
     * Expected: HTTP 200 OK with a valid JWT token in response.
     */
    @Test(description = "Verify successful authentication with valid credentials")
    public void testAuthenticateWithValidCredentials() {
        AuthRequest authRequest = new AuthRequest(ADMIN_USERNAME, ADMIN_PASSWORD, false);

        Response response = given()
                .spec(requestSpec)
                .body(authRequest)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Expected HTTP 200 OK");
        
        // Verify response contains JWT token
        AuthResponse authResponse = response.as(AuthResponse.class);
        assertNotNull(authResponse.getIdToken(), "JWT token should not be null");
        assertFalse(authResponse.getIdToken().isEmpty(), "JWT token should not be empty");
    }

    /**
     * Positive Test: Verify authentication with rememberMe flag set to true.
     * Expected: HTTP 200 OK with a valid JWT token in response.
     */
    @Test(description = "Verify authentication with rememberMe flag enabled")
    public void testAuthenticateWithRememberMe() {
        AuthRequest authRequest = new AuthRequest(ADMIN_USERNAME, ADMIN_PASSWORD, true);

        Response response = given()
                .spec(requestSpec)
                .body(authRequest)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Expected HTTP 200 OK");
        
        // Verify response contains JWT token
        AuthResponse authResponse = response.as(AuthResponse.class);
        assertNotNull(authResponse.getIdToken(), "JWT token should not be null");
    }

    /**
     * Negative Test: Verify authentication fails with invalid password.
     * Expected: HTTP 401 Unauthorized.
     */
    @Test(description = "Verify authentication fails with invalid password")
    public void testAuthenticateWithInvalidPassword() {
        AuthRequest authRequest = new AuthRequest(ADMIN_USERNAME, "wrongpassword", false);

        Response response = given()
                .spec(requestSpec)
                .body(authRequest)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .extract()
                .response();

        // Verify status code indicates authentication failure
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Negative Test: Verify authentication fails with invalid username.
     * Expected: HTTP 401 Unauthorized.
     */
    @Test(description = "Verify authentication fails with invalid username")
    public void testAuthenticateWithInvalidUsername() {
        AuthRequest authRequest = new AuthRequest("nonexistentuser", ADMIN_PASSWORD, false);

        Response response = given()
                .spec(requestSpec)
                .body(authRequest)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .extract()
                .response();

        // Verify status code indicates authentication failure
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Negative Test: Verify authentication fails with empty credentials.
     * Expected: HTTP 400 Bad Request or 401 Unauthorized.
     */
    @Test(description = "Verify authentication fails with empty credentials")
    public void testAuthenticateWithEmptyCredentials() {
        AuthRequest authRequest = new AuthRequest("", "", false);

        Response response = given()
                .spec(requestSpec)
                .body(authRequest)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .extract()
                .response();

        // Verify status code indicates failure (400 or 401)
        assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 401,
                "Expected HTTP 400 Bad Request or 401 Unauthorized, got: " + response.getStatusCode());
    }

    /**
     * Negative Test: Verify authentication fails with null values.
     * Expected: HTTP 400 Bad Request or 401 Unauthorized.
     */
    @Test(description = "Verify authentication fails with missing required fields")
    public void testAuthenticateWithMissingFields() {
        // Send request with empty body
        Response response = given()
                .spec(requestSpec)
                .body("{}")
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .extract()
                .response();

        // Verify status code indicates failure
        assertTrue(response.getStatusCode() >= 400,
                "Expected HTTP 4xx error, got: " + response.getStatusCode());
    }
}
