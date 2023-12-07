package ade.myanimelist.anime.gui.home;

import javax.swing.*;
import java.awt.*;

public class Tes {
    public static void main(String[] args) {
        JLabel information = new JLabel("Information");
        information.setBounds(40, 340, 300, 25);
        information.setFont(new Font(null, Font.BOLD, 20));

        JLabel type = new JLabel("Type: ");
        type.setBounds(40, 370, 300, 25);
        type.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansType = new JLabel("TV");
        ansType.setBounds(85, 370, 300, 25);
        ansType.setFont(new Font(null, Font.PLAIN, 15));

        JLabel episode = new JLabel("Episodes: ");
        episode.setBounds(40, 400, 300, 25);
        episode.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansEpisode = new JLabel("12");
        ansEpisode.setBounds(115, 400, 300, 25);
        ansEpisode.setFont(new Font(null, Font.PLAIN, 15));

        JLabel status = new JLabel("Status: ");
        status.setBounds(40, 430, 300, 25);
        status.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansStatus = new JLabel("Currently Watching");
        ansStatus.setBounds(95, 430, 300, 25);
        ansStatus.setFont(new Font(null, Font.PLAIN, 15));

        JLabel aired = new JLabel("Aired: ");
        aired.setBounds(40, 460, 300, 25);
        aired.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansAired = new JLabel("Oct 6, 2023 to ?");
        ansAired.setBounds(85, 460, 300, 25);
        ansAired.setFont(new Font(null, Font.PLAIN, 15));

        JLabel premiered = new JLabel("Premiered: ");
        premiered.setBounds(40, 490, 300, 25);
        premiered.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansPremiered = new JLabel("Fall 2023");
        ansPremiered.setBounds(125, 490, 300, 25);
        ansPremiered.setFont(new Font(null, Font.PLAIN, 15));

        JLabel broadcast = new JLabel("Broadcast: ");
        broadcast.setBounds(40, 520, 300, 25);
        broadcast.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansBroadcast = new JLabel("Friday at 22:00 (JST)");
        ansBroadcast.setBounds(125, 520, 300, 25);
        ansBroadcast.setFont(new Font(null, Font.PLAIN, 15));

        JLabel studios = new JLabel("Studios: ");
        studios.setBounds(40, 550, 300, 25);
        studios.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansStudios = new JLabel("LIDENFILMS");
        ansStudios.setBounds(105, 550, 300, 25);
        ansStudios.setFont(new Font(null, Font.PLAIN, 15));

        JLabel source = new JLabel("Source: ");
        source.setBounds(40, 580, 300, 25);
        source.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansSource = new JLabel("Light novel");
        ansSource.setBounds(105, 580, 300, 25);
        ansSource.setFont(new Font(null, Font.PLAIN, 15));

        JLabel genres = new JLabel("Genres: ");
        genres.setBounds(40, 610, 300, 25);
        genres.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansGenres = new JLabel("Action, Adventure, Fantasy");
        ansGenres.setBounds(105, 610, 300, 25);
        ansGenres.setFont(new Font(null, Font.PLAIN, 15));

        JLabel theme = new JLabel("Theme: ");
        theme.setBounds(40, 640, 300, 25);
        theme.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansTheme = new JLabel("Gore");
        ansTheme.setBounds(100, 640, 300, 25);
        ansTheme.setFont(new Font(null, Font.PLAIN, 15));

        JLabel duration = new JLabel("Duration: ");
        duration.setBounds(40, 670, 300, 25);
        duration.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansDuration = new JLabel("23 min. per ep.");
        ansDuration.setBounds(115, 670, 300, 25);
        ansDuration.setFont(new Font(null, Font.PLAIN, 15));

        JLabel rating = new JLabel("Rating: ");
        rating.setBounds(40, 700, 300, 25);
        rating.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansRating = new JLabel("R - 17+");
        ansRating.setBounds(100, 700, 300, 25);
        ansRating.setFont(new Font(null, Font.PLAIN, 15));

        JLabel score = new JLabel("Score: ");
        score.setBounds(40, 730, 300, 25);
        score.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansScore = new JLabel("9.23");
        ansScore.setBounds(95, 730, 300, 25);
        ansScore.setFont(new Font(null, Font.PLAIN, 15));

        JLabel ranked = new JLabel("Ranked: ");
        ranked.setBounds(40, 760, 300, 25);
        ranked.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansRanked = new JLabel("#1");
        ansRanked.setBounds(105, 760, 300, 25);
        ansRanked.setFont(new Font(null, Font.PLAIN, 15));

        JLabel popularity = new JLabel("Popularity: ");
        popularity.setBounds(40, 790, 300, 25);
        popularity.setFont(new Font(null, Font.BOLD, 15));

        JLabel ansPopularity = new JLabel("#646");
        ansPopularity.setBounds(120, 790, 300, 25);
        ansPopularity.setFont(new Font(null, Font.PLAIN, 15));


        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(null);
        panelLeft.setBounds(0, 50, 300, 1080);
        panelLeft.setBackground(Color.decode("#ffffff"));

        panelLeft.add(popularity);
        panelLeft.add(ansPopularity);
        panelLeft.add(ranked);
        panelLeft.add(ansRanked);
        panelLeft.add(ansScore);
        panelLeft.add(score);
        panelLeft.add(duration);
        panelLeft.add(ansDuration);
        panelLeft.add(rating);
        panelLeft.add(ansRating);
        panelLeft.add(theme);
        panelLeft.add(ansTheme);
        panelLeft.add(genres);
        panelLeft.add(ansGenres);
        panelLeft.add(source);
        panelLeft.add(ansSource);
        panelLeft.add(studios);
        panelLeft.add(ansStudios);
        panelLeft.add(ansBroadcast);
        panelLeft.add(broadcast);
        panelLeft.add(premiered);
        panelLeft.add(ansPremiered);
        panelLeft.add(aired);
        panelLeft.add(ansAired);
        panelLeft.add(ansStatus);
        panelLeft.add(status);
        panelLeft.add(episode);
        panelLeft.add(ansEpisode);
        panelLeft.add(information);
        panelLeft.add(ansType);
        panelLeft.add(type);
        panelLeft.add(getImageAnime());

        JFrame frame = new JFrame();

        frame.add(panelLeft);
        frame.add(getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1920, 1080);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static JPanel getTitle() {
        JLabel title = new JLabel();
        title.setText("Sengoku Basara");
        title.setBounds(10, 5, 1920, 40);
        title.setFont(new Font(null, Font.PLAIN, 30));

        JPanel panelTitle = new JPanel();
        panelTitle.setBackground(Color.decode("#e1e7f5"));
        panelTitle.setBounds(0, 0, 1920, 50);
        panelTitle.add(title);
        panelTitle.setLayout(null);

        return panelTitle;
    }

    public static JLabel getImageAnime() {
        JLabel image = new JLabel("");
//        image.setSize(400, 300);
        image.setBounds(40, 30, 300, 300);
        image.setIcon(ResizeImageIcon.setImageIconSize(new ImageIcon("src/main/java/ade/myanimelist/anime/gui/home/assets/dummyData.jpg"), 300, 300));

        return image;
    }
}