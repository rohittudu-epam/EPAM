package com.epam.campus.selenium.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.epam.campus.selenium.enums.Browser;
import com.epam.campus.selenium.factory.DriverFactory;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(Method method, @Optional("CHROME") String browser){
        Browser initBrowser = Browser.valueOf(browser.toUpperCase());
        DriverFactory.initDriver(initBrowser);
    }

    public WebDriver getDriver(){
        return DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
