package ade.myanimelist.anime.gui;

import javax.swing.*;
import java.awt.*;

public class AnimeListSwing extends JFrame {

    public AnimeListSwing() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.setResizable(false);
        LeftPanel tes = new LeftPanel();
        this.add(tes.getPanelLeft(this));
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AnimeListSwing();
        });
    }

}
