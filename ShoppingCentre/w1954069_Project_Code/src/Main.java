import com.westminstershopping.core.manager.*;
import com.westminstershopping.ui.manager.WestminsterShoppingCentre;
import com.westminstershopping.ui.model.WestminsterShoppingUI;
import com.westminstershopping.util.InputValidator;

// Main class representing the entry point of the Westminster Shopping application
public class Main {

    // Main method where the program execution begins
    public static void main(String[] args) {
        // Create an instance of WestminsterShoppingManager for managing products
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Load products from file
        shoppingManager.loadProducts();

        // Create an instance of WestminsterShoppingCentre for managing customer interactions
        WestminsterShoppingCentre shoppingCentre = new WestminsterShoppingCentre();

        // Variable to store user's choice
        int choice;

        // Display the main menu and process user's choice until the user chooses to exit
        do {
            System.out.println("Welcome to Westminster Shopping");

            // Get and validate user's choice (Manager, Customer, or Exit)
            choice = InputValidator.choiceValidator3("Are you a\n 1. Manager\n 2. Customer\n 3. Exit\nYour Choice: ");

            // Process user's choice
            switch (choice) {
                case 1:
                    // Start the shopping manager's functionality
                    shoppingManager.start();
                    break;
                case 2:
                    // Check if there are products available before starting customer interactions
                    if (WestminsterShoppingManager.getProductList().isEmpty()) {
                        System.out.println("There are no products in stock. Please ask a manager to add products first\n");
                    } else {
                        // Start customer interactions
                        shoppingCentre.start();
                    }
                    break;
                case 3:
                    // Exit the program
                    System.out.println("Thank you for using Westminster Shopping");
                    break;
            }
        } while (choice != 3);
    }

}
