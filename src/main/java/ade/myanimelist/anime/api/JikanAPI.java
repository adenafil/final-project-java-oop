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

}
