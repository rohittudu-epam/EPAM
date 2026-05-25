package com.epam.campus.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.campus.selenium.pages.LoginPage;
import com.epam.campus.selenium.utils.ConfigReader;

public class LoginTests extends BaseTest {

    @Test(description = "TC1: Validate successful login with correct credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password")
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "User should be redirected to inventory page after successful login"
        );
    }

    @Test(description = "TC2: Validate unsuccessful login with invalid credentials")
    public void testUnsuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.getProperty("invalid.username"),
                ConfigReader.getProperty("invalid.password")
        );

        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Error message should be displayed for invalid credentials"
        );

        String errorText = loginPage.getErrorMessage();
        Assert.assertTrue(
                errorText.contains("Username and password do not match"),
                "Error message should indicate mismatched credentials. Actual: " + errorText
        );
    }

    @Test(description = "TC2b: Validate error displayed for empty username")
    public void testEmptyUsernameLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for empty username");
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username is required"),
                "Error should indicate username is required"
        );
    }

    @Test(description = "TC2c: Validate error displayed for empty password")
    public void testEmptyPasswordLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for empty password");
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Password is required"),
                "Error should indicate password is required"
        );
    }

    @Test(description = "TC2d: Validate locked-out user cannot login")
    public void testLockedOutUserLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Error message should be displayed for locked out user");
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("locked out"),
                "Error should indicate user is locked out"
        );
    }
}
