package com.epam.campus.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
//    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    public static void initDriver(Browser browser){
        logger.info("Initializing WebDriver for Browser: {}", browser);
        try {
            WebDriver webDriver = createDriver(browser);
            configureDriver(webDriver);
            driver.set(webDriver);
            logger.info("{} WebDriver Initialized Successfully...", browser);
        } catch (Exception e) {
            logger.error("Failed to Initialize WebDriver for {}: {}", browser, e);
            throw new RuntimeException("WebDriver Initialization Failed", e);
        }
    }

    public static WebDriver createDriver(Browser browser){
        if (browser == null) {
            throw new IllegalArgumentException("Browser type cannot be null");
        }

        return switch (browser){
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
        };
    }

    private static void configureDriver(WebDriver webDriver){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT_SECONDS));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        webDriver.manage().window().maximize();
//        webDriver.manage().window().set`Size(new Dimension(1920, 1080));
        logger.info("WebDriver successfully Configured....");
    }

    public static WebDriver getDriver(){
        if (!isDriverActive()){
            throw new IllegalStateException("Web Driver not Initialized.");
        }

        return driver.get();
    }

    public static void quitDriver(){
        try {
            if (isDriverActive()){
                driver.get().quit();
                driver.remove();
            } else {
                logger.debug("No active WebDriver instance found for current thread.");
            }
        } catch (Exception e){
            logger.error("Error occurred while quitting WebDriver", e);
            driver.remove();
        }
    }

    public static boolean isDriverActive(){
        return driver.get() != null;
    }
}
