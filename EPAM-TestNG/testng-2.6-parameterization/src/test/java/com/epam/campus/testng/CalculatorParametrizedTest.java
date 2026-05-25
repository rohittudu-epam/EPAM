package com.epam.campus.testng;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CalculatorParametrizedTest {

    private Calculator calc;

    @BeforeClass
    public void initializeCalculator() {
        calc = new Calculator();
    }

    @Test
    @Parameters({"addA", "addB", "addExpected"})
    public void testAddition(int a, int b, int expected) {
        Assert.assertEquals(calc.add(a, b), expected, "Addition failed");
    }

    @Test
    @Parameters({"subA", "subB", "subExpected"})
    public void testSubtraction(int a, int b, int expected) {
        Assert.assertEquals(calc.subtract(a, b), expected, "Subtraction failed");
    }

    @Test
    @Parameters({"mulA", "mulB", "mulExpected"})
    public void testMultiplication(int a, int b, int expected) {
        Assert.assertEquals(calc.multiply(a, b), expected, "Multiplication failed");
    }

    @Test
    @Parameters({"divA", "divB", "divExpected"})
    public void testDivision(int a, int b, int expected) {
        Assert.assertEquals(calc.divide(a, b), expected, "Division failed");
    }
}
