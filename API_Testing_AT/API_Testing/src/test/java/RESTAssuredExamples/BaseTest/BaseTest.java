package RESTAssuredExamples.BaseTest;

import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeTest;

import static RESTAssuredExamples.SpecBuilders.RequestBuilderUtil.getRequestSpecification;

public class BaseTest {
    public static RequestSpecification requestSpec;

    @BeforeTest
    void setup(){
        requestSpec = getRequestSpecification();
        System.out.println("Request Spec Defined");
    }

    public RequestSpecification getRequestSpec(){
        System.out.println("Returning ReqSpec");

        return requestSpec;
    }
}
