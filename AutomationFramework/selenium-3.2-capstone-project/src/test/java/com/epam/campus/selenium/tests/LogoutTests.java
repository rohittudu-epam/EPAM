package com.epam.campus.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.campus.selenium.pages.LoginPage;
import com.epam.campus.selenium.pages.ProductPage;
import com.epam.campus.selenium.utils.ConfigReader;

public class LogoutTests extends BaseTest {

    @Test(description = "TC8: Validate that the user can successfully log out")
    public void testSuccessfulLogout() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        Assert.assertTrue(productPage.isPageLoaded(),
                "Should be on the inventory page before logout");

        productPage.openBurgerMenu();
        productPage.clickLogout();

        String expectedUrl = ConfigReader.getProperty("base.url");
        Assert.assertTrue(
                driver.getCurrentUrl().contains(expectedUrl),
                "User should be redirected to login page after logout"
        );
    }

    @Test(description = "TC8b: Validate login page elements visible after logout")
    public void testLoginPageAccessibleAfterLogout() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        productPage.openBurgerMenu();
        productPage.clickLogout();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertFalse(loginPage.isErrorDisplayed(),
                "No error should be present on clean login page after logout");
    }
}
