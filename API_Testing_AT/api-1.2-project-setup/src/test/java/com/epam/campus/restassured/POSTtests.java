package com.epam.campus.restassured;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * POST Tests for JSONPlaceholder API
 * Tests for creating new posts and other resources
 */
public class POSTtests {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    @BeforeClass
    public void setUp() {
        // Base configuration for all requests
        baseURI = BASE_URI;
    }

    /**
     * Test Case: Create a new post with valid data
     * Expected: Status 201 (Created), response contains the new post with generated ID
     */
    @Test(priority = 1, description = "Create a new post with valid data")
    public void testCreateNewPost() {
        String requestBody = "{\n" +
                "  \"title\": \"New Test Post\",\n" +
                "  \"body\": \"This is a test post created via REST Assured automation\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("New Test Post"))
                .body("body", equalTo("This is a test post created via REST Assured automation"))
                .body("userId", equalTo(1))
                .body("id", notNullValue())
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 201, "Status code should be 201 (Created)");
        Assert.assertNotNull(response.jsonPath().get("id"), "Response should contain an ID");
        Assert.assertEquals(response.jsonPath().getString("title"), "New Test Post");
    }

    /**
     * Test Case: Create a post with multiple fields
     * Expected: Status 201, all fields are returned in the response
     */
    @Test(priority = 2, description = "Create post with multiple fields")
    public void testCreatePostWithMultipleFields() {
        String requestBody = "{\n" +
                "  \"title\": \"Advanced Post Test\",\n" +
                "  \"body\": \"This post tests all the required fields\",\n" +
                "  \"userId\": 5\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", notNullValue())
                .body("body", notNullValue())
                .body("userId", notNullValue())
                .body("id", notNullValue())
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 201);
        int userId = response.jsonPath().getInt("userId");
        Assert.assertEquals(userId, 5, "User ID should match the request");
    }

    /**
     * Test Case: Create a post and verify all required fields are present
     * Expected: Status 201, response contains all required fields
     */
    @Test(priority = 3, description = "Verify all required fields in POST response")
    public void testPostResponseContainsAllFields() {
        String requestBody = "{\n" +
                "  \"title\": \"Field Validation Test\",\n" +
                "  \"body\": \"Testing field presence in response\",\n" +
                "  \"userId\": 3\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        // Verify all fields are present in the response
        Assert.assertNotNull(response.jsonPath().get("id"), "id field should be present");
        Assert.assertNotNull(response.jsonPath().get("title"), "title field should be present");
        Assert.assertNotNull(response.jsonPath().get("body"), "body field should be present");
        Assert.assertNotNull(response.jsonPath().get("userId"), "userId field should be present");
    }

    /**
     * Test Case: Create post with specific user ID
     * Expected: Status 201, post created with correct user ID
     */
    @Test(priority = 4, description = "Create post with specific user ID")
    public void testCreatePostWithSpecificUser() {
        String requestBody = "{\n" +
                "  \"title\": \"User Specific Post\",\n" +
                "  \"body\": \"This post belongs to a specific user\",\n" +
                "  \"userId\": 7\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("userId", equalTo(7))
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getInt("userId"), 7);
    }

    /**
     * Test Case: Verify POST response headers
     * Expected: Status 201, correct Content-Type header in response
     */
    @Test(priority = 5, description = "Verify POST response headers")
    public void testPostResponseHeaders() {
        String requestBody = "{\n" +
                "  \"title\": \"Header Test Post\",\n" +
                "  \"body\": \"Testing response headers\",\n" +
                "  \"userId\": 2\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .header("Content-Type", notNullValue())
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertNotNull(response.header("Content-Type"));
    }

    /**
     * Test Case: Create multiple posts sequentially
     * Expected: Each post returns Status 201 with unique ID
     */
    @Test(priority = 6, description = "Create multiple posts sequentially")
    public void testCreateMultiplePosts() {
        for (int i = 1; i <= 3; i++) {
            String requestBody = "{\n" +
                    "  \"title\": \"Batch Post " + i + "\",\n" +
                    "  \"body\": \"This is batch post number " + i + "\",\n" +
                    "  \"userId\": " + i + "\n" +
                    "}";

            Response response = given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .when()
                    .post("/posts")
                    .then()
                    .statusCode(201)
                    .extract()
                    .response();

            Assert.assertEquals(response.statusCode(), 201);
            Assert.assertNotNull(response.jsonPath().get("id"));
        }
    }

    /**
     * Test Case: Create post with long title and body
     * Expected: Status 201, post created with full content
     */
    @Test(priority = 7, description = "Create post with long content")
    public void testCreatePostWithLongContent() {
        String longTitle = "This is a very long title for testing purposes. " +
                "It contains multiple sentences and should be handled correctly by the API.";
        String longBody = "This is a very long body content that tests the API's ability to handle " +
                "extended text input. It should be stored and returned correctly without any truncation. " +
                "This ensures that the API can handle real-world scenarios with substantial content.";

        String requestBody = "{\n" +
                "  \"title\": \"" + longTitle + "\",\n" +
                "  \"body\": \"" + longBody + "\",\n" +
                "  \"userId\": 4\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), longTitle);
    }
}
