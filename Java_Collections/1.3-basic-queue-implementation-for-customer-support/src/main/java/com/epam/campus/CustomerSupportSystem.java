package com.epam.campus;

import java.util.PriorityQueue;

/**
 * Manages customer support by processing customers from a priority queue.
 * Customers are serviced in order of their priority level (highest first).
 * Provides detailed reporting of customer issues and processing statistics.
 */
public class CustomerSupportSystem implements SupportSystemInterface{
    private int totalCustomersProcessed;
    private int totalPrioritySum;

    /**
     * Default constructor initializing customer processing statistics.
     */
    public CustomerSupportSystem() {
        this.totalCustomersProcessed = 0;
        this.totalPrioritySum = 0;
    }

    /**
     * Processes customers from the priority queue in priority order.
     * Continuously removes and services customers from the queue until empty.
     * Displays detailed information including priority, name, and issue for each customer.
     * Generates a summary report at the end.
     *
     * @param customers A PriorityQueue of Customer objects to process
     */
    @Override
    public void prioritySupport (PriorityQueue<Customer> customers){

        System.out.println("========================================");
        System.out.println("Servicing Customers on Priority Basis");
        System.out.println("========================================\n");
        
        while(customers.peek() != null){
            Customer currentCustomer = customers.remove();
            processCustomer(currentCustomer);
        }

        printSummary();
    }

    /**
     * Processes an individual customer request.
     * Displays comprehensive details about the customer and their issue.
     *
     * @param customer The customer to process
     */
    private void processCustomer(Customer customer) {
        this.totalCustomersProcessed++;
        this.totalPrioritySum += customer.getPriority();

        System.out.println("Servicing Customer #" + this.totalCustomersProcessed + ":");
        System.out.println("  Priority Level: " + customer.getPriority() + "/5");
        System.out.println("  Customer Name: " + customer.getCustomerName());
        System.out.println("  Issue: " + customer.getIssue());
        System.out.println("----------------------------------------\n");
    }

    /**
     * Prints a summary report of all processed customers.
     * Includes total count and average priority level.
     */
    private void printSummary() {
        System.out.println("========================================");
        System.out.println("SUMMARY REPORT");
        System.out.println("========================================");
        System.out.println("Total Customers Processed: " + this.totalCustomersProcessed);
        
        if (this.totalCustomersProcessed > 0) {
            double averagePriority = (double) this.totalPrioritySum / this.totalCustomersProcessed;
            System.out.printf("Average Priority Level: %.2f/5.0%n", averagePriority);
        }
        
        System.out.println("========================================\n");
    }

    /**
     * Gets the total number of customers processed.
     *
     * @return The count of customers processed
     */
    public int getTotalCustomersProcessed() {
        return this.totalCustomersProcessed;
    }

    /**
     * Gets the average priority of processed customers.
     *
     * @return The average priority, or 0 if no customers processed
     */
    public double getAveragePriority() {
        if (this.totalCustomersProcessed == 0) {
            return 0.0;
        }
        return (double) this.totalPrioritySum / this.totalCustomersProcessed;
    }
}
