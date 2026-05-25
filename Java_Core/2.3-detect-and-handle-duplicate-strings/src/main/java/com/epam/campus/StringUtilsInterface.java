package com.epam.campus;

import java.util.ArrayList;

/**
 * Interface for string utility operations, specifically duplicate detection.
 * 
 * This interface defines the contract for implementations that analyze lists of strings
 * and identify duplicate entries using hash-based algorithms.
 */
public interface StringUtilsInterface {
    /**
     * Identifies and reports all duplicate strings from the provided list.
     * 
     * Implementations should compare strings using proper equality checks and report
     * any duplicates found. The detection algorithm should handle edge cases such as
     * null values and empty strings appropriately.
     * 
     * @param listOfStrings list of strings to analyze for duplicates (must not be null or empty)
     * @throws EmptyListException if listOfStrings is null or empty
     */
    void identifyDuplicates(ArrayList<String> listOfStrings);
}
