package com.epam.campus.testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CalculatorAssertionTest {

    private Calculator calc;
    private SoftAssert softAssert;

    @BeforeClass
    public void initialize() {
        calc = new Calculator();
        softAssert = new SoftAssert();
    }

    @Test
    public void hardAssertions() {
        // Hard assertions stop execution immediately on failure

        Assert.assertEquals(
                calc.add(5, 7),
                12,
                "Addition result mismatch"
        );

        Assert.assertTrue(
                calc.add(9, 11) >= 20,
                "Addition result is less than expected"
        );

        Assert.assertFalse(
                calc.subtract(12, 5) > 9,
                "Subtraction result is unexpectedly greater"
        );
    }

    @Test
    public void softAssertions() {
        // Soft assertions allow multiple validations in a single test

        softAssert.assertEquals(
                calc.add(9, 100),
                109,
                "Addition failed"
        );

        softAssert.assertEquals(
                calc.subtract(20, 5),
                15,
                "Subtraction failed"
        );

        softAssert.assertEquals(
                calc.multiply(4, 5),
                20,
                "Multiplication failed"
        );

        softAssert.assertEquals(
                calc.divide(20, 4),
                5,
                "Division failed"
        );

        // Reports all failures together
        softAssert.assertAll();
    }

    @AfterClass
    public void testCompletion() {
        System.out.println("All Calculator Assertion Tests Completed.");
    }
}
