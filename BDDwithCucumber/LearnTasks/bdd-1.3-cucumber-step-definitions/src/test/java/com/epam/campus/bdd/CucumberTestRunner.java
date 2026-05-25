package com.epam.campus.bdd;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

/**
 * Cucumber Test Runner using JUnit 5 Platform Suite.
 *
 * This class configures and launches Cucumber scenarios via JUnit 5.
 * - GLUE_PROPERTY_NAME: tells Cucumber where to find step definitions
 * - FEATURES_PROPERTY_NAME: tells Cucumber where to find .feature files
 * - PLUGIN_PROPERTY_NAME: configures output format (pretty console + HTML report)
 */
@Suite
@IncludeEngines("cucumber")
@SelectPackages("com.epam.campus.bdd")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.epam.campus.bdd.steps")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/com/epam/campus/bdd")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/cucumber-report.html")
public class CucumberTestRunner {
    // This class serves as a test entry point — no code needed here.
}
