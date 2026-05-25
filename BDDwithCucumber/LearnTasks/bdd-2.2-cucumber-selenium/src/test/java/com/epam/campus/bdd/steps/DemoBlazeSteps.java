package com.epam.campus.bdd.steps;

import com.epam.campus.bdd.utils.WebDriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DemoBlazeSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://www.demoblaze.com";

    private WebDriver getDriver() {
        if (driver == null) {
            driver = WebDriverFactory.getDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }
        return driver;
    }

    private WebDriverWait getWait() {
        getDriver();
        return wait;
    }

    @Given("the user is on the DemoBlaze home page")
    public void theUserIsOnTheDemoBlazeHomePage() {
        getDriver().get(BASE_URL);
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
    }

    @When("the user clicks on the {string} category")
    public void theUserClicksOnTheCategory(String category) {
        WebElement categoryLink = getWait().until(
                ExpectedConditions.elementToBeClickable(By.linkText(category)));
        categoryLink.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("the user should see a list of laptop products")
    public void theUserShouldSeeAListOfLaptopProducts() {
        List<WebElement> products = getWait().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("#tbodyid .card-title a")));
        assertFalse("No laptop products found", products.isEmpty());
    }

    @Then("the user should see a list of phone products")
    public void theUserShouldSeeAListOfPhoneProducts() {
        List<WebElement> products = getWait().until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("#tbodyid .card-title a")));
        assertFalse("No phone products found", products.isEmpty());
    }

    @When("the user clicks on a product {string}")
    public void theUserClicksOnAProduct(String productName) {
        WebElement product = getWait().until(
                ExpectedConditions.elementToBeClickable(By.linkText(productName)));
        product.click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-content")));
    }

    @When("the user clicks the {string} button")
    public void theUserClicksTheButton(String buttonText) {
        WebElement button = getWait().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(text(),'" + buttonText + "')]")));
        button.click();
    }

    @Then("an alert confirms the product was added to the cart")
    public void anAlertConfirmsTheProductWasAddedToTheCart() {
        Alert alert = getWait().until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        assertTrue("Alert text does not confirm product added",
                alertText.contains("Product added"));
        alert.accept();
    }

    @When("the user navigates to the cart")
    public void theUserNavigatesToTheCart() {
        WebElement cartLink = getWait().until(
                ExpectedConditions.elementToBeClickable(By.id("cartur")));
        cartLink.click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("the product {string} should be visible in the cart")
    public void theProductShouldBeVisibleInTheCart(String productName) {
        List<WebElement> cartItems = getDriver().findElements(By.xpath(
                "//tr/td[contains(text(),'" + productName + "')]"));
        assertFalse("Product '" + productName + "' not found in cart", cartItems.isEmpty());
    }

    @Then("the product {string} should not be visible in the cart")
    public void theProductShouldNotBeVisibleInTheCart(String productName) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        List<WebElement> cartItems = getDriver().findElements(By.xpath(
                "//tr/td[contains(text(),'" + productName + "')]"));
        assertTrue("Product '" + productName + "' is still visible in cart", cartItems.isEmpty());
    }

    @When("the user removes the product {string} from the cart")
    public void theUserRemovesTheProductFromTheCart(String productName) {
        WebElement deleteLink = getWait().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//tr[td[contains(text(),'" + productName + "')]]//a[text()='Delete']")));
        deleteLink.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("the user clicks {string}")
    public void theUserClicks(String buttonText) {
        WebElement button = getWait().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'" + buttonText + "')]")));
        button.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("the user fills in the order form with the following details:")
    public void theUserFillsInTheOrderFormWithTheFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> details = data.get(0);

        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        getDriver().findElement(By.id("name")).sendKeys(details.get("Name"));
        getDriver().findElement(By.id("country")).sendKeys(details.get("Country"));
        getDriver().findElement(By.id("city")).sendKeys(details.get("City"));
        getDriver().findElement(By.id("card")).sendKeys(details.get("Card"));
        getDriver().findElement(By.id("month")).sendKeys(details.get("Month"));
        getDriver().findElement(By.id("year")).sendKeys(details.get("Year"));
    }

    @Then("the user should see a purchase confirmation message")
    public void theUserShouldSeeAPurchaseConfirmationMessage() {
        WebElement confirmation = getWait().until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")));
        assertNotNull("Purchase confirmation not displayed", confirmation);
        assertTrue(confirmation.isDisplayed());

        // Close the confirmation dialog
        WebElement okButton = getDriver().findElement(
                By.xpath("//button[contains(text(),'OK')]"));
        okButton.click();
    }
}
