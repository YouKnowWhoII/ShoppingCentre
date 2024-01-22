package com.westminstershopping.ui.model;

import javax.swing.*;
import java.awt.*;

// Represents a JPanel displaying checkout information
public class CheckoutPanel extends JPanel {

    // Labels for displaying checkout information
    private JLabel totalLabel;
    private static JLabel total;
    private JLabel categoryDiscountLabel;
    private static JLabel categoryDiscount;
    private JLabel firstCustomerDiscountLabel;
    private static JLabel firstCustomerDiscount;
    private JLabel finalTotalLabel;
    private static JLabel finalTotal;

    // Reference to the ShoppingCartTable
    private ShoppingCartTable shoppingCartTable;

    // Constructor for the CheckoutPanel class
    public CheckoutPanel(ShoppingCartTable shoppingCartTable) {
        super();
        this.shoppingCartTable = shoppingCartTable;
        setLayout(new GridLayout(4, 2));
        initializeLabels();
        updateLabels();

        // Add labels to the panel
        add(totalLabel);
        add(total);
        add(categoryDiscountLabel);
        add(categoryDiscount);
        add(firstCustomerDiscountLabel);
        add(firstCustomerDiscount);
        add(finalTotalLabel);
        add(finalTotal);
    }

    // Initialize the labels used in the panel
    private void initializeLabels() {
        totalLabel = new JLabel("Total: ");
        total = new JLabel();
        categoryDiscountLabel = new JLabel("Three Items in the same Category Discount (20%): ");
        categoryDiscount = new JLabel();
        firstCustomerDiscountLabel = new JLabel("First Customer Discount (10%): ");
        firstCustomerDiscount = new JLabel();
        finalTotalLabel = new JLabel("Final Total: ");
        finalTotal = new JLabel();
        Font font = new Font("Arial", Font.BOLD, 20);
        finalTotal.setFont(font);
    }

    // Update the labels based on the shopping cart information
    public void updateLabels() {
        double totalPrice = ShoppingCartTable.getTotal();
        double categoryDiscountValue = ShoppingCartTable.getCategoryDiscount();
        double firstCustomerDiscountValue = ShoppingCartTable.getFirstCustomerDiscount();

        // Set text for the labels
        total.setText("£" + totalPrice);
        categoryDiscount.setText("- £" + categoryDiscountValue);
        firstCustomerDiscount.setText("- £" + firstCustomerDiscountValue);
        finalTotal.setText("£" + calculateFinalTotal(totalPrice));
    }

    // Calculate the final total taking into account discounts
    private double calculateFinalTotal(double totalPrice) {
        return Math.round((totalPrice - ShoppingCartTable.getCategoryDiscount() - ShoppingCartTable.getFirstCustomerDiscount()) * 100.0) / 100.0;
    }
}
