package com.epam.campus.selenium.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserConfigs {

    private BrowserConfigs() {
    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = getChromePrefs();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordCheck,PasswordManagerOnboarding,AutofillServerCommunication");
        return options;
    }

    private static Map<String, Object> getChromePrefs() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        return prefs;
    }

    public static EdgeOptions getEdgeOptions() {
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

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("signon.rememberSignons", false);
        options.addPreference("extensions.formautofill.addresses.enabled", false);
        options.addPreference("extensions.formautofill.creditCards.enabled", false);
        return options;
    }
}
