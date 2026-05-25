package com.epam.campus;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Demonstrates the use of Java Stream API's flatMap operation
 * to flatten a nested {@link ArrayList} structure.
 *
 * <p>
 * This class maintains a collection of integer lists and provides
 * functionality to insert lists and flatten them into a single list.
 * </p>
 */
public class FlatMapExample {

    /**
     * Stores a collection of integer lists.
     * Each inner list represents a separate group of integers.
     */
    private ArrayList<ArrayList<Integer>> listOfLists;

    /**
     * Constructs an empty {@code FlatMapExample} instance.
     * Initializes the internal list structure.
     */
    public FlatMapExample() {
        this.listOfLists = new ArrayList<>();
    }

    /**
     * Inserts a non-null and non-empty {@link ArrayList} of integers
     * into the collection.
     *
     * @param array the list of integers to be added
     * @throws IllegalArgumentException if the provided list is null or empty
     */
    public void insertArray(ArrayList<Integer> array) {
        if (array == null || array.isEmpty()) {
            throw new IllegalArgumentException("The list contains no Element");
        }
        listOfLists.add(array);
    }

    /**
     * Flattens the nested {@code ArrayList<ArrayList<Integer>>}
     * into a single {@code ArrayList<Integer>}.
     *
     * <p>
     * Uses {@code flatMap} to transform multiple inner lists
     * into one continuous stream of integers.
     * </p>
     *
     * @return a flattened list containing all integers from all inner lists
     */
    public ArrayList<Integer> flattenArray() {
        return new ArrayList<>(
                listOfLists.stream()
                           .flatMap(Collection::stream)
                           .toList()
        );
    }

    /**
     * Returns the internal collection of integer lists.
     *
     * @return the list of integer lists
     */
    public ArrayList<ArrayList<Integer>> getArrayList() {
        return listOfLists;
    }
}
