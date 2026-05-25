package com.epam.campus.service;

import com.epam.campus.model.Order;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for processing orders.
 */
public class OrderProcessor {

    /**
     * Processes a list of orders and returns a sorted list of order IDs for cancelled orders.
     *
     * This method filters the provided list of {@link Order} objects to include only those that are cancelled,
     * extracts their order IDs, sorts them in natural order, and returns the resulting list.
     *
     * @param orders the list of orders to process
     * @return a sorted list of order IDs for cancelled orders
     */
    public List<String> processOrders(List<Order> orders) {
        if (orders == null) {
            throw new IllegalArgumentException("Order list cannot be null");
        }
        return orders.stream()
                .filter(Order::isCancelled)
                .map(Order::getOrderId)
                .sorted()
                .collect(Collectors.toList());
    }
}