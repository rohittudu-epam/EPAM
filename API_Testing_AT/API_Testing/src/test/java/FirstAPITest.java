
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FirstAPITest {

    @Test
    public void testAPI() {
        // given -> starts building the HTTP request -> returns RequestSpecification
            given()
                    .baseUri("https://jsonplaceholder.typicode.com")
                    // Sets the root URL of the API -> stores URI inside the request specification
                    .when()
                    // Signals that request configuration is complete
                    .get("/users/1")
                    // Sends HTTP get request
                    .then()
                    // Switches to Validation Mode -> allows assertion chaining
                    .statusCode(200)
                    // Assert HTTP response status is 200
                    .body("id", equalTo(1))
                    // Validates JSON field `id` -> Uses JSONPath expression `id`
                    .body("username", notNullValue());
                    // Validates JSON field `username` if it contains a value
    }
}
