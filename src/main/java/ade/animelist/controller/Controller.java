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
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


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
        frame.setSize((int) screenSize.getWidth(),(int) screenSize.getHeight());
        frame.getContentPane().setBackground(Color.ORANGE);
        frame.setResizable(false);

        if (screenSize.getWidth() == 1366 && screenSize.getHeight() == 768) {
            createLoginHD();
        }
        if (screenSize.getWidth() == 1920 && screenSize.getHeight() == 1080) {
            createLogin();
        }

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

    public static void removeSignUpHD() {
        ade.animelist.components.hd.SignUp.container.removeAll();
        removeComponent(ade.animelist.components.hd.SignUp.container);
    }

    public static void createSignUp() {
        addComponent(SignUp.getSignUp());
    }
    public static void createSignUpHD() {
        addComponent(ade.animelist.components.hd.SignUp.getSignUp());
    }

    public static void removeLogin() {
        Login.container.removeAll();
        Login.container.repaint();
        Login.container.revalidate();
        removeComponent(Login.container);
    }

    public static void removeLoginHD() {
        ade.animelist.components.hd.Login.container.removeAll();
        ade.animelist.components.hd.Login.container.repaint();
        ade.animelist.components.hd.Login.container.revalidate();
        removeComponent(ade.animelist.components.hd.Login.container);
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

    public static void createDasshboardHD() {
        ade.animelist.components.hd.Navbar.addNavbar();
//        System.out.println((Navbar.navbar == null) + " navbar log");
        addComponent(ade.animelist.components.hd.Navbar.navbar);
        ade.animelist.components.hd.Dashboard.isOpened = true;
        addComponent(ade.animelist.components.hd.Dashboard.getDashboard());
    }


    public static void removeDashboard() {
        Dashboard.dashboardDiv.removeAll();
        removeComponent(Dashboard.dashboardDiv);
    }

    public static void removeDashboardHD() {
        ade.animelist.components.hd.Dashboard.dashboardDiv.removeAll();
        removeComponent(ade.animelist.components.hd.Dashboard.dashboardDiv);
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
