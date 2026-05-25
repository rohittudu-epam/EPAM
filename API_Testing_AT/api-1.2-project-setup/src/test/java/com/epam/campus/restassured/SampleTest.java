package com.epam.campus.restassured;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class SampleTest {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    /**
     * Test Case: Validate GET /posts/1 endpoint response
     * Expected: Status 200, correct Content-Type header, and valid post data
     */
    @Test(priority = 1, description = "Test GET /posts/1 endpoint from JSONPlaceholder API")
    public void testGetPostById() {
        Response response = given()
                .baseUri(BASE_URI)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue())
                .extract()
                .response();

        // Additional assertions using TestNG
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(response.header("Content-Type"), "Content-Type header should not be null");
        Assert.assertTrue(response.header("Content-Type").contains("application/json"), 
                "Content-Type should be application/json");
        
        int postId = response.jsonPath().getInt("id");
        Assert.assertEquals(postId, 1, "Post ID should be 1");
    }

    /**
     * Test Case: Validate POST /posts endpoint for creating new resource
     * Expected: Status 201 (Created), response contains new post with ID
     */
    @Test(priority = 2, description = "Test POST /posts endpoint - Create new post")
    public void testCreatePost() {
        String requestBody = "{\n" +
                "  \"title\": \"Test Post\",\n" +
                "  \"body\": \"This is a test post created via REST Assured\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response = given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("Test Post"))
                .body("body", equalTo("This is a test post created via REST Assured"))
                .body("userId", equalTo(1))
                .body("id", notNullValue())
                .extract()
                .response();

        // Additional assertions
        Assert.assertEquals(response.statusCode(), 201, "Status code should be 201 (Created)");
        Assert.assertNotNull(response.jsonPath().getInt("id"), "Response should contain an ID");
    }

    /**
     * Test Case: Validate GET /comments?postId=1 endpoint
     * Expected: Status 200, returns array of comments for post 1
     */
    @Test(priority = 3, description = "Test GET /comments?postId=1 endpoint")
    public void testGetCommentsByPostId() {
        Response response = given()
                .baseUri(BASE_URI)
                .queryParam("postId", 1)
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)))
                .body("[0].postId", equalTo(1))
                .body("[0].id", notNullValue())
                .body("[0].name", notNullValue())
                .body("[0].email", notNullValue())
                .body("[0].body", notNullValue())
                .extract()
                .response();

        // Additional assertions
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        int commentCount = response.jsonPath().getList("").size();
        Assert.assertTrue(commentCount > 0, "Should return at least one comment");
    }
}
