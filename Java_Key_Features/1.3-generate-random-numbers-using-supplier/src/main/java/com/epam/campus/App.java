package com.epam.campus;

/**
 * The App class demonstrates the usage of the GenerateRandom utility.
 * <p>
 * It creates an instance of GenerateRandom and generates random numbers
 * in various quantities using the getRandomNumber method.
 * </p>
 * 
 * @author YourName
 */
public class App {
    /**
     * The main method is the entry point of the application.
     * It demonstrates generating random numbers in different quantities.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create an instance of GenerateRandom
        GenerateRandom number = new GenerateRandom();

        // Generate and print 5 random numbers
        System.out.println("Generating 5 random numbers:");
        number.getRandomNumber(5);

        // Generate and print 10 random numbers
        System.out.println("\nGenerating 10 random numbers:");
        number.getRandomNumber(10);

        // Generate and print 1 random number
        System.out.println("\nGenerating 1 random number:");
        number.getRandomNumber(1);

        // Generate and print 20 random numbers
        System.out.println("\nGenerating 20 random numbers:");
        number.getRandomNumber(20);
    }
}