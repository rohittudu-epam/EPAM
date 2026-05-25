package com.epam.campus.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Factory class for creating and managing WebDriver instances.
 * Supports Chrome, Firefox, and Edge browsers with WebDriverManager integration.
 * Uses ThreadLocal for thread-safe driver management in parallel test execution.
 * 
 * @author EPAM Campus
 * @version 1.0
 */
public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    /**
     * Private constructor to prevent instantiation.
     */
    private DriverFactory() {
        // Utility class, prevent instantiation
    }

    /**
     * Initializes WebDriver for the specified browser.
     * Uses WebDriverManager for automatic driver binary management.
     * 
     * @param browser The browser type to initialize
     * @throws IllegalArgumentException if an invalid browser type is provided
     */
    public static void initDriver(Browser browser) {
        logger.info("Initializing WebDriver for browser: " + browser);
        try {
            WebDriver webDriver = createDriver(browser);
            configureDriver(webDriver);
            driver.set(webDriver);
            logger.info("WebDriver initialized successfully for: " + browser);
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver for " + browser + ": " + e.getMessage(), e);
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    /**
     * Creates a WebDriver instance for the specified browser.
     * 
     * @param browser The browser type
     * @return Configured WebDriver instance
     */
    private static WebDriver createDriver(Browser browser) {
        return switch (browser) {
            case CHROME -> {
                logger.info("Setting up ChromeDriver...");
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                yield new ChromeDriver(options);
            }
            case FIREFOX -> {
                logger.info("Setting up FirefoxDriver...");
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--start-maximized");
                yield new FirefoxDriver(options);
            }
            case EDGE -> {
                logger.info("Setting up EdgeDriver...");
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                yield new EdgeDriver(options);
            }
        };
    }

    /**
     * Configures common WebDriver settings.
     * 
     * @param webDriver The WebDriver instance to configure
     */
    private static void configureDriver(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();
        logger.info("WebDriver configured with implicit wait: " + DEFAULT_TIMEOUT_SECONDS + "s");
    }

    /**
     * Returns the WebDriver instance for the current thread.
     * 
     * @return The current WebDriver instance
     * @throws IllegalStateException if driver is not initialized
     */
    public static WebDriver getDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver == null) {
            logger.error("WebDriver not initialized. Call initDriver() first.");
            throw new IllegalStateException("WebDriver not initialized. Call initDriver() first.");
        }
        return currentDriver;
    }

    /**
     * Quits the WebDriver and removes it from ThreadLocal storage.
     * Properly terminates the browser process.
     */
    public static void quitDriver() {
        logger.info("Attempting to quit WebDriver...");
        try {
            WebDriver currentDriver = driver.get();
            if (currentDriver != null) {
                currentDriver.quit();
                driver.remove();
                logger.info("WebDriver quit successfully");
            } else {
                logger.warn("No WebDriver instance to quit");
            }
        } catch (Exception e) {
            logger.error("Error while quitting WebDriver: " + e.getMessage(), e);
            driver.remove();
        }
    }

    /**
     * Checks if a WebDriver instance is currently active.
     * 
     * @return true if driver is initialized, false otherwise
     */
    public static boolean isDriverActive() {
        return driver.get() != null;
    }
}
