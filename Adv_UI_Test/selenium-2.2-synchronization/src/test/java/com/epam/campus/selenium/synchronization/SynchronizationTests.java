package com.epam.campus.selenium.synchronization;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.campus.selenium.base.BaseTest;
import com.epam.campus.selenium.config.ConfigLoader;

public class SynchronizationTests extends BaseTest {

    private static final By ENABLE_AFTER_BUTTON = By.id("enableAfter");
    private static final By VISIBLE_AFTER_BUTTON = By.id("visibleAfter");
    private static final By COLOR_CHANGE_BUTTON = By.id("colorChange");
    private static final By START_STOP_BUTTON = By.id("startStopButton");
    private static final By RESET_BUTTON = By.id("resetButton");
    private static final By PROGRESS_BAR = By.cssSelector("#progressBar .progress-bar");

    private String buildUrl(String pathKey) {
        return ConfigLoader.getProp("site.url") + ConfigLoader.getProp(pathKey);
    }

    private Duration secondsFromConfig(String key) {
        return Duration.ofSeconds(Integer.parseInt(ConfigLoader.getProp(key)));
    }

    private Duration millisFromConfig(String key) {
        return Duration.ofMillis(Integer.parseInt(ConfigLoader.getProp(key)));
    }


    @Test
    public void testWithImplicitWait() {
        WebDriver driver = getDriver();

        driver.get(buildUrl("dynamic_site.path"));
        driver.manage().timeouts().implicitlyWait(secondsFromConfig("IMPLICIT_WAIT_TIMEOUT_DURATION_SECONDS"));

        WebElement visibleAfterButton = driver.findElement(VISIBLE_AFTER_BUTTON);
        WebElement enableAfterButton = driver.findElement(ENABLE_AFTER_BUTTON);

        Assert.assertTrue(visibleAfterButton.isDisplayed(), "visibleAfter should be displayed after delay.");
        Assert.assertTrue(enableAfterButton.isDisplayed(), "enableAfter should be present on page.");
    }

    @Test
    public void testWithExplicitWait() {
        WebDriver driver = getDriver();

        driver.get(buildUrl("dynamic_site.path"));

        WebDriverWait explicitWait = new WebDriverWait(driver, secondsFromConfig("EXPLICIT_WAIT_TIMEOUT_DURATION_SECONDS"));

        WebElement enableAfterButton = explicitWait.until(ExpectedConditions.elementToBeClickable(ENABLE_AFTER_BUTTON));
        WebElement colorChangeButton = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(COLOR_CHANGE_BUTTON));
        WebElement visibleAfterButton = explicitWait.until(ExpectedConditions.presenceOfElementLocated(VISIBLE_AFTER_BUTTON));
        explicitWait.until(ExpectedConditions.attributeContains(COLOR_CHANGE_BUTTON, "class", "text-danger"));

        Assert.assertTrue(enableAfterButton.isEnabled(), "enableAfter should be clickable.");
        Assert.assertTrue(colorChangeButton.isDisplayed(), "colorChange should be visible.");
        Assert.assertTrue(visibleAfterButton.isDisplayed(), "visibleAfter should be present and displayed.");
        Assert.assertTrue(colorChangeButton.getDomAttribute("class").contains("text-danger"), "colorChange should update style.");
    }

    @Test
    public void testWithFluentWait() {
        WebDriver driver = getDriver();

        driver.get(buildUrl("dynamic_site.path"));

        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(secondsFromConfig("FLUENT_WAIT_TIMEOUT_DURATION_SECONDS"))
                .pollingEvery(millisFromConfig("FLUENT_WAIT_POLLING_DURATION_MILLIS"))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        WebElement colorChangeButton = fluentWait.until(webDriver -> {
            WebElement element = webDriver.findElement(COLOR_CHANGE_BUTTON);
            return element.getDomAttribute("class").contains("text-danger") ? element : null;
        });

        Assert.assertTrue(colorChangeButton.isDisplayed(), "colorChange button should be displayed.");
        Assert.assertTrue(colorChangeButton.getDomAttribute("class").contains("text-danger"), "colorChange should become red.");

    }

    @Test
    public void testDynamicPageUpdates() {
        WebDriver driver = getDriver();

        driver.get(buildUrl("progress_bar.path"));

        WebDriverWait wait = new WebDriverWait(driver, secondsFromConfig("DYNAMIC_WAIT_TIMEOUT_DURATION_SECONDS"));

        WebElement startStopButton = wait.until(ExpectedConditions.elementToBeClickable(START_STOP_BUTTON));
        startStopButton.click();

        WebElement progressBar = wait.until(ExpectedConditions.presenceOfElementLocated(PROGRESS_BAR));
        wait.until(ExpectedConditions.attributeToBe(progressBar, "aria-valuenow", "100"));

        WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(RESET_BUTTON));
        Assert.assertTrue(resetButton.isDisplayed(), "Reset button should appear after progress completion.");
        Assert.assertEquals(progressBar.getText().trim(), "100%", "Progress bar should reach 100%.");

    }

}
