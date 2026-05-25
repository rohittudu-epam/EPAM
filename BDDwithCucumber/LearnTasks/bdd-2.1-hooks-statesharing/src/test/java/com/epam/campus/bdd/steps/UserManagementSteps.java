package com.epam.campus.bdd.steps;

import java.util.HashMap;
import java.util.Map;

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

public class UserManagementSteps {

    private final TestContext testContext;

    // Simulated in-memory user store (acts as a mini database)
    private static final Map<String, User> userStore = new HashMap<>();

    public UserManagementSteps(ScenarioContext scenarioContext) {
        this.testContext = new TestContext(scenarioContext);
    }

    @Given("a new user with name {string} and email {string} and role {string}")
    public void aNewUserWithNameAndEmailAndRole(String name, String email, String role) {
        // Verify that regression test data was loaded by the @Regression hook
        boolean testDataLoaded = Boolean.TRUE.equals(
                testContext.getScenarioContext().get("testDataLoaded"));
        assertTrue(testDataLoaded, "Test data should have been loaded by @Regression hook");

        User user = new User(name, email, role);
        testContext.setCurrentUser(user);

        System.out.println("[STEP] New user prepared: " + user);
    }

    @When("the user is registered in the system")
    public void theUserIsRegisteredInTheSystem() {
        User user = testContext.getCurrentUser();
        assertNotNull(user, "User should be available from previous step via shared state");

        // Simulate registration — assign ID and store
        user.setId(userStore.size() + 1);
        userStore.put(user.getEmail(), user);

        System.out.println("[STEP] User registered: " + user);
    }

    @Then("the user should exist in the system")
    public void theUserShouldExistInTheSystem() {
        User user = testContext.getCurrentUser();
        assertTrue(userStore.containsKey(user.getEmail()),
                "User should exist in the store: " + user.getEmail());
        System.out.println("[STEP] User exists in system: " + user.getEmail());
    }

    @And("the user's role should be {string}")
    public void theUserRoleShouldBe(String expectedRole) {
        User user = testContext.getCurrentUser();
        User stored = userStore.get(user.getEmail());
        assertEquals(expectedRole, stored.getRole());
        System.out.println("[STEP] User role verified: " + stored.getRole());
    }

    @And("the user's email should be {string}")
    public void theUserEmailShouldBe(String expectedEmail) {
        User user = testContext.getCurrentUser();
        assertEquals(expectedEmail, user.getEmail());
        System.out.println("[STEP] User email verified: " + user.getEmail());
    }
}
