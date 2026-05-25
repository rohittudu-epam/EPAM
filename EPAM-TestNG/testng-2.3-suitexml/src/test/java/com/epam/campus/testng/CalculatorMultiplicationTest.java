package com.epam.campus.testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorMultiplicationTest {
    Calculator calc;

    @BeforeClass
    public void initialize() {
        calc = new Calculator();
    }

    @Test
    public void testPositiveMultiplication() {
        // basic multiplication
        Assert.assertEquals(calc.multiply(2, 3), 6);
        Assert.assertEquals(calc.multiply(5, 4), 20);

        // multiplication with zero
        Assert.assertEquals(calc.multiply(0, 10), 0);
        Assert.assertEquals(calc.multiply(7, 0), 0);

        // multiplication with one
        Assert.assertEquals(calc.multiply(1, 9), 9);
    }


    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        calc.divide(5, 0);
    }


    @AfterClass
    public void testCompletion() {
        System.out.println("CalculatorMultiplicationTest Completed.\n");
    }
}
