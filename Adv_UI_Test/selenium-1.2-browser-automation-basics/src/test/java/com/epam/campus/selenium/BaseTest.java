package com.epam.campus.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public abstract class BaseTest {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(Method method, @Optional("CHROME") String browser){
        logger.info("STARTING TEST: {} | Thread: {}", method.getName(), Thread.currentThread().getId());

        Browser selectedBrowser = Browser.valueOf(browser.toUpperCase());
        DriverFactory.initDriver(selectedBrowser);
    }

    protected WebDriver getDriver(){
        return DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown(Method method){
        logger.info("ENDING TEST: {} | Thread: {}", method.getName(), Thread.currentThread().getId());

        DriverFactory.quitDriver();
    }
}
