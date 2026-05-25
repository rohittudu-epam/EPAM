package com.epam.campus.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.campus.selenium.pages.CartPage;
import com.epam.campus.selenium.pages.CheckoutPage;
import com.epam.campus.selenium.pages.ProductPage;

public class ProductInteractionTests extends BaseTest {

    @Test(description = "TC3: Validate that product inventory is accessible post-login")
    public void testProductInventoryAccessible() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        Assert.assertTrue(productPage.isPageLoaded(),
                "Product inventory should be visible after login");
        Assert.assertTrue(productPage.getProductCount() > 0,
                "There should be at least one product in the inventory");
    }

    @Test(description = "TC4: Validate the ability to add a product to the cart")
    public void testAddProductToCart() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        Assert.assertEquals(productPage.getCartBadgeCount(), 0,
                "Cart should be empty initially");

        productPage.addProductToCart(0);

        Assert.assertEquals(productPage.getCartBadgeCount(), 1,
                "Cart badge should show 1 after adding a product");
    }

    @Test(description = "TC4b: Validate adding multiple products to the cart")
    public void testAddMultipleProductsToCart() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        productPage.addProductToCart(0);
        productPage.addProductToCart(1);

        Assert.assertEquals(productPage.getCartBadgeCount(), 2,
                "Cart badge should show 2 after adding two products");
    }

    @Test(description = "TC5: Validate the ability to remove a product from the cart")
    public void testRemoveProductFromCart() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        productPage.addProductToCart(0);
        Assert.assertEquals(productPage.getCartBadgeCount(), 1,
                "Cart badge should show 1 after adding a product");

        productPage.removeProduct(0);
        Assert.assertEquals(productPage.getCartBadgeCount(), 0,
                "Cart badge should disappear after removing the product");
    }

    @Test(description = "TC5b: Validate removing a product from within the cart page")
    public void testRemoveProductFromCartPage() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        productPage.addProductToCart(0);
        CartPage cartPage = productPage.goToCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "Cart should contain 1 item");

        cartPage.removeItem(0);
        Assert.assertEquals(cartPage.getCartItemCount(), 0,
                "Cart should be empty after removing the item");
    }

    @Test(description = "TC6: Validate the checkout process through to the Finish page")
    public void testCheckoutProcess() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        productPage.addProductToCart(0);
        CartPage cartPage = productPage.goToCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "Cart should contain 1 item");

        CheckoutPage checkoutPage = cartPage.clickCheckout();
        checkoutPage.fillCheckoutInfo("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertEquals(
                checkoutPage.getCompleteHeader(),
                "Thank you for your order!",
                "Order completion message should be displayed"
        );
    }
}
