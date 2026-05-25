package com.epam.campus.selenium.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {

    private static final By INVENTORY_ITEMS = By.className("inventory_item");
    private static final By INVENTORY_ITEM_NAMES = By.className("inventory_item_name");
    private static final By INVENTORY_ITEM_PRICES = By.className("inventory_item_price");
    private static final By SORT_DROPDOWN = By.className("product_sort_container");
    private static final By CART_BADGE = By.className("shopping_cart_badge");
    private static final By CART_LINK = By.className("shopping_cart_link");
    private static final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button[id^='add-to-cart']");
    private static final By REMOVE_BUTTON = By.cssSelector("button[id^='remove']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(INVENTORY_ITEMS));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductCount() {
        return findElements(INVENTORY_ITEMS).size();
    }

    public List<String> getProductNames() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(INVENTORY_ITEM_NAMES));
        return findElements(INVENTORY_ITEM_NAMES)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(INVENTORY_ITEM_PRICES));
        return findElements(INVENTORY_ITEM_PRICES)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void sortBy(String visibleText) {
        Select select = new Select(waitForClickable(SORT_DROPDOWN));
        select.selectByVisibleText(visibleText);
    }

    public void addProductToCart(int index) {
        List<WebElement> items = findElements(INVENTORY_ITEMS);
        items.get(index).findElement(ADD_TO_CART_BUTTON).click();
    }

    public void removeProduct(int index) {
        List<WebElement> items = findElements(INVENTORY_ITEMS);
        items.get(index).findElement(REMOVE_BUTTON).click();
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = findElements(CART_BADGE);
        if (badges.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badges.get(0).getText());
    }

    public CartPage goToCart() {
        click(CART_LINK);
        return new CartPage(driver);
    }

    public void openBurgerMenu() {
        click(BURGER_MENU_BUTTON);
    }

    public void clickLogout() {
        waitForClickable(LOGOUT_LINK).click();
    }
}
