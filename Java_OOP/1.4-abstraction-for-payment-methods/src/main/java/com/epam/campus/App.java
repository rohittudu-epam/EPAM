package com.epam.campus;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // System.out.println("Hello World!");

        Payment card = new CreditCardPayment();
        Payment paypal = new PayPalPayment();

        card.setAmount(1000);
        card.processPayment();

        paypal.setAmount(2000);
        paypal.processPayment();
    }
}
