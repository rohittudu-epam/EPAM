package com.epam.campus.selenium.pages;

import com.epam.campus.selenium.base.BasePage;
import com.epam.campus.selenium.components.ProductCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage{

    protected WebDriver driver;

    private final By inventory_items = By.cssSelector("inventory_item");
    private final By productsTitle = By.cssSelector(".title");
    private final By productCards = By.cssSelector("div.inventory_item");
    private final By productName = By.cssSelector("div.inventory_item_name");
    private final By productPrice = By.cssSelector("div.inventory_item_price");
    private final By addToCartButton = By.cssSelector("button.btn_primary");
    private final By removeFromCartButton = By.cssSelector("button.btn_secondary");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(inventory_items));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ProductCard> getAllProductCards() {
        List<WebElement> elements = driver.findElements(productCards);
        return elements.stream()
                .map(element -> new ProductCard(
                        element.findElement(productName).getText(),
                        Double.parseDouble(element.findElement(productPrice).getText().replace("$", "")),
                        element.findElement(addToCartButton)
                ))
                .toList();
    }

    public ProductCard getProductCardByName(String name) {
        return getAllProductCards().stream()
                .filter(card -> card.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product with name " + name + " not found"));
    }

    public void addProductToCart(String name) {
        ProductCard card = getProductCardByName(name);
        card.getAddToCartButton().click();
    }

    public void removeProductFromCart(String name) {
        ProductCard card = getProductCardByName(name);
        card.getRemoveFromCartButton().click();
    }

    public int getCartBadgeCount() {
            WebElement badge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
            return badge.isDisplayed() ? Integer.parseInt(badge.getText()) : 0;
    }

    public void openCart() {

    }

    public void sortByVisibleText(String option) {}

    public List<String> getVisibleProductNames() {}

    public List<Double> getVisibleProductPrices() {}

    public void openMenu() {}

    public void logout() {}
}
