package com.epam.campus.selenium;

import com.epam.campus.selenium.base.BaseTest;
import com.epam.campus.selenium.client.PostApiClient;
import com.epam.campus.selenium.pojo.PostRequest;
import com.epam.campus.selenium.pojo.PostResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SerializationTest extends BaseTest {

    @Test
    public void createPost_SerializationTest(){
        PostRequest request = new PostRequest(
                "Green Hornet",
                "This is the story of The Green Hornet",
                1
        );

        PostApiClient client = new PostApiClient();
        Response response = client.createPost(request);

        PostResponse postResponse = response.as(PostResponse.class);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(postResponse.getTitle(), request.getTitle());
    }

}
