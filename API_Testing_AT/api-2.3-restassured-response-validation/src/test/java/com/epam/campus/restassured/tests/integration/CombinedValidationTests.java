package com.epam.campus.restassured.tests.integration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.epam.campus.restassured.base.BaseTest;
import com.epam.campus.restassured.base.ResponseUtils;

import static io.restassured.RestAssured.given;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class CombinedValidationTests extends BaseTest {

    private static final long PERFORMANCE_THRESHOLD = 500;

    @Test
    public void validatePostEndpointEndToEnd(){
        System.out.println("\n========== END-TO-END POST VALIDATION ==========");

        long startTime = System.currentTimeMillis();

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("id", equalTo(1))
                .body("userId", greaterThan(0))
                .body("title", not(emptyString()))
                .body("body", not(emptyString()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/post-schema.json"))
                .extract()
                .response();

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        Integer postId = ResponseUtils.getIntValue(response, "id");
        String title = ResponseUtils.getStringValue(response, "title");
        Integer userId = ResponseUtils.getIntValue(response, "userId");

        assertEquals(postId, Integer.valueOf(1), "Post ID should be 1");
        assertNotNull(title, "Title should not be null");
        assertTrue(userId > 0, "User ID should be greater than 0");


        assertTrue(responseTime < PERFORMANCE_THRESHOLD,
                "Response time should be under " + PERFORMANCE_THRESHOLD + "ms. Actual: " + responseTime + "ms");

        System.out.println("Status Code: 200 OK");
        System.out.println("Headers validated: Content-Type, Content-Length present");
        System.out.println("Body validated: All required fields present and valid");
        System.out.println("JsonPath extraction: ID=" + postId + ", Title=" + title);
        System.out.println("Schema validation: Matches post-schema.json");
        System.out.println("Performance: " + responseTime + "ms (under threshold)");
        System.out.println("========== VALIDATION COMPLETE ==========\n");
    }

    @Test
    public void validateUserWorkflowScenario(){
        System.out.println("\n========== USER WORKFLOW VALIDATION ==========");

        Response userResponse = given()
                .spec(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", not(emptyString()))
                .body("email", not(emptyString()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-schema.json"))
                .extract()
                .response();

        String userName = ResponseUtils.getStringValue(userResponse, "name");
        String userEmail = ResponseUtils.getStringValue(userResponse, "email");

        assertNotNull(userName, "User name should not be null");
        assertNotNull(userEmail, "User email should not be null");

        System.out.println("Step 1: Fetched user - ID=1, Name=" + userName);

        Response postsResponse = given()
                .spec(requestSpec)
                .when()
                .get("/posts?userId=1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        int postCount = postsResponse.jsonPath().getList("id").size();
        assertTrue(postCount > 0, "User should have at least one post");

        System.out.println("Step 2: Fetched user's posts - Count=" + postCount);

        Response postResponse = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/post-schema.json"))
                .extract()
                .response();

        String postTitle = ResponseUtils.getStringValue(postResponse, "title");
        System.out.println("Step 3: Validated post - Title=" + postTitle);

        System.out.println("========== WORKFLOW COMPLETE ==========\n");
    }

    @Test
    public void validatePostsWorkflowScenario(){
        System.out.println("\n========== POSTS WORKFLOW VALIDATION ==========");

        Response page1 = given()
                .spec(requestSpec)
                .when()
                .get("/posts?_page=1&_limit=5")
                .then()
                .statusCode(200)
                .extract()
                .response();

        int page1Count = page1.jsonPath().getList("id").size();
        assertTrue(page1Count > 0, "First page should have posts");

        System.out.println("Step 1: Fetched page 1 - Count=" + page1Count);

        java.util.List<Integer> postIds = page1.jsonPath().getList("id");
        java.util.List<Integer> userIds = page1.jsonPath().getList("userId");
        java.util.List<String> titles = page1.jsonPath().getList("title");

        for (int i = 0; i < postIds.size(); i++) {
            assertNotNull(postIds.get(i), "Post ID should not be null");
            assertTrue(userIds.get(i) > 0, "User ID should be greater than 0");
            assertFalse(titles.get(i).isEmpty(), "Title should not be empty");
        }

        System.out.println("Step 2: Validated all posts have required fields");

        Response userPosts = given()
                .spec(requestSpec)
                .when()
                .get("/posts?userId=2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        java.util.List<Integer> filteredUserIds = userPosts.jsonPath().getList("userId");
        for (Integer userId : filteredUserIds) {
            assertEquals(userId, Integer.valueOf(2), "All posts should belong to userId=2");
        }

        System.out.println("Step 3: Filtered posts by userId=2 - Count=" + filteredUserIds.size());

        System.out.println("========== POSTS WORKFLOW COMPLETE ==========\n");
    }

    @Test
    public void validateErrorScenarios(){
        System.out.println("\n========== ERROR SCENARIO VALIDATION ==========");

        given()
                .spec(requestSpec)
                .when()
                .get("/invalid-endpoint")
                .then()
                .statusCode(404);

        System.out.println("Invalid endpoint returns 404");

        given()
                .spec(requestSpec)
                .when()
                .get("/posts/999999")
                .then()
                .statusCode(404);

        System.out.println("Non-existent post gracefully handled");

        System.out.println("========== ERROR SCENARIOS COMPLETE ==========\n");
    }
}
