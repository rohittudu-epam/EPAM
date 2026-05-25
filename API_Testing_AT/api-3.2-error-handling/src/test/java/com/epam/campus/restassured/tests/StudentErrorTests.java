package com.epam.campus.restassured.tests;

import org.testng.annotations.Test;

import com.epam.campus.restassured.BaseTest;
import com.epam.campus.restassured.client.StudentApiClient;
import com.epam.campus.restassured.pojo.ErrorResponse;
import com.epam.campus.restassured.utils.ResponseValidator;

import io.restassured.response.Response;

public class StudentErrorTests extends BaseTest {

    private final StudentApiClient client = new StudentApiClient();

    @Test
    public void testUnauthorizedAccess(){
        Response response = client.getStudentsWithAuth("my-incorrect-bearer-token007");

        ResponseValidator.validateStatusCode(response, 401);
        ResponseValidator.validateContentType(response);

        ErrorResponse error = response.as(ErrorResponse.class);

        ResponseValidator.validateErrorMessage(
                error,
                "Unauthorized",
                "You must be logged in to access this resource."
        );
    }

    @Test
    public void testMissingAuthorizationHeader(){
        Response response = client.getStudentsWithoutAuth();

        ResponseValidator.validateStatusCode(response, 403);
        ResponseValidator.validateContentType(response);

        ErrorResponse error = response.as(ErrorResponse.class);

        ResponseValidator.validateErrorMessage(
                error,
                "Forbidden",
                "Authorization header is missing. Please provide valid credentials."
        );
    }

    @Test
    public void testBadRequestMissingFields() {

        String invalidBody = """
            {
              "firstName": "",
              "lastName": "",
              "email": ""
            }
        """;

        Response response = client.createStudent(invalidBody);

        ResponseValidator.validateStatusCode(response, 400);

        ErrorResponse error = response.as(ErrorResponse.class);

        ResponseValidator.validateErrorMessage(
                error,
                "Bad Request",
                "Missing required fields: firstName, lastName, or email."
        );
    }

    @Test
    public void testResourceNotFound() {

        Response response =
                client.getStudentById("non-exist-id", "my-unique-bearer-token007");

        ResponseValidator.validateStatusCode(response, 404);
        ResponseValidator.validateContentType(response);

        ErrorResponse error = response.as(ErrorResponse.class);

        ResponseValidator.validateErrorMessage(
                error,
                "Not Found",
                "Student with the requested ID does not exist."
        );
    }

    @Test
    public void testInvalidQueryParameter() {

        Response response = client.getStudentsWithInvalidSort();

        ResponseValidator.validateStatusCode(response, 422);
        ResponseValidator.validateContentType(response);

        ErrorResponse error = response.as(ErrorResponse.class);

        ResponseValidator.validateErrorMessage(
                error,
                "Unprocessable Entity",
                "The requested field for sorting does not exist."
        );
    }
}
