package tests.Advanced;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorTests {

    // Task 1: Click on a hidden Element
    public void clickHiddenElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].style.display='block'", element);
        element.click();
    }

    public void scrollToElementAndClick(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}

/**
 * JavascriptExecutorTest
 *
 * This class demonstrates how to use JavaScriptExecutor
 * for advanced interactions when Selenium default methods fail.
 */
class JavascriptExecutorTest {

    /**
     * Task #1: Click a hidden element using JavaScriptExecutor
     *
     * Why?
     * Selenium cannot click elements that are hidden (display:none, visibility:hidden).
     * JavaScriptExecutor bypasses this limitation.
     *
     * Approach:
     * 1. Make the element visible (optional but safer)
     * 2. Perform click using JavaScript
     */
    public void clickHiddenElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step 1: Make element visible (if hidden)
        js.executeScript("arguments[0].style.display='block';", element);

        // Step 2: Click using JavaScript (IMPORTANT)
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Task #2: Scroll to an element and click using JavaScriptExecutor
     *
     * Why?
     * Sometimes elements are outside viewport or covered,
     * and Selenium fails with ElementNotInteractableException.
     *
     * Approach:
     * 1. Scroll element into view
     * 2. Perform click using JavaScript
     */
    public void scrollToElementAndClick(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step 1: Scroll element into view
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        // Optional: small delay (helps in some UI cases)
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Step 2: Click using JavaScript
        js.executeScript("arguments[0].click();", element);
    }
}