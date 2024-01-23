package ade.animelist.components.utilcomponent;

import ade.animelist.database.repository.AddAnimeToDbRepository;
import ade.animelist.database.repository.AddAnimeToDbRepositoryImpl;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class untuk image rendering
 */
public class ImageRenderer  {
    private static final Map<String, ImageIcon> imageCache = new ConcurrentHashMap<>();

    /**
     * Method untuk membuat ImageIcon By URL
     * @param imageURL => link
     * @return => ImageIcon Anime
     */
    public static ImageIcon createImageIconByURL(String imageURL) {
        // Check if the image is already in the cache
        ImageIcon cachedImage = imageCache.get(imageURL);
        if (cachedImage != null) {
            return cachedImage;
        }

        try {
            // Fetch and create the ImageIcon
            Image image = ImageIO.read(new URL(imageURL));
            if (image != null) {
                ImageIcon newImageIcon = new ImageIcon(image);

                // Cache the image for future use
                imageCache.put(imageURL, newImageIcon);

                return newImageIcon;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method untuk mengresize ImageIcon
     * @param img => imageIcon
     * @param width => width
     * @param height => height
     * @return -> ImageIcon
     */
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
