package com.epam.campus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


/**
 * This class demonstrates how the HashSet only stores unique values
 * and discards any duplicates. Provides generic implementation for different types.
 */
public class HashSetImplementation {

    /**
     * Generic method to analyze duplicate elements and display both original and unique values
     * @param <T> Type parameter for generic implementation
     * @param elements takes a list of elements for analysis
     * @throws IllegalArgumentException if elements list is null or empty
     */
    public static <T> void checkDuplicateInsertion(ArrayList<T> elements) {
        // Null and empty validation checks
        if (elements == null) {
            throw new IllegalArgumentException("Elements list cannot be null");
        }
        if (elements.isEmpty()) {
            throw new IllegalArgumentException("No elements passed");
        }

        // Validate elements - check for null entries
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) == null) {
                throw new IllegalArgumentException("Element at index " + i + " is null. Please provide valid elements.");
            }
        }

        // Display original elements with duplicates
        System.out.println("\n=== ORIGINAL ELEMENTS (with duplicates) ===");
        displayElements(elements);

        // HashSet initiation for storing unique values
        HashSet<T> set = new HashSet<>(elements);

        // Display unique elements
        System.out.println("\n=== UNIQUE ELEMENTS ===");
        displayElements(new ArrayList<>(set));

        // Display statistics
        System.out.println("\n=== STATISTICS ===");
        System.out.println("Total elements (with duplicates): " + elements.size());
        System.out.println("Unique elements: " + set.size());
        System.out.println("Duplicate elements removed: " + (elements.size() - set.size()));

        // Display duplicate elements that were removed
        displayDuplicateElements(elements, set);
    }

    /**
     * Generic method to display elements in a collection
     * @param <T> Type parameter for generic implementation
     * @param elements the collection to display
     */
    private static <T> void displayElements(List<T> elements) {
        if (elements == null || elements.isEmpty()) {
            System.out.println("No elements to display.");
            return;
        }

        Iterator<T> iterator = elements.iterator();
        int count = 1;
        while (iterator.hasNext()) {
            T element = iterator.next();
            System.out.println(count + ". " + element);
            count++;
        }
    }

    /**
     * Generic method to identify and display duplicate elements
     * @param <T> Type parameter for generic implementation
     * @param originalElements the original list with duplicates
     * @param uniqueSet the set of unique elements
     */
    private static <T> void displayDuplicateElements(ArrayList<T> originalElements, HashSet<T> uniqueSet) {
        System.out.println("\n=== DUPLICATE ELEMENTS REMOVED ===");

        ArrayList<T> duplicates = new ArrayList<>();
        HashSet<T> seen = new HashSet<>();

        for (T element : originalElements) {
            if (!seen.add(element)) {
                // Element already exists in 'seen', so it's a duplicate
                if (!duplicates.contains(element)) {
                    duplicates.add(element);
                }
            }
        }

        if (duplicates.isEmpty()) {
            System.out.println("No duplicate elements found.");
        } else {
            displayElements(duplicates);
        }
    }
}
