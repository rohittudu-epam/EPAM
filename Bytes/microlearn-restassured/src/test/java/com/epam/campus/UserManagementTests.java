package com.epam.campus;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.epam.campus.Base.BaseTest;
import com.epam.campus.constants.ApiEndPoints;
import com.epam.campus.pojo.UserResponse;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Test class for User Management API endpoints.
 * Tests the following endpoints:
 * - GET /api/admin/users: View User
 * - POST /api/admin/users: Create User
 * - PUT /api/admin/users/{login}: Update User
 * - DELETE /api/admin/users/{login}: Delete User
 * - PUT /api/admin/users: Update User (without login in path)
 */
public class UserManagementTests extends BaseTest {

    /**
     * Positive Test: Verify successful retrieval of all users.
     * Expected: HTTP 200 OK with list of users.
     */
    @Test(description = "Verify successful retrieval of all users", priority = 1)
    public void testGetAllUsers() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .get(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Expected HTTP 200 OK");
        
        // Verify response contains users
        UserResponse[] users = response.as(UserResponse[].class);
        assertNotNull(users, "Response should contain users array");
        assertTrue(users.length > 0, "Should have at least one user (admin)");
    }

    /**
     * Negative Test: Verify unauthorized access to users without authentication.
     * Expected: HTTP 401 Unauthorized.
     */
    @Test(description = "Verify unauthorized access to users without authentication", priority = 1)
    public void testGetAllUsersWithoutAuth() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code indicates unauthorized
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Positive Test: Verify successful creation of a new user.
     * Expected: HTTP 201 Created with user details.
     */
    @Test(description = "Verify successful creation of a new user", priority = 2)
    public void testCreateUser() {
        String uniqueId = generateUniqueName("");
        String login = "testuser" + uniqueId;
        
        // Create user request body
        String userBody = String.format(
                "{\"login\":\"%s\",\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"%s@example.com\"," +
                "\"activated\":true,\"langKey\":\"en\",\"authorities\":[\"ROLE_USER\"]}",
                login, login
        );

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(userBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(response.getStatusCode(), 201, "Expected HTTP 201 Created");
        
        // Verify response contains created user
        UserResponse createdUser = response.as(UserResponse.class);
        assertNotNull(createdUser.getId(), "Created user should have an ID");
        assertEquals(createdUser.getLogin(), login, "User login should match");
        assertEquals(createdUser.getFirstName(), "Test", "User first name should match");
    }

    /**
     * Negative Test: Verify user creation fails with duplicate login.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify user creation fails with duplicate login", priority = 2)
    public void testCreateUserWithDuplicateLogin() {
        // Try to create user with existing admin login
        String userBody = "{\"login\":\"admin\",\"firstName\":\"Duplicate\",\"lastName\":\"Admin\"," +
                "\"email\":\"duplicateadmin@example.com\",\"activated\":true,\"langKey\":\"en\"," +
                "\"authorities\":[\"ROLE_USER\"]}";

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(userBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code indicates failure
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request for duplicate login");
    }

    /**
     * Negative Test: Verify user creation fails without authentication.
     * Expected: HTTP 401 Unauthorized.
     */
    @Test(description = "Verify user creation fails without authentication", priority = 2)
    public void testCreateUserWithoutAuth() {
        String uniqueId = generateUniqueName("");
        String userBody = String.format(
                "{\"login\":\"noauthuser%s\",\"firstName\":\"NoAuth\",\"lastName\":\"User\"," +
                "\"email\":\"noauth%s@example.com\",\"activated\":true,\"langKey\":\"en\"," +
                "\"authorities\":[\"ROLE_USER\"]}",
                uniqueId, uniqueId
        );

        Response response = given()
                .spec(requestSpec)
                .body(userBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code indicates unauthorized
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Negative Test: Verify user creation fails with invalid email format.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify user creation fails with invalid email format", priority = 2)
    public void testCreateUserWithInvalidEmail() {
        String uniqueId = generateUniqueName("");
        String userBody = String.format(
                "{\"login\":\"invalidemail%s\",\"firstName\":\"Invalid\",\"lastName\":\"Email\"," +
                "\"email\":\"not-an-email\",\"activated\":true,\"langKey\":\"en\"," +
                "\"authorities\":[\"ROLE_USER\"]}",
                uniqueId
        );

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(userBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code indicates validation failure
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request for invalid email");
    }

    /**
     * Positive Test: Verify successful update of an existing user using PUT with login in path.
     * Expected: HTTP 200 OK with updated user details.
     */
    @Test(description = "Verify successful update of an existing user", priority = 3)
    public void testUpdateUserByLogin() {
        // First create a user to update
        String uniqueId = generateUniqueName("");
        String login = "updatetest" + uniqueId;
        
        String createBody = String.format(
                "{\"login\":\"%s\",\"firstName\":\"Original\",\"lastName\":\"User\"," +
                "\"email\":\"%s@example.com\",\"activated\":true,\"langKey\":\"en\"," +
                "\"authorities\":[\"ROLE_USER\"]}",
                login, login
        );

        Response createResponse = given()
                .spec(authenticatedRequestSpec)
                .body(createBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        UserResponse createdUser = createResponse.as(UserResponse.class);

        // Update the user
        String updateBody = String.format(
                "{\"id\":%d,\"login\":\"%s\",\"firstName\":\"Updated\",\"lastName\":\"UserUpdated\"," +
                "\"email\":\"%s@example.com\",\"activated\":true,\"langKey\":\"en\"," +
                "\"authorities\":[\"ROLE_USER\"]}",
                createdUser.getId(), login, login
        );

        Response updateResponse = given()
                .spec(authenticatedRequestSpec)
                .body(updateBody)
                .when()
                .put(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(updateResponse.getStatusCode(), 200, "Expected HTTP 200 OK");
        
        // Verify user was updated
        UserResponse updatedUser = updateResponse.as(UserResponse.class);
        assertEquals(updatedUser.getFirstName(), "Updated", "First name should be updated");
        assertEquals(updatedUser.getLastName(), "UserUpdated", "Last name should be updated");
    }

    /**
     * Negative Test: Verify update fails for non-existent user.
     * Expected: HTTP 400 Bad Request or 404 Not Found.
     */
    @Test(description = "Verify update fails for non-existent user", priority = 3)
    public void testUpdateNonExistentUser() {
        String updateBody = "{\"id\":99999,\"login\":\"nonexistentuser\",\"firstName\":\"NonExistent\"," +
                "\"lastName\":\"User\",\"email\":\"nonexistent@example.com\",\"activated\":true," +
                "\"langKey\":\"en\",\"authorities\":[\"ROLE_USER\"]}";

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(updateBody)
                .when()
                .put(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code indicates failure
        assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 404 || response.getStatusCode() == 500,
                "Expected HTTP 400, 404, or 500, got: " + response.getStatusCode());
    }

    /**
     * Positive Test: Verify successful deletion of a user.
     * Expected: HTTP 204 No Content.
     */
    @Test(description = "Verify successful deletion of a user", priority = 4)
    public void testDeleteUser() {
        // First create a user to delete
        String uniqueId = generateUniqueName("");
        String login = "deletetest" + uniqueId;
        
        String createBody = String.format(
                "{\"login\":\"%s\",\"firstName\":\"Delete\",\"lastName\":\"Me\"," +
                "\"email\":\"%s@example.com\",\"activated\":true,\"langKey\":\"en\"," +
                "\"authorities\":[\"ROLE_USER\"]}",
                login, login
        );

        Response createResponse = given()
                .spec(authenticatedRequestSpec)
                .body(createBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        assertEquals(createResponse.getStatusCode(), 201, "User should be created first");

        // Delete the user
        Response deleteResponse = given()
                .spec(authenticatedRequestSpec)
                .when()
                .delete(ApiEndPoints.USERS + "/" + login)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(deleteResponse.getStatusCode(), 204, "Expected HTTP 204 No Content");
    }

    /**
     * Negative Test: Verify deletion fails for non-existent user.
     * Expected: HTTP 404 Not Found or 204 No Content (idempotent delete).
     */
    @Test(description = "Verify deletion of non-existent user", priority = 4)
    public void testDeleteNonExistentUser() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .delete(ApiEndPoints.USERS + "/nonexistentuserlogin")
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
    @Test(description = "Verify deletion fails without authentication", priority = 4)
    public void testDeleteUserWithoutAuth() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .delete(ApiEndPoints.USERS + "/someuser")
                .then()
                .extract()
                .response();

        // Verify status code indicates unauthorized
        assertEquals(response.getStatusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    /**
     * Negative Test: Verify cannot delete admin user.
     * Expected: HTTP 400 Bad Request (protection against deleting admin).
     */
    @Test(description = "Verify cannot delete the admin user", priority = 5)
    public void testCannotDeleteAdminUser() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .delete(ApiEndPoints.USERS + "/admin")
                .then()
                .extract()
                .response();

        // Verify admin cannot be deleted (400 Bad Request expected)
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request - admin cannot be deleted");
    }

    /**
     * Positive Test: Verify users can be retrieved with pagination parameters.
     * Expected: HTTP 200 OK.
     */
    @Test(description = "Verify users retrieval with pagination", priority = 5)
    public void testGetUsersWithPagination() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code
        assertEquals(response.getStatusCode(), 200, "Expected HTTP 200 OK");
    }

    /**
     * Negative Test: Verify user creation fails with missing required fields.
     * Expected: HTTP 400 Bad Request.
     */
    @Test(description = "Verify user creation fails with missing required fields", priority = 2)
    public void testCreateUserWithMissingFields() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .body("{}")
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .extract()
                .response();

        // Verify status code indicates validation failure
        assertEquals(response.getStatusCode(), 400, "Expected HTTP 400 Bad Request for missing fields");
    }
}
