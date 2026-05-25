package com.epam.campus.service;

import com.epam.campus.model.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderProcessorTest {
    @Test
    public void testProcessOrdersNormalCase() {
        List<Order> orders = Arrays.asList(
            new Order("123", false),
            new Order("456", true),
            new Order("789", false)
        );
        OrderProcessor processor = new OrderProcessor();
        List<String> result = processor.processOrders(orders);
        assertEquals(Arrays.asList("123", "789"), result);
    }

    @Test
    public void testProcessOrdersWithAllCancelled() {
        List<Order> orders = Arrays.asList(
            new Order("123", true),
            new Order("456", true)
        );
        OrderProcessor processor = new OrderProcessor();
        List<String> result = processor.processOrders(orders);
        assertEquals(Arrays.asList(), result);
    }

    @Test
    public void testProcessOrdersWithEmptyList() {
        List<Order> orders = Arrays.asList();
        OrderProcessor processor = new OrderProcessor();
        List<String> result = processor.processOrders(orders);
        assertEquals(Arrays.asList(), result);
    }

    @Test
    public void testProcessOrdersBoundaryCondition() {
        List<Order> orders = Arrays.asList(
            new Order("123", false)
        );
        OrderProcessor processor = new OrderProcessor();
        List<String> result = processor.processOrders(orders);
        assertEquals(Arrays.asList("123"), result);
    }

    @Test
    public void testProcessOrdersInvalidInput() {
        List<Order> orders = null;
        OrderProcessor processor = new OrderProcessor();
        List<String> result = processor.processOrders(orders);
        assertEquals(null, result);
    }
}