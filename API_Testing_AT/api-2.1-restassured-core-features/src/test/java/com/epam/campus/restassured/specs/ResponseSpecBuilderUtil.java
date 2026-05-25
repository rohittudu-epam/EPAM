package com.epam.campus.restassured.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderUtil {

    public static ResponseSpecification getResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectHeader("Content-Type", "application/json; charset=utf-8")
                .build();
    }
}
