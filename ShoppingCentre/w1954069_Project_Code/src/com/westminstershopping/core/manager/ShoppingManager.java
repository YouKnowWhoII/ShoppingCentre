package com.westminstershopping.core.manager;

// Interface defining the contract for managing shopping-related operations
public interface ShoppingManager {

    // Method to start the shopping manager
    void start();

    // Method to add a product to the shopping manager
    void addProduct();

    // Method to delete a product from the shopping manager
    void deleteProduct();

    // Method to print/display the list of products in the shopping manager
    void printProducts();

    // Method to save the current state of the shopping manager to a file
    // @param fileName: the name of the file to save the data to
    void saveToFile(String fileName);
}


