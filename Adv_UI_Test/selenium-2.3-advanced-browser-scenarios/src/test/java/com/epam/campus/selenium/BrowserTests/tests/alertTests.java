package com.epam.campus.selenium.BrowserTests.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class alertTests {

    public void alertButton(WebDriver driver, WebDriverWait wait){
        WebElement button = driver.findElement(By.id("alertButton"));
        button.click();
        wait.until(ExpectedConditions.alertIsPresent());

    }

    public void timerAlertButton(WebDriver driver, WebDriverWait wait) {
        WebElement button = driver.findElement(By.id("timerAlertButton"));
        button.click();
        wait.until(ExpectedConditions.alertIsPresent());

    }

    public void confirmAlertButton(WebDriver driver, WebDriverWait wait) {
        WebElement button = driver.findElement(By.id("confirmButton"));
        button.click();
        wait.until(ExpectedConditions.alertIsPresent());

    }

    public void promptAlertButton(WebDriver driver, WebDriverWait wait, String words) {
        WebElement button = driver.findElement(By.id("promtButton"));
        button.click();
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
