package com.epam.campus;

import java.util.Objects;

/**
 * Product class representing a product with multiple properties.
 * Used for demonstrating complex Spliterator processing scenarios.
 */
public class Product {
    private final String name;
    private final double price;
    private final int quantity;
    private final String category;

    public Product(String name, double price, int quantity, String category) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Product category cannot be null or empty");
        }
        
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', price=%.2f, quantity=%d, category='%s', value=%.2f}",
                name, price, quantity, category, getTotalValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                Objects.equals(name, product.name) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, category);
    }
}
