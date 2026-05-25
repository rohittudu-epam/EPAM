package com.epam.campus.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.campus.selenium.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, wait);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessageContainer;
    private WebElement errorCloseButton;

    public String getErrorMessage() {
        return errorMessageContainer.getText();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessageContainer.isDisplayed();
    }

    protected void enterUsername(String user) {
        if (user.isEmpty() || user.isBlank()) throw new IllegalArgumentException("Username shouldn't be empty");
        username.clear();
        username.sendKeys(user);
    }

    protected void enterPassword(String pwd) {
        if (pwd.isBlank() || pwd.isEmpty()) throw new IllegalArgumentException("Password shouldn't be Empty");
        password.clear();
        password.sendKeys(pwd);
    }

    protected void clickLogin() {
        loginButton.click();
    }

    public void loginAs(String user, String pwd) {
        enterUsername(user);
        enterPassword(pwd);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("inventory");
    }

    public String getCurrentUri() {
        return driver.getCurrentUrl();
    }
}
