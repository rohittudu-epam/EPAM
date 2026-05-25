package base;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import enums.Browser;
import factory.DriverFactory;

public class BaseTest {

    private static final Properties TEST_CONFIG = loadTestConfig();

    private static Properties loadTestConfig() {
        String configFileName = resolveConfigFileName();
        String configPath = "configs/" + configFileName;
        Properties properties = new Properties();
        try (InputStream inputStream = BaseTest.class.getClassLoader()
                .getResourceAsStream(configPath)) {
            if (inputStream == null) {
                throw new RuntimeException("Unable to find " + configPath + " in classpath");
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Unable to load test config", e);
        }
    }

    private static String resolveConfigFileName() {
        String env = System.getProperty("env", "local").trim();
        return env.endsWith(".properties") ? env : env + ".properties";
    }

    @Parameters({"browser"})
    @BeforeMethod
    protected void setup(Method method, @Optional("") String browser){
        String resolvedBrowser = browser == null || browser.isBlank()
                ? getConfig("default.browser", "CHROME")
                : browser;

        System.setProperty("grid.enabled", getConfig("grid.enabled", "false"));
        System.setProperty("grid.url", getConfig("grid.url", "http://localhost:4444/wd/hub"));

        Browser currentBrowser = Browser.valueOf(resolvedBrowser.toUpperCase());
        DriverFactory.initDriver(currentBrowser);
    }

    public WebDriver getDriver(){
        return DriverFactory.getBrowserDriver();
    }

    protected String getConfig(String key) {
        String value = TEST_CONFIG.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Missing test config key: " + key);
        }
        return value.trim();
    }

    protected String getConfig(String key, String defaultValue) {
        String value = TEST_CONFIG.getProperty(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value.trim();
    }

    @AfterMethod
    protected void teardown(){
        DriverFactory.quitDriver();
    }

}
