package Grid;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

import enums.Browser;
import factory.DriverFactory;

public class GridSetup {

    @Parameters({"os", "browser"})
    public void setup(String os, String browser) {
        Browser currentBrowser = Browser.valueOf(browser.toUpperCase());
        DriverFactory.initDriver(currentBrowser);
    }

    public WebDriver getDriver() {
        return DriverFactory.getBrowserDriver();
    }

    public void teardown() {
        DriverFactory.quitDriver();
    }
}
