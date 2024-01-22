package com.westminstershopping.ui.model;

import com.westminstershopping.core.model.Product;

import javax.swing.*;
import java.awt.*;

// Represents a JButton for adding products to the shopping cart
public class AddToCartButton extends JButton {

    // Reference to the ProductTable, ShoppingCartTable, and CheckoutPanel
    private ProductTable productTable;
    private ShoppingCartTable shoppingCartTable;
    private CheckoutPanel checkoutPanel;

    // Constructor for the AddToCartButton class
    public AddToCartButton(ProductTable productTable, ShoppingCartTable shoppingCartTable, CheckoutPanel checkoutPanel) {
        super("Add to Cart");

        // Set preferred size for the button
        setPreferredSize(new Dimension(100, 50));

        // Assign references to the provided instances
        this.productTable = productTable;
        this.shoppingCartTable = shoppingCartTable;
        this.checkoutPanel = checkoutPanel;

        // Add ActionListener to respond when the button is clicked
        addActionListener(e -> {
            // Get the selected product from the ProductTable
            Product selectedProduct;
            if (productTable.getSelectedProduct().getAvailableItems() == 0) {
                // Display a message if all available stock has been added to the cart
                JOptionPane.showMessageDialog(null, "Sorry, all available stock has been added to the cart");
                return;
            } else {
                selectedProduct = productTable.getSelectedProduct();
                // Decrement the available items of the selected product
                productTable.getSelectedProduct().setAvailableItems(productTable.getSelectedProduct().getAvailableItems() - 1);
            }

            // Update the ShoppingCartTable with the selected product
            shoppingCartTable.updateCartTable(selectedProduct);

            // Update the labels in the CheckoutPanel
            checkoutPanel.updateLabels();
        });
    }
}
