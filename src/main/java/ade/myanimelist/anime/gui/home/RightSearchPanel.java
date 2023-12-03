package ade.myanimelist.anime.gui.home;

import ade.myanimelist.anime.api.JikanAPI;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RightSearchPanel {
    JPanel leftSearchPanel;
    JTextField textSearch;
    JButton submit;
    JPanel searchAndSubmitPanel;
    JScrollPane searchPanel;
    public JPanel getRIghtSearchPanel() {

        leftSearchPanel = new JPanel();
        leftSearchPanel.setBounds(390, 0, 1500, 1800);
        leftSearchPanel.setBackground(Color.BLUE);
        leftSearchPanel.setLayout(null);

        textSearch = new JTextField();
//        textSearch.setBounds(420, 10, 200, 200);
        textSearch.setPreferredSize(new Dimension(800, 40));

        submit = new JButton("Submit");
        submit.setBounds(0, 10, 150, 40);
//        submit.setBounds(40);


        searchAndSubmitPanel = new JPanel();
        searchAndSubmitPanel.setBounds(15, 10, 1450, 60);
        searchAndSubmitPanel.setBackground(Color.YELLOW);
        searchAndSubmitPanel.add(textSearch);
        searchAndSubmitPanel.add(submit);


        searchPanel = new JScrollPane();
        searchPanel.setBounds(15, 100, 1450, 880);

//        searchPanel.setBackground(Color.blue);


        submit.addActionListener((e) -> {
            List<Anime> searchByuser = new ArrayList<>();
            System.out.println(textSearch.getText());
            try {
                searchByuser = JikanAPI.searchAnimeByTitle(textSearch.getText());
            } catch (JikanQueryException ex) {
                throw new RuntimeException(ex);
            }
            TableListAnime tableListAnime = new TableListAnime();

            JTable table = null;
            try {
                table = tableListAnime.getResultOfSearch(JikanAPI.animeToDataTable(searchByuser));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            searchPanel.add(table);
            searchPanel.setViewportView(table);
        });

        leftSearchPanel.add(searchPanel);
        leftSearchPanel.add(searchAndSubmitPanel);


        return leftSearchPanel;
    }
}
