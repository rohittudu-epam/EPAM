package com.epam.campus.selenium.tests;

import io.restassured.filter.session.SessionFilter;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CookiesTest {

    private final String baseUrl = "https://postman-echo.com";

    @Test
    public void testSendCookies() {

        Response response = given()
                .baseUri(baseUrl)
                .cookie("session_id", "abc123def456")
                .cookie("user_token", "token_xyz789")
                .when()
                .get("/cookies")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Response with Cookies Sent:");
        System.out.println(response.asPrettyString());
    }

    @Test
    public void testValidateResponseCookies() {

        Response response = given()
                .baseUri(baseUrl)
                .redirects().follow(false)   // 🔥 VERY IMPORTANT
                .when()
                .get("/cookies/set?test_cookie=test_value")
                .then()
                .statusCode(302)   // 🔥 It returns 302, not 200
                .extract()
                .response();

        System.out.println("Set-Cookie Header: " + response.getHeader("Set-Cookie"));
        System.out.println("Extracted Cookie: " + response.getCookie("test_cookie"));
    }

    @Test
    public void testExtractCookieValues() {

        Response response = given()
                .baseUri(baseUrl)
                .redirects().follow(false)   // 🔥 required
                .when()
                .get("/cookies/set?session=session123&tracking=track456")
                .then()
                .statusCode(302)
                .extract()
                .response();

        String sessionCookie = response.getCookie("session");
        String trackingCookie = response.getCookie("tracking");

        System.out.println("Session Cookie: " + sessionCookie);
        System.out.println("Tracking Cookie: " + trackingCookie);

        Cookies cookies = response.getDetailedCookies();
        cookies.forEach(cookie ->
                System.out.println(cookie.getName() + ": " + cookie.getValue()));
    }

    @Test
    public void testMultipleCookiesFlow() {

        SessionFilter session = new SessionFilter();

        // First request sets cookies
        given()
                .baseUri(baseUrl)
                .filter(session)
                .redirects().follow(false)
                .when()
                .get("/cookies/set?jsessionid=SESSION123&user_pref=dark_mode")
                .then()
                .statusCode(302);

        // Second request automatically sends cookies
        Response response = given()
                .baseUri(baseUrl)
                .filter(session)
                .redirects().follow(false)
                .when()
                .get("/cookies")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Cookies Echoed Back:");
        System.out.println(response.asPrettyString());
    }

    @Test
    public void testValidateCookieAttributes() {

        Response response = given()
                .baseUri(baseUrl)
                .redirects().follow(false)
                .when()
                .get("/cookies/set?auth_token=secure_token_xyz")
                .then()
                .statusCode(302)
                .extract()
                .response();

        String authToken = response.getCookie("auth_token");

        if (authToken != null && !authToken.isEmpty()) {
            System.out.println("Cookie validation passed: auth_token cookie is set");
        } else {
            System.out.println("Cookie validation failed");
        }
    }
}
