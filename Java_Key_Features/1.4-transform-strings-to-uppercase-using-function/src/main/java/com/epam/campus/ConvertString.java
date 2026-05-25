package com.epam.campus;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * ConvertString provides functionality to store, convert, and print a list of strings.
 * Strings can be inserted, converted to uppercase, and printed.
 */
public class ConvertString {
    private ArrayList<String> strings;

    /**
     * Constructs a new ConvertString instance with an empty list of strings.
     */
    public ConvertString() {
        strings = new ArrayList<>();
    }

    /**
     * Converts all stored strings to uppercase.
     */
    public void convert() {
        Function<String, String> convertToUpper = str -> str.toUpperCase();
        // Update the list with converted strings
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, convertToUpper.apply(strings.get(i)));
        }
        // Alternatively, using streams:
        // strings = new ArrayList<>(strings.stream().map(convertToUpper).toList());
    }

    /**
     * Inserts a non-null, non-empty string into the list.
     * @param s the string to insert
     * @throws IllegalArgumentException if the string is null or empty
     */
    public void insertString(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("String must not be null or empty");
        }
        strings.add(s);
    }

    /**
     * Prints all stored strings to the standard output.
     */
    public void printStrings() {
        for (String s : strings) {
            System.out.println(s);
        }
    }
}