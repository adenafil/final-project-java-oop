package ade.animelist.components;
import ade.animelist.api.JikanAPI;
import ade.animelist.controller.Controller;
import ade.animelist.database.repository.AddAnimeToDbRepository;
import ade.animelist.database.repository.AddAnimeToDbRepositoryImpl;
import ade.animelist.util.AnimeListWorker;
import ade.animelist.util.ImageLoaderWorker;
import ade.animelist.util.ImageRenderer;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import net.sandrohc.jikan.model.enums.AgeRating;
import reactor.core.scheduler.Schedulers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;


public class Navbar extends JFrame {
    JLabel logo;
    JTextField search;
    private String searchByUser;
    private CardSearchAnime cardSearchAnime = new CardSearchAnime();
    private CardTopAnime cardTopAnime = new CardTopAnime();
    private JPanel topAnimeDiv = cardTopAnime.getCard();
    private JPanel searchAnimeDiv = cardSearchAnime.getCard();
    private CardRecomendationAnime cardRecomendationAnime = new CardRecomendationAnime();

    private JPanel recomendationAnimeDiv = cardRecomendationAnime.getCard();
    int bingung = 0;

    public JPanel getNavbar() {
        // div
        JPanel divContainer = new JPanel();
        divContainer.setLayout(new BoxLayout(divContainer, BoxLayout.Y_AXIS));
        divContainer.setSize(1920, 100);
        divContainer.setOpaque(true);
        divContainer.setBackground(Color.ORANGE);

        // div
        JPanel divNav = new JPanel();
//        divNav.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        divNav.setOpaque(true);
        divNav.setBackground(Color.ORANGE);

//
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(1920, 1080);
//        this.setResizable(false);
//        this.setLayout(null);
//        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Component Logo
        logo = new JLabel("AdeAnimeList");
        logo.setOpaque(true);
//        logo.setPreferredSize(new Dimension(200, 200));
//        logo.setLayout(null);
        logo.setPreferredSize(new Dimension(300, 40));
        logo.setMaximumSize(new Dimension(300, 40));
        logo.setForeground(Color.decode("#333b48"));
        logo.setBackground(Color.ORANGE);
        logo.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        // Component JTextField

        search = new JTextField();
//        search.setBounds(500, 0, 1000, 40);
        search.setPreferredSize(new Dimension(500, 40));
        search.setText("Cari anime...");
        search.setFont(new Font(null, Font.BOLD, 20));
//        search.setFocusable(false);
        search.setBorder(BorderFactory.createEmptyBorder());

        // Component Dasboard
        JLabel dashboard = new JLabel("Dashbsoard");
        dashboard.setOpaque(true);
        dashboard.setPreferredSize(new Dimension(300, 40));
        dashboard.setMaximumSize(new Dimension(300, 40));
//        dashboard.setBounds(400, 0, 120, 100);
        dashboard.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        dashboard.setForeground(Color.decode("#333b48"));
        dashboard.setBackground(Color.ORANGE);
        dashboard.setHorizontalAlignment(SwingConstants.CENTER);


        // Dashboard Listener
        dashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                dashboard.setForeground(Color.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                dashboard.setForeground(Color.decode("#333b48"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

//                // bad code 😎
//                if (!Dashboard.isOpened) Dashboard.isOpened = true;


                super.mouseClicked(e);
                if (cardTopAnime.isOpened && cardRecomendationAnime.isOpened) {
                    cardTopAnime.removePanel();
                    cardRecomendationAnime.removePanel();

                    removeTopCardComponent();
                    removeRecomdendationCardComponent();
                    cardTopAnime.isOpened = false;
                    cardRecomendationAnime.isOpened = false;
                    Dashboard.isOpened = true;
                    Controller.addComponent(Dashboard.getDashboard());
                }

                if (AnimePage.isOpened) {
                    AnimePage.isOpened = false;
                    AnimePage.removeContainer();
                    Dashboard.isOpened = true;
                    Controller.addComponent(Dashboard.getDashboard());
                }

//                if (cardSearchAnime.isOpened) {
//                    removeSearchAnimeCard();
//                    cardSearchAnime.isOpened = false;
//                }

//                if (cardTopAnime.animePage != null) {
//                    cardTopAnime.animePage.removeContainer();
//                }
//
//                if (cardRecomendationAnime.animePage != null) {
//                    cardRecomendationAnime.animePage.removeContainer();
//                }

                if (cardSearchAnime.isOpened) {
//                    cardSearchAnime.animePage.removeContainer();
                    removeSearchAnimeCard();
                    Controller.addComponent(Dashboard.getDashboard());
                    cardSearchAnime.isOpened = false;
                    Dashboard.isOpened = true;
                }

//                if (CardCollection.isOpened) {
//                    System.out.println("HEllo");
//                    Controller.removeComponent(CardCollection.panel);
//                    CardCollection.cardPanel.removeAll();
//                    CardCollection.panel.removeAll();
//
//                }

                // bug
                if (CardCollection.isOpened) {
                    System.out.println("IFELSE");
                    Controller.removeComponent(CardCollection.panel);
                    CardCollection.cardPanel.removeAll();
                    CardCollection.panel.removeAll();
                    CardCollection.isOpened = false;
                    Dashboard.isOpened = true;
                    Controller.addComponent(Dashboard.getDashboard());
                }

                if (Setting.isOpened) {
                    Controller.removeComponent(Setting.panelSetting);
                    Setting.removePanelSetting();
                    Setting.isOpened = false;
                    Dashboard.isOpened = true;
                    Controller.addComponent(Dashboard.getDashboard());
                }


//                if (!Dashboard.isOpened)  {
//                    Controller.addComponent(Dashboard.getDashboard());
//                    Dashboard.isOpened = true;
//                }
            }
        });


        // Listener refresh logo
        cardRecomendationAnime.refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                recomendationAnimeDiv.removeAll();
                removeRecomdendationCardComponent();

                recomendationAnimeDiv = cardRecomendationAnime.getCard();
                addRecomendationAnime();
                System.out.println("difarina");
            }
        });

        CardCollection.refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AddAnimeToDbRepository listAnimeuser = new AddAnimeToDbRepositoryImpl();
                CardCollection.panel.removeAll();
                Controller.removeComponent(CardCollection.panel);

                CardCollection.panel = CardCollection.getCard();
