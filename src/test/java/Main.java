import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

public class Main {
    @Test
    void testGetPopulerAnime() throws JikanQueryException {
        Jikan jikan = new Jikan();

        List<Anime> animes = jikan.query()
                .anime()
                .top()
                .execute()
                .collectList()
                .block();

        for (Anime anime : animes) {
            System.out.println(anime.title);
            System.out.println(anime.images.getJpg().largeImageUrl);
        }
    }
}
