package com.westminstershopping.ui.model;

import com.westminstershopping.core.manager.WestminsterShoppingManager;
import com.westminstershopping.core.model.Clothing;
import com.westminstershopping.core.model.Electronics;
import com.westminstershopping.core.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

// Represents a table for displaying products
public class ProductTable extends JTable {

    // Array of column names
    public static String[] columnNames = {"ProductID", "ProductName", "Category", "Quantity", "Price (£)", "Info"};

    // List of products retrieved from the manager
    public static List<Product> productList = WestminsterShoppingManager.getProductList();

    // Reference to the ProductDetailsPanel
    private ProductDetailsPanel detailsPanel;

    // Constructor for the ProductTable class
    public ProductTable(ProductDetailsPanel productDetailsPanel) {
        super();
        this.detailsPanel = productDetailsPanel;

        // Sort the productList alphabetically by product ID
        List<Product> sortedProductList = sortProductList(productList);

        // Create a 2D array of objects to store the data
        Object[][] data = new Object[productList.size()][6];

        // Loop through the product list and add the data to the 2D array
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            data[i][0] = product.getProductID();
            data[i][1] = product.getProductName();

            if (product instanceof Electronics) {
                data[i][2] = "Electronics";
                data[i][5] = ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod() + " months";
            } else if (product instanceof Clothing) {
                data[i][2] = "Clothing";
                data[i][5] = ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor();
            }

            data[i][3] = product.getAvailableItems();
            data[i][4] = product.getPrice();
        }

        // Create a new table model with the data and column names
        TableModel tableModel = new DefaultTableModel(data, columnNames);

        // Set the table model to this table
        setModel(tableModel);

        // Set the column widths
        setColumnWidths();

        // Set up selection listener to update details panel when a row is selected
        getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = getSelectedRow();
            if (selectedRow != -1) {
                Product selectedProduct = productList.get(selectedRow);
                detailsPanel.updateDetails(selectedProduct);
            }
        });

        // Limit table size to display 5 rows, make the rest scrollable
        setPreferredScrollableViewportSize(new Dimension(500, 100));
        setFillsViewportHeight(true);

        // Set table header with tooltips
        setTableHeader(new JTableHeader(getColumnModel()) {
            @Override
            public String getToolTipText() {
                return String.join(", ", columnNames);
            }
        });
    }

    // Set the column widths
    public void setColumnWidths() {
        getColumnModel().getColumn(0).setPreferredWidth(100);
        getColumnModel().getColumn(1).setPreferredWidth(200);
        getColumnModel().getColumn(2).setPreferredWidth(200);
        getColumnModel().getColumn(3).setPreferredWidth(100);
        getColumnModel().getColumn(4).setPreferredWidth(100);
        getColumnModel().getColumn(5).setPreferredWidth(300);
    }

    // Highlight rows with low quantity in red
    public void highlightLowQuantity() {
        for (int i = 0; i < getRowCount(); i++) {
            if ((int) getValueAt(i, 3) < 3) {
                for (int j = 0; j < getColumnCount(); j++) {
                    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                    renderer.setBackground(Color.RED);
                    renderer.setForeground(Color.WHITE);
                    renderer.setHorizontalAlignment(JLabel.CENTER);
                    getColumnModel().getColumn(j).setCellRenderer(renderer);
                }
            }
        }
    }

    // Update the product table based on the selected category
    public void updateProductTable(String category) {
        // Create a 2D array of objects to store the data
        Object[][] data = new Object[productList.size()][6];

        // Loop through the product list and add the data to the 2D array
        int j = 0;
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);

            if (category.equals("Clothing") && product instanceof Clothing) {
                data[j][0] = product.getProductID();
                data[j][1] = product.getProductName();
                data[j][2] = "Clothing";
                data[j][3] = product.getAvailableItems();
                data[j][4] = product.getPrice();
                data[j][5] = ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor();
                j++;
            } else if (category.equals("Electronics") && product instanceof Electronics) {
                data[j][0] = product.getProductID();
                data[j][1] = product.getProductName();
                data[j][2] = "Electronics";
                data[j][3] = product.getAvailableItems();
                data[j][4] = product.getPrice();
                data[j][5] = ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod() + " months";
                j++;
            } else if (category.equals("Both")) {
                data[j][0] = product.getProductID();
                data[j][1] = product.getProductName();

                if (product instanceof Electronics) {
                    data[j][2] = "Electronics";
                    data[j][5] = ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod() + " months";
                } else if (product instanceof Clothing) {
                    data[j][2] = "Clothing";
                    data[j][5] = ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor();
                }

                data[j][3] = product.getAvailableItems();
                data[j][4] = product.getPrice();
                j++;
            }
        }

        // Create a new table model with the data and column names
        TableModel tableModel = new DefaultTableModel(data, columnNames);

        // Set the table model to this table
        setModel(tableModel);

        // Set the column widths
        setColumnWidths();

        // Delete blank rows
        for (int i = 0; i < getRowCount(); i++) {
            if (getValueAt(i, 0) == null) {
                ((DefaultTableModel) getModel()).removeRow(i);
            }
        }
    }

    // Get the selected product from the table
    public Product getSelectedProduct() {
        int selectedRow = getSelectedRow();
        if (selectedRow != -1) {
            // Assuming the product list is still up-to-date
            return productList.get(selectedRow);
        } else {
            return null;
        }
    }

    // Sort the product list based on product ID
    public static List<Product> sortProductList(List<Product> productList) {
        productList.sort((o1, o2) -> o1.getProductID().compareTo(o2.getProductID()));
        return productList;
    }
}
