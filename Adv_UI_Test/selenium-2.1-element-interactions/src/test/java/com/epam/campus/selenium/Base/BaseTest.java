package com.epam.campus.selenium.Base;

import com.epam.campus.selenium.Utils.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

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
