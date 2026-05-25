package com.epam.campus.selenium.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class DataDrivenJsonTest {

    private final String jsonFilePath = "src/test/resources/testdata.json";
    private final String baseUrl = "https://jsonplaceholder.typicode.com";

    /**
     * Reading JSON file using Jackson ObjectMapper
     * Parametrize test with DataProvider
     * Test Multiple IDs
     *
     * Uses JSONPlaceholder `/posts/{id}`
     */

    @DataProvider(name = "testDataFromJson")
    public Object[][] getTestDataFromJson() throws IOException {
        List<Map<String, Object>> testData = readJsonTestData();
        Object[][] data = new Object[testData.size()][1];

        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }

        return data;
    }

    @Test(dataProvider = "testDataFromJson")
    public void testGetPostWithDataDriven(Map<String, Object> testData) {
        Integer postId = ((Number) testData.get("id")).intValue();
        String title = (String) testData.get("title");

        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Test for ID: " + postId + ", Expected Title: " + title);
        System.out.println("Response: " + response.asPrettyString());

        // Validate the response contains the expected ID
        assertEquals(response.jsonPath().getInt("id"), postId);
    }

    @Test(dataProvider = "testDataFromJson")
    public void testValidatePostContent(Map<String, Object> testData) {
        Integer postId = ((Number) testData.get("id")).intValue();
        Integer userId = ((Number) testData.get("userId")).intValue();

        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Validate userId
        System.out.println(response.asPrettyString());
        assertEquals(response.jsonPath().getInt("userId"), userId);
        System.out.println("Post ID: " + postId + " - UserId validation passed");
    }

    @Test
    public void testMultiplePostsFromJson() throws IOException {
        List<Map<String, Object>> testData = readJsonTestData();

        for (Map<String, Object> data : testData) {
            Integer postId = ((Number) data.get("id")).intValue();

            Response response = given()
                    .baseUri(baseUrl)
                    .when()
                    .get("/posts/" + postId)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            System.out.println("Successfully retrieved post with ID: " + postId);
            System.out.println(response.asPrettyString());
        }
    }

    /**
     * Helper method to read test data from JSON file
     */
    private List<Map<String, Object>> readJsonTestData() throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> testData = objectMapper.readValue(
                jsonContent,
                objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class)
        );

        return testData;
    }
}

