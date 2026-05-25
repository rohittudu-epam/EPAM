package com.epam.campus.restassured.tests.jsonpath;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.epam.campus.restassured.base.BaseTest;
import com.epam.campus.restassured.base.ResponseUtils;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

/**
 * Test class for JsonPath extraction and validation
 */
public class JsonPathValidationTests extends BaseTest {

    /**
     * Extract and validate title using JsonPath
     */
    @Test
    public void extractTitleUsingJsonPath(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String title = ResponseUtils.getStringValue(response, "title");
        assertNotNull(title, "Title should not be null");
        assertFalse(title.isEmpty(), "Title should not be empty");

        System.out.println("✓ Extracted title using JsonPath: " + title);
    }

    /**
     * Extract and count posts from a user
     */
    @Test
    public void validatePostsCountForUser(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts?userId=1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Integer> userIds = ResponseUtils.extractList(response, "userId");
        assertTrue(userIds.size() > 0, "Should have posts for userId=1");
        assertTrue(userIds.stream().allMatch(id -> id == 1), "All posts should have userId=1");

        System.out.println("✓ User has " + userIds.size() + " posts");
    }

    /**
     * Validate all posts belong to specified user
     */
    @Test
    public void validateAllPostsBelongToUser(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts?userId=2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Integer> userIds = response.jsonPath().getList("userId");
        
        for (Integer userId : userIds) {
            assertEquals(userId, Integer.valueOf(2), "All posts should belong to userId=2");
        }

        System.out.println("✓ All " + userIds.size() + " posts belong to userId=2");
    }

    /**
     * Validate nested JSON fields using JsonPath
     */
    @Test
    public void validateNestedJsonFields(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Extract nested address fields
        String city = response.jsonPath().getString("address.city");
        String zipcode = response.jsonPath().getString("address.zipcode");
        String companyName = response.jsonPath().getString("company.name");

        assertNotNull(city, "City should not be null");
        assertNotNull(zipcode, "Zipcode should not be null");
        assertNotNull(companyName, "Company name should not be null");

        System.out.println("✓ Nested fields extracted:");
        System.out.println("  City: " + city);
        System.out.println("  Zipcode: " + zipcode);
        System.out.println("  Company: " + companyName);
    }

    /**
     * Iterate and validate array elements
     */
    @Test
    public void iterateAndValidateArrayElements(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts?userId=1&_limit=5")
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Integer> ids = response.jsonPath().getList("id");
        List<Integer> userIds = response.jsonPath().getList("userId");
        List<String> titles = response.jsonPath().getList("title");

        assertEquals(ids.size(), userIds.size(), "Size should match");
        assertEquals(ids.size(), titles.size(), "Size should match");

        for (int i = 0; i < ids.size(); i++) {
            assertNotNull(ids.get(i), "Post ID should not be null");
            assertEquals(userIds.get(i), Integer.valueOf(1), "UserId should be 1");
            assertFalse(titles.get(i).isEmpty(), "Title should not be empty");
        }

        System.out.println("✓ Validated " + ids.size() + " array elements");
    }

    /**
     * Extract specific values and perform calculations
     */
    @Test
    public void extractAndValidateMultipleValues(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Integer id = ResponseUtils.getIntValue(response, "id");
        Integer userId = ResponseUtils.getIntValue(response, "userId");
        String title = ResponseUtils.getStringValue(response, "title");
        String body = ResponseUtils.getStringValue(response, "body");

        assertNotNull(id);
        assertNotNull(userId);
        assertNotNull(title);
        assertNotNull(body);

        System.out.println("✓ Extracted all values:");
        System.out.println("  ID: " + id);
        System.out.println("  UserID: " + userId);
        System.out.println("  Title: " + title);
    }
}
