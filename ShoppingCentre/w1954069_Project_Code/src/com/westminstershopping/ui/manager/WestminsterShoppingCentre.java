package com.westminstershopping.ui.manager;

import com.westminstershopping.core.model.User;
import com.westminstershopping.ui.model.WestminsterShoppingUI;
import com.westminstershopping.util.FileManager;
import com.westminstershopping.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

// Represents the main class for the Westminster Shopping Centre
public class WestminsterShoppingCentre {

    // List to store user information
    private List<User> users;

    // Static flag to indicate if the user is returning
    public static boolean returningCustomer;

    // Constructor to initialize the WestminsterShoppingCentre with an empty list of users
    public WestminsterShoppingCentre() {
        this.users = new ArrayList<>();
    }

    // Method to start the Westminster Shopping Centre application
    public void start() {
        System.out.println("Welcome to Westminster Shopping Centre");

        // Get the username from the user using the InputValidator
        String username = InputValidator.stringValidator("Please enter your name: ");

        // Check if the user is a returning customer based on the username
        returningCustomer = User.checkName(username);

        // Save user information to file
        FileManager.saveUsersToFile(username, "names.txt", returningCustomer);

        // Create a new instance of the WestminsterShoppingUI class to start the UI
        new WestminsterShoppingUI();
    }
}
