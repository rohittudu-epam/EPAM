package com.epam.campus;

import java.util.Scanner;

/**
 * Handles division operations with comprehensive error handling for division by
 * zero,
 * overflow conditions, and invalid input validation.
 */
public class HandleZeroDivision {
    private Scanner scanner;
    private static final String SEPARATOR = "===================================";

    // Constructor initializes Scanner for user input
    public HandleZeroDivision() {
        this.scanner = new Scanner(System.in);
    }

    // Performs division with error handling and displays results
    public void performDivision() {
        try {
            // Get and validate inputs from user
            int dividend = getValidatedInput("Enter the dividend (numerator): ");
            int divisor = getValidatedInput("Enter the Divisor: ");

            // Check for division by zero
            if (divisor == 0) {
                throw new ArithmeticException("Division by Zero Not allowed");
            }

            // Check for integer overflow when dividing Integer.MIN_VALUE by -1
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                throw new ArithmeticException("Result would overflow: Integer.MIN_VALUE cannot be negated");
            }

            // Calculate both integer and decimal results
            int result = dividend / divisor;
            double decimalResult = (double) dividend / divisor;

            // Display results
            System.out.println(SEPARATOR);
            System.out.println("Division Result (Integer: )" + dividend + "/" + divisor + " = " + result);

            System.out.println("Division Result (Decimal): " + String.format("%.2f", decimalResult));
            System.out.println(SEPARATOR);
        } catch (ArithmeticException e) {
            // Handle arithmetic exceptions (division by zero, overflow)
            System.out.println(SEPARATOR);
            System.out.println("Arithmetic Error: " + e.getMessage());
            System.out.println("Reason: Cannot perform divison operation");
            System.out.println(SEPARATOR);
        } catch (java.util.InputMismatchException e) {
            // Handle invalid input (non-integer values)
            System.out.println(SEPARATOR);
            System.out.println("Input Erro: Invalid input detected");
            System.out.println("Reason: Please enter valid integers only");
            System.out.println("Details: " + e.getMessage());
            System.out.println(SEPARATOR);
            scanner.nextLine();
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            System.out.println(SEPARATOR);
            System.out.println("Message: " + e.getMessage());
            System.out.println(SEPARATOR);
        } finally {
            // Cleanup resources and notify user
            if (scanner != null) {
                System.out.println("\nProgram Execution has ended.");
                System.out.println("Resources cleaned up successfully");
            }
        }
    }

    // Reads and validates integer input from user
    public int getValidatedInput(String prompt) {
        System.out.println(prompt + ": ");

        int value = scanner.nextInt();

        // Warn user if input is at extreme values
        if (value == Integer.MIN_VALUE || value == Integer.MAX_VALUE) {
            System.out.println("Warning: Input is at Integer boundary. Proceed with Caution.");
        }

        return value;
    }

    // Closes the Scanner resource to prevent memory leaks
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
