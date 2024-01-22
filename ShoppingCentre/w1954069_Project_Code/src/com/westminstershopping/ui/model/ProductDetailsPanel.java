package com.westminstershopping.ui.model;

import com.westminstershopping.core.model.Clothing;
import com.westminstershopping.core.model.Electronics;
import com.westminstershopping.core.model.Product;

import javax.swing.*;
import java.awt.*;

// Represents a JPanel for displaying details of selected products in the product table
public class ProductDetailsPanel extends JPanel {

    // Labels for displaying product details
    private JLabel productIdLabel;
    private JLabel productNameLabel;
    private JLabel categoryLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private JLabel additionalInfoLabel;

    // String for creating blank space
    public static String blankSpace = "                                                        ";

    // Static panel for adding components
    private static JPanel topPanel = new JPanel();

    // Constructor for the ProductDetailsPanel class
    public ProductDetailsPanel() {
        setLayout(new BorderLayout());
        topPanel.setLayout(new GridLayout(6, 1));
        initializeComponents();
        addComponents();
        JPanel bottomPanel = new JPanel();
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        add(new JLabel(blankSpace), BorderLayout.WEST);
        add(new JLabel(blankSpace), BorderLayout.EAST);
    }

    // Initialize the components (labels)
    private void initializeComponents() {
        productIdLabel = new JLabel("Product ID:");
        productNameLabel = new JLabel("Product Name:");
        categoryLabel = new JLabel("Category:");
        quantityLabel = new JLabel("Quantity:");
        priceLabel = new JLabel("Price:");
        additionalInfoLabel = new JLabel("Additional Info:");
    }

    // Add components to the panel
    private void addComponents() {
        topPanel.add(productIdLabel);
        add(new JLabel()); // Placeholder for Product ID value
        topPanel.add(productNameLabel);
        add(new JLabel()); // Placeholder for Product Name value
        topPanel.add(categoryLabel);
        add(new JLabel()); // Placeholder for Category value
        topPanel.add(quantityLabel);
        add(new JLabel()); // Placeholder for Quantity value
        topPanel.add(priceLabel);
        add(new JLabel()); // Placeholder for Price value
        topPanel.add(additionalInfoLabel);
        add(new JLabel()); // Placeholder for Additional Info value
    }

    // Update the labels with the details of the selected product
    public void updateDetails(Product product) {
        productIdLabel.setText("Product ID: " + product.getProductID());
        productNameLabel.setText("Product Name: " + product.getProductName());

        // Check if the product is an instance of Electronics or Clothing and update category accordingly
        if (product instanceof Electronics) {
            categoryLabel.setText("Category: Electronics");
            additionalInfoLabel.setText("Additional Info: " +
                    ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod() + " months");
        } else if (product instanceof Clothing) {
            categoryLabel.setText("Category: Clothing");
            additionalInfoLabel.setText("Additional Info: " +
                    ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor());
        }

        quantityLabel.setText("Quantity: " + product.getAvailableItems());
        priceLabel.setText("Price: " + product.getPrice());
    }

    // Reset the labels to their initial state
    public void resetDetails() {
        productIdLabel.setText("Product ID:");
        productNameLabel.setText("Product Name:");
        categoryLabel.setText("Category:");
        quantityLabel.setText("Quantity:");
        priceLabel.setText("Price:");
        additionalInfoLabel.setText("Additional Info:");
    }
}
