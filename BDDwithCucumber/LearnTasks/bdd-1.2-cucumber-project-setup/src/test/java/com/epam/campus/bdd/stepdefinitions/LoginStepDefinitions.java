package com.epam.campus.bdd.stepdefinitions;

import com.epam.campus.bdd.constants.FrameworkConstants;
import com.epam.campus.bdd.context.TestContext;
import com.epam.campus.bdd.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginStepDefinitions {

    private final TestContext testContext;
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("User on the login page")
    public void user_on_the_login_page() {
        driver = testContext.getDriver();
        loginPage = testContext.getLoginPage();
        driver.get(FrameworkConstants.BASE_URL);
        assertEquals(FrameworkConstants.BASE_URL, driver.getCurrentUrl());
    }

    @When("User enter valid credentials")
    public void user_enter_valid_credentials() {
        loginPage.enterUsername(FrameworkConstants.VALID_USERNAME);
        loginPage.enterPassword(FrameworkConstants.VALID_PASSWORD);
        loginPage.clickLoginButton();
    }

    @When("User enter invalid credentials")
    public void user_enter_invalid_credentials() {
        loginPage.enterUsername(FrameworkConstants.INVALID_USERNAME);
        loginPage.enterPassword(FrameworkConstants.INVALID_PASSWORD);
    }

    @When("User leave the username and password fields empty")
    public void user_leave_the_username_and_password_fields_empty() {
        // Fields are already empty, nothing to do
    }

    @And("User click on the login button")
    public void user_click_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("User should be redirected to the dashboard")
    public void user_should_be_redirected_to_the_dashboard() {
        assertEquals(FrameworkConstants.DASHBOARD_URL, driver.getCurrentUrl());
    }

    @Then("User should see an error message")
    public void user_should_see_an_error_message() {
        assertTrue(loginPage.isErrorMessageDisplayed());
        String errorMsg = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Username and password do not match any user in this service", errorMsg);
    }

    @Then("User should see a validation error message")
    public void user_should_see_a_validation_error_message() {
        assertTrue(loginPage.isErrorMessageDisplayed());
        String errorMsg = loginPage.getErrorMessage();
        assertEquals("Epic sadface: Username is required", errorMsg);
    }
}
