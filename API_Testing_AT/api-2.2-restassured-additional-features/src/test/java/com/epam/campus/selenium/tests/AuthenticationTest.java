package com.epam.campus.selenium.tests;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTest {

    private String access_token = null;
    private String refresh_token = null;
    private final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://api.escuelajs.co")
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .build();

    // Receiving Bearer token by sending username and Password
    @Test
    public void getBearerToken(){
        String body = "{\n" +
                "  \"email\": \"john@mail.com\",\n" +
                "  \"password\": \"changeme\"\n" +
                "}";
        Response res = given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post("/api/v1/auth/login")
                .then()
                .statusCode(201)
                .extract()
                .response();

        access_token = res.body().jsonPath().get("access_token");
        refresh_token = res.body().jsonPath().get("refresh_token");
        System.out.println(access_token);
        System.out.println(refresh_token);
    }

    // Test with Bearer Token
    @Test(dependsOnMethods = {"getBearerToken"})
    public void getValidTokenTest(){
        Response res = given()
                .spec(requestSpec)
                .header("Authorization", String.format("bearer %s", access_token))
                .when()
                .get("/api/v1/auth/profile")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(res.asPrettyString());
    }

    // Test with Invalid Token
    // 401 Validation
    @Test(dependsOnMethods = {"getBearerToken"})
    public void getInvalidTokenTest(){
        Response res = given()
                .spec(requestSpec)
                .header("Authorization", String.format("bearer %s", refresh_token))
                .when()
                .get("/api/v1/auth/profile")
                .then()
                .statusCode(401) // 401 Validation
                .extract()
                .response();

        System.out.println(res.asPrettyString());
    }

}
