package com.epam.campus.restassured.client;

import com.epam.campus.restassured.constants.ApiEndPoints;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class StudentApiClient {

    public Response getStudentsWithAuth(String token){
        return given()
                .header("Authorization", "Bearer " + token)
                .log()
                .all()
                .when()
                .get(ApiEndPoints.STUDENTS)
                .then()
                .log()
                .all()
                .extract()
                .response();
    }

    public Response getStudentsWithoutAuth(){
        return given()
                .log().all()
                .when()
                .get(ApiEndPoints.STUDENTS)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response createStudent(String body) {
        return given()
                .contentType("application/json")
                .body(body)
                .log().all()
                .when()
                .post(ApiEndPoints.STUDENTS)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response createStudentWithoutAuth(String body){
        return given()
                .body(body)
                .contentType("application/json")
                .log().all()
                .when()
                .post(ApiEndPoints.STUDENTS)
                .then()
                .log().all()
                .extract().response();
    }

    public Response getStudentById(String id, String token){
        return given()
                .header("Authorization", "Bearer " + token)
                .log().all()
                .when()
                .get(ApiEndPoints.STUDENTS + "/" + id)
                .then()
                .log().all()
                .extract().response();
    }

    public Response getStudentsWithInvalidSort(){
        return given()
                .queryParam("sortField", "non-existent-field")
                .log().all()
                .when()
                .get(ApiEndPoints.STUDENTS)
                .then()
                .log().all()
                .extract().response();
    }
}
