package com.westminstershopping.ui.model;

import com.westminstershopping.core.model.Clothing;
import com.westminstershopping.core.model.Electronics;
import com.westminstershopping.core.model.Product;
import com.westminstershopping.core.model.ShoppingCart;
import com.westminstershopping.ui.manager.WestminsterShoppingCentre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Represents the table for displaying the shopping cart
public class ShoppingCartTable extends JTable {

    // Array of column names for the table
    private static final String[] columnNames = {"Product", "Quantity", "Price (£)"};

    // Reference to the shopping cart
    private final ShoppingCart shoppingCart;

    // Constructor for the ShoppingCartTable class
    public ShoppingCartTable(ShoppingCart shoppingCart) {
        // Call the constructor of the superclass with a DefaultTableModel
        super(new DefaultTableModel(new Object[][]{}, columnNames));
        this.shoppingCart = shoppingCart;

        // Set column widths, row height, and preferred scrollable viewport size
        setColumnWidths();
        setRowHeight(70);
        setPreferredScrollableViewportSize(new Dimension(500, 250));
        setFillsViewportHeight(true);

        // Set column headers
        getTableHeader().getColumnModel().getColumn(0).setHeaderValue(columnNames[0]);
        getTableHeader().getColumnModel().getColumn(1).setHeaderValue(columnNames[1]);
        getTableHeader().getColumnModel().getColumn(2).setHeaderValue(columnNames[2]);
    }

    // Method to update the shopping cart table with a selected product
    public void updateCartTable(Product selectedProduct) {
        // Get the table model
        DefaultTableModel tableModel = (DefaultTableModel) getModel();

        // Check if the selected product is not null
        if (selectedProduct != null) {
            // Add the selected product to the shopping cart
            shoppingCart.addProduct(selectedProduct);

            // Create an array to store row data
            Object[] rowData = new Object[3];

            // Populate row data based on the selected product's category
            if (selectedProduct instanceof Electronics) {
                rowData[0] = "<html>" + selectedProduct.getProductID() +
                        "<br>" + selectedProduct.getProductName() +
                        "<br>" + ((Electronics) selectedProduct).getBrand() + " | " + ((Electronics) selectedProduct).getWarrantyPeriod() + " months</html>";
            } else if (selectedProduct instanceof Clothing) {
                rowData[0] = "<html>" + selectedProduct.getProductID() +
                        "<br>" + selectedProduct.getProductName() +
                        "<br>" + ((Clothing) selectedProduct).getSize() + " | " + ((Clothing) selectedProduct).getColor() + "</html>";
            }

            // Find the index of an existing row with the same product data
            int existingRowIndex = findExistingRowIndex(rowData[0], tableModel);

            // Check if an existing row is found
            if (existingRowIndex != -1) {
                // Update the quantity and price of the existing row
                int existingQuantity = (int) tableModel.getValueAt(existingRowIndex, 1);
                double existingPrice = (double) tableModel.getValueAt(existingRowIndex, 2);

                rowData[1] = existingQuantity + 1;
                rowData[2] = calculateNewPrice(existingPrice, selectedProduct.getPrice());

                tableModel.setValueAt(rowData[1], existingRowIndex, 1);
                tableModel.setValueAt(rowData[2], existingRowIndex, 2);
            } else {
                // Add a new row for the selected product
                rowData[1] = 1;
                rowData[2] = selectedProduct.getPrice();
                tableModel.addRow(rowData);
            }
        }
    }

    // Method to find the index of an existing row with the same product data
    private int findExistingRowIndex(Object productData, DefaultTableModel tableModel) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(productData)) {
                return i;
            }
        }
        return -1;
    }

    // Method to calculate the new total price given an existing price and product price
    private double calculateNewPrice(double existingPrice, double productPrice) {
        return Math.round((existingPrice + productPrice) * 100.0) / 100.0;
    }

    // Method to set column widths for the table
    private void setColumnWidths() {
        getColumnModel().getColumn(0).setPreferredWidth(300);
        getColumnModel().getColumn(1).setPreferredWidth(100);
        getColumnModel().getColumn(2).setPreferredWidth(100);
    }

    // Method to get the total cost of items in the shopping cart
    public static double getTotal() {
        double total = ShoppingCart.calculateTotalCost();
        return Math.round(total * 100.0) / 100.0;
    }

    // Method to get the category discount for items in the shopping cart
    public static double getCategoryDiscount() {
        double total = ShoppingCart.checkCategoryDiscount();
        // Discount is 20% of the total
        return Math.round(total * 0.2 * 100.0) / 100.0;
    }

    // Method to get the first customer discount for items in the shopping cart
    public static double getFirstCustomerDiscount() {
        double total = ShoppingCart.calculateTotalCost();
        boolean firstCustomer = WestminsterShoppingCentre.returningCustomer;
        // If not a returning customer, apply a 10% discount
        if (!firstCustomer) {
            return Math.round(total * 0.1 * 100.0) / 100.0;
        } else {
            return 0.0;
        }
    }
}
