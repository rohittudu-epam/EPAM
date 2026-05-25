package com.epam.campus;

public class PayPalPayment extends Payment {

    public PayPalPayment() {
        super("PayPal");
    }

    @Override
    public void processPayment() {
        if ("completed".equals(getStatus())){
            throw new IllegalArgumentException("Payment has alrady been completed");
        }
        setStatus("processing");

        System.out.println("Processing PayPal payment of amount: " + amount);
        setStatus("completed");
    }
}
