package stepdefinitions;

import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.InventoryPage;
import pages.LoginPage;


public class LoginSteps {

    private LoginPage loginPage() {
        return new LoginPage(DriverFactory.getDriver());
    }

    private InventoryPage inventoryPage() {
        return new InventoryPage(DriverFactory.getDriver());
    }

    @Given("user is on login page")
    public void user_is_on_login_page() {
        // Already handled in hooks
    }

    @When("user logs in with username {string} and password {string}")
    public void enter_credentials(String username, String password) {
        loginPage().enterUsername(username);
        loginPage().enterPassword(password);
    }

    @And("clicks on login button")
    public void click_login() {
        loginPage().clickLogin();
    }

    @Then("user should be navigated to inventory page")
    public void verify_login() {
        String title = inventoryPage().getPageTitle();
        Assert.assertEquals(title, "Products", "Page title mismatch after login");
    }

    @Then("error message should be displayed {string}")
    public void verify_error_message(String expectedMessage) {
        String actualMessage = loginPage().getErrorMessage();
        if (actualMessage.equals(expectedMessage)) {
            System.out.println("Error message verification passed");
        } else {
            System.out.println("Error message verification failed");
            System.out.println("Expected: " + expectedMessage);
            System.out.println("Actual: " + actualMessage);
        }
    }
}
