package com.epam.campus.restassured.base;

import io.restassured.response.Response;

import java.util.List;

public class ResponseUtils {
    public static Object getJsonValue(Response response, String path) {
        return response.jsonPath().get(path);
    }

    public static <T> List<T> extractList(Response response, String path) {
        return response.jsonPath().getList(path);
    }

    public static long getResponseTime(Response response) {
        return response.getTime();
    }

    public static String prettyPrint(Response response) {
        return response.prettyPrint();
    }

    public static String getStringValue(Response response, String path) {
        return response.jsonPath().getString(path);
    }

    public static Integer getIntValue(Response response, String path) {
        return response.jsonPath().getInt(path);
    }

    public static Boolean getBooleanValue(Response response, String path) {
        return response.jsonPath().getBoolean(path);
    }
}
