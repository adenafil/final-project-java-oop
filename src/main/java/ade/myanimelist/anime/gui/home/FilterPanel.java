package ade.myanimelist.anime.gui.home;

import javax.swing.*;
import java.awt.*;

public class FilterPanel {
   private String[] filterList = {
            "ALL ANIME", "CURRENTLY WATCHING", "COMPLETED",
            "DROPPED", "PLAN TO WATCH"
    };
    JComboBox filter;
    Button selectFilter;
    JPanel filterPanel;
    public JPanel getFilterPanel() {


        filter = new JComboBox(filterList);

        selectFilter = new Button("Select Filter");
//        selectFilter.setBounds(420, 20, 100, 100);

        filterPanel = new JPanel();
        filterPanel.setBounds(400, 20, 400, 80);
        filterPanel.add(filter);
        filterPanel.add(selectFilter);

        selectFilter.addActionListener((e) -> {
            System.out.println(filter.getSelectedIndex());
            System.out.println(filter.getSelectedItem());
        });


        return filterPanel;
    }
}
