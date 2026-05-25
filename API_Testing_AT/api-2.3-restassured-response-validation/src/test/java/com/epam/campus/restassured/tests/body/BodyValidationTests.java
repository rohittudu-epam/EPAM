package com.epam.campus.restassured.tests.body;

import com.epam.campus.restassured.base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BodyValidationTests extends BaseTest {


    @Test
    public void validateIndividualPostFields(){
        given()
        .spec(requestSpec)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue());

        System.out.println("POST: Individual Fields Validated");
    }

    @Test
    public void validatePostIdEqualsOne(){
        given()
        .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));

        System.out.println("POST: ID equals to 1 Check Completed");
    }

    @Test
    public void validatePostTitleNotNull(){
        given()
        .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("title", notNullValue());

        System.out.println("POST: Title NOT NULL Completed");
    }

    @Test
    public void validateResponseContainsExpectedKeys(){
        Response res = given()
        .spec(requestSpec)
                .when()
                .get("/posts/15")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String responseBody = res.asString();
        System.out.println(responseBody);

        assert responseBody.contains("userId") : "Response Should contain userId";
        assert responseBody.contains("id") : "Response Should contain id";
        assert responseBody.contains("title") : "Response body Should contain title";
        assert responseBody.contains("body") : "Response body should contain a body";
    }

    @Test
    public void validateBodyContent(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("userId", greaterThan(0))
                .body("id", greaterThan(0))
                .body("title", not(emptyString()));

        System.out.println("POST: Body Validation Completed");
    }
}
