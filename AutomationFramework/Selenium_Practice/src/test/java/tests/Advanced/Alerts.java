package tests.Advanced;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.AlertHandler;

public class Alerts extends BaseTest {

    @Test
    public void testHandleJsAlerts() throws InterruptedException {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        AlertHandler alertHandler = new AlertHandler();
        driver.get(getConfig("url.alerts.js"));

        WebElement jsAlertButton = driver.findElement(By.xpath("//li/button[contains(text(), 'JS Alert')]"));
        System.out.println(jsAlertButton.getText());
        jsAlertButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        alertHandler.acceptAlert(driver);

        WebElement jsConfirmButton = driver.findElement(By.xpath("//li/button[contains(text(), 'JS Confirm')]"));
        System.out.println(jsConfirmButton.getText());
        jsConfirmButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        alertHandler.acceptAlert(driver);

        WebElement jsPromptButton = driver.findElement(By.xpath("//li/button[contains(text(), 'JS Prompt')]"));
        System.out.println(jsPromptButton.getText());
        jsPromptButton.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(500);
        alert.sendKeys("Hello");
        Thread.sleep(5000);
        alert.accept();
    }

    @Test
    public void testHandleDemoQaAlerts() throws InterruptedException {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        AlertHandler alertHandler = new AlertHandler();

        driver.get(getConfig("url.alerts.demoqa"));

        WebElement promptAlertButton = driver.findElement(By.id("promtButton"));
        promptAlertButton.click();

        Thread.sleep(500);

        alertHandler.sendText(driver, "Hello");
        Thread.sleep(1000);
//        alertHandler.acceptAlert(driver);

    }

    @Test void selectorHubTest() throws InterruptedException {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        AlertHandler alertHandler = new AlertHandler();

        driver.get(getConfig("url.alerts.selectorhub"));

        WebElement promptAlertButton = driver.findElement(By.id("promptBtn"));
        promptAlertButton.click();

        Thread.sleep(500);

        alertHandler.sendText(driver, "Hello");
        Thread.sleep(1000);
        alertHandler.acceptAlert(driver);
    }
}
