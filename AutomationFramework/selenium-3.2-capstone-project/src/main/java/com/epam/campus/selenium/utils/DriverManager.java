package com.epam.campus.selenium.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {

    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver createDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        boolean headless = ConfigReader.getBooleanProperty("headless", false);
        int implicitWait = ConfigReader.getIntProperty("implicit.wait.seconds", 0);

        WebDriver driver;

        switch (browser) {
            case "firefox":
                FirefoxOptions ffOptions = BrowserConfigs.getFirefoxOptions();
                if (headless) {
                    ffOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(ffOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = BrowserConfigs.getEdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
            case "chrome":
            default:
                ChromeOptions chromeOptions = BrowserConfigs.getChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        if (implicitWait > 0) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        }
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
        logger.info("Created {} driver (headless={})", browser, headless);
        return driver;
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
            logger.info("Driver quit and removed from thread");
        }
    }
}
