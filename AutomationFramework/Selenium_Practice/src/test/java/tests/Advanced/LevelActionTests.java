package tests.Advanced;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;

public class LevelActionTests extends BaseTest {

    @Test
    public void clearCookies(){
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(getConfig("url.saucedemo"));

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
    }
}
