package ade.myanimelist.anime.gui;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AnimeListSwing extends JFrame {
//    private static ImageIcon dummyData = new ImageIcon(ResizeImageIcon.setImageIconSize(new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/dummyData.jpg"), 200, 200).getImage());
    private static ImageIcon profileImg = new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/profile.png");
    private static ImageIcon listImg = new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/list.png");
    private static ImageIcon searchImg = new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/search.png");
    private JLabel profile;
    private JLabel list;
    private JLabel search;

    private static JLabel logo() {
        JLabel logo = new JLabel("MyAnimeList", SwingConstants.CENTER);
        logo.setForeground(Color.decode("#ffffff"));
        logo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        logo.setBackground(Color.decode("#373130"));
        logo.setBounds(0, 15, 380, 50);
        logo.setOpaque(true);

        return logo;
    }

    private static JLabel subProfile(String text, ImageIcon img, int x, int y) {
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

    public AnimeListSwing() {
        profile = subProfile("PROFILE", profileImg, 0, 80);
        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("okay");
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
                System.out.println("okday");
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

        JScrollPane rightPanel = new JScrollPane();
        rightPanel.setBackground(Color.yellow);
        rightPanel.setBounds(420, 100, 1450, 900);
        rightPanel.setOpaque(true);
//        rightPanel.setLayout(null);

        // make table and dummy data for a developing need
        Object[][] data = {
                {1, new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/dummyData.jpg"),
                       "Sousou No Frieren", 9.12, "PG13", "10/28"
                },
                {2, new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/dummyData.jpg"),
                        "Sousou No Frieren", 9.12, "PG13", "10/28"
                },
                {3, new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/dummyData.jpg"),
                        "Sousou No Frieren", 9.12, "PG13", "10/28"
                },
                {4, new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/dummyData.jpg"),
                        "Sousou No Frieren", 9.12, "PG13", "10/28"
                },
                {5, new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/dummyData.jpg"),
                        "Sousou No Frieren", 9.12, "PG13", "10/28"
                },
                {6, new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/dummyData.jpg"),
                        "Sousou No Frieren", 9.12, "PG13", "10/28"
                },
        };

        String[] columnNames = {"#", "Image", "Anime Title", "Score", "Type", "Progress"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
          @Override
          public boolean isCellEditable(int row, int column) {
              return  false;
          }
        };

        JTable table = new JTable(model);
        table.setRowHeight(150);
//        table.setLayout(null);
//        table.setBounds(500, 100, 500, 500);
//        table.setOpaque(true);
        table.setDefaultRenderer(Object.class, new ImageRenderer());

        // set the hight of header

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 60));

        // Color rendering table

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                if (row >= 0 && col >= 0) {
                    Object value = table.getValueAt(row, col);
                    System.out.println("Clicked on row  " + row + " adn column "
                    + col + ". Value : " + value
                    );
                }
            }
        });

        table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());

        // set width table by column index
        table.getColumnModel().getColumn(0).setPreferredWidth(1);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(1);
        table.getColumnModel().getColumn(4).setPreferredWidth(1);
        table.getColumnModel().getColumn(5).setPreferredWidth(1);


        rightPanel.add(table);
        rightPanel.setViewportView(table);

        // end table

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.setResizable(false);
//        panelLeft.add(logo());
//        panelLeft.add(profile);
//        panelLeft.add(list);
//        panelLeft.add(search);
        PanelLeft tes = new PanelLeft();
        this.add(tes.getPanelLeft());
        this.add(rightPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AnimeListSwing();
        });
    }

    static class ImageRenderer extends DefaultTableCellRenderer {
        @Override
        protected void setValue(Object value) {
            if (value instanceof ImageIcon) {
                ImageIcon original = (ImageIcon) value;
                ImageIcon resizedImg = ResizeImageIcon.setImageIconSize(original, 300, 300);
                setIcon(resizedImg);
                setText(null);  // Clear text when displaying an icon
            } else {
                super.setValue(value);
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // set the bg by odd or even
            if (row % 2 == 0) {
                renderer.setBackground(Color.WHITE);
            } else {
                renderer.setBackground(Color.decode("#f6f6f6"));
            }

            setHorizontalAlignment(SwingConstants.CENTER);

            return renderer;
        }


    }



}
