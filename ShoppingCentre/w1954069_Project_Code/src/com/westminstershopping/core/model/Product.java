package com.westminstershopping.core.model;

// Import the Serializable interface for serialization support
import java.io.Serializable;

// Abstract class representing a generic product
public abstract class Product implements Serializable {

    // Common attributes for all products
    private String productID;
    private String productName;
    private int availableItems;
    private double price;
    private String info;

    // Constructor for initializing common attributes of a product
    public Product(String productID, String productName, int availableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }

    // Getter method for retrieving the product ID
    public String getProductID() {
        return productID;
    }

    // Setter method for setting the product ID
    public void setProductID(String productID) {
        this.productID = productID;
    }

    // Getter method for retrieving the product name
    public String getProductName() {
        return productName;
    }

    // Setter method for setting the product name
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter method for retrieving the available quantity of the product
    public int getAvailableItems() {
        return availableItems;
    }

    // Setter method for setting the available quantity of the product
    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    // Getter method for retrieving the price of the product
    public double getPrice() {
        return price;
    }

    // Setter method for setting the price of the product
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter method for retrieving additional information about the product
    public String getInfo() {
        return info;
    }

    // Setter method for setting additional information about the product
    public void setInfo(String info) {
        this.info = info;
    }

    // Method to increment the available quantity of the product
    public void incrementAvailableItems(int quantity) {
        this.availableItems += quantity;
        System.out.println("Added " + quantity + " items. Available: " + availableItems);
    }

    // Method to decrement the available quantity of the product
    public void decrementAvailableItems(int quantity) {
        if (quantity <= availableItems) {
            availableItems -= quantity;
            System.out.println("Removed " + quantity + " items. Available: " + availableItems);
        } else {
            System.out.println("Cannot remove. Insufficient stock.");
        }
    }

    // Override the toString method to provide a simple representation of the object
    @Override
    public String toString() {
        return "This is the products class";
    }
}
