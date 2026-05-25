package com.epam.campus.selenium.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private static final By CART_ITEMS = By.className("cart_item");
    private static final By CART_ITEM_NAMES = By.className("inventory_item_name");
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    private static final By REMOVE_BUTTONS = By.cssSelector("button[id^='remove']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        return findElements(CART_ITEMS).size();
    }

    public List<String> getCartItemNames() {
        return findElements(CART_ITEM_NAMES)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void removeItem(int index) {
        findElements(REMOVE_BUTTONS).get(index).click();
    }

    public CheckoutPage clickCheckout() {
        click(CHECKOUT_BUTTON);
        return new CheckoutPage(driver);
    }
}
