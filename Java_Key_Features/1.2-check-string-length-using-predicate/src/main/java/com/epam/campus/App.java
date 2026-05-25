package com.epam.campus;

import java.util.function.Predicate;

/**
 * The App class demonstrates the usage of the ListOfStrings utility.
 * <p>
 * It inserts several strings into a ListOfStrings instance and checks
 * if any string in the list is longer than 5 characters.
 * </p>
 * 
 * @author YourName
 */
public class App {
    /**
     * The main method is the entry point of the application.
     * It demonstrates inserting strings and checking their lengths using ListOfStrings.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create an instance of ListOfStrings
        ListOfStrings los = new ListOfStrings();

        // Insert various strings into the list
        los.insertString("one");
        los.insertString("noone");
        los.insertString("Bravo Six");
        los.insertString("Alex Mercer");
        los.insertString("NopeThisAintIt");

        // Check if any string in the list is longer than 5 characters and print the result
        System.out.println(los.containsStringLongerThan5());
    }
}