package com.epam.campus.testng;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ListenerTest {

    private Calculator calculator;

    @BeforeMethod
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdditionPass() {
        int result = calculator.add(5, 5);
        Assert.assertEquals(result, 10, "Addition result mismatch");
    }

    @Test
    public void testDivisionFail() {
        calculator.divide(10, 0);
    }


    @Test
    public void testSkippedScenario() {
        throw new SkipException("Skipping this test intentionally");
    }
}
