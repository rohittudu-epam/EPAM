package com.epam.campus.selenium.BrowserTests;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.campus.selenium.base.BaseTest;

public abstract class AdvancedBrowserScenarioTests extends BaseTest {

    protected final String alertUrl = "https://demoqa.com/alerts";
    protected final String popupUrl = "https://demoqa.com/modal-dialogs";
    protected final String windowHandlingUrl = "https://demoqa.com/browser-windows";
    protected final String frameHandlingUrl = "https://demoqa.com/frames";
    protected final String cookiesClearUri = "https://demoqa.com";

    @Test
    public void testHandleAlert() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(alertUrl);

        driver.findElement(By.id("alertButton")).click();
        String immediateAlertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertEquals(immediateAlertText, "You clicked a button");
        driver.switchTo().alert().accept();

        driver.findElement(By.id("timerAlertButton")).click();
        String timedAlertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertEquals(timedAlertText, "This alert appeared after 5 seconds");
        driver.switchTo().alert().accept();

        driver.findElement(By.id("confirmButton")).click();
        String confirmText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertEquals(confirmText, "Do you confirm action?");
        driver.switchTo().alert().dismiss();
        Assert.assertTrue(driver.findElement(By.id("confirmResult")).getText().contains("Cancel"));

        String name = "SeleniumUser";
        driver.findElement(By.id("promtButton")).click();
        String promptText = wait.until(ExpectedConditions.alertIsPresent()).getText();
        Assert.assertEquals(promptText, "Please enter your name");
        driver.switchTo().alert().sendKeys(name);
        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(By.id("promptResult")).getText().contains(name));
    }

    @Test
    public void testHandlePopUp() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(popupUrl);

        driver.findElement(By.id("showSmallModal")).click();
        WebElement smallTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-sm")));
        Assert.assertTrue(smallTitle.getText().contains("Small Modal"));
        driver.findElement(By.id("closeSmallModal")).click();
        wait.until(ExpectedConditions.invisibilityOf(smallTitle));

        driver.findElement(By.id("showLargeModal")).click();
        WebElement largeTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
        Assert.assertTrue(largeTitle.getText().contains("Large Modal"));
        driver.findElement(By.id("closeLargeModal")).click();
        wait.until(ExpectedConditions.invisibilityOf(largeTitle));

    }

    @Test
    public void testWindowSwitching() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(windowHandlingUrl);
        String originalHandle = driver.getWindowHandle();

        driver.findElement(By.id("tabButton")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        wait.until(ExpectedConditions.urlContains("sample"));
        Assert.assertTrue(driver.getCurrentUrl().contains("sample"));

        driver.close();
        driver.switchTo().window(originalHandle);
        Assert.assertTrue(driver.getCurrentUrl().contains("browser-windows"));

    }

    @Test
    public void testIframeSwitching() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(frameHandlingUrl);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("frame1")));
        String frame1Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading"))).getText();
        Assert.assertEquals(frame1Text, "This is a sample page");
        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("frame2")));
        String frame2Text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sampleHeading"))).getText();
        Assert.assertEquals(frame2Text, "This is a sample page");
        driver.switchTo().defaultContent();
    }

    @Test
    public void testManageCookies() {
        WebDriver driver = getDriver();
        driver.get(cookiesClearUri);

        Cookie testCookie = new Cookie("course", "selenium");
        driver.manage().addCookie(testCookie);

        Cookie retrievedCookie = driver.manage().getCookieNamed("course");
        Assert.assertNotNull(retrievedCookie);
        Assert.assertEquals(retrievedCookie.getValue(), "selenium");

        driver.manage().deleteCookieNamed("course");
        Assert.assertNull(driver.manage().getCookieNamed("course"));

        driver.manage().addCookie(new Cookie("temp", "1"));
        Assert.assertFalse(driver.manage().getCookies().isEmpty());
        driver.manage().deleteAllCookies();
        Assert.assertTrue(driver.manage().getCookies().isEmpty());

    }

    @Test
    public void testClearCache() {
        WebDriver driver = getDriver();
        driver.get(cookiesClearUri);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('ui-course', 'selenium');");
        js.executeScript("window.sessionStorage.setItem('ui-session', 'advanced');");

        Long localCount = (Long) js.executeScript("return window.localStorage.length;");
        Long sessionCount = (Long) js.executeScript("return window.sessionStorage.length;");
        Assert.assertTrue(localCount > 0);
        Assert.assertTrue(sessionCount > 0);

        js.executeScript("window.localStorage.clear();");
        js.executeScript("window.sessionStorage.clear();");

        Long clearedLocalCount = (Long) js.executeScript("return window.localStorage.length;");
        Long clearedSessionCount = (Long) js.executeScript("return window.sessionStorage.length;");
        Assert.assertEquals(clearedLocalCount.longValue(), 0L);
        Assert.assertEquals(clearedSessionCount.longValue(), 0L);

    }
}
