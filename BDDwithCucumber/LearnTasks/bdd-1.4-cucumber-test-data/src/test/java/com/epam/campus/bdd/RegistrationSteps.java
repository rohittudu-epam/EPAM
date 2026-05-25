package com.epam.campus.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.regex.Pattern;

public class RegistrationSteps {

    private String username;
    private String email;
    private String password;
    private String registrationResult;

    @Given("the user is on the registration page")
    public void theUserIsOnTheRegistrationPage() {
        // Simulate navigating to registration page
    }

    @When("the user enters username {string} email {string} and password {string}")
    public void theUserEntersCredentials(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registrationResult = validateRegistration(username, email, password);
    }

    @Then("the registration result should be {string}")
    public void theRegistrationResultShouldBe(String expectedResult) {
        assert registrationResult.equals(expectedResult) :
                "Expected: " + expectedResult + " but got: " + registrationResult;
    }

    private String validateRegistration(String username, String email, String password) {
        if (username == null || username.isEmpty()) {
            return "Username is required";
        }
        if (email == null || email.isEmpty()) {
            return "Email is required";
        }
        if (!Pattern.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$", email)) {
            return "Invalid email format";
        }
        if (password == null || password.isEmpty()) {
            return "Password is required";
        }
        if (password.length() < 8) {
            return "Password must be at least 8 characters";
        }
        if (!Pattern.matches(".*[A-Z].*", password)) {
            return "Password must contain uppercase letter";
        }
        return "Registration successful";
    }
}
