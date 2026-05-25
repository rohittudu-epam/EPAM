package com.epam.campus;

import java.util.Scanner;
import java.util.Optional;

/**
 * The {@code App} class demonstrates the usage of the {@link StringCheck} utility.
 * <p>
 * It prompts the user to enter a string, checks if the input is null, empty, or blank,
 * and prints the result. It also demonstrates the behavior when passing {@code null} as input.
 * </p>
 */
public class App {

    /**
     * The entry point of the application.
     * <p>
     * This method reads a string from the user, checks its validity using {@link StringCheck},
     * and prints the results for both user input and a {@code null} test case.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String userInput = scanner.nextLine();

        StringCheck strCheck = new StringCheck();

        Optional<String> optString = strCheck.checkNullString(userInput);
        Optional<String> nullTest = strCheck.checkNullString(null);

        printResult(optString, "User input");
        printResult(nullTest, "Null test");

        scanner.close();
    }

    /**
     * Prints the result of the string check.
     * <p>
     * If the {@link Optional} contains a value, it prints the value; otherwise,
     * it indicates that the input was null or empty.
     * </p>
     *
     * @param opt   the {@link Optional} result from {@link StringCheck}
     * @param label a label describing the test case
     */
    private static void printResult(Optional<String> opt, String label) {
        if (opt.isPresent()) {
            System.out.println(label + ": String is present! Value = \"" + opt.get() + "\"");
        } else {
            System.out.println(label + ": The input string is null or empty.");
        }
    }
}