package tests.Advanced;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;

public class PopUpTests extends BaseTest {

    @Test
    public void testHandlePopUp(){

    }

    @Test
    public void testHandleSmallModal() throws InterruptedException {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(getConfig("url.popup.modal"));

        WebElement smallModalButton = driver.findElement(By.id("showSmallModal"));
        smallModalButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-dialog")));

        Thread.sleep(1000);
        WebElement closeModalButton = driver.findElement(By.id("closeSmallModal"));
        closeModalButton.click();
        Thread.sleep(1000);
    }

    @Test
    public void testHandleLargeModal() throws InterruptedException {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(getConfig("url.popup.modal"));

        WebElement largeModalButton = driver.findElement(By.id("showLargeModal"));
        largeModalButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-dialog")));

        Thread.sleep(1000);
        WebElement closeModalButton = driver.findElement(By.id("closeLargeModal"));
        closeModalButton.click();

        Thread.sleep(1000);
    }
}
