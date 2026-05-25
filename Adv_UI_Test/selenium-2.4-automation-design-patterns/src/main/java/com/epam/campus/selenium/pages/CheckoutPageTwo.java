package com.epam.campus.selenium.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPageTwo {
	private final WebDriver driver;

	@FindBy(className = "title")
	private WebElement title;

	@FindBy(className = "cart_item")
	private List<WebElement> itemRows;

	@FindBy(id = "finish")
	private WebElement finishButton;

	public CheckoutPageTwo(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("checkout-step-two"));
		wait.until(ExpectedConditions.visibilityOf(title));
		String currentTitle = title.getText();
		return currentTitle.contains("Checkout: Overview");
	}

	public int getItemsCount() {
		return itemRows.size();
	}

	public void finishCheckout() {
		finishButton.click();
	}

	public String getPageTitle() {
		return title.getText();
	}
}
