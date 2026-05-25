package com.epam.campus;

import java.util.ArrayList;

/**
 * App class demonstrates the usage of the RemoveEven utility class.
 * 
 * This class contains multiple test cases that showcase how to use an Iterator
 * to safely remove elements from an ArrayList while iterating over it.
 * 
 * Each example illustrates different scenarios:
 * - Mixed odd and even numbers
 * - Lists with only even or only odd numbers
 * - Edge cases like single elements and empty results
 * - Negative numbers, duplicates, and large collections
 * 
 * @author EPAM Campus
 */
public class App {
    
    /**
     * Main method that runs all 10 test cases for the RemoveEven functionality.
     * 
     * Each test case demonstrates a different scenario for removing even numbers
     * from an ArrayList using an Iterator. The results are printed to the console.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        RemoveEven remover = new RemoveEven();

        // Example 1: List with mixed odd and even numbers
        // Expected output: 5, 9 (even numbers 2, 4 are removed)
        ArrayList<Integer> nums1 = new ArrayList<>();
        nums1.add(5);
        nums1.add(2);
        nums1.add(9);
        nums1.add(4);
        System.out.println("Example 1: Mixed odd and even numbers");
        remover.removeEvenIterator(nums1);

        // Example 2: List with only even numbers
        // Expected output: (empty - all elements are removed)
        ArrayList<Integer> nums2 = new ArrayList<>();
        nums2.add(2);
        nums2.add(4);
        nums2.add(6);
        nums2.add(8);
        System.out.println("\nExample 2: List with only even numbers (all removed)");
        remover.removeEvenIterator(nums2);

        // Example 3: List with only odd numbers
        // Expected output: 1, 3, 5, 7 (no numbers are removed)
        ArrayList<Integer> nums3 = new ArrayList<>();
        nums3.add(1);
        nums3.add(3);
        nums3.add(5);
        nums3.add(7);
        System.out.println("\nExample 3: List with only odd numbers (none removed)");
        remover.removeEvenIterator(nums3);

        // Example 4: Single element list (even)
        // Expected output: (empty - the single even element is removed)
        ArrayList<Integer> nums4 = new ArrayList<>();
        nums4.add(10);
        System.out.println("\nExample 4: Single even element");
        remover.removeEvenIterator(nums4);    
    }
}
