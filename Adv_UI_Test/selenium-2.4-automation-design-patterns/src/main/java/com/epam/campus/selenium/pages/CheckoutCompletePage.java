package com.epam.campus.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCompletePage {
	private final WebDriver driver;

	@FindBy(className = "title")
	private WebElement title;

	@FindBy(className = "complete-header")
	private WebElement completeHeader;

	public CheckoutCompletePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("checkout-complete"));
		wait.until(ExpectedConditions.visibilityOf(title));
		String currentTitle = title.getText();
		return currentTitle.contains("Checkout: Complete!");
	}

	public String getSuccessMessage() {
		return completeHeader.getText();
	}

	public String getPageTitle() {
		return title.getText();
	}
}
