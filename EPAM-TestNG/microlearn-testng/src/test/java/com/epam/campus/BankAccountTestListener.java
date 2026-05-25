package com.epam.campus;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class BankAccountTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getMethod().getMethodName());
        System.out.println("Failure Reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Failed But Within Success Percentage: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(org.testng.ITestContext context) {
        System.out.println("=== Test Suite Started: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        System.out.println("=== Test Suite Finished: " + context.getName() + " ===");
        System.out.println("Total Tests: " + context.getAllTestMethods().length);
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
    }
}
