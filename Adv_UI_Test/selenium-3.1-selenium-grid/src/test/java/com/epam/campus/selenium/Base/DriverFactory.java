package com.epam.campus.selenium.Base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.epam.campus.selenium.Utils.Browser;
import com.epam.campus.selenium.Utils.ConfigReader;

public class DriverFactory {
    protected static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);
    private static final String DEFAULT_GRID_HUB_URL = "http://localhost:4444/wd/hub";
    private static final String DEFAULT_BROWSERSTACK_HUB_URL = "https://hub.browserstack.com/wd/hub";

    public static void initDriver(Browser browser){
        ExecutionMode mode = ConfigReader.getExecutionMode();
        WebDriver currentDriver = createDriver(browser, mode);
        driver.set(configureDriver(currentDriver));

        System.out.println("Driver configured with mode: " + mode);
    }

    public static WebDriver createDriver(Browser browser, ExecutionMode mode){
        return switch (mode) {
            case LOCAL -> createLocalDriver(browser);
            case GRID -> createGridDriver(browser);
            case BROWSERSTACK -> createBrowserStackDriver(browser);
        };
    }

    private static WebDriver createGridDriver(Browser browser) {
        String gridHubUrl = ConfigReader.get("grid.hub.url", DEFAULT_GRID_HUB_URL);
        boolean fallbackToLocal = ConfigReader.getBoolean("grid.fallbackToLocal", false);

        if (!isRemoteEndpointReachable(gridHubUrl)) {
            String message = "Unable to reach Selenium Grid at " + gridHubUrl
                    + ". Start the Grid first (for example: .\\start-grid-hub.ps1 -SeleniumServerJar \"selenium-server-4.29.0.jar\")";
            if (fallbackToLocal) {
                System.out.println(message + " Falling back to LOCAL mode because grid.fallbackToLocal=true.");
                return createLocalDriver(browser);
            }
            throw new IllegalStateException(message);
        }

        return createRemoteDriver(gridHubUrl, buildBrowserCapabilities(browser));
    }

    private static WebDriver createLocalDriver(Browser browser) {
        return switch(browser){
            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                yield new ChromeDriver(chromeOptions);
            }
            case FIREFOX -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                yield new FirefoxDriver(firefoxOptions);
            }
            case EDGE -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                yield new EdgeDriver(edgeOptions);
            }
        };
    }

    private static WebDriver createBrowserStackDriver(Browser browser) {
        MutableCapabilities capabilities = buildBrowserCapabilities(browser);
        capabilities.setCapability("browserVersion", ConfigReader.get("browser.version", "latest"));

        Map<String, Object> browserStackOptions = new HashMap<>();
        browserStackOptions.put("userName", ConfigReader.getRequired("browserstack.username"));
        browserStackOptions.put("accessKey", ConfigReader.getRequired("browserstack.accessKey"));
        browserStackOptions.put("buildName", ConfigReader.get("browserstack.buildName", "selenium-grid-build"));
        browserStackOptions.put("projectName", ConfigReader.get("browserstack.projectName", "selenium-grid-project"));
        browserStackOptions.put("os", ConfigReader.get("browserstack.os", "Windows"));
        browserStackOptions.put("osVersion", ConfigReader.get("browserstack.osVersion", "11"));
        browserStackOptions.put("local", ConfigReader.getBoolean("browserstack.local", false));

        capabilities.setCapability("bstack:options", browserStackOptions);

        return createRemoteDriver(
            ConfigReader.get("browserstack.hub.url", DEFAULT_BROWSERSTACK_HUB_URL),
                capabilities
        );
    }

    private static MutableCapabilities buildBrowserCapabilities(Browser browser) {
        return switch (browser) {
            case CHROME -> new ChromeOptions();
            case FIREFOX -> new FirefoxOptions();
            case EDGE -> new EdgeOptions();
        };
    }

    private static WebDriver createRemoteDriver(String hubUrl, Capabilities capabilities) {
        try {
            return new RemoteWebDriver(URI.create(hubUrl).toURL(), capabilities);
        } catch (MalformedURLException ex) {
            throw new IllegalStateException("Invalid remote hub URL: " + hubUrl, ex);
        }
    }

    private static boolean isRemoteEndpointReachable(String hubUrl) {
        String statusUrl = toStatusUrl(hubUrl);
        return canConnect(statusUrl) || canConnect(hubUrl);
    }

    private static String toStatusUrl(String hubUrl) {
        if (hubUrl.endsWith("/wd/hub")) {
            return hubUrl.replace("/wd/hub", "/status");
        }
        if (hubUrl.endsWith("/")) {
            return hubUrl + "status";
        }
        return hubUrl + "/status";
    }

    private static boolean canConnect(String endpoint) {
        try {
            HttpURLConnection connection = (HttpURLConnection) URI.create(endpoint).toURL().openConnection();
            connection.setConnectTimeout(2500);
            connection.setReadTimeout(2500);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            return responseCode >= 200 && responseCode < 500;
        } catch (IOException ex) {
            return false;
        }
    }

    public static WebDriver getDriver(){
        if (isDriverActive()){
            return driver.get();
        } else {
            throw new IllegalStateException("Driver Not Initialized");
        }
    }

    public static WebDriver configureDriver(WebDriver webDriver){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            webDriver.manage().window().maximize();
        } catch (Exception ignored) {
            // Some remote/mobile-like providers may not support maximize.
        }

        return webDriver;
    }

    public static boolean isDriverActive(){
        return driver.get() != null;
    }

    public static void quitDriver(){
        try {
            if (isDriverActive()){
                driver.get().quit();
                driver.remove();

                System.out.println("Driver Quit Successful...");
            }
        } catch (Exception e){

        }
    }
}
