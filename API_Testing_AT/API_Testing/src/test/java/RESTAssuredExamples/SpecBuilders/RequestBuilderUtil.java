package RESTAssuredExamples.SpecBuilders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestBuilderUtil {
    public static RequestSpecification getRequestSpecification(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }
}
