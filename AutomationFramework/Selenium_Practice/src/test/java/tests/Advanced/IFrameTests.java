package tests.Advanced;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;

public class IFrameTests extends BaseTest {

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

    @Test
    public void findIframeTest() throws InterruptedException {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(getConfig("url.iframe.frames"));

        WebElement frame1 = driver.findElement(By.id("frame1"));
        System.out.println(frame1);
        printFrameURL(driver, frame1);

        WebElement frame2 = driver.findElement(By.id("frame2"));
        System.out.println(frame2);
        printFrameURL(driver, frame1);

//        printFrameURL(driver, frame1);
//        printFrameURL(driver, frame2);
    }

    @Test
    public void nestedFrameTest() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(getConfig("url.iframe.nested"));

        WebElement parentFrame = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("frame1"))
        );

        nestedFrameTest(driver, parentFrame);
    }
}
