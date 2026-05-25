package com.epam.campus;

/**
 * The App class demonstrates usage of the ConvertString utility.
 * It shows how to insert strings, convert them to uppercase, and print the results.
 */
public class App {
    /**
     * Main method to run demonstration examples for ConvertString.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create an instance of ConvertString
        ConvertString converter = new ConvertString();

        // Insert example strings
        converter.insertString("hello");
        converter.insertString("world");
        converter.insertString("Epam Campus");

        // Print original strings
        System.out.println("Original strings:");
        converter.printStrings();

        // Convert all strings to uppercase
        converter.convert();

        // Print converted strings
        System.out.println("\nConverted s   trings (to uppercase):");
        converter.printStrings();
    }
}