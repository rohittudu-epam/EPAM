package com.epam.campus.selenium.locators;

import com.epam.campus.selenium.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ElementLocatorTests extends BaseTest {

    @Test
    public void testById() {
        WebDriver driver = getDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        sleep();
    }

    @Test
    public void testByName() {
        WebDriver driver = getDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.name("user-name"));
        WebElement password = driver.findElement(By.name("password"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.name("login-button"));
        loginButton.click();

        sleep();
    }

    @Test
    public void testByTagName() {
        WebDriver driver = getDriver();
        driver.get("https://www.saucedemo.com/");

        List<WebElement> inputBoxes = driver.findElements(By.tagName("input"));
        List<String> inputs = List.of("standard_user", "secret_sauce");

        for (int i = 0; i < 2; i++) {
            inputBoxes.get(i).sendKeys(inputs.get(i));
        }

        WebElement loginButton = driver.findElement(By.name("login-button"));
        loginButton.click();

        sleep();
    }

    @Test
    public void testByLinkText() {
        try {

            WebDriver driver = getDriver();
            driver.get("https://www.saucedemo.com/");

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            Thread.sleep(1000);

            WebElement product = driver.findElement(By.linkText("Sauce Labs Backpack"));
            product.click();

            Thread.sleep(1000);

            driver.findElement(By.id("add-to-cart")).click();
            driver.findElement(By.className("shopping_cart_link")).click();

            driver.findElement(By.id("checkout")).click();

            driver.findElement(By.id("first-name")).sendKeys("Ryan");
            driver.findElement(By.id("last-name")).sendKeys("Grey");
            driver.findElement(By.id("postal-code")).sendKeys("696768");

            driver.findElement(By.id("continue")).click();
            driver.findElement(By.id("finish")).click();

            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("Thread Sleep Interrupted...");
        }
    }

    @Test
    public void testByPartialLinkTest() {
        WebDriver driver = getDriver();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        sleep();

        // Using Partial Link Text
        driver.findElement(By.partialLinkText("Backpack")).click();

        sleep();
    }

    @Test
    public void testByXPath() {
        WebDriver driver = getDriver();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        sleep();

        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();
        driver.findElement(By.xpath("//button[contains(@id,'add-to-cart')]")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        sleep();
    }

    @Test
    public void testByCssSelector() {
        WebDriver driver = getDriver();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        sleep();

        driver.findElement(By.cssSelector(".inventory_item_name")).click();
        driver.findElement(By.cssSelector("button[id*='add-to-cart']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Sleep Interrupted");
        }
    }
}