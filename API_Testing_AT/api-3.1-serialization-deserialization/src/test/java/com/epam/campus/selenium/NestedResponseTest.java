package com.epam.campus.selenium;

import com.epam.campus.selenium.base.BaseTest;
import com.epam.campus.selenium.client.PostApiClient;
import com.epam.campus.selenium.pojo.UserResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NestedResponseTest extends BaseTest {

    @Test
    public void getUser_NestedDeserializationTest(){
        PostApiClient client = new PostApiClient();
        Response response = client.getUserById(1);

        UserResponse userResponse = response.as(UserResponse.class);

        Assert.assertEquals(userResponse.getId(), 1);
        Assert.assertNotNull(userResponse.getAddress().getCity());
        Assert.assertNotNull(userResponse.getAddress().getGeo().getLat());
        Assert.assertNotNull(userResponse.getCompany().getName());
    }
}
