package com.epam.campus.selenium.tests;

import com.epam.campus.selenium.base.BaseTest;
import com.epam.campus.selenium.pages.LoginPage;
import com.epam.campus.selenium.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @DataProvider(name = "ValidLoginTests")
    public Object[][] validLoginDataProvider() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "InvalidLoginTests")
    public Object[][] invalidLoginDataProvider() {
        return new Object[][] {
                {"ghost_user", "secret_sauce"},
                {"rain_man", "secret_sauce"},
                {"hail_mary", "secret_sauce"}
        };
    }

    @Test(dataProvider = "ValidLoginTests")
    public void TestValidLogin(String username, String password){
        WebDriver driver = getDriver();
        String URI = ConfigReader.getValue("base.uri");
        driver.get(URI);

        LoginPage loginpage = new LoginPage(driver);

        loginpage.loginAs(username, password);
        Assert.assertFalse(loginpage.getCurrentUri().equals(URI));
    }

    @Test(dataProvider = "InvalidLoginTests")
    public void TestInvalidLogin(String username, String password){
        WebDriver driver = getDriver();
        String URI = ConfigReader.getValue("base.uri");

        driver.get(URI);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(username, password);

        Assert.assertTrue(loginPage.getCurrentUri().equals(URI));
    }
}
