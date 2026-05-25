package com.epam.campus.restassured.tests.performance;

import static org.hamcrest.Matchers.lessThan;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.epam.campus.restassured.base.BaseTest;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class PerformanceTests extends BaseTest {

    private static final long PERFORMANCE_THRESHOLD_MS = 200;
    private static final long PERFORMANCE_THRESHOLD_MS_ACCEPTABLE = 500;

    @Test
    public void validateResponseTimeUnder200ms(){
        long startTime = System.currentTimeMillis();

        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        assertTrue(responseTime < PERFORMANCE_THRESHOLD_MS,
                "Response time should be under 200ms. Actual: " + responseTime + "ms");

        System.out.println("Response time under 200ms: " + responseTime + "ms");
    }

    @Test
    public void validateResponseTimeUsingAssertions(){
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .time(lessThan(PERFORMANCE_THRESHOLD_MS_ACCEPTABLE));

        System.out.println("Response time is less than " + PERFORMANCE_THRESHOLD_MS_ACCEPTABLE + "ms");
    }

    @Test
    public void validateMultipleEndpointsPerformance(){
        String[] endpoints = {"/posts/1", "/posts/2", "/users/1", "/comments/1"};

        for (String endpoint : endpoints) {
            long startTime = System.currentTimeMillis();

            given()
                    .spec(requestSpec)
                    .when()
                    .get(endpoint)
                    .then()
                    .statusCode(200);

            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;

            assertTrue(responseTime < PERFORMANCE_THRESHOLD_MS_ACCEPTABLE,
                    "Response time for " + endpoint + " should be under " + PERFORMANCE_THRESHOLD_MS_ACCEPTABLE + "ms. Actual: " + responseTime + "ms");

            System.out.println("" + endpoint + " - Response time: " + responseTime + "ms");
        }
    }


    @Test
    public void logResponseTime(){
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        long responseTime = response.getTime();
        System.out.println("Response time logged: " + responseTime + "ms");
    }

    @Test
    public void validateAverageResponseTime(){
        int numberOfRequests = 5;
        long totalTime = 0;

        for (int i = 0; i < numberOfRequests; i++) {
            long startTime = System.currentTimeMillis();

            given()
                    .spec(requestSpec)
                    .when()
                    .get("/posts/" + (i + 1))
                    .then()
                    .statusCode(200);

            long endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }

        long averageTime = totalTime / numberOfRequests;
        assertTrue(averageTime < PERFORMANCE_THRESHOLD_MS_ACCEPTABLE,
                "Average response time should be under " + PERFORMANCE_THRESHOLD_MS_ACCEPTABLE + "ms. Actual: " + averageTime + "ms");

        System.out.println("Average response time (" + numberOfRequests + " requests): " + averageTime + "ms");
    }

    @Test
    public void validatePerformanceWithLargePayload(){
        long startTime = System.currentTimeMillis();

        given()
                .spec(requestSpec)
                .when()
                .get("/posts?_limit=100")
                .then()
                .statusCode(200);

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        assertTrue(responseTime < PERFORMANCE_THRESHOLD_MS_ACCEPTABLE,
                "Response time with large payload should be under " + PERFORMANCE_THRESHOLD_MS_ACCEPTABLE + "ms. Actual: " + responseTime + "ms");

        System.out.println("Large payload response time: " + responseTime + "ms");
    }
}
