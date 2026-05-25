package com.epam.campus.selenium.BrowserTests.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class iframeTests {

    public void printFrameURL(WebDriver driver, WebElement frame){
        driver.switchTo().frame(frame);
        System.out.println("URL Of Frame " + frame + " is " + driver.getCurrentUrl());
        driver.switchTo().defaultContent();
    }

    public void nestedFrameTest(WebDriver driver, WebElement parentFrame) {

        driver.switchTo().frame(parentFrame);

        WebElement childFrame = driver.findElement(By.tagName("iframe"));

        driver.switchTo().frame(childFrame);

        WebElement childText = driver.findElement(By.tagName("p"));
        System.out.println("Child Text: " + childText.getText());

        driver.switchTo().parentFrame();

        WebElement parentText = driver.findElement(By.tagName("body"));
        System.out.println("Parent Text: " + parentText.getText());

        driver.switchTo().defaultContent();
    }


}
