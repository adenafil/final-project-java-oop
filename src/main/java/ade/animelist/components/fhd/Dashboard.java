package ade.animelist.components.fhd;

import ade.animelist.components.utilcomponent.Counter;
import ade.animelist.controller.Controller;
import ade.animelist.database.repository.*;
import ade.animelist.components.utilcomponent.AnimeListWorker;
import ade.animelist.components.utilcomponent.ImageRenderer;
import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Class untuk component dasboard
 */
public class Dashboard {
    private static int indexListenerCollection = 0;
    public static JPanel dashboardDiv = new JPanel();
    private static ConfigRepository configRepository = new ConfigRepositoryImpl();
    public static boolean isOpened = false;

    /**
     * Method untuk mendapatkan component dashboard
     * @return component dashboard
     */
    public static JPanel getDashboard() {
        dashboardDiv.setOpaque(true);
        dashboardDiv.setPreferredSize(new Dimension(1920, 980));
        dashboardDiv.setMaximumSize(new Dimension(1920, 980));
        dashboardDiv.setBackground(Color.decode("#333b48"));

        // container for welcoming name
        JPanel containerFeat = new JPanel();
        containerFeat.setOpaque(true);
        containerFeat.setLayout(new BoxLayout(containerFeat, BoxLayout.Y_AXIS));
        containerFeat.setPreferredSize(new Dimension(1920, 600));
        containerFeat.setMaximumSize(new Dimension(1920, 600));
        containerFeat.setBackground(Color.decode("#333b48"));

        // add to container bg below navbar
        dashboardDiv.add(containerFeat);

        // logic name
        JPanel containerName = new JPanel();
        containerName.setOpaque(true);
        containerName.setPreferredSize(new Dimension(1920, 100));
        containerName.setMaximumSize(new Dimension(1920, 100));
        containerName.setBackground(Color.decode("#333b48"));

        JLabel name = new JLabel("Hello " + configRepository.getCurrentUsername());
        SettingRepository settingRepository = new SettingRepositoryImpl();
        name.setForeground(Color.WHITE);
        name.setFont(new Font(Font.SERIF, Font.BOLD, 60));



        containerName.add(name);

        containerFeat.add(containerName);

        // logic profile
        JPanel containerProfile = new JPanel();
        containerProfile.setOpaque(true);
        containerProfile.setPreferredSize(new Dimension(1920, 350));
        containerProfile.setMaximumSize(new Dimension(1920, 350));
        containerProfile.setBackground(Color.decode("#333b48"));

        JPanel profile = new JPanel();
        profile.setOpaque(true);
        profile.setPreferredSize(new Dimension(350, 350));
        profile.setMaximumSize(new Dimension(350, 350));
        profile.setBackground(Color.decode("#" + getHexaColor(configRepository.getCurrentUsername())));
        ImageIcon img = new ImageIcon(settingRepository.getPath());

        if (settingRepository.getPath() != null && img.getIconWidth() != -1) {

            JLabel imgLabel = new JLabel();
            imgLabel.setOpaque(true);
            imgLabel.setPreferredSize(new Dimension(350, 350));
            imgLabel.setMaximumSize(new Dimension(350, 350));
            imgLabel.setIcon(ImageRenderer.setImageIconSize(img, 350, 350));
            profile.add(imgLabel);
            profile.setBackground(Color.decode("#333b48"));
        }

        JLabel textIfProfileNotExist = new JLabel(configRepository.getCurrentUsername().toUpperCase().charAt(0) + "");
        textIfProfileNotExist.setOpaque(true);
        textIfProfileNotExist.setPreferredSize(new Dimension(350, 350));
        textIfProfileNotExist.setMaximumSize(new Dimension(350, 350));
        textIfProfileNotExist.setBackground(Color.decode("#" + getHexaColor(configRepository.getCurrentUsername())));
        textIfProfileNotExist.setFont(new Font(Font.SERIF, Font.BOLD, 250));
        textIfProfileNotExist.setVerticalAlignment(SwingConstants.CENTER);
        textIfProfileNotExist.setHorizontalAlignment(SwingConstants.CENTER);

        profile.add(textIfProfileNotExist);

        containerProfile.add(profile);

        containerFeat.add(containerProfile);

        JPanel containerButton = new JPanel();
        containerButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 25));
        containerButton.setOpaque(true);
        containerButton.setPreferredSize(new Dimension(1920, 150));
        containerButton.setMaximumSize(new Dimension(1920, 150));
        containerButton.setBackground(Color.decode("#333b48"));

        JButton myCollectionBtn = new JButton("My Collection");
        myCollectionBtn.setOpaque(true);
        myCollectionBtn.setBackground(Color.ORANGE);
        myCollectionBtn.setForeground(Color.decode("#333b48"));
        myCollectionBtn.setPreferredSize(new Dimension(300, 70));
        myCollectionBtn.setMaximumSize(new Dimension(300, 70));
        myCollectionBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        myCollectionBtn.setBorder(BorderFactory.createEmptyBorder());
        myCollectionBtn.setFocusable(false);

        myCollectionBtn.addActionListener(e -> {
            Dashboard.isOpened = false;
            CardCollection.isOpened = true;
            dashboardDiv.removeAll();
            Controller.removeComponent(dashboardDiv);

            JPanel card = CardCollection.getCardPanel();

            AddAnimeToDbRepository listAnimeuser = new AddAnimeToDbRepositoryImpl();
            if (indexListenerCollection  == 0) {
                Counter.getStartedUsingIncrement();
                CardCollection.totalAnime = 0;
                listAnimeuser.getAllAnimeListInDatabaseUser().forEach(
                        maguire -> {
                            if (Counter.a <= 5) {
                                CardCollection.panel.getComponent(2).setPreferredSize(new Dimension(1920, 400));
                                CardCollection.cardPanel.repaint();
                                CardCollection.cardPanel.revalidate();
                                CardCollection.panel.revalidate();
                                CardCollection.panel.repaint();
                            } else {
                                CardCollection.panel.getComponent(2).setPreferredSize(new Dimension(1920, 840));
                                CardCollection.panel.revalidate();
                                CardCollection.panel.repaint();
                            }

                            CardCollection.addCard(
                                    maguire.title,
                                    null,
                                    maguire.malId
                            );
                            Counter.incremennt();
                            ++CardCollection.totalAnime;
                        }
                );
            }

            Controller.addComponent(card);

            if (indexListenerCollection != 0) {
                List<CompletableFuture<Anime>> animeFutures = listAnimeuser.getAllAnimeListUserAsync();
                AnimeListWorker animeListWorker = new AnimeListWorker(animeFutures);
                animeListWorker.execute();
            }
            ++indexListenerCollection;
        });


        JButton signOutBtn = new JButton("Sign Out");
        signOutBtn.setOpaque(true);
        signOutBtn.setBackground(Color.ORANGE);
        signOutBtn.setForeground(Color.decode("#333b48"));
        signOutBtn.setPreferredSize(new Dimension(200, 70));
        signOutBtn.setMaximumSize(new Dimension(200, 70));
        signOutBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        signOutBtn.setBorder(BorderFactory.createEmptyBorder());
        signOutBtn.setFocusable(false);

        signOutBtn.addActionListener(e -> {
            Dashboard.isOpened = false;
            Controller.removeComponent(dashboardDiv);
            Controller.createLogin();
            Controller.removeDashboard();
            Controller.removeComponent(Navbar.navbar);
            Navbar.removeNavbar();
        });

        JButton settingBtn = new JButton("Setting");
        settingBtn.setOpaque(true);
        settingBtn.setBackground(Color.ORANGE);
        settingBtn.setForeground(Color.decode("#333b48"));
        settingBtn.setPreferredSize(new Dimension(200, 70));
        settingBtn.setMaximumSize(new Dimension(200, 70));
        settingBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        settingBtn.setBorder(BorderFactory.createEmptyBorder());
        settingBtn.setFocusable(false);

        settingBtn.addActionListener(e -> {
            Dashboard.isOpened = false;
            dashboardDiv.removeAll();
            Controller.removeComponent(dashboardDiv);
            Controller.addComponent(Setting.getPanelSetting());
        });


        containerButton.add(myCollectionBtn);
        containerButton.add(settingBtn);
        containerButton.add(signOutBtn);

        containerFeat.add(containerButton);

        return dashboardDiv;
    }

    /**
     * Mendapatkan warna based on username
     * @param name => nama
     * @return => warna hexacolor
     */
    public static String getHexaColor(String name) {
        int hashCode = name.hashCode();

        // Tetapkan nilai tetap untuk komponen merah dan hijau
        int red = 100;
        int green = 150;

        // Gunakan nilai hashCode untuk komponen biru
        int blue = Math.abs(hashCode % 256); // Ambil nilai absolute untuk menghindari nilai negatif

        Color color = new Color(red, green, blue);

        return Integer.toHexString(color.getRGB()).substring(2).toUpperCase();

    }
}
