package com.epam.campus.selenium;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.epam.campus.selenium.Enums.Browser;
import com.epam.campus.selenium.Factory.WebDriverFactory;

public class Base {

    @Parameters({"browser"})
    @BeforeSuite
    public void setup(@Optional("CHROME") String browser) {
        Browser currentBrowser = Browser.valueOf(browser);
        WebDriverFactory.initDriver(currentBrowser);
    }

    public WebDriver getDriver() {
        return WebDriverFactory.getInstance();
    }

    @AfterSuite
    public void teardown() {
        WebDriverFactory.quitDriver();
    }
}
