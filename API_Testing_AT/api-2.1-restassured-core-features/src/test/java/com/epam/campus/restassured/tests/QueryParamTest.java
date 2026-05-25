package com.epam.campus.restassured.tests;

import com.epam.campus.restassured.specs.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

public class QueryParamTest extends BaseTest {

    @Test
    public void validatePostsByUserId() {
        given()
                .spec(requestSpec)
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .spec(responseSpec)
                .body("size()", greaterThan(0));
    }
}
