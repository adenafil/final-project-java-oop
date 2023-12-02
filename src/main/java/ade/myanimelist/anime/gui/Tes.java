package ade.myanimelist.anime.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.Vector;

public class Tes {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JComboBox in JTable Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Sample data
            Vector<Vector<Object>> data = new Vector<>();
            Vector<Object> row1 = new Vector<>();
            row1.add("Item 1");
            data.add(row1);
            Vector<Object> row2 = new Vector<>();
            row2.add("Item 2");
            data.add(row2);

            // Column names
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Column 1");

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);

            // Set the editor for the cells in column 1
            table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(createComboBox()));

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JComboBox<String> createComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Item 1");
        comboBox.addItem("Item 2");
        comboBox.addItem("Item 3");
        return comboBox;
    }
}
