package com.epam.campus.restassured.tests;

import com.epam.campus.restassured.specs.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetPostsTest extends BaseTest {

    @Test
    public void validateGetPostById(){
        given()
                .spec(requestSpec)
                .pathParam("postId", 1)
                .when()
                .get("/posts/{postId}")
                .then()
                .spec(responseSpec)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", notNullValue());
    }
}
