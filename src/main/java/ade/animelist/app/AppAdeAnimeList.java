package ade.animelist.app;
import ade.animelist.controller.Controller;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

/**
 * Main class atau application
 */
public class AppAdeAnimeList {
    /**
     * Main Method dari program ini
     * @param args
     */
    public static void main(String[] args) {
        FlatLightLaf.setup();
        Controller.run();
    }
}
