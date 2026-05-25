package com.epam.campus;

import java.util.PriorityQueue;

/**
 * Interface for customer support system implementations.
 * Defines the contract for processing customer support requests in a priority-based manner.
 */
public interface SupportSystemInterface {
    /**
     * Processes a queue of customers in priority order.
     * Higher priority customers are serviced first.
     *
     * @param customers A PriorityQueue of Customer objects ordered by priority
     */
    public void prioritySupport(PriorityQueue<Customer> customers);
}
