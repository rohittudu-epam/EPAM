package com.epam.campus;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * PredicateFilter demonstrates how to combine multiple Predicate
 * functional interfaces to apply complex filtering logic on a list of integers.
 *
 * <p>
 * This class maintains an internal list of integers and provides
 * a method to filter numbers that:
 * <ul>
 *   <li>are greater than 10</li>
 *   <li>are divisible by 3</li>
 * </ul>
 *
 * The filtering logic is implemented using Predicate composition.
 * </p>
 */
public class PredicateFilter {

    /**
     * Internal list that stores integers added by the user.
     */
    private ArrayList<Integer> numbers;

    /**
     * Default constructor initializes the internal list.
     */
    public PredicateFilter() {
        numbers = new ArrayList<>();
    }

    /**
     * Adds a number to the internal list.
     *
     * @param num the integer value to be added
     */
    public void insertNumber(int num) {
        numbers.add(num);
        System.out.println("Number Added: " + num + "\n");
    }

    /**
     * Filters the stored numbers using combined Predicate conditions.
     *
     * <p>
     * The filtering conditions are:
     * <ul>
     *   <li>The number must be greater than 10</li>
     *   <li>The number must be divisible by 3</li>
     * </ul>
     *
     * These conditions are combined using {@link Predicate#and(Predicate)}
     * to form a single composite predicate.
     * </p>
     *
     * @return a new ArrayList containing numbers that satisfy both conditions
     */
    public ArrayList<Integer> predicateFilter() {

        // Predicate to check if a number is greater than 10
        Predicate<Integer> greaterThan10 = num -> num > 10;

        // Predicate to check if a number is divisible by 3
        Predicate<Integer> divisibleBy3 = num -> num % 3 == 0;

        // Combine both predicates using logical AND
        Predicate<Integer> numberFilter = greaterThan10.and(divisibleBy3);

        // Apply the combined predicate using Stream API and return the result
        return new ArrayList<>(
                numbers.stream()
                       .filter(numberFilter)
                       .toList()
        );
    }
}
