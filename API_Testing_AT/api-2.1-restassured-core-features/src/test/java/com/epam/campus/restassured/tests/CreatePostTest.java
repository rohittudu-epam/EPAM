package com.epam.campus.restassured.tests;

import com.epam.campus.restassured.specs.base.BaseTest;
import com.epam.campus.restassured.specs.ResponseSpecBuilderUtil;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreatePostTest extends BaseTest {

    @Test
    public void validateCreatePost() {

        String payload = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post("/posts")
                .then()
                .spec(ResponseSpecBuilderUtil.getResponseSpec(201))
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }
}
