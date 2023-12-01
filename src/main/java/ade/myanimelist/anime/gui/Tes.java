package ade.myanimelist.anime.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Tes {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Immutable Table Example");

            // Sample data for the table
            Object[][] data = {
                    {"John Doe", 25, "Male"},
                    {"Jane Smith", 30, "Female"},
                    {"Bob Johnson", 22, "Male"}
            };

            // Column names
            String[] columnNames = {"Name", "Age", "Gender"};

            // Create DefaultTableModel with sample data
            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Make all cells non-editable
                    return false;
                }
            };

            // Create JTable with the custom table model
            JTable table = new JTable(model);

            // Add JTable to a JScrollPane for scrolling
            JScrollPane scrollPane = new JScrollPane(table);

            // Set up the frame
            frame.setLayout(new BorderLayout());
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
