package com.epam.campus;

import java.util.PriorityQueue;

/**
 * Main application class for the Customer Support System.
 * Demonstrates the priority queue-based customer support system by creating
 * customers with different priorities and processing them in order.
 */
public class App {
    /**
     * Main method to run the Customer Support System.
     * Creates a priority queue of customers with different priorities and processes them
     * in descending order of priority.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {

        PriorityQueue<Customer> customers = new PriorityQueue<Customer>(new CustomerComparator());

        customers.add(new Customer("Jack", "Faulty Display", 5));
        customers.add(new Customer("Emma", "Battery Issue", 3));
        customers.add(new Customer("Liam", "Software Crash", 2));
        customers.add(new Customer("Olivia", "Screen Flicker", 4));
        customers.add(new Customer("Noah", "Slow Performance", 1));

        CustomerSupportSystem pq = new CustomerSupportSystem();

        pq.prioritySupport(customers);
    }
}
