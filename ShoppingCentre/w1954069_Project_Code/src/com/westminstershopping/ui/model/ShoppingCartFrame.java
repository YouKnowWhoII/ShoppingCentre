package com.westminstershopping.ui.model;

import javax.swing.*;
import java.awt.*;

// Represents the frame for displaying the shopping cart and checkout panel
public class ShoppingCartFrame extends JFrame {

    // String constants for creating blank spaces
    public static String blankSpace = "                         ";
    public static String blankSpace2 = "  ";

    // Reference to the shopping cart table
    private ShoppingCartTable shoppingCartTable;

    // Constructor for the ShoppingCartFrame class
    public ShoppingCartFrame(ShoppingCartTable shoppingCartTable) {
        super("Shopping Cart");
        this.shoppingCartTable = shoppingCartTable;

        // Set frame size and default close operation
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set layout to BorderLayout
        setLayout(new BorderLayout());

        // Create a scroll pane for the shopping cart table
        JScrollPane scrollPane = new JScrollPane(shoppingCartTable);
        add(scrollPane, BorderLayout.NORTH);

        // Create a checkout panel and add it to the center of the frame
        CheckoutPanel checkoutPanel = new CheckoutPanel(shoppingCartTable);
        add(checkoutPanel, BorderLayout.CENTER);

        // Set the frame to be visible
        setVisible(true);
    }

    // Get the instance of the ShoppingCartFrame if it exists
    public static Component getInstance() {
        // Return the instance of the frame if it exists
        for (Window window : JFrame.getWindows()) {
            if (window instanceof ShoppingCartFrame) {
                return window;
            }
        }
        return null;
    }
}
