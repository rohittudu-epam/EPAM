package com.example;

import org.testng.Assert;
import org.testng.annotations.*;

public class AnnotatedTests {

    @BeforeClass
    public void beforeClass() {
        System.out.println("---- Before Class: Setting up class-level configuration ----");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Opening file / Preparing test data");
    }

    /**
     * Pre-configuration test.
     * This test must pass for dependent tests to execute.
     */
    @Test(priority = 1)
    public void preConfigFileOne() {
        System.out.println("Running pre-configuration for File One");
        Assert.assertTrue(true, "Pre-configuration successful");
    }

    /**
     * Main test dependent on pre-configuration.
     * Will be skipped automatically if preConfigFileOne fails.
     */
    @Test(priority = 2, dependsOnMethods = "preConfigFileOne")
    public void fileOneTest() {
        System.out.println("Executing test on File One");
        Assert.assertEquals("FILE1", "FILE1", "File One validation passed");
    }

    /**
     * Disabled test example.
     * Demonstrates TestNG's enabled=false feature.
     */
    @Test(enabled = false)
    public void fileTwoTest() {
        System.out.println("This test is intentionally disabled");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Closing file / Cleaning up test data");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("---- After Class: Cleaning up class-level configuration ----");
    }
}
