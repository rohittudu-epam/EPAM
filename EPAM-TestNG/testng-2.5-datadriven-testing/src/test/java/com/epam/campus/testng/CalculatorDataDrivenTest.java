package com.epam.campus.testng;

import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorDataDrivenTest {

    private Calculator calc;

    @BeforeClass
    public void calculatorInitialize() {
        calc = new Calculator();
    }

    @DataProvider(name = "additionData")
    public Object[][] additionData() {
        return new Object[][]{
                {1, 2, 3},
                {-1, -2, -3},
                {0, 0, 0}
        };
    }

    @DataProvider(name = "subtractionData")
    public Object[][] subtractionData() {
        return new Object[][]{
                {5, 3, 2},
                {-5, -3, -2},
                {0, 5, -5}
        };
    }

    @DataProvider(name = "multiplicationData")
    public Object[][] multiplicationData() {
        return new Object[][]{
                {2, 3, 6},
                {-2, 3, -6},
                {0, 5, 0}
        };
    }

    @DataProvider(name = "divisionData")
    public Object[][] divisionData() {
        return new Object[][]{
                {10, 2, 5},
                {-10, 2, -5},
                {0, 5, 0}
        };
    }

    @BeforeTest
    public void runBefore(){
        System.out.println("Before Test");
    }

    @BeforeMethod
    public void runBeforeMethod(){
        System.out.println("Before Method");
    }


    @Test(dataProvider = "additionData")
    public void testAdditionWithData(int a, int b, int expected) {
        Assert.assertEquals(calc.add(a, b), expected);
    }

    @Test(dataProvider = "subtractionData")
    public void testSubtractWithData(int a, int b, int expected) {
        Assert.assertEquals(calc.subtract(a, b), expected);
    }

    @Test(dataProvider = "multiplicationData")
    public void testMultiplyWithData(int a, int b, int expected) {
        Assert.assertEquals(calc.multiply(a, b), expected);
    }

    @Test(dataProvider = "divisionData")
    public void testDivisionWithData(int a, int b, int expected) {
        Assert.assertEquals(calc.divide(a, b), expected);
    }
}
