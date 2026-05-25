package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);

    public static WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());

        getDriver().manage().window().maximize();

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        getDriver().quit();
        driver.remove();
    }
}
