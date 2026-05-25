package com.epam.campus;

/**
 * Represents a customer in the support system.
 * Contains customer information including name, issue description, and priority level.
 */
public class Customer {
    /** The name of the customer */
    String customerName;
    /** The issue reported by the customer */
    String issue;
    /** Priority level of the issue (1-5, where 5 is highest) */
    int priority;

    /**
     * Constructs a Customer object with validation.
     *
     * @param name The name of the customer
     * @param issue The issue description
     * @param priority The priority level (1-5)
     * @throws IllegalArgumentException if any parameter is invalid
     */
    Customer(String name, String issue, int priority){
        this.customerName = customerNameValidation(name);
        this.issue = issueValidation(issue);
        this.priority = priorityValidation(priority);
    }

    /**
     * Gets the customer's name.
     *
     * @return The customer's name
     */
    public String getCustomerName(){
        return this.customerName;
    }

    /**
     * Gets the customer's issue description.
     *
     * @return The issue description
     */
    public String getIssue(){
        return this.issue;
    }

    /**
     * Gets the customer's priority level.
     *
     * @return The priority level (1-5)
     */
    public int getPriority(){
        return this.priority;
    }

    /**
     * Validates the customer name.
     *
     * @param customerName The name to validate
     * @return The validated customer name
     * @throws IllegalArgumentException if name is null or empty
     */
    public String customerNameValidation(String customerName){
        if (customerName == null || customerName.isEmpty()){
            throw new IllegalArgumentException("Name Cannot be null or Empty");
        }

        return customerName;
    }

    /**
     * Validates the issue description.
     *
     * @param issue The issue to validate
     * @return The validated issue
     * @throws IllegalArgumentException if issue is null or empty
     */
    public String issueValidation(String issue){
        if (issue == null || issue.isEmpty()){
            throw new IllegalArgumentException("Issue cannot be null or Empty");
        }

        return issue;
    }

    /**
     * Validates the priority level.
     *
     * @param priority The priority level to validate
     * @return The validated priority level
     * @throws IllegalArgumentException if priority is not between 1 and 5
     */
    public int priorityValidation(int priority){
        if (priority < 1 || priority > 5){
            throw new IllegalArgumentException("Invalid Priority Value");
        }

        return priority;
    }
}
