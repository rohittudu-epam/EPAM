package com.epam.campus.selenium.BrowserTests.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;

public class windowTests {

    public void windowTest(WebDriver driver, WebDriverWait wait) {
        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();
        newTabButton.click();
        newTabButton.click();

        String currentWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        System.out.printf("Current Window Handle: %s%n", currentWindowHandle);
        for (String s : windowHandles) {
            if (!currentWindowHandle.equals(s)) {
                driver.switchTo().window(s);
                break;
            }
        }

        String newCurrentWindowHandle = driver.getWindowHandle();
        System.out.printf("Same Current Window Handle: %s%n", currentWindowHandle.equals(newCurrentWindowHandle));

        //        System.out.println(String.format("New Window Title: %s", driver.getTitle()));
        System.out.printf("New Window URI: %s%n", driver.getCurrentUrl());

        Assert.assertFalse(currentWindowHandle.equals(newCurrentWindowHandle));

    }
}
