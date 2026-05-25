package com.epam.campus.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPageOne {
	private final WebDriver driver;

	@FindBy(className = "title")
	private WebElement title;

	@FindBy(id = "first-name")
	private WebElement firstName;

	@FindBy(id = "last-name")
	private WebElement lastName;

	@FindBy(id = "postal-code")
	private WebElement postalCode;

	@FindBy(id = "continue")
	private WebElement continueButton;

	public CheckoutPageOne(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("checkout-step-one"));
		wait.until(ExpectedConditions.visibilityOf(title));
		String currentTitle = title.getText();
		return currentTitle.contains("Checkout: Your Information");
	}

	public void enterFirstName(String value) {
		firstName.clear();
		firstName.sendKeys(value);
	}

	public void enterLastName(String value) {
		lastName.clear();
		lastName.sendKeys(value);
	}

	public void enterPostalCode(String value) {
		postalCode.clear();
		postalCode.sendKeys(value);
	}

	public void continueCheckout(String first, String last, String zip) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(firstName));
		enterFirstName(first);
		enterLastName(last);
		enterPostalCode(zip);
		wait.until(ExpectedConditions.elementToBeClickable(continueButton));
		continueButton.click();
		wait.until(ExpectedConditions.urlContains("checkout-step-two"));
	}

	public String getPageTitle() {
		return title.getText();
	}
}
