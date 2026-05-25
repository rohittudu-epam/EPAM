package com.epam.campus.bdd.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Cucumber Hooks for setup and teardown.
 *
 * Hooks run before/after each scenario to manage test state.
 * Demonstrates lifecycle management and debugging aids.
 */
public class Hooks {

    private final SharedState state;

    public Hooks(SharedState state) {
        this.state = state;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Starting scenario: " + scenario.getName());
        System.out.println("Tags: " + scenario.getSourceTagNames());
        System.out.println("========================================");
    }

    @After
    public void afterScenario(Scenario scenario) {
        // Cleanup: ensure user is logged out and cart is cleared
        if (state.getAuthService().isLoggedIn()) {
            state.getAuthService().logout();
        }
        state.getShoppingCart().clear();

        System.out.println("----------------------------------------");
        System.out.println("Finished scenario: " + scenario.getName());
        System.out.println("Status: " + scenario.getStatus());
        if (scenario.isFailed()) {
            System.err.println("FAILED: " + scenario.getName());
        }
        System.out.println("----------------------------------------\n");
    }
}
