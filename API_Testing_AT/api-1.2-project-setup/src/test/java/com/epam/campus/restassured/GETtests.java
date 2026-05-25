package com.epam.campus.restassured;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * GET Tests for JSONPlaceholder API
 * Tests for retrieving posts and comments data
 */
public class GETtests {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    @BeforeClass
    public void setUp() {
        // Base configuration for all requests
        baseURI = BASE_URI;
    }

    /**
     * Test Case: Retrieve all posts
     * Expected: Status 200, returns array of posts
     */
    @Test(priority = 1, description = "Get all posts from the API")
    public void testGetAllPosts() {
        Response response = given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)))
                .body("[0].id", notNullValue())
                .body("[0].title", notNullValue())
                .body("[0].body", notNullValue())
                .body("[0].userId", notNullValue())
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertTrue(response.jsonPath().getList("").size() > 0, "Should return posts");
    }

    /**
     * Test Case: Retrieve a specific post by ID
     * Expected: Status 200, returns correct post data
     */
    @Test(priority = 2, description = "Get specific post by ID")
    public void testGetPostById() {
        int postId = 5;
        Response response = given()
                .pathParam("postId", postId)
                .when()
                .get("/posts/{postId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), postId);
    }

    /**
     * Test Case: Retrieve comments for a specific post
     * Expected: Status 200, returns array of comments for post ID 1
     */
    @Test(priority = 3, description = "Get all comments for a specific post")
    public void testGetCommentsByPostId() {
        int postId = 1;
        Response response = given()
                .queryParam("postId", postId)
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)))
                .body("findAll {it.postId == " + postId + "}", hasSize(greaterThan(0)))
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("").size() > 0);
    }

    /**
     * Test Case: Retrieve all comments
     * Expected: Status 200, returns array of all comments
     */
    @Test(priority = 4, description = "Get all comments from the API")
    public void testGetAllComments() {
        Response response = given()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)))
                .body("[0].postId", notNullValue())
                .body("[0].id", notNullValue())
                .body("[0].name", notNullValue())
                .body("[0].email", notNullValue())
                .body("[0].body", notNullValue())
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200);
    }

    /**
     * Test Case: Retrieve posts by user ID
     * Expected: Status 200, returns posts for the specified user
     */
    @Test(priority = 5, description = "Get posts by user ID")
    public void testGetPostsByUserId() {
        int userId = 2;
        Response response = given()
                .queryParam("userId", userId)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)))
                .body("findAll {it.userId == " + userId + "}", hasSize(greaterThan(0)))
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200);
    }

    /**
     * Test Case: Retrieve a specific comment by ID
     * Expected: Status 200, returns correct comment data
     */
    @Test(priority = 6, description = "Get specific comment by ID")
    public void testGetCommentById() {
        int commentId = 3;
        Response response = given()
                .pathParam("commentId", commentId)
                .when()
                .get("/comments/{commentId}")
                .then()
                .statusCode(200)
                .body("id", equalTo(commentId))
                .body("postId", notNullValue())
                .body("name", notNullValue())
                .body("email", notNullValue())
                .body("body", notNullValue())
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), commentId);
    }

    /**
     * Test Case: Validate response headers for GET request
     * Expected: Correct Content-Type and other headers
     */
    @Test(priority = 7, description = "Validate response headers for GET request")
    public void testGetResponseHeaders() {
        Response response = given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .header("Server", notNullValue())
                .extract()
                .response();

        Assert.assertNotNull(response.header("Content-Type"));
        Assert.assertTrue(response.header("Content-Type").contains("application/json"));
    }
}
