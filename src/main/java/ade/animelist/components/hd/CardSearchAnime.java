package ade.animelist.components.hd;

import ade.animelist.components.utilcomponent.ImageRenderer;
import ade.animelist.controller.Controller;
import net.sandrohc.jikan.exception.JikanQueryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardSearchAnime {
    public boolean isOpened = false;
    private int indexAddUpAnime;

//    public AnimePage animePage = new AnimePage();

    JPanel cardPanel;
    private final int CARD_WIDTH = 240;
    private final int CARD_HEIGHT = 300;
    private int[] x = {0, 350, 700, 1050, 1400};
    private int index = 0;
    // will up constantly when index ==4 do 20 + 300;
    private int normalY = 20;
    JPanel panel = new JPanel();

    public JPanel getCard() {

        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());


        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setPreferredSize(new Dimension(1366, 768));
//        scrollPane.setPreferredSize(new Dimension(1366, 3000));
        scrollPane.setOpaque(true);
        scrollPane.getViewport().getView().setBackground(Color.decode("#333b48"));
//        scrollPane.getViewport().getView().setBackground(Color.PINK);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(Color.decode("#333b48"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
//        scrollPane.getHorizontalScrollBar().setUnitIncrement(30);
//        scrollPane.setBackground(Color.BLACK);
//        scrollPane.setForeground(Color.YELLOW);
//        scrollPane.setBackground(Color.RED);
        System.out.println(scrollPane.getBackground());
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel topAnime = new JLabel("Search Anime");
        topAnime.setOpaque(true);
        topAnime.setBackground(Color.decode("#333b48"));
        topAnime.setForeground(Color.WHITE);
        topAnime.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        topAnime.setPreferredSize(new Dimension(1300, 25));
        topAnime.setAlignmentX(JLabel.LEFT);


        panel.add(topAnime);
        panel.setPreferredSize(new Dimension(1366, 800));
        panel.setBackground(Color.decode("#333b48"));
        panel.setLayout(new FlowLayout());
//        panel.setLayout(null);
//        panel.setBounds(20, 20, 1000, 1000);
        panel.add(scrollPane);
        panel.setBorder(BorderFactory.createEmptyBorder());

        return panel;
    }

    public void addCard(String tileAnime, ImageIcon imgAnime, int id) {
        indexAddUpAnime++;

//        imgAnime = ImageRenderer.setImageIconSize(imgAnime, CARD_WIDTH, CARD_HEIGHT- 150);

        if (tileAnime.length() > 25) {
            String temp = "";
            for (int i = 0; i < 25; i++) {
                temp += tileAnime.charAt(i);
            }

            String titik = ".....";
            temp = temp + titik;

            tileAnime = temp;
        }

        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 50));
        card.setBackground(Color.decode("#333b48"));

        JLabel img = new JLabel();
        img.setOpaque(true);
        img.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 150));
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
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;

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
                        System.out.println("mama huhu");
                        System.out.println(title.getText());
                        System.out.println(idAnime.getText());
                        System.out.println(id);
                        try {
//                            Controller.navbar.getRecomendationAnimeDiv().removeAll();
//                            Controller.navbar.getTopAnime().removeAll();
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

//        Controller.navbar.logo.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (animePage.contaienrDiv != null) {
//                    super.mouseClicked(e);
//                    animePage.removeContainer();
//                    System.out.println("log in card search Anime");
//                    cardPanel.revalidate();
//                    cardPanel.repaint();
//                }
////                Controller.navbar.addTopCardAnime();
////                Controller.navbar.addRecomendationAnime();
//            }
//        });
    }

    public int getIndexAddUpAnime() {
        return indexAddUpAnime;
    }

    public void setIndexAddUpAnime(int indexAddUpAnime) {
        this.indexAddUpAnime = indexAddUpAnime;
    }

    public void removeData() {
        if (indexAddUpAnime > 0) {
            cardPanel.removeAll();
        }
        setIndexAddUpAnime(0);
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
