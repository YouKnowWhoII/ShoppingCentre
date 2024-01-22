package com.westminstershopping.util;

import com.westminstershopping.core.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Provides utility methods for saving and loading data to/from files
public class FileManager {

    // Save products to a file, updating existing products based on productID
    public static void saveProductsToFile(List<Product> productList, String fileName) {
        // Load existing products from the file
        List<Product> savedProducts = loadProductsFromFile(fileName);

        // Iterate through the provided products
        for (Product product : productList) {
            boolean productExists = false;
            // Check if the product with the same productID exists in the saved products
            for (Product savedProduct : savedProducts) {
                if (product.getProductID().equals(savedProduct.getProductID())) {
                    // Update existing product with new details
                    savedProduct.setAvailableItems(product.getAvailableItems());
                    productExists = true;
                    break;
                }
            }
            // If the product does not exist, add it to the saved products
            if (!productExists) {
                savedProducts.add(product);
            }
        }

        // Save the updated products to the file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(savedProducts);
            System.out.println("Products saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    // Load products from a file
    @SuppressWarnings("unchecked")
    public static List<Product> loadProductsFromFile(String fileName) {
        List<Product> productList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            // Read the products from the file
            productList = (List<Product>) inputStream.readObject();
            System.out.println("Products loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading products from file: " + e.getMessage());
        }
        return productList;
    }

    // Save users to a file, adding a new user or updating an existing user based on username
    public static void saveUsersToFile(String username, String fileName, boolean firstTimeUser) {
        // Load existing users from the file
        List<String> savedUsers = loadUsersFromFile(fileName);

        // Add the new username to the list
        String name = username;
        savedUsers.add(name);

        // If it's not the first time user, save the updated users to the file
        if (!firstTimeUser) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
                outputStream.writeObject(savedUsers);
            } catch (IOException e) {
                System.out.println("Error saving users to file: " + e.getMessage());
            }
        }
    }

    // Load users from a file
    @SuppressWarnings("unchecked")
    public static List<String> loadUsersFromFile(String fileName) {
        List<String> userList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            // Read the users from the file
            userList = (List<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
        return userList;
    }

    // Check if a name exists in the list of users saved in the file
    public static boolean checkNameExists(String name, String fileName) {
        // Load existing users from the file
        List<String> savedUsers = loadUsersFromFile(fileName);
        boolean nameExists = false;
        // Check if the name exists in the list
        for (int i = 0; i < savedUsers.size(); i++) {
            if (name.equals(savedUsers.get(i))) {
                nameExists = true;
                break;
            }
        }
        return nameExists;
    }
}
