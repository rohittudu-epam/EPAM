package com.epam.campus;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * The GenerateRandom class demonstrates the use of the Supplier functional interface
 * to generate and print random numbers.
 * <p>
 * It uses a Supplier to provide random double values and prints a specified number of them.
 * </p>
 * 
 * @author YourName
 */
public class GenerateRandom {
    /**
     * Supplier that generates random double values.
     */
    Supplier<Double> randomNumber;

    /**
     * Constructs a GenerateRandom instance.
     */
    public GenerateRandom() {}

    /**
     * Generates and prints a specified number of random double values.
     *
     * @param n the number of random numbers to generate and print
     */
    public void getRandomNumber(int n) {
        // Initialize the Supplier to generate random double values
        randomNumber = () -> Math.random();

        // Generate and print n random numbers using the Supplier and Stream API
        Stream.generate(randomNumber).limit(n).forEach(System.out::println);
    }
}