package com.epam.campus.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    @Test
    public void testOrderCreation() {
        Order order = new Order("123", false);
        assertEquals("123", order.getOrderId());
        assertEquals(false, order.isCancelled());
    }

    @Test
    public void testCancelledOrder() {
        Order order = new Order("456", true);
        assertEquals(true, order.isCancelled());
    }
}