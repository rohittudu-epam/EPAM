package com.epam.campus.restassured.base;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp(){
        requestSpec = RequestSpecBuilderUtil.getRequestSpec();
    }
}
