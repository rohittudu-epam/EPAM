package com.epam.campus.restassured.tests.body;

import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

import com.epam.campus.restassured.base.BaseTest;
import com.epam.campus.restassured.base.ResponseUtils;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Test class for Response Body validation
 */
public class BodyValidationTests extends BaseTest {

    /**
     * Validate single post contains all expected fields
     */
    @Test
    public void validateSinglePostFields(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue());

        System.out.println("✓ Single post response contains all expected fields");
    }

    /**
     * Validate post ID equals expected value
     */
    @Test
    public void validatePostIdEqualsOne(){
        Response res = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Integer postId = ResponseUtils.getIntValue(res, "id");
        assertEquals(postId, Integer.valueOf(1), "Post ID should be 1");

        System.out.println("✓ Post ID equals 1");
    }

    /**
     * Validate post title is not null and not empty
     */
    @Test
    public void validatePostTitleNotNull(){
        Response res = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String title = ResponseUtils.getStringValue(res, "title");
        assertNotNull(title, "Title should not be null");
        assert !title.isEmpty() : "Title should not be empty";

        System.out.println("✓ Post title is not null: " + title);
    }

    /**
     * Validate response contains all expected keys
     */
    @Test
    public void validateResponseContainsExpectedKeys(){
        Response res = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String responseBody = res.asString();
        assert responseBody.contains("userId") : "Response should contain 'userId'";
        assert responseBody.contains("id") : "Response should contain 'id'";
        assert responseBody.contains("title") : "Response should contain 'title'";
        assert responseBody.contains("body") : "Response should contain 'body'";

        System.out.println("✓ Response contains all expected keys");
    }

    /**
     * Validate body content using hamcrest matchers
     */
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

        System.out.println("✓ Body content validation passed");
    }
}
