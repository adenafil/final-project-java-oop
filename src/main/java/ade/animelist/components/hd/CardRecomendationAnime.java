package ade.animelist.components.hd;

import ade.animelist.components.utilcomponent.ImageRenderer;
import ade.animelist.controller.Controller;
import net.sandrohc.jikan.exception.JikanQueryException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardRecomendationAnime {
    public boolean isOpened = false;
    JPanel cardPanel;

//    AnimePage animePage = new AnimePage();
    private static final int CARD_WIDTH = 240;
    private static final int CARD_HEIGHT = 350;
    private static int[] x = {0, 350, 700, 1050, 1400};
    public static int index = 0;
    // will up constantly when index ==4 do 20 + 300;
    private static int normalY = 20;
    ImageIcon refreshImg = ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL("https://img.icons8.com/ios-filled/50/ffffff/refresh--v1.png"), 20, 20);
    ImageIcon hoverRefresh = ImageRenderer.setImageIconSize(ImageRenderer.createImageIconByURL("https://img.icons8.com/ios-filled/50/FFBF00/refresh--v1.png"), 20, 20);


    JLabel refresh;

    public JLabel getLabelRefresh() {
        refresh = new JLabel("refresh");
        refresh.setOpaque(true);
        refresh.setBackground(Color.decode("#333b48"));
//        refresh.setBackground(Color.CYAN);
        refresh.setForeground(Color.WHITE);
        refresh.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        refresh.setPreferredSize(new Dimension(1300, 30));
        refresh.setAlignmentX(JLabel.LEFT);
        refresh.setIcon(refreshImg);

        refresh.addMouseListener(new MouseAdapter() {
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

    public JPanel getCard() {

        if (refresh == null) {
            refresh = getLabelRefresh();
        }


        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());


        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setPreferredSize(new Dimension(1366, 370));
        scrollPane.setOpaque(true);
        scrollPane.getViewport().getView().setBackground(Color.decode("#333b48"));
//        scrollPane.getViewport().getView().setBackground(Color.GREEN);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setBackground(Color.decode("#333b48"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(30);
//        scrollPane.setBackground(Color.BLACK);
//        scrollPane.setForeground(Color.YELLOW);
//        scrollPane.setBackground(Color.RED);
        System.out.println(scrollPane.getBackground());
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel topAnime = new JLabel("Rekomendasi Anime");
        topAnime.setOpaque(true);
        topAnime.setBackground(Color.decode("#333b48"));
//        topAnime.setBackground(Color.PINK);
        topAnime.setForeground(Color.WHITE);
        topAnime.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        topAnime.setPreferredSize(new Dimension(1300, 20));
        topAnime.setAlignmentX(JLabel.LEFT);

        // bikin getter refresh sehingga ntar lu bisa add Event Listener di sini
//        refresh = new JLabel("refresh");
//        refresh.setOpaque(true);
//        refresh.setBackground(Color.decode("#333b48"));
//        refresh.setForeground(Color.WHITE);
//        refresh.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
//        refresh.setPreferredSize(new Dimension(1600, 30));
//        refresh.setAlignmentX(JLabel.LEFT);
//        refresh.setIcon(refreshImg);


        JPanel panel = new JPanel();
        panel.add(topAnime);
        panel.add(refresh);
        panel.setPreferredSize(new Dimension(1366, 370));
        panel.setBackground(Color.decode("#333b48"));
//        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new FlowLayout());
//        panel.setLayout(null);
//        panel.setBounds(20, 20, 1000, 1000);
        panel.add(scrollPane);
        panel.setBorder(BorderFactory.createEmptyBorder());

        return panel;
    }

    public void removePanel() {
        this.cardPanel.removeAll();
    }


    public void addCard(String titleAnime, ImageIcon imgAnime, int id, int count) {

        if (titleAnime.length() > 25) {
            String temp = "";
            for (int i = 0; i < 25; i++) {
                temp += titleAnime.charAt(i);
            }

            String titik = ".....";
            temp = temp + titik;

            titleAnime = temp;
        }


//        System.out.println("debug : " + x[index]);

        JPanel card = new JPanel();
        if (count <= 10) {
            System.out.println("count " + count);
            card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 55));
        }
        if (count >= 6) card.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 30));
        card.setBackground(Color.decode("#333b48"));

        JLabel img = new JLabel();
        img.setOpaque(true);

        if (count <= 5)  img.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 150));
        else if (count >= 6) img.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT - 200));

        img.setBackground(Color.decode("#333b48"));
        img.setIcon(imgAnime);

        JLabel title = new JLabel("<html><p> " + titleAnime + " </p></html>");
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
//        gbc.fill = GridBagConstraints.BOTH;
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
                        System.out.println("mama huhu");
                        System.out.println(title.getText());
                        System.out.println(id);
                        try {
//                            Controller.navbar.getRecomendationAnimeDiv().removeAll();
//                            Controller.navbar.getTopAnime().removeAll();
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

//        Controller.navbar.logo.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (animePage.contaienrDiv != null) {
//                    super.mouseClicked(e);
//                    animePage.removeContainer();
//                    System.out.println("log in CardRecomendation");
//                    cardPanel.revalidate();
//                    cardPanel.repaint();
//                }
////                Controller.navbar.addTopCardAnime();
////                Controller.navbar.addRecomendationAnime();
//            }
//        });


    }

    public JLabel getRefresh() {
        System.out.println(refresh == null);
        return this.refresh;
    }

}

