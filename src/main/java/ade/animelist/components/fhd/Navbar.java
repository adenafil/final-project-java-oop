package ade.animelist.components.fhd;
import ade.animelist.api.JikanAPI;
import ade.animelist.components.utilcomponent.Counter;
import ade.animelist.controller.Controller;
import ade.animelist.components.utilcomponent.ImageLoaderWorker;
import ade.animelist.components.utilcomponent.ImageRenderer;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import net.sandrohc.jikan.model.enums.AgeRating;
import reactor.core.scheduler.Schedulers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Gui Class Navbar
 */
public class Navbar {
    static JLabel logo;
    static JTextField search;
    static private String searchByUser;
    static private CardSearchAnime cardSearchAnime = new CardSearchAnime();
    static private CardTopAnime cardTopAnime = new CardTopAnime();
    static private JPanel topAnimeDiv = cardTopAnime.getCard();
    static private JPanel searchAnimeDiv = cardSearchAnime.getCard();
    static private CardRecomendationAnime cardRecomendationAnime = new CardRecomendationAnime();

    private static JPanel recomendationAnimeDiv = cardRecomendationAnime.getCard();

    public static JPanel navbar = null;

    /**
     * Method untuk menambahkan navbar
     */
    public static void addNavbar() {
        navbar = getNavbar();
        navbar.repaint();
        navbar.revalidate();
        Controller.addComponent(navbar);
    }

    /**
     * Method untuk meremove navbar
     */
    public static void removeNavbar() {
        Controller.removeComponent(navbar);
        navbar.revalidate();
        navbar.repaint();
        navbar = null;
    }

    /**
     * Method untuk mendapakan component navbar
     * @return component navbar
     */
    public static JPanel getNavbar() {
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

        // Component Logo
        logo = new JLabel("Home");
        logo.setOpaque(true);
//        logo.setPreferredSize(new Dimension(200, 200));
//        logo.setLayout(null);
        logo.setPreferredSize(new Dimension(240, 40));
        logo.setMaximumSize(new Dimension(240, 40));
        logo.setForeground(Color.decode("#333b48"));
        logo.setBackground(Color.ORANGE);
//        logo.setBackground(Color.RED);
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

                if (cardSearchAnime.isOpened) {
                    removeSearchAnimeCard();
                    Controller.addComponent(Dashboard.getDashboard());
                    cardSearchAnime.isOpened = false;
                    Dashboard.isOpened = true;
                }
                if (CardCollection.isOpened) {
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
                super.mouseClicked(e);
                if (AnimePage.isOpened) {
                    AnimePage.isOpened = false;
                    cardTopAnime.isOpened = true;
                    cardRecomendationAnime.isOpened = true;
                    AnimePage.removeContainer();
                    addTopCardAnime();
                    addRecomendationAnime();

                    return;
                }

                if (cardTopAnime.isOpened && cardRecomendationAnime.isOpened) {
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

                if (CardCollection.isOpened) {
                    CardCollection.panel.removeAll();
                    Controller.removeComponent(CardCollection.panel);
                    addTopCardAnime();
                    addRecomendationAnime();
                    CardCollection.isOpened = false;
                    cardRecomendationAnime.isOpened = true;
                    cardTopAnime.isOpened = true;
                    return;
                }

                if (cardSearchAnime.isOpened) {
                    removeSearchAnimeCard();
                    addTopCardAnime();
                    addRecomendationAnime();
                    cardSearchAnime.isOpened = false;
                    cardTopAnime.isOpened = true;
                    cardRecomendationAnime.isOpened = true;
                    return;
                }

                if (Dashboard.isOpened) {
                    cardTopAnime.isOpened = true;
                    cardRecomendationAnime.isOpened = true;
                    addTopCardAnime();
                    addRecomendationAnime();
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

            }
        });

        search.addActionListener(e -> {
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
            if (e.getSource() == search ) {
                cardSearchAnime.removeData();
                cardSearchAnime.setIndexAddUpAnime(0);
                setSearchByUser(search.getText());

                SwingWorker<Void, Void> addSearchCollection = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        try {
                            Counter.getStartedUsingIncrement();
                            JikanAPI.getTitleAndImageAnimeBySearchAsync(search.getText())
                                    .subscribeOn(Schedulers.parallel())
                                    .subscribe(
                                            animeList -> {
                                                CardRecomendationAnime.index = 0;
                                                ImageLoaderWorker imageLoaderWorker = new ImageLoaderWorker(animeList);
                                                imageLoaderWorker.execute();
                                                animeList.stream().parallel().forEach(bayor -> {

                                                    if (Counter.incremennt() - 1 <= 5) {
                                                        cardSearchAnime.panel.getComponent(1).setPreferredSize(new Dimension(1920, 400));
                                                        cardSearchAnime.panel.repaint();
                                                        cardSearchAnime.panel.revalidate();
                                                    } else {
                                                        cardSearchAnime.panel.getComponent(1).setPreferredSize(new Dimension(1920, 940));
                                                        cardSearchAnime.panel.repaint();
                                                        cardSearchAnime.panel.revalidate();
                                                    }

                                                    index.incrementAndGet();
                                                    addAnime(bayor.title, ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL(bayor.images.getJpg().largeImageUrl), 450, 450), bayor.malId);
                                                });
                                            },
                                            throwable -> {
                                                System.out.println("error : " + throwable.getMessage());
                                            }
                                    );
                        } catch (JikanQueryException ex) {
                            throw new RuntimeException(ex);
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            if (JikanAPI.searchAnimeByTitle(search.getText()).size() == 0) {
                                JOptionPane.showMessageDialog(null, "Anime Tersebut Tidak Dapat Di Temukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Anime Berhasil Di Temukan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (JikanQueryException ex) {
                            throw new RuntimeException(ex);
                        }
                    };

                };

                addSearchCollection.execute();

            }

        });

