package com.epam.campus.restassured.specs.base;

import com.epam.campus.restassured.specs.RequestSpecBuilderUtil;
import com.epam.campus.restassured.specs.ResponseSpecBuilderUtil;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeClass
    public void setup(){
        requestSpec = RequestSpecBuilderUtil.getRequestSpec();

        responseSpec = ResponseSpecBuilderUtil.getResponseSpec(200);
    }
}
