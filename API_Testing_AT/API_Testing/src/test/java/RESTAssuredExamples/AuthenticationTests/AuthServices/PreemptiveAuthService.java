package RESTAssuredExamples.AuthenticationTests.AuthServices;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PreemptiveAuthService {

    public static Response getPreemptAuth(RequestSpecification reqSpec){
        return given().baseUri("https://postman-echo.com/basic-auth")
                .auth().preemptive()
                .basic("postman", "password")
                .spec(reqSpec)
                .when()
                .get();
    }
}
