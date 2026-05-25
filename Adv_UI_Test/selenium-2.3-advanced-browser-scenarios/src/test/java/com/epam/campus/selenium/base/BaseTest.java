package com.epam.campus.selenium.base;

import com.epam.campus.selenium.enums.Browser;
import com.epam.campus.selenium.factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(Method method, @Optional("CHROME") String browser) {
        Browser browserType = Browser.valueOf(browser.toUpperCase());
        DriverFactory.initDriver(browserType);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod
    public void teardown() {
        DriverFactory.quitDriver();
    }

}