//                ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(listAnimeuser.getAllAnimeListUser());
//                imageLoaderWorker.execute();

//                SwingUtilities.invokeLater(() -> listAnimeuser.getAllAnimeListUser().parallelStream().forEach(
//                        luAsikBang -> {
//                            ImageIcon ade = ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) != null ? ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) : null;
//                            System.out.println("is image null ? " + ade);
//                            CardCollection.addCard(
//                                    luAsikBang.title,
//                                    ImageRenderer.createImageIconByURL(luAsikBang.images.getJpg().largeImageUrl),
//                                    luAsikBang.malId
//                            );
//                        }
//                ));
//                listAnimeuser.getAllAnimeListUserAsync().parallelStream().forEach(b -> {
//                    try {
//                        ImageIcon tes = ImageRenderer.createImageIconByURL(b.join().images.getJpg().largeImageUrl);
//                        System.out.println(tes.getIconWidth());
//                        System.out.println(b.join().title);
//                        SwingUtilities.invokeLater(() -> {
//                            CardCollection.addCard(
//                                    b.join().title,
//                                    tes,
//                                    b.join().malId
//                            );
//                        });
//                    } catch (Exception ex) {
//                        throw new RuntimeException(ex);
//                    }
//                });

                List<CompletableFuture<Anime>> animeFutures = listAnimeuser.getAllAnimeListUserAsync();
                AnimeListWorker animeListWorker = new AnimeListWorker(animeFutures);
                animeListWorker.execute();

                CardCollection.setIndex(0);
