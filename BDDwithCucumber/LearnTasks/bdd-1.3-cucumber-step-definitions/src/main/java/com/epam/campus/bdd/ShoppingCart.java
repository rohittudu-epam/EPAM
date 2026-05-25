package com.epam.campus.bdd;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulated shopping cart for BDD testing.
 */
public class ShoppingCart {

    private final List<CartItem> items = new ArrayList<>();

    public void addItem(String name, double price) {
        items.add(new CartItem(name, price));
    }

    public boolean removeItem(String name) {
        return items.removeIf(item -> item.getName().equals(name));
    }

    public int getItemCount() {
        return items.size();
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(CartItem::getPrice)
                .sum();
    }

    public void clear() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Represents a single item in the cart.
     */
    public static class CartItem {
        private final String name;
        private final double price;

        public CartItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
}
