package com.epam.campus.Base;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Base test class that provides common setup and utility methods for all API tests.
 * Handles authentication and provides authenticated request specifications.
 */
public class BaseTest {

    protected static final String BASE_URI = "http://localhost:8080";
    protected static final String ADMIN_USERNAME = "admin";
    protected static final String ADMIN_PASSWORD = "admin";
    
    protected static String authToken;
    protected static RequestSpecification requestSpec;
    protected static RequestSpecification authenticatedRequestSpec;

    /**
     * Sets up the base configuration for RestAssured and authenticates with the API.
     * This method runs once before all tests in each test class.
     */
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;
        
        // Create base request specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        
        // Authenticate and get token
        authToken = authenticate(ADMIN_USERNAME, ADMIN_PASSWORD);
        
        // Create authenticated request specification
        authenticatedRequestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + authToken)
                .build();
    }

    /**
     * Authenticates with the API and returns the JWT token.
     * 
     * @param username The username for authentication
     * @param password The password for authentication
     * @return The JWT token string
     */
    protected String authenticate(String username, String password) {
        String requestBody = String.format(
                "{\"username\":\"%s\",\"password\":\"%s\",\"rememberMe\":false}",
                username, password);

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/api/authenticate")
                .then()
                .extract()
                .response();

        return response.jsonPath().getString("id_token");
    }

    /**
     * Creates a request specification with a specific bearer token.
     * 
     * @param token The bearer token to use
     * @return A new RequestSpecification with the provided token
     */
    protected RequestSpecification getRequestSpecWithToken(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .build();
    }

    /**
     * Generates a unique string for creating unique test data.
     * 
     * @param prefix A prefix to add to the generated string
     * @return A unique string with the given prefix
     */
    protected String generateUniqueName(String prefix) {
        return prefix + System.currentTimeMillis();
    }
}
