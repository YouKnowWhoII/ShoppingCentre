package com.westminstershopping.ui.model;

import javax.swing.*;
import java.awt.*;

// Represents a JButton for interacting with the shopping cart
public class CartButton extends JButton {

    // Constructor for the CartButton class (default)
    // When clicked, this button opens the ShoppingCartFrame
    public CartButton() {
        super("Cart");
        setPreferredSize(new Dimension(100, 50));
    }

    // Constructor for the CartButton class with a reference to ShoppingCartTable
    public CartButton(ShoppingCartTable shoppingCartTable) {
        super("Cart");
        setPreferredSize(new Dimension(100, 50));

        // Open the ShoppingCartFrame when the button is clicked
        addActionListener(e -> {
            if (ShoppingCartFrame.getInstance() == null) {
                // If no instance exists, create a new ShoppingCartFrame
                ShoppingCartFrame shoppingCartFrame = new ShoppingCartFrame(shoppingCartTable);
                shoppingCartFrame.setVisible(true);
            } else {
                // If an instance exists, make the existing frame visible
                ShoppingCartFrame.getInstance().setVisible(true);
            }
        });
    }
}
