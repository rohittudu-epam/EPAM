package com.epam.campus;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        // Constructing Product objects
        Product p1 = new Product("xa101", "Lenovo X1 Carbon", 1500);
        Product p2 = new Product("ap202", "Apple MacBook Pro", 2000);

        // Displaying product details
        p1.displayProductDetails();
        p2.displayProductDetails();

    }
}
