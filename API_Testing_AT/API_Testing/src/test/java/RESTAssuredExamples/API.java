package RESTAssuredExamples;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class API {

    @Test
    public void baseAPITest() {
        Response response = given()
                .auth()
                .basic("admin", "password123")
                .when()
                .post("http://api.ecommerce.com/admin/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response);
    }
}
