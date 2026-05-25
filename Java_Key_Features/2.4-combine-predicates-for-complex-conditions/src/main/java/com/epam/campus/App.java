package com.epam.campus;

/**
 * Entry point of the application.
 *
 * <p>
 * This class demonstrates the usage of {@link PredicateFilter}
 * to combine multiple Predicate functional interfaces and apply
 * complex filtering logic on a collection of integers.
 * </p>
 *
 * <p>
 * The example filters numbers that:
 * <ul>
 *   <li>are greater than 10</li>
 *   <li>are divisible by 3</li>
 * </ul>
 * </p>
 */
public class App {

    /**
     * Main method – application execution starts here.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create an instance of PredicateFilter
        PredicateFilter filter = new PredicateFilter();

        // Insert sample numbers into the list
        filter.insertNumber(5);    // less than 10
        filter.insertNumber(9);    // divisible by 3 but not > 10
        filter.insertNumber(12);   // valid: > 10 and divisible by 3
        filter.insertNumber(15);   // valid: > 10 and divisible by 3
        filter.insertNumber(20);   // > 10 but not divisible by 3
        filter.insertNumber(30);   // valid: > 10 and divisible by 3

        /*
         * Apply the combined Predicate logic:
         * 1. Number must be greater than 10
         * 2. Number must be divisible by 3
         */
        System.out.println("Filtered Numbers (greater than 10 and divisible by 3):");
        System.out.println(filter.predicateFilter());
    }
}
