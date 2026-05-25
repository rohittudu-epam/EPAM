package com.epam.campus.selenium.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHandler {

    public Alert getAlert(WebDriver driver) {
        return driver.switchTo().alert();
    }

    public void acceptAlert(WebDriver driver) {
        getAlert(driver).accept();
    }

    public void dismissAlert(WebDriver driver) {
        getAlert(driver).dismiss();
    }

    public String getText(WebDriver driver) {
        return getAlert(driver).getText();
    }

    public void sendText(WebDriver driver, String text) {
        getAlert(driver).sendKeys(text);
    }
}
