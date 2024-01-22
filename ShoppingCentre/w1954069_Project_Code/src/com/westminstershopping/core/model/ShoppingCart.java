package com.westminstershopping.core.model;

import java.util.ArrayList;
import java.util.List;

// Represents a shopping cart that can hold a list of products
public class ShoppingCart {

    // List to store the products in the shopping cart
    private static List<Product> productList;

    // Constructor to initialize the shopping cart with an empty list of products
    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }

    // Method to add a product to the shopping cart
    public void addProduct(Product product) {
        productList.add(product);
    }

    // Method to remove a product from the shopping cart
    public void removeProduct(Product product) {
        productList.remove(product);
    }

    // Method to calculate the total cost of all products in the shopping cart
    public static double calculateTotalCost() {
        double total = 0.0;

        // Iterate through the products and sum their prices
        for (Product product : productList) {
            total += product.getPrice();
        }

        // Round to two decimal places
        return Math.round(total * 100.0) / 100.0;
    }

    // Method to check for category-based discount (20% if 3 or more products in the same category)
    public static double checkCategoryDiscount() {
        double total = 0.0;
        int electronicsCount = 0;
        int clothingCount = 0;

        // Count the number of electronics and clothing products in the shopping cart
        for (Product product : productList) {
            if (product instanceof Electronics) {
                electronicsCount++;
            } else if (product instanceof Clothing) {
                clothingCount++;
            }
        }

        // Apply discount if there are 3 or more products in either category
        if (electronicsCount >= 3 || clothingCount >= 3) {
            total = calculateTotalCost();
        } else {
            total = 0.0;
        }

        return total;
    }

    // Getter method to retrieve the list of products in the shopping cart
    public List<Product> getProductList() {
        return productList;
    }
}
