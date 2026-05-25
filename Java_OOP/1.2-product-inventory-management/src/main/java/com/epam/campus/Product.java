package com.epam.campus;

public class Product {

    // Attributes of Product Class
    private String productId;
    private String productName;
    private int price;

    // Constructor for Product class
    Product(String productId, String productName, int price) {
        setProductId(productId);
        setProductName(productName);
        setPrice(price);
    }

    // Setters with basic validation
    public void setProductId(String productId){
        // Validation check for null values
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        this.productId = productId;
    }

    public void setProductName(String productName){
        // Validation check for null or blank values
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product Name cannot be null or blank");
        }
        this.productName = productName;
    }

    public void setPrice(int price){
        // Validation for price to be non-negative
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    // Getters
    public String getProductId(){
        return productId;
    }

    public String getProductName(){
        return productName;
    }

    public int getPrice(){
        return price;
    }

    // Display Product Details Method
    void displayProductDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Price: $" + price);
    }
    
}
