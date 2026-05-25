package com.epam.campus.restassured.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {
    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
