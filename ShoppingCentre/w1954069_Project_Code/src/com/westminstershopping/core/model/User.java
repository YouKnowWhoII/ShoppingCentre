package com.westminstershopping.core.model;


import com.westminstershopping.util.FileManager;
import java.io.Serializable;

// Represents a user in the system
public class User implements Serializable {

    // Static variable to store the username (shared among all instances of User)
    private static String username;

    // Instance variable to store the password
    private String password;

    // Constructor to initialize the User with a username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Static method to retrieve the shared username
    public static String getUsername() {
        return username;
    }

    // Static method to check if a username already exists (uses FileManager)
    public static boolean checkName(String username) {
        return FileManager.checkNameExists(username, "names.txt");
    }

    // Setter method to set the username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method to retrieve the password
    public String getPassword() {
        return password;
    }

    // Setter method to set the password
    public void setPassword(String password) {
        this.password = password;
    }
}
