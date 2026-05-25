package com.epam.campus;

import java.util.Comparator;

/**
 * Comparator for sorting Product objects by their price in ascending order.
 * This comparator is used by TreeSet to maintain products in sorted order.
 * Products with lower prices appear first in the sorted collection.
 */
public class ProductComparator implements Comparator<Product>{
    /**
     * Compares two products by their price.
     * 
     * @param p1 the first product to compare
     * @param p2 the second product to compare
     * @return a negative integer if p1's price is less than p2's,
     *         zero if prices are equal,
     *         or a positive integer if p1's price is greater than p2's
     */
    public int compare(Product p1, Product p2){
        // Uses Double.compare for proper numerical comparison
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}
