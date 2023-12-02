package ade.myanimelist.anime.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableListAnime {
    JTable table;
    public JTable getTableAnimeList() {

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

        table = new JTable(model);
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


        return table;
    }
}
