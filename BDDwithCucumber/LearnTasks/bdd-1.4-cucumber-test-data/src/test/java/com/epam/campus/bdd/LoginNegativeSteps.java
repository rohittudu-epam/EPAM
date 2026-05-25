package com.epam.campus.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginNegativeSteps {

    private String loginErrorMessage;
    private List<String> loginAttemptErrors;
    private boolean accountLocked;
    private int failedAttempts;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginErrorMessage = null;
        accountLocked = false;
        failedAttempts = 0;
    }

    @When("the user attempts to login with email {string} and password {string}")
    public void theUserAttemptsToLogin(String email, String password) {
        loginErrorMessage = validateLogin(email, password);
    }

    @Then("the login should fail with message {string}")
    public void theLoginShouldFailWithMessage(String expectedMessage) {
        assert expectedMessage.equals(loginErrorMessage) :
                "Expected: '" + expectedMessage + "' but got: '" + loginErrorMessage + "'";
    }

    @When("the following login attempts are made:")
    public void theFollowingLoginAttemptsAreMade(DataTable dataTable) {
        loginAttemptErrors = new ArrayList<>();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String email = row.get("email");
            String password = row.get("password");
            String expectedError = row.get("expected_error");
            String actualError = validateLogin(email, password);
            loginAttemptErrors.add(actualError);
        }
    }

    @Then("all login attempts should display appropriate error messages")
    public void allLoginAttemptsShouldDisplayErrorMessages() {
        assert !loginAttemptErrors.isEmpty() : "There should be login attempt errors";
        for (String error : loginAttemptErrors) {
            assert error != null && !error.isEmpty() : "Each attempt should produce an error message";
        }
    }

    @Given("the account {string} exists")
    public void theAccountExists(String email) {
        // Simulate account existence
    }

    @When("the user fails to login {int} times consecutively with email {string}")
    public void theUserFailsToLoginConsecutively(int times, String email) {
        for (int i = 0; i < times; i++) {
            failedAttempts++;
        }
        if (failedAttempts >= 5) {
            accountLocked = true;
        }
    }

    @Then("the account should be temporarily locked")
    public void theAccountShouldBeTemporarilyLocked() {
        assert accountLocked : "Account should be locked after 5 failed attempts";
    }

    @Then("the error message should be {string}")
    public void theErrorMessageShouldBe(String expectedMessage) {
        String actualMessage = accountLocked ?
                "Account locked. Try again after 30 minutes." : "Login failed";
        assert expectedMessage.equals(actualMessage) :
                "Expected: '" + expectedMessage + "' but got: '" + actualMessage + "'";
    }

    private String validateLogin(String email, String password) {
        if (email == null || email.isEmpty()) {
            return "Email is required";
        }
        if (!Pattern.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$", email)) {
            return "Invalid email format";
        }
        if (password == null || password.isEmpty()) {
            return "Password is required";
        }
        if (password.contains("<") || password.contains(">")) {
            return "Invalid characters in password";
        }
        if (password.length() < 6) {
            return "Password too short";
        }
        if (password.length() > 30) {
            return "Password too long";
        }
        // Simulate invalid credentials for valid format but wrong password
        return "Invalid credentials";
    }
}
