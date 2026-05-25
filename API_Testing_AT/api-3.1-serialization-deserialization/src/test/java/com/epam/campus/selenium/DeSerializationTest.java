package com.epam.campus.selenium;

import com.epam.campus.selenium.base.BaseTest;
import com.epam.campus.selenium.client.PostApiClient;
import com.epam.campus.selenium.pojo.PostResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeSerializationTest extends BaseTest {

    @Test
    public void getPost_DeserializationTest(){
        PostApiClient client = new PostApiClient();
        Response response = client.getPostById(1);

        PostResponse postResponse = response.as(PostResponse.class);

        Assert.assertEquals(postResponse.getId(), 1);
        Assert.assertNotNull(postResponse.getTitle());
    }
}
