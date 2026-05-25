package com.epam.campus.model;

/**
 * Represents an order with a unique identifier and cancellation status.
 */
public class Order {
    /**
     * The unique identifier for the order.
     */
    private String orderId;

    /**
     * Indicates whether the order has been cancelled.
     */
    private boolean isCancelled;

    /**
     * Constructs a new Order with the specified order ID and cancellation status.
     *
     * @param orderId     the unique identifier for the order
     * @param isCancelled true if the order is cancelled, false otherwise
     */
    public Order(String orderId, boolean isCancelled) {
        this.orderId = orderId;
        this.isCancelled = isCancelled;
    }

    /**
     * Returns the unique identifier for the order.
     *
     * @return the order ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Returns whether the order has been cancelled.
     *
     * @return true if the order is cancelled, false otherwise
     */
    public boolean isCancelled() {
        return isCancelled;
    }
}