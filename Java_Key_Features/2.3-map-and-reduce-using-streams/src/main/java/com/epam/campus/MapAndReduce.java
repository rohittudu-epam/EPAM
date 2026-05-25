package com.epam.campus;

import java.util.ArrayList;

/**
 * The MapAndReduce class demonstrates the use of Java Stream API's
 * map and reduce operations to perform functional-style computations.
 *
 * It maintains a collection of integers and provides functionality
 * to compute the sum of squares of all stored numbers using map-reduce.
 */
public class MapAndReduce {

    /**
     * A list that stores integer values inserted by the user.
     */
    private ArrayList<Integer> numbers;

    /**
     * Constructs a MapAndReduce object and initializes
     * the internal list of numbers.
     */
    public MapAndReduce() {
        numbers = new ArrayList<Integer>();
    }

    /**
     * Inserts a number into the list.
     *
     * @param num the integer value to be added
     */
    public void insertNumber(int num) {
        numbers.add(num);
        System.out.println("Number Added: " + num);
    }

    /**
     * Computes the sum of squares of all numbers in the list
     * using map and reduce operations.
     *
     * <p>
     * The map operation transforms each number into its square.
     * The reduce operation then accumulates the squared values
     * into a single result using addition.
     * </p>
     *
     * <p>
     * If the list is empty, the method returns 0 due to the
     * identity value used in the reduce operation.
     * </p>
     *
     * @return the sum of squares of all stored integers
     */
    public int mapReduce() {
        return numbers.stream()
                      .map(n -> n * n)
                      .reduce(0, (a, b) -> (a + b));
    }
}
