package com.epam.campus.restassured.tests.headers;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.epam.campus.restassured.base.BaseTest;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Test class for Response Header validation
 */
public class HeaderValidationTests extends BaseTest {

    /**
     * Validate Content-Type header is present and correct
     */
    @Test
    public void validateContentTypeHeader(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .extract()
                .response();

        String contentType = response.getHeader("Content-Type");
        assertNotNull(contentType, "Content-Type header should not be null");
        assertTrue(contentType.contains("application/json"), "Content-Type should be application/json");

        System.out.println("✓ Content-Type header is correct: " + contentType);
    }

    /**
     * Validate Cache-Control header is present
     */
    @Test
    public void validateCacheControlHeader(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .extract()
                .response();

        String cacheControl = response.getHeader("Cache-Control");
        assertNotNull(cacheControl, "Cache-Control header should be present");

        System.out.println("✓ Cache-Control header is present: " + cacheControl);
    }

    /**
     * Validate Content-Length header exists
     */
    @Test
    public void validateContentLengthHeaderExists(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .extract()
                .response();

        String contentLength = response.getHeader("Content-Length");
        assertNotNull(contentLength, "Content-Length header should be present");
        assertTrue(!contentLength.isEmpty(), "Content-Length should not be empty");

        System.out.println("✓ Content-Length header is present: " + contentLength);
    }

    /**
     * Validate all expected headers are present
     */
    @Test
    public void validateAllHeadersPresent(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .extract()
                .response();

        String contentType = response.getHeader("Content-Type");
        String contentLength = response.getHeader("Content-Length");
        String cacheControl = response.getHeader("Cache-Control");

        assertNotNull(contentType, "Content-Type header should be present");
        assertNotNull(contentLength, "Content-Length header should be present");
        assertNotNull(cacheControl, "Cache-Control header should be present");

        System.out.println("✓ All expected headers are present");
        System.out.println("  Content-Type: " + contentType);
        System.out.println("  Content-Length: " + contentLength);
        System.out.println("  Cache-Control: " + cacheControl);
    }

    /**
     * Validate specific header value
     */
    @Test
    public void validateSpecificHeaderValue(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .header("Content-Type", org.hamcrest.Matchers.containsString("application/json"));

        System.out.println("✓ Header Content-Type validation passed");
    }
}
