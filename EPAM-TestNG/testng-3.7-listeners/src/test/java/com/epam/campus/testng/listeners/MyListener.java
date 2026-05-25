package com.epam.campus.testng.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Custom TestNG Listener to capture and log
 * test execution events such as start, pass,
 * fail, and skip.
 */
public class MyListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Test Execution Started: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Test Execution Finished: " + context.getName() + " ===");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("STARTING TEST: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("TEST PASSED: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("TEST FAILED: " + result.getName());
        System.out.println("FAILURE REASON: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("TEST SKIPPED: " + result.getName());
    }
}
