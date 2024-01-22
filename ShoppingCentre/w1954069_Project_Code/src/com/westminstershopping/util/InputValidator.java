package com.westminstershopping.util;

import com.westminstershopping.core.model.Product;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Provides methods for validating user input
public class InputValidator {
    // Scanner for reading user input
    static Scanner scanner = new Scanner(System.in);

    // Validates and returns a non-empty string input
    public static String stringValidator(String promptMessage) {
        String input;
        while (true) {
            System.out.println(promptMessage);
            input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a valid string.\n");
            } else {
                return input;
            }
        }
    }

    // Validates and returns an integer input
    public static int integerValidator(String promptMessage) {
        int input;
        while (true) {
            try {
                System.out.println(promptMessage);
                input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    // Validates and returns a double input
    public static double doubleValidator(String promptMessage) {
        double input;
        while (true) {
            try {
                System.out.println(promptMessage);
                input = scanner.nextDouble();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid double.");
                scanner.nextLine();
            }
        }
    }

    // Validates and returns a product ID input
    public static String productIDValidator(String promptMessage, List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(promptMessage);
            String input = scanner.nextLine();
            if (input.matches("^p\\d{3}$")) {
                return input;
            } else {
                System.out.println("Invalid product ID format. Please enter a valid product ID (e.g., p1234567).");
            }
            // Also needs to check if product ID already exists
        }
    }

    // Validates and returns a choice input (1 or 2)
    public static int choiceValidator(String promptMessage) {
        int input;
        while (true) {
            try {
                System.out.println(promptMessage);
                input = scanner.nextInt();
                scanner.nextLine();
                if (input == 1 || input == 2) {
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter a valid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    // Validates and returns a choice input (1, 2, or 3)
    public static int choiceValidator3(String promptMessage) {
        int input;
        while (true) {
            try {
                System.out.println(promptMessage);
                input = scanner.nextInt();
                scanner.nextLine();
                if (input == 1 || input == 2 || input == 3) {
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter a valid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    // Validates and returns a size input (s, m, or l)
    public static char sizeValidator(String promptMessage) {
        char input;
        while (true) {
            try {
                System.out.println(promptMessage);
                input = scanner.nextLine().charAt(0);
                if (input == 's' || input == 'm' || input == 'l' || input == 'S' || input == 'M' || input == 'L') {
                    return input;
                } else {
                    System.out.println("Invalid input. Please enter a valid size.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid size.");
                scanner.nextLine();
            }

        }
    }
}
