package com.epam.campus.selenium.tests;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.epam.campus.selenium.pages.LoginPage;
import com.epam.campus.selenium.utils.ConfigReader;
import com.epam.campus.selenium.utils.DriverManager;

public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void suiteSetup() {
        ConfigReader.initialize();
        logger.info("Test suite initialized");
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.createDriver();
        driver.get(ConfigReader.getProperty("base.url"));
        logger.info("Browser navigated to {}", ConfigReader.getProperty("base.url"));
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        logger.info("Browser session closed");
    }

    @AfterSuite
    public void suiteTearDown() {
        logger.info("Test suite completed");
    }

    protected void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );
    }
}
