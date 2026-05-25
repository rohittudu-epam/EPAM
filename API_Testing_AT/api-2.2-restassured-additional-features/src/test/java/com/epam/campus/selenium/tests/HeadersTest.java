package com.epam.campus.selenium.tests;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HeadersTest {

    private final String baseUrl = "https://jsonplaceholder.typicode.com";

    // Add Custom Headers and validate response headers
    @Test
    public void testAddCustomHeaders() {
        Response response = given()
                .baseUri(baseUrl)
                .header("X-Custom-Header", "CustomValue")
                .header("X-Correlation-ID", "12345")
                .contentType("application/json")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .extract()
                .response();

        System.out.println("Response Headers: " + response.headers());
    }

    // Validate Response Headers
    @Test
    public void testValidateResponseHeaders() {
        given()
                .baseUri(baseUrl)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .header("Cache-Control", notNullValue());
    }

    // Extract header values
    @Test
    public void testExtractHeaderValues() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Extract individual headers
        String contentType = response.getHeader("Content-Type");
        String cacheControl = response.getHeader("Cache-Control");

        System.out.println("Content-Type: " + contentType);
        System.out.println("Cache-Control: " + cacheControl);

        // Get all headers
        Headers headers = response.getHeaders();
        headers.forEach(header -> System.out.println(header.getName() + ": " + header.getValue()));
    }

    // Test with custom headers and verify they are sent
    @Test
    public void testCustomHeadersWithHttpBin() {
        Response response = given()
                .baseUri("https://httpbin.org")
                .header("X-Custom-Header", "TestValue")
                .header("X-Request-ID", "REQ-12345")
                .when()
                .get("/headers")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Request Headers Echo: " + response.asPrettyString());
    }
}

