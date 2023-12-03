package ade.myanimelist.anime.gui.home;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class RoundedPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();
        int arc = 20; // radius sudut melingkar

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, arc, arc);
        g2d.setColor(Color.BLUE); // warna panel
        g2d.fill(roundedRectangle);

        g2d.dispose();
    }
}

public class Tes {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Rounded Panel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RoundedPanel roundedPanel = new RoundedPanel();
        roundedPanel.setPreferredSize(new Dimension(200, 200));

        frame.getContentPane().add(roundedPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
