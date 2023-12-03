package ade.myanimelist.anime.api;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class JikanAPI {
    private static Jikan jikan;

    public static List<Anime> searchAnimeByTitle(String title) throws JikanQueryException {
        jikan = new Jikan();

        List<Anime> searchResult = jikan.query().anime().search().query(title).execute().collectList().block();

        return searchResult;
    }

    public static Object[][] animeToDataTable(List<Anime> animeList) throws IOException {
        int totalAnime = lengthAnimeResultOfAPI(animeList);
        Object[][] result = new Object[totalAnime][6];
        int i = 0;
        for (Anime anime : animeList) {
            result[i][0] = anime.malId;
            result[i][1] = createImageIconByURL(anime.images.jpg.largeImageUrl);
            result[i][2] = anime.title;
            result[i][3] = anime.score;
            result[i][4] = anime.rating;
            result[i][5] = anime.episodes;
            i++;
        }

        return result;
    }

    public static int lengthAnimeResultOfAPI(List<Anime> animeList) {
        int total = 0;
        for (Anime anime : animeList) {
            total++;
        }
        return total;
    }

    public static ImageIcon createImageIconByURL(String imgaeURL) throws IOException {
        Image image = ImageIO.read(new URL(imgaeURL));
        if (image != null) return new ImageIcon(image);
        return null;
    }

//    public static void main(String[] args) throws JikanQueryException {
//        List<Anime> animeList = searchAnimeByTitle("naruto");
//
//        Object[][] result = animeToDataTable(animeList);
//
//        for (Object[] obj : result) {
//            for (Object e : obj) {
//                System.out.println(e);
//            }
//        }
//    }

}
