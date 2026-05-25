package com.epam.campus.selenium.client;

import com.epam.campus.selenium.constants.ApiEndpoints;
import com.epam.campus.selenium.pojo.PostRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostApiClient {

    // createPost(PostRequest request) -> Response
    public Response createPost(PostRequest request){
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(ApiEndpoints.POSTS);
    }


    // getPostById(int id) -> Response
    public Response getPostById(int id){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiEndpoints.POSTS + "/" + id);
    }

    // getUserById(int id) -> Response
    public Response getUserById(int id){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiEndpoints.USERS + "/" + id);
    }
}
