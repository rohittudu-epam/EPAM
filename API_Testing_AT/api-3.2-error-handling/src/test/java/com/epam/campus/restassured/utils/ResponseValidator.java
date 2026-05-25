package com.epam.campus.restassured.utils;

import com.epam.campus.restassured.pojo.ErrorResponse;
import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {
    public static void validateStatusCode(Response response, int expected){
        Assert.assertEquals(response.getStatusCode(), expected, "Unexpected Status Code");
    }

    public static void validateContentType(Response response){
        Assert.assertEquals(response.contentType(), "application/json", "Unexpected Content Type");
    }

    public static void validateErrorMessage(ErrorResponse error, String expectedError, String expectedMessage){
        Assert.assertEquals(error.getError(), expectedError, "Error Mismatched");
        Assert.assertEquals(error.getMessage(), expectedMessage, "Error Message Mismatched");
    }
}
