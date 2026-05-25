package com.epam.campus;

import java.util.Arrays;
import java.util.List;

/**
 * ------------------------------------------------------------
 * Main Class
 * ------------------------------------------------------------
 *
 * Entry point of the application.
 *
 * This class demonstrates how to use the
 * {@link ParallelStreamsProcessor} to process
 * collections using Java Parallel Streams.
 *
 * The example calculates the sum of all even numbers
 * from a given list of integers.
 */
public class Main {

    /**
     * Program execution starts here.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        /**
         * ------------------------------------------------------------
         * Step 1: Create processor instance
         * ------------------------------------------------------------
         *
         * ParallelStreamsProcessor encapsulates logic that
         * uses parallel streams for faster data processing
         * on multi-core systems.
         */
        ParallelStreamsProcessor processor = new ParallelStreamsProcessor();

        /**
         * ------------------------------------------------------------
         * Step 2: Prepare input data
         * ------------------------------------------------------------
         *
         * Arrays.asList() is used to create a fixed-size
         * list of integers for demonstration purposes.
         */
        List<Integer> numbers = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        );

        /**
         * ------------------------------------------------------------
         * Step 3: Process data using parallel streams
         * ------------------------------------------------------------
         *
         * calculateSumOfEvenNumbers():
         *  - Filters even numbers
         *  - Processes elements in parallel
         *  - Computes and returns their sum
         */
        int sum = processor.calculateSumOfEvenNumbers(numbers);

        /**
         * ------------------------------------------------------------
         * Step 4: Display result
         * ------------------------------------------------------------
         */
        System.out.println("Sum of even numbers: " + sum);
    }
}
