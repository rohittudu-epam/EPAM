package com.epam.campus.selenium.Base;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.epam.campus.selenium.Utils.Browser;
import com.epam.campus.selenium.Utils.ConfigReader;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(Method method, @Optional("CHROME") String browser){
        Browser initBrowser = Browser.valueOf(browser.toUpperCase());
        System.out.printf("[TEST-START] %s.%s | browser=%s | mode=%s%n",
                method.getDeclaringClass().getSimpleName(),
                method.getName(),
                initBrowser,
                ConfigReader.getExecutionMode());
        DriverFactory.initDriver(initBrowser);
        setBrowserStackSessionName(method.getDeclaringClass().getSimpleName() + "." + method.getName());
    }

    public WebDriver getDriver(){
        return DriverFactory.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result){
        captureScreenshotOnFailure(result);
        setBrowserStackSessionStatus(result);
        System.out.printf("[TEST-END] %s.%s | status=%s%n",
                result.getTestClass().getRealClass().getSimpleName(),
                result.getMethod().getMethodName(),
                result.isSuccess() ? "PASSED" : "FAILED");
        DriverFactory.quitDriver();
    }

    private void captureScreenshotOnFailure(ITestResult result) {
        if (result.isSuccess() || !DriverFactory.isDriverActive()) {
            return;
        }

        WebDriver driver = getDriver();
        if (!(driver instanceof TakesScreenshot takesScreenshot)) {
            return;
        }

        try {
            byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            Path screenshotDir = Path.of("target", "screenshots");
            Files.createDirectories(screenshotDir);

            String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS").format(new Date());
            String fileName = result.getTestClass().getRealClass().getSimpleName()
                    + "_"
                    + result.getMethod().getMethodName()
                    + "_"
                    + timestamp
                    + ".png";

            Path screenshotPath = screenshotDir.resolve(fileName);
            Files.write(screenshotPath, screenshot);
            System.out.println("Saved failure screenshot: " + screenshotPath);
        } catch (WebDriverException | java.io.IOException ex) {
            System.out.println("Failed to capture screenshot: " + ex.getMessage());
        }
    }

    private void setBrowserStackSessionName(String sessionName) {
        if (!DriverFactory.isDriverActive()) {
            return;
        }

        WebDriver driver = getDriver();
        if (!isBrowserStackSession(driver)) {
            return;
        }

        String command = String.format(
                "browserstack_executor: {\"action\":\"setSessionName\",\"arguments\":{\"name\":\"%s\"}}",
                escapeForJson(sessionName)
        );

        ((JavascriptExecutor) driver).executeScript(command);
    }

    private void setBrowserStackSessionStatus(ITestResult result) {
        if (!DriverFactory.isDriverActive()) {
            return;
        }

        WebDriver driver = getDriver();
        if (!isBrowserStackSession(driver)) {
            return;
        }

        String status = result.isSuccess() ? "passed" : "failed";
        String reason;
        if (result.isSuccess()) {
            reason = "All assertions passed";
        } else if (result.getThrowable() != null && result.getThrowable().getMessage() != null) {
            reason = result.getThrowable().getMessage();
        } else {
            reason = "Test failed";
        }

        String command = String.format(
                "browserstack_executor: {\"action\":\"setSessionStatus\",\"arguments\":{\"status\":\"%s\",\"reason\":\"%s\"}}",
                status,
                escapeForJson(reason)
        );

        ((JavascriptExecutor) driver).executeScript(command);
    }

    private boolean isBrowserStackSession(WebDriver driver) {
        if (!(driver instanceof JavascriptExecutor)) {
            return false;
        }
        if (!(driver instanceof RemoteWebDriver remoteWebDriver)) {
            return false;
        }

        Object browserStackOptions = remoteWebDriver.getCapabilities().getCapability("bstack:options");
        return browserStackOptions != null;
    }

    private String escapeForJson(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", " ")
                .replace("\n", " ");
    }
}
