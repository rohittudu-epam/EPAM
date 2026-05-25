package com.epam.campus;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.campus.Base.BaseTest;
import com.epam.campus.constants.ApiEndPoints;
import com.epam.campus.pojo.Request.AuthRequest;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthenticationTests extends BaseTest {

    @Test(description = "For Valid Login Credentials")
    public void testAuthenticateWithValidCredentials() {
        String loginBody = """
                {
                    "username": "admin",
                    "password": "admin",
                    "rememberMe": "false"
                }
                """;
        Response response = given()
                .body(loginBody)
                .header("Content-Type", "application/json; charset=utf-8")
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .statusCode(200)
                .extract().response();

        String token = response.jsonPath().get("id_token");
        Assert.assertNotNull(token, "Token should not be null");
    }

    @Test(description = "For Valid Login With Remember Me")
    public void testAuthenticateWithRememberMe() {
        String loginBody = """
                {
                    "username": "admin",
                    "password": "admin",
                    "rememberMe": "true"
                }
                """;

        given()
                .body(loginBody)
                .header("Content-Type", "application/json; charset=utf-8")
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .statusCode(200);
    }

    @Test(description = "For Invalid JSON Format")
    public void testAuthenticationWithInvalidJsonFormat() {
        String body = """
                {.
                    "username": "EDITH",
                    "password": "simonriley",
                    "rememberMe": "false"
                }
                """;

        given().body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .statusCode(400);
    }

    @Test(description = "For Invalid Username")
    public void testAuthenticationWithInvalidUsername() {
        String body = """
                {
                    "username": "ghost",
                    "password": "admin",
                    "rememberMe": "false"
                }
                """;
        given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .statusCode(401);
    }

    @Test(description = "For Invalid Password")
    public void testAuthenticateWithInvalidPassword() {
        String body = """
                {
                    "username": "admin",
                    "password": "jarvis",
                    "rememberMe": "false"
                }
                """;
        given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .statusCode(401);
    }

    @Test(description = "For Empty Credentials")
    public void testAuthenticationWithEmptyCredentials() {
        String body = """
                {}
                """;

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .statusCode(400);
    }

    @Test(description = "For Missing Authentication Fields")
    public void testAuthenticateWithMissingFields() {
        AuthRequest missingFieldAuth = new AuthRequest();

        given()
                .spec(authenticatedRequestSpec)
                .body(missingFieldAuth)
                .when()
                .post(ApiEndPoints.AUTH)
                .then()
                .statusCode(400);
    }
}
