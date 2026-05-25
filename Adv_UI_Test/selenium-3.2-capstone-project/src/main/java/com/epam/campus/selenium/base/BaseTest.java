package com.epam.campus.selenium.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.epam.campus.selenium.utils.Browser;
import com.epam.campus.selenium.utils.ConfigReader;
import com.epam.campus.selenium.utils.DriverFactory;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setup(Method method) {
        Browser currentBrowser = Browser.valueOf(ConfigReader.getValue("browser"));
        DriverFactory.initDriver(currentBrowser);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        DriverFactory.quitDriver();
    }

}