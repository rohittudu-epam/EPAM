package com.epam.campus.restassured.tests.status;

import org.testng.annotations.Test;
import com.epam.campus.restassured.base.BaseTest;
import static io.restassured.RestAssured.given;

public class StatusCodeTests extends BaseTest {

    @Test
    public void validateGetPostStatusCode(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200);

        System.out.println("TEST: returned 200 OK");
    }

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

        System.out.println("TEST: returned 201 Created");
    }

    @Test
    public void validateInvalidEndpointReturns404(){
        given()
                .spec(requestSpec)
                .when()
                .get("/invalid-endpoint")
                .then()
                .statusCode(404);

        System.out.println("TEST: returned 404 Not Found");
    }

    @Test
    public void validateBadRequestReturns400(){
        String invalidBody = "{\n" +
                "  \"title\": \"\"\n" +
                "}";

        given()
                .spec(requestSpec)
                .body(invalidBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201);

        System.out.println("TEST: returned BAD Request");
    }
}
