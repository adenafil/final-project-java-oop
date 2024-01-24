package ade.animelist.components.fhd;

import ade.animelist.controller.Controller;
import ade.animelist.database.repository.AddAnimeToDbRepository;
import ade.animelist.database.repository.AddAnimeToDbRepositoryImpl;
import ade.animelist.components.utilcomponent.AnimeListWorker;
import ade.animelist.components.utilcomponent.ImageRenderer;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Gui Class MyCollection
 */
public class CardCollection {
    public static boolean isOpened = false;
    private static int indexAddUpAnime;
    public static int totalAnime = -1;

    public static JPanel cardPanel;
    public static JPanel panel = new JPanel();
    private static final int CARD_WIDTH = 300;
    private static final int CARD_HEIGHT = 400;
    private static int[] x = {0, 350, 700, 1050, 1400};
    private static int index = 0;
    // will up constantly when index ==4 do 20 + 300;
    private static int normalY = 0;
    static ImageIcon refreshImg = ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL("https://img.icons8.com/ios-filled/50/ffffff/refresh--v1.png"), 20, 20);
    static ImageIcon hoverRefresh = ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL("https://img.icons8.com/ios-filled/50/FFBF00/refresh--v1.png"), 20, 20);
    public static JLabel refresh = new JLabel("refresh");
    public static int countRefreshClicked = 0;

    /**
     * Method untuk mendapakan component myCollection
     * @return myCollection Panel
     */
    public static JLabel getLabelRefresh() {
        refresh.setOpaque(true);
        refresh.setBackground(Color.decode("#333b48"));
        refresh.setForeground(Color.WHITE);
        refresh.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        refresh.setPreferredSize(new Dimension(1600, 30));
        refresh.setAlignmentX(JLabel.LEFT);
        refresh.setIcon(refreshImg);
        CardCollection.setIndex(0);

        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AddAnimeToDbRepository listAnimeuser = new AddAnimeToDbRepositoryImpl();

                if (countRefreshClicked != 3) {
                    List<CompletableFuture<Anime>> animeFutures = listAnimeuser.getAllAnimeListUserAsync();
                    AnimeListWorker animeListWorker = new AnimeListWorker(animeFutures);
                    animeListWorker.execute();
                    ++countRefreshClicked;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                refresh.setForeground(Color.ORANGE);
                refresh.setIcon(hoverRefresh);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                refresh.setForeground(Color.decode("#FFFFFF"));
                refresh.setIcon(refreshImg);
            }



        });


        return refresh;
    }

    /**
     * Method untuk mendapatkan card panel
     * @return card panel
     */
    public static JPanel getCardPanel() {
        return panel = getCard();
    }

    /**
     * Method untuk merenove cardPanel
     */
    public static void removeCardPanel() {
        panel.removeAll();
        panel.repaint();
        panel.revalidate();
    }


    /**
     * Method untuk mendapatkan panel card
     * @return card panel
     */
    public static JPanel getCard() {
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setPreferredSize(new Dimension(1920, 940));
        scrollPane.setOpaque(true);
        scrollPane.getViewport().getView().setBackground(Color.decode("#333b48"));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(Color.decode("#333b48"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        JLabel topAnime = new JLabel("My Collection");
        topAnime.setOpaque(true);
        topAnime.setBackground(Color.decode("#333b48"));
        topAnime.setForeground(Color.WHITE);
        topAnime.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        topAnime.setPreferredSize(new Dimension(1600, 30));
        topAnime.setAlignmentX(JLabel.LEFT);

        panel.add(topAnime);
        panel.add(getLabelRefresh());
        panel.setPreferredSize(new Dimension(1920, 960));
        panel.setBackground(Color.decode("#333b48"));
        panel.setLayout(new FlowLayout());
        panel.add(scrollPane);
        panel.setBorder(BorderFactory.createEmptyBorder());

        return panel;
    }

    /**
     * Method untuk menambahkan kotak-kotak atau card
     * @param tileAnime judul anime
     * @param imgAnime gambar
     * @param id -> malID
     */
    public static void addCard(String tileAnime, ImageIcon imgAnime, int id) {
        indexAddUpAnime++;

        if (tileAnime.length() > 40) {
            String temp = "";
            for (int i = 0; i < 40; i++) {
                temp += tileAnime.charAt(i);
            }

            String titik = ".....";
            temp = temp + titik;

            tileAnime = temp;
        }

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setBackground(Color.decode("#333b48"));

        JLabel img = new JLabel();
        img.setOpaque(true);
        img.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 100));
        img.setBackground(Color.decode("#333b48"));
        img.setIcon(imgAnime);
        img.setVerticalAlignment(SwingConstants.CENTER);
        img.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel title = new JLabel("<html><p> " + tileAnime + " </p></html>");
        title.setOpaque(true);
        title.setPreferredSize(new Dimension(CARD_WIDTH, 60));
        title.setForeground(Color.WHITE);
        title.setBackground(Color.decode("#333b48"));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.BOLD, 22));

        JLabel idAnime = new JLabel(id + "");
        idAnime.setFont(new Font(null, Font.BOLD, 0));

        card.add(img);
        card.add(title);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x[index];
        gbc.gridy = normalY;
        gbc.insets = new Insets(10, 10, 10, 10);
        cardPanel.add(card, gbc);

        index++;
        if (index > 4) {
            index = 0;
            normalY += 300;
        }


        card.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        title.setForeground(Color.ORANGE);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        super.mouseReleased(e);
                        title.setForeground(Color.decode("#333b48"));
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        try {
                            Navbar.syncDelete();

                            Controller.removeComponent(panel);
                            panel.removeAll();

                            AnimePage.isOpened = true;
                            isOpened = false;
                            Controller.addComponent(AnimePage.getAnimePageById(id));
                            Controller.doScync();

                            Controller.doScync();
                        } catch (JikanQueryException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
        );


        cardPanel.revalidate();
        cardPanel.repaint();
    }


    /**
     * Method untuk setIndex kotak-kotak
     * boasa digunakan untuk set index menjadi 0
     * @param indexx no index
     */
    public static void setIndex(int indexx) {
        index = indexx;
    }


}
