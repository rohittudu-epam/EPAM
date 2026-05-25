package com.epam.campus.bdd.steps;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.epam.campus.bdd.config.ConfigManager;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiStepDefinitions {

    private Response response;
    private RequestSpecification request;
    private final Map<String, String> storedValues = new HashMap<>();

    @Given("the API base URL is configured")
    public void theApiBaseUrlIsConfigured() {
        RestAssured.baseURI = ConfigManager.getBaseUrl();
        request = RestAssured.given()
                .header("Content-Type", "application/json");
    }

    @When("I send a GET request to {string}")
    public void iSendAGetRequestTo(String endpoint) {
        response = request.when().get(endpoint);
    }

    @When("I send a POST request to {string} with body:")
    public void iSendAPostRequestToWithBody(String endpoint, DataTable dataTable) {
        JSONObject jsonBody = new JSONObject();
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            jsonBody.put(entry.getKey(), entry.getValue());
        }
        response = request.body(jsonBody.toString()).when().post(endpoint);
    }

    @When("I send a PUT request to {string} with body:")
    public void iSendAPutRequestToWithBody(String endpoint, DataTable dataTable) {
        JSONObject jsonBody = new JSONObject();
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            jsonBody.put(entry.getKey(), entry.getValue());
        }
        response = request.body(jsonBody.toString()).when().put(endpoint);
    }

    @When("I send a DELETE request to {string}")
    public void iSendADeleteRequestTo(String endpoint) {
        response = request.when().delete(endpoint);
    }

    @When("I send a PUT request to the stored resource {string} with body:")
    public void iSendAPutRequestToStoredResourceWithBody(String endpointTemplate, DataTable dataTable) {
        String endpoint = resolveStoredValues(endpointTemplate);
        JSONObject jsonBody = new JSONObject();
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            jsonBody.put(entry.getKey(), entry.getValue());
        }
        response = request.body(jsonBody.toString()).when().put(endpoint);
    }

    @When("I send a DELETE request to the stored resource {string}")
    public void iSendADeleteRequestToStoredResource(String endpointTemplate) {
        String endpoint = resolveStoredValues(endpointTemplate);
        response = request.when().delete(endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("the response should contain a {string} field")
    public void theResponseShouldContainAField(String fieldName) {
        assertNotNull("Field '" + fieldName + "' should be present in response",
                response.jsonPath().getString(fieldName));
    }

    @And("the response should contain field {string} with value {string}")
    public void theResponseShouldContainFieldWithValue(String fieldName, String expectedValue) {
        String actualValue = response.jsonPath().getString(fieldName);
        assertEquals(expectedValue, actualValue);
    }

    @And("the response header {string} should contain {string}")
    public void theResponseHeaderShouldContain(String headerName, String expectedValue) {
        String headerValue = response.getHeader(headerName);
        assertNotNull("Header '" + headerName + "' should be present", headerValue);
        assertTrue("Header '" + headerName + "' should contain '" + expectedValue + "'",
                headerValue.contains(expectedValue));
    }

    @And("the response JSON path {string} should be {int}")
    public void theResponseJsonPathShouldBeInt(String jsonPath, int expectedValue) {
        int actualValue = response.jsonPath().getInt(jsonPath);
        assertEquals(expectedValue, actualValue);
    }

    @And("the response JSON path {string} should be {string}")
    public void theResponseJsonPathShouldBeString(String jsonPath, String expectedValue) {
        String actualValue = response.jsonPath().getString(jsonPath);
        assertEquals(expectedValue, actualValue);
    }

    @And("I store the response field {string} as {string}")
    public void iStoreTheResponseFieldAs(String fieldName, String key) {
        String value = response.jsonPath().getString(fieldName);
        assertNotNull("Field '" + fieldName + "' should be present to store", value);
        storedValues.put(key, value);
    }

    private String resolveStoredValues(String template) {
        String resolved = template;
        for (Map.Entry<String, String> entry : storedValues.entrySet()) {
            resolved = resolved.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return resolved;
    }
}
