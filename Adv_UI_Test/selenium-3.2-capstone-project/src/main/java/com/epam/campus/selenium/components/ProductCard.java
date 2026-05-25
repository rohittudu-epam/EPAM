package com.epam.campus.selenium.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class ProductCard {
    private WebElement root;
    //    public By name = By.id("product");

    @FindBy(css = "[data-test='inventory-item-name']")
    private WebElement name;

    @FindBy(css = "[data-test='inventory-item-price']")
    private WebElement price;

    @FindBy(css = "button")
    private WebElement actionButton;

    public ProductCard(WebElement root) {
        this.root = root;
        PageFactory.initElements(new DefaultElementLocatorFactory(root), this);
    }

    public ProductCard(String text, double $, WebElement element) {}

    public String getName() {
        return name.getText();
    }

    public String getPrice() {
        return price.getText();
    }

    public void clickProduct() {
        name.click();
    }

    public void clickAddCart() {
        actionButton.click();
    }

    public boolean isAddedToCart() {
        return actionButton.getText().equalsIgnoreCase("Remove");
    }
}
