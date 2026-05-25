package com.epam.campus;

/**
 * Represents a product with a name and price.
 * This class is used in conjunction with ProductComparator to sort products by price
 * within a TreeSet data structure.
 */
public class Product {
    /** The name of the product */
    private String productName;
    
    /** The price of the product in currency units */
    private double price;
    
    /**
     * Constructs a Product with the specified name and price.
     * 
     * @param productName the name of the product
     * @param price the price of the product
     */
    public Product(String productName, double price){
        setProductName(productName);
        setPrice(price);
    }
    
    
    /**
     * Sets the product name.
     * 
     * @param productName the name to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    /**
     * Sets the product price.
     * 
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the product name.
     * 
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Retrieves the product price.
     * 
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the product with name and price.
     * 
     * @return a string in the format "productName: price"
     */
    public String getProductData(){
        return productName + ": " + price;
    }
}
