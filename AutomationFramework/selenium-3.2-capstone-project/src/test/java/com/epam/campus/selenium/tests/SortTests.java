package com.epam.campus.selenium.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.campus.selenium.pages.ProductPage;

public class SortTests extends BaseTest {

    @Test(description = "TC7a: Validate sorting by Name (A to Z)")
    public void testSortByNameAtoZ() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        productPage.sortBy("Name (A to Z)");
        List<String> productNames = productPage.getProductNames();

        List<String> sorted = new ArrayList<>(productNames);
        Collections.sort(sorted, String.CASE_INSENSITIVE_ORDER);

        Assert.assertEquals(productNames, sorted,
                "Products should be sorted by Name A to Z");
    }

    @Test(description = "TC7b: Validate sorting by Name (Z to A)")
    public void testSortByNameZtoA() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        productPage.sortBy("Name (Z to A)");
        List<String> productNames = productPage.getProductNames();

        List<String> sorted = new ArrayList<>(productNames);
        sorted.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

        Assert.assertEquals(productNames, sorted,
                "Products should be sorted by Name Z to A");
    }

    @Test(description = "TC7c: Validate sorting by Price (Low to High)")
    public void testSortByPriceLowToHigh() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        productPage.sortBy("Price (low to high)");
        List<Double> prices = productPage.getProductPrices();

        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);

        Assert.assertEquals(prices, sorted,
                "Products should be sorted by Price Low to High");
    }

    @Test(description = "TC7d: Validate sorting by Price (High to Low)")
    public void testSortByPriceHighToLow() {
        loginWithValidCredentials();
        ProductPage productPage = new ProductPage(driver);

        productPage.sortBy("Price (high to low)");
        List<Double> prices = productPage.getProductPrices();

        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());

        Assert.assertEquals(prices, sorted,
                "Products should be sorted by Price High to Low");
    }
}
