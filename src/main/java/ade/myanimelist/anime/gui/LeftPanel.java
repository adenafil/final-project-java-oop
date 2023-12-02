package ade.myanimelist.anime.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LeftPanel {
    private JLabel profile;
    private JLabel list;
    private JLabel search;
    private RightPanel rightPanel;
    private JScrollPane rightPanelScroll;
    private FilterPanel filterPanel;
    private JPanel filterPanelComboBox;
    private static ImageIcon profileImg = new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/profile.png");
    private static ImageIcon listImg = new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/list.png");
    private static ImageIcon searchImg = new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/search.png");

    public JPanel getPanelLeft(JFrame frame) {
        filterPanel = new FilterPanel();
        filterPanelComboBox = new FilterPanel().getFilterPanel();
        rightPanel = new RightPanel();
        rightPanelScroll = rightPanel.getRightPanel();
        profile = subProfile("PROFILE", profileImg, 0, 80);
        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("okay");
                frame.remove(rightPanelScroll);
                frame.remove(filterPanelComboBox);
                frame.revalidate();
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                profile.setBackground(Color.decode("#ffffff"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                profile.setBackground(Color.decode("#373130"));
            }
        });
        list = subProfile("ANIME LIST", listImg, 0, 160);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.add(rightPanelScroll);
                frame.add(filterPanelComboBox);
                frame.revalidate();
                frame.repaint();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                list.setBackground(Color.decode("#ffffff"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                list.setBackground(Color.decode("#373130"));
            }
        });

        search = subProfile("SEARCH ANIME", listImg, 0, 240);
        search.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        frame.remove(rightPanelScroll);
                        frame.remove(filterPanelComboBox);
                        frame.revalidate();
                        frame.repaint();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        search.setBackground(Color.decode("#ffffff"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        search.setBackground(Color.decode("#373130"));
                    }

                }
        );


        JPanel panelLeft = new JPanel();
        panelLeft.setBackground(Color.decode("#373130"));
        panelLeft.setBounds(0, 0, 380, 1080);
        panelLeft.setLayout(null);

        panelLeft.add(logo());
        panelLeft.add(profile);
        panelLeft.add(list);
        panelLeft.add(search);

        return panelLeft;
    }

    public JLabel logo() {
        JLabel logo = new JLabel("MyAnimeList", SwingConstants.CENTER);
        logo.setForeground(Color.decode("#ffffff"));
        logo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        logo.setBackground(Color.decode("#373130"));
        logo.setBounds(0, 15, 380, 50);
        logo.setOpaque(true);

        return logo;
    }

    private JLabel subProfile(String text, ImageIcon img, int x, int y) {
        JLabel sub = new JLabel(text, SwingConstants.CENTER);
        sub.setForeground(Color.decode("#999999"));
        sub.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        sub.setIcon(ResizeImageIcon.setImageIconSize(img, 25, 25));
        sub.setIconTextGap(10);
        sub.setBackground(Color.decode("#373130"));
        sub.setBounds(x, y, 380, 50);
        sub.setOpaque(true);
        return sub;
    }

}
