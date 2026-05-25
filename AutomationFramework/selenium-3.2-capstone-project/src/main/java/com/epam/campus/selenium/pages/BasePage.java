package com.epam.campus.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.campus.selenium.utils.ConfigReader;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        int timeout = ConfigReader.getIntProperty("explicit.wait.seconds", 10);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    protected boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void waitForUrlContains(String urlFragment) {
        wait.until(ExpectedConditions.urlContains(urlFragment));
    }
}
