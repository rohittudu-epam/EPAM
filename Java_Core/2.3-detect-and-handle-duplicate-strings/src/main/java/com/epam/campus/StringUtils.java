package com.epam.campus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Utility class for detecting duplicate strings in a collection.
 * 
 * This class implements a hash-based duplicate detection algorithm using Java's
 * hashCode() and equals() methods for efficient string comparison.
 * 
 * Algorithm Overview:
 * - Uses a HashMap to group strings by their hash code (hash bucketing)
 * - For each string with a matching hash code, performs equality comparison
 * - Handles hash collisions by comparing actual string equality
 * - Stores detected duplicates in a HashSet to avoid printing the same duplicate multiple times
 * - Time Complexity: O(n) for n strings (with minimal hash collisions)
 * - Space Complexity: O(n) in the worst case for storing strings and duplicates
 */
public class StringUtils implements StringUtilsInterface {
    /**
     * Identifies and prints all duplicate strings from the provided list.
     * 
     * This method analyzes an ArrayList of strings and detects duplicates using a two-step
     * verification process: first by hash code matching, then by equality comparison. This
     * approach efficiently handles hash collisions and ensures accurate duplicate detection.
     * 
     * Process Flow:
     * 1. Validates that the input list is not null and contains elements
     * 2. Creates a HashMap to organize strings by hash code into buckets
     * 3. Iterates through each string in the input list
     * 4. For each string, calculates its hash code and checks existing strings in that bucket
     * 5. If a matching string is found (via equals()), adds it to the duplicates HashSet
     * 6. Prints all detected duplicates to the console
     * 
     * @param listOfStrings ArrayList of strings to analyze for duplicates (must not be null or empty)
     * @throws IllegalArgumentException if listOfStrings is null or empty
     * 
     * @example
     * ArrayList<String> list = new ArrayList<>();
     * list.add("apple");
     * list.add("banana");
     * list.add("apple");
     * util.identifyDuplicates(list); // Outputs: apple
     */
    @Override
    public void identifyDuplicates(ArrayList<String> listOfStrings){

        // Validation Check: Ensure the list is not null and contains at least one element
        validateInput(listOfStrings);

        // Initialize data structures for duplicate detection
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        HashSet<String> duplicates = new HashSet<>();

        // Process each string and detect duplicates
        for (String s: listOfStrings){
            if (!s.isEmpty()) {
                checkAndAddDuplicate(s, map, duplicates);
            }
        }

        // Output all detected duplicates to the console
        printDuplicates(duplicates);
    }

    /**
     * Validates the input list to ensure it is not null and contains elements.
     * 
     * @param listOfStrings the list to validate
     * @throws EmptyListException if the list is null or empty
     */
    private void validateInput(ArrayList<String> listOfStrings) {
        if (listOfStrings == null || listOfStrings.size() <= 0){
            throw new EmptyListException("List of Strings is Empty");
        }
    }

    /**
     * Checks if a string is a duplicate of any string already in the hash bucket,
     * and adds it to the duplicates set if found. Handles hash collisions properly.
     * 
     * @param s the current string being processed
     * @param map the hash map containing string buckets by hash code
     * @param duplicates the set to store detected duplicate strings
     */
    private void checkAndAddDuplicate(String s, HashMap<Integer, ArrayList<String>> map, HashSet<String> duplicates) {
        // Calculate hash code for the current string
        int hash = s.hashCode();

        // Initialize a new bucket for this hash code if it doesn't exist
        initializeBucketIfNeeded(hash, map);

        // Check for duplicates by comparing with existing strings in the same hash bucket
        // This handles hash collisions by using equals() comparison
        if (isDuplicateInBucket(s, map.get(hash))) {
            duplicates.add(s);
        }

        // Store the current string in its hash bucket for future comparisons
        map.get(hash).add(s);
    }

    /**
     * Initializes a new bucket (ArrayList) in the map if the given hash code doesn't have one.
     * 
     * @param hash the hash code for which to create a bucket
     * @param map the hash map to update
     */
    private void initializeBucketIfNeeded(int hash, HashMap<Integer, ArrayList<String>> map) {
        if (!map.containsKey(hash)){
            map.put(hash, new ArrayList<>());
        }
    }

    /**
     * Checks if a given string exists in the bucket (ArrayList) using equals() comparison.
     * This method properly handles hash collisions by comparing actual string equality.
     * 
     * @param s the string to check for duplicates
     * @param bucket the list of strings with the same hash code
     * @return true if the string already exists in the bucket, false otherwise
     */
    private boolean isDuplicateInBucket(String s, ArrayList<String> bucket) {
        for (String existing: bucket){
            if (existing.equals(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * Prints all detected duplicate strings to the console, one per line.
     * 
     * @param duplicates the set of duplicate strings to print
     */
    private void printDuplicates(HashSet<String> duplicates) {
        for (String dup: duplicates){
            System.out.println(dup);
        }
    }
    
}
