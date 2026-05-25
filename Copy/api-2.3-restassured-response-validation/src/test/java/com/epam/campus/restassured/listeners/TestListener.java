package com.epam.campus.restassured.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Test Listener for test execution events
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("✓ Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✓ Test Passed: " + result.getName());
        System.out.println("  Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("✗ Test Failed: " + result.getName());
        System.out.println("  Failure Message: " + result.getThrowable().getMessage());
        result.getThrowable().printStackTrace();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⊝ Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("⊗ Test Failed Within Success Percentage: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("\n========== Test Execution Started ==========");
        System.out.println("TestSuite Name: " + context.getSuite().getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("\n========== Test Execution Finished ==========");
        System.out.println("Total Tests Run: " + context.getAllTestMethods().length);
        System.out.println("Passed Tests: " + context.getPassedTests().size());
        System.out.println("Failed Tests: " + context.getFailedTests().size());
        System.out.println("Skipped Tests: " + context.getSkippedTests().size());
        System.out.println("==========================================\n");
    }
}

