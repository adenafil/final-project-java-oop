package ade.animelist.components.hd;

import ade.animelist.controller.Controller;
import net.sandrohc.jikan.exception.JikanQueryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GUI Class Top Card Kotak-Kotak
 */
public class CardTopAnime {
    public boolean isOpened = false;
    JPanel cardPanel;

    private static final int CARD_WIDTH = 240;
    private static final int CARD_HEIGHT = 300;
    private static int[] x = {0, 350, 700, 1050, 1400};
    private static int index = 0;
    // will up constantly when index ==4 do 20 + 300;
    private static int normalY = 20;
    JPanel panel = new JPanel();

    /**
     * Method untuk mendapatkan component div panel card
     * @return panel
     */
    public JPanel getCard() {

        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setPreferredSize(new Dimension(1366, 300));
        scrollPane.setOpaque(true);
        scrollPane.getViewport().getView().setBackground(Color.decode("#333b48"));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(Color.decode("#333b48"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(30);
        JLabel topAnime = new JLabel("Top Anime");
        topAnime.setOpaque(true);
        topAnime.setBackground(Color.decode("#333b48"));
        topAnime.setForeground(Color.WHITE);
        topAnime.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        topAnime.setPreferredSize(new Dimension(1300, 20));
        topAnime.setAlignmentX(JLabel.LEFT);

        panel.add(topAnime);
        panel.setPreferredSize(new Dimension(1366, 350));
        panel.setBackground(Color.decode("#333b48"));
        panel.setLayout(new FlowLayout());
        panel.add(scrollPane);
        panel.setBorder(BorderFactory.createEmptyBorder());


        return panel;
    }

    /**
     * Method untuk menghapus cardPanel
     */
    public void removePanel() {
        this.cardPanel.removeAll();
    }

    /**
     * Method untuk menambahkan kartu
     * @param titleAnime => Judul anime
     * @param imgAnime => ImageIcon anime
     * @param id => Mal id
     */
    public void addCard(String titleAnime, ImageIcon imgAnime, int id, int count) {

        if (titleAnime.length() > 30) {
            String temp = "";
            for (int i = 0; i < 30; i++) {
                temp += titleAnime.charAt(i);
            }

            String titik = ".....";
            temp = temp + titik;

            titleAnime = temp;
        }


        JPanel card = new JPanel();
        if (count <= 5) {
            card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 20));
        }
        if (count >= 6) card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        card.setBackground(Color.decode("#333b48"));

        JLabel img = new JLabel();
        img.setOpaque(true);
        img.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 100));
        img.setBackground(Color.decode("#333b48"));
        img.setIcon(imgAnime);

        JLabel title = new JLabel("<html><p> " + titleAnime +"</p></html>");
        title.setOpaque(true);
        title.setPreferredSize(new Dimension(CARD_WIDTH, 60));
        title.setForeground(Color.WHITE);
        title.setBackground(Color.decode("#333b48"));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SERIF, Font.BOLD, 22));

        card.add(img);
        card.add(title);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x[index];
        gbc.gridy = normalY;
        gbc.insets = new Insets(10, 10, 10, 10);
        cardPanel.add(card, gbc);

        JLabel idAnime = new JLabel(id + "");
        idAnime.setFont(new Font(null, Font.BOLD, 0));

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
                            Navbar.removeRecomdendationCardComponent();
                            Navbar.removeTopCardComponent();
                            AnimePage.isOpened = true;
                            isOpened = false;
                            Controller.addComponent(AnimePage.getAnimePageById(id));
                            Controller.doScync();
                        } catch (JikanQueryException ex) {
                            throw new RuntimeException(ex);
                        }


                    }
                }
        );

    }
}

