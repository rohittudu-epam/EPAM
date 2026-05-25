package com.epam.campus.selenium.BrowserTests.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class popupTests {

    public void smallModalTest (WebDriver driver, WebDriverWait wait) {
        WebElement smallModalButton = driver.findElement(By.id("showSmallModal"));
        smallModalButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-dialog")));
        WebElement closeSmallModalButton = driver.findElement(By.id("closeSmallModal"));
        closeSmallModalButton.click();


    }


    public void largeModalTest(WebDriver driver, WebDriverWait wait) {
        WebElement largeModalButton = driver.findElement(By.id("showLargeModal"));
        largeModalButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-dialog")));

        WebElement closeLargeModalButton = driver.findElement(By.id("closeLargeModal"));
        closeLargeModalButton.click();

    }
}
