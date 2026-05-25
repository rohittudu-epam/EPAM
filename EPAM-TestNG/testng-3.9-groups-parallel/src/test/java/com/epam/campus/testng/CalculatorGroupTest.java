package com.epam.campus.testng;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorGroupTest {

    // ---------- Data Providers ----------

    @DataProvider(name = "additionDataProvider")
    public Object[][] additionDataProvider() {
        return new Object[][]{
                {2, 3, 5},
                {-1, 1, 0},
                {0, 0, 0}
        };
    }

    @DataProvider(name = "subtractionDataProvider")
    public Object[][] subtractionDataProvider() {
        return new Object[][]{
                {5, 3, 2},
                {3, 5, -2},
                {0, 5, -5}
        };
    }

    @DataProvider(name = "multiplicationDataProvider")
    public Object[][] multiplicationDataProvider() {
        return new Object[][]{
                {2, 3, 6},
                {-2, 3, -6},
                {0, 5, 0}
        };
    }

    @DataProvider(name = "divisionDataProvider")
    public Object[][] divisionDataProvider() {
        return new Object[][]{
                {6, 3, 2},
                {-6, 3, -2}
        };
    }

    @DataProvider(name = "divisionByZeroProvider")
    public Object[][] divisionByZeroProvider() {
        return new Object[][]{
                {5, 0}
        };
    }

    // ---------- Tests ----------

    @Test(groups = {"arithmetic"}, dataProvider = "additionDataProvider")
    public void testAddition(int a, int b, int expected) {
        int result = a + b;
        Assert.assertEquals(result, expected, "Addition failed");
    }

    @Test(groups = {"arithmetic"}, dataProvider = "subtractionDataProvider")
    public void testSubtraction(int a, int b, int expected) {
        int result = a - b;
        Assert.assertEquals(result, expected, "Subtraction failed");
    }

    @Test(groups = {"advanced"}, dataProvider = "multiplicationDataProvider")
    public void testMultiplication(int a, int b, int expected) {
        int result = a * b;
        Assert.assertEquals(result, expected, "Multiplication failed");
    }

    @Test(groups = {"advanced"}, dataProvider = "divisionDataProvider")
    public void testDivision(int a, int b, int expected) {
        int result = a / b;
        Assert.assertEquals(result, expected, "Division failed");
    }

    @Test(groups = {"advanced", "edge-cases"}, dataProvider = "divisionByZeroProvider",
            expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero(int a, int b) {
        int result = a / b; // should throw exception
    }
}
