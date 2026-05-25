package com.epam.campus.restassured.base;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderUtil {
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();
    }
}
