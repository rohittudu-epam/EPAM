package com.epam.campus;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Main application demonstrating advanced Spliterator usage.
 */
public class App {
    public static void main(String[] args) {
        List<String> products = new ArrayList<>(Arrays.asList(
            "Laptop", "Fridge", "Fan", "Lights", "Table", "Phone", "Dock", "Monitor", "Keyboard"
        ));

        ArrayListSplit spliterator = new ArrayListSplit();

        System.out.println("========================================");
        System.out.println("  SPLITERATOR DEMONSTRATIONS");
        System.out.println("========================================\n");

        System.out.println("1. BASIC SPLITTING:");
        spliterator.splitArrayList(products);

        System.out.println("\n2. MULTI-LEVEL SPLITTING:");
        spliterator.splitArrayListMultipleLevels(products);

        System.out.println("\n3. TRY ADVANCE:");
        spliterator.demonstrateTryAdvance(products);

        System.out.println("\n4. CHARACTERISTICS:");
        spliterator.demonstrateSpliteratorCharacteristics(products);

        // Complex data demonstrations
        List<Product> productList = createProducts();

        System.out.println("\n\n5. COMPLEX PRODUCT PROCESSING:");
        spliterator.processComplexData(productList);

        System.out.println("\n6. PARALLEL-LIKE PROCESSING:");
        spliterator.demonstrateParallelProcessing(productList);

        System.out.println("\n7. PERFORMANCE COMPARISON:");
        spliterator.performanceComparison(productList);

        // Error handling
        System.out.println("\n\n8. ERROR HANDLING:");
        spliterator.splitArrayList(null);

        System.out.println("\n========================================");
        System.out.println("  COMPLETE");
        System.out.println("========================================\n");
    }

    private static List<Product> createProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop Computer", 1200.00, 5, "Electronics"));
        products.add(new Product("Office Chair", 350.00, 8, "Furniture"));
        products.add(new Product("Standing Desk", 750.00, 3, "Furniture"));
        products.add(new Product("USB Hub", 45.99, 25, "Electronics"));
        products.add(new Product("Wireless Mouse", 35.50, 45, "Electronics"));
        products.add(new Product("Desk Lamp", 89.99, 12, "Office Supplies"));
        products.add(new Product("Monitor Stand", 120.00, 6, "Office Supplies"));
        products.add(new Product("Keyboard", 125.00, 15, "Electronics"));
        return products;
    }
}
