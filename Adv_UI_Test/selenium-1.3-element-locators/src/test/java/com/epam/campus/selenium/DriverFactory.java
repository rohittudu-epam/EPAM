package com.epam.campus.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);

    public static void initDriver(Browser browser) throws Exception {

        try {
            System.out.println("Initializing WebDriver");
            WebDriver webDriver = createDriver(browser);
            configureDriver(webDriver);

            driver.set(webDriver);

            System.out.println("WebDriver Successfully Initialized");
        } catch (Exception e) {
            throw new Exception("Failed to Create WebDriver");
        }
    }

    public static WebDriver createDriver(Browser browser) {
        return switch (browser) {
            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();

                Map<String, Object> prefs = new HashMap<>();

                /*
                Disable save password popup
                Disable password manager
                Disable password breach detection
                 */
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);

                chromeOptions.setExperimentalOption("prefs", prefs);

                yield new ChromeDriver(chromeOptions);
            }
            case FIREFOX -> WebDriverManager.firefoxdriver().create();
            case EDGE -> WebDriverManager.edgedriver().create();
        };
    }

    public static void configureDriver(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        webDriver.manage().window().fullscreen();
    }

    public static WebDriver getDriver() {
        if (isDriverActive()) {
            return driver.get();
        } else {
            throw new IllegalStateException("Driver not initialized");
        }
    }

    public static void quitDriver() {
        try{
            if (isDriverActive()) {
                driver.get().quit();
                driver.remove();
                System.out.println("Driver quit Successful.");
            }
        } catch (Exception e) {

        }
    }

    public static boolean isDriverActive() {
        return driver.get() != null;
    }
}
