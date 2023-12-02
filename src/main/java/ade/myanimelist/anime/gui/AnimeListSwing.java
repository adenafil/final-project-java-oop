package ade.myanimelist.anime.gui;

import javax.swing.*;
import java.awt.*;

public class AnimeListSwing extends JFrame {

    public AnimeListSwing() {
//        String[] filterList = {
//                "ALL ANIME", "CURRENTLY WATCHING", "COMPLETED",
//                "DROPPED", "PLAN TO WATCH"
//        };
//        JComboBox filter = new JComboBox(filterList);
//
//        Button selectFilter = new Button("Select Filter");
//        selectFilter.setBounds(420, 20, 100, 100);
//
//        JPanel filterPanel = new JPanel();
//        filterPanel.setBounds(400, 20, 400, 80);
//        filterPanel.add(filter);
//        filterPanel.add(selectFilter);
//
//        selectFilter.addActionListener((e) -> {
//            System.out.println(filter.getSelectedIndex());
//            System.out.println(filter.getSelectedItem());
//        });

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
