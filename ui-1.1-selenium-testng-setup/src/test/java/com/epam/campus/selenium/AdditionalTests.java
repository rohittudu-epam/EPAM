package com.epam.campus.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static com.epam.campus.selenium.DriverFactory.*;

/**
 * Additional test cases demonstrating various Selenium WebDriver capabilities.
 * Uses DriverFactory for browser management and includes multiple test scenarios.
 * 
 * @author EPAM Campus
 * @version 1.0
 */
public class AdditionalTests {

    private static final Logger logger = LogManager.getLogger(AdditionalTests.class);
    private WebDriverWait wait;
    
    // Test constants
    private static final String WIKIPEDIA_URL = "https://www.wikipedia.org";
    private static final String WIKIPEDIA_TITLE = "Wikipedia";
    private static final int TIMEOUT_SECONDS = 10;

    /**
     * Sets up WebDriver before test execution using DriverFactory.
     */
    @BeforeClass
    public void setup() {
        logger.info("Setting up AdditionalTests...");
        try {
            initDriver(Browser.CHROME);
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT_SECONDS));
            logger.info("AdditionalTests setup completed successfully");
        } catch (Exception e) {
            logger.error("Failed to setup AdditionalTests: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Verifies Wikipedia homepage loads correctly.
     */
    @Test(priority = 1)
    public void testWikipediaHomePage() {
        logger.info("Starting test: testWikipediaHomePage");
        try {
            WebDriver driver = getDriver();
            
            logger.info("Navigating to: " + WIKIPEDIA_URL);
            driver.get(WIKIPEDIA_URL);
            
            String actualTitle = driver.getTitle();
            logger.info("Page title: " + actualTitle);
            
            Assert.assertTrue(actualTitle.contains(WIKIPEDIA_TITLE), 
                "Title should contain 'Wikipedia'");
            logger.info("Test testWikipediaHomePage passed");
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in testWikipediaHomePage: " + e.getMessage(), e);
            throw new RuntimeException("Test execution failed", e);
        }
    }

    /**
     * Verifies that language links are present on Wikipedia homepage.
     */
    @Test(priority = 2, dependsOnMethods = "testWikipediaHomePage")
    public void testLanguageLinksPresent() {
        logger.info("Starting test: testLanguageLinksPresent");
        try {
            WebDriver driver = getDriver();
            
            logger.info("Looking for language links...");
            List<WebElement> languageLinks = driver.findElements(
                By.cssSelector(".central-featured-lang")
            );
            
            logger.info("Found " + languageLinks.size() + " language links");
            Assert.assertFalse(languageLinks.isEmpty(), 
                "Should have at least one language link");
            
            logger.info("Test testLanguageLinksPresent passed");
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in testLanguageLinksPresent: " + e.getMessage(), e);
            throw new RuntimeException("Test execution failed", e);
        }
    }

    /**
     * Verifies search functionality on Wikipedia.
     */
    @Test(priority = 3)
    public void testWikipediaSearch() {
        logger.info("Starting test: testWikipediaSearch");
        try {
            WebDriver driver = getDriver();
            
            driver.get(WIKIPEDIA_URL);
            
            logger.info("Locating search input...");
            WebElement searchInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))
            );
            
            String searchTerm = "Selenium (software)";
            logger.info("Searching for: " + searchTerm);
            searchInput.sendKeys(searchTerm);
            
            // Click search button
            WebElement searchButton = driver.findElement(
                By.cssSelector("button[type='submit']")
            );
            searchButton.click();
            
            // Wait for results
            wait.until(ExpectedConditions.urlContains("search"));
            
            String currentUrl = driver.getCurrentUrl();
            logger.info("Current URL after search: " + currentUrl);
            
            Assert.assertTrue(
                currentUrl.contains("search") || currentUrl.contains("Selenium"),
                "URL should indicate search was performed"
            );
            
            logger.info("Test testWikipediaSearch passed");
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in testWikipediaSearch: " + e.getMessage(), e);
            throw new RuntimeException("Test execution failed", e);
        }
    }

    /**
     * Verifies page navigation and browser history.
     */
    @Test(priority = 4)
    public void testBrowserNavigation() {
        logger.info("Starting test: testBrowserNavigation");
        try {
            WebDriver driver = getDriver();
            
            // Navigate to first page
            String firstUrl = "https://www.google.com";
            logger.info("Navigating to: " + firstUrl);
            driver.get(firstUrl);
            
            // Navigate to second page
            String secondUrl = WIKIPEDIA_URL;
            logger.info("Navigating to: " + secondUrl);
            driver.get(secondUrl);
            
            // Go back
            logger.info("Navigating back...");
            driver.navigate().back();
            
            String currentUrl = driver.getCurrentUrl();
            logger.info("Current URL after back: " + currentUrl);
            
            Assert.assertTrue(currentUrl.contains("google"), 
                "Should be back on Google page");
            
            // Go forward
            logger.info("Navigating forward...");
            driver.navigate().forward();
            
            currentUrl = driver.getCurrentUrl();
            logger.info("Current URL after forward: " + currentUrl);
            
            Assert.assertTrue(currentUrl.contains("wikipedia"), 
                "Should be on Wikipedia page");
            
            logger.info("Test testBrowserNavigation passed");
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in testBrowserNavigation: " + e.getMessage(), e);
            throw new RuntimeException("Test execution failed", e);
        }
    }

    /**
     * Cleans up WebDriver after all tests complete.
     */
    @AfterClass
    public void tearDown() {
        logger.info("Starting teardown for AdditionalTests...");
        try {
            quitDriver();
            logger.info("AdditionalTests teardown completed");
        } catch (Exception e) {
            logger.error("Error during teardown: " + e.getMessage(), e);
        }
    }
}
