package com.epam.campus.Base;

import com.epam.campus.constants.ApiEndPoints;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected static final String BASE_URI = "http://localhost:8080";
    protected static final String ADMIN_USERNAME = "admin";
    protected static final String ADMIN_PASSWORD = "admin";
    protected static String access_token;
    protected static RequestSpecification requestSpec;
    protected static RequestSpecification authenticatedRequestSpec;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;

        requestSpec =  new RequestSpecBuilder().setBaseUri(BASE_URI).setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();

            access_token = authenticate(ADMIN_USERNAME, ADMIN_PASSWORD);

        authenticatedRequestSpec = getRequestSpecWithToken(access_token);
    }

    protected RequestSpecification getRequestSpecWithToken(String token){
        return new RequestSpecBuilder().setBaseUri(BASE_URI).setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON).addHeader("Authorization", "Bearer " + token)
                .build();
    }

    protected String authenticate(String username, String password) {
        String requestBody = String.format("""
                {
                    "username": "%s",
                    "password": "%s",
                    "rememberMe": "true"
                }
                """, username, password);

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .post(ApiEndPoints.AUTH)
                .then()
                .statusCode(200)
                .extract().response();

        return response.jsonPath().get("id_token");
    }

    protected String generateUniqueName(String prefix){
        return prefix + System.currentTimeMillis();
    }
}
