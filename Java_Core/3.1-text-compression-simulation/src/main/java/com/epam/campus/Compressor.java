package com.epam.campus;

/**
 * Interface defining the contract for string compression strategies.
 * Implementations of this interface provide different compression algorithms
 * for processing input strings.
 */
public interface Compressor {
    /**
     * Compresses the input string based on the implementation strategy.
     *
     * @param inputString the string to compress. Must not be null or empty.
     * @return a compressed representation of the input string
     * @throws IllegalArgumentException if the input string is null, empty, or contains non-printable characters
     */
    String compress(String inputString);
}
