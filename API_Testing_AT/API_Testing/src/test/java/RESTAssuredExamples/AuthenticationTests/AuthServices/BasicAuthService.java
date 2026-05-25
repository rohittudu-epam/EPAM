package RESTAssuredExamples.AuthenticationTests.AuthServices;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BasicAuthService {
    public static Response auth(RequestSpecification reqSpec){
        return given().baseUri("https://postman-echo.com/basic-auth")
                .auth().basic("postman", "password")
                .when()
                .get();
    }
}
