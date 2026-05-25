package com.epam.campus.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void enterUsername(String user) {
        username.clear();
        username.sendKeys(user);
    }

    protected void enterPassword(String pwd){
        password.clear();
        password.sendKeys(pwd);
    }

    protected void clickLogin() {
        loginButton.click();
    }

    public void loginAs(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
