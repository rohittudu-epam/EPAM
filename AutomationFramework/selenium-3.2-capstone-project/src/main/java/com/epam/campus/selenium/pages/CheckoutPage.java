package com.epam.campus.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private static final By FIRST_NAME_INPUT = By.id("first-name");
    private static final By LAST_NAME_INPUT = By.id("last-name");
    private static final By ZIP_CODE_INPUT = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By COMPLETE_HEADER = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCheckoutInfo(String firstName, String lastName, String zipCode) {
        type(FIRST_NAME_INPUT, firstName);
        type(LAST_NAME_INPUT, lastName);
        type(ZIP_CODE_INPUT, zipCode);
    }

    public void clickContinue() {
        click(CONTINUE_BUTTON);
    }

    public void clickFinish() {
        click(FINISH_BUTTON);
    }

    public String getCompleteHeader() {
        return getText(COMPLETE_HEADER);
    }
}
