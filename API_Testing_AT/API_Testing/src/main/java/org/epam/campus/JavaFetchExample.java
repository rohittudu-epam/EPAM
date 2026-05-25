package org.epam.campus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaFetchExample {
    void main() throws Exception {
        URI uri = URI.create("https://jsonplaceholder.typicode.com/posts/1");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

            System.out.println("Status Code: " + response.statusCode());

            System.out.println("Body: " + response.body());
    }
}
