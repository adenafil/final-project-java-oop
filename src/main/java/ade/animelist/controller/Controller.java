package ade.animelist.controller;

import ade.animelist.components.fhd.Dashboard;
import ade.animelist.components.fhd.Login;
import ade.animelist.components.fhd.Navbar;
import ade.animelist.components.fhd.SignUp;
import ade.animelist.components.utilcomponent.ImageRenderer;

import javax.swing.*;
import java.awt.*;

public class Controller {
//    public static Navbar navbar = new Navbar();
    public static JFrame frame =  new JFrame();
    private static ImageIcon imageIcon = new ImageIcon("src/main/resources/assets/icon.jpg");

//    static {
//        ImageRenderer.runConfig();
//    }


    public static void run() {
        SwingUtilities.invokeLater(Controller::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        frame.setIconImage(ImageRenderer.setImageIconSize(imageIcon, 32, 32).getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEADING, -8, 0));
//        frame.setSize(1920, 1080);
        frame.setSize(1366, 768);
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setResizable(false);
        createLoginHD();

//        addComponent(navbar.getNavbar());
//        navbar.addTopCardAnime();
//        navbar.addRecomendationAnime();
//        try {
//            AnimePage animePage = new AnimePage();
//            addComponent(animePage.getAnimePageById(20));
//        } catch (JikanQueryException e) {
//            e.printStackTrace();
//        }
////        navbar.addHomeAnime();

//        CardSearchAnime searchAnime = new CardSearchAnime();
//        CardTopAnime cardTopAnime = new CardTopAnime();
//        addComponent(cardTopAnime.getCard());
//        frame.add(navbar.getNavbar());
//        addComponent(navbar.getNavbar());
//        navbar.setCardSearchAnime(searchAnime);
//        frame.add(searchAnime.getCard());
//        frame.add(cardTopAnime.getCard());

        frame.setVisible(true);

    }

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

    public static void removeSignUp() {
        SignUp.container.removeAll();
        removeComponent(SignUp.container);
    }

    public static void createSignUp() {
        addComponent(SignUp.getSignUp());
    }

    public static void removeLogin() {
        Login.container.removeAll();
        Login.container.repaint();
        Login.container.revalidate();
        removeComponent(Login.container);
    }

    public static void createLogin() {
        addComponent(Login.getLogin());
    }

    public static void createLoginHD() {
        addComponent(ade.animelist.components.hd.Login.getLogin());
    }

    public static void createDasshboard() {
        Navbar.addNavbar();
//        System.out.println((Navbar.navbar == null) + " navbar log");
        addComponent(Navbar.navbar);
        Dashboard.isOpened = true;
        addComponent(Dashboard.getDashboard());
    }

    public static void removeDashboard() {
        Dashboard.dashboardDiv.removeAll();
        removeComponent(Dashboard.dashboardDiv);
    }


    public static void removeComponent(JPanel div) {
        if (div != null) {
            frame.remove(div);
            frame.setVisible(true);
            frame.repaint();
            frame.revalidate();
        }
    }

    public static void doScync() {
        frame.repaint();
        frame.revalidate();
    }
}
