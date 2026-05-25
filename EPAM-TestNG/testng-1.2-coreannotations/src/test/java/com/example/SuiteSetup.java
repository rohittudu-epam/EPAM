package com.example;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SuiteSetup {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("===== Before Suite: Initializing global resources =====");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("===== After Suite: Releasing global resources =====");
    }
}
