package com.epam.campus.restassured.tests.headers;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.epam.campus.restassured.base.BaseTest;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class HeaderValidationTests extends BaseTest {

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

        System.out.println("Content-Type header is correct: " + contentType);
    }

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

        System.out.println("Cache-Control header is present: " + cacheControl);
    }


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
        String cacheControl = response.getHeader("Cache-Control");

        assertNotNull(contentType, "Content-Type header should be present");
        assertNotNull(cacheControl, "Cache-Control header should be present");

        System.out.println("All expected headers are present");
        System.out.println("Content-Type: " + contentType);
        System.out.println("Cache-Control: " + cacheControl);
    }

    @Test
    public void validateSpecificHeaderValue(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .header("Content-Type", org.hamcrest.Matchers.containsString("application/json"));

        System.out.println("Header Content-Type validation passed");
    }
}
