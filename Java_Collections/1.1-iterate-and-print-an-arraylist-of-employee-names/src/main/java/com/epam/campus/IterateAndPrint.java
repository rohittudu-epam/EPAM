package com.epam.campus;

import java.util.ArrayList;
import java.util.Iterator;

public class IterateAndPrint {
    /**
     * 
     * @param employeeNames takes an ArrayList with Strings of Employee Names
     */
    public static void printIterator(ArrayList<String> employeeNames) {
        if (employeeNames == null) {
            throw new IllegalArgumentException("No Names Passed to Iterate");
        }

        // Iterator Object for ArrayList
        Iterator<String> employeeIterator = employeeNames.iterator();

        System.out.println("\nIterating With Iterator\n");

        // Implementation of Iterator for Iteration
        while (employeeIterator.hasNext()) {
            String element = employeeIterator.next();
            System.out.println(element);
        }

        System.out.println("\nIteration Ended\n");
    }

    /**
     * 
     * @param employeeNames takes an ArrayList with Strings of Employee Names
     */
    public static void printForEach(ArrayList<String> employeeNames) {
        if (employeeNames == null) {
            throw new IllegalArgumentException("No Names Passed to Iterate");
        }

        System.out.println("\nIterating With For Each Loop\n");

        // Implementation of For-Each-Loop for Iteration
        for (String s : employeeNames) {
            System.out.println(s);
        }

        System.out.println("\nIteration Ended\n");
    }
}
