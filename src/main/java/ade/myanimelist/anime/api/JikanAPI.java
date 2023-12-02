package ade.myanimelist.anime.api;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;

import java.util.List;

public class JikanAPI {
    private static Jikan jikan;

    public static List<Anime> searchAnimeByTitle(String title) throws JikanQueryException {
        jikan = new Jikan();

        List<Anime> searchResult = jikan.query().anime().search().query(title).execute().collectList().block();

        return searchResult;
    }

    public static Object[][] animeToDataTable(List<Anime> animeList) {
        int totalAnime = lengthAnimeResultOfAPI(animeList);
        Object[][] result = new Object[totalAnime][6];
        int i = 0;
        for (Anime anime : animeList) {
            result[i][0] = i + 1;
            result[i][1] = anime.images.jpg.largeImageUrl;
            result[i][2] = anime.title;
            result[i][3] = anime.score;
            result[i][4] = anime.type;
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
