package com.epam.campus.selenium;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(Method method, @Optional("CHROME") String browser){
        Browser initBrowser = Browser.valueOf(browser.toUpperCase());
        try {
            DriverFactory.initDriver(initBrowser);
        } catch (Exception e) {
            System.out.println("Exception Occurred during setup");
        }
    }

    protected WebDriver getDriver(){
        return DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown(Method method){
        DriverFactory.quitDriver();
    }
}
