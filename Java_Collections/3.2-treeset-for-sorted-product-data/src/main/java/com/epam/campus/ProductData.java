package com.epam.campus;

import java.util.TreeSet;

/**
 * Manages a collection of products using TreeSet for automatic sorting by price.
 * Provides methods to insert products with validation and list them in sorted order.
 * All products are automatically sorted in ascending order by price.
 */
public class ProductData {
    /** TreeSet that stores products sorted by price using ProductComparator */
    TreeSet<Product> products;

    /**
     * Constructs a ProductData object and initializes the TreeSet with ProductComparator.
     */
    public ProductData(){
        // Initialize TreeSet with custom comparator for price-based sorting
        products = new TreeSet<>(new ProductComparator());
    }

    /**
     * Inserts a product into the collection with validation.
     * 
     * @param name the name of the product
     * @param price the price of the product (must be positive)
     * @throws IllegalArgumentException if price is not positive or name is null/empty
     */
    public void insertProduct(String name, double price){
        // Validate price is positive
        if (price <= 0){
            throw new IllegalArgumentException("Invalid Price for Product.");
        }

        // Validate product name is not null or empty
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        // Add product to TreeSet - automatically sorted by price
        products.add(new Product(name, price));
    }

    /**
     * Displays all products in sorted order (by price, ascending).
     * Each product is printed with its name and price.
     */
    public void listProducts(){
        // Iterate through TreeSet - elements are already sorted by price
        for (Product p: products){
            System.out.println(p.getProductData());
        }
    }
}
