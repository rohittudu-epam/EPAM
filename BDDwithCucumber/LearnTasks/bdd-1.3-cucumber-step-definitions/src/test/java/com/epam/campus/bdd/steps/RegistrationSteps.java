package com.epam.campus.bdd.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step Definitions for Registration feature.
 *
 * Demonstrates:
 * - Using DataTable parameters from Gherkin tables
 * - Extracting multiple dynamic values from a single step
 * - Parameterized assertions for flexible verification
 * - Meaningful error messages for debugging failed steps
 */
public class RegistrationSteps {

    private final SharedState state;

    private String registrationUsername;
    private String registrationEmail;
    private String registrationPassword;

    public RegistrationSteps(SharedState state) {
        this.state = state;
    }

    // ---- GIVEN steps ----

    @Given("the user navigates to the registration page")
    public void theUserNavigatesToTheRegistrationPage() {
        state.setCurrentPage("registration");
        assertEquals("registration", state.getCurrentPage(),
                "Failed to navigate to registration page");
    }

    // ---- WHEN steps ----

    @When("the user enters registration details:")
    public void theUserEntersRegistrationDetails(DataTable dataTable) {
        // DataTable parameterization: extract field-value pairs from a Gherkin table
        Map<String, String> details = dataTable.asMap(String.class, String.class);

        registrationUsername = details.get("username");
        registrationEmail = details.get("email");
        registrationPassword = details.get("password");

        assertNotNull(registrationUsername, "Username field is missing from the registration data table");
        assertNotNull(registrationEmail, "Email field is missing from the registration data table");
        assertNotNull(registrationPassword, "Password field is missing from the registration data table");
    }

    @When("the user submits the registration form")
    public void theUserSubmitsTheRegistrationForm() {
        String result = state.getAuthService().register(
                registrationUsername, registrationEmail, registrationPassword);
        state.setLastMessage(result);
    }

    // ---- THEN steps ----

    @Then("the registration should be successful")
    public void theRegistrationShouldBeSuccessful() {
        String message = state.getLastMessage();
        assertTrue(message.contains("Registration successful"),
                "Expected successful registration but got: '" + message + "'");
    }

    @Then("a confirmation message should display {string}")
    public void aConfirmationMessageShouldDisplay(String expectedMessage) {
        assertEquals(expectedMessage, state.getLastMessage(),
                "Confirmation message mismatch. Expected: '" + expectedMessage
                        + "' but got: '" + state.getLastMessage() + "'");
    }
}
