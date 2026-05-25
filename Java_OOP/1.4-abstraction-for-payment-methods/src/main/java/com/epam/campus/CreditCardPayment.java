package com.epam.campus;

public class CreditCardPayment extends Payment {

    public CreditCardPayment() {
        super("Credit Card");
    }

    @Override
    public void processPayment() {
        if ("completed".equals(status)){
            throw new IllegalArgumentException("Payment has already been completed");
        }

        setStatus("processing");
        System.out.println("Processing Credit Card payment of amount: " + amount);
        setStatus("completed");
    }
}
