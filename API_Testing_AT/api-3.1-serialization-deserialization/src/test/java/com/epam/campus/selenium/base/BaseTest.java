package com.epam.campus.selenium.base;

import com.epam.campus.selenium.config.TestConfig;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = TestConfig.BASE_URI;
    }
}
