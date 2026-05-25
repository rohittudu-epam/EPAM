package RESTAssuredExamples.AuthenticationTests;

import RESTAssuredExamples.AuthenticationTests.AuthServices.BasicAuthService;
import RESTAssuredExamples.AuthenticationTests.AuthServices.DigestAuthService;
import RESTAssuredExamples.AuthenticationTests.AuthServices.PreemptiveAuthService;
import RESTAssuredExamples.BaseTest.BaseTest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class AuthTests extends BaseTest {

    @Test
    public void testBasicAuth(){
        RequestSpecification reqSpec = getRequestSpec();
        System.out.println(reqSpec);
        Response response = BasicAuthService.auth(reqSpec);

        System.out.println(String.format("Response Headers: \n%s", response.headers()));
        System.out.println(String.format("Response Body: \n%s", response.asString()));
    }

    @Test
    public void testDigestAuth(){
        Response response = DigestAuthService.getDigestAuth();

        System.out.println(String.format("Response Headers: ", response.getHeaders()));
        System.out.println(String.format("Response Body: ", response.asString()));
    }

//    @Test
//    public void testPreemptAuth(){
//        RequestSpecification reqSpec = getRequestSpec();
//        System.out.println(reqSpec);
//
//        Response response = PreemptiveAuthService.getPreemptAuth(reqSpec);
//
//        System.out.println(String.format("Response Headers: \n%s", response.headers()));
//        System.out.println(String.format("Response Body: \n%s", response.asString()));
//    }
}
