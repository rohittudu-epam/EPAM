package com.epam.campus;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        // Scanner for Input
        Scanner scan = new Scanner(System.in);

        final int MAX_SIZE = 1000;

        System.out.println("Enter # of Strings to input: ");
        // Validate integer input
        if (!scan.hasNextInt()) {
            scan.close();
            throw new IllegalArgumentException("Invalid input: expected an integer value.");
        }

        int n = scan.nextInt();

        // Validate negative number
        if (n < 0) {
            scan.close();
            throw new IllegalArgumentException("Number of strings cannot be negative.");
        }

        // Validate maximum size limit
        if (n > MAX_SIZE) {
            scan.close();
            throw new IllegalArgumentException(
                    "Input size exceeds the maximum allowed limit of " + MAX_SIZE);
        }

        // Consume leftover newline after nextInt()
        scan.nextLine();

        // ArrayList to store multiple Strings
        ArrayList<String> listOfStrings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // Adding String to ArrayList
            String input = scan.nextLine();
            if (!input.isEmpty()) {
                listOfStrings.add(input);
            }
        }

        StringUtils util = new StringUtils();
        util.identifyDuplicates(listOfStrings);

        scan.close();
    }
}
