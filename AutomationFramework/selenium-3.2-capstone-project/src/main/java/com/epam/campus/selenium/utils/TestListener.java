package com.epam.campus.selenium.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("STARTING: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("PASSED: {} ({}ms)", result.getMethod().getMethodName(),
                result.getEndMillis() - result.getStartMillis());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("FAILED: {} - {}", result.getMethod().getMethodName(),
                result.getThrowable().getMessage());
        captureScreenshot(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("SKIPPED: {}", result.getMethod().getMethodName());
    }

    private void captureScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        if (driver instanceof TakesScreenshot) {
            try {
                File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotDir = ConfigReader.getProperty("screenshot.dir");
                Path dir = Paths.get(screenshotDir);
                Files.createDirectories(dir);

                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                Path destination = dir.resolve(testName + "_" + timestamp + ".png");
                Files.copy(source.toPath(), destination);
                logger.info("Screenshot saved: {}", destination);
            } catch (IOException e) {
                logger.error("Failed to capture screenshot for {}", testName, e);
            }
        }
    }
}
