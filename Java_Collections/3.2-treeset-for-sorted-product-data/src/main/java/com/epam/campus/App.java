package com.epam.campus;

/**
 * Demonstrates the usage of TreeSet for managing and sorting Product data by price.
 * TreeSet automatically maintains elements in sorted order using the ProductComparator.
 * This example shows basic sorting, bulk insertion, and error handling.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=== TreeSet for Sorted Product Data ===\n");
        
        // Example 1: Demonstrates basic TreeSet functionality with three products
        // Products are automatically sorted by price in ascending order
        System.out.println("Example 1: Basic Product Sorting");
        System.out.println("---------------------------------");
        ProductData productData = new ProductData();
        productData.insertProduct("Laptop", 999.99);
        productData.insertProduct("Mouse", 25.50);
        productData.insertProduct("Keyboard", 79.99);
        System.out.println("Products sorted by price (ascending):");
        productData.listProducts();
        System.out.println();
        
        // Example 2: Demonstrates insertion of multiple products in random order
        // TreeSet uses ProductComparator to automatically sort them by price
        System.out.println("Example 2: Multiple Products");
        System.out.println("----------------------------");
        ProductData store = new ProductData();
        String[][] products = {
            {"Monitor", "299.99"},
            {"USB Cable", "5.99"},
            {"Headphones", "149.99"},
            {"Desk Lamp", "35.00"},
            {"Webcam", "89.99"}
        };
        for (String[] product : products) {
            store.insertProduct(product[0], Double.parseDouble(product[1]));
        }
        System.out.println("Products automatically sorted by price:");
        store.listProducts();
        System.out.println();
        
        // Example 3: Demonstrates validation and error handling
        // Shows how ProductData prevents insertion of invalid products
        System.out.println("Example 3: Error Handling");
        System.out.println("-------------------------");
        ProductData errorTest = new ProductData();
        
        try {
            errorTest.insertProduct("Valid Product", 49.99);
            System.out.println("✓ Valid product added successfully");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        try {
            errorTest.insertProduct("Invalid Price", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        try {
            errorTest.insertProduct("Negative Price", -10.00);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        try {
            errorTest.insertProduct(null, 50.00);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        try {
            errorTest.insertProduct("", 50.00);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
        
        System.out.println("\nFinal product list:");
        errorTest.listProducts();
    }
}
