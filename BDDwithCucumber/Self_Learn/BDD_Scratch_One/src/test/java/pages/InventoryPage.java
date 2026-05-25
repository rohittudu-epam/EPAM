package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    WebElement pageTitle;

    private By productPrices = By.className("inventory_item_price");

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(className = "product_sort_container")
    WebElement sortDropdown;

    @FindBy(className = "inventory_item_price")
    WebElement itemPrice;

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void addProductToCart(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }

    public void removeProductFromCart(String productName) {
        String xpath = "//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }

    public String getCartBadgeCount() {
        try {
            return cartBadge.getText();
        } catch (Exception e) {
            return "0"; // No badge means 0 items
        }
    }

    public boolean isCartBadgeDisplayed() {
        try {
            return cartBadge.isDisplayed();
        } catch (Exception e) {
            return false; // No badge means not displayed
        }
    }

    // public void sortProducts(String sortOption) {
    //     sortDropdown.click();
    //     String optionXpath = "//option[text()='" + sortOption + "']";
    //     driver.findElement(By.xpath(optionXpath)).click();
    // }

    public void sortProducts(String option) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option);
    }

    public boolean isSortedByPriceLowToHigh() {

        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> actualPrices = new ArrayList<>();

        for (WebElement price : priceElements) {
            actualPrices.add(Double.parseDouble(price.getText().replace("$", "")));
        }

        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        sortedPrices.sort(Double::compareTo);

        return actualPrices.equals(sortedPrices);
    }
}
