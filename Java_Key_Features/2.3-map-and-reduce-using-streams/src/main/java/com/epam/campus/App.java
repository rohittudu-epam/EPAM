package com.epam.campus;

/**
 * The App class serves as the entry point of the application.
 *
 * <p>
 * It demonstrates the usage of the {@link MapAndReduce} class by:
 * <ul>
 *   <li>Creating an instance of MapAndReduce</li>
 *   <li>Inserting a set of integer values</li>
 *   <li>Executing the map-reduce operation</li>
 * </ul>
 * </p>
 *
 * The program computes and displays the sum of squares
 * of the inserted numbers using Java Stream API.
 */
public class App {

    /**
     * The main method is the starting point of the Java application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create an instance of MapAndReduce
        MapAndReduce mapReduceObject = new MapAndReduce();

        // Insert sample numbers into the collection
        mapReduceObject.insertNumber(5);
        mapReduceObject.insertNumber(10);
        mapReduceObject.insertNumber(51);
        mapReduceObject.insertNumber(13);
        mapReduceObject.insertNumber(37);

        // Perform map-reduce operation and display the result
        System.out.println("\nAfter MapReduce: " + mapReduceObject.mapReduce());
    }
}
