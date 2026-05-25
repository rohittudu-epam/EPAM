package com.epam.campus.restassured.base;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

/**
 * Utility class for response operations and data extraction
 */
public class ResponseUtils {

    /**
     * Extract a value from response using JsonPath
     */
    public static Object getJsonValue(Response response, String path) {
        return response.jsonPath().get(path);
    }

    /**
     * Extract a list from response using JsonPath
     */
    public static <T> List<T> extractList(Response response, String path) {
        return response.jsonPath().getList(path);
    }

    /**
     * Get the response time in milliseconds
     */
    public static long getResponseTime(Response response) {
        return response.getTimeIn(io.restassured.http.ContentType.JSON);
    }

    /**
     * Pretty print the response body
     */
    public static String prettyPrint(Response response) {
        return response.prettyPrint();
    }

    /**
     * Get a string value from response using JsonPath
     */
    public static String getStringValue(Response response, String path) {
        return response.jsonPath().getString(path);
    }

    /**
     * Get an integer value from response using JsonPath
     */
    public static Integer getIntValue(Response response, String path) {
        return response.jsonPath().getInt(path);
    }

    /**
     * Get a boolean value from response using JsonPath
     */
    public static Boolean getBooleanValue(Response response, String path) {
        return response.jsonPath().getBoolean(path);
    }
}
