package com.westminstershopping.core.model;

public class Electronics extends Product {

    // Additional attributes for Electronics
    private String brand;
    private int warrantyPeriod;

    // Constructor for the Electronics class
    public Electronics(String productId, String productName, int availableItems, double price, String brand, int warrantyPeriod) {
        // Call the constructor of the base class (Product) using the 'super' keyword
        super(productId, productName, availableItems, price);

        // Initialize the additional attributes specific to Electronics
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Getter method for retrieving the brand of the electronics
    public String getBrand() {
        return brand;
    }

    // Setter method for setting the brand of the electronics
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter method for retrieving the warranty period of the electronics
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    // Setter method for setting the warranty period of the electronics
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