//                listAnimeuser.getAllAnimeListUser().forEach(
//                        luAsikBang -> {
//                            ImageIcon ade = ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) != null ? ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) : null;
//                            System.out.println("is image null ? " + ade);
//                            CardCollection.addCard(
//                                    luAsikBang.title,
//                                    ade,
//                                    luAsikBang.malId
//                            );
//                        }
//                );
//
//                listAnimeuser.getAllAnimeListUser().parallelStream().forEach(
//                        luAsikBang -> {
//                            ImageIcon ade = ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) != null ? ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) : null;
//                            System.out.println("is image null ? " + ade);
//                            CardCollection.addCard(
//                                    luAsikBang.title,
//                                    ade,
//                                    luAsikBang.malId
//                            );
//                        }
//                );

                Controller.addComponent(CardCollection.panel);

                System.out.println("difarina");
            }
        });


        // listener logo
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                logo.setForeground(Color.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                logo.setForeground(Color.decode("#333b48"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

//                cardTopAnime.isOpened = true;
//                cardRecomendationAnime.isOpened = true;
                super.mouseClicked(e);
                System.out.println("log bingung when enter logo in : " + bingung);

//                System.out.println(cardRecomendationAnime.refresh);

                System.out.println("page -> " + AnimePage.isOpened);

                if (AnimePage.isOpened) {
                    AnimePage.isOpened = false;
                    cardTopAnime.isOpened = true;
                    cardRecomendationAnime.isOpened = true;
                    AnimePage.removeContainer();

                    System.out.println("masuk sini");

                    addTopCardAnime();
                    addRecomendationAnime();

                    return;
                }

                System.out.println("top && reco -> " + (cardTopAnime.isOpened && cardRecomendationAnime.isOpened));
                if (cardTopAnime.isOpened && cardRecomendationAnime.isOpened) {
                    System.out.println("mamamam nenene" + topAnimeDiv != null);
                    recomendationAnimeDiv.removeAll();
                    removeRecomdendationCardComponent();

                    topAnimeDiv.removeAll();
                    removeTopCardComponent();

                    topAnimeDiv = cardTopAnime.getCard();
                    addTopCardAnime();

                    recomendationAnimeDiv = cardRecomendationAnime.getCard();
                    addRecomendationAnime();
                    return;
                }

//                if (CardCollection.isOpened) {
//                    System.out.println("MEssi");
//                    Controller.removeComponent(CardCollection.animePage.contaienrDiv);
//                    CardCollection.animePage.contaienrDiv.removeAll();
////                    addTopCardAnime();
//                    addRecomendationAnime();
//                    CardCollection.isOpened = false;
//                    bingung = 0;
//                }
//

                System.out.println("Cc -> " + CardCollection.isOpened);
                if (CardCollection.isOpened) {
                    System.out.println("Colection");
                    CardCollection.panel.removeAll();
                    Controller.removeComponent(CardCollection.panel);
//                    addTopCardAnime();
                    addTopCardAnime();
                    addRecomendationAnime();
                    CardCollection.isOpened = false;
                    cardRecomendationAnime.isOpened = true;
                    cardTopAnime.isOpened = true;
                    return;
                }

                System.out.println("search -> " + cardSearchAnime.isOpened);
                if (cardSearchAnime.isOpened) {
                    System.out.println("log search");
                    removeSearchAnimeCard();
                    addTopCardAnime();
                    addRecomendationAnime();
                    cardSearchAnime.isOpened = false;
                    cardTopAnime.isOpened = true;
                    cardRecomendationAnime.isOpened = true;
                    return;
                }

//                // ini untuk adding up
//                if (!CardCollection.isOpened) {
//                    System.out.println("hiihihj");
//                    addTopCardAnime();
//                    addRecomendationAnime();
//                    bingung = 0;
//                }

                System.out.println("Dashboard -> " + Dashboard.isOpened);

                if (Dashboard.isOpened) {
                    cardTopAnime.isOpened = true;
                    cardRecomendationAnime.isOpened = true;
                    System.out.println("log 3939 : " + bingung);
                    addTopCardAnime();
                    addRecomendationAnime();
                    System.out.println("Mmaamma huthuthuthut");
                    Dashboard.dashboardDiv.removeAll();
                    Controller.removeComponent(Dashboard.dashboardDiv);
                    Dashboard.isOpened = false;
                }

                if (Setting.isOpened) {
                    Controller.removeComponent(Setting.panelSetting);
                    Setting.removePanelSetting();
                    Setting.isOpened = false;
                    addTopCardAnime();
                    addRecomendationAnime();
                    cardTopAnime.isOpened = true;
                    cardRecomendationAnime.isOpened = true;
                }


//                if (bingung == 6767) {
//                    System.out.println("HEllo");
//                    Controller.removeComponent(CardCollection.panel);
//                    CardCollection.cardPanel.removeAll();
//                    CardCollection.panel.removeAll();
//                    addTopCardAnime();
//                    addRecomendationAnime();
//                    bingung = 0;
//                }

            }
        });

        search.addActionListener(e -> {
//            if (cardSearchAnime.cardPanel != null) {
//                removeSearchAnime();
//            }
//            cardSearchAnime.isOpened = true;
//            addSearchAnimeCard();
            if (AnimePage.isOpened) {
                AnimePage.isOpened = false;
                AnimePage.removeContainer();
                addSearchAnimeCard();
                cardSearchAnime.isOpened = true;
            }

            if (Setting.isOpened) {
                Controller.removeComponent(Setting.panelSetting);
                Setting.removePanelSetting();
                Setting.isOpened = false;
                addSearchAnimeCard();
                cardSearchAnime.isOpened = true;
            }


            if (cardTopAnime.isOpened && cardRecomendationAnime.isOpened) {
                cardTopAnime.removePanel();
                cardRecomendationAnime.removePanel();

                removeTopCardComponent();
                removeRecomdendationCardComponent();
                cardSearchAnime.isOpened = true;
                cardTopAnime.isOpened = false;
                cardRecomendationAnime.isOpened = false;
                addSearchAnimeCard();
            }

            if (CardCollection.isOpened) {
                Controller.removeComponent(CardCollection.panel);
                CardCollection.panel.removeAll();
                CardCollection.cardPanel.removeAll();
                CardCollection.isOpened = false;
                cardSearchAnime.isOpened = true;
                addSearchAnimeCard();
            }

            if (Dashboard.isOpened) {
                Dashboard.dashboardDiv.removeAll();
                Controller.removeComponent(Dashboard.dashboardDiv);
                Dashboard.isOpened = false;
                cardSearchAnime.isOpened = true;
                addSearchAnimeCard();
            }
//            cardSearchAnime = new CardSearchAnime();
//            Controller.addComponent(cardSearchAnime.getCard());
            if (e.getSource() == search ) {
                cardSearchAnime.removeData();
                cardSearchAnime.setIndex(0);
                System.out.println(search.getText());
                setSearchByUser(search.getText());

                try {
                    JikanAPI.getTitleAndImageAnimeBySearchAsync(search.getText())
                            .subscribeOn(Schedulers.parallel())
                            .subscribe(
                                    animeList -> {
                                        CardRecomendationAnime.index = 0;
                                        ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(animeList);
                                        imageLoaderWorker.execute();
                                        animeList.stream().parallel().forEach(bayor -> addAnime(bayor.title, ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL(bayor.images.getJpg().largeImageUrl), 450, 450), bayor.malId));
                                    },
                                    throwable -> {
                                        System.out.println("error : " + throwable.getMessage());
                                    }
                            );
                } catch (JikanQueryException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


//        add(dashboard);
//        add(search);
//        add(logo);
//
        divNav.add(Box.createVerticalStrut(80));
        divNav.add(logo);

        JLabel margin = new JLabel();
        margin.setOpaque(true);
        margin.setPreferredSize(new Dimension(400, 20));
        margin.setMaximumSize(new Dimension(400, 20));
        margin.setBackground(Color.ORANGE);

        divNav.add(margin);
//        divNav.add(Box.createHorizontalStrut(500));

        JLabel margin2 = new JLabel();
        margin2.setOpaque(true);
        margin2.setPreferredSize(new Dimension(400, 20));
        margin2.setMaximumSize(new Dimension(400, 20));
        margin2.setBackground(Color.ORANGE);

        divNav.add(search);
        divNav.add(margin2);
//        divNav.add(Box.createHorizontalStrut(500));
        divNav.add(dashboard);


        divContainer.add(divNav);
//        add(divContainer);

        return divContainer;
    }

    public String getSearchByUser() {
        return searchByUser;
    }

    public void setSearchByUser(String search) {
        this.searchByUser = search;
    }


    public static void main(String[] args) {
        Navbar navbar = new Navbar();
        navbar.add(navbar.getNavbar());
        navbar.setVisible(true);
    }

    public void addTopCardAnime() {
        System.out.println("log add top card");
        try {
            JikanAPI.getTopAnime()
                    .subscribeOn(Schedulers.parallel())
                    .subscribe(
                            animeList -> {
                                ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(animeList);
                                imageLoaderWorker.execute();
                                animeList.forEach(bayor -> cardTopAnime.addCard(bayor.title, ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL(bayor.images.getJpg().largeImageUrl), 450, 450), bayor.malId));
                            },
                            throwable -> {
                                System.out.println("error : " + throwable.getMessage());
                            }
                    );
            Controller.addComponent(topAnimeDiv);
        } catch (JikanQueryException ex) {
            throw new RuntimeException(ex);
        }
    }

    AtomicInteger index = new AtomicInteger();
    public void addRecomendationAnime() {
        JikanAPI.getRecommendationAnimeAsync()
                .subscribeOn(Schedulers.parallel())
                .subscribe(
                        animeList -> {
                            ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(animeList);
                            imageLoaderWorker.execute();
                            // fix indexing
                            CardRecomendationAnime.index = 0;
                            animeList.forEach(
                                    (bayor) -> {
                                        if (isHalalAnime(bayor)) {
//                                            ImageIcon imgReco = ImageRenderer.createImageIconByURL(bayor.images.getJpg().largeImageUrl);
//                                            System.out.println("log width " + imgReco.getIconWidth());
                                            System.out.println("log name " + bayor.title);
                                                    cardRecomendationAnime.addCard(bayor.title, ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL(bayor.images.getJpg().largeImageUrl), 450, 450), bayor.malId);
                                        }
                                    }
                            );
                        },
                        throwable -> {
                            System.out.println("error when fetch recomenation anime : " + throwable.getMessage());
                            JOptionPane.showMessageDialog(null, "Maaf Internal Server Error, Klik Ok Untuk Mengrefresh Lagi.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                            throwable.printStackTrace();
                            index.set(1);
                            System.out.println(index.get());
                            addRecomendationAnime();
                        }
                );
        System.out.println(index.get());
        if (index.get() == 1) {
            System.out.println("masssuppk");
            index.set(0);
            return;
        }
        Controller.addComponent(recomendationAnimeDiv);
    }

    private boolean isHalalAnime(Anime anime) {
        return anime.rating == AgeRating.PG13;
    }

    public void addHomeAnime() {
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel home = new JPanel();
        home.setSize(1920, 1000);
        home.setLayout(new GridLayout());

        gbc.gridy = 1;

        home.add(topAnimeDiv, gbc);

        gbc.gridy = 2;

        home.add(recomendationAnimeDiv, gbc);

        Controller.addComponent(home);
    }
    public void addSearchAnimeCard() {
        Controller.addComponent(searchAnimeDiv);
    }

    public void removeSearchAnimeCard() {
//        searchAnimeDiv.removeAll();
        Controller.removeComponent(searchAnimeDiv);
    }

    public void removeTopCardComponent() {
        Controller.removeComponent(topAnimeDiv);
    }

    public void removeRecomdendationCardComponent() {
        Controller.removeComponent(recomendationAnimeDiv);
    }

    public CardSearchAnime getCardSearch() {
        return cardSearchAnime;
    }


    public CardTopAnime getCardTopAnime() {
        if (cardTopAnime.getCard() == null) {
            System.out.println("okay");
        }
        return cardTopAnime;
    }

    public void setCardTopAnime() {

    }

    public void setCardSearchAnime(CardSearchAnime cardSearchAnime) {
        this.cardSearchAnime = cardSearchAnime;
    }

    public void addAnime(String title,ImageIcon image, int id) {
        getCardSearch().addCard(title, image, id);
    }

    public JPanel getTopAnime() {
        return topAnimeDiv;
    }

    public JPanel getRecomendationAnimeDiv() {
        return  recomendationAnimeDiv;
    }

    public void syncDelete() {
        cardTopAnime.removePanel();
        cardRecomendationAnime.removePanel();

        removeTopCardComponent();
        removeRecomdendationCardComponent();
//
    }

}

class MyThread extends Thread {
    public void run() {
        AddAnimeToDbRepository listAnimeuser = new AddAnimeToDbRepositoryImpl();
        ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(listAnimeuser.getAllAnimeListUser());
        imageLoaderWorker.execute();
//                listAnimeuser.getAllAnimeListUser().forEach(
//                        luAsikBang -> {
//                            ImageIcon ade = ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) != null ? ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) : null;
//                            System.out.println("is image null ? " + ade);
//                            CardCollection.addCard(
//                                    luAsikBang.title,
//                                    ade,
//                                    luAsikBang.malId
//                            );
//                        }
//                );
//
        listAnimeuser.getAllAnimeListUser().parallelStream().forEach(
                luAsikBang -> {
                    ImageIcon ade = ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) != null ? ImageRenderer.getCacheImageForCollectionPage(luAsikBang.images.getJpg().largeImageUrl) : null;
                    System.out.println("is image null ? " + ade);
                    CardCollection.addCard(
                            luAsikBang.title,
                            ade,
                            luAsikBang.malId
                    );
                }
        );
    }
}




