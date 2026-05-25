package com.epam.campus.bdd.context;

import com.epam.campus.bdd.factory.DriverFactory;
import com.epam.campus.bdd.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class TestContext {
    private WebDriver driver;
    private LoginPage loginPage;

    public WebDriver getDriver() {
        if (driver == null) {
            driver = DriverFactory.getDriver();
        }
        return driver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(getDriver());
        }
        return loginPage;
    }
}

