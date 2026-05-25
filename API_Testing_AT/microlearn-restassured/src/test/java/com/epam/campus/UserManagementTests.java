package com.epam.campus;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.epam.campus.Base.BaseTest;
import com.epam.campus.constants.ApiEndPoints;
import com.epam.campus.pojo.Response.UserResponse;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class UserManagementTests extends BaseTest {

    @Test(description = "Verify Successful retrieval of all users", priority = 1)
    public void testGetAllUsers() {
        Response response = given()
                .spec(authenticatedRequestSpec)
                .when()
                .get(ApiEndPoints.USERS)
                .then()
                .statusCode(200)
                .extract().response();

        UserResponse[] userResponses = response.as(UserResponse[].class);
        Assert.assertTrue(userResponses.length > 0, "Users shouldn't be Empty");
    }

    @Test(description = "Verify unauthorized access denied", priority = 1)
    public void testGetAllUsersWithoutAuth() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get(ApiEndPoints.USERS)
                .then()
                .statusCode(401)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    @Test(description = "Verify successful user creation", priority = 1)
    public void testCreateUser() {
        String uniqueId = generateUniqueName("");
        Map<String, String> createUserBody = new HashMap<>();
        createUserBody.put("login", "raneTraye" + uniqueId);
        createUserBody.put("firstName", "Rane");
        createUserBody.put("lastName", "Traye");
        createUserBody.put("email", "ranetraye" + uniqueId + "@example.com");
        createUserBody.put("langKey", "en");

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(createUserBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .statusCode(201)
                .extract().response();

        Assert.assertEquals(response.statusCode(), 201, "User creation should return HTTP 201");
    }

    @Test(description = "Verify duplicate login rejected", priority = 2)
    public void testCreateUserWithDuplicateLogin() {
        Map<String, String> createUserBody = new HashMap<>();
        createUserBody.put("login", "admin");
        createUserBody.put("firstName", "Admin");
        createUserBody.put("lastName", "User");
        createUserBody.put("email", "admin@example.com");
        createUserBody.put("langKey", "en");

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(createUserBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .statusCode(400)
                .extract().response();

        Assert.assertEquals(response.statusCode(), 400, "Duplicate login should return HTTP 400");
    }

    @Test(description = "Verify user creation fails without auth", priority = 2)
    public void testCreateUserWithoutAuth() {
        Map<String, String> createUserBody = new HashMap<>();
        createUserBody.put("login", "testUser");
        createUserBody.put("firstName", "Test");
        createUserBody.put("lastName", "User");
        createUserBody.put("email", "testuser@example.com");
        createUserBody.put("langKey", "en");

        Response response = given()
                .spec(requestSpec)
                .body(createUserBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .statusCode(401)
                .extract().response();

        Assert.assertEquals(response.statusCode(), 401, "Expected HTTP 401 Unauthorized");
    }

    @Test(description = "Verify invalid email rejected", priority = 2)
    public void testCreateUserWithInvalidEmail() {
        Map<String, String> createUserBody = new HashMap<>();
        createUserBody.put("login", generateUniqueName("testUser"));
        createUserBody.put("firstName", "Test");
        createUserBody.put("lastName", "User");
        createUserBody.put("email", "invalidemail");
        createUserBody.put("langKey", "en");

        Response response = given()
                .spec(authenticatedRequestSpec)
                .body(createUserBody)
                .when()
                .post(ApiEndPoints.USERS)
                .then()
                .statusCode(400)
                .extract().response();

        Assert.assertEquals(response.statusCode(), 400, "Invalid email should return HTTP 400");
    }

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

        assertEquals(updateResponse.getStatusCode(), 200, "Expected HTTP 200 OK");

        UserResponse updatedUser = updateResponse.as(UserResponse.class);
        assertEquals(updatedUser.getFirstName(), "Updated", "First name should be updated");
        assertEquals(updatedUser.getLastName(), "UserUpdated", "Last name should be updated");
    }



}
