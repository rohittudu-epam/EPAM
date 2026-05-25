package com.epam.campus.selenium.factory;

import org.openqa.selenium.WebDriver;
import com.epam.campus.selenium.enums.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {
    protected static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);

    public static void initDriver(Browser browser) {
        WebDriver currentBrowser = createDriver(browser);
        driver.set(configureDriver(currentBrowser));

        System.out.println("Driver Configured");
    }

    protected static WebDriver createDriver(Browser browser) {
        return switch (browser) {
            case CHROME -> new ChromeDriver();
            case EDGE -> new EdgeDriver();
            case FIREFOX -> new FirefoxDriver();
            default -> throw new RuntimeException("Invalid driver request");
        };
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    protected static WebDriver configureDriver(WebDriver webDriver) {
        // implicit Wait
        // webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();

        return webDriver;
    }

    public static boolean isDriverActive() {
        return driver.get() != null;
    }

    public static void quitDriver() {
        try {
            if (isDriverActive()) {
                driver.get().quit();
                driver.remove();

                System.out.println("Driver Exit Successfully!!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Driver Not Initialized");
        }
    }
}
