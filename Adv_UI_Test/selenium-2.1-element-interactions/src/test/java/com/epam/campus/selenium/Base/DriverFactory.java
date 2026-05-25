package com.epam.campus.selenium.Base;

import com.epam.campus.selenium.Utils.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    protected static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);

    public static void initDriver(Browser browser){
        WebDriver currentDriver = createDriver(browser);
        driver.set(configureDriver(currentDriver));

        System.out.println("Driver Configured...");
    }

    public static WebDriver createDriver(Browser browser){
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

    public static WebDriver getDriver(){
        if (isDriverActive()){
            return driver.get();
        } else {
            throw new IllegalStateException("Driver Not Initialized");
        }
    }

    public static WebDriver configureDriver(WebDriver webDriver){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();

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
