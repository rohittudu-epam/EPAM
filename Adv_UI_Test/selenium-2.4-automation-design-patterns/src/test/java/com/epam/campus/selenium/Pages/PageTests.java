package com.epam.campus.selenium.Pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.campus.selenium.Base;
import com.epam.campus.selenium.pages.CartPage;
import com.epam.campus.selenium.pages.CheckoutCompletePage;
import com.epam.campus.selenium.pages.CheckoutPageOne;
import com.epam.campus.selenium.pages.CheckoutPageTwo;
import com.epam.campus.selenium.pages.InventoryPage;
import com.epam.campus.selenium.pages.LoginPage;

public class PageTests extends Base {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setupTestCase() {
        driver = getDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.loginAs("standard_user", "secret_sauce");
        inventoryPage = new InventoryPage(driver);
        inventoryPage.clearCartFromInventory();
    }

    @AfterMethod
    public void cleanupTestCase() {
        driver.manage().deleteAllCookies();
    }

    @Test(priority = 1)
    public void testLoginPage() {
        Assert.assertTrue(loginPage.getCurrentUrl().contains("inventory.html"),
                "Login should navigate to inventory page.");
    }

    @Test(priority = 2)
    public void testInventoryPage() {
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Inventory page should be visible after login.");
        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartItemsCount(), 1, "Cart badge count should be 1 after adding item.");
    }

    @Test(priority = 3)
    public void testCartPage() {
        addBackpackAndOpenCart();
        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart", "Cart page should be open.");
        Assert.assertEquals(cartPage.getItemsCount(), 1, "Cart should contain one selected item.");
    }

    @Test(priority = 4)
    public void testCheckoutPageOne() {
        addBackpackAndOpenCart();
        new CartPage(driver).clickCheckout();
        CheckoutPageOne checkoutPageOne = new CheckoutPageOne(driver);

        Assert.assertEquals(checkoutPageOne.getPageTitle(), "Checkout: Your Information",
                "Checkout information page should be open.");
        checkoutPageOne.continueCheckout("Test", "User", "12345");
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"),
                "Continue should navigate to checkout overview page.");
    }

    @Test(priority = 5)
    public void testCheckoutPageTwo() {
        CheckoutPageOne checkoutPageOne = goToCheckoutStepOne();
        checkoutPageOne.continueCheckout("Test", "User", "12345");
        CheckoutPageTwo checkoutPageTwo = new CheckoutPageTwo(driver);

        Assert.assertEquals(checkoutPageTwo.getPageTitle(), "Checkout: Overview",
                "Checkout overview page should be open.");
        Assert.assertEquals(checkoutPageTwo.getItemsCount(), 1, "Checkout overview should contain selected item.");
    }

    @Test(priority = 6)
    public void testCheckoutCompletePage() {
        CheckoutPageOne checkoutPageOne = goToCheckoutStepOne();
        checkoutPageOne.continueCheckout("Test", "User", "12345");
        CheckoutPageTwo checkoutPageTwo = new CheckoutPageTwo(driver);
        checkoutPageTwo.finishCheckout();
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);

        Assert.assertEquals(checkoutCompletePage.getPageTitle(), "Checkout: Complete!",
                "Checkout completion page should be open.");
        Assert.assertEquals(checkoutCompletePage.getSuccessMessage(), "Thank you for your order!",
                "Completion page should show order success message.");
    }

    private void addBackpackAndOpenCart() {
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();
    }

    private CheckoutPageOne goToCheckoutStepOne() {
        addBackpackAndOpenCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        return new CheckoutPageOne(driver);
    }
}
