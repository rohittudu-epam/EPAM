package Grid;

import org.openqa.selenium.WebDriver;

import enums.Browser;
import factory.DriverFactory;

public class SeleniumGrid {

    public static void main(String[] args) {
        System.setProperty("grid.enabled", "true");
        System.setProperty("grid.url", "http://localhost:4444/wd/hub");

        DriverFactory.initDriver(Browser.CHROME);
        WebDriver driver = DriverFactory.getBrowserDriver();
        driver.get("https://www.selenium.dev/");

        DriverFactory.quitDriver();
    }
}
