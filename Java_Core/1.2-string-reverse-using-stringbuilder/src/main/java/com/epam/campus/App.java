package com.epam.campus;

import java.util.Scanner;

/**
 * Application that demonstrates string reversal and address formatting.
 * Handles user input with comprehensive validation and error handling.
 */
public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            // String Reversal
            System.out.println("Enter String to Reverse: ");
            String input_string = scan.nextLine();

            // stringReverse method call with validation
            String reversedString = StringUtils.stringReverse(input_string);
            System.out.println("Reversed String: " + reversedString);

            // Address Input and Formatting
            System.out.println("\nEnter Your Address (Press Enter to skip optional parts)");
            System.out.println("Enter Street (required): ");
            String street = scan.nextLine();

            System.out.println("Enter City (optional): ");
            String city = scan.nextLine();

            System.out.println("Enter State (optional): ");
            String state = scan.nextLine();

            System.out.println("Enter Postal Code (optional): ");
            String postalCode = scan.nextLine();

            // formatAddress method call with comprehensive validation
            String formattedAddress = StringUtils.formatAddress(street, city, state, postalCode);
            System.out.println("Formatted Address: " + formattedAddress);

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } finally {
            // Closing Scanner to Avoid Memory leaks
            scan.close();
        }
    }
}
