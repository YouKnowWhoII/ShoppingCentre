package com.westminstershopping.ui.model;

import com.westminstershopping.core.CategorySelector;

import javax.swing.*;
import java.awt.*;

// Represents a panel for selecting products and displaying a product table
public class ProductSelectorPanel extends JPanel {

    // Constructor for the ProductSelectorPanel class
    ProductSelectorPanel(CategorySelector categorySelector, CartButton cartButton, ProductTable productTable) {
        super(new BorderLayout());  // Set BorderLayout for this panel

        // Add label for selecting a category to the west
        add(new JLabel("Select a category: "), BorderLayout.WEST);

        // Add the CategorySelector to the center
        add(categorySelector, BorderLayout.CENTER);

        // Add the CartButton to the east
        add(cartButton, BorderLayout.EAST);

        // Add a scrollable product table to the south
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.SOUTH);
    }
}
