package com.epam.campus.bdd.hooks;

import com.epam.campus.bdd.context.ScenarioContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Cucumber Hooks class that manages setup and teardown logic.
 * <p>
 * - Generic @Before / @After hooks run for EVERY scenario.
 * - Tagged hooks (e.g., @Before("@UI")) run only when the scenario
 *   carries the matching tag.
 * <p>
 * PicoContainer injects a fresh ScenarioContext per scenario, ensuring
 * complete isolation between tests.
 */
public class Hooks {

    private final ScenarioContext scenarioContext;

    public Hooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    // ========================== GENERIC HOOKS ==========================

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        System.out.println("=======================================================");
        System.out.println("[HOOK] @Before — Starting scenario: " + scenario.getName());
        System.out.println("  Tags: " + scenario.getSourceTagNames());
        System.out.println("=======================================================");
        scenarioContext.set("scenarioName", scenario.getName());
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        System.out.println("=======================================================");
        System.out.println("[HOOK] @After — Finished scenario: " + scenario.getName());
        System.out.println("  Status: " + scenario.getStatus());
        System.out.println("=======================================================");
        scenarioContext.clear();
    }

    // ========================== UI TAGGED HOOKS ==========================

    @Before(value = "@UI", order = 1)
    public void beforeUIScenario(Scenario scenario) {
        System.out.println("[HOOK] @Before(@UI) — Initializing browser environment...");
        // Simulate browser initialization
        scenarioContext.set("browserInitialized", true);
        scenarioContext.set("browser", "Chrome");
        System.out.println("[HOOK] @Before(@UI) — Browser initialized: Chrome");
    }

    @After(value = "@UI", order = 1)
    public void afterUIScenario(Scenario scenario) {
        System.out.println("[HOOK] @After(@UI) — Closing browser environment...");
        // Simulate browser teardown
        boolean wasInitialized = Boolean.TRUE.equals(scenarioContext.get("browserInitialized"));
        if (wasInitialized) {
            scenarioContext.set("browserInitialized", false);
            System.out.println("[HOOK] @After(@UI) — Browser closed successfully.");
        }
    }

    // ========================== API TAGGED HOOKS ==========================

    @Before(value = "@API", order = 1)
    public void beforeAPIScenario(Scenario scenario) {
        System.out.println("[HOOK] @Before(@API) — Setting up API client...");
        // Simulate API client initialization
        scenarioContext.set("apiClientReady", true);
        scenarioContext.set("apiBaseUrl", "https://api.example.com/v1");
        System.out.println("[HOOK] @Before(@API) — API client ready. Base URL: https://api.example.com/v1");
    }

    @After(value = "@API", order = 1)
    public void afterAPIScenario(Scenario scenario) {
        System.out.println("[HOOK] @After(@API) — Tearing down API client...");
        boolean wasReady = Boolean.TRUE.equals(scenarioContext.get("apiClientReady"));
        if (wasReady) {
            scenarioContext.set("apiClientReady", false);
            System.out.println("[HOOK] @After(@API) — API client shut down.");
        }
    }

    // ======================== REGRESSION TAGGED HOOKS ========================

    @Before(value = "@Regression", order = 1)
    public void beforeRegressionScenario(Scenario scenario) {
        System.out.println("[HOOK] @Before(@Regression) — Loading regression test data...");
        scenarioContext.set("testDataLoaded", true);
        System.out.println("[HOOK] @Before(@Regression) — Test data loaded.");
    }

    @After(value = "@Regression", order = 1)
    public void afterRegressionScenario(Scenario scenario) {
        System.out.println("[HOOK] @After(@Regression) — Cleaning up regression test data...");
        scenarioContext.set("testDataLoaded", false);
        System.out.println("[HOOK] @After(@Regression) — Regression test data cleaned up.");
    }
}
