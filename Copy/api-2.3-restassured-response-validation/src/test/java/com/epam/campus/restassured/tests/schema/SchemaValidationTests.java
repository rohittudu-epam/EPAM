package com.epam.campus.restassured.tests.schema;

import org.testng.annotations.Test;

import com.epam.campus.restassured.base.BaseTest;

import static io.restassured.RestAssured.given;
import io.restassured.module.jsv.JsonSchemaValidator;

/**
 * Test class for JSON Schema validation
 */
public class SchemaValidationTests extends BaseTest {

    /**
     * Validate single post response against schema
     */
    @Test
    public void validateSinglePostSchema(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/post-schema.json"));

        System.out.println("✓ Single post response matches schema");
    }

    /**
     * Validate posts array response against schema
     */
    @Test
    public void validatePostsArraySchema(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts?_limit=5")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/posts-array-schema.json"));

        System.out.println("✓ Posts array response matches schema");
    }

    /**
     * Validate user response against schema
     */
    @Test
    public void validateUserSchema(){
        given()
                .spec(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-schema.json"));

        System.out.println("✓ User response matches schema");
    }

    /**
     * Validate schema with specific assertions
     */
    @Test
    public void validateSchemaWithAssertions(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/post-schema.json"))
                .body("id", org.hamcrest.Matchers.notNullValue())
                .body("title", org.hamcrest.Matchers.notNullValue());

        System.out.println("✓ Schema validation with assertions passed");
    }
}
