package stepdefinitions;

import org.epam.campus.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    WebDriver driver;

    @Given("user is on login page")
    public void user_on_login_page() {
        driver = Hooks.driver;
        driver.get("https://www.saucedemo.com/");
    }

    @When("user enters username and password")
    public void enter_credentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.login();
    }

    @Then("user should be logged in")
    public void verify_login() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Login failed: URL does not contain 'inventory'");
    }
}
