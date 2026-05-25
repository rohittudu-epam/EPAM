package com.epam.campus;

/**
 * TextCompressor implements a run-length encoding compression algorithm.
 * 
 * This class provides a concrete implementation of the Compressor interface,
 * converting sequences of identical consecutive characters into a character
 * followed by the count of consecutive occurrences.
 * 
 * Example: "aabbbcc" becomes "a2b3c2"
 * 
 * @author EPAM Campus
 * @version 1.0
 */
public class TextCompressor implements Compressor {

    /**
     * Compresses the input string using run-length encoding algorithm.
     * 
     * Consecutive identical characters are replaced with the character itself
     * followed by the count of consecutive occurrences. For example:
     * - "aabbbcc" becomes "a2b3c2"
     * - "a" becomes "a1"
     * - "@@@###$$" becomes "@3#3$2"
     *
     * @param inputString the string to compress. Must not be null or empty,
     *                   and should contain only printable characters.
     * @return a compressed string representation using run-length encoding
     * @throws IllegalArgumentException if the input string is null, empty,
     *                                  or contains non-printable characters
     */
    @Override
    public String compress(String inputString) {
        // Comprehensive validation
        validateInput(inputString);

        // storing size for future use
        int size = inputString.length();

        // StringBuilder for efficient string building
        StringBuilder result = new StringBuilder();

        char prev = inputString.charAt(0);
        int count = 1;

        // Iterate through the string starting from index 1
        for (int i = 1; i < size; i++) {
            // checking for equality of current and previous characters
            if (prev == inputString.charAt(i)) {
                // incrementing count if the characters are same
                count++;
            } else {
                // Append character and its count to result
                result.append(prev).append(count);
                count = 1;
                prev = inputString.charAt(i);
            }
        }

        // Append the last character and its count
        result.append(prev).append(count);

        // conversion to String before returning
        return result.toString();
    }

    /**
     * Validates the input string for compression.
     * 
     * Checks that the string is not null or empty, and contains only
     * printable characters (no control characters or non-printable Unicode).
     *
     * @param inputString the string to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateInput(String inputString) {
        // Null and empty check
        if (inputString == null || inputString.isEmpty()) {
            throw new IllegalArgumentException("String cannot be null or empty");
        }

        // Check for non-printable characters
        for (char c : inputString.toCharArray()) {
            if (Character.isISOControl(c) && c != '\t' && c != '\n' && c != '\r') {
                throw new IllegalArgumentException("String contains non-printable control characters");
            }
        }
    }
}


