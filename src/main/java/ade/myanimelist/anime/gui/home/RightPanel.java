package ade.myanimelist.anime.gui.home;

import javax.swing.*;
import java.awt.*;

public class RightPanel {
    TableListAnime tableListAnime;
    JScrollPane rightPanel;

    public JScrollPane getRightPanel() {
        tableListAnime = new TableListAnime();
        rightPanel = new JScrollPane();
        rightPanel.setBackground(Color.yellow);
        rightPanel.setBounds(420, 100, 1450, 900);
        rightPanel.setOpaque(true);



        rightPanel.add(tableListAnime.getTableAnimeList());
        rightPanel.setViewportView(tableListAnime.getTableAnimeList());

        return rightPanel;
    }
}
