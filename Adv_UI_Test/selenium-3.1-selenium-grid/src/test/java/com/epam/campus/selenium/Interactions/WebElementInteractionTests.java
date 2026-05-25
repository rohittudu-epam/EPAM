package com.epam.campus.selenium.Interactions;

import com.epam.campus.selenium.Base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

/*
- **ButtonClick**: Practice clicking buttons and handling alerts.
    https://demoqa.com/buttons

- **SendInput**: Enter text into input fields.
    https://demoqa.com/text-box

- **ClearField**: Clear text from input fields.
    https://demoqa.com/text-box

- **DropDown Selection**: Select options from dropdown menus.
    https://demoqa.com/select-menu

- **MouseHover**: Trigger tooltips with mouse hover actions.
    https://demoqa.com/tool-tips

- **DragAndDrop**: Perform drag-and-drop interactions.
    https://demoqa.com/droppable

- **KeyBoardEvents**: Simulate keyboard events like typing and shortcuts.
    https://demoqa.com/text-box

- **FileUpload**: Upload files via input elements.
    https://demoqa.com/upload-download

- **FileDownload**: Download files and verify actions.
    https://demoqa.com/upload-download
* */

public class WebElementInteractionTests extends BaseTest {
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);

    private WebDriverWait waitFor(WebDriver driver) {
        return new WebDriverWait(driver, WAIT_TIMEOUT);
    }

    private WebElement waitForClickable(WebDriver driver, By locator) {
        return waitFor(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement waitForVisible(WebDriver driver, By locator) {
        return waitFor(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    private void removeDemoQaAds(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll('#fixedban, div[id^=adplus], iframe[id*=google_ads], .adsbygoogle').forEach(e => e.remove());");
    }

    private void selectReactOption(WebDriver driver, By inputLocator, String optionText) {
        WebElement input = waitForClickable(driver, inputLocator);
        input.click();
        input.sendKeys(optionText);
        input.sendKeys(Keys.ENTER);
    }

    // Basic Element Interactions
    // testButtonClick
    @Test
    public void testButtonClick() {
        WebDriver driver = getDriver();
        driver.get("https://demoqa.com/buttons");
        removeDemoQaAds(driver);

        Actions mouseAction = new Actions(driver);
        WebElement doubleClickButton = waitForClickable(driver, By.id("doubleClickBtn"));
        WebElement rightClickButton = waitForClickable(driver, By.id("rightClickBtn"));
        WebElement clickButton = waitForClickable(driver, By.xpath("//button[text()='Click Me']"));

        scrollIntoView(driver, doubleClickButton);
        scrollIntoView(driver, rightClickButton);
        scrollIntoView(driver, clickButton);

        mouseAction.doubleClick(doubleClickButton).perform();
        mouseAction.contextClick(rightClickButton).perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickButton);

        Assert.assertTrue(waitForVisible(driver, By.id("doubleClickMessage")).getText().contains("double click"),
                "Double click confirmation was not shown");
        Assert.assertTrue(waitForVisible(driver, By.id("rightClickMessage")).getText().contains("right click"),
                "Right click confirmation was not shown");
        Assert.assertTrue(waitForVisible(driver, By.id("dynamicClickMessage")).getText().contains("dynamic click"),
                "Single click confirmation was not shown");
    }

    // testSendTextInput
    @Test
    public void testSendTextInput() {
        WebDriver driver = getDriver();
        driver.get("https://demoqa.com/text-box");

        WebElement fullNameInput = waitForClickable(driver, By.id("userName"));
        WebElement emailInput = waitForClickable(driver, By.id("userEmail"));
        WebElement curAddTextArea = waitForClickable(driver, By.id("currentAddress"));
        WebElement permAddTextArea = waitForClickable(driver, By.id("permanentAddress"));
        String fullName = "Simon Riley";
        String email = "simonriley141@sas.com";

        fullNameInput.sendKeys(fullName);
        emailInput.sendKeys(email);
        curAddTextArea.sendKeys("2187 Morningview Lane\n" +
                "New York, NY 10011\n" +
                "United States");
        permAddTextArea.sendKeys("2187 Morningview Lane\n" +
                "New York, NY 10011\n" +
                "United States");

        waitForClickable(driver, By.id("submit")).click();

        Assert.assertTrue(waitForVisible(driver, By.id("name")).getText().contains(fullName),
                "Submitted name did not match input value");
        Assert.assertTrue(waitForVisible(driver, By.id("email")).getText().contains(email),
                "Submitted email did not match input value");
    }

    // testClearField
    @Test
    public void testClearField() {
        WebDriver driver = getDriver();

        driver.get("https://demoqa.com/text-box");

        WebElement fullNameInput = waitForClickable(driver, By.id("userName"));
        WebElement emailInput = waitForClickable(driver, By.id("userEmail"));
        WebElement curAddTextArea = waitForClickable(driver, By.id("currentAddress"));
        WebElement permAddTextArea = waitForClickable(driver, By.id("permanentAddress"));

        fullNameInput.sendKeys("Simon Riley");
        emailInput.sendKeys("simonriley141@sas.com");
        curAddTextArea.sendKeys("2187 Morningview Lane\n" +
                "New York, NY 10011\n" +
                "United States");
        permAddTextArea.sendKeys("2187 Morningview Lane\n" +
                "New York, NY 10011\n" +
                "United States");

        fullNameInput.clear();
        emailInput.clear();
        curAddTextArea.clear();
        permAddTextArea.clear();

        Assert.assertEquals(fullNameInput.getDomProperty("value"), "", "Full name field was not cleared");
        Assert.assertEquals(emailInput.getDomProperty("value"), "", "Email field was not cleared");
        Assert.assertEquals(curAddTextArea.getDomProperty("value"), "", "Current address field was not cleared");
        Assert.assertEquals(permAddTextArea.getDomProperty("value"), "", "Permanent address field was not cleared");
    }

    // testDropdownSelection
    @Test
    public void testDropdownSelection() {
        WebDriver driver = getDriver();
        driver.get("https://demoqa.com/select-menu");

        selectReactOption(driver, By.id("react-select-2-input"), "Group 2, option 2");
        selectReactOption(driver, By.id("react-select-3-input"), "Mrs.");

        Select selectElement = new Select(waitForClickable(driver, By.id("oldSelectMenu")));
        selectElement.selectByVisibleText("Black");

        selectReactOption(driver, By.id("react-select-4-input"), "Green");
        selectReactOption(driver, By.id("react-select-4-input"), "Blue");
        selectReactOption(driver, By.id("react-select-4-input"), "Black");

        Assert.assertEquals(selectElement.getFirstSelectedOption().getText(), "Black",
                "Expected 'Black' to be selected in old style dropdown");
        Assert.assertTrue(waitForVisible(driver, By.xpath("//div[@aria-label='Remove Green']")).isDisplayed(),
                "Green was not selected in multi-select dropdown");
        Assert.assertTrue(waitForVisible(driver, By.xpath("//div[@aria-label='Remove Blue']")).isDisplayed(),
                "Blue was not selected in multi-select dropdown");
        Assert.assertTrue(waitForVisible(driver, By.xpath("//div[@aria-label='Remove Black']")).isDisplayed(),
                "Black was not selected in multi-select dropdown");

    }

    // Advanced Interactions
    // testMouseHover()
    @Test
    public void testMouseHover() {

        WebDriver driver = getDriver();
        WebDriverWait wait = waitFor(driver);

        driver.get("https://demoqa.com/tool-tips");
        removeDemoQaAds(driver);

        WebElement hoverButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("toolTipButton"))
        );

        // Scroll to center
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                hoverButton
        );

        // Trigger hover using JS (reliable)
        ((JavascriptExecutor) driver).executeScript(
                "var event = new MouseEvent('mouseover', { bubbles: true }); arguments[0].dispatchEvent(event);",
                hoverButton
        );

        WebElement tooltip = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("buttonToolTip"))
        );

        System.out.println(tooltip.getText());
    }

    // testClickAndHold()
    @Test
    public void testClickAndHold() {
        WebDriver driver = getDriver();
        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/droppable");
        WebElement draggable = waitForVisible(driver, By.id("draggable"));
        scrollIntoView(driver, draggable);

        Point initialLocation = draggable.getLocation();
        actions.clickAndHold(draggable)
                .pause(Duration.ofSeconds(1))
                .moveByOffset(120, 60)
                .release()
                .perform();

        waitFor(driver).until(d -> {
            Point current = draggable.getLocation();
            return current.getX() != initialLocation.getX() || current.getY() != initialLocation.getY();
        });

        Point finalLocation = draggable.getLocation();
        Assert.assertTrue(
                finalLocation.getX() != initialLocation.getX() || finalLocation.getY() != initialLocation.getY(),
                "Element location did not change after click-and-hold action");
    }

    // testDragAndDrop()
    @Test
    public void testDragAndDrop() {
        WebDriver driver = getDriver();
        Actions actions = new Actions(driver);

        driver.get("https://demoqa.com/droppable");
        removeDemoQaAds(driver);

        WebElement draggable = waitForVisible(driver, By.id("draggable"));
        WebElement droppable = waitForVisible(driver, By.id("droppable"));
        scrollIntoView(driver, draggable);
        scrollIntoView(driver, droppable);

        actions.clickAndHold(draggable)
            .pause(Duration.ofMillis(500))
            .moveToElement(droppable)
            .pause(Duration.ofMillis(500))
            .release()
            .perform();

        String dropText = waitForVisible(driver, By.id("droppable")).getText().trim();
        if (!"Dropped!".equals(dropText)) {
            Point source = draggable.getLocation();
            Point target = droppable.getLocation();
            actions.clickAndHold(draggable)
                .moveByOffset(target.getX() - source.getX() + 10, target.getY() - source.getY() + 10)
                .release()
                .perform();
            dropText = waitForVisible(driver, By.id("droppable")).getText().trim();
        }

        Assert.assertEquals(dropText, "Dropped!", "Drag-and-drop did not complete successfully");
    }

    // testKeyboardEvents()
    @Test
    public void testKeyboardEvents() {
        WebDriver driver = getDriver();
        driver.get("https://demoqa.com/text-box");

        String name = "Rahul Verma";
        String email = "rahul.verma92@example.com";
        String currentAddress = "Flat 302, Green Heights Apartment, Madhapur, Hyderabad, Telangana, 500081";
        String permanentAddress = "H.No. 12-45, Main Road, Kothapet Village, Guntur District, Andhra Pradesh, 522001";

        WebElement fullNameInput = waitForClickable(driver, By.id("userName"));
        fullNameInput.click();

        new Actions(driver)
                .sendKeys(name)
                .sendKeys(Keys.TAB)
                .sendKeys(email)
                .sendKeys(Keys.TAB)
                .sendKeys(currentAddress)
                .sendKeys(Keys.TAB)
                .sendKeys(permanentAddress)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .perform();

        Assert.assertEquals(waitForVisible(driver, By.id("name")).getText(), "Name:" + name,
                "Keyboard event flow did not populate Name correctly");
        Assert.assertEquals(waitForVisible(driver, By.id("email")).getText(), "Email:" + email,
                "Keyboard event flow did not populate Email correctly");
    }

    // testFileUpload()
    @Test
    public void testFileUpload() throws IOException {
        WebDriver driver = getDriver();

        driver.get("https://demoqa.com/upload-download");

        Path tempFile = Files.createTempFile("upload-test-", ".txt");
        Files.writeString(tempFile, "hello world");

        try {
            File file = tempFile.toFile();
            WebElement fileInput = waitForVisible(driver, By.id("uploadFile"));
            fileInput.sendKeys(file.getAbsolutePath());

            String uploadedFileName = waitForVisible(driver, By.id("uploadedFilePath")).getText();
            Assert.assertTrue(uploadedFileName.contains(file.getName()),
                    "Uploaded file path did not contain file name");
        } finally {
            Files.deleteIfExists(tempFile);
        }

    }

    // testFileDownload()
    @Test
    public void testFileDownload() {
        WebDriver driver = getDriver();

        driver.get("https://demoqa.com/upload-download");

        WebElement downloadButton = waitForVisible(driver, By.id("downloadButton"));

        String href = downloadButton.getDomAttribute("href");
        String downloadName = downloadButton.getDomAttribute("download");

        Assert.assertNotNull(href, "Download link should have href attribute");
        Assert.assertFalse(href.isBlank(), "Download href should not be blank");
        Assert.assertTrue(href.startsWith("data:"), "Expected demo download link to be a data URI");

        Assert.assertNotNull(downloadName, "Download attribute should be present");
        Assert.assertFalse(downloadName.isBlank(), "Download filename should not be blank");

    }
}
