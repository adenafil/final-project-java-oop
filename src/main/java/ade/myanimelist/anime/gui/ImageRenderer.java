package ade.myanimelist.anime.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        if (value instanceof ImageIcon) {
            ImageIcon original = (ImageIcon) value;
            ImageIcon resizedImg = ResizeImageIcon.setImageIconSize(original, 300, 300);
            setIcon(resizedImg);
            setText(null);  // Clear text when displaying an icon
        } else {
            super.setValue(value);
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // set the bg by odd or even
        if (row % 2 == 0) {
            renderer.setBackground(Color.WHITE);
        } else {
            renderer.setBackground(Color.decode("#f6f6f6"));
        }

        setHorizontalAlignment(SwingConstants.CENTER);

        return renderer;
    }


}
