package factory;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import enums.Browser;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> webDriver = ThreadLocal.withInitial(() -> null);

    private static final String GRID_ENABLED = "grid.enabled";
    private static final String GRID_URL = "grid.url";
    private static final String DEFAULT_GRID_URL = "http://localhost:4444/wd/hub";


    public static void initDriver(Browser browser){

        WebDriver currentWebDriver = createDriver(browser);
        webDriver.set(configureWebDriver(currentWebDriver));
    }

    public static WebDriver getBrowserDriver(){
        return webDriver.get();
    }

    public static WebDriver createDriver(Browser browser){
        if (isGridEnabled()) {
            return createRemoteDriver(browser);
        }

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

    private static boolean isGridEnabled() {
        return Boolean.parseBoolean(System.getProperty(GRID_ENABLED, "false"));
    }

    private static WebDriver createRemoteDriver(Browser browser) {
        String hubUrl = System.getProperty(GRID_URL, DEFAULT_GRID_URL);

        try {
            URL remoteUrl = URI.create(hubUrl).toURL();
            return switch (browser) {
                case CHROME -> new RemoteWebDriver(remoteUrl, new ChromeOptions());
                case FIREFOX -> new RemoteWebDriver(remoteUrl, new FirefoxOptions());
                case EDGE -> new RemoteWebDriver(remoteUrl, new EdgeOptions());
                default -> throw new RuntimeException("Invalid Driver Request for Grid");
            };
        } catch (IllegalArgumentException | MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL: " + hubUrl, e);
        }
    }

    public static WebDriver configureWebDriver(WebDriver driver){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    public static boolean isBrowserActive(){
        return webDriver.get() != null;
    }

    public static void quitDriver(){
        try{
            if (isBrowserActive()){
                webDriver.get().quit();
                webDriver.remove();
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to Exit the Driver:", e);
        }
    }
}
