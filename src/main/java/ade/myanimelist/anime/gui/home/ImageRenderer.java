package ade.myanimelist.anime.gui.home;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        if (value instanceof ImageIcon) {
            ImageIcon original = (ImageIcon) value;
            ImageIcon resizedImg = ResizeImageIcon.setImageIconSize(original, 200, 200);
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

    public static ImageIcon createImageIconByURL(String imgaeURL) throws IOException {
        Image image = ImageIO.read(new URL(imgaeURL));
        if (image != null) return new ImageIcon(image);
        return null;
    }

    public ImageIcon createImageIconByURLNonStatic(String imgaeURL) throws IOException {
        Image image = ImageIO.read(new URL(imgaeURL));
        if (image != null) return new ImageIcon(image);
        return null;
    }





}
