package com.epam.campus.selenium.tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CombinedFlowTest {
    /**
     * Complete flow test
     * Each of the test will be dependent on each other
     * 1. Login
     * 2. Extract Token
     * 3. Pass Token in next Request
     * 4. Add Custom Header
     * 5. Validate Response
     * This shows:
     * ✔ Authentication
     * ✔ Header
     * ✔ Cookie
     * ✔ Data-driven
     * <p>
     * All in one test.
     */

    private String access_token = null;
    private String refresh_token = null;
    private Cookies sessionCookies = null;
    private SessionFilter session = new SessionFilter();

    private final RequestSpecification reqSpec = new RequestSpecBuilder()
            .setBaseUri("https://api.escuelajs.co")
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .build();

    @Test(priority = 1)
    public void authenticateAndExtractTokenAndCookies() {
        String loginBody = """
                {
                    "email": "john@mail.com",
                    "password": "changeme"
                }
                """;

        Response loginResponse = given()
                .spec(reqSpec)
                .body(loginBody)
                .redirects().follow(false)
                .filter(session)
                .when()
                .post("/api/v1/auth/login")
                .then()
                .statusCode(201)
                .extract()
                .response();

        access_token = loginResponse.body().jsonPath().get("access_token");
        refresh_token = loginResponse.body().jsonPath().get("refresh_token");

//        System.out.println(loginResponse.asPrettyString());

        Assert.assertNotNull(access_token);
        Assert.assertNotNull(refresh_token);
    }

    @Test(priority = 2)
    public void accessProtectedEndpointWithToken() {
        Response res = given()
                .spec(reqSpec)
                .header("Authorization", "Bearer " + access_token)
                .header("X-Request-ID", "REQ-Access-0x18")
                .header("X-Client-Type", "API-Test")
                .when()
                .get("/api/v1/auth/profile")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Protected Endpoint access successfully");
        System.out.println("User Profile: " + res.asPrettyString());

        Assert.assertNotNull(res.body().jsonPath().get("id"), "User ID should not be null");
    }

    @Test(priority = 3)
    public void testInvalidTokenHandling() {
        String invalid_token = "invalid_token_x012_abc";

        Response res = given()
                .spec(reqSpec)
                .header("Authorization", "Bearer " + invalid_token)
                .when()
                .get("/api/v1/auth/profile")
                .then()
                .statusCode(401)
                .extract()
                .response();

        System.out.println("Invalid Token Correctly Rejected with 401");
        System.out.println("Error Response: " + res.asPrettyString());
    }

    @Test(priority = 4)
    public void testRefreshAccessToken() {

        String refreshBody = String.format("""
                {
                    "refreshToken": "%s"
                }
                """, refresh_token);

        Response res = given()
                .spec(reqSpec)
                .body(refreshBody)
                .when()
                .post("/api/v1/auth/refresh-token")
                .then()
                .statusCode(201)
                .extract()
                .response();

        String newAccessToken = res.body().jsonPath().get("access_token");
        System.out.println("Token Refreshed Successfully");
        System.out.println("New Access Token: " + newAccessToken);

        Assert.assertNotNull(newAccessToken, "New Access Token should not be null");
    }

    @Test(priority = 5)
    public void completeEndToEndFlow() {
        // Login
        Map<String, String> loginBody = new HashMap<>();
        loginBody.put("email", "john@mail.com");
        loginBody.put("password", "changeme");

        Response loginResponse = given()
                .spec(reqSpec)
                .body(loginBody)
                .when()
                .post("/api/v1/auth/login")
                .then()
                .statusCode(201)
                .extract()
                .response();

        String token = loginResponse.body().jsonPath().get("access_token");
        Cookies cookies = loginResponse.getDetailedCookies();

        Assert.assertNotNull(token);
        Assert.assertNotNull(cookies);

        System.out.println("Login Successful");

        // Access Protected Endpoints with token and custom Headers ("X-Correlation-ID", "X-User-Agent")
        Response endpointResponse = given()
                .spec(reqSpec)
                .header("Authorization", "Bearer " + token)
                .header("X-Correlation-ID", "CORR-X0123BYD")
                .header("X-User-Agent", "RestAssuredTest/1.0")
                .cookies(cookies)
                .when()
                .get("/api/v1/auth/profile")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .extract()
                .response();

        System.out.println("Endpoint Accessed with Token, headers and Cookies");
        System.out.println("Response Validated!");

        // Extract and validate user information
        Integer userId = endpointResponse.jsonPath().get("id");
        String userEmail = endpointResponse.jsonPath().get("email");

        Assert.assertNotNull(userId);
        Assert.assertNotNull(userEmail);

        System.out.println(String.format("User ID: %d", userId));
        System.out.println(String.format("User Email: %s", userEmail));
    }

    @Test(priority = 6)
    public void headersAndResponseValidation(){
        Response res = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("X-Custom-Header", "CustomValue")
                .header("X-Request-ID", "REQ-COMBINED-001")
                .contentType("application/json")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .extract()
                .response();

        Assert.assertNotNull(res.headers());
        Assert.assertNotNull(res.body());

        System.out.println("\nHeaders and Response Validated\n");
        System.out.println("Custom Headers sent successfully");
        System.out.println("✔ Response headers: " + res.getHeaders());
        System.out.println("✔ Response body: " + res.asPrettyString());
    }

    @Test(priority = 7)
    public void cookieManagementFlow(){
        Response setCookieResponse = given()
                .baseUri("https://httpbin.org")
                .redirects().follow(false)
                .when()
                .get("/cookies/set?session_id=SESSION123&user_id=USER967")
                .then()
                .statusCode(302)
                .extract()
                .response();

        Cookies receivedCookies = setCookieResponse.getDetailedCookies();
        Assert.assertNotNull(setCookieResponse.getDetailedCookies());

        System.out.println("Cookies received from server: " + receivedCookies);

        Response getCookiesResponse = given()
                .baseUri("https://httpbin.org")
                .cookies(receivedCookies)
                .when()
                .get("/cookies")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertNotNull(getCookiesResponse.asPrettyString());

        System.out.println("✔ Cookies sent successfully in next request");
        System.out.println("✔ Server echoed cookies: " + getCookiesResponse.asPrettyString());
    }
}
