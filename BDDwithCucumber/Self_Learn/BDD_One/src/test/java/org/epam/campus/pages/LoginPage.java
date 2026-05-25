package org.epam.campus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "user-name")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;

    // Actions
    // Send username
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    // Send password
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    // Submit Button
    public void login() {
        loginButton.click();
    }
}
