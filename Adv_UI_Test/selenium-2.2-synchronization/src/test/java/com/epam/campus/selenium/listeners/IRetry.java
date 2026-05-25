package com.epam.campus.selenium.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetry implements IRetryAnalyzer {
    private int retry_count;
    private final int MAX_RETRY_COUNT = 5;

    @Override
    public boolean retry(ITestResult result) {
        if (retry_count < MAX_RETRY_COUNT){
            retry_count++;
            return true;
        }
        return false;
    }
}
