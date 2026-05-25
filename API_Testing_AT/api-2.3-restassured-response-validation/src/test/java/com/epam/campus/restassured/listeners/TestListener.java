package com.epam.campus.restassured.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class TestListener implements ITestListener {
    private static ThreadLocal<String> testName = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context){
        System.out.println("========== TEST SUITE STARTED ==========");
        System.out.println("Suite Name: " + context.getName());
        System.out.println("Start Time: " + new Date(context.getStartDate().getTime()));

    }

    @Override
    public void onFinish(ITestContext context){
        System.out.println("========== TEST SUITE FINISHED ==========");
        System.out.println("End Time: " + new Date(context.getEndDate().getTime()));
        System.out.println("Passed: " + context.getPassedTests().size());
        System.out.println("Failed: " + context.getFailedTests().size());
        System.out.println("Skipped: " + context.getSkippedTests().size());
    }

    // ========== TEST LEVEL ==========
    @Override
    public void onTestStart(ITestResult result){
        testName.set(result.getMethod().getMethodName());
        System.out.println("Started: " + testName.get());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        System.out.println("PASSED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result){
        System.out.println("FAILED: " + result.getMethod().getMethodName());
        System.out.println("Reason: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result){
        System.out.println("SKIPPED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result){
        System.out.println("FAILED WITHIN SUCCESS PERCENTAGE: " + result.getMethod().getMethodName());
    }

}
