package com.epam.campus.bdd.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.campus.bdd.context.ScenarioContext;
import com.epam.campus.bdd.context.TestContext;
import com.epam.campus.bdd.model.User;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final TestContext testContext;

    public LoginSteps(ScenarioContext scenarioContext) {
        this.testContext = new TestContext(scenarioContext);
    }

    @Given("a user with username {string} and password {string}")
    public void aUserWithUsernameAndPassword(String username, String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        testContext.setCurrentUser(user);

        // Verify that browser was initialized by the @UI hook
        boolean browserReady = Boolean.TRUE.equals(
                testContext.getScenarioContext().get("browserInitialized"));
        assertTrue(browserReady, "Browser should have been initialized by @UI hook");

        System.out.println("[STEP] User set: " + username);
    }

    @When("the user attempts to login")
    public void theUserAttemptsToLogin() {
        User user = testContext.getCurrentUser();
        assertNotNull(user, "User should be available from previous step via shared state");

        // Simulate login logic
        boolean success = "admin".equals(user.getName()) && "secret123".equals(user.getPassword());
        testContext.setLoginResult(success);

        if (!success) {
            testContext.setErrorMessage("Invalid credentials");
        }

        System.out.println("[STEP] Login attempted for: " + user.getName() + " — Success: " + success);
    }

    @Then("the login should be successful")
    public void theLoginShouldBeSuccessful() {
        assertTrue(testContext.getLoginResult(), "Login should have succeeded");
        System.out.println("[STEP] Login verified as successful.");
    }

    @Then("the login should fail")
    public void theLoginShouldFail() {
        assertFalse(testContext.getLoginResult(), "Login should have failed");
        System.out.println("[STEP] Login verified as failed.");
    }

    @And("the user should see the welcome message {string}")
    public void theUserShouldSeeTheWelcomeMessage(String expectedMessage) {
        User user = testContext.getCurrentUser();
        String actual = "Welcome, " + user.getName() + "!";
        assertEquals(expectedMessage, actual);
        System.out.println("[STEP] Welcome message verified: " + actual);
    }

    @And("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedError) {
        assertEquals(expectedError, testContext.getErrorMessage());
        System.out.println("[STEP] Error message verified: " + testContext.getErrorMessage());
    }
}
