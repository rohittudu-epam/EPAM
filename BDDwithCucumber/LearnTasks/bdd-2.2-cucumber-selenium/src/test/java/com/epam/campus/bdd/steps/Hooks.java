package com.epam.campus.bdd.steps;

import com.epam.campus.bdd.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

    @Before
    public void setUp() {
        WebDriverFactory.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = WebDriverFactory.getDriver();

        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot-" + scenario.getName());

                Path screenshotsDir = Paths.get("target", "screenshots");
                Files.createDirectories(screenshotsDir);
                String fileName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
                File screenshotFile = screenshotsDir.resolve(fileName).toFile();
                Files.write(screenshotFile.toPath(), screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        WebDriverFactory.quitDriver();
    }
}
