package com.epam.campus.selenium.utils;

import com.epam.campus.selenium.utils.Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(Browser browser) {
        if (isActive()) return;

        driver.set(configureDriver(createDriver(browser)));
    }

    public static WebDriver getDriver() {
        if (!isActive()) {
            throw new IllegalStateException("WebDriver is not initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    public static WebDriver createDriver(Browser browser) {
        if (browser == null) {
            throw new IllegalArgumentException("Browser cannot be null");
        }

        return switch (browser) {
            case CHROME -> new ChromeDriver(BrowserConfigs.getChromeOptions());
            case FIREFOX -> new FirefoxDriver(BrowserConfigs.getFirefoxOptions());
            case EDGE -> new EdgeDriver(BrowserConfigs.getEdgeOptions());
        };
    }

    protected static WebDriver configureDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static boolean isActive() {
        return driver.get() != null;
    }

    public static void quitDriver() {
        WebDriver currentDriver = driver.get();

        if (currentDriver == null) return;

        try {
            currentDriver.quit();
        } finally {
            driver.remove();
        }
    }
}

