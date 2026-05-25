package com.epam.campus.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class ApiPayloadSteps {

    private String endpoint;
    private String requestBody;
    private int responseStatusCode;
    private boolean responseHasIdField;
    private boolean commentSaved;
    private boolean configParsed;
    private String databaseHost;

    @Given("the API endpoint {string} is available")
    public void theApiEndpointIsAvailable(String endpoint) {
        this.endpoint = endpoint;
    }

    @When("the user sends a POST request with the following JSON body:")
    public void theUserSendsPostRequestWithJsonBody(String jsonBody) {
        this.requestBody = jsonBody;
        // Simulate API call
        if (jsonBody.contains("\"email\"") && jsonBody.contains("\"firstName\"")) {
            responseStatusCode = 201;
            responseHasIdField = true;
        } else {
            responseStatusCode = 400;
            responseHasIdField = false;
        }
    }

    @Then("the API should respond with status code {int}")
    public void theApiShouldRespondWithStatusCode(int expectedStatusCode) {
        assert responseStatusCode == expectedStatusCode :
                "Expected status code " + expectedStatusCode + " but got " + responseStatusCode;
    }

    @Then("the response should contain {string} field")
    public void theResponseShouldContainField(String fieldName) {
        assert responseHasIdField : "Response should contain '" + fieldName + "' field";
    }

    @Given("the user is on the blog post page")
    public void theUserIsOnTheBlogPostPage() {
        // Simulate navigating to blog post page
    }

    @When("the user submits a comment with the following text:")
    public void theUserSubmitsCommentWithText(String commentText) {
        assert commentText != null && !commentText.isEmpty() : "Comment text should not be empty";
        commentSaved = commentText.length() > 0;
    }

    @Then("the comment should be saved successfully")
    public void theCommentShouldBeSavedSuccessfully() {
        assert commentSaved : "Comment should have been saved";
    }

    @Given("the configuration endpoint is available")
    public void theConfigurationEndpointIsAvailable() {
        // Simulate configuration endpoint
    }

    @When("the system receives the following XML configuration:")
    public void theSystemReceivesXmlConfiguration(String xmlConfig) {
        assert xmlConfig != null && !xmlConfig.isEmpty() : "XML config should not be empty";
        configParsed = xmlConfig.contains("<configuration>");
        if (xmlConfig.contains("<host>")) {
            int start = xmlConfig.indexOf("<host>") + "<host>".length();
            int end = xmlConfig.indexOf("</host>");
            databaseHost = xmlConfig.substring(start, end);
        }
    }

    @Then("the configuration should be parsed successfully")
    public void theConfigurationShouldBeParsedSuccessfully() {
        assert configParsed : "Configuration should have been parsed";
    }

    @Then("the database host should be {string}")
    public void theDatabaseHostShouldBe(String expectedHost) {
        assert expectedHost.equals(databaseHost) :
                "Expected host '" + expectedHost + "' but got '" + databaseHost + "'";
    }
}
