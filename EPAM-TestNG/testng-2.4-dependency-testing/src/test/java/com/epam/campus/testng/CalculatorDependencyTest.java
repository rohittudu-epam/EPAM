package com.epam.campus.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorDependencyTest {

    private Calculator calc;

    // Must execute first
    @Test(priority = 1)
    public void testInitializeCalculator() {
        calc = new Calculator();
        Assert.assertNotNull(calc, "Calculator initialization failed");
    }

    // Depends on initialization
    @Test(
            dependsOnMethods = {"testInitializeCalculator"},
            priority = 2
    )
    public void testAddition() {
        int result = calc.add(10, 5);
        Assert.assertEquals(result, 15, "Addition result incorrect");
    }

    // Depends on addition
    @Test(
            dependsOnMethods = {"testAddition"},
            priority = 3
    )
    public void testSubtraction() {
        int result = calc.subtract(10, 5);
        Assert.assertEquals(result, 5, "Subtraction result incorrect");
    }

    // Independent but higher priority than division
    @Test(
            dependsOnMethods = {"testInitializeCalculator"},
            priority = 4
    )
    public void testMultiplication() {
        int result = calc.multiply(4, 5);
        Assert.assertEquals(result, 20, "Multiplication result incorrect");
    }

    // Lowest priority test + edge case
    @Test(
            dependsOnMethods = {"testInitializeCalculator"},
            priority = 5
    )
    public void testDivision() {
        int result = calc.divide(20, 4);
        Assert.assertEquals(result, 5, "Division result incorrect");
    }
}
