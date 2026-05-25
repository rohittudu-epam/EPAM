package com.epam.campus.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step Definitions for Login feature.
 *
 * Demonstrates:
 * - Mapping Gherkin steps to Java methods using @Given, @When, @Then
 * - Parameterization with Cucumber Expressions (string, int)
 * - Reusable steps shared across multiple scenarios
 * - Meaningful assertions with descriptive error messages
 */
public class LoginSteps {

    private final SharedState state;

    // Cucumber injects SharedState via PicoContainer (default DI)
    public LoginSteps(SharedState state) {
        this.state = state;
    }

    // ---- GIVEN steps (preconditions) ----

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        state.setCurrentPage("login");
        assertNotNull(state.getCurrentPage(), "Failed to navigate to login page");
        assertEquals("login", state.getCurrentPage(),
                "Expected to be on login page but was on: " + state.getCurrentPage());
    }

    // ---- WHEN steps (actions) ----

    @When("the user enters username {string} and password {string}")
    public void theUserEntersUsernameAndPassword(String username, String password) {
        // Parameterized step: Cucumber extracts username and password from the Gherkin step
        String result = state.getAuthService().login(username, password);

        if ("SUCCESS".equals(result)) {
            state.setCurrentPage("dashboard");
            state.setLastMessage("Welcome, " + username + "!");
        } else {
            state.setLastMessage(result);
        }
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        // In a real application, this would trigger a form submission.
        // The login action was already performed in the previous step.
        // This step exists to match the Gherkin narrative flow.
    }

    @When("the user clicks the logout button")
    public void theUserClicksTheLogoutButton() {
        state.getAuthService().logout();
        state.setCurrentPage("login");
        state.setLastMessage("");
    }

    // ---- THEN steps (assertions / expected outcomes) ----

    @Then("the user should be redirected to the dashboard")
    public void theUserShouldBeRedirectedToTheDashboard() {
        assertEquals("dashboard", state.getCurrentPage(),
                "Expected redirect to dashboard, but current page is: " + state.getCurrentPage());
        assertTrue(state.getAuthService().isLoggedIn(),
                "User should be logged in when on dashboard");
    }

    @Then("the welcome message should display {string}")
    public void theWelcomeMessageShouldDisplay(String expectedMessage) {
        assertEquals(expectedMessage, state.getLastMessage(),
                "Welcome message mismatch. Expected: '" + expectedMessage
                        + "' but got: '" + state.getLastMessage() + "'");
    }

    @Then("an error message should be displayed with text {string}")
    public void anErrorMessageShouldBeDisplayedWithText(String expectedError) {
        assertEquals(expectedError, state.getLastMessage(),
                "Error message mismatch. Expected: '" + expectedError
                        + "' but got: '" + state.getLastMessage() + "'");
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLoginPage() {
        assertEquals("login", state.getCurrentPage(),
                "Expected redirect to login page, but current page is: " + state.getCurrentPage());
        assertFalse(state.getAuthService().isLoggedIn(),
                "User should be logged out when on login page");
    }
}
