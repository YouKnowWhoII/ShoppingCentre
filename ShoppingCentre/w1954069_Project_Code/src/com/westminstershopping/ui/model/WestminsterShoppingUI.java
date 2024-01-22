package com.westminstershopping.ui.model;

import com.westminstershopping.core.CategorySelector;
import com.westminstershopping.core.model.ShoppingCart;

import javax.swing.*;
import java.awt.*;

// Represents the main user interface for Westminster Shopping Centre
public class WestminsterShoppingUI extends JFrame {

    // Reference to the product details panel
    public ProductDetailsPanel productDetailsPanel;

    // Constructor for the WestminsterShoppingUI class
    public WestminsterShoppingUI() {
        // Call the constructor of the superclass with the title
        super("Westminster Shopping Centre");

        // Set the size and default close operation for the frame
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout manager for the frame
        setLayout(new BorderLayout());

        // Create an instance of ProductDetailsPanel
        this.productDetailsPanel = new ProductDetailsPanel();

        // Create instances of ProductTable, ShoppingCart, ShoppingCartTable, CheckoutPanel,
        // CategorySelector, CartButton, and AddToCartButton
        ProductTable productTable = new ProductTable(productDetailsPanel);
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartTable shoppingCartTable = new ShoppingCartTable(shoppingCart);
        CheckoutPanel checkoutPanel = new CheckoutPanel(shoppingCartTable);
        CategorySelector categorySelector = new CategorySelector(productTable);
        CartButton cartButton = new CartButton(shoppingCartTable);

        // Add components to the frame using BorderLayout
        add(new ProductSelectorPanel(categorySelector, cartButton, productTable), BorderLayout.NORTH);
        add(new JSeparator(), BorderLayout.CENTER);
        add(productDetailsPanel, BorderLayout.CENTER);
        add(new AddToCartButton(productTable, shoppingCartTable, checkoutPanel), BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);
    }

}
