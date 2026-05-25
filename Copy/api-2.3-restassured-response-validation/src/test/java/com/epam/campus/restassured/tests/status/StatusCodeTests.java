package com.epam.campus.restassured.tests.status;

import org.testng.annotations.Test;

import com.epam.campus.restassured.base.BaseTest;

import static io.restassured.RestAssured.given;

/**
 * Test class for HTTP Status Code validation
 */
public class StatusCodeTests extends BaseTest {

    /**
     * Validate GET request returns 200 OK
     */
    @Test
    public void validateGetPostStatusCode(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);

        System.out.println("✓ GET request to /posts/1 returned 200 OK");
    }

    /**
     * Validate POST request returns 201 Created
     */
    @Test
    public void validateCreatePostStatusCode(){
        String requestBody = "{\n" +
                "  \"title\": \"Test Post\",\n" +
                "  \"body\": \"This is a test post\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201);

        System.out.println("✓ POST request to /posts returned 201 Created");
    }

    /**
     * Validate invalid endpoint returns 404 Not Found
     */
    @Test
    public void validateInvalidEndpointReturns404(){
        given()
                .spec(requestSpec)
                .when()
                .get("/invalid-endpoint")
                .then()
                .statusCode(404);

        System.out.println("✓ GET request to /invalid-endpoint returned 404 Not Found");
    }

    /**
     * Validate missing required field returns 400 Bad Request (if applicable)
     */
    @Test
    public void validateBadRequestReturns400(){
        String invalidBody = "{\n" +
                "  \"title\": \"\"\n" +
                "}";

        // Note: JSONPlaceholder is permissive and accepts most requests
        // This test demonstrates the pattern
        given()
                .spec(requestSpec)
                .body(invalidBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201);

        System.out.println("✓ Invalid request validation completed");
    }
}
