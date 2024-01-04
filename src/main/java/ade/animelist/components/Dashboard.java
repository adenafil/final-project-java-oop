package ade.animelist.components;

import ade.animelist.controller.Controller;
import ade.animelist.database.repository.*;
import ade.animelist.util.AnimeListWorker;
import ade.animelist.util.ImageLoaderWorker;
import ade.animelist.util.ImageRenderer;
import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class Dashboard {
    private static int indexListenerCollection = 0;
    public static JPanel dashboardDiv = new JPanel();
    private static ConfigRepository configRepository = new ConfigRepositoryImpl();
    public static boolean isOpened = false;
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

        JLabel name = new JLabel("Welcome, @" + configRepository.getCurrentUsername());
        SettingRepository settingRepository = new SettingRepositoryImpl();
        name.setForeground(Color.WHITE);
        name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));



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

            System.out.println(img.getIconWidth());
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

            System.out.println("index collect " + indexListenerCollection);
            AddAnimeToDbRepository listAnimeuser = new AddAnimeToDbRepositoryImpl();
            if (indexListenerCollection  == 0) {
//                indexListenerCollection++;
                CardCollection.totalAnime = 0;
                listAnimeuser.getAllAnimeListInDatabaseUser().forEach(
                        maguire -> {
                            System.out.println();
                            CardCollection.addCard(
                                    maguire.title,
                                    null,
                                    maguire.malId
                            );
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

//            CardCollection.setIndex(0);
//
//            CardCollection.setIndex(0);
//            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
//
//                @Override
//                protected Void doInBackground() throws Exception {
//                    System.out.println("masih 0 kah ?" + indexListenerCollection);
//                    ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(listAnimeuser.getAllAnimeListUser());
//                    imageLoaderWorker.execute();
//
//                    listAnimeuser.getAllAnimeListUser().forEach(
//                            luAsikBang -> {
////                                CardCollection.addCard(
////                                        luAsikBang.title,
//                                        ImageRenderer.createImageIconByURL(luAsikBang.images.getJpg().largeImageUrl);
////                                        luAsikBang.malId
////                                );
//                            }
//                    );
//
//                    if (indexListenerCollection == 0) {
//                        ++indexListenerCollection;
//                        return null;
//                    } else {
//                        listAnimeuser.getAllAnimeListUser().forEach(
//                                luAsikBang -> {
//                                CardCollection.addCard(
//                                        luAsikBang.title,
//                                    ImageRenderer.createImageIconByURL(luAsikBang.images.getJpg().largeImageUrl),
//                                        luAsikBang.malId
//                                );
//                                }
//                        );
//                        Controller.addComponent(card);
//                    }
//
//                    CardCollection.setIndex(0);
//
////                    Thread.sleep(5000);
//
//                    return null;
//                }
//            };
//
//            worker.execute();


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
//            dashboardDiv.removeAll();
//            dashboardDiv.repaint();
//            dashboardDiv.revalidate();
            Controller.removeComponent(dashboardDiv);
            Controller.createLogin();
//            Controller.removeComponent(Controller.navbar.getNavbar());
            Controller.removeDashboard();
            Controller.removeComponent(Navbar.navbar);
            Navbar.removeNavbar();
//            Controller.navbar.removeAll();

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
//            CardCollection.isOpened = true;
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
