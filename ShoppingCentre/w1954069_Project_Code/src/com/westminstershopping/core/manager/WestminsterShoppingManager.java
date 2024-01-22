package com.westminstershopping.core.manager;
import com.westminstershopping.core.model.*;
import com.westminstershopping.util.FileManager;
import com.westminstershopping.util.InputValidator;

import java.util.*;

// Implementation of the ShoppingManager interface
public class WestminsterShoppingManager implements ShoppingManager {

    private static final int MaxItemsLimit = 50;
    private static List<Product> productList;

    // Constructor to initialize the product list
    public WestminsterShoppingManager() {
        this.productList = new ArrayList<>();
    }

    // Getter for the product list
    public static List<Product> getProductList() {
        return productList;
    }

    // Main method to start the shopping manager and handle user interactions
    public void start() {
        int choice;
        do {
            // Display menu options
            System.out.println("\n---Westminster Shopping Manager---\n");
            System.out.println("1. Add a product");
            System.out.println("2. Delete a product");
            System.out.println("3. Print list of products");
            System.out.println("4. Save products to file");
            System.out.println("5. Reset system");
            System.out.println("6. Exit\n");
            choice = InputValidator.integerValidator("Enter your choice: ");

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    System.out.println("---Add a product---\n");
                    addProduct();
                    break;
                case 2:
                    System.out.println("---Delete a product---\n");
                    deleteProduct();
                    break;
                case 3:
                    System.out.println("---Print list of products---\n");
                    printProducts();
                    break;
                case 4:
                    saveToFile("products.txt");
                    break;
                case 5:
                    resetSystem();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.\n");
                    break;
            }
        } while (choice != 6);
    }

    // Method to add a product to the shopping manager
    public void addProduct() {
        int totalItems = calculateTotalItems();
        int availableItems = InputValidator.integerValidator("How many items would you like to add? :");

        // Check if adding the items exceeds the maximum limit
        if (totalItems + availableItems > MaxItemsLimit) {
            System.out.println("Cannot add more than " + MaxItemsLimit + " items.\n");
            return;
        }

        int typeChoice = InputValidator.choiceValidator("Choose product type to add\n1. Electronics\n2. Clothing\nYour choice:");

        String productID;

        productID = InputValidator.productIDValidator("Enter product ID [format p123] :", productList);

        // Check if the product already exists and add more items to it
        for (Product product : productList) {
            if (product.getProductID().equals(productID)) {
                addExistingProduct(availableItems, productID);
                return;
            }
        }

        String productName = InputValidator.stringValidator("Enter product name:");
        double price = InputValidator.doubleValidator("Enter price:");

        // Create and add a new Electronics product
        if (typeChoice == 1) {
            String brand = InputValidator.stringValidator("Enter brand:");
            int warrantyPeriod = InputValidator.integerValidator("Enter warranty period (in months):");
            Electronics electronics = new Electronics(productID, productName, availableItems, price, brand, warrantyPeriod);
            productList.add(electronics);
            System.out.println("Electronics product added successfully.\n");
        }

        // Create and add a new Clothing product
        else if (typeChoice == 2) {
            char size = InputValidator.sizeValidator("Enter size:");
            String color = InputValidator.stringValidator("Enter color:");
            Clothing clothing = new Clothing(productID, productName, availableItems, price, size, color);
            productList.add(clothing);
            System.out.println("Clothing product added successfully.\n");
        }
    }

    // Method to delete a product from the shopping manager
    public void deleteProduct() {
        String productID = InputValidator.productIDValidator("Enter product ID of product to delete:", productList);
        int quantityToRemove = InputValidator.integerValidator("Enter amount to decrease:");

        // Find the product by ID and decrement the available items
        for (Product product : productList) {
            if (product.getProductID().equals(productID)) {
                product.decrementAvailableItems(quantityToRemove);
                return;
            }
        }
        System.out.println("Product not found.\n");
    }

    // Method to print/display the list of products in the shopping manager
    public void printProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.\n");
            return;
        }

        System.out.println("\n===== List of Products =====\n");

        // Print each product's information
        productList.stream()
                .sorted(Comparator.comparing(Product::getProductID))
                .forEach(this::printProductInfo);
    }

    // Method to save the current state of the shopping manager to a file
    public void saveToFile(String fileName) {
        FileManager.saveProductsToFile(productList, fileName);
    }

    // Method to reset the shopping manager system
    public void resetSystem() {
        int response = InputValidator.choiceValidator("Are you sure you want to reset the system? Once the products are cleared, they cannot be backed up\n1. Yes\n2. No:");
        if (response == 1) {
            productList.clear();
            System.out.println("System reset successfully.\n");
        } else {
            System.out.println("System reset cancelled.\n");
        }
    }

    // Helper method to calculate the total number of items in the shopping manager
    private int calculateTotalItems() {
        int totalItems = 0;
        for (Product product : productList) {
            totalItems += product.getAvailableItems();
        }
        return totalItems;
    }

    // Helper method to add items to an existing product
    public void addExistingProduct(int quantityToAdd, String productID) {
        int totalItems = calculateTotalItems();

        // Check if adding the items exceeds the maximum limit
        if (totalItems + quantityToAdd > MaxItemsLimit) {
            System.out.println("Cannot add more than " + MaxItemsLimit + " items.\n");
            return;
        }

        // Find the product by ID and increment the available items
        for (Product product : productList) {
            if (product.getProductID().equals(productID)) {
                product.incrementAvailableItems(quantityToAdd);
                return;
            }
        }
        System.out.println("Product not found.\n");
    }

    // Helper method to print detailed information about a product
    private void printProductInfo(Product product) {
        System.out.println("============================\n");
        System.out.println("Product ID: " + product.getProductID());
        System.out.println("Product Name: " + product.getProductName());

        // Print additional information based on the product type
        if (product instanceof Electronics) {
            System.out.println("Type: Electronics");
            System.out.println("Brand: " + ((Electronics) product).getBrand());
            System.out.println("Warranty Period: " + ((Electronics) product).getWarrantyPeriod() + " months");
        } else if (product instanceof Clothing) {
            System.out.println("Type: Clothing");
            System.out.println("Size: " + ((Clothing) product).getSize());
            System.out.println("Color: " + ((Clothing) product).getColor());
        }

        System.out.println("Available Items: " + product.getAvailableItems());
        System.out.println("Price: $" + product.getPrice());
    }

    // Method to load products from a file
    public void loadProducts() {
        productList = FileManager.loadProductsFromFile("products.txt");
    }
}
