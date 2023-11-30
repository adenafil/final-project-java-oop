import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.model.character.CharacterAnime;
import net.sandrohc.jikan.model.character.CharacterBasic;
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
            System.out.println(anime.titleEnglish);
            System.out.println(anime.images.getJpg().largeImageUrl);
            System.out.println(anime.episodes);
            System.out.println(anime.season);
            System.out.println(anime.year);
            System.out.println(anime.type);
            System.out.println(anime.genres);
            System.out.println(anime.rank);
            System.out.println(anime.score);
            System.out.println(anime.popularity);
            System.out.println(anime.synopsis);
            System.out.println(anime.source);
            System.out.println(anime.studios);
            System.out.println(anime.aired);
            System.out.println(anime.rating);
        }
    }

    @Test
    void testGetCharacterAnime() throws JikanQueryException {
        Jikan jikan = new Jikan();

        List<CharacterBasic> characterList = jikan.query()
                .anime()
                .characters(52991)
                .execute()
                .collectList()
                .block();

        for (CharacterBasic character : characterList) {
            System.out.println(character.getCharacter().name);
            System.out.println(character.character.images.getJpg().imageUrl);
        }
    }
}
