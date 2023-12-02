package ade.myanimelist.anime.gui;

import javax.swing.*;
import java.awt.*;

public class SearchPanel {
    JTextField textSearch;
    JButton submit;
    JPanel searchPanel;
    public JPanel getSaarchPanel() {
        textSearch = new JTextField();
//        textSearch.setBounds(420, 20, 200, 200);
        textSearch.setPreferredSize(new Dimension(800, 40));

        submit = new JButton("Submit");
//        submit.setBounds(40);

        searchPanel = new JPanel();
        searchPanel.setBounds(620, 20, 1000, 60);
//        searchPanel.setBackground(Color.blue);
        searchPanel.add(textSearch);
        searchPanel.add(submit);

        submit.addActionListener((e) -> {
            System.out.println(textSearch.getText());
        });


        return searchPanel;
    }
}
