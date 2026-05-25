package com.epam.campus.bdd.factory;

import com.epam.campus.bdd.utils.Browser;
import com.epam.campus.bdd.constants.FrameworkConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static void initDriver(Browser browser) {
        WebDriver driver = switch (browser) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver();
        };
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FrameworkConstants.IMPLICIT_WAIT));
        webDriver.set(driver);
    }

    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            throw new IllegalStateException("Driver is not initialized. Call initDriver() first.");
        }
        return webDriver.get();
    }

    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}
