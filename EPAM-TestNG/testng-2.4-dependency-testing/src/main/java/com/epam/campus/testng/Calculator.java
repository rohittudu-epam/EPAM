package com.epam.campus.testng;

public class Calculator {

    /**
     * Adds two integers.
     *
     * @param a Integer value 1
     * @param b Integer value 2
     * @return The sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts the second integer from the first integer.
     *
     * @param a Integer value 1
     * @param b Integer value 2
     * @return The result of a - b
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two integers.
     *
     * @param a Integer value 1
     * @param b Integer value 2
     * @return The product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides the first integer by the second integer.
     *
     * @param a Integer value 1
     * @param b Integer value 2
     * @return The result of a / b
     * @throws ArithmeticException if b is 0
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }
}
