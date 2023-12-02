package ade.myanimelist.anime.gui;

import javax.swing.*;
import java.awt.*;

public class ResizeImageIcon {
    public static ImageIcon setImageIconSize(ImageIcon img, int width, int height) {
        Image image = img.getImage();
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);

        double aspectRatio = (double) originalWidth / originalHeight;

        int newWidth = width;
        int newHeight = (int) (width / aspectRatio);

        // Ensure the calculated height is not greater than the specified height

        if (newHeight > height) {
            newHeight = height;
            newWidth = (int) (height * aspectRatio);
        }

        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        return new ImageIcon(resizedImage);
    }
}
