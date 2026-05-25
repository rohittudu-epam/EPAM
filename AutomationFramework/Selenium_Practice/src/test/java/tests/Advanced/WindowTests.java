package tests.Advanced;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;

public class WindowTests extends BaseTest {

    @Test
    public void testWindowSwitching() throws InterruptedException {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(getConfig("url.window.demoqa"));

        System.out.printf("Current Window URI: %s%n", driver.getCurrentUrl());

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
        Thread.sleep(5000);
    }
}
