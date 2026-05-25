package com.epam.campus.bdd.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.campus.bdd.context.ScenarioContext;
import com.epam.campus.bdd.context.TestContext;
import com.epam.campus.bdd.model.User;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApiUserSteps {

    private final TestContext testContext;

    public ApiUserSteps(ScenarioContext scenarioContext) {
        this.testContext = new TestContext(scenarioContext);
    }

    @Given("an API request payload with name {string} and email {string}")
    public void anAPIRequestPayloadWithNameAndEmail(String name, String email) {
        // Verify that API client was initialized by the @API hook
        boolean apiReady = Boolean.TRUE.equals(
                testContext.getScenarioContext().get("apiClientReady"));
        assertTrue(apiReady, "API client should have been initialized by @API hook");

        User user = new User(name, email, "user");
        testContext.setCurrentUser(user);

        System.out.println("[STEP] API request prepared for user: " + name);
    }

    @When("the create user API is called")
    public void theCreateUserAPIIsCalled() {
        User user = testContext.getCurrentUser();
        assertNotNull(user, "User should be available from previous step via shared state");

        // Simulate API call — assign an ID and set response
        user.setId(101);
        testContext.setResponseStatus(201);
        testContext.setResponseBody("{\"id\":101,\"name\":\"" + user.getName()
                + "\",\"email\":\"" + user.getEmail() + "\"}");

        System.out.println("[STEP] Create User API called. Response: 201 Created");
    }

    @Given("an existing user with id {int}")
    public void anExistingUserWithId(int userId) {
        boolean apiReady = Boolean.TRUE.equals(
                testContext.getScenarioContext().get("apiClientReady"));
        assertTrue(apiReady, "API client should have been initialized by @API hook");

        User user = new User("Existing User", "existing@example.com", "user");
        user.setId(userId);
        testContext.setCurrentUser(user);

        System.out.println("[STEP] Existing user set with id: " + userId);
    }

    @When("the get user API is called")
    public void theGetUserAPIIsCalled() {
        User user = testContext.getCurrentUser();
        assertNotNull(user, "User should be available from previous step via shared state");

        // Simulate GET API call
        testContext.setResponseStatus(200);
        testContext.setResponseBody("{\"id\":" + user.getId()
                + ",\"name\":\"" + user.getName()
                + "\",\"email\":\"" + user.getEmail() + "\"}");

        System.out.println("[STEP] Get User API called for id: " + user.getId());
    }

    @Then("the API response status code should be {int}")
    public void theAPIResponseStatusCodeShouldBe(int expectedStatus) {
        assertEquals(expectedStatus, testContext.getResponseStatus());
        System.out.println("[STEP] API status code verified: " + expectedStatus);
    }

    @And("the response body should contain the user name {string}")
    public void theResponseBodyShouldContainTheUserName(String expectedName) {
        String body = testContext.getResponseBody();
        assertTrue(body.contains(expectedName),
                "Response body should contain user name: " + expectedName);
        System.out.println("[STEP] Response body contains name: " + expectedName);
    }

    @And("the response body should contain the user id {int}")
    public void theResponseBodyShouldContainTheUserId(int expectedId) {
        String body = testContext.getResponseBody();
        assertTrue(body.contains("\"id\":" + expectedId),
                "Response body should contain user id: " + expectedId);
        System.out.println("[STEP] Response body contains id: " + expectedId);
    }
}
