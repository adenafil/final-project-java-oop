package ade.animelist.components.fhd;

import ade.animelist.controller.Controller;
import net.sandrohc.jikan.exception.JikanQueryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Gui Kotak kotak ketika mencari anime
 */
public class CardSearchAnime {
    public boolean isOpened = false;
    private int indexAddUpAnime;
    JPanel cardPanel;
    private final int CARD_WIDTH = 300;
    private final int CARD_HEIGHT = 400;
    private int[] x = {0, 350, 700, 1050, 1400};
    private int index = 0;
    // will up constantly when index ==4 do 20 + 300;
    private int normalY = 20;
    JPanel panel = new JPanel();

    /**
     * Method untuk mendapatkan kartu pada saat mencari anime
     * @return komponent card-card atau kotak-kotak
     */
    public JPanel getCard() {
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setPreferredSize(new Dimension(1920, 940));
        scrollPane.setOpaque(true);
        scrollPane.getViewport().getView().setBackground(Color.decode("#333b48"));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(Color.decode("#333b48"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        JLabel topAnime = new JLabel("Search Anime");
        topAnime.setOpaque(true);
        topAnime.setBackground(Color.decode("#333b48"));
        topAnime.setForeground(Color.WHITE);
        topAnime.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        topAnime.setPreferredSize(new Dimension(1600, 30));
        topAnime.setAlignmentX(JLabel.LEFT);
        panel.add(topAnime);
        panel.setPreferredSize(new Dimension(1920, 970));
        panel.setBackground(Color.decode("#333b48"));
        panel.setLayout(new FlowLayout());
        panel.add(scrollPane);
        panel.setBorder(BorderFactory.createEmptyBorder());

        return panel;
    }

    /**
     * Method untuk menambahkan kotak-kotak
     * @param tileAnime => judul anime
     * @param imgAnime => Gambar anime
     * @param id => id anime
     */
    public void addCard(String tileAnime, ImageIcon imgAnime, int id) {
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
        gbc.anchor = GridBagConstraints.NORTH;

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
                            AnimePage.isOpened = true;
                            isOpened = false;
                            Navbar.removeSearchAnimeCard();
                            Controller.addComponent(AnimePage.getAnimePageById(id));
                            Controller.doScync();

                            Controller.doScync();
                        } catch (JikanQueryException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
        );

    }

    /**
     * Method untuk set index kotak-kotak
     * @param indexAddUpAnime
     */
    public void setIndexAddUpAnime(int indexAddUpAnime) {
        this.indexAddUpAnime = indexAddUpAnime;
    }

    /**
     * Method untuk menghapus cardPanel
     */
    public void removeData() {
        if (indexAddUpAnime > 0) {
            cardPanel.removeAll();
        }
        setIndexAddUpAnime(0);
    }


}
