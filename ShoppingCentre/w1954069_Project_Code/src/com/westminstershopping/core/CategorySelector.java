package com.westminstershopping.core;

import com.westminstershopping.ui.model.ProductTable;

import javax.swing.*;
import java.awt.*;

// Represents a JComboBox for selecting product categories
public class CategorySelector extends JComboBox {

    // Constructor for the CategorySelector class
    public CategorySelector(ProductTable productTable) {
        super();

        // Set maximum size to make the size not changeable
        setMaximumSize(new Dimension(100, 30));

        // Add items to the JComboBox
        addItem("Both");
        addItem("Electronics");
        addItem("Clothing");

        // Add ActionListener to respond when a category is selected
        addActionListener(e -> {
            String category = (String) getSelectedItem();
            if (category.equals("Clothing")) {
                // Update the product table to show products in the "Clothing" category
                productTable.updateProductTable("Clothing");
            } else if (category.equals("Electronics")) {
                // Update the product table to show products in the "Electronics" category
                productTable.updateProductTable("Electronics");
            } else {
                // Update the product table to show products in both categories
                productTable.updateProductTable("Both");
            }
        });

    }
}
