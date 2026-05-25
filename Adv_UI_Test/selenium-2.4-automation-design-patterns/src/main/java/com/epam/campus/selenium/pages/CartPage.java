package com.epam.campus.selenium.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	private final WebDriver driver;

	@FindBy(className = "title")
	private WebElement title;

	@FindBy(className = "cart_item")
	private List<WebElement> cartItems;

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isLoaded() {
		return title.isDisplayed() && "Your Cart".equals(title.getText());
	}

	public int getItemsCount() {
		return cartItems.size();
	}

	public void clickCheckout() {
		checkoutButton.click();
	}

	public String getPageTitle() {
		return title.getText();
	}
}
