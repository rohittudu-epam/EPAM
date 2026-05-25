package com.epam.campus.selenium.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    private final WebDriver driver;

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(css = "button.btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_badge")
    private List<WebElement> cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return title.isDisplayed() && "Products".equals(title.getText());
    }

    public void addBackpackToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(title));
        for (WebElement button : addToCartButtons) {
            if ("Add to cart".equalsIgnoreCase(button.getText().trim())) {
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
                return;
            }
        }
        throw new IllegalStateException("No 'Add to cart' button found on inventory page.");
    }

    public int getCartItemsCount() {
        if (cartBadge.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(cartBadge.get(0).getText());
    }

    public void openCart() {
        cartLink.click();
    }

    public String getPageTitle() {
        return title.getText();
    }

    public void clearCartFromInventory() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(title));
        for (WebElement button : addToCartButtons) {
            if ("Remove".equalsIgnoreCase(button.getText().trim())) {
                wait.until(ExpectedConditions.elementToBeClickable(button));
                button.click();
            }
        }
    }

}
