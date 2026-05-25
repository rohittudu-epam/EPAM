package com.epam.campus.selenium.factory;


import com.epam.campus.selenium.enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> webDriver = ThreadLocal.withInitial(() -> null);


    public static void initDriver(Browser browser) {

        WebDriver currentWebDriver = createDriver(browser);
        webDriver.set(configureWebDriver(currentWebDriver));
    }


    public static WebDriver createDriver(Browser browser) {
        return switch (browser) {
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                yield new ChromeDriver(options);
            }
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                yield new FirefoxDriver(options);
            }
            case EDGE -> {
                EdgeOptions options = new EdgeOptions();
                yield new EdgeDriver(options);
            }
            default -> {
                throw new RuntimeException("Invalid Driver Request");
            }
        };
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static WebDriver configureWebDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public static boolean isBrowserActive() {
        return webDriver.get() != null;
    }

    public static void quitDriver() {

        try {
            if (isBrowserActive()) {
                webDriver.get().quit();
                webDriver.remove();
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to Exit the Driver:", e);
        }
    }
}
