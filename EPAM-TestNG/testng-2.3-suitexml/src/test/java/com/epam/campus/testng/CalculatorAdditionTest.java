package com.epam.campus.testng;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorAdditionTest {
    Calculator calc;

    @BeforeClass
    public void initialize() {
        calc = new Calculator();
    }

    @Test
    public void testPositiveAddition() {
        // basic positive numbers
        Assert.assertEquals(calc.add(2, 3), 5);
        Assert.assertEquals(calc.add(10, 20), 30);

        // adding zero
        Assert.assertEquals(calc.add(0, 5), 5);
        Assert.assertEquals(calc.add(7, 0), 7);

        // large numbers
        Assert.assertEquals(calc.add(1000, 2000), 3000);
    }

    @Test
    public void testNegativeAddition() {
        // both negative
        Assert.assertEquals(calc.add(-2, -99), -101);
        Assert.assertEquals(calc.add(-10, -20), -30);

        // mixed values
        Assert.assertEquals(calc.add(-5, 5), 0);
        Assert.assertEquals(calc.add(5, -10), -5);

        // negative with zero
        Assert.assertEquals(calc.add(-7, 0), -7);
    }

    @Test
    public void testSubtraction() {
        // basic subtraction
        Assert.assertEquals(calc.subtract(9, 6), 3);
        Assert.assertEquals(calc.subtract(20, 10), 10);

        // subtracting zero
        Assert.assertEquals(calc.subtract(5, 0), 5);

        // negative results
        Assert.assertEquals(calc.subtract(5, 10), -5);

        // negative numbers
        Assert.assertEquals(calc.subtract(-10, -5), -5);
        Assert.assertEquals(calc.subtract(-10, 5), -15);
    }

    @Test
    public void mixedAdditionSubtraction() {

        // addition followed by subtraction (positive flow)
        int sum = calc.add(10, 5);          // 15
        int result = calc.subtract(sum, 3); // 12
        Assert.assertEquals(result, 12);

        // subtraction followed by addition
        int diff = calc.subtract(20, 8);    // 12
        int finalResult = calc.add(diff, 4);// 16
        Assert.assertEquals(finalResult, 16);

        // mixed positive and negative values
        int mixedSum = calc.add(-10, 30);   // 20
        Assert.assertEquals(calc.subtract(mixedSum, 5), 15);

        // zero interaction
        int zeroCase = calc.add(0, 7);      // 7
        Assert.assertEquals(calc.subtract(zeroCase, 7), 0);

        // negative result flow
        int negativeFlow = calc.add(5, 3);  // 8
        Assert.assertEquals(calc.subtract(negativeFlow, 10), -2);
    }


    @AfterClass
    public void testingEnd() {
        System.out.println("CalculatorAdditionTest Completed.\n");
    }
}
