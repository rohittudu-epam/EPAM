package com.epam.campus;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point of the application.
 *
 * <p>
 * This class demonstrates how the {@link FlatMapExample} class works
 * by creating multiple nested integer lists, flattening them using
 * Java Stream API, and printing the results to the console.
 * </p>
 */
public class App {

    /**
     * Main method that drives the program execution.
     *
     * <p>
     * Two {@link FlatMapExample} objects are created to showcase
     * independent flattening of nested integer lists.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // First FlatMapExample instance
        FlatMapExample flatmap = new FlatMapExample();

        // Inserting nested integer lists
        flatmap.insertArray(new ArrayList<>(List.of(1)));
        flatmap.insertArray(new ArrayList<>(List.of(2, 3)));
        flatmap.insertArray(new ArrayList<>(List.of(4, 5)));
        flatmap.insertArray(new ArrayList<>(List.of(6, 7, 8)));

        // Second FlatMapExample instance
        FlatMapExample flatmap1 = new FlatMapExample();

        // Inserting nested integer lists
        flatmap1.insertArray(new ArrayList<>(List.of(9, 10)));
        flatmap1.insertArray(new ArrayList<>(List.of(11, 12, 13)));
        flatmap1.insertArray(new ArrayList<>(List.of(14)));
        flatmap1.insertArray(new ArrayList<>(List.of(15, 16)));
        flatmap1.insertArray(new ArrayList<>(List.of(17, 18, 19)));
        flatmap1.insertArray(new ArrayList<>(List.of(20)));

        // Flattening the nested lists
        ArrayList<Integer> flatMapResult1 = flatmap.flattenArray();
        ArrayList<Integer> flatMapResult2 = flatmap1.flattenArray();

        // Printing original and flattened lists
        System.out.println("Original Array: " + flatmap.getArrayList());
        System.out.println("Flatten Array: " + flatMapResult1);

        System.out.println();

        System.out.println("Original Array: " + flatmap1.getArrayList());
        System.out.println("Flatten Array: " + flatMapResult2);
    }
}