        divNav.add(Box.createVerticalStrut(80));
        divNav.add(logo);

        JLabel margin = new JLabel();
        margin.setOpaque(true);
        margin.setPreferredSize(new Dimension(450, 20));
        margin.setMaximumSize(new Dimension(450, 20));
        margin.setBackground(Color.ORANGE);

        divNav.add(margin);

        JLabel margin2 = new JLabel();
        margin2.setOpaque(true);
        margin2.setPreferredSize(new Dimension(400, 20));
        margin2.setMaximumSize(new Dimension(400, 20));
        margin2.setBackground(Color.ORANGE);

        divNav.add(search);
        divNav.add(margin2);
        divNav.add(dashboard);
        divContainer.add(divNav);

        return divContainer;
    }

    /**
     * Method untuk mengset search user di panel search navbar
     * @param search
     */
    public static void setSearchByUser(String search) {
        searchByUser = search;
    }

    /**
     * Method untuk menambahkan top card anime
     */
    public static void addTopCardAnime() {
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

    static AtomicInteger index = new AtomicInteger();
    static int stopWhenTen = 1;

    /**
     * Method untuk menambahkan recomendation anime
     */
    public static void addRecomendationAnime() {
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
                                        if (isHalalAnime(bayor) && stopWhenTen <= 10) {
                                            cardRecomendationAnime.addCard(bayor.title, ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL(bayor.images.getJpg().largeImageUrl), 450, 450), bayor.malId);
                                            ++stopWhenTen;
                                        }
                                        if (stopWhenTen == 10) return;
                                    }
                            );
                        },
                        throwable -> {
                            JOptionPane.showMessageDialog(null, "Maaf Internal Server Error, Klik Ok Untuk Mengrefresh Lagi.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                            throwable.printStackTrace();
                            index.set(1);
                            addRecomendationAnime();
                        }
                );
        if (index.get() == 1) {
            index.set(0);
            return;
        }
        Controller.addComponent(recomendationAnimeDiv);
        stopWhenTen = 1;
    }

    /**
     * Method untuk mengecek apakah animenya bukan anime 18++
     * @param anime => anime object
     * @return true jika anime haram dan sebaliknya
     */
    private static boolean isHalalAnime(Anime anime) {
        return anime.rating == AgeRating.PG13;
    }

    /**
     * Method untuk mendapatkan search panel ke main frame
     */
    public static void addSearchAnimeCard() {
        Controller.addComponent(searchAnimeDiv);
    }

    /**
     * Method untuk menghapus search component pada main frame
     */
    public static void removeSearchAnimeCard() {
        Controller.removeComponent(searchAnimeDiv);
    }

    /**
     * Method untuk menghapus top card component pada main frame
     */
    public static void removeTopCardComponent() {
        Controller.removeComponent(topAnimeDiv);
    }

    /**
     * Method untuk menghapus recomendation panel dari main frame
     */
    public static void removeRecomdendationCardComponent() {
        Controller.removeComponent(recomendationAnimeDiv);
    }

    /**
     * Method untuk mendapakan object cardSearchAnime
     * @return object cardSearchAnime
     */
    public static CardSearchAnime getCardSearch() {
        return cardSearchAnime;
    }

    /**
     * Method untuk menambahkan anime kotak2 pada search panel
     * @param title
     * @param image
     * @param id
     */
    public static void addAnime(String title,ImageIcon image, int id) {
        getCardSearch().addCard(title, image, id);
    }

    /**
     * Method untuk mengsingkronisasi GUI ketika user mengdelete sesuatu pada component
     */
    public static void syncDelete() {
        cardTopAnime.removePanel();
        cardRecomendationAnime.removePanel();

        removeTopCardComponent();
        removeRecomdendationCardComponent();
    }

}

