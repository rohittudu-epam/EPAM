package com.epam.campus.selenium.Factory;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.epam.campus.selenium.Enums.Browser;

public class WebDriverFactory {
    private static ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);
    
    public static void initDriver(Browser browser) {
        if (driver.get() == null) {
            driver.set(configureDriver(createDriver(browser)));
        }
    }

    public static WebDriver createDriver(Browser browser) {
        return switch(browser) {
            case CHROME -> new ChromeDriver(getChromeOptions());
            case FIREFOX -> new FirefoxDriver(getFirefoxOptions());
            case EDGE -> new EdgeDriver(getEdgeOptions());
        };
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordCheck,PasswordManagerOnboarding,AutofillServerCommunication");
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordCheck,PasswordManagerOnboarding,AutofillServerCommunication");
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("signon.rememberSignons", false);
        options.addPreference("extensions.formautofill.addresses.enabled", false);
        options.addPreference("extensions.formautofill.creditCards.enabled", false);
        return options;
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            throw new IllegalStateException("WebDriver instance is not initialized. Call initDriver() first.");
        }
        return driver.get();
    }

    public static WebDriver configureDriver(WebDriver webDriver) {
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        return webDriver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static boolean isDriverActive(WebDriver webDriver) {
        return webDriver != null;
    }
}
