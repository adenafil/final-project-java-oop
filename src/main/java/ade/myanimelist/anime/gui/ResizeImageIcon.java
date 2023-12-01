package ade.myanimelist.anime.gui;

import javax.swing.*;
import java.awt.*;

public class ResizeImageIcon {
    public static ImageIcon setImageIconSize(ImageIcon img, int width, int height) {
        return new ImageIcon(
                img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)
        );
    }
}
