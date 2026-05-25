package com.epam.campus;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.campus.Base.BaseTest;
import com.epam.campus.constants.ApiEndPoints;
import com.epam.campus.pojo.Request.RegisterRequest;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class AccountsManagementTests extends BaseTest {

    @Test
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
                .statusCode(201)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 201, "Error Occurred while creating a new User");

    }

    @Test
    public void testRegisterUserWithDuplicateLogin() {
        RegisterRequest registerRequest = new RegisterRequest(
                "admin",
                "admin@example.com",
                "thisisadmin",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(registerRequest)
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .statusCode(400)
                .extract().response();

        Assert.assertEquals(response.statusCode(), 400,
                "The API should throw Status Code 400: Bad Request");
    }

    @Test
    public void testRegisterUserWithInvalidEmail() {
        RegisterRequest invalidRegisterRequest = new RegisterRequest(
                "invalid",
                "invalid",
                "invalid",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(invalidRegisterRequest)
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .statusCode(400)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 400,
                "The API should throw Status Code 400: Bad Request");
    }

    @Test
    public void testRegisterUserWithShortPassword() {
        RegisterRequest invalidRegisterRequest = new RegisterRequest(
                "shortUser",
                "shortuser@example.com",
                "",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(invalidRegisterRequest)
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .statusCode(400)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 400,
                "The API should throw Status Code 400: Bad Request");
    }

    @Test
    public void testRegisterUserWithMissingFields() {
        RegisterRequest invalidRegisterRequest = new RegisterRequest();

        Response response = given()
                .spec(requestSpec)
                .body(invalidRegisterRequest)
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .statusCode(400)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 400,
                "The API should throw Status Code 400: Bad Request");
    }

    @Test
    public void testResetPasswordInitWithValidEmail() {
        RegisterRequest passResetRequest = new RegisterRequest(
                "reaganblade",
                "reagan@blade.com",
                "newpassword",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(passResetRequest)
                .when()
                .post(ApiEndPoints.RESET_PASSWORD)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200,
                "Password reset should return HTTP 200");
    }

    @Test
    public void testResetPasswordInitWithNonExistentEmail() {
        RegisterRequest invalidResetRequest = new RegisterRequest(
                "shortUser",
                "shortuser@example.com",
                "",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(invalidResetRequest)
                .when()
                .post(ApiEndPoints.REGISTER)
                .then()
                .statusCode(400)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 400,
                "The API should throw Status Code 400: Bad Request");
    }

    @Test
    public void testResetPasswordInitInvalidEmailFormat() {
        RegisterRequest invalidResetRequest = new RegisterRequest(
                "reaganblade",
                "reaganblade",
                "ghost",
                "en"
        );

        Response response = given()
                .spec(requestSpec)
                .body(invalidResetRequest)
                .when()
                .post(ApiEndPoints.RESET_PASSWORD)
                .then()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(response.statusCode(), 200,
                "Reset password should handle invalid email format");
    }
}
