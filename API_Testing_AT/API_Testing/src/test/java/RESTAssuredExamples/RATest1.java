package RESTAssuredExamples;

import io.restassured.response.Response;
import org.testng.annotations.*;
import org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RATest1 {

    @Test
    public void testGetPostById() {
        Response res = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .extract()
                .response();

        System.out.println(res.asString());
    }

    @Test
    public void testOne(){
        Response res = given()
                .baseUri("https://www.google.com")
                .when()
                .get(); // returns a Response;

        System.out.println(res.asString());
    }
}
