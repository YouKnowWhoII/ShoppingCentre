package com.westminstershopping.core.model;

public class Clothing extends Product {

    // Additional attributes for Clothing
    private char size;
    private String color;

    // Constructor for the Clothing class
    public Clothing(String productId, String productName, int availableItems, double price, char size, String color) {
        // Call the constructor of the base class (Product) using the 'super' keyword
        super(productId, productName, availableItems, price);

        // Initialize the additional attributes specific to Clothing
        this.size = size;
        this.color = color;
    }

    // Getter method for retrieving the size of the clothing
    public int getSize() {
        return size;
    }

    // Setter method for setting the size of the clothing
    public void setSize(char size) {
        this.size = size;
    }

    // Getter method for retrieving the color of the clothing
    public String getColor() {
        return color;
    }

    // Setter method for setting the color of the clothing
    public void setColor(String color) {
        this.color = color;
    }
}
