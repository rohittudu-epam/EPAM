package RESTAssuredExamples.AuthenticationTests.AuthServices;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DigestAuthService {

    public static Response getDigestAuth(){
        return given().baseUri("https://postman-echo.com/digest-auth")
                .auth().digest("postman", "password").get();
    }
}
