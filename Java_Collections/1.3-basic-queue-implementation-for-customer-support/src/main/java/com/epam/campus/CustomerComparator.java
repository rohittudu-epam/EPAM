package com.epam.campus;

import java.util.Comparator;

/**
 * Comparator for Customer objects based on priority level.
 * Implements descending order comparison, where higher priority values are sorted first.
 * This comparator is used with PriorityQueue to enable priority-based customer servicing.
 */
public class CustomerComparator implements Comparator<Customer> {
    /**
     * Compares two customers by their priority level in descending order.
     * Higher priority values are considered "less than" lower priority values,
     * resulting in a max-heap behavior in PriorityQueue.
     *
     * @param c1 The first customer to compare
     * @param c2 The second customer to compare
     * @return A negative integer if c1 has higher priority than c2,
     *         a positive integer if c1 has lower priority than c2,
     *         or zero if they have equal priority
     */
    public int compare(Customer c1, Customer c2) {
        return Integer.compare(c2.getPriority(), c1.getPriority());
    }
}
