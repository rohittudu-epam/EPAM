package com.epam.campus.selenium;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BrowserTests extends BaseTest {

    @Test
    public void testBasicBrowserActions(){
        WebDriver driver = getDriver();

        logger.info("Navigating to https://www.google.com");
        driver.get("https://www.google.com");

        logger.info("Validating page Title");
        String title = driver.getTitle();
        Assert.assertEquals(title, "Google", "Page title doesn't match the expected title");

        // refresh page
        logger.info("Refreshing the page");
        driver.navigate().refresh();

        // validate page title after refresh
        logger.info("Validating page title after page refresh");
        String refreshTitle = driver.getTitle();
        Assert.assertEquals(refreshTitle, "Google", "Page title doesn't match the expected title");

        // validate current url contains google
        logger.info("Validating Current URL contains `google`");
        String uri = driver.getCurrentUrl();
        Assert.assertTrue(uri.contains("google"));
    }
}
