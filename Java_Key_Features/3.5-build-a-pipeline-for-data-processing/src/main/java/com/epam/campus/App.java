package com.epam.campus;

import com.epam.campus.model.Order;
import com.epam.campus.service.OrderProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * Entry point for the application.
 */
public class App {

    /**
     * The main method that serves as the entry point for the application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        // Create a list of sample orders
        List<Order> orders = Arrays.asList(
                new Order("A123", false),
                new Order("B456", true),
                new Order("C789", true),
                new Order("D012", false)
        );

        // Process orders to get sorted list of cancelled order IDs
        OrderProcessor processor = new OrderProcessor();
        List<String> cancelledOrderIds = processor.processOrders(orders);

        // Print the result
        System.out.println("Cancelled Order IDs: " + cancelledOrderIds);
    }
}