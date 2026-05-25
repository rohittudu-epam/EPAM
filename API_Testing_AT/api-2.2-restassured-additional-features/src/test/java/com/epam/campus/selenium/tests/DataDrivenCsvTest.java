package com.epam.campus.selenium.tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class DataDrivenCsvTest {

    private final String csvFilePath = "src/test/resources/testdata.csv";
    private final String baseUrl = "https://jsonplaceholder.typicode.com";

    /**
     * Read CSV file using OpenCSV
     * Loop through test data
     * Validate response status and content
     */

    @DataProvider(name = "testDataFromCsv")
    public Object[][] getTestDataFromCsv() throws IOException {
        List<Map<String, String>> testData = readCsvTestData();
        Object[][] data = new Object[testData.size()][1];

        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }

        return data;
    }

    @Test(dataProvider = "testDataFromCsv")
    public void testGetPostFromCsvData(Map<String, String> testData) {
        String id = testData.get("id");
        String userId = testData.get("userId");
        String title = testData.get("title");

        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/posts/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("CSV Data - ID: " + id + ", UserId: " + userId + ", Title: " + title);
        System.out.println("Response Status: " + response.getStatusCode());

        // Validate response status
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getInt("id"), Integer.parseInt(id));
    }

    @Test(dataProvider = "testDataFromCsv")
    public void testValidatePostUserIdFromCsv(Map<String, String> testData) {
        String id = testData.get("id");
        String expectedUserId = testData.get("userId");
        System.out.println("ExpectedUserID: " + expectedUserId);
        System.out.println("Test Data: " + testData);

        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/posts/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.asPrettyString());
        Integer actualUserId = response.jsonPath().getInt("userId");
        assertEquals(actualUserId, Integer.parseInt(expectedUserId));
        System.out.println("UserId validation passed for post ID: " + id);
    }

    @Test
    public void testAllCsvDataIteratively() throws IOException {
        List<Map<String, String>> testData = readCsvTestData();

        for (Map<String, String> data : testData) {
            String id = data.get("id");

            Response response = given()
                    .baseUri(baseUrl)
                    .when()
                    .get("/posts/" + id)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            System.out.println("Post ID " + id + " - Status: " + response.getStatusCode());
            System.out.println(response.asPrettyString());
        }
    }

    /**
     * Helper method to read CSV test data
     */
    private List<Map<String, String>> readCsvTestData() throws IOException {
        List<Map<String, String>> testData = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] headers = reader.readNext(); // Read header row
            String[] line;

            while ((line = reader.readNext()) != null) {
                Map<String, String> rowData = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    rowData.put(headers[i], line[i]);
                }

                testData.add(rowData);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return testData;
    }
}

