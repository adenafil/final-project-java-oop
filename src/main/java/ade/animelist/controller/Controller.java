package ade.animelist.controller;

import ade.animelist.components.fhd.Dashboard;
import ade.animelist.components.fhd.Login;
import ade.animelist.components.fhd.Navbar;
import ade.animelist.components.fhd.SignUp;
import ade.animelist.components.utilcomponent.ImageRenderer;

import javax.swing.*;
import java.awt.*;

/**
 * Class untuk mengontron main frame
 */

public class Controller {
    // Membuat main frame
    public static JFrame frame =  new JFrame();
    // Membuat logo
    private static ImageIcon imageIcon = new ImageIcon("src/main/resources/assets/icon.jpg");
    // Mengambil ukuran resolution layar laptop
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Method untuk run aplikasi
     */
    public static void run() {
        SwingUtilities.invokeLater(Controller::createAndShowGUI);
    }

    /**
     * Method untuk membuat frame visible sekaligus mendirect ke halaman login
     * sekaligus memvalidasi apakah layar laptopnya hd atau fhd
     * jika hd tampilkan layout hd dan sebaliknya
     */
    public static void createAndShowGUI() {
        frame.setIconImage(ImageRenderer.setImageIconSize(imageIcon, 32, 32).getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEADING, -8, 0));
        frame.setTitle("animeList");
        frame.setSize((int) screenSize.getWidth(),(int) screenSize.getHeight());
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setResizable(false);

        // cek ukuran layar
        if (screenSize.getWidth() == 1366 && screenSize.getHeight() == 768) {
            createLoginHD();
        }
        if (screenSize.getWidth() == 1920 && screenSize.getHeight() == 1080) {
            createLogin();
        }

        frame.setVisible(true);

    }

    /**
     * Method untuk menambahkan component ke dalam frame
     * @param div => sebuah Jpanel atau component
     */
    public static void addComponent(JPanel div) {
        frame.add(div);
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();
    }

    public static void createAdeAnimeList() {
        addComponent(Navbar.navbar);
        Navbar.addTopCardAnime();
        Navbar.addRecomendationAnime();
    }

    /**
     * Method untuk merove signup panel dari main frame
     */
    public static void removeSignUp() {
        SignUp.container.removeAll();
        removeComponent(SignUp.container);
    }

    /**
     * Method untuk meremove signup panel dari main frame HD
     */
    public static void removeSignUpHD() {
        ade.animelist.components.hd.SignUp.container.removeAll();
        removeComponent(ade.animelist.components.hd.SignUp.container);
    }

    /**
     * Method untuk menampilkan sign up ke dalam main frame
     */
    public static void createSignUp() {
        addComponent(SignUp.getSignUp());
    }

    /**
     * Method untuk menampilkan sign up hd ke dalam main frame
     */
    public static void createSignUpHD() {
        addComponent(ade.animelist.components.hd.SignUp.getSignUp());
    }

    /**
     * Method untuk menghapus panel login ke dalam main frame
     */
    public static void removeLogin() {
        Login.container.removeAll();
        Login.container.repaint();
        Login.container.revalidate();
        removeComponent(Login.container);
    }

    /**
     * Method untuk menghapus panel login hd ke dalam main frame
     */
    public static void removeLoginHD() {
        ade.animelist.components.hd.Login.container.removeAll();
        ade.animelist.components.hd.Login.container.repaint();
        ade.animelist.components.hd.Login.container.revalidate();
        removeComponent(ade.animelist.components.hd.Login.container);
    }

    /**
     * Method untuk menampilkan panel login ke dalam main frame
     */
    public static void createLogin() {
        addComponent(Login.getLogin());
    }

    /**
     * Method untuk menampilkan panel hd login ke dalam main frame
     */
    public static void createLoginHD() {
        addComponent(ade.animelist.components.hd.Login.getLogin());
    }

    /**
     * Method untuk menambahkan dashboard ke dalam main frame
     */
    public static void createDasshboard() {
        Navbar.addNavbar();
        addComponent(Navbar.navbar);
        Dashboard.isOpened = true;
        addComponent(Dashboard.getDashboard());
    }

    /**
     * Method untuk menambahkan dashboard hd ke dalam main frame
     */
    public static void createDasshboardHD() {
        ade.animelist.components.hd.Navbar.addNavbar();
        addComponent(ade.animelist.components.hd.Navbar.navbar);
        ade.animelist.components.hd.Dashboard.isOpened = true;
        addComponent(ade.animelist.components.hd.Dashboard.getDashboard());
    }


    /**
     * Method untuk menghapus dashboard ke dalam main frame
     */
    public static void removeDashboard() {
        Dashboard.dashboardDiv.removeAll();
        removeComponent(Dashboard.dashboardDiv);
    }

    /**
     * Method untuk menghapus dashboard hd ke dalam main frame
     */
    public static void removeDashboardHD() {
        ade.animelist.components.hd.Dashboard.dashboardDiv.removeAll();
        removeComponent(ade.animelist.components.hd.Dashboard.dashboardDiv);
    }

    /**
     * Method untuk menghapus component di dalam main frame
     * @param div => sebuah component atau Jpanel
     */
    public static void removeComponent(JPanel div) {
        if (div != null) {
            frame.remove(div);
            frame.setVisible(true);
            frame.repaint();
            frame.revalidate();
        }
    }

    /**
     * Method untuk melakukan repaint atau revalidate pada gui
     * sehingga tampilan singkron ketika client mengklik sebuah api gui
     */
    public static void doScync() {
        frame.repaint();
        frame.revalidate();
    }
}
