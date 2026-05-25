package com.epam.campus;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("\n========== HashSet Duplicate Checker ==========");
            System.out.println("Enter Number of Elements: ");

            // Validate number input
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty");
            }

            int number = Integer.parseInt(input);

            // Validate positive number of elements
            if (number <= 0) {
                throw new IllegalArgumentException("Number of elements must be greater than 0");
            }

            // ArrayList for Storing the elements
            ArrayList<String> elements = new ArrayList<>();

            System.out.println("Enter the elements (one per line):");

            // Adding input to ArrayList with validation
            for (int i = 0; i < number; i++) {
                String element = scanner.nextLine().trim();
                if (element.isEmpty()) {
                    System.out.println("Warning: Empty element detected at position " + (i + 1) + ". Skipping...");
                    i--; // Retry this iteration
                    continue;
                }
                elements.add(element);
            }

            // Validate that we have collected required elements
            if (elements.size() == 0) {
                throw new IllegalArgumentException("No valid elements were provided");
            }

            // Calling checkDuplicateInsertion method for HashSet Demonstration
            HashSetImplementation.checkDuplicateInsertion(elements);

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid input. Please enter a valid integer for the number of elements.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Closing scanner to avoid Memory Leak
            scanner.close();
        }
    }
}
