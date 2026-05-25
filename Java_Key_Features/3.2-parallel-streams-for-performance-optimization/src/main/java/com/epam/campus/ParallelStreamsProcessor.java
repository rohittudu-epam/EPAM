package com.epam.campus;

import java.util.List;

/**
 * ------------------------------------------------------------
 * ParallelStreamsProcessor
 * ------------------------------------------------------------
 *
 * This class demonstrates how to use Java Parallel Streams
 * to process collections efficiently on multi-core systems.
 *
 * Current responsibility:
 *  - Calculate the sum of all even numbers from a list of integers
 *
 * Parallel streams automatically split work across available
 * CPU cores, making them useful for large datasets.
 */
public class ParallelStreamsProcessor {

    /**
     * ------------------------------------------------------------
     * Calculate Sum of Even Numbers
     * ------------------------------------------------------------
     *
     * Processes a list of integers using a parallel stream and
     * computes the sum of all even values.
     *
     * Processing steps:
     *  1. Convert the list into a parallel stream
     *  2. Filter only even numbers
     *  3. Reduce the stream by summing the values
     *
     * Edge case handling:
     *  - If the input list is null, an IllegalArgumentException is thrown
     *  - If the list is empty, the method safely returns 0
     *
     * @param numbers list of integers to process
     * @return sum of all even numbers (0 if none exist)
     * @throws IllegalArgumentException if numbers is null
     */
    public int calculateSumOfEvenNumbers(List<Integer> numbers) {

        // Validate input to avoid NullPointerException
        if (numbers == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }

        // Use parallel stream to filter even numbers and compute sum
        return numbers.parallelStream()
                .filter(n -> n % 2 == 0)
                .reduce(0, Integer::sum);
    }
}
