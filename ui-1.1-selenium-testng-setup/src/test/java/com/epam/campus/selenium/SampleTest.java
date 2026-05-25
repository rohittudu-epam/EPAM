package com.epam.campus.selenium;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Sample test class demonstrating Selenium WebDriver with TestNG.
 * Contains test cases for Google homepage verification and search functionality.
 * 
 * @author EPAM Campus
 * @version 1.0
 */
public class SampleTest {
    
    private static final Logger logger = LogManager.getLogger(SampleTest.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private Properties config;
    
    // Constants for test data
    private static final String CONFIG_FILE = "config.properties";
    private static final String GOOGLE_URL_KEY = "google.url";
    private static final String GOOGLE_TITLE_KEY = "google.expected.title";
    private static final String SEARCH_QUERY_KEY = "google.search.query";
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    /**
     * Initializes WebDriver before test execution.
     * Sets up ChromeDriver using WebDriverManager and configures implicit waits.
     */
    @BeforeClass
    public void setUp() {
        try {
            logger.info("Loading test configuration...");
            loadConfiguration();
            
            logger.info("Setting up WebDriverManager for Chrome...");
            WebDriverManager.chromedriver().setup();
            
            logger.info("Initializing ChromeDriver...");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
            driver.manage().window().maximize();
            
            wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
            logger.info("ChromeDriver initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize driver: " + e.getMessage(), e);
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    /**
     * Loads test configuration from properties file.
     */
    private void loadConfiguration() {
        config = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                logger.warn("Config file not found, using default values");
                setDefaultConfiguration();
                return;
            }
            config.load(input);
            logger.info("Configuration loaded successfully");
        } catch (IOException e) {
            logger.warn("Error loading config file, using defaults: " + e.getMessage());
            setDefaultConfiguration();
        }
    }

    /**
     * Sets default configuration values when config file is not available.
     */
    private void setDefaultConfiguration() {
        config.setProperty(GOOGLE_URL_KEY, "https://www.google.com");
        config.setProperty(GOOGLE_TITLE_KEY, "Google");
        config.setProperty(SEARCH_QUERY_KEY, "Selenium WebDriver");
    }

    /**
     * Verifies Google homepage loads correctly and displays expected title.
     */
    @Test(priority = 1)
    public void testGoogleHomePage() {
        logger.info("Starting test: testGoogleHomePage");
        try {
            String url = config.getProperty(GOOGLE_URL_KEY);
            String expectedTitle = config.getProperty(GOOGLE_TITLE_KEY);
            
            logger.info("Navigating to: " + url);
            driver.get(url);
            
            String actualTitle = driver.getTitle();
            logger.info("Page title retrieved: " + actualTitle);
            
            Assert.assertEquals(actualTitle, expectedTitle, "Page Title Mismatch");
            logger.info("Test testGoogleHomePage passed successfully");
        } catch (AssertionError e) {
            logger.error("Assertion failed in testGoogleHomePage: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in testGoogleHomePage: " + e.getMessage(), e);
            throw new RuntimeException("Test execution failed", e);
        }
    }

    /**
     * Verifies Google search functionality works correctly.
     * Performs a search and validates that results are displayed.
     */
//    @Test(priority = 2, dependsOnMethods = "testGoogleHomePage")
//    public void testGoogleSearch() {
//        logger.info("Starting test: testGoogleSearch");
//        try {
//            String searchQuery = config.getProperty(SEARCH_QUERY_KEY);
//
//            logger.info("Locating search input field...");
//            WebElement searchBox = wait.until(
//                ExpectedConditions.presenceOfElementLocated(By.name("q"))
//            );
//
//            logger.info("Entering search query: " + searchQuery);
//            searchBox.clear();
//            searchBox.sendKeys(searchQuery);
//            searchBox.sendKeys(Keys.ENTER);
//
//            logger.info("Waiting for search results...");
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));
//
//            // Verify search results contain the query term
//            String pageSource = driver.getPageSource();
//            Assert.assertTrue(pageSource.contains(searchQuery),
//                "Search results should contain the query term");
//
//            logger.info("Test testGoogleSearch passed successfully");
//        } catch (AssertionError e) {
//            logger.error("Assertion failed in testGoogleSearch: " + e.getMessage());
//            throw e;
//        } catch (Exception e) {
//            logger.error("Error in testGoogleSearch: " + e.getMessage(), e);
//            throw new RuntimeException("Test execution failed", e);
//        }
//    }

    /**
     * Verifies that Google page URL is correct after navigation.
     */
    @Test(priority = 3)
    public void testGooglePageUrl() {
        logger.info("Starting test: testGooglePageUrl");
        try {
            String expectedUrl = config.getProperty(GOOGLE_URL_KEY);
            
            logger.info("Navigating to: " + expectedUrl);
            driver.get(expectedUrl);
            
            String currentUrl = driver.getCurrentUrl();
            logger.info("Current URL: " + currentUrl);
            
            Assert.assertTrue(currentUrl.contains("google.com"), 
                "URL should contain google.com");
            logger.info("Test testGooglePageUrl passed successfully");
        } catch (AssertionError e) {
            logger.error("Assertion failed in testGooglePageUrl: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error in testGooglePageUrl: " + e.getMessage(), e);
            throw new RuntimeException("Test execution failed", e);
        }
    }

    /**
     * Cleans up WebDriver resources after all tests complete.
     * Uses quit() to properly terminate the driver process.
     */
    @AfterClass
    public void tearDown() {
        logger.info("Starting teardown...");
        try {
            if (driver != null) {
                driver.quit();
                logger.info("WebDriver quit successfully");
            }
        } catch (Exception e) {
            logger.error("Error during teardown: " + e.getMessage(), e);
        }
    }
}
